/*
 * Copyright 2024 CGI Deutschland B.V. & Co. KG
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cgi.space.psi.common.model.Alarm;
import com.cgi.space.psi.pss.stub.api.AlarmApi;
import com.cgi.space.psi.pss.stub.event.PublishedEvent;
import com.cgi.space.psi.pss.stub.event.ReceivedEvent;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class for managing operations for {@link Alarm Alarms}.
 */
@Slf4j
@Service
public class AlarmService {

    @Autowired
    private PageableMongoTemplate template;

    /**
     * This operation retrieves a Alarm entity by id.
     * 
     * @see {@link AlarmApi#retrieveAlarm(String, java.util.Optional)}
     * @param id to get
     * @return Alarm result or null
     */
    public Alarm getAlarmById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        var result = template.find(query, Alarm.class);
        return result.size() == 1 ? result.get(0) : null;
    }

    /**
     * This operation retrieves all Alarm entities related to the params.
     * 
     * @see {@link AlarmApi#listAlarm(java.util.Optional, java.util.Optional, java.util.Optional)}
     * @param pageable to paginate the result
     * @return Page<Alarm> the results
     */
    public Page<Alarm> getAllAlarms(Pageable pageable) {
        return template.find(new Query(), pageable, Alarm.class);
    }

    /**
     * Listens for received events concerning alarms.
     *
     * @param event the received event
     */
    @EventListener
    public void onApplicationEvent(ReceivedEvent event) {
        log.info("Received event: {} {}", event.getTopic(), event.getType());
        if (PublishedEvent.TOPIC_ALARM.equals(event.getTopic())) {
            if (PublishedEvent.TYPE_CREATED.equals(event.getType())
                    || PublishedEvent.TYPE_UPDATED.equals(event.getType())) {
                template.save(event.getValue());
            }
            else if (PublishedEvent.TYPE_DELETED.equals(event.getType())) {
                template.remove(event.getValue());
            }
        }
    }

}
