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
import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.CustomerInquiry;
import com.cgi.space.psi.common.model.CustomerInquiryFVO;
import com.cgi.space.psi.common.model.CustomerInquiryMVO;
import com.cgi.space.psi.common.model.InquiryResult;
import com.cgi.space.psi.common.model.InquiryStateType;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.CustomerInquiryApi;
import com.cgi.space.psi.pss.stub.service.CustomerInquiryService;
import com.cgi.space.psi.pss.stub.service.MatchmakingService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link CustomerInquiry}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/customerInquiry/v2")
public class CustomerInquiryController implements CustomerInquiryApi {

    @Autowired
    private CustomerInquiryService service;
    @Autowired
    private MatchmakingService matchmaking;

    @Override
    @PsiOperation("TOD-03-01-01")
    public ResponseEntity<CustomerInquiry> createCustomerInquiry(CustomerInquiryFVO inquiryCreate) {
        CustomerInquiry result = service.createCustomerInquiry(inquiryCreate);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @Override
    @PsiOperation("TOD-03-01-04")
    public ResponseEntity<CustomerInquiry> patchCustomerInquiry(String id, CustomerInquiryMVO inquiryUpdate) {
        CustomerInquiry inquiry = service.getCustomerInquiry(id);
        if (inquiry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (InquiryStateType.CANCELLED.equals(inquiry.getState())
                || InquiryStateType.COMPLETED.equals(inquiry.getState())) {
            return new ResponseEntity<>(inquiry, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(
                service.updateCustomerInquiry(id, inquiryUpdate),
                HttpStatus.ACCEPTED);
    }

    @Override
    @PsiOperation("TOD-03-01-05")
    public ResponseEntity<Void> deleteCustomerInquiry(String id) {
        boolean result = service.cancelCustomerInquiry(id);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    @Override
    @PsiOperation("TOD-03-01-02")
    public ResponseEntity<CustomerInquiry> retrieveCustomerInquiry(String id) {
        return okOrNotFound(service.getCustomerInquiry(id));
    }

    @Override
    @PsiOperation("TOD-03-01-03")
    public ResponseEntity<List<InquiryResult>> retrieveCustomerInquiryResults(String id, @Valid Optional<String> fields, @Valid Optional<Integer> offset,
            @Valid Optional<Integer> limit) {
        return getInquiryResult(id, (inquiry) -> matchmaking.getInquiryResults(inquiry, OffsetBasedPageRequest.of(offset, limit)));
    }

    private <T> ResponseEntity<List<T>> getInquiryResult(String id, Function<CustomerInquiry, Page<T>> resultFunc) {
        CustomerInquiry inquiry = service.getCustomerInquiry(id);
        if (inquiry == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (inquiry.getState() == InquiryStateType.INPROGRESS) {
            log.info("Inquiry {} is in progress, waiting", id);
            try {
                service.waitUntilComplete(inquiry);
                inquiry = service.getCustomerInquiry(id);
            }
            catch (InterruptedException ex) {
                log.error("Interrupted while waiting for inquiry", ex);
            }
        }

        switch (inquiry.getState()) {
            case PENDING:
            case INPROGRESS:
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            case CANCELLED:
                return ResponseEntity.status(HttpStatus.GONE).build();
            case COMPLETED:
                return OffsetBasedPageRequest.toResponse(resultFunc.apply(inquiry));
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
