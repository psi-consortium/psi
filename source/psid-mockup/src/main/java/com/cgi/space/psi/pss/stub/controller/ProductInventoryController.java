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
package com.cgi.space.psi.pss.stub.controller;

import static com.cgi.space.psi.pss.stub.util.ControllerUtil.okOrNotFound;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.Product;
import com.cgi.space.psi.common.model.ProductFVO;
import com.cgi.space.psi.common.model.ProductMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.ProductApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.ProductFilter;
import com.cgi.space.psi.pss.stub.service.ProductInventoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link Product}s.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/productInventory/v2")
@Profile("!" + Profiles.PROVIDER)
public class ProductInventoryController implements ProductApi {

    @Autowired
    private ProductInventoryService service;

    /**
     * An endpoint for creating a new {@link Product}.
     *
     * @param productCreate The {@link ProductFVO} to be used to create {@link Product}
     *            (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link Product}
     */
    @Override
    @PsiOperation("TOD-05-03-01")
    public ResponseEntity<Product> createProduct(ProductFVO productCreate, Optional<String> fields) {
        Product result = service.createProduct(productCreate);
        log.info("Created new product: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for retrieving the {@link Product} with given identifier.
     *
     * @param id Identifier of the {@link Product} (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link Product}
     */
    @Override
    @PsiOperation("TOD-05-03-04")
    public ResponseEntity<Product> retrieveProduct(String id, Optional<String> fields) {
        return okOrNotFound(service.getProduct(id));
    }

    /**
     * An endpoint for listing all {@link Product}s.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return All {@link Product}s.
     */
    @Override
    @PsiOperation("TOD-05-03-05")
    public ResponseEntity<List<Product>> listProduct(Optional<ProductFilter> filter,
            Optional<String> fields, Optional<Integer> offset,
            Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Product> allProducts = service.getAllProducts(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allProducts);
    }

    /**
     * An endpoint that removes an existing {@link Product} with given identifier.
     *
     * @param id Identifier of the Product (required)
     */
    @Override
    @PsiOperation("TOD-05-03-03")
    public ResponseEntity<Void> deleteProduct(String id) {
        boolean result = service.deleteProduct(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint that updates an {@link Product} with given identifier.
     *
     * @param id Identifier of the Product (required)
     * @param productUpdate The Product to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link Product}
     */
    @Override
    @PsiOperation("TOD-05-03-02")
    public ResponseEntity<Product> patchProduct(String id, ProductMVO productUpdate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateProduct(id, productUpdate), HttpStatus.OK);
    }
}
