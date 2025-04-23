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
import io.swagger.v3.oas.models.media.Discriminator;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.ToString;

import static java.util.stream.Collectors.toList;

/**
 * Delete all schemas that are not used by any operation or other schema anymore.
 */
@ToString
@AllArgsConstructor
public class PruneSchemas implements OasTransformer {

    private static final String REF_PREFIX = "#/components/schemas/";

    @Override
    public void accept(OpenAPI api) {
        // augment schema names for later use
        for (Entry<String, Schema> entry : api.getComponents().getSchemas().entrySet()) {
            entry.getValue().setName(entry.getKey());
        }

        for (int i = 0; i < 10; i++) {
            Set<String> usedSchemas = collectSchemasUsedByAPI(api);
            if (!api.getComponents().getSchemas().keySet().removeIf(schema -> !usedSchemas.contains(schema))) {
                break;
            }
        }
    }

    private Set<String> collectSchemasUsedByAPI(OpenAPI api) {
        Set<String> usedSchemas = new HashSet<>();
        for (PathItem path : api.getPaths().values()) {
            for (Operation operation : path.readOperations()) {
                usedSchemas.addAll(collectSchemasUsedByOperation(operation));
            }
        }
        if (api.getComponents().getRequestBodies() != null) {
            usedSchemas.addAll(collectSchemasUsedByRequestBodies(api.getComponents().getRequestBodies().values()));
        }
        if (api.getComponents().getResponses() != null) {
            usedSchemas.addAll(collectSchemasUsedByResponses(api.getComponents().getResponses().values()));
        }
        usedSchemas.addAll(collectSchemasUsedByOtherSchemas(api.getComponents().getSchemas().values()));
        return usedSchemas;
    }

    private Set<String> collectSchemasUsedByOperation(Operation operation) {
        Set<String> usedSchemas = new HashSet<>();
        if (operation.getParameters() != null) {
            usedSchemas.addAll(collectSchemasUsedByParameters(operation.getParameters()));
        }
        if (operation.getRequestBody() != null) {
            usedSchemas.addAll(collectSchemasUsedByRequestBodies(List.of(operation.getRequestBody())));
        }
        if (operation.getResponses() != null) {
            usedSchemas.addAll(collectSchemasUsedByResponses(operation.getResponses().values()));
        }
        return usedSchemas;
    }

    private Collection<? extends String> collectSchemasUsedByParameters(Collection<Parameter> parameters) {
        final List<Schema> parameterSchemas = parameters
                .stream()
                .map(p -> p.getSchema())
                .filter(s -> s != null)
                .collect(toList());
        return collectSchemasUsedByOtherSchemas(parameterSchemas);
    }

    private Set<String> collectSchemasUsedByRequestBodies(Collection<RequestBody> requestBodies) {
        Set<String> usedSchemas = new HashSet<>();
        final List<Schema> requestBodySchemas = requestBodies
                .stream()
                .filter(b -> b.getContent() != null)
                .flatMap(b -> b.getContent().values().stream())
                .map(mt -> mt.getSchema())
                .filter(s -> s != null)
                .collect(toList());
        usedSchemas.addAll(collectSchemasUsedByOtherSchemas(requestBodySchemas));
        return usedSchemas;
    }

    private Collection<? extends String> collectSchemasUsedByResponses(Collection<ApiResponse> responses) {
        final List<Schema> responseSchemas = responses
                .stream()
                .filter(r -> r.getContent() != null)
                .flatMap(r -> r.getContent().values().stream())
                .map(mt -> mt.getSchema())
                .filter(s -> s != null)
                .collect(toList());
        return collectSchemasUsedByOtherSchemas(responseSchemas);
    }

    @SuppressWarnings("unchecked")
    private Set<String> collectSchemasUsedByOtherSchemas(Collection<Schema> schemas) {
        Set<String> usedSchemas = new HashSet<>();
        for (Schema schema : schemas) {
            final String ref = schema.get$ref();
            if (ref != null && ref.startsWith(REF_PREFIX)) {
                usedSchemas.add(ref.substring(REF_PREFIX.length()));
            }

            final Discriminator discriminator = schema.getDiscriminator();
            if (discriminator != null && discriminator.getMapping() != null) {
                for (Entry<String, String> entry : discriminator.getMapping().entrySet()) {
                    if (entry.getValue().startsWith(REF_PREFIX)) {
                        final String value = entry.getValue().substring(REF_PREFIX.length());
                        if (!value.equals(schema.getName())) { // do not consider self-references
                            usedSchemas.add(value);
                        }
                    }
                }
            }

            final Map<String, Schema> properties = schema.getProperties();
            if (properties != null) {
                usedSchemas.addAll(collectSchemasUsedByOtherSchemas(properties.values()));
            }

            final List<Schema> allOf = schema.getAllOf();
            if (allOf != null) {
                usedSchemas.addAll(collectSchemasUsedByOtherSchemas(allOf));
            }

            final List<Schema> oneOf = schema.getOneOf();
            if (oneOf != null) {
                usedSchemas.addAll(collectSchemasUsedByOtherSchemas(oneOf));
            }

            final Schema items = schema.getItems();
            if (items != null) {
                usedSchemas.addAll(collectSchemasUsedByOtherSchemas(List.of(items)));
            }
        }
        return usedSchemas;
    }

}
