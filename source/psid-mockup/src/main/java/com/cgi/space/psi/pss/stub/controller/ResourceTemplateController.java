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
import com.cgi.space.psi.common.model.ResourceSpecification;
import com.cgi.space.psi.common.model.ResourceSpecificationFVO;
import com.cgi.space.psi.common.model.ResourceSpecificationMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.ResourceTemplateApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.ResourceTemplateFilter;
import com.cgi.space.psi.pss.stub.service.ResourceTemplateService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for resource templates.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/resourceCatalog/v2")
@Profile("!" + Profiles.PROVIDER)
public class ResourceTemplateController implements ResourceTemplateApi {

    @Autowired
    private ResourceTemplateService service;

    /**
     * An endpoint for creating a new resource template.
     *
     * @param resourceTemplate The {@link ResourceSpecificationFVO} to be used to create a
     *            resource template (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created template.
     */
    @Override
    @PsiOperation("TOD-04-01-01")
    public ResponseEntity<ResourceSpecification> createResourceTemplate(ResourceSpecificationFVO resourceTemplate, Optional<String> fields) {
        ResourceSpecification result = service.createResourceTemplate(resourceTemplate);
        log.info("Created new resource template: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint that removes an existing resource template with given identifier.
     *
     * @param id Identifier of the resource template (required)
     */
    @Override
    @PsiOperation("TOD-04-01-03")
    public ResponseEntity<Void> deleteResourceTemplate(String id) {
        boolean result = service.deleteResourceTemplate(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint for listing all resource templates.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return All resource templates.
     */
    @Override
    @PsiOperation("TOD-04-01-05")
    public ResponseEntity<List<ResourceSpecification>> listResourceTemplate(Optional<ResourceTemplateFilter> filter,
            Optional<String> fields, Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<ResourceSpecification> allResourceTemplates = service.getAllResourceTemplates(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allResourceTemplates);
    }

    /**
     * An endpoint that updates a resource template with given identifier.
     *
     * @param id Identifier of the resource template (required)
     * @param resourceTemplate The {@link ResourceSpecificationMVO} to be used to update the
     *            resource template (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated resource template
     */
    @Override
    @PsiOperation("TOD-04-01-02")
    public ResponseEntity<ResourceSpecification> patchResourceTemplate(String id, ResourceSpecificationMVO resourceTemplate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateResourceTemplate(id, resourceTemplate), HttpStatus.OK);
    }

    /**
     * An endpoint for retrieving the resource template with given identifier.
     *
     * @param id Identifier of the resource template (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved resource template.
     */
    @Override
    @PsiOperation("TOD-04-01-04")
    public ResponseEntity<ResourceSpecification> retrieveResourceTemplate(String id, Optional<String> fields) {
        return okOrNotFound(service.getResourceTemplate(id));
    }
}
