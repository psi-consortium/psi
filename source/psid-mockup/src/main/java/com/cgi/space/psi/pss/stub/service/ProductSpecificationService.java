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
import com.cgi.space.psi.pss.stub.controller.ProductSpecificationController;
import com.cgi.space.psi.pss.stub.filter.ProductSpecificationFilter;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.ProductSpecificationMapper;
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
 * Service class for managing CRUD operations for {@link ProductSpecification}s.
 */
@Service
public class ProductSpecificationService {

    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link ProductSpecification} object.
     *
     * @param productSpecificationCreate the {@link ProductSpecificationFVO} object based on which
     *                                   the {@link ProductSpecification} is created.
     * @return the newly created {@link ProductSpecification} object.
     */
    public ProductSpecification createProductSpecification(ProductSpecificationFVO productSpecificationCreate) {
        ProductSpecification product = productSpecificationMapper.toProductSpecification(productSpecificationCreate);
        product.setId(UUID.randomUUID().toString());
        product.setAtType(product.getClass().getSimpleName());
        product.setAtBaseType("ProductSpecification");
        product.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        if (StringUtils.isBlank(product.getLifecycleStatus())) {
            product.setLifecycleStatus("active");
        }

        Link self = linkTo(methodOn(ProductSpecificationController.class)
                .retrieveProductSpecification(product.getId(), Optional.empty())).withSelfRel();
        product.setHref(self.toUri());

        return template.save(product);
    }

    /**
     * The method retrieves an existing {@link ProductSpecification}.
     *
     * @param id The Identifier of the {@link ProductSpecification} to be retrieved.
     * @return The retrieved {@link ProductSpecification}.
     */
    public ProductSpecification getProductSpecification(String id) {
        return template.findById(id, ProductSpecification.class);
    }

    /**
     * The method retrieves all {@link ProductSpecification ProductSpecifications}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link ProductSpecification ProductSpecifications}
     */
    public Page<ProductSpecification> getAllProductSpecifications(Pageable pageable, Optional<ProductSpecificationFilter> filter) {
        return template.find(buildQuery(filter), pageable, ProductSpecification.class);
    }

    private Query buildQuery(Optional<ProductSpecificationFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("description"), filterProps.getDescriptionContains());
            builder.createContains(List.of("name", "description"), filterProps.getNameOrDescriptionContains());
            builder.createEquals("version", filterProps.getVersion());
            builder.createEqualsAny("productNumber", filterProps.getProductNumber());
            builder.createEqualsAny("lifecycleStatus", filterProps.getLifecycleStatus());
            builder.createContains(List.of("relatedParty.partyOrPartyRole.name"), filterProps.getRelatedPartyContains());
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
     * The method updates an existing {@link ProductSpecification ProductSpecification}.
     *
     * @param id                         The Identifier of the {@link ProductSpecification} to be updated.
     * @param productSpecificationUpdate The {@link ProductSpecificationMVO} object containing the updates to be applied to the {@link ProductSpecification} object.
     * @return the updated {@link ProductSpecification ProductSpecification}
     */
    public ProductSpecification updateProductSpecification(String id, ProductSpecificationMVO productSpecificationUpdate) {
        ProductSpecification productSpecification = template.findOne(Query.query(Criteria.where("id").is(id)), ProductSpecification.class);
        productSpecificationMapper.updateProductSpecification(productSpecificationUpdate, productSpecification);
        productSpecification.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));

        return template.save(productSpecification);
    }

    /**
     * The method deletes an existing {@link ProductSpecification ProductSpecification}.
     *
     * @param id The Identifier of the {@link ProductSpecification} to be deleted.
     * @return <code>true</code> if {@link ProductSpecification} successfully deleted, <code>false</code> otherwise.
     */
    public boolean deleteProductSpecification(String id) {
        ProductSpecification productSpecification = template.findById(id, ProductSpecification.class);
        return template.remove(productSpecification).getDeletedCount() > 0;
    }
}
