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
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.PathItem.HttpMethod;
import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Removes an operation from a path.
 */
@ToString
@AllArgsConstructor
public class RemoveOperation implements OasTransformer {

    private final String pathPattern;
    private final String operationPattern;

    @Override
    public void accept(OpenAPI api) {
        for (Entry<String, PathItem> entry : api.getPaths().entrySet()) {
            if (entry.getKey().matches(pathPattern)) {
                removeOperation(entry.getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void removeOperation(PathItem path) {
        for (HttpMethod method : HttpMethod.values()) {
            if (method.name().toLowerCase().matches(operationPattern)) {
                path.operation(method, null);
            }
        }
    }

}
