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

import com.cgi.space.psi.common.model.Resource;
import com.cgi.space.psi.common.model.ResourceFVO;
import com.cgi.space.psi.common.model.ResourceMVO;
import com.cgi.space.psi.pss.stub.controller.ResourceInventoryController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.ResourceFilter;
import com.cgi.space.psi.pss.stub.mapper.ResourceMapper;
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
 * Service class for managing CRUD operations for {@link Resource}s.
 */
@Service
public class ResourceInventoryService {

    private static final String RESOURCE_COLLECTION = "resource";

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link Resource} object.
     *
     * @param resourceCreate the {@link ResourceFVO} object based on which the {@link Resource} is created.
     * @return the newly created {@link Resource} object.
     */
    public Resource createResource(ResourceFVO resourceCreate) {
        Resource resource = resourceMapper.toResource(resourceCreate);
        resource.setId(UUID.randomUUID().toString());
        resource.setAtType(resource.getClass().getSimpleName());
        resource.setAtBaseType("Resource");

        Link self = linkTo(methodOn(ResourceInventoryController.class)
                .retrieveResource(resource.getId(), Optional.empty())).withSelfRel();
        resource.setHref(self.toUri());

        return template.save(resource, RESOURCE_COLLECTION);
    }

    /**
     * The method retrieves an existing {@link Resource}.
     *
     * @param id The Identifier of the {@link Resource} to be retrieved.
     * @return The retrieved {@link Resource}.
     */
    public Resource getResource(String id) {
        return template.findById(id, Resource.class, RESOURCE_COLLECTION);
    }

    /**
     * The method retrieves all {@link Resource Resources}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link Resource Resources}
     */
    public Page<Resource> getAllResources(Pageable pageable, Optional<ResourceFilter> filter) {
        return template.find(buildQuery(filter), pageable, Resource.class, RESOURCE_COLLECTION);
    }

    private Query buildQuery(Optional<ResourceFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("description"), filterProps.getDescriptionContains());
            builder.createContains(List.of("name", "description"), filterProps.getNameOrDescriptionContains());
            builder.createEquals("resourceVersion", filterProps.getVersion());
            builder.createEqualsAny("resourceStatus", filterProps.getStatus());
            builder.createContains(List.of("relatedParty.partyOrPartyRole.name"), filterProps.getRelatedPartyContains());
            builder.createEquals("category", filterProps.getCategory());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing {@link Resource Resource}.
     *
     * @param id             The Identifier of the {@link Resource} to be updated.
     * @param resourceUpdate The {@link ResourceMVO} object containing the updates to be applied to the {@link Resource} object.
     * @return the updated {@link Resource Resource}
     */
    public Resource updateResource(String id, ResourceMVO resourceUpdate) {
        Resource resource = template.findOne(Query.query(Criteria.where("id").is(id)), Resource.class, RESOURCE_COLLECTION);
        resourceMapper.updateResource(resourceUpdate, resource);

        return template.save(resource, RESOURCE_COLLECTION);
    }

    /**
     * The method deletes an existing {@link Resource Resource}.
     *
     * @param id The Identifier of the {@link Resource} to be deleted.
     * @return <code>true</code> if {@link Resource} successfully deleted, <code>false</code> otherwise.
     */
    public boolean deleteResource(String id) {
        Resource resource = template.findById(id, Resource.class, RESOURCE_COLLECTION);
        return template.remove(resource, RESOURCE_COLLECTION).getDeletedCount() > 0;
    }
}
