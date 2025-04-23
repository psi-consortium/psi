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

import com.cgi.space.psi.common.model.Mission;
import com.cgi.space.psi.common.model.MissionStatusType;
import com.cgi.space.psi.common.model.TimePeriod;
import com.cgi.space.psi.pss.stub.config.Profiles;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.oneOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(Profiles.PSS)
public class MissionApiIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    private MockMvc mockMvc;

    private static final String TEMPLATE_COLLECTION = "mission";

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(new Query(), TEMPLATE_COLLECTION);
    }

    @Test
    public void testCreateMission() throws Exception {
        this.mockMvc.perform(post("/psi-api/missionManagement/v2/mission")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Foobar\", \"@type\": \"Mission\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testDeleteMission() throws Exception {
        Mission mission = new Mission();
        mission.setId("7a781cf5-24bf-4e0e-980c-6061423bb77e");
        mongoTemplate.save(mission, TEMPLATE_COLLECTION);

        this.mockMvc.perform(delete("/psi-api/missionManagement/v2/mission/7a781cf5-24bf-4e0e-980c-6061423bb77e"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListMission() throws Exception {
        Mission mission = new Mission();
        mission.setId("3ace4a35-8ccf-4e47-a810-85176570958e");
        mission.setTimeframe(new TimePeriod()
                .startDateTime(OffsetDateTime.parse("2024-08-01T00:00:00Z"))
                .endDateTime(OffsetDateTime.parse("2024-08-31T00:00:00Z"))
        );
        mongoTemplate.save(mission, TEMPLATE_COLLECTION);
        mission = new Mission();
        mission.setId("677ecae3-adb6-4c6d-a8cd-aa0600360c22");
        mission.setTimeframe(new TimePeriod()
                .startDateTime(OffsetDateTime.parse("2024-10-01T00:00:00Z"))
                .endDateTime(OffsetDateTime.parse("2024-10-31T00:00:00Z"))
        );
        mongoTemplate.save(mission, TEMPLATE_COLLECTION);

        this.mockMvc.perform(get("/psi-api/missionManagement/v2/mission"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));

        this.mockMvc.perform(get("/psi-api/missionManagement/v2/mission?runningBefore=2024-09-01T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("3ace4a35-8ccf-4e47-a810-85176570958e"));

        this.mockMvc.perform(get("/psi-api/missionManagement/v2/mission?runningAfter=2024-09-01T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("677ecae3-adb6-4c6d-a8cd-aa0600360c22"));
    }

    @Test
    public void testPatchMission() throws Exception {
        Mission mission = new Mission();
        mission.setId("b3b44fa0-bef5-4164-b41f-9c41e199fa48");
        mission.setStatus(MissionStatusType.ACTIVE);
        mongoTemplate.save(mission, TEMPLATE_COLLECTION);

        this.mockMvc.perform(patch("/psi-api/missionManagement/v2/mission/b3b44fa0-bef5-4164-b41f-9c41e199fa48")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Foobar\", \"geographicAddress\": [{\"@type\": \"GeographicAddress\"}], "
                        + "\"asset\": [{\"servicePeriod\": {\"startOffset\": \"P7D\", \"endOffset\": \"-P7D\"}}], \"@type\": \"Mission\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("status").value("active"))
                .andExpect(jsonPath("asset").isArray())
                .andExpect(jsonPath("asset.length()").value("1"))
                // ISO 8601-2:2019 prefers the "-" to be at the front, but Java decided to have it in the number itself
                .andExpect(jsonPath("asset[0].servicePeriod.endOffset").value(oneOf("-P7D", "P-7D")));
    }

    @Test
    public void testRetrieveMission() throws Exception {
        Mission mission = new Mission();
        mission.setId("2a6f805b-2f1b-4314-baf9-7c5898a213fe");
        mongoTemplate.save(mission, TEMPLATE_COLLECTION);

        this.mockMvc.perform(get("/psi-api/missionManagement/v2/mission/2a6f805b-2f1b-4314-baf9-7c5898a213fe"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("2a6f805b-2f1b-4314-baf9-7c5898a213fe"));
    }

    @Test
    public void testRetrieveMissionWithFields() throws Exception {
        Mission mission = new Mission();
        mission.setId("fcccc924-3761-476c-9fab-ddfc4157becd");
        mission.setName("Foo");
        mission.setStatus(MissionStatusType.ACTIVE);
        mongoTemplate.save(mission, TEMPLATE_COLLECTION);

        this.mockMvc.perform(get("/psi-api/missionManagement/v2/mission/fcccc924-3761-476c-9fab-ddfc4157becd?fields=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("fcccc924-3761-476c-9fab-ddfc4157becd"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("status").doesNotExist());
    }

}
