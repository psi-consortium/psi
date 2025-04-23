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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Changes the format of all occurrences of the given property.
 */
@ToString
@AllArgsConstructor
public class OverwritePropertyFormat implements OasTransformer {

    private final String schemaName;
    private final String propertyName;
    private final String propertyFormat;

    @Override
    public void accept(OpenAPI api) {
        for (Entry<String, Schema> entry : api.getComponents().getSchemas().entrySet()) {
            if (entry.getKey().matches(schemaName)) {
                overwritePropertyFormat(entry.getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void overwritePropertyFormat(Schema schema) {
        final Map<String, Schema> properties = (Map<String, Schema>) schema.getProperties();
        if (properties != null) {
            for (Entry<String, Schema> entry : properties.entrySet()) {
                if (entry.getKey().matches(propertyName)) {
                    entry.getValue().setFormat(propertyFormat);
                }
            }
        }

        final List<Schema> allOf = (List<Schema>) schema.getAllOf();
        if (allOf != null) {
            for (Schema subschema : allOf) {
                overwritePropertyFormat(subschema);
            }
        }
    }

}
