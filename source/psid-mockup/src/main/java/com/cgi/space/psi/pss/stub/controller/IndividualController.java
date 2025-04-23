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
import com.cgi.space.psi.common.model.Individual;
import com.cgi.space.psi.common.model.IndividualFVO;
import com.cgi.space.psi.common.model.IndividualMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.IndividualApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.IndividualFilter;
import com.cgi.space.psi.pss.stub.service.IndividualService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link Individual}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/partyManagement/v2")
@Profile("!" + Profiles.PROVIDER)
public class IndividualController implements IndividualApi {

    @Autowired
    private IndividualService service;

    /**
     * An endpoint for creating a new {@link Individual}.
     *
     * @param individualCreate The {@link IndividualFVO} to be used to create {@link Individual} (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link Individual}
     */
    @Override
    @PsiOperation("TOD-01-01-01")
    public ResponseEntity<Individual> createIndividual(IndividualFVO individualCreate, Optional<String> fields) {
        Individual result = service.createIndividual(individualCreate);
        log.info("Created new individual: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for listing all {@link Individual Individuals}.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit  Requested number of resources to be provided in response (optional)
     * @return All {@link Individual Individuals}.
     */
    @Override
    @PsiOperation("TOD-01-01-05")
    public ResponseEntity<List<Individual>> listIndividual(Optional<IndividualFilter> filter, Optional<String> fields,
                                                           Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Individual> allIndividuals = service.getAllIndividuals(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allIndividuals);
    }

    /**
     * An endpoint for retrieving the {@link Individual} with given identifier.
     *
     * @param id     Identifier of the {@link Individual} (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link Individual}
     */
    @Override
    @PsiOperation("TOD-01-01-04")
    public ResponseEntity<Individual> retrieveIndividual(String id, Optional<String> fields) {
        return okOrNotFound(service.getIndividual(id));
    }

    /**
     * An endpoint that removes an existing {@link Individual} with given identifier.
     *
     * @param id Identifier of the Individual (required)
     */
    @Override
    @PsiOperation("TOD-01-01-03")
    public ResponseEntity<Void> deleteIndividual(String id) {
        boolean result = service.deleteIndividual(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint that updates an {@link Individual} with given identifier.
     *
     * @param id               Identifier of the Individual (required)
     * @param individualUpdate The Individual to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link Individual}
     */
    @Override
    @PsiOperation("TOD-01-01-02")
    public ResponseEntity<Individual> patchIndividual(String id, IndividualMVO individualUpdate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateIndividual(id, individualUpdate), HttpStatus.OK);
    }
}
