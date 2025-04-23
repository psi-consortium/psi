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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.CheckProductStock;
import com.cgi.space.psi.common.model.CheckProductStockCreate;
import com.cgi.space.psi.common.model.ProductStock;
import com.cgi.space.psi.pss.stub.api.CheckProductStockApi;
import com.cgi.space.psi.pss.stub.service.CheckProductStockService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link CheckProductStock}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/stock/v2")
public class CheckProductStockController implements CheckProductStockApi {

    @Autowired
    private CheckProductStockService service;

    /**
     * An endpoint for creating a new {@link ProductStock}.
     *
     * @param checkProductStockCreate The {@link CheckProductStockCreate} to be used to create
     *            {@link CheckProductStock} (required)
     * @return ResponseEntity with the created {@link CheckProductStock}
     */
    @Override
    @PsiOperation("TOD-05-04-01")
    public ResponseEntity<CheckProductStock> createCheckProductStock(CheckProductStockCreate checkProductStockCreate) {
        CheckProductStock result = service.createCheckProductStock(checkProductStockCreate);
        log.debug("Created new CheckProductStock: {}", result);
        HttpStatus status = checkProductStockCreate.getInstantSyncCheck() ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(result, status);
    }

    /**
     * An endpoint for retrieving the {@link CheckProductStock} with given identifier.
     *
     * @param id Identifier of the {@link CheckProductStock} (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link CheckProductStock}
     */
    @Override
    @PsiOperation("TOD-05-04-01")
    public ResponseEntity<CheckProductStock> retrieveCheckProductStock(String id, Optional<String> fields) {
        var checkProductStock = service.getCheckProductStock(id);
        HttpStatus status = checkProductStock != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(checkProductStock, status);
    }
}
