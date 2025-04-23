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

import com.cgi.space.psi.common.model.Individual;
import com.cgi.space.psi.common.model.IndividualFVO;
import com.cgi.space.psi.common.model.IndividualMVO;
import com.cgi.space.psi.pss.stub.controller.IndividualController;
import com.cgi.space.psi.pss.stub.filter.IndividualFilter;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.IndividualMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link Individual Individuals}.
 */
@Service
public class IndividualService {

    @Autowired
    private IndividualMapper individualMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link Individual} object.
     *
     * @param individualCreate the {@link IndividualFVO} object based on which
     *                         the {@link Individual} is created.
     * @return the newly created {@link Individual} object.
     */
    public Individual createIndividual(IndividualFVO individualCreate) {
        Individual individual = individualMapper.toIndividual(individualCreate);
        individual.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(IndividualController.class)
                .retrieveIndividual(individual.getId(), Optional.empty())).withSelfRel();
        individual.setHref(self.toUri());

        return template.save(individual);
    }

    /**
     * The method retrieves an existing {@link Individual}.
     *
     * @param id The Identifier of the {@link Individual} to be retrieved.
     * @return The retrieved {@link Individual}.
     */
    public Individual getIndividual(String id) {
        return template.findById(id, Individual.class);
    }

    /**
     * The method retrieves all {@link Individual Individuals}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link Individual Individuals}
     */
    public Page<Individual> getAllIndividuals(Pageable pageable, Optional<IndividualFilter> filter) {
        return template.find(buildQuery(filter), pageable, Individual.class);
    }

    private Query buildQuery(Optional<IndividualFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("familyName", filterProps.getFamilyName());
            builder.createContains(List.of("familyName"), filterProps.getFamilyNameContains());
            builder.createContains(List.of("givenName"), filterProps.getGivenName());
            builder.createContains(List.of("givenName"), filterProps.getGivenNameContains());
            builder.createContains(List.of("name"), filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing {@link Individual Individual}.
     *
     * @param id               The Identifier of the {@link Individual} object to be updated.
     * @param individualUpdate The {@link IndividualMVO} object containing the updates necessary for the {@link Individual}.
     * @return The updated {@link Individual Individual}.
     */
    public Individual updateIndividual(String id, IndividualMVO individualUpdate) {
        Individual individual = template.findOne(Query.query(Criteria.where("id").is(id)), Individual.class);
        individualMapper.updateIndividual(individualUpdate, individual);
        return template.save(individual);
    }

    /**
     * The method deletes an existing {@link Individual Individual}.
     *
     * @param id The Identifier of the {@link Individual} object to be deleted.
     * @return <code>true</code> if {@link Individual} successfully deleted, <code>false</code> otherwise.
     */
    public boolean deleteIndividual(String id) {
        Individual individual = template.findById(id, Individual.class);
        return template.remove(individual).getDeletedCount() > 0;
    }
}
