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

import com.cgi.space.psi.common.model.ProductOffering;
import com.cgi.space.psi.common.model.ProductOfferingFVO;
import com.cgi.space.psi.common.model.ProductOfferingMVO;
import com.cgi.space.psi.pss.stub.controller.ProductOfferingController;
import com.cgi.space.psi.pss.stub.filter.ProductOfferingFilter;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.ProductOfferingMapper;
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
 * Service class for managing CRUD operations for {@link ProductOffering}s.
 */
@Service
public class ProductOfferingService {

    @Autowired
    private ProductOfferingMapper productOfferingMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link ProductOffering} object.
     *
     * @param productOfferingCreate the {@link ProductOfferingFVO} object based on which
     *            the {@link ProductOffering} is created.
     * @return the newly created {@link ProductOffering} object.
     */
    public ProductOffering createProductOffering(ProductOfferingFVO productOfferingCreate) {
        ProductOffering offering = productOfferingMapper.toProductOffering(productOfferingCreate);
        offering.setId(UUID.randomUUID().toString());
        offering.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        if (StringUtils.isBlank(offering.getLifecycleStatus())) {
            offering.setLifecycleStatus("active");
        }

        Link self = linkTo(methodOn(ProductOfferingController.class)
                .retrieveProductOffering(offering.getId(), Optional.empty())).withSelfRel();
        offering.setHref(self.toUri());

        return template.save(offering);
    }

    /**
     * The method retrieves an existing {@link ProductOffering}.
     *
     * @param id The Identifier of the {@link ProductOffering} to be retrieved.
     * @return The retrieved {@link ProductOffering}.
     */
    public ProductOffering getProductOffering(String id) {
        return template.findById(id, ProductOffering.class);
    }

    /**
     * The method retrieves all {@link ProductOffering ProductOfferings}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link ProductOffering ProductOfferings}
     */
    public Page<ProductOffering> getAllProductOfferings(Pageable pageable, Optional<ProductOfferingFilter> filter) {
        return template.find(buildQuery(filter), pageable, ProductOffering.class);
    }

    private Query buildQuery(Optional<ProductOfferingFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("description"), filterProps.getDescriptionContains());
            builder.createContains(List.of("name", "description"), filterProps.getNameOrDescriptionContains());
            builder.createEquals("version", filterProps.getVersion());
            builder.createEqualsAny("lifecycleStatus", filterProps.getLifecycleStatus());
            if (StringUtils.isNotBlank(filterProps.getValidBefore())) {
                builder.orOperator(List.of(
                        Criteria.where("validFor").isNull(),
                        Criteria.where("validFor.startDateTime").isNull(),
                        Criteria.where("validFor.startDateTime").lte(OffsetDateTime.parse(filterProps.getValidBefore()))));
            }
            if (StringUtils.isNotBlank(filterProps.getValidAfter())) {
                builder.orOperator(List.of(
                        Criteria.where("validFor").isNull(),
                        Criteria.where("validFor.endDateTime").isNull(),
                        Criteria.where("validFor.endDateTime").gt(OffsetDateTime.parse(filterProps.getValidAfter()))));
            }
        });
        return builder.getQuery();
    }

    /**
     * The method retrieves all {@link ProductOffering ProductOfferings} of the given specification.
     *
     * @param id the specification id
     * @return all {@link ProductOffering ProductOfferings}
     */
    public List<ProductOffering> getAllByProductSpecificationId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("productSpecification.id").is(id));
        return template.find(query, ProductOffering.class);
    }

    /**
     * The method updates an existing {@link ProductOffering ProductOffering}.
     *
     * @param id The Identifier of the {@link ProductOffering} to be updated.
     * @param productOfferingUpdate The {@link ProductOfferingMVO} object containing the updates
     *            to be applied on the {@link ProductOffering} object.
     * @return the updated {@link ProductOffering ProductOffering}
     */
    public ProductOffering updateProductOffering(String id, ProductOfferingMVO productOfferingUpdate) {
        ProductOffering productOffering = template.findOne(Query.query(Criteria.where("id").is(id)), ProductOffering.class);
        productOfferingMapper.updateProductOffering(productOfferingUpdate, productOffering);
        productOffering.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));

        return template.save(productOffering);
    }

    /**
     * The method deletes an existing {@link ProductOffering ProductOffering}.
     *
     * @param id The Identifier of the {@link ProductOffering} to be deleted.
     * @return <code>true</code> if {@link ProductOffering} successfully deleted, <code>false</code>
     *         otherwise.
     */
    public boolean deleteProductOffering(String id) {
        ProductOffering productOffering = template.findById(id, ProductOffering.class);
        return template.remove(productOffering).getDeletedCount() > 0;
    }

}
