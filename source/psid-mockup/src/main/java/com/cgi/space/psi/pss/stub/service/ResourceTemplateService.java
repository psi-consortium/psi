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
import com.cgi.space.psi.pss.stub.controller.ResourceTemplateController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.ResourceTemplateFilter;
import com.cgi.space.psi.pss.stub.mapper.ResourceSpecificationMapper;
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
 * Service class for managing CRUD operations for resource templates.
 */
@Service
public class ResourceTemplateService {

    private static final String TEMPLATE_ID = "id";
    private static final String TEMPLATE_NAME = "name";
    private static final String TEMPLATE_CATEGORY = "category";
    private static final String TEMPLATE_COLLECTION = "resourceTemplate";
    @Autowired
    private ResourceSpecificationMapper resourceSpecificationMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new resource template object.
     *
     * @param resourceSpecificationCreate the {@link ResourceSpecificationFVO} object based on
     *            which
     *            the resource template is created.
     * @return the newly created {@link ResourceSpecification} object of the
     *         resource template.
     */
    public ResourceSpecification createResourceTemplate(ResourceSpecificationFVO resourceSpecificationCreate) {
        ResourceSpecification resourceSpecification = resourceSpecificationMapper.toResourceSpecification(resourceSpecificationCreate);
        resourceSpecification.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(ResourceTemplateController.class)
                .retrieveResourceTemplate(resourceSpecification.getId(), Optional.empty())).withSelfRel();
        resourceSpecification.setHref(self.toUri());

        return template.save(resourceSpecification, TEMPLATE_COLLECTION);
    }

    /**
     * The method retrieves an existing resource template.
     *
     * @param id The Identifier of the resource template to be retrieved.
     * @return The retrieved {@link ResourceSpecification} from the resource template.
     */
    public ResourceSpecification getResourceTemplate(String id) {
        return template.findOne(Query.query(Criteria.where(TEMPLATE_ID).is(id)), ResourceSpecification.class, TEMPLATE_COLLECTION);
    }

    /**
     * The method retrieves all resource templates.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all resource templates.
     */
    public Page<ResourceSpecification> getAllResourceTemplates(Pageable pageable, Optional<ResourceTemplateFilter> filter) {
        return template.find(buildQuery(filter), pageable, ResourceSpecification.class, TEMPLATE_COLLECTION);
    }

    private Query buildQuery(Optional<ResourceTemplateFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals(TEMPLATE_NAME, filterProps.getName());
            builder.createContains(List.of(TEMPLATE_NAME), filterProps.getNameContains());
            builder.createEquals(TEMPLATE_CATEGORY, filterProps.getCategory());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing resource template.
     *
     * @param id The Identifier of the resource template to be updated.
     * @param resourceSpecificationUpdate The {@link ResourceSpecificationMVO} object containing
     *            the updates to be applied to the resource template object.
     * @return the updated {@link ResourceSpecification} of the resource template.
     */
    public ResourceSpecification updateResourceTemplate(String id, ResourceSpecificationMVO resourceSpecificationUpdate) {
        ResourceSpecification resourceTemplate =
                template.findOne(Query.query(Criteria.where(TEMPLATE_ID).is(id)), ResourceSpecification.class, TEMPLATE_COLLECTION);
        resourceSpecificationMapper.updateResourceSpecification(resourceSpecificationUpdate, resourceTemplate);

        return template.save(resourceTemplate, TEMPLATE_COLLECTION);
    }

    /**
     * The method deletes an existing resource template.
     *
     * @param id The Identifier of the resource template to be deleted.
     * @return <code>true</code> if resource template is successfully deleted,
     *         <code>false</code> otherwise.
     */
    public boolean deleteResourceTemplate(String id) {
        return template.remove(Query.query(Criteria.where(TEMPLATE_ID).is(id)), ResourceSpecification.class, TEMPLATE_COLLECTION).getDeletedCount() > 0;
    }

}
