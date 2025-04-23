/*
 * Copyright 2021 CGI Deutschland B.V. & Co. KG
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
package com.cgi.space.psi.pss.stub.controller;

import static com.cgi.space.psi.pss.stub.util.ControllerUtil.okOrNotFound;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.Resource;
import com.cgi.space.psi.common.model.ResourceFVO;
import com.cgi.space.psi.common.model.ResourceMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.ResourceApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.ResourceFilter;
import com.cgi.space.psi.pss.stub.service.ResourceInventoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link Resource}s Inventory.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/resourceInventory/v2")
@Profile("!" + Profiles.PROVIDER)
public class ResourceInventoryController implements ResourceApi {

    @Autowired
    private ResourceInventoryService service;

    /**
     * An endpoint for creating a new {@link Resource}.
     *
     * @param resourceCreate The {@link ResourceFVO} to be used to create {@link Resource}
     *            (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link Resource}
     */
    @Override
    @PsiOperation("TOD-05-01-01")
    public ResponseEntity<Resource> createResource(ResourceFVO resourceCreate, Optional<String> fields) {
        Resource result = service.createResource(resourceCreate);
        log.info("Created new resource: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for retrieving the {@link Resource} with given identifier.
     *
     * @param id Identifier of the Resource (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link Resource}
     */
    @Override
    @PsiOperation("TOD-05-01-04")
    public ResponseEntity<Resource> retrieveResource(String id, Optional<String> fields) {
        return okOrNotFound(service.getResource(id));
    }

    /**
     * An endpoint for listing all {@link Resource}s.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return All {@link Resource}s.
     */
    @Override
    @PsiOperation("TOD-05-01-05")
    public ResponseEntity<List<Resource>> listResource(Optional<ResourceFilter> filter,
            Optional<String> fields, Optional<Integer> offset,
            Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Resource> allResources = service.getAllResources(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allResources);
    }

    /**
     * An endpoint that removes an existing {@link Resource} with given identifier.
     *
     * @param id Identifier of the Resource (required)
     */
    @Override
    @PsiOperation("TOD-05-01-03")
    public ResponseEntity<Void> deleteResource(String id) {
        boolean result = service.deleteResource(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint that updates an {@link Resource} with given identifier.
     *
     * @param id Identifier of the Resource (required)
     * @param resourceUpdate The Resource to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link Resource}
     */
    @Override
    @PsiOperation("TOD-05-01-02")
    public ResponseEntity<Resource> patchResource(String id, ResourceMVO resourceUpdate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateResource(id, resourceUpdate), HttpStatus.OK);
    }
}
