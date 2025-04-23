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
import com.cgi.space.psi.common.model.Interval;
import com.cgi.space.psi.common.model.OutputFormat;
import com.cgi.space.psi.common.model.PerformanceReport;
import com.cgi.space.psi.common.model.PerformanceReportComplexQuery;
import com.cgi.space.psi.common.model.PerformanceReportComplexQueryCreate;
import com.cgi.space.psi.common.model.PerformanceReportCreate;
import com.cgi.space.psi.common.model.PerformanceReportFind;
import com.cgi.space.psi.common.model.ResultFormat;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.PerformanceReportApi;
import com.cgi.space.psi.pss.stub.api.PerformanceReportComplexQueryApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.service.PerformanceReportService;
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
 * REST Controller for managing endpoints for {@link PerformanceReport}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/performanceMonitoring/v2")
@Profile(Profiles.PROVIDER)
public class PerformanceReportController implements PerformanceReportApi, PerformanceReportComplexQueryApi {

    @Autowired
    private PerformanceReportService service;

    @Override
    @PsiOperation("TOD-06-05-01")
    public ResponseEntity<PerformanceReport> createPerformanceReport(PerformanceReportCreate performanceReportCreate) {
        PerformanceReport result = service.createPerformanceReport(performanceReportCreate);
        log.info("Created new performanceReport: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    @PsiOperation("TOD-06-05-02")
    public ResponseEntity<PerformanceReport> retrievePerformanceReport(String id) {
        return okOrNotFound(service.getPerformanceReport(id));
    }

    @Override
    @PsiOperation("TOD-06-05-03")
    public ResponseEntity<List<PerformanceReportFind>> listPerformanceReport(Optional<String> performanceJobId, Optional<String> state,
            Optional<OffsetDateTime> creationDateGt, Optional<OffsetDateTime> creationDateLt, Optional<OffsetDateTime> reportingTimeframeStartDateGt,
            Optional<OffsetDateTime> reportingTimeframeStartDateLt, Optional<OffsetDateTime> reportingTimeframeEndDateGt,
            Optional<OffsetDateTime> reportingTimeframeEndDateLt, Optional<Interval> granularity, Optional<OutputFormat> outputFormat,
            Optional<ResultFormat> resultFormat, Optional<String> consumingApplicationId, Optional<String> producingApplicationId, Optional<Integer> offset,
            Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<PerformanceReportFind> allPerformanceReports = service.getAllPerformanceReports(pageable);
        return OffsetBasedPageRequest.toResponse(allPerformanceReports);
    }

    @Override
    @PsiOperation("TOD-06-05-03")
    public ResponseEntity<List<PerformanceReportComplexQuery>>
            createPerformanceReportComplexQuery(@Valid PerformanceReportComplexQueryCreate performanceReportComplexQuery) {
        List<PerformanceReportComplexQuery> result = service.getAllPerformanceReportsByFilter(performanceReportComplexQuery);
        return ResponseEntity.ok(result);
    }

}
