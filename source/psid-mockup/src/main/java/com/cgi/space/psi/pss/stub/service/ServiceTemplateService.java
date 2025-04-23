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
import com.cgi.space.psi.pss.stub.controller.ServiceTemplateController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.ServiceTemplateFilter;
import com.cgi.space.psi.pss.stub.mapper.ServiceSpecificationMapper;
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
 * Service class for managing CRUD operations for service templates.
 */
@Service
public class ServiceTemplateService {

    private static final String TEMPLATE_ID = "id";
    private static final String TEMPLATE_NAME = "name";
    private static final String TEMPLATE_CATEGORY = "category";
    private static final String TEMPLATE_COLLECTION = "serviceTemplate";
    @Autowired
    private ServiceSpecificationMapper serviceSpecificationMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new service template object.
     *
     * @param serviceSpecificationCreate the {@link ServiceSpecificationFVO} object based on
     *            which
     *            the service template is created.
     * @return the newly created {@link ServiceSpecification} object of the service template.
     */
    public ServiceSpecification createServiceTemplate(ServiceSpecificationFVO serviceSpecificationCreate) {
        ServiceSpecification serviceSpecification = serviceSpecificationMapper.toServiceSpecification(serviceSpecificationCreate);
        serviceSpecification.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(ServiceTemplateController.class)
                .retrieveServiceTemplate(serviceSpecification.getId(), Optional.empty())).withSelfRel();
        serviceSpecification.setHref(self.toUri());

        return template.save(serviceSpecification, TEMPLATE_COLLECTION);
    }

    /**
     * The method retrieves an existing service template.
     *
     * @param id The Identifier of the service template to be retrieved.
     * @return The retrieved {@link ServiceSpecification} from the service template.
     */
    public ServiceSpecification getServiceTemplate(String id) {
        return template.findOne(Query.query(Criteria.where(TEMPLATE_ID).is(id)), ServiceSpecification.class, TEMPLATE_COLLECTION);
    }

    /**
     * The method retrieves all service templates.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all service templates.
     */
    public Page<ServiceSpecification> getAllServiceTemplates(Pageable pageable, Optional<ServiceTemplateFilter> filter) {
        return template.find(buildQuery(filter), pageable, ServiceSpecification.class, TEMPLATE_COLLECTION);
    }

    private Query buildQuery(Optional<ServiceTemplateFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals(TEMPLATE_NAME, filterProps.getName());
            builder.createContains(List.of(TEMPLATE_NAME), filterProps.getNameContains());
            builder.createEquals(TEMPLATE_CATEGORY, filterProps.getCategory());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing service template.
     *
     * @param id The Identifier of the service template to be updated.
     * @param serviceSpecificationUpdate The {@link ServiceSpecificationMVO} object containing
     *            the updates to be applied to the service template object.
     * @return the updated {@link ServiceSpecification} of the service template.
     */
    public ServiceSpecification updateServiceTemplate(String id, ServiceSpecificationMVO serviceSpecificationUpdate) {
        ServiceSpecification serviceTemplate =
                template.findOne(Query.query(Criteria.where(TEMPLATE_ID).is(id)), ServiceSpecification.class, TEMPLATE_COLLECTION);
        serviceSpecificationMapper.updateServiceSpecification(serviceSpecificationUpdate, serviceTemplate);

        return template.save(serviceTemplate, TEMPLATE_COLLECTION);
    }

    /**
     * The method deletes an existing service template.
     *
     * @param id The Identifier of the service template to be deleted.
     * @return <code>true</code> if service template is successfully deleted,
     *         <code>false</code> otherwise.
     */
    public boolean deleteServiceTemplate(String id) {
        return template.remove(Query.query(Criteria.where(TEMPLATE_ID).is(id)), ServiceSpecification.class, TEMPLATE_COLLECTION).getDeletedCount() > 0;
    }

}
