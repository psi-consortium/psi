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
 * Renames a schema in place.
 */
@ToString
@AllArgsConstructor
public class RenameSchema implements OasTransformer {

    private static final String REF_PREFIX = "#/components/schemas/";
    private final String pattern;
    private final String replacement;

    @Override
    public void accept(OpenAPI api) {
        Map<String, Schema> newSchemas = new LinkedHashMap<>();
        for (Entry<String, Schema> entry : api.getComponents().getSchemas().entrySet()) {
            if (entry.getKey().matches(pattern)) {
                newSchemas.put(entry.getKey().replaceAll(pattern, replacement), entry.getValue());
            }
            else {
                newSchemas.put(entry.getKey(), entry.getValue());
            }
            updateReferences(entry.getValue());
        }
        api.getComponents().schemas(newSchemas);
    }

    @SuppressWarnings("unchecked")
    private void updateReferences(Schema schema) {
        if (schema.getDiscriminator() != null) {
            for (Entry<String, String> entry : schema.getDiscriminator().getMapping().entrySet()) {
                if (entry.getValue().startsWith(REF_PREFIX)) {
                    String name = entry.getValue().substring(REF_PREFIX.length());
                    entry.setValue(REF_PREFIX + name.replaceAll(pattern, replacement));
                }
            }
        }
        if (schema.get$ref() != null && schema.get$ref().startsWith(REF_PREFIX)) {
            String name = schema.get$ref().substring(REF_PREFIX.length());
            schema.set$ref(name.replaceAll(pattern, replacement));
        }

        if (schema.getOneOf() != null) {
            for (Schema oneOf : (List<Schema>) schema.getOneOf()) {
                updateReferences(oneOf);
            }
        }
        if (schema.getAllOf() != null) {
            for (Schema allOf : (List<Schema>) schema.getAllOf()) {
                updateReferences(allOf);
            }
        }
        if (schema.getItems() != null) {
            updateReferences(schema.getItems());
        }
    }

}
