/*
 * Copyright 2021 CGI Deutschland B.V. & Co. KG
 *
 * The copyright of this file is vested in CGI Deutschland B.V. & Co. KG.
 * Licensed under the CGI Space Germany License, Version 1.1 (the "License"); you may not use this file except in compliance with the License.
 *
 * If a copy of this license was not distributed with this file, please contact CGI Deutschland B.V. & Co. KG.
 *
 * This file may only be reproduced in whole or in part, or stored in a retrieval system, or transmitted in any form,
 * or by any means electronic, mechanical, photocopying or otherwise, either with the prior permission of CGI Deutschland B.V. & Co. KG
 * or in accordance with the terms of a contract made with CGI Deutschland B.V. & Co. KG on use of this source code.
 */
package com.cgi.space.psi.tasks

import com.cgi.space.psi.tasks.utils.OpenAPIUtil
import io.swagger.v3.oas.models.media.ArraySchema
import io.swagger.v3.oas.models.media.ComposedSchema
import io.swagger.v3.oas.models.media.Discriminator
import io.swagger.v3.oas.models.media.IntegerSchema
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.parser.OpenAPIV3Parser
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

import java.nio.file.Path
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * This task generates files containing schemas used throughout this REST API.
 */
class GenerateRestApiSchemasTask extends DefaultTask {

    private static final Pattern DUPLICATE_PATTERN = Pattern.compile("(.*)_[0-9]+\$")

    @InputDirectory
    File openApiDocsDir

    @OutputDirectory
    File outputDir

    protected Map<String, Schema> schemas = new HashMap<>()
    protected Map<String, List<String>> subtypes = new HashMap<>().withDefault { k -> new ArrayList<>() }

    @TaskAction
    void generateRestApiSchemas() {
        Map<String, List<String>> matchingSchemas = new HashMap<>()
        Map<String, List<String>> divergingSchemas = new HashMap<>()

        def inputFiles = []
        openApiDocsDir.eachFileRecurse(groovy.io.FileType.FILES) { file ->
            inputFiles << file
        }
        inputFiles.sort().each { file ->
            if (file.isFile() && file.name.endsWith(".oas.json")) {
                def openApi = new OpenAPIV3Parser().read(file.toPath().toString())
                def localSchemas = openApi.components.schemas

                localSchemas.each { key, value ->
                    Matcher matcher = DUPLICATE_PATTERN.matcher(key)
                    if (matcher.matches()) {
                        key = matcher.group(1)
                    }
                    def existingSchema = schemas.get(key)
                    if (existingSchema != null) {
                        if (schemaEquals(key, existingSchema, value))
                            matchingSchemas.get(key).add(file.name)
                        else
                            divergingSchemas.get(key).add(file.name)
                    } else {
                        matchingSchemas.put(key, new ArrayList<>(Arrays.asList(file.name)))
                        divergingSchemas.put(key, new ArrayList<>())
                        schemas.put(key, value)
                        collectSubtypes(key, value)
                    }
                }
            }
        }

        StringBuilder error = new StringBuilder()
        divergingSchemas.each { key, value ->
            if (!value.empty) {
                error.append("\n" + key + ": " + value + " != " + matchingSchemas.get(key))
            }
        }
        if (error.length() > 0) {
            throw new GradleException("The following schemas diverge: " + error);
        }

        if (outputDir.exists()) {
            outputDir.deleteDir()
        }
        outputDir.mkdirs()

        Path schemaDocsPath = outputDir.toPath()
        schemas.each { key, value ->
            if (!key.equals("Any") && !(value instanceof IntegerSchema)) {
                def filename = "${key}.md"
                def schemaFile = schemaDocsPath.resolve(filename).toFile()
                generateSchema(schemaFile, key, value)
            }
        }
    }

    boolean schemaEquals(String name, Schema schemaA, Schema schemaB) {
        if (schemaA == null) {
            logger.error(name + " missing on left side")
            return false
        }
        if (schemaB == null) {
            logger.error(name + " missing on right side")
            return false
        }

        if (schemaA.type != null && schemaB.type != null) {
            if (schemaA.type != schemaB.type) {
                logger.error(name + " diverging in type: " + schemaA.type + " != " + schemaB.type)
                return false
            }
        }

        if (schemaA.required != schemaB.required) {
            logger.error(name + " diverging in required fields: " + schemaA.required + " != " + schemaB.required)
            return false
        }
        if (schemaA.format != schemaB.format) {
            logger.error(name + " diverging in format: " + schemaA.format + " != " + schemaB.format)
            return false
        }
        if (schemaA.minItems != schemaB.minItems) {
            logger.error(name + " diverging in minItems: " + schemaA.minItems + " != " + schemaB.minItems)
            return false
        }
        if ((schemaA.properties == null) != (schemaB.properties == null)) {
            logger.error(name + " diverging in property existance: " + schemaA.properties + " != " + schemaB.properties)
            return false
        }

        if (schemaA.allOf && schemaB.allOf) {
            for (def i = 0; i < schemaA.allOf.size(); ++i) {
                if (!schemaEquals(name + ".allOf["+i+"]", schemaA.allOf[i], schemaB.allOf[i])) {
                    return false
                }
            }
        }
        if (schemaA.items || schemaB.items) {
            if (!schemaEquals(name + ".items", schemaA.items, schemaB.items)) {
                return false
            }
        }

        return !schemaA.properties.any { field, fieldSchema ->
            return !schemaEquals(name + "." + field, fieldSchema, schemaB.properties.get(field))
        } && !schemaB.properties.any { field, fieldSchema ->
            return !schemaEquals(name + "." + field, schemaA.properties.get(field), fieldSchema)
        };
    }

    void collectSubtypes(String schemaName, Schema schema) {
        if (schema instanceof ComposedSchema && schema.allOf) {
            for (def parent in schema.allOf) {
                if (parent.$ref) {
                    def parentName = OpenAPIUtil.getRefFromSchema(parent);
                    subtypes.get(parentName).add(schemaName);
                }
            }
        }
        if (schema instanceof ComposedSchema && schema.oneOf) {
            for (def child in schema.oneOf) {
                if (child.$ref) {
                    def childName = OpenAPIUtil.getRefFromSchema(child);
                    subtypes.get(schemaName).add(childName);
                }
            }
        }
    }

    void generateSchema(File file, String name, Schema schema) {
        file.withWriter { writer ->
            writer.writeLine(OpenAPIUtil.generateFileHeader())
            writer.writeLine("# $name")
            if (schema.description) {
                writer.newLine()
                writer.writeLine(schema.description.replace('. ', '.\n'))
            }
            writer.writeLine("""\

                | Field | Type | Format | Required |
                | ------- | ------- | ------- | --- |""".stripIndent())
            generateTableRows(writer, name, schema)
            writer.writeLine("\nTable: Fields of ${name}. {#tbl:${file.name}:$name}")
            writer.writeLine('\n{#page:break}')
        }
    }

    void generateTableRows(BufferedWriter writer, String name, Schema schema) {
        if (schema instanceof ComposedSchema) {
            schema = flatten(name, schema)
        }
        if (schema instanceof ObjectSchema) {
            if (schema.properties == null) return;
            def discriminator = schema.discriminator
            if (!discriminator) {
                // check if this schema is a implicit subtype of another one
                subtypes.each { key, val ->
                    if (val.contains(name) && schemas.containsKey(key)) {
                        discriminator = schemas.get(key).discriminator
                    }
                }
            }
            writeAllProperties(writer, name, schema.properties, schema.required, discriminator)
        }
        else if (schema instanceof StringSchema && schema.enum) {
            def required = schema.required?.contains(name) ? "Yes" : "No"
            def type = schema.type
            def format = "oneOf[${schema.enum.join(", ")}]"
            writer.writeLine("| ${name} | $type | $format | $required |")
        } else if (schema instanceof ArraySchema) {
            def required = schema.required? "Yes" : "No"
            def type = schema.type
            def format = schema.items.type
            writer.writeLine("| N/A | $type | $format | $required |")
        }
        else {
            //writer.writeLine("Unexpected schema type! Needs to be fixed!")
            // If this is ever hit we have to evaluate how to deal with it
            throw new GradleException(String.format("Unexpected schema type encountered on %s: %s",
                    name, schema.class))
        }
    }

    Schema flatten(String name, ComposedSchema schema) {
        def flattened = new ObjectSchema()
        flattened.properties = schema.properties as LinkedHashMap ?: new LinkedHashMap<>()
        flattened.required = schema.required
        flattened.discriminator = schema.discriminator

        for (Schema it in schema.allOf) {
            def refName = name
            if (it.$ref) {
                refName = OpenAPIUtil.getRefFromSchema(it)
                it = schemas.get(stripDuplicate(refName))
                if (it == null) {
                    throw new GradleException(String.format("Could not find %s, referenced by %s", refName, name))
                }
            }
            if (it instanceof ComposedSchema) {
                it = flatten(refName, it)
            }
            if (it.properties) {
                flattened.properties.putAll(it.properties)
            }
            if (it.required) {
                if (!flattened.required) {
                    flattened.required = it.required
                } else {
                    for (String reqProp in it.required) {
                        if (!flattened.required.contains(reqProp)) {
                            flattened.required.add(reqProp)
                        }
                    }
                }
            }
            if (!flattened.discriminator) {
                flattened.discriminator = it.discriminator
                if (flattened.discriminator && !flattened.discriminator.mapping) {
                    flattened.discriminator.mapping = findAllSubtypes(refName);
                }
            }
        }
        return flattened
    }

    void writeAllProperties(BufferedWriter writer, String schemaName, Map<String, Schema> properties, List<String> requiredProps, Discriminator discriminator) {
            def keys = properties.keySet() as ArrayList
            if (discriminator) {
                def index = keys.indexOf(discriminator.propertyName)
                if (index != -1) {
                    keys.removeAt(index)
                }
            }

            ["id", "href", "name", "description"].reverse().each { key ->
                def index = keys.indexOf(key)
                if (index != -1) {
                    keys.removeAt(index)
                    keys.push(key)
                }
            }
            ["@baseType", "@schemaLocation", "@type", "@referredType", "@valueSchemaLocation"].each { key ->
                def index = keys.indexOf(key)
                if (index != -1) {
                    keys.removeAt(index)
                    keys.add(key)
                }
            }

            keys.each { field ->
                def fieldSchema = properties[field]
                def required = requiredProps?.contains(field) ? "Yes" : "No"
                def type = stripDuplicate(fieldSchema.type ?: OpenAPIUtil.getRefFromSchema(fieldSchema))
                def format
                if (type == "string" && fieldSchema.enum) {
                    format = "oneOf[${fieldSchema.enum.join(", ")}]"
                } else if (type == "array") {
                    format = stripDuplicate(OpenAPIUtil.getRefFromSchema(fieldSchema))
                } else {
                    format = fieldSchema.format ?: "N/A"
                }
                def dimension = OpenAPIUtil.getArrayDimension(fieldSchema)
                if (dimension > 1) {
                    type = dimension + "D-" + type
                }

                writer.writeLine("| ${field} | $type | $format | $required |")
            }
            if (discriminator) {
                def subtypes = findAllSubtypes(schemaName)
                def discriminatorList = discriminator.mapping == null ? [] : discriminator.mapping.entrySet().stream()
                    .filter(entry -> subtypes.containsValue(entry.getValue()))
                    .map(entry -> entry.getValue().endsWith(schemaName) ? entry.getKey() : toDocRef(entry))
                    .map(str -> '"' + str + '"')
                    .collect()
                String format
                if (discriminatorList.isEmpty()) {
                    format = "N/A"
                } else if (discriminatorList.size() == 1) {
                    format = discriminatorList.get(0)
                } else {
                    format = "oneOf[" + discriminatorList.join(", ") + "]"
                }
                writer.writeLine("| ${discriminator.propertyName} | string | $format | Yes |")
            }
    }

    String toDocRef(Map.Entry<String, String> entry) {
        def ref = entry.value.replaceAll('(.*)\\/', '').replaceAll('\\W', '').toLowerCase()
        return "[${entry.key}](#${ref})"
    }

    String stripDuplicate(String typeName) {
        Matcher matcher = DUPLICATE_PATTERN.matcher(typeName)
        if (matcher.matches() && schemas.containsKey(matcher.group(1))) {
            return matcher.group(1)
        } else {
            return typeName
        }
    }

    Map<String, String> findAllSubtypes(String schemaName) {
        Map<String, String> map = new TreeMap<>()
        map.put(schemaName, "#/components/schemas/" + schemaName)
        for (def subtype : subtypes.get(schemaName)) {
            map.putAll(findAllSubtypes(subtype))
        }
        return map
    }

}
