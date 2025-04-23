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

import com.cgi.space.psi.common.model.ServiceSpecification;
import com.cgi.space.psi.common.model.ServiceSpecificationFVO;
import com.cgi.space.psi.common.model.ServiceSpecificationMVO;
import com.cgi.space.psi.pss.stub.controller.ServiceSpecificationController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.ServiceSpecificationFilter;
import com.cgi.space.psi.pss.stub.mapper.ServiceSpecificationMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link ServiceSpecification}s.
 */
@Service
public class ServiceSpecificationService {

    @Autowired
    private ServiceSpecificationMapper serviceSpecificationMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link ServiceSpecification} object.
     *
     * @param serviceSpecificationCreate the {@link ServiceSpecificationFVO} object based on which
     *                                   the {@link ServiceSpecification} is created.
     * @return the newly created {@link ServiceSpecification} object.
     */
    public ServiceSpecification createServiceSpecification(ServiceSpecificationFVO serviceSpecificationCreate) {
        ServiceSpecification service = serviceSpecificationMapper.toServiceSpecification(serviceSpecificationCreate);
        service.setId(UUID.randomUUID().toString());
        service.setAtType(service.getClass().getSimpleName());
        service.setAtBaseType("ServiceSpecification");
        service.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        if (StringUtils.isBlank(service.getLifecycleStatus())) {
            service.setLifecycleStatus("active");
        }
        if (service.getIsBundle() == null) {
            service.setIsBundle(false);
        }

        Link self = linkTo(methodOn(ServiceSpecificationController.class)
                .retrieveServiceSpecification(service.getId(), Optional.empty())).withSelfRel();
        service.setHref(self.toUri());

        return template.save(service);
    }

    /**
     * The method retrieves an existing {@link ServiceSpecification}.
     *
     * @param id The Identifier of the {@link ServiceSpecification} to be retrieved.
     * @return The retrieved {@link ServiceSpecification}.
     */
    public ServiceSpecification getServiceSpecification(String id) {
        return template.findById(id, ServiceSpecification.class);
    }

    /**
     * The method retrieves all {@link ServiceSpecification ServiceSpecifications}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link ServiceSpecification ServiceSpecifications}
     */
    public Page<ServiceSpecification> getAllServiceSpecifications(Pageable pageable, Optional<ServiceSpecificationFilter> filter) {
        return template.find(buildQuery(filter), pageable, ServiceSpecification.class);
    }

    private Query buildQuery(Optional<ServiceSpecificationFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("description"), filterProps.getDescriptionContains());
            builder.createContains(List.of("name", "description"), filterProps.getNameOrDescriptionContains());
            builder.createEquals("version", filterProps.getVersion());
            builder.createEqualsAny("lifecycleStatus", filterProps.getLifecycleStatus());
            builder.createContains(List.of("relatedParty.partyOrPartyRole.name"), filterProps.getRelatedPartyContains());
            builder.createEquals("category", filterProps.getCategory());
            if (StringUtils.isNotBlank(filterProps.getValidBefore())) {
                builder.orOperator(List.of(
                        Criteria.where("validFor").isNull(),
                        Criteria.where("validFor.startDateTime").isNull(),
                        Criteria.where("validFor.startDateTime").lte(OffsetDateTime.parse(filterProps.getValidBefore()))
                ));
            }
            if (StringUtils.isNotBlank(filterProps.getValidAfter())) {
                builder.orOperator(List.of(
                        Criteria.where("validFor").isNull(),
                        Criteria.where("validFor.endDateTime").isNull(),
                        Criteria.where("validFor.endDateTime").gt(OffsetDateTime.parse(filterProps.getValidAfter()))
                ));
            }
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing {@link ServiceSpecification ServiceSpecification}.
     *
     * @param id                         The Identifier of the {@link ServiceSpecification} to be updated.
     * @param serviceSpecificationUpdate The {@link ServiceSpecificationMVO} object containing the updates to be applied to the {@link ServiceSpecification} object.
     * @return the updated {@link ServiceSpecification ServiceSpecification}
     */
    public ServiceSpecification updateServiceSpecification(String id, ServiceSpecificationMVO serviceSpecificationUpdate) {
        ServiceSpecification serviceSpecification = template.findOne(Query.query(Criteria.where("id").is(id)), ServiceSpecification.class);
        serviceSpecificationMapper.updateServiceSpecification(serviceSpecificationUpdate, serviceSpecification);
        serviceSpecification.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));

        return template.save(serviceSpecification);
    }

    /**
     * The method deletes an existing {@link ServiceSpecification ServiceSpecification}.
     *
     * @param id The Identifier of the {@link ServiceSpecification} to be deleted.
     * @return <code>true</code> if {@link ServiceSpecification} successfully deleted, <code>false</code> otherwise.
     */
    public boolean deleteServiceSpecification(String id) {
        ServiceSpecification serviceSpecification = template.findById(id, ServiceSpecification.class);
        return template.remove(serviceSpecification).getDeletedCount() > 0;
    }

}
