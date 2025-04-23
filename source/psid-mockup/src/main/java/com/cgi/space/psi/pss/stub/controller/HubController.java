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
import com.cgi.space.psi.common.model.HubSubscription;
import com.cgi.space.psi.common.model.HubSubscriptionCreate;
import com.cgi.space.psi.common.model.Topic;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.HubApi;
import com.cgi.space.psi.pss.stub.api.TopicApi;
import com.cgi.space.psi.pss.stub.service.HubService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link HubSubscription HubSubscriptions} for publishing events.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/eventManagement/v2")
public class HubController implements HubApi, TopicApi {

    @Autowired
    private HubService service;

    /**
     *  An endpoint for listing all {@link HubSubscription HubSubscriptions} for the {@link Topic} with the provided Identifier.
     *
     * @param topicId Identifier of the parent Topic entity (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return
     */
    @Override
    public ResponseEntity<List<HubSubscription>> listHubSubscription(String topicId, Optional<String> fields,
                                                                     Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<HubSubscription> allTopicSubscriptions = service.getAllTopicSubscriptions(topicId, pageable);
        return OffsetBasedPageRequest.toResponse(allTopicSubscriptions);
    }

    /**
     * An endpoint for listing all available {@link Topic Topics}.
     *
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit  Requested number of resources to be provided in response (optional)
     * @return All {@link Topic Topics}.
     */
    @Override
    @PsiOperation("TOD-01-02-01")
    public ResponseEntity<List<Topic>> listTopic(Optional<String> fields, Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Topic> allTopics = service.getAllTopics(pageable);
        return OffsetBasedPageRequest.toResponse(allTopics);
    }

    /**
     * An endpoint for creating a new {@link HubSubscription}.
     *
     * @param topicId               The Identifier of the {@link Topic} where the {@link HubSubscription} will be created.
     * @param hubSubscriptionCreate The Hub subscription to be created. (required)
     * @return ResponseEntity with the created {@link HubSubscription}.
     */
    @Override
    @PsiOperation("TOD-01-02-02")
    public ResponseEntity<HubSubscription> registerListener(String topicId, HubSubscriptionCreate hubSubscriptionCreate) {
        HubSubscription result = service.registerSubscription(topicId, hubSubscriptionCreate);
        log.info("Created new hub subscription: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint that removes an existing {@link HubSubscription} with given identifier from the identified {@link Topic}.
     *
     * @param topicId Identifier of the parent Topic entity (required)
     * @param id Identifier of the {@link HubSubscription} (required)
     */
    @Override
    @PsiOperation("TOD-01-02-04")
    public ResponseEntity<Void> unregisterListener(String topicId, String id) {
        boolean result = service.unregisterSubscription(topicId, id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }
}
