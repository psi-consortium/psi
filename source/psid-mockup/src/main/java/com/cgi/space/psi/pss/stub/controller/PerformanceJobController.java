/*
 * Copyright 2024 CGI Deutschland B.V. & Co. KG
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

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.CancelPerformanceJob;
import com.cgi.space.psi.common.model.CancelPerformanceJobCreate;
import com.cgi.space.psi.common.model.CancelPerformanceJobFind;
import com.cgi.space.psi.common.model.Interval;
import com.cgi.space.psi.common.model.JobType;
import com.cgi.space.psi.common.model.ModifyPerformanceJob;
import com.cgi.space.psi.common.model.ModifyPerformanceJobCreate;
import com.cgi.space.psi.common.model.ModifyPerformanceJobFind;
import com.cgi.space.psi.common.model.PerformanceJobCreate;
import com.cgi.space.psi.common.model.PerformanceJob;
import com.cgi.space.psi.common.model.PerformanceJobComplexQuery;
import com.cgi.space.psi.common.model.PerformanceJobComplexQueryCreate;
import com.cgi.space.psi.common.model.PerformanceJobFind;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.CancelPerformanceJobApi;
import com.cgi.space.psi.pss.stub.api.ModifyPerformanceJobApi;
import com.cgi.space.psi.pss.stub.api.PerformanceJobApi;
import com.cgi.space.psi.pss.stub.api.PerformanceJobComplexQueryApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.service.PerformanceJobService;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cgi.space.psi.pss.stub.util.ControllerUtil.okOrNotFound;

/**
 * REST Controller for managing endpoints for {@link PerformanceJob}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/performanceMonitoring/v2")
@Profile(Profiles.PROVIDER)
public class PerformanceJobController implements PerformanceJobApi, ModifyPerformanceJobApi, CancelPerformanceJobApi, PerformanceJobComplexQueryApi {

    @Autowired
    private PerformanceJobService service;

    @Override
    @PsiOperation("TOD-06-04-01")
    public ResponseEntity<PerformanceJob> createPerformanceJob(PerformanceJobCreate performanceJobCreate) {
        PerformanceJob result = service.createPerformanceJob(performanceJobCreate);
        log.info("Created new performanceJob: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    @PsiOperation("TOD-06-04-02")
    public ResponseEntity<ModifyPerformanceJob> createModifyPerformanceJob(ModifyPerformanceJobCreate modifyPerformanceJobCreate) {
        ModifyPerformanceJob result = service.modifyPerformanceJob(modifyPerformanceJobCreate);
        log.info("Modified performanceJob: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    @PsiOperation("TOD-06-04-03")
    public ResponseEntity<CancelPerformanceJob> createCancelPerformanceJob(CancelPerformanceJobCreate cancelPerformanceJobCreate) {
        CancelPerformanceJob result = service.cancelPerformanceJob(cancelPerformanceJobCreate);
        log.info("Cancelled performanceJob: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    @PsiOperation("TOD-06-04-04")
    public ResponseEntity<PerformanceJob> retrievePerformanceJob(String id) {
        return okOrNotFound(service.getPerformanceJob(id));
    }

    @Override
    @PsiOperation("TOD-06-04-05")
    public ResponseEntity<List<PerformanceJobFind>> listPerformanceJob(Optional<String> buyerJobId, Optional<String> state,
            Optional<OffsetDateTime> creationDateGt, Optional<OffsetDateTime> creationDateLt, Optional<JobType> jobType, Optional<Interval> granularity,
            Optional<Interval> reportingPeriod, Optional<String> consumingApplicationId, Optional<String> producingApplicationId, Optional<String> jobPriority,
            Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<PerformanceJobFind> allPerformanceJobs = service.getAllPerformanceJobs(pageable);
        return OffsetBasedPageRequest.toResponse(allPerformanceJobs);
    }

    @Override
    @PsiOperation("TOD-06-04-05")
    public ResponseEntity<List<PerformanceJobComplexQuery>>
            createPerformanceJobComplexQuery(@Valid PerformanceJobComplexQueryCreate performanceJobComplexQueryCreate) {
        List<PerformanceJobComplexQuery> result = service.getAllPerformanceJobsByFilter(performanceJobComplexQueryCreate);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ModifyPerformanceJob> retrieveModifyPerformanceJob(String id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @Override
    public ResponseEntity<List<ModifyPerformanceJobFind>> listModifyPerformanceJob(Optional<String> performanceJobId, Optional<String> state,
            Optional<OffsetDateTime> creationDateGt, Optional<OffsetDateTime> creationDateLt, Optional<Integer> offset, Optional<Integer> limit) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @Override
    public ResponseEntity<CancelPerformanceJob> retrieveCancelPerformanceJob(String id) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @Override
    public ResponseEntity<List<CancelPerformanceJobFind>> listCancelPerformanceJob(Optional<String> performanceJobId, Optional<String> state,
            Optional<OffsetDateTime> creationDateGt, Optional<OffsetDateTime> creationDateLt, Optional<Integer> offset, Optional<Integer> limit) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

}
