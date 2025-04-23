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
import com.cgi.space.psi.common.model.Organization;
import com.cgi.space.psi.common.model.OrganizationFVO;
import com.cgi.space.psi.common.model.OrganizationMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.OrganizationApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.OrganizationFilter;
import com.cgi.space.psi.pss.stub.service.OrganizationService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link Organization}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/partyManagement/v2")
@Profile("!" + Profiles.PROVIDER)
public class OrganizationController implements OrganizationApi {

    @Autowired
    private OrganizationService service;

    /**
     * An endpoint for creating a new {@link Organization}.
     *
     * @param organizationCreate The {@link OrganizationFVO} to be used to create {@link Organization} (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link Organization}
     */
    @Override
    @PsiOperation("TOD-01-01-01")
    public ResponseEntity<Organization> createOrganization(OrganizationFVO organizationCreate, Optional<String> fields) {
        Organization result = service.createOrganization(organizationCreate);
        log.info("Created new organization: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for listing all {@link Organization Organizations}.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit  Requested number of resources to be provided in response (optional)
     * @return All {@link Organization Organizations}.
     */
    @Override
    @PsiOperation("TOD-01-01-05")
    public ResponseEntity<List<Organization>> listOrganization(Optional<OrganizationFilter> filter, Optional<String> fields,
                                                               Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Organization> allOrganizations = service.getAllOrganizations(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allOrganizations);
    }

    /**
     * An endpoint for retrieving the {@link Organization} with given identifier.
     *
     * @param id     Identifier of the {@link Organization} (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link Organization}
     */
    @Override
    @PsiOperation("TOD-01-01-04")
    public ResponseEntity<Organization> retrieveOrganization(String id, Optional<String> fields) {
        return okOrNotFound(service.getOrganization(id));
    }

    /**
     * An endpoint that removes an existing {@link Organization} with given identifier.
     *
     * @param id Identifier of the Organization (required)
     */
    @Override
    @PsiOperation("TOD-01-01-03")
    public ResponseEntity<Void> deleteOrganization(String id) {
        boolean result = service.deleteOrganization(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint that updates an {@link Organization} with given identifier.
     *
     * @param id                 Identifier of the Organization (required)
     * @param organizationUpdate The Organization to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link Organization}
     */
    @Override
    @PsiOperation("TOD-01-01-02")
    public ResponseEntity<Organization> patchOrganization(String id, OrganizationMVO organizationUpdate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateOrganization(id, organizationUpdate), HttpStatus.OK);
    }

}
