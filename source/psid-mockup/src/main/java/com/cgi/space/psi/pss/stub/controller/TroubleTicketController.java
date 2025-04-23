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
import com.cgi.space.psi.common.model.TroubleTicket;
import com.cgi.space.psi.common.model.TroubleTicketFVO;
import com.cgi.space.psi.common.model.TroubleTicketMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.TroubleTicketApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.TroubleTicketFilter;
import com.cgi.space.psi.pss.stub.service.TroubleTicketService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for trouble tickets.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/troubleTicket/v2")
@Profile("!" + Profiles.PROVIDER)
public class TroubleTicketController implements TroubleTicketApi {

    @Autowired
    private TroubleTicketService service;

    @Override
    @PsiOperation("TOD-01-04-01")
    public ResponseEntity<TroubleTicket> createTroubleTicket(TroubleTicketFVO troubleTicket, Optional<String> fields) {
        TroubleTicket result = service.createTroubleTicket(troubleTicket);
        log.info("Created new trouble ticket: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    @PsiOperation("TOD-01-04-04")
    public ResponseEntity<TroubleTicket> retrieveTroubleTicket(String id, Optional<String> fields) {
        return okOrNotFound(service.getTroubleTicket(id));
    }

    @Override
    @PsiOperation("TOD-01-04-05")
    public ResponseEntity<List<TroubleTicket>> listTroubleTicket(Optional<TroubleTicketFilter> filter,
            Optional<String> fields, Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<TroubleTicket> allTroubleTickets = service.getAllTroubleTickets(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allTroubleTickets);
    }

    @Override
    @PsiOperation("TOD-01-04-02")
    public ResponseEntity<TroubleTicket> patchTroubleTicket(String id, TroubleTicketMVO troubleTicket, Optional<String> fields) {
        return new ResponseEntity<>(service.updateTroubleTicket(id, troubleTicket), HttpStatus.OK);
    }

    @Override
    @PsiOperation("TOD-01-04-03")
    public ResponseEntity<Void> deleteTroubleTicket(String id) {
        boolean result = service.deleteTroubleTicket(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

}
