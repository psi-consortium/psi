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
import com.cgi.space.psi.common.model.ProductOffering;
import com.cgi.space.psi.common.model.ProductOfferingFVO;
import com.cgi.space.psi.common.model.ProductOfferingMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.ProductOfferingApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.ProductOfferingFilter;
import com.cgi.space.psi.pss.stub.service.ProductOfferingService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link ProductOffering}s.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/productCatalog/v2")
@Profile("!" + Profiles.PROVIDER)
public class ProductOfferingController implements ProductOfferingApi {

    @Autowired
    private ProductOfferingService service;

    /**
     * An endpoint for creating a new {@link ProductOffering}.
     *
     * @param productOfferingCreate The {@link ProductOfferingFVO} to be used to create {@link ProductOffering} (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link ProductOffering}
     */
    @Override
    @PsiOperation("TOD-02-04-01")
    public ResponseEntity<ProductOffering> createProductOffering(ProductOfferingFVO productOfferingCreate, Optional<String> fields) {
        ProductOffering result = service.createProductOffering(productOfferingCreate);
        log.info("Created new product offering: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for retrieving the {@link ProductOffering} with given identifier.
     *
     * @param id     Identifier of the {@link ProductOffering} (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link ProductOffering}
     */
    @Override
    @PsiOperation("TOD-02-04-04")
    public ResponseEntity<ProductOffering> retrieveProductOffering(String id, Optional<String> fields) {
        return okOrNotFound(service.getProductOffering(id));
    }

    /**
     * An endpoint for listing all {@link ProductOffering}s.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit  Requested number of resources to be provided in response (optional)
     * @return All {@link ProductOffering}s.
     */
    @Override
    @PsiOperation("TOD-02-04-05")
    public ResponseEntity<List<ProductOffering>> listProductOffering(Optional<ProductOfferingFilter> filter,
                                                                     Optional<String> fields, Optional<Integer> offset,
                                                                     Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<ProductOffering> allProductOfferings = service.getAllProductOfferings(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allProductOfferings);
    }

    /**
     * An endpoint that removes an existing {@link ProductOffering} with given identifier.
     *
     * @param id Identifier of the ProductOffering (required)
     */
    @Override
    @PsiOperation("TOD-02-04-03")
    public ResponseEntity<Void> deleteProductOffering(String id) {
        boolean result = service.deleteProductOffering(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint that updates an {@link ProductOffering} with given identifier.
     *
     * @param id                    Identifier of the ProductOffering (required)
     * @param productOfferingUpdate The ProductOffering to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link ProductOffering}
     */
    @Override
    @PsiOperation("TOD-02-04-02")
    public ResponseEntity<ProductOffering> patchProductOffering(String id, ProductOfferingMVO productOfferingUpdate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateProductOffering(id, productOfferingUpdate), HttpStatus.OK);
    }
}
