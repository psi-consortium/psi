/*
 * Copyright 2025 CGI Deutschland B.V. & Co. KG
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
package com.cgi.space.psi.tasks.transformers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.gradle.api.GradleException;

/**
 * Replaces all occurrences of the given property with a new definition.
 */
@ToString
@RequiredArgsConstructor
public class AddProperty implements OasTransformer {

    private final String schemaPattern;
    private final String propertyName;
    private final String propertyJson;

    @Override
    public void accept(OpenAPI api) {
        Schema newProperty;
        try {
            newProperty = Json.mapper().readValue(propertyJson, ObjectSchema.class);
        }
        catch (JsonProcessingException ex) {
            throw new GradleException("Invalid new property for " + schemaPattern + "." + propertyName, ex);
        }

        for (Entry<String, Schema> entry : api.getComponents().getSchemas().entrySet()) {
            if (entry.getKey().matches(schemaPattern)) {
                addProperty(entry.getValue(), newProperty);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private boolean addProperty(Schema schema, Schema newProperty) {
        final Map<String, Schema> properties = (Map<String, Schema>) schema.getProperties();
        if (properties != null) {
            properties.put(propertyName, newProperty);
            return true; // do not search allOf's
        }

        final List<Schema> allOf = (List<Schema>) schema.getAllOf();
        if (allOf != null) {
            for (Schema subschema : allOf) {
                if (addProperty(subschema, newProperty)) {
                    return true; // stop looking
                }
            }
        }
        return false;
    }

}
