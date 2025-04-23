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

import com.cgi.space.psi.common.model.Product;
import com.cgi.space.psi.common.model.ProductFVO;
import com.cgi.space.psi.common.model.ProductStatusType;
import com.cgi.space.psi.common.model.ProductMVO;
import com.cgi.space.psi.pss.stub.controller.ProductInventoryController;
import com.cgi.space.psi.pss.stub.filter.ProductFilter;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.ProductMapper;
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
 * Service class for managing CRUD operations for {@link Product}s.
 */
@Service
public class ProductInventoryService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link Product} object.
     *
     * @param productCreate the {@link ProductFVO} object based on which
     *            the {@link Product} is created.
     * @return the newly created {@link Product} object.
     */
    public Product createProduct(ProductFVO productCreate) {
        Product product = productMapper.toProduct(productCreate);
        product.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(ProductInventoryController.class)
                .retrieveProduct(product.getId(), Optional.empty())).withSelfRel();
        product.setHref(self.toUri());

        return template.save(product);
    }

    /**
     * The method retrieves an existing {@link Product}.
     *
     * @param id The Identifier of the {@link Product} to be retrieved.
     * @return The retrieved {@link Product}.
     */
    public Product getProduct(String id) {
        return template.findById(id, Product.class);
    }

    /**
     * The method retrieves all {@link Product Products}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link Product Products}
     */
    public Page<Product> getAllProducts(Pageable pageable, Optional<ProductFilter> filter) {
        return template.find(buildQuery(filter), pageable, Product.class);
    }

    private Query buildQuery(Optional<ProductFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("description"), filterProps.getDescriptionContains());
            builder.createContains(List.of("name", "description"), filterProps.getNameOrDescriptionContains());
            builder.createEqualsAny("productSerialNumber", filterProps.getSerialNumber());
            builder.createEqualsAny("status", filterProps.getStatus(), ProductStatusType::fromValue);
            builder.createContains(List.of("relatedParty.partyOrPartyRole.name"), filterProps.getRelatedPartyContains());
        });
        return builder.getQuery();
    }

    /**
     * The method retrieves all {@link Product Products} of the given specification.
     *
     * @param id the specification id
     * @return all {@link Product Products}
     */
    public List<Product> getAllByProductSpecificationId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("productSpecification.id").is(id));
        return template.find(query, Product.class);
    }

    /**
     * The method updates an existing {@link Product Product}.
     *
     * @param id The Identifier of the {@link Product} to be updated.
     * @param productUpdate The {@link ProductMVO} object containing the updates to be applied to
     *            the {@link Product} object.
     * @return the updated {@link Product Product}
     */
    public Product updateProduct(String id, ProductMVO productUpdate) {
        Product product = template.findOne(Query.query(Criteria.where("id").is(id)), Product.class);
        productMapper.updateProduct(productUpdate, product);

        return template.save(product);
    }

    /**
     * The method deletes an existing {@link Product Product}.
     *
     * @param id The Identifier of the {@link Product} to be deleted.
     * @return <code>true</code> if {@link Product} successfully deleted, <code>false</code>
     *         otherwise.
     */
    public boolean deleteProduct(String id) {
        Product product = template.findById(id, Product.class);
        return template.remove(product).getDeletedCount() > 0;
    }

}
