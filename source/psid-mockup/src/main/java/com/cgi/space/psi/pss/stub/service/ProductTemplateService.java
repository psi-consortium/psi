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

import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.ProductSpecificationFVO;
import com.cgi.space.psi.common.model.ProductSpecificationMVO;
import com.cgi.space.psi.pss.stub.controller.ProductTemplateController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.ProductTemplateFilter;
import com.cgi.space.psi.pss.stub.mapper.ProductSpecificationMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for product templates.
 */
@Service
public class ProductTemplateService {

    private static final String TEMPLATE_ID = "id";
    private static final String TEMPLATE_NAME = "name";
    private static final String TEMPLATE_TARGET_SCHEMA = "targetProductSchema.atType";
    private static final String TEMPLATE_COLLECTION = "productTemplate";
    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new product template object.
     *
     * @param productSpecificationCreate the {@link ProductSpecificationFVO} object based on
     *            which
     *            the product template is created.
     * @return the newly created {@link ProductSpecification} object of the product template.
     */
    public ProductSpecification createProductTemplate(ProductSpecificationFVO productSpecificationCreate) {
        ProductSpecification productSpecification = productSpecificationMapper.toProductSpecification(productSpecificationCreate);
        productSpecification.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(ProductTemplateController.class)
                .retrieveProductTemplate(productSpecification.getId(), Optional.empty())).withSelfRel();
        productSpecification.setHref(self.toUri());

        return template.save(productSpecification, TEMPLATE_COLLECTION);
    }

    /**
     * The method retrieves an existing product template.
     *
     * @param id The Identifier of the product template to be retrieved.
     * @return The retrieved {@link ProductSpecification} from the product template.
     */
    public ProductSpecification getProductTemplate(String id) {
        return template.findOne(Query.query(Criteria.where(TEMPLATE_ID).is(id)), ProductSpecification.class, TEMPLATE_COLLECTION);
    }

    /**
     * The method retrieves all product templates.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all product templates.
     */
    public Page<ProductSpecification> getAllProductTemplates(Pageable pageable, Optional<ProductTemplateFilter> filter) {
        return template.find(buildQuery(filter), pageable, ProductSpecification.class, TEMPLATE_COLLECTION);
    }

    private Query buildQuery(Optional<ProductTemplateFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals(TEMPLATE_NAME, filterProps.getName());
            builder.createContains(List.of(TEMPLATE_NAME), filterProps.getNameContains());
            builder.createEquals(TEMPLATE_TARGET_SCHEMA, filterProps.getTargetProductSchema());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing product template.
     *
     * @param id The Identifier of the product template to be updated.
     * @param productSpecificationUpdate The {@link ProductSpecificationMVO} object containing
     *            the updates to be applied to the product template object.
     * @return the updated {@link ProductSpecification} of the product template.
     */
    public ProductSpecification updateProductTemplate(String id, ProductSpecificationMVO productSpecificationUpdate) {
        ProductSpecification productTemplate =
                template.findOne(Query.query(Criteria.where(TEMPLATE_ID).is(id)), ProductSpecification.class, TEMPLATE_COLLECTION);
        productSpecificationMapper.updateProductSpecification(productSpecificationUpdate, productTemplate);

        return template.save(productTemplate, TEMPLATE_COLLECTION);
    }

    /**
     * The method deletes an existing product template.
     *
     * @param id The Identifier of the product template to be deleted.
     * @return <code>true</code> if product template is successfully deleted,
     *         <code>false</code> otherwise.
     */
    public boolean deleteProductTemplate(String id) {
        return template.remove(Query.query(Criteria.where(TEMPLATE_ID).is(id)), ProductSpecification.class, TEMPLATE_COLLECTION).getDeletedCount() > 0;
    }

}
