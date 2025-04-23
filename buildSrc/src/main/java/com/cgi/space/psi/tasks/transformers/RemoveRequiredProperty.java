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
 * Removes a required property.
 */
@ToString
@AllArgsConstructor
public class RemoveRequiredProperty implements OasTransformer {

    private final String schemaPattern;
    private final String propertyPattern;

    @Override
    public void accept(OpenAPI api) {
        for (Entry<String, Schema> entry : api.getComponents().getSchemas().entrySet()) {
            if (entry.getKey().matches(schemaPattern)) {
                removeRequiredProperty(entry.getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void removeRequiredProperty(Schema schema) {
        if (schema.getRequired() != null) {
            for (var it = ((List<String>) schema.getRequired()).iterator(); it.hasNext();) {
                if (it.next().matches(propertyPattern)) {
                    it.remove();
                }
            }
            if (schema.getRequired().isEmpty()) {
                schema.setRequired(null);
            }
        }

        final List<Schema> allOf = (List<Schema>) schema.getAllOf();
        if (allOf != null) {
            for (Schema subschema : allOf) {
                removeRequiredProperty(subschema);
            }
        }
    }

}
