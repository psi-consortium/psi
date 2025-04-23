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

import com.cgi.space.psi.common.EventWrapper;
import com.cgi.space.psi.common.model.Event;
import com.cgi.space.psi.common.model.EventCreate;
import com.cgi.space.psi.common.model.Topic;
import com.cgi.space.psi.pss.stub.event.ReceivedEvent;
import com.cgi.space.psi.pss.stub.filter.EventFilter;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.EventMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Service class for managing operations for {@link Event Events}.
 */
@Slf4j
@Service
public class ListenerService {

    private static final String EVENT_TIME = "event.eventTime";
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private EventMapper mapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates (dispatches) an {@link Event}.
     *
     * @param topicId     The Identifier of the {@link Topic} to which the {@link Event} will be dispatched.
     * @param eventCreate The {@link EventCreate} object based on which the {@link Event} is created.
     * @return The newly created (dispatched) {@link Event} object.
     */
    public Event dispatchEvent(String topicId, EventCreate eventCreate) {
        Event event = mapper.toEvent(eventCreate);
        event.setId(UUID.randomUUID().toString());
        EventWrapper wrapper = new EventWrapper(event, topicId);
        template.save(wrapper);

        Topic topic = template.findById(topicId, Topic.class);
        applicationEventPublisher.publishEvent(new ReceivedEvent<>(topic.getName(), eventCreate.getEventType(), eventCreate.getEvent()));
        return event;
    }

    /**
     * The method retrieves all available {@link Event Events} in a {@link Topic}.
     *
     * @param topicId The Identifier of the {@link Topic} for which the {@link Event Events} should be retrieved.
     * @param pageable Collection of pagination parameters
     * @param filter  Properties for filtering Events to be returned to the response (optional)
     * @return All {@link Event Events}.
     */
    public Page<Event> getAllEvents(String topicId, Pageable pageable, Optional<EventFilter> filter) {
        final Page<EventWrapper> eventWrappers = template.find(buildQuery(topicId, filter), pageable, EventWrapper.class);
        final List<Event> events = eventWrappers.stream().map(s -> s.getEvent()).collect(Collectors.toList());
        return new PageImpl<>(events, pageable, eventWrappers.getTotalElements());
    }

    private Query buildQuery(String topicId, Optional<EventFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        builder.createEquals("topicId", topicId);
        filter.ifPresent(filterProps -> {
            if (StringUtils.isNotBlank(filterProps.getRaisedBefore())) {
                builder.orOperator(List.of(
                        Criteria.where(EVENT_TIME).isNull(),
                        Criteria.where(EVENT_TIME).lte(OffsetDateTime.parse(filterProps.getRaisedBefore()))
                ));
            }
            if (StringUtils.isNotBlank(filterProps.getRaisedAfter())) {
                builder.orOperator(List.of(
                        Criteria.where(EVENT_TIME).isNull(),
                        Criteria.where(EVENT_TIME).gt(OffsetDateTime.parse(filterProps.getRaisedAfter()))
                ));
            }
        });
        return builder.getQuery();
    }
}
