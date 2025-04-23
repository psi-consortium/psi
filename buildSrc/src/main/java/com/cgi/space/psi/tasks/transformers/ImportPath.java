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
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.parser.OpenAPIV3Parser;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Imports a path (and the required tags) from another OAS file.
 */
@ToString
@AllArgsConstructor
public class ImportPath implements OasTransformer {

    private final String pattern;
    private final File file;

    @Override
    public void accept(OpenAPI api) {
        Set<String> tagsToImport = new LinkedHashSet<>();
        var otherOas = new OpenAPIV3Parser().read(file.toString(), null, null);
        for (Entry<String, PathItem> entry : otherOas.getPaths().entrySet()) {
            if (entry.getKey().matches(pattern)) {
                var paths = api.getPaths().computeIfAbsent(entry.getKey(), k -> entry.getValue());
                for (Entry<HttpMethod, Operation> operation : entry.getValue().readOperationsMap().entrySet()) {
                    paths.operation(operation.getKey(), operation.getValue());
                    tagsToImport.addAll(operation.getValue().getTags());
                }
            }
        }

        for (Tag tag : api.getTags()) {
            tagsToImport.remove(tag.getName());
        }

        if (otherOas.getTags() != null) {
            for (Tag tag : otherOas.getTags()) {
                if (tagsToImport.contains(tag.getName())) {
                    api.getTags().add(tag);
                }
            }
        }
    }

}
