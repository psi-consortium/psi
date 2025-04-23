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
package com.cgi.space.psi.tasks.utils

import io.swagger.v3.oas.models.media.ArraySchema
import io.swagger.v3.oas.models.media.BinarySchema
import io.swagger.v3.oas.models.media.BooleanSchema
import io.swagger.v3.oas.models.media.ComposedSchema
import io.swagger.v3.oas.models.media.FileSchema
import io.swagger.v3.oas.models.media.IntegerSchema
import io.swagger.v3.oas.models.media.MapSchema
import io.swagger.v3.oas.models.media.NumberSchema
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.media.StringSchema
import io.swagger.v3.oas.models.media.UUIDSchema
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.util.stream.Collectors

/**
 * A utility class for working with the OpenAPI spec.
 */
class OpenAPIUtil {

    static final Logger logger = LoggerFactory.getLogger(CommandUtil.class)

    /**
     * The method returns the reference ($ref) property from a Schema.
     *
     * @param schema The schema where to look for the reference property.
     * @return The reference property found in the given Schema.
     */
    static String getRefFromSchema(Schema schema) {
        String reference
        if (schema instanceof ArraySchema) {
            if (schema.items.$ref != null) {
                reference = schema.items.$ref
            } else {
                reference = getRefFromSchema(schema.items)
            }
        } else if (schema instanceof ObjectSchema || schema instanceof MapSchema) {
            if (schema.additionalProperties == null) {
                reference = 'object'
            } else {
                reference = schema.additionalProperties.$ref
            }
        } else if (schema instanceof StringSchema) {
            reference = 'string'
        } else if (schema instanceof NumberSchema) {
            reference = 'number'
        } else if (schema instanceof BooleanSchema) {
            reference = 'boolean'
        } else if (schema instanceof IntegerSchema) {
            reference = 'integer'
        } else if (schema instanceof BinarySchema) {
            reference = 'binary'
        } else if (schema instanceof FileSchema) {
            reference = 'file'
        } else if (schema instanceof ComposedSchema) {
            List<Schema> references
            reference = ""
            if (schema.oneOf.size() > 0) {
                references = schema.oneOf
                reference = "oneOf"
            } else if (schema.anyOf.size() > 0) {
                references = schema.anyOf
                reference = "anyOf"
            } else if (schema.allOf.size() > 0) {
                references = schema.allOf
                reference = "allOf"
            }
            if (reference == "") {
                logger.error("Unhandled case for ComposedSchema: {}", schema)
                reference = "UNKNOWN"
            } else {
                reference = "$reference[${references.stream().map { getRefFromSchema(it) }.collect(Collectors.joining(", "))}]"
            }
        } else if (schema instanceof UUIDSchema) {
            reference = 'UUID'
        } else if (schema.$ref == null && schema.properties != null && schema.properties.size() == 1) {
            reference = getRefFromSchema(schema.properties.values().first())
        } else if (schema.$ref) {
            reference = schema.$ref
            if (reference.endsWith('/Any')) {
                return 'any'
            }
        } else {
            reference = 'object'
        }
        return reference.find("(?:[^\\/](?!(\\|/)))+\$")
    }

    static int getArrayDimension(Schema schema) {
        if (schema instanceof ArraySchema) {
            if (schema.items.$ref != null) {
                return 1
            } else {
                return getArrayDimension(schema.items) + 1
            }
        } else {
            return 0
        }
    }

    static String generateFileHeader() {
        return """\
            <!--
                ATTENTION: This file was generated via gradle!
                           Do NOT manually edit this file! Any such changes will be overwritten!
            -->
            """.stripIndent()
    }

}
