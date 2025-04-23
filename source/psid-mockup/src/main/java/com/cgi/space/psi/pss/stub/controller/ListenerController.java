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
import com.cgi.space.psi.common.model.Event;
import com.cgi.space.psi.common.model.EventCreate;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.EventApi;
import com.cgi.space.psi.pss.stub.filter.EventFilter;
import com.cgi.space.psi.pss.stub.service.ListenerService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for dispatching and listing {@link Event Events}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/eventManagement/v2")
public class ListenerController implements EventApi {

    @Autowired
    private ListenerService service;

    /**
     * An endpoint for dispatching an {@link Event} for a given Topic.
     *
     * @param topicId     Identifier of the parent Topic entity (required)
     * @param eventCreate The Event to be created (required)
     * @return The {@link Event} that has been dispatched
     */
    @Override
    @PsiOperation("TOD-01-02-03")
    public ResponseEntity<Event> createEvent(String topicId, EventCreate eventCreate) {
        Event result = service.dispatchEvent(topicId, eventCreate);
        log.debug("Dispatched new event: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for listing all {@link Event Events} if a given Topic.
     *
     * @param topicId Identifier of the parent Topic entity (required)
     * @param filter  Properties for filtering Events to be returned to the response (optional)
     * @param fields  Comma-separated properties to be provided in response (optional)
     * @param offset  Requested index for start of resources to be provided in response (optional)
     * @param limit   Requested number of resources to be provided in response (optional)
     * @return The list of all related {@link Event Events} in a Topic.
     */
    @Override
    public ResponseEntity<List<Event>> listEvent(String topicId, Optional<EventFilter> filter, Optional<String> fields,
                                                 Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Event> allEvents = service.getAllEvents(topicId, pageable, filter);
        return OffsetBasedPageRequest.toResponse(allEvents);
    }
}
