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

import com.cgi.space.psi.common.model.Organization;
import com.cgi.space.psi.common.model.OrganizationFVO;
import com.cgi.space.psi.common.model.OrganizationMVO;
import com.cgi.space.psi.pss.stub.controller.OrganizationController;
import com.cgi.space.psi.pss.stub.filter.OrganizationFilter;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.OrganizationMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link Organization Organizations}.
 */
@Service
public class OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link Organization} object.
     *
     * @param organizationCreate the {@link OrganizationFVO} object based on which
     *                           the {@link Organization} is created.
     * @return the newly created {@link Organization} object.
     */
    public Organization createOrganization(OrganizationFVO organizationCreate) {
        Organization organization = organizationMapper.toOrganization(organizationCreate);
        organization.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(OrganizationController.class)
                .retrieveOrganization(organization.getId(), Optional.empty())).withSelfRel();
        organization.setHref(self.toUri());

        return template.save(organization);
    }

    /**
     * The method retrieves an existing {@link Organization}.
     *
     * @param id The Identifier of the {@link Organization} to be retrieved.
     * @return The retrieved {@link Organization}.
     */
    public Organization getOrganization(String id) {
        return template.findById(id, Organization.class);
    }

    /**
     * The method retrieves all {@link Organization Organizations}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link Organization Organizations}
     */
    public Page<Organization> getAllOrganizations(Pageable pageable, Optional<OrganizationFilter> filter) {
        return template.find(buildQuery(filter), pageable, Organization.class);
    }

    private Query buildQuery(Optional<OrganizationFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("tradingName"), filterProps.getTradingName());
            builder.createContains(List.of("tradingName"), filterProps.getTradingNameContains());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing {@link Organization Organization}.
     *
     * @param id                 The Identifier of the {@link Organization} object to be updated.
     * @param organizationUpdate The {@link OrganizationMVO} object containing the updates necessary for the {@link Organization}.
     * @return The updated {@link Organization Organization}.
     */
    public Organization updateOrganization(String id, OrganizationMVO organizationUpdate) {
        Organization organization = template.findOne(Query.query(Criteria.where("id").is(id)), Organization.class);
        organizationMapper.updateOrganization(organizationUpdate, organization);

        return template.save(organization);
    }

    /**
     * The method deletes an existing {@link Organization Organization}.
     *
     * @param id The Identifier of the {@link Organization} object to be deleted.
     * @return <code>true</code> if {@link Organization} successfully deleted, <code>false</code> otherwise.
     */
    public boolean deleteOrganization(String id) {
        Organization organization = template.findById(id, Organization.class);
        return template.remove(organization).getDeletedCount() > 0;
    }
}
