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

import com.cgi.space.psi.common.model.ResourceSpecification;
import com.cgi.space.psi.common.model.ResourceSpecificationFVO;
import com.cgi.space.psi.common.model.ResourceSpecificationMVO;
import com.cgi.space.psi.pss.stub.controller.ResourceSpecificationController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.ResourceSpecificationFilter;
import com.cgi.space.psi.pss.stub.mapper.ResourceSpecificationMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;

import lombok.extern.slf4j.Slf4j;

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
 * Service class for managing CRUD operations for {@link ResourceSpecification}s.
 */
@Slf4j
@Service
public class ResourceSpecificationService {

    private static final String RESOURCE_SPECIFICATION_COLLECTION = "resourceSpecification";

    @Autowired
    private ResourceSpecificationMapper resourceSpecificationMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link ResourceSpecification} object.
     *
     * @param resourceSpecificationCreate the {@link ResourceSpecificationFVO} object based on which
     *                                    the {@link ResourceSpecification} is created.
     * @return the newly created {@link ResourceSpecification} object.
     */
    public ResourceSpecification createResourceSpecification(ResourceSpecificationFVO resourceSpecificationCreate) {
        ResourceSpecification resource = resourceSpecificationMapper.toResourceSpecification(resourceSpecificationCreate);
        resource.setId(UUID.randomUUID().toString());
        resource.setAtType(resource.getClass().getSimpleName());
        resource.setAtBaseType("ResourceSpecification");
        resource.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        if (StringUtils.isBlank(resource.getLifecycleStatus())) {
            resource.setLifecycleStatus("active");
        }
        if (resource.getIsBundle() == null) {
            resource.setIsBundle(false);
        }

        Link self = linkTo(methodOn(ResourceSpecificationController.class)
                .retrieveResourceSpecification(resource.getId(), Optional.empty())).withSelfRel();
        resource.setHref(self.toUri());
        return template.save(resource, RESOURCE_SPECIFICATION_COLLECTION);
    }

    /**
     * The method retrieves an existing {@link ResourceSpecification}.
     *
     * @param id The Identifier of the {@link ResourceSpecification} to be retrieved.
     * @return The retrieved {@link ResourceSpecification}.
     */
    public ResourceSpecification getResourceSpecification(String id) {
        return template.findById(id, ResourceSpecification.class, RESOURCE_SPECIFICATION_COLLECTION);
    }

    /**
     * The method retrieves an existing {@link ResourceSpecification} by name and version.
     *
     * @param name    The name of the {@link ResourceSpecification} to be retrieved.
     * @param version The version of the {@link ResourceSpecification} to be retrieved.
     * @return The retrieved {@link ResourceSpecification}.
     */
    public ResourceSpecification getResourceSpecificationByName(String name, String version) {
        Query query = new Query(Criteria.where("name").is(name).and("version").is(version));
        return template.findOne(query, ResourceSpecification.class, RESOURCE_SPECIFICATION_COLLECTION);
    }

    /**
     * The method retrieves all {@link ResourceSpecification ResourceSpecifications}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link ResourceSpecification ResourceSpecifications}
     */
    public Page<ResourceSpecification> getAllResourceSpecifications(Pageable pageable, Optional<ResourceSpecificationFilter> filter) {
        return template.find(buildQuery(filter), pageable, ResourceSpecification.class, RESOURCE_SPECIFICATION_COLLECTION);
    }

    private Query buildQuery(Optional<ResourceSpecificationFilter> filter) {
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
     * The method updates an existing {@link ResourceSpecification ResourceSpecification}.
     *
     * @param id                          The Identifier of the {@link ResourceSpecification} to be updated.
     * @param resourceSpecificationUpdate The {@link ResourceSpecificationMVO} object containing the updates to be applied to the {@link ResourceSpecification} object.
     * @return the updated {@link ResourceSpecification ResourceSpecification}
     */
    public ResourceSpecification updateResourceSpecification(String id, ResourceSpecificationMVO resourceSpecificationUpdate) {
        ResourceSpecification resourceSpecification = template.findOne(Query.query(Criteria.where("id").is(id)),
            ResourceSpecification.class, RESOURCE_SPECIFICATION_COLLECTION);

        resourceSpecificationMapper.updateResourceSpecification(resourceSpecificationUpdate, resourceSpecification);
        resourceSpecification.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));

        return template.save(resourceSpecification, RESOURCE_SPECIFICATION_COLLECTION);
    }

    /**
     * The method deletes an existing {@link ResourceSpecification ResourceSpecification}.
     *
     * @param id The Identifier of the {@link ResourceSpecification} to be deleted.
     * @return <code>true</code> if {@link ResourceSpecification} successfully deleted, <code>false</code> otherwise.
     */
    public boolean deleteResourceSpecification(String id) {
        ResourceSpecification resourceSpecification = template.findById(id, ResourceSpecification.class, RESOURCE_SPECIFICATION_COLLECTION);
        return template.remove(resourceSpecification, RESOURCE_SPECIFICATION_COLLECTION).getDeletedCount() > 0;
    }
}
