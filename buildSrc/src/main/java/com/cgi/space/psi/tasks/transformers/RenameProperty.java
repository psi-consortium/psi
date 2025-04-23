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

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Renames a property in place.
 */
@ToString
@AllArgsConstructor
public class RenameProperty implements OasTransformer {

    private final String schemaPattern;
    private final String propertyPattern;
    private final String replacement;

    @Override
    public void accept(OpenAPI api) {
        for (Entry<String, Schema> entry : api.getComponents().getSchemas().entrySet()) {
            if (entry.getKey().matches(schemaPattern)) {
                renameProperty(entry.getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void renameProperty(Schema schema) {
        final Map<String, Schema> oldProperties = (Map<String, Schema>) schema.getProperties();
        if (schema.getProperties() != null) {
            final Map<String, Schema> newProperties = new LinkedHashMap<>();
            for (Entry<String, Schema> entry : oldProperties.entrySet()) {
                if (entry.getKey().matches(propertyPattern)) {
                    newProperties.put(entry.getKey().replaceAll(propertyPattern, replacement), entry.getValue());
                }
                else {
                    newProperties.put(entry.getKey(), entry.getValue());
                }
            }
            schema.setProperties(newProperties);
        }

        final List<Schema> allOf = (List<Schema>) schema.getAllOf();
        if (allOf != null) {
            for (Schema subschema : allOf) {
                renameProperty(subschema);
            }
        }
    }

}
