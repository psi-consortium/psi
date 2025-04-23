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
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Delete all request bodies that are not used by any operation.
 */
@ToString
@AllArgsConstructor
public class PruneRequestBodies implements OasTransformer {

    private static final String REF_PREFIX = "#/components/requestBodies/";

    @Override
    public void accept(OpenAPI api) {
        if (api.getComponents().getRequestBodies() == null) {
            return; // nothing to do
        }

        for (int i = 0; i < 10; i++) {
            Set<String> usedRequestBodies = collectRequestBodiesUsedByAPI(api);
            if (!api.getComponents().getRequestBodies().keySet().removeIf(requestBody -> !usedRequestBodies.contains(requestBody))) {
                break;
            }
        }
    }

    private Set<String> collectRequestBodiesUsedByAPI(OpenAPI api) {
        Set<String> usedRequestBodies = new HashSet<>();
        for (PathItem path : api.getPaths().values()) {
            for (Operation operation : path.readOperations()) {
                if (operation.getRequestBody() != null) {
                    final String ref = operation.getRequestBody().get$ref();
                    if (ref != null && ref.startsWith(REF_PREFIX)) {
                        usedRequestBodies.add(ref.substring(REF_PREFIX.length()));
                    }
                }
            }
        }
        return usedRequestBodies;
    }

}
