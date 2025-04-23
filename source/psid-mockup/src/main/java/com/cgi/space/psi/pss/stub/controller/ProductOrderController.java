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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.ProductOrder;
import com.cgi.space.psi.common.model.ProductOrderFVO;
import com.cgi.space.psi.common.model.ProductOrderStateType;
import com.cgi.space.psi.common.model.ProductOrderMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.ProductOrderApi;
import com.cgi.space.psi.pss.stub.service.ProductOrderService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link ProductOrder}s.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/productOrdering/v2")
public class ProductOrderController implements ProductOrderApi {

    @Autowired
    private ProductOrderService service;

    /**
     * An endpoint for creating a new {@link ProductOrder}.
     *
     * @param productOrderCreate The {@link ProductOrderFVO} to be used to create {@link ProductOrder} (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link ProductOrder}
     */
    @Override
    @PsiOperation("TOD-03-02-01")
    public ResponseEntity<ProductOrder> createProductOrder(ProductOrderFVO productOrderCreate, Optional<String> fields) {
        ProductOrder result = service.createProductOrder(productOrderCreate);
        log.info("Created new product order: {}", result);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for retrieving the {@link ProductOrder} with given identifier.
     *
     * @param id     Identifier of the {@link ProductOrder} (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link ProductOrder}
     */
    @Override
    @PsiOperation("TOD-03-02-03")
    public ResponseEntity<ProductOrder> retrieveProductOrder(String id, Optional<String> fields) {
        return okOrNotFound(service.getProductOrder(id));
    }

    /**
     * An endpoint for listing all {@link ProductOrder ProductOrders}.
     *
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit  Requested number of resources to be provided in response (optional)
     * @return All {@link ProductOrder ProductOrders}.
     */
    @Override
    @PsiOperation("TOD-03-02-04")
    public ResponseEntity<List<ProductOrder>> listProductOrder(Optional<String> fields, Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<ProductOrder> allProductOrders = service.getAllProductOrders(pageable);
        return OffsetBasedPageRequest.toResponse(allProductOrders);
    }

    /**
     * An endpoint that updates a {@link ProductOrder} with given identifier.
     *
     * @param id                 Identifier of the ProductOrder (required)
     * @param productOrderUpdate The ProductOrder to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link ProductOrder}
     */
    @Override
    @PsiOperation("TOD-03-02-02")
    public ResponseEntity<ProductOrder> patchProductOrder(String id, ProductOrderMVO productOrderUpdate, Optional<String> fields) {
        ProductOrder productOrder = service.getProductOrder(id);
        if (ProductOrderStateType.CANCELLED.equals(productOrder.getState()) ||
                ProductOrderStateType.COMPLETED.equals(productOrder.getState())) {
            return new ResponseEntity<>(productOrder, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(service.updateProductOrder(productOrder, productOrderUpdate), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteProductOrder(String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
