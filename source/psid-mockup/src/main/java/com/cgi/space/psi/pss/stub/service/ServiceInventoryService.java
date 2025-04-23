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

import com.cgi.space.psi.common.model.Service;
import com.cgi.space.psi.common.model.ServiceFVO;
import com.cgi.space.psi.common.model.ServiceStateType;
import com.cgi.space.psi.common.model.ServiceMVO;
import com.cgi.space.psi.pss.stub.controller.ServiceInventoryController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.ServiceFilter;
import com.cgi.space.psi.pss.stub.mapper.ServiceMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link Service}s.
 */
@org.springframework.stereotype.Service
public class ServiceInventoryService {

    @Autowired
    private ServiceMapper serviceMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link Service} object.
     *
     * @param serviceCreate the {@link ServiceFVO} object based on which
     *                                   the {@link Service} is created.
     * @return the newly created {@link Service} object.
     */
    public Service createService(ServiceFVO serviceCreate) {
        Service service = serviceMapper.toService(serviceCreate);
        service.setId(UUID.randomUUID().toString());
        service.setAtType(service.getClass().getSimpleName());
        service.setAtBaseType("Service");
        if (service.getServiceDate() == null) {
            service.setServiceDate(OffsetDateTime.now().toString());
        }

        Link self = linkTo(methodOn(ServiceInventoryController.class)
                .retrieveService(service.getId(), Optional.empty())).withSelfRel();
        service.setHref(self.toUri());

        return template.save(service);
    }

    /**
     * The method retrieves an existing {@link Service}.
     *
     * @param id The Identifier of the {@link Service} to be retrieved.
     * @return The retrieved {@link Service}.
     */
    public Service getService(String id) {
        return template.findById(id, Service.class);
    }

    /**
     * The method retrieves all {@link Service Services}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link Service Services}
     */
    public Page<Service> getAllServices(Pageable pageable, Optional<ServiceFilter> filter) {
        return template.find(buildQuery(filter), pageable, Service.class);
    }

    private Query buildQuery(Optional<ServiceFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("description"), filterProps.getDescriptionContains());
            builder.createContains(List.of("name", "description"), filterProps.getNameOrDescriptionContains());
            builder.createEqualsAny("state", filterProps.getState(), ServiceStateType::fromValue);
            builder.createContains(List.of("relatedParty.partyOrPartyRole.name"), filterProps.getRelatedPartyContains());
            builder.createEquals("category", filterProps.getCategory());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing {@link Service Service}.
     *
     * @param id                         The Identifier of the {@link Service} to be updated.
     * @param serviceUpdate The {@link ServiceMVO} object containing the updates to be applied to the {@link Service} object.
     * @return the updated {@link Service Service}
     */
    public Service updateService(String id, ServiceMVO serviceUpdate) {
        Service service = template.findOne(Query.query(Criteria.where("id").is(id)), Service.class);
        serviceMapper.updateService(serviceUpdate, service);

        return template.save(service);
    }

    /**
     * The method deletes an existing {@link Service Service}.
     *
     * @param id The Identifier of the {@link Service} to be deleted.
     * @return <code>true</code> if {@link Service} successfully deleted, <code>false</code> otherwise.
     */
    public boolean deleteService(String id) {
        Service service = template.findById(id, Service.class);
        return template.remove(service).getDeletedCount() > 0;
    }
}
