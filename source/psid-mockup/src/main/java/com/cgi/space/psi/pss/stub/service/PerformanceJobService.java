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

import com.cgi.space.psi.common.model.CancelPerformanceJob;
import com.cgi.space.psi.common.model.CancelPerformanceJobCreate;
import com.cgi.space.psi.common.model.ModifyPerformanceJob;
import com.cgi.space.psi.common.model.ModifyPerformanceJobCreate;
import com.cgi.space.psi.common.model.PerformanceJob;
import com.cgi.space.psi.common.model.PerformanceJobComplexQuery;
import com.cgi.space.psi.common.model.PerformanceJobComplexQueryCreate;
import com.cgi.space.psi.common.model.PerformanceJobCreate;
import com.cgi.space.psi.common.model.PerformanceJobFind;
import com.cgi.space.psi.common.model.PerformanceJobRef;
import com.cgi.space.psi.common.model.PerformanceJobStateType;
import com.cgi.space.psi.common.model.PerformanceReport;
import com.cgi.space.psi.common.model.ReportingTimeframe;
import com.cgi.space.psi.pss.stub.controller.PerformanceJobController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.PerformanceJobMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link PerformanceJob PerformanceJobs}.
 */
@Service
public class PerformanceJobService {

    @Autowired
    private PerformanceJobMapper performanceJobMapper;
    @Autowired
    private PerformanceReportService reportService;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link PerformanceJob} object.
     *
     * @param performanceJobCreate the {@link PerformanceJobCreate} object based on which
     *            the {@link PerformanceJob} is created.
     * @return the newly created {@link PerformanceJob} object.
     */
    public PerformanceJob createPerformanceJob(PerformanceJobCreate performanceJobCreate) {
        PerformanceJob job = performanceJobMapper.toPerformanceJob(performanceJobCreate);
        job.setId(UUID.randomUUID().toString());
        job.setCreationDate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));

        Link self = linkTo(methodOn(PerformanceJobController.class).retrievePerformanceJob(job.getId())).withSelfRel();
        job.setHref(self.toUri());

        job = template.save(job);

        // create fake report for this job
        PerformanceReport report = new PerformanceReport();
        report.setDescription(job.getDescription());
        report.setReportingTimeframe(new ReportingTimeframe().reportingStartDate(job.getCreationDate()).reportingEndDate(job.getCreationDate().plusHours(1)));
        report.setPerformanceJob(new PerformanceJobRef().id(job.getId()).href(job.getHref()));
        reportService.createPerformanceReport(report);

        return job;
    }

    /**
     * Modifies a {@link PerformanceJob} according to the given {@link ModifyPerformanceJobCreate}.
     * Though the interface enables asynchron processing, the current implementation is synchron.
     * 
     * @param modifyPerformanceJobCreate the {@link ModifyPerformanceJobCreate} of the
     *            {@link PerformanceJob} to modify.
     * @return a mirror {@link ModifyPerformanceJob} of the input
     */
    public ModifyPerformanceJob modifyPerformanceJob(ModifyPerformanceJobCreate modifyPerformanceJobCreate) {
        final PerformanceJob job = getPerformanceJob(modifyPerformanceJobCreate.getPerformanceJob().getId());
        if (job == null) {
            return null;
        }
        performanceJobMapper.updatePerformanceProfile(modifyPerformanceJobCreate.getPerformanceProfile(), job.getPerformanceProfile());
        template.save(job);
        return performanceJobMapper.toModifyPerformanceJob(modifyPerformanceJobCreate);
    }

    /**
     * Cancels a {@link PerformanceJob} according to the given {@link CancelPerformanceJobCreate}.
     * Though the interface enables asynchron processing, the current implementation is synchron.
     * 
     * @param cancelPerformanceJobCreate the {@link CancelPerformanceJobCreate} of the
     *            {@link PerformanceJob} to cancel.
     * @return a mirror {@link CancelPerformanceJob} of the input
     */
    public CancelPerformanceJob cancelPerformanceJob(CancelPerformanceJobCreate cancelPerformanceJobCreate) {
        final PerformanceJob job = getPerformanceJob(cancelPerformanceJobCreate.getPerformanceJob().getId());
        if (job == null) {
            return null;
        }
        job.setState(PerformanceJobStateType.CANCELLED);
        template.save(job);
        return performanceJobMapper.toCancelPerformanceJob(cancelPerformanceJobCreate);
    }

    /**
     * The method retrieves an existing {@link PerformanceJob}.
     *
     * @param id The Identifier of the {@link PerformanceJob} to be retrieved.
     * @return The retrieved {@link PerformanceJob}.
     */
    public PerformanceJob getPerformanceJob(String id) {
        return template.findById(id, PerformanceJob.class);
    }

    /**
     * The method retrieves all {@link PerformanceJob PerformanceJobs}.
     *
     * @param pageable Collection of pagination parameters
     * @return all {@link PerformanceJob PerformanceJobs}
     */
    public Page<PerformanceJobFind> getAllPerformanceJobs(Pageable pageable) {
        return template.find(new Query(), pageable, PerformanceJob.class).map(performanceJobMapper::toPerformanceJobFind);
    }

    /**
     * The method retrieves all {@link PerformanceJob PerformanceJobs} matching the given
     * query.
     *
     * @param performanceJobComplexQuery the query
     * @return the matching {@link PerformanceJob PerformanceJobs}
     */
    public List<PerformanceJobComplexQuery> getAllPerformanceJobsByFilter(PerformanceJobComplexQueryCreate performanceJobComplexQuery) {
        return template.find(buildQuery(performanceJobComplexQuery), PerformanceJob.class)
                .stream()
                .map(this::toPerformanceJobComplexQuery)
                .collect(toList());
    }

    private Query buildQuery(PerformanceJobComplexQueryCreate filter) {
        QueryBuilder builder = new QueryBuilder();
        builder.createEquals("state", filter.getState());
        builder.createEquals("buyerJobId", filter.getBuyerJobId());
        builder.createEquals("consumingApplicationId", filter.getConsumingApplicationId());
        builder.createEquals("producingApplicationId", filter.getProducingApplicationId());
        return builder.getQuery();
    }

    private PerformanceJobComplexQuery toPerformanceJobComplexQuery(PerformanceJob job) {
        var result = new PerformanceJobComplexQuery();
        result.setBuyerJobId(job.getBuyerJobId());
        result.setConsumingApplicationId(job.getConsumingApplicationId());
        result.setCreationDate(job.getCreationDate());
        result.setDescription(job.getDescription());
        result.setGranularity(job.getPerformanceProfile().getGranularity());
        result.setJobPriority(job.getPerformanceProfile().getJobPriority());
        result.setJobType(job.getPerformanceProfile().getJobType());
        result.setPerformanceJob(new PerformanceJobRef().id(job.getId()).href(job.getHref()));
        result.setProducingApplicationId(job.getProducingApplicationId());
        result.setReportingPeriod(job.getPerformanceProfile().getReportingPeriod());
        result.setScheduleDefinition(job.getScheduleDefinition());
        result.setServicePayloadSpecificAttributes(job.getServicePayloadSpecificAttributes());
        result.setState(job.getState());
        return result;
    }

}
