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
package com.cgi.space.psi.pss.stub.service;

import com.cgi.space.psi.common.model.ExampleResultPayload;
import com.cgi.space.psi.common.model.MeasurementTime;
import com.cgi.space.psi.common.model.PerformanceJob;
import com.cgi.space.psi.common.model.PerformanceJobRef;
import com.cgi.space.psi.common.model.PerformanceJobValue;
import com.cgi.space.psi.common.model.PerformanceReport;
import com.cgi.space.psi.common.model.PerformanceReportComplexQuery;
import com.cgi.space.psi.common.model.PerformanceReportComplexQueryCreate;
import com.cgi.space.psi.common.model.PerformanceReportCreate;
import com.cgi.space.psi.common.model.PerformanceReportFind;
import com.cgi.space.psi.common.model.PerformanceReportRef;
import com.cgi.space.psi.common.model.PerformanceReportStateType;
import com.cgi.space.psi.common.model.ReportContentItem;
import com.cgi.space.psi.pss.stub.controller.PerformanceReportController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.PerformanceReportMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link PerformanceReport PerformanceReports}.
 */
@Service
public class PerformanceReportService {

    @Autowired
    private PerformanceReportMapper performanceReportMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link PerformanceReport} object.
     *
     * @param performanceReportCreate the {@link PerformanceReportCreate} object based on which
     *            the {@link PerformanceReport} is created.
     * @return the newly created {@link PerformanceReport} object.
     */
    public PerformanceReport createPerformanceReport(PerformanceReportCreate performanceReportCreate) {
        PerformanceReport report = performanceReportMapper.toPerformanceReport(performanceReportCreate);
        return createPerformanceReport(report);
    }

    /**
     * The method creates a new {@link PerformanceReport} object.
     * For "internal" use only.
     *
     * @param report the {@link PerformanceReport} to create.
     * @return the persisted created {@link PerformanceReport} object.
     */
    PerformanceReport createPerformanceReport(PerformanceReport report) {
        report.setId(UUID.randomUUID().toString());
        report.setCreationDate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));

        Link self = linkTo(methodOn(PerformanceReportController.class).retrievePerformanceReport(report.getId())).withSelfRel();
        report.setHref(self.toUri());

        // The processing could be async (and contain multiple chunks of data instead of one), the mockup simplifies it
        report.setState(PerformanceReportStateType.COMPLETED);
        report.addReportContentItem(
                new ReportContentItem()
                        .measurementTime(new MeasurementTime()
                                .measurementStartDate(report.getReportingTimeframe().getReportingStartDate())
                                .measurementEndDate(report.getReportingTimeframe().getReportingEndDate()))
                        .addMeasurementDataPointsItem(
                                new ExampleResultPayload()
                                        .packetsIn(1500)
                                        .charsIn(1500)
                                        .packetsOut(1500)
                                        .charsOut(1500)));

        return template.save(report);
    }

    /**
     * The method retrieves an existing {@link PerformanceReport}.
     *
     * @param id The Identifier of the {@link PerformanceReport} to be retrieved.
     * @return The retrieved {@link PerformanceReport}.
     */
    public PerformanceReport getPerformanceReport(String id) {
        return template.findById(id, PerformanceReport.class);
    }

    /**
     * The method retrieves all {@link PerformanceReport PerformanceReports}.
     *
     * @param pageable Collection of pagination parameters
     * @return all {@link PerformanceReport PerformanceReports}
     */
    public Page<PerformanceReportFind> getAllPerformanceReports(Pageable pageable) {
        return template.find(new Query(), pageable, PerformanceReport.class).map(performanceReportMapper::toPerformanceReportFind);
    }

    /**
     * The method retrieves all {@link PerformanceReport PerformanceReports} matching the given
     * query.
     * 
     * @param performanceReportComplexQuery the query
     * @return the matching {@link PerformanceReport PerformanceReports}
     */
    public List<PerformanceReportComplexQuery> getAllPerformanceReportsByFilter(PerformanceReportComplexQueryCreate performanceReportComplexQuery) {
        return template.find(buildQuery(performanceReportComplexQuery), PerformanceReport.class)
                .stream()
                .map(this::toPerformanceReportComplexQuery)
                .collect(toList());
    }

    private Query buildQuery(PerformanceReportComplexQueryCreate filter) {
        QueryBuilder builder = new QueryBuilder();
        builder.createEquals("state", filter.getState());
        builder.createEquals("performanceJob.consumingApplicationId", filter.getConsumingApplicationId());
        if (filter.getPerformanceJob() != null) {
            builder.createEquals("performanceJob._id", filter.getPerformanceJob().getId());
        }
        return builder.getQuery();
    }

    private PerformanceReportComplexQuery toPerformanceReportComplexQuery(PerformanceReport report) {
        var jobOrRef = report.getPerformanceJob();
        var result = new PerformanceReportComplexQuery();
        result.setCreationDate(report.getCreationDate());
        result.setDescription(report.getDescription());
        result.setPerformanceReport(new PerformanceReportRef().id(report.getId()).href(report.getHref()));
        result.setReportingTimeframe(report.getReportingTimeframe());
        result.setState(report.getState());

        if (jobOrRef instanceof PerformanceJobValue) {
            PerformanceJobValue job = (PerformanceJobValue) jobOrRef;
            result.setPerformanceJob(null);
            result.setConsumingApplicationId(job.getConsumingApplicationId());
            result.setProducingApplicationId(job.getProducingApplicationId());
            result.setGranularity(job.getGranularity());
            result.setOutputFormat(job.getOutputFormat());
            result.setResultFormat(job.getResultFormat());
            result.setServicePayloadSpecificAttributes(job.getServicePayloadSpecificAttributes());
        }
        else if (jobOrRef instanceof PerformanceJobRef) {
            PerformanceJob job = template.findById(((PerformanceJobRef) jobOrRef).getId(), PerformanceJob.class);
            result.setPerformanceJob((PerformanceJobRef) jobOrRef);
            result.setConsumingApplicationId(job.getConsumingApplicationId());
            result.setProducingApplicationId(job.getProducingApplicationId());
            result.setGranularity(job.getPerformanceProfile().getGranularity());
            result.setOutputFormat(job.getPerformanceProfile().getOutputFormat());
            result.setResultFormat(job.getPerformanceProfile().getResultFormat());
            result.setServicePayloadSpecificAttributes(job.getServicePayloadSpecificAttributes());
        }
        return result;
    }

}
