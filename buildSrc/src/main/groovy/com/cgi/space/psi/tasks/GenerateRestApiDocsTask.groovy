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

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.Paths
import io.swagger.v3.oas.models.media.BinarySchema
import io.swagger.v3.oas.models.tags.Tag
import io.swagger.v3.parser.OpenAPIV3Parser
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException;
import org.gradle.api.InvalidUserDataException
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

import com.cgi.space.psi.tasks.utils.OpenAPIUtil

/**
 * This task generates REST API documentation from an OpenAPI specification.
 */
class GenerateRestApiDocsTask extends DefaultTask {

    private static final String REQ_BODY_PREFIX = "#/components/requestBodies/";

    @InputDirectory
    File openApiDocsDir
    @InputFiles
    FileCollection todOperations
    @Internal
    Map<String, Set<String>> operationsMap = [:];

    @TaskAction
    void generateRestApiDocs() {
        List<RestEndpoint> restEndpoints = new ArrayList<>()
        openApiDocsDir.eachFileRecurse(groovy.io.FileType.FILES) { file ->
            if (file.isFile() && file.name.endsWith(".oas.json")) {
                restEndpoints.addAll(parseRestEndpoints(file.path))
            }
        }
        generateRestApiDocumentation(restEndpoints)
    }

    /**
     * We tag our REST controllers with a name and description which is then used in our generated documentation.
     * To include all the routes/paths under one controller we have to group the paths together with this tag.
     * This is done in this function.
     * @return the parsed RestEndpoints
     */
     List<RestEndpoint> parseRestEndpoints(String openApiDocsPath) {
        def openApi = new OpenAPIV3Parser().read(openApiDocsPath)
        def basePath = openApi.servers[0].url.replaceAll("https?://\\{?serverRoot\\}?", "")
        basePath = basePath.replaceAll("/psi-api", "")
        List<RestEndpoint> endpoints = openApi.tags.stream().map { tag ->
            def endpoint = new RestEndpoint(tag, basePath)
            openApi.paths.each { path ->
                if (path.value.readOperations().any { it.tags.contains(tag.name) }) {
                    flattenPath(openApi, path.value)
                    endpoint.paths.addPathItem(path.key.replaceFirst("/", ""), path.value)
                }
            }
            endpoint.paths = endpoint.paths.sort { a, b -> a.key <=> b.key } as Paths
            return endpoint
        }.collect(Collectors.toList())
        return endpoints.sort { a, b -> a.tag.name <=> b.tag.name }
    }

    void flattenPath(OpenAPI openApi, PathItem path) {
        for (def operation : path.readOperations()) {
            if (operation.requestBody && operation.requestBody.$ref) {
                def ref = operation.requestBody.$ref.substring(REQ_BODY_PREFIX.length())
                operation.requestBody = openApi.components.requestBodies.get(ref)
                if (operation.requestBody == null) {
                    throw new GradleException("RequestBody ${ref} not found")
                }
            }
        }
    }

    void generateRestApiDocumentation(List<RestEndpoint> restEndpoints) {
        def docDirPath = project.rootProject.projectDir.toPath().resolve("doc/PSI/PSI-ICD/rest-apis/")
        if (Files.exists(docDirPath)) {
            docDirPath.toFile().deleteDir()
        }
        docDirPath.toFile().mkdirs()
        def indexFile = docDirPath.resolve("index.md")
        indexFile.write("${OpenAPIUtil.generateFileHeader()}\n")

        def folderNameList = []
        restEndpoints.each {
            def folderName = it.tag.name.replaceAll(" ", "-").toLowerCase()
            folderNameList.add(folderName)

            def folder = docDirPath.resolve(folderName)
            folder.toFile().mkdir()
            generateEndpointDocumentation(folder, it)
        }
        folderNameList.sort().each(folderName -> {
            indexFile << "@include [$folderName]($folderName/index.md)\n"
        })
    }

    void generateEndpointDocumentation(Path endpointDir, RestEndpoint pathsByTag) {
        if (pathsByTag.tag.description == null) {
            throw new InvalidUserDataException("Missing description on tag '$pathsByTag.tag.name'")
        }

        def indexFile = endpointDir.resolve("index.md")
        def basePathWithoutVersion = pathsByTag.basePath.replaceAll("/v(\\d)/?", "")
        indexFile.write(OpenAPIUtil.generateFileHeader())
        indexFile << """
            # $pathsByTag.tag.name ($basePathWithoutVersion)

            $pathsByTag.tag.description

        """.stripIndent()

        pathsByTag.paths.each { paths ->
            paths.value.readOperationsMap().each { operationEntry ->
                def fileName = generateFileName(paths.key, operationEntry.key)
                indexFile << "@include [$fileName]($fileName)\n"

                def generateTableCaption = { String section ->
                    def label = "{#tbl:$fileName:${section.toLowerCase().replaceAll(" ", "_")}}"
                    return "\nTable: $section of $operationEntry.key $paths.key. $label"
                }
                def absolutePath = pathsByTag.basePath + paths.key
                endpointDir.resolve(fileName).toFile().withWriter { writer ->
                    writer.write(OpenAPIUtil.generateFileHeader())
                    writer << """
                        # $operationEntry.key /$paths.key

                        **Absolute Path:** $absolutePath

                    """.stripIndent()

                    def todOperation = operationsMap.get(operationEntry.key.toString() + ' ' + absolutePath)
                    if (todOperation != null) {
                        generationOperationTodSection(writer, todOperation)
                    }

                    def operation = operationEntry.value
                    if (operation.summary) {
                        writer.writeLine("**Summary:** $operation.summary")
                    }
                    if (operation.parameters) {
                        generateOperationParametersSection(writer, operation, generateTableCaption)
                    }
                    if (operation.requestBody) {
                        generateOperationRequestBodySection(writer, operation, generateTableCaption)
                    }
                    if (operation.responses) {
                        generateOperationResponseSection(writer, operation, generateTableCaption)
                    }
                    writer.writeLine('\n{#page:break}')
                }
            }
        }
    }

    void generationOperationTodSection(BufferedWriter writer, Set<String> todIndices) {
        String joined = todIndices.stream().map(this::toToReference).collect(Collectors.joining(", "))
        writer.writeLine("**TOD Reference:** $joined")
        writer.newLine()
    }

    String toToReference(String todIndex) {
        return todOperations.stream()
                .map((file) -> file.name.replace('.md', ''))
                .filter((name) -> name.startsWith(todIndex))
                .findFirst()
                .orElse(todIndex)
    }

    void generateOperationParametersSection(BufferedWriter writer, Operation operation, Closure<String> generateTableCaption) {
        writer.writeLine("""
            ## Parameters

            | Name | Type | Required | Description |
            | ------ | ------ | --- | ------------ |""".stripIndent())
        operation.parameters.each {
            writer.writeLine("| $it.name | ${it.schema == null ? "N/A" : it.schema.type} | ${it.required ? "Yes" : "No"} | ${it.description ?: "N/A"} |")
        }
        writer.writeLine(generateTableCaption("Parameters"))
    }

    void generateOperationRequestBodySection(BufferedWriter writer, Operation operation, Closure<String> generateTableCaption) {
        writer.writeLine("""
            ## Request Body

            | Content Type | Reference |
            |--------------|-----------|""".stripIndent())
        operation.requestBody.content.with { content ->
            // Our default media type is application/json therefore we replace */* with this value
            if (content.size() == 1 && content.keySet().first() == "*/*"
                && !(content.values().first().schema instanceof BinarySchema)) {
                writer.writeLine("| application/json | ${OpenAPIUtil.getRefFromSchema(content.values().first().schema)} |")
            } else {
                content.each {
                    writer.writeLine("| $it.key | ${OpenAPIUtil.getRefFromSchema(it.value.schema)} |")
                }
            }
        }
        writer.writeLine(generateTableCaption("Request Body"))
    }

    void generateOperationResponseSection(BufferedWriter writer, Operation operation, Closure<String> generateTableCaption) {
        writer.writeLine("""
            ## Responses

            | Code | Description | Content |
            |------|-------------|---------|""".stripIndent())
        operation.responses.each {
            def description = it.value.description ?: "N/A"
            def schemas = it.value.content?.values().stream()
                .filter(content -> content.schema?.$ref != null)
                .map(content -> OpenAPIUtil.getRefFromSchema(content.schema))
                .collect(Collectors.toSet())
            def content;
            if (schemas.size() == 0) {
                content = "N/A"
            } else if (schemas.size() == 1) {
                content = schemas[0]
            } else {
                getLogger().warn("Unhandled amount of response contents!")
                content = "Various responses"
            }
            writer.writeLine("| $it.key | $description | $content |")
        }
        writer.writeLine(generateTableCaption("Responses"))
    }

    static String generateFileName(String path, PathItem.HttpMethod method) {
        return path
        // Replace all non-whitelisted characters with a dash
                .replaceAll("[^a-zA-Z0-9_-]", "-")
        // Replace multiple dashes with a single one
                .replaceAll("-{2,}", "-")
        // Remove leading and trailing dashes
                .replaceAll("(^-|-\$)", "")
                .concat(".${method.toString().toLowerCase()}.md")
    }

    private class RestEndpoint {
        Tag tag
        String basePath
        Paths paths

        RestEndpoint(Tag tag, String basePath) {
            this.tag = tag
            this.basePath = basePath
            this.paths = new Paths()
        }
    }
}
