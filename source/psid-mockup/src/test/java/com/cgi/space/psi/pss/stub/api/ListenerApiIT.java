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
package com.cgi.space.psi.pss.stub.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cgi.space.psi.common.EventWrapper;
import com.cgi.space.psi.common.model.Event;
import com.cgi.space.psi.common.model.Topic;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ListenerApiIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // remove explicitly the topics created on application startup for demo purposes
        mongoTemplate.remove(Topic.class).all();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(EventWrapper.class).all();
        mongoTemplate.remove(Topic.class).all();
    }

    @Test
    public void testDispatchEventToTopic() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + topicId + "/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"eventType\": \"ChangeOrderStateEvent\" }"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("eventType").value("ChangeOrderStateEvent"));
    }

    @Test
    public void testListAllEvents() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        Event firstEvent = new Event();
        firstEvent.setId("c9198cff-9b2d-4aa5-ad18-ffe54f22cd4a");
        firstEvent.setEventType("eventType1");
        mongoTemplate.save(new EventWrapper(firstEvent, topicId));

        Event secondEvent = new Event();
        secondEvent.setId("d604bec9-1f69-42e6-832a-035bce59a6cb");
        secondEvent.setEventType("eventType2");
        mongoTemplate.save(new EventWrapper(secondEvent, topicId));

        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/event"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("c9198cff-9b2d-4aa5-ad18-ffe54f22cd4a"))
                .andExpect(jsonPath("$[0].eventType").value("eventType1"))
                .andExpect(jsonPath("$[1].id").value("d604bec9-1f69-42e6-832a-035bce59a6cb"))
                .andExpect(jsonPath("$[1].eventType").value("eventType2"));
    }

    @Test
    public void testListAllEventsWithFields() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        Event event = new Event();
        event.setId("c9198cff-9b2d-4aa5-ad18-ffe54f22cd4a");
        event.setEventType("eventType");
        event.setEventId("eventId");
        mongoTemplate.save(new EventWrapper(event, topicId));

        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/event?fields=id,eventType"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].id").value("c9198cff-9b2d-4aa5-ad18-ffe54f22cd4a"))
                .andExpect(jsonPath("$[0].eventType").exists())
                .andExpect(jsonPath("$[0].eventType").value("eventType"))
                .andExpect(jsonPath("$[0].eventId").doesNotExist());
    }

    @Test
    public void testEventsWithOffsetAndLimit() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        List<EventWrapper> events = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Event event = new Event();
                    event.setId(UUID.randomUUID().toString());
                    event.setEventType("eventType" + num);
                    events.add(mongoTemplate.save(new EventWrapper(event, topicId)));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/event?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(events.get(offset).getEvent().getId()))
                .andExpect(jsonPath("$[1].id").value(events.get(offset + 1).getEvent().getId()))
                .andExpect(jsonPath("$[2].id").value(events.get(offset + 2).getEvent().getId()))
                .andExpect(jsonPath("$[3].id").value(events.get(offset + 3).getEvent().getId()))
                .andExpect(jsonPath("$[4].id").value(events.get(offset + 4).getEvent().getId()));
    }

    @Test
    public void testEventsWithLimitOnly() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        List<EventWrapper> events = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Event event = new Event();
                    event.setId(UUID.randomUUID().toString());
                    event.setEventType("eventType" + num);
                    events.add(mongoTemplate.save(new EventWrapper(event, topicId)));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/event?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(events.get(defaultOffset).getEvent().getId()))
                .andExpect(jsonPath("$[1].id").value(events.get(defaultOffset + 1).getEvent().getId()))
                .andExpect(jsonPath("$[2].id").value(events.get(defaultOffset + 2).getEvent().getId()))
                .andExpect(jsonPath("$[3].id").value(events.get(defaultOffset + 3).getEvent().getId()))
                .andExpect(jsonPath("$[4].id").value(events.get(defaultOffset + 4).getEvent().getId()));
    }

    @Test
    public void testListEventsWithTimeRaisedFilters() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        List<EventWrapper> events = new ArrayList<>();
        IntStream.range(0, 5).forEach(num -> {
                    Event event = new Event();
                    event.setId(UUID.randomUUID().toString());
                    if (num > 0) {
                        event.setEventTime(OffsetDateTime.parse("2020-09-2" + num + "T00:00:00Z"));
                    }
                    events.add(mongoTemplate.save(new EventWrapper(event, topicId)));
                }
        );

        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/event?raisedBefore=2020-09-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].eventTime").doesNotExist())
                .andExpect(jsonPath("$[1].eventTime").value("2020-09-21T00:00:00Z"))
                .andExpect(jsonPath("$[2].eventTime").value("2020-09-22T00:00:00Z"))
                .andExpect(jsonPath("$[3].eventTime").value("2020-09-23T00:00:00Z"));

        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/event?raisedAfter=2020-09-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].eventTime").doesNotExist())
                .andExpect(jsonPath("$[1].eventTime").value("2020-09-24T00:00:00Z"));
    }
}
