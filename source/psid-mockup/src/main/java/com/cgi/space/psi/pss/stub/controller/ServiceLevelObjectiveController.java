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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.ServiceLevelObjective;
import com.cgi.space.psi.common.model.ServiceLevelObjectiveCreate;
import com.cgi.space.psi.common.model.ServiceLevelObjectiveUpdate;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.ServiceLevelObjectiveApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.ServiceLevelObjectiveFilter;
import com.cgi.space.psi.pss.stub.service.ServiceLevelObjectiveService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for service level objectives.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/serviceQualityManagement/v2")
@Profile("!" + Profiles.PROVIDER)
public class ServiceLevelObjectiveController implements ServiceLevelObjectiveApi {

    @Autowired
    private ServiceLevelObjectiveService service;

    @Override
    @PsiOperation("TOD-06-01-01")
    public ResponseEntity<ServiceLevelObjective> createServiceLevelObjective(@Valid ServiceLevelObjectiveCreate slo) {
        ServiceLevelObjective result = service.createServiceLevelObjective(slo);
        log.info("Created new SLO: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    @PsiOperation("TOD-06-01-04")
    public ResponseEntity<ServiceLevelObjective> retrieveServiceLevelObjective(String id, @Valid Optional<String> fields) {
        return okOrNotFound(service.getServiceLevelObjective(id));
    }

    @Override
    @PsiOperation("TOD-06-01-05")
    public ResponseEntity<List<ServiceLevelObjective>> listServiceLevelObjective(
            @Valid Optional<ServiceLevelObjectiveFilter> filter,
            @Valid Optional<String> fields, @Valid Optional<Integer> offset, @Valid Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<ServiceLevelObjective> allServiceLevelObjectives = service.getAllServiceLevelObjectives(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allServiceLevelObjectives);
    }

    @Override
    @PsiOperation("TOD-06-01-02")
    public ResponseEntity<ServiceLevelObjective> patchServiceLevelObjective(String id, @Valid ServiceLevelObjectiveUpdate slo) {
        ServiceLevelObjective patchedSlo = service.updateServiceLevelObjective(id, slo);
        HttpStatus status = patchedSlo == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(patchedSlo, status);
    }

    @Override
    @PsiOperation("TOD-06-01-03")
    public ResponseEntity<Void> deleteServiceLevelObjective(String id) {
        boolean result = service.deleteServiceLevelObjective(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }
}
