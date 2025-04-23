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
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Delete all examples that are not used by any operation.
 */
@ToString
@AllArgsConstructor
public class PruneExamples implements OasTransformer {

    private static final String REF_PREFIX = "#/components/examples/";

    @Override
    public void accept(OpenAPI api) {
        if (api.getComponents().getExamples() == null) {
            return; // nothing to do
        }

        for (int i = 0; i < 10; i++) {
            Set<String> usedExamples = collectExamplesUsedByAPI(api);
            if (!api.getComponents().getExamples().keySet().removeIf(example -> !usedExamples.contains(example))) {
                break;
            }
        }
    }

    private Set<String> collectExamplesUsedByAPI(OpenAPI api) {
        Set<String> usedExamples = new HashSet<>();
        for (PathItem path : api.getPaths().values()) {
            for (Operation operation : path.readOperations()) {
                if (operation.getRequestBody() != null) {
                    usedExamples.addAll(collectExamplesUsedByRequestBodies(List.of(operation.getRequestBody())));
                }
                usedExamples.addAll(collectExamplesUsedByResponses(operation.getResponses().values()));
            }
        }
        usedExamples.addAll(collectExamplesUsedByRequestBodies(api.getComponents().getRequestBodies().values()));
        usedExamples.addAll(collectExamplesUsedByResponses(api.getComponents().getResponses().values()));
        return usedExamples;
    }

    private Collection<? extends String> collectExamplesUsedByRequestBodies(Collection<RequestBody> requestBodies) {
        Set<String> usedExamples = new HashSet<>();
        for (RequestBody requestBody : requestBodies) {
            if (requestBody.getContent() != null) {
                usedExamples.addAll(collectExamplesUsedByMediaTypes(requestBody.getContent().values()));
            }
        }
        return usedExamples;
    }

    private Set<String> collectExamplesUsedByResponses(Collection<ApiResponse> responses) {
        Set<String> usedExamples = new HashSet<>();
        for (ApiResponse response : responses) {
            if (response.getContent() != null) {
                usedExamples.addAll(collectExamplesUsedByMediaTypes(response.getContent().values()));
            }
        }
        return usedExamples;
    }

    private Set<String> collectExamplesUsedByMediaTypes(Collection<MediaType> mediaTypes) {
        Set<String> usedExamples = new HashSet<>();
        for (MediaType mediaType : mediaTypes) {
            if (mediaType.getExamples() != null) {
                for (Example example : mediaType.getExamples().values()) {
                    final String ref = example.get$ref();
                    if (ref != null && ref.startsWith(REF_PREFIX)) {
                        usedExamples.add(ref.substring(REF_PREFIX.length()));
                    }
                }
            }
        }
        return usedExamples;
    }

}
