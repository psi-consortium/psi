/*
 * Copyright 2026 CGI Deutschland B.V. & Co. KG
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
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.Map.Entry;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Removes the request and response examples of an operation, including those of the request bodies and responses it
 * references. Unused examples are left for pruneExamples.
 */
@ToString
@AllArgsConstructor
public class RemoveOperationExamples implements OasTransformer {

    private static final String REQUEST_BODY_PREFIX = "#/components/requestBodies/";
    private static final String RESPONSE_PREFIX = "#/components/responses/";

    private final String pathPattern;
    private final String operationPattern;

    @Override
    public void accept(OpenAPI api) {
        for (Entry<String, PathItem> entry : api.getPaths().entrySet()) {
            if (entry.getKey().matches(pathPattern)) {
                for (Entry<HttpMethod, Operation> operation : entry.getValue().readOperationsMap().entrySet()) {
                    if (operation.getKey().name().toLowerCase().matches(operationPattern)) {
                        removeExamples(api, operation.getValue());
                    }
                }
            }
        }
    }

    private void removeExamples(OpenAPI api, Operation operation) {
        RequestBody requestBody = operation.getRequestBody();
        if (requestBody != null && requestBody.get$ref() != null) {
            requestBody = api.getComponents().getRequestBodies().get(requestBody.get$ref().replace(REQUEST_BODY_PREFIX, ""));
        }
        if (requestBody != null) {
            removeExamples(requestBody.getContent());
        }

        if (operation.getResponses() != null) {
            for (ApiResponse response : operation.getResponses().values()) {
                if (response.get$ref() != null) {
                    response = api.getComponents().getResponses().get(response.get$ref().replace(RESPONSE_PREFIX, ""));
                }
                if (response != null) {
                    removeExamples(response.getContent());
                }
            }
        }
    }

    private void removeExamples(Content content) {
        if (content == null) {
            return;
        }
        for (MediaType mediaType : content.values()) {
            mediaType.setExamples(null);
            mediaType.setExample(null);
            mediaType.setExampleSetFlag(false); // otherwise "example": null is written

        }
    }

}
