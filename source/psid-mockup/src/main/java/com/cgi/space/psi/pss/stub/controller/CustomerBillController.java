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
import com.cgi.space.psi.common.model.CustomerBill;
import com.cgi.space.psi.common.model.CustomerBillFVO;
import com.cgi.space.psi.common.model.CustomerBillMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.CustomerBillApi;
import com.cgi.space.psi.pss.stub.filter.CustomerBillFilter;
import com.cgi.space.psi.pss.stub.service.CustomerBillService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link CustomerBill}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/customerBillManagement/v2")
public class CustomerBillController implements CustomerBillApi {

    @Autowired
    private CustomerBillService service;

    /**
     * An endpoint for creating a new {@link CustomerBill}.
     *
     * @param customerBillCreate The {@link CustomerBillFVO} to be used to create
     *            {@link CustomerBill} (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link CustomerBill}
     */
    @Override
    @PsiOperation("TOD-03-03-01")
    public ResponseEntity<CustomerBill> createCustomerBill(CustomerBillFVO customerBillCreate, Optional<String> fields) {
        CustomerBill result = service.createCustomerBill(customerBillCreate);
        log.debug("Created new customer bill: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for listing all {@link CustomerBill}s.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return All (filtered) {@link CustomerBill CustomerBills}.
     */
    @Override
    @PsiOperation("TOD-03-03-04")
    public ResponseEntity<List<CustomerBill>> listCustomerBill(Optional<CustomerBillFilter> filter,
            Optional<String> fields, Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<CustomerBill> allCustomerBills = service.getAllCustomerBills(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allCustomerBills);
    }

    /**
     * An endpoint that updates a {@link CustomerBill} (state only) with given identifier.
     *
     * @param id Identifier of the {@link CustomerBill} (required)
     * @param customerBillUpdate The {@link CustomerBill} to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link CustomerBill}.
     */
    @Override
    @PsiOperation("TOD-03-03-02")
    public ResponseEntity<CustomerBill> patchCustomerBill(String id, CustomerBillMVO customerBillUpdate, Optional<String> fields) {
        return okOrNotFound(service.updateCustomerBill(id, customerBillUpdate));
    }

    /**
     * An endpoint that cancels a {@link CustomerBill} with given identifier.
     *
     * @param id Identifier of the {@link CustomerBill} (required)
     */
    @Override
    @PsiOperation("TOD-03-03-05")
    public ResponseEntity<Void> deleteCustomerBill(String id) {
        boolean result = service.cancelCustomerBill(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint for retrieving the {@link CustomerBill} with given identifier.
     *
     * @param id Identifier of the {@link CustomerBill} (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link CustomerBill}
     */
    @Override
    @PsiOperation("TOD-03-03-03")
    public ResponseEntity<CustomerBill> retrieveCustomerBill(String id, Optional<String> fields) {
        return okOrNotFound(service.getCustomerBill(id));
    }
}
