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
package com.cgi.space.psi.pss.stub.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cgi.space.psi.common.model.Alarm;
import com.cgi.space.psi.common.model.Topic;
import com.cgi.space.psi.pss.stub.event.AbstractEvent;
import org.springframework.http.MediaType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AlarmApiIT {

    private static final List<String> alarmIds = List.of(UUID.randomUUID().toString(), UUID.randomUUID().toString());

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MongoTemplate mongoTemplate;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mongoTemplate.remove(Alarm.class).all();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        alarmIds.stream()
                .map(id -> new Alarm().id(id).alarmDetails("This is alarm " + id))
                .forEach(a -> mongoTemplate.save(a));
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(Alarm.class).all();
    }

    @Test
    public void testListAllAlarm() throws Exception {
        this.mockMvc.perform(get("/psi-api/alarmManagement/v2/alarm"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(alarmIds.size()))
                .andExpect(jsonPath("$[0].id").value(alarmIds.get(0)))
                .andExpect(jsonPath("$[1].id").value(alarmIds.get(1)));
    }

    @Test
    public void testRetrieveAlarm() throws Exception {
        final String id = alarmIds.get(0);

        this.mockMvc.perform(get("/psi-api/alarmManagement/v2/alarm/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id").value(id));

        this.mockMvc.perform(get("/psi-api/alarmManagement/v2/alarm/" + UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isNotFound());

        // only happy cases
        // this.mockMvc.perform(get("/psi-api/alarmManagement/v2/alarm/" + UUID.randomUUID()))
        //         .andDo(print())
        //         .andExpect(status().is5xxServerError());
    }

    @Test
    public void testCreateAlarmViaEventApi() throws Exception {
        Topic alarmTopic = mongoTemplate.findAll(Topic.class).stream()
                .filter(t -> t.getName().equals(AbstractEvent.TOPIC_ALARM))
                .findFirst()
                .orElseThrow();

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + alarmTopic.getId() + "/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"domain\": \"alarm\", \"eventType\": \"created\", \"event\": { \"id\": \"dcf884e4-8414-4699-9b39-e64a9e4e7b55\", \"state\": \"raised\", \"@type\": \"Alarm\" } }"))
                .andDo(print())
                .andExpect(status().isCreated());

        assertThat(mongoTemplate.findById("dcf884e4-8414-4699-9b39-e64a9e4e7b55", Alarm.class).getState(), is(Alarm.StateEnum.RAISED));
    }

    @Test
    public void testUpdateAlarmViaEventApi() throws Exception {
        Topic alarmTopic = mongoTemplate.findAll(Topic.class).stream()
                .filter(t -> t.getName().equals(AbstractEvent.TOPIC_ALARM))
                .findFirst()
                .orElseThrow();

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + alarmTopic.getId() + "/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"domain\": \"alarm\", \"eventType\": \"updated\", \"event\": { \"id\": \"" + alarmIds.get(0) + "\", \"state\": \"updated\", \"@type\": \"Alarm\" } }"))
                .andDo(print())
                .andExpect(status().isCreated());

        assertThat(mongoTemplate.findById(alarmIds.get(0), Alarm.class).getState(), is(Alarm.StateEnum.UPDATED));
    }

    @Test
    public void testDeleteAlarmViaEventApi() throws Exception {
        Topic alarmTopic = mongoTemplate.findAll(Topic.class).stream()
                .filter(t -> t.getName().equals(AbstractEvent.TOPIC_ALARM))
                .findFirst()
                .orElseThrow();

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + alarmTopic.getId() + "/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"domain\": \"alarm\", \"eventType\": \"deleted\", \"event\": { \"id\": \"" + alarmIds.get(0) + "\", \"state\": \"cleared\", \"@type\": \"Alarm\" } }"))
                .andDo(print())
                .andExpect(status().isCreated());

        assertThat(mongoTemplate.findById(alarmIds.get(0), Alarm.class), is(nullValue()));
    }

}
