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

import com.cgi.space.psi.common.model.Interval;
import com.cgi.space.psi.common.model.OutputFormat;
import com.cgi.space.psi.common.model.PerformanceJob;
import com.cgi.space.psi.common.model.PerformanceJobStateType;
import com.cgi.space.psi.common.model.PerformanceProfileValue;
import com.cgi.space.psi.common.model.PerformanceReport;
import com.cgi.space.psi.pss.stub.config.Profiles;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(Profiles.PROVIDER)
public class PerformanceJobApiIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(PerformanceJob.class).all();
        mongoTemplate.remove(PerformanceReport.class).all();
    }

    @Test
    public void testCreatePerformanceJob() throws Exception {
        this.mockMvc.perform(post("/psi-api/performanceMonitoring/v2/performanceJob")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                        + "  \"description\": \"on-demand report\",\n"
                        + "  \"performanceProfile\": {\n"
                        + "    \"@type\": \"PerformanceProfileValue\",\n"
                        + "    \"jobType\": \"on-demand\",\n"
                        + "    \"granularity\": \"1 hour\",\n"
                        + "    \"outputFormat\": \"json\",\n"
                        + "    \"resultFormat\": \"payload\"\n"
                        + "  },\n"
                        + "  \"servicePayloadSpecificAttributes\": {\n"
                        + "    \"@type\": \"urn:mef:lso:spec:legato:ip-performance-monitoring-configuration:v0.0.1:all\",\n"
                        + "    \"interface\": {\n"
                        + "      \"serviceEndpoint\": \"etc\"\n"
                        + "    },\n"
                        + "    \"vlan\": 100,\n"
                        + "    \"packetsIn\": true,\n"
                        + "    \"charsIn\": true,\n"
                        + "    \"packetsOut\": true,\n"
                        + "    \"charsOut\": true\n"
                        + "  }\n"
                        + "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("description").value("on-demand report"))
                .andExpect(jsonPath("performanceProfile.outputFormat").value("json"));
    }

    @Test
    public void testCreateModifyPerformanceJob() throws Exception {
        PerformanceJob job = new PerformanceJob();
        job.setId("d3f4e3a3-3d8f-43aa-9383-d8c32d606ab6");
        job.setPerformanceProfile(new PerformanceProfileValue().outputFormat(OutputFormat.JSON));
        mongoTemplate.save(job);

        this.mockMvc.perform(post("/psi-api/performanceMonitoring/v2/modifyPerformanceJob")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        + "\"performanceJob\": {\"id\": \"d3f4e3a3-3d8f-43aa-9383-d8c32d606ab6\", \"@type\": \"PerformanceJobRef\"},"
                        + "\"performanceProfile\": {\"outputFormat\": \"csv\"}"
                        + "}"))
                .andDo(print())
                .andExpect(status().isCreated());

        final PerformanceJob persistedJob = mongoTemplate.findById(job.getId(), PerformanceJob.class);
        assertEquals(OutputFormat.CSV, persistedJob.getPerformanceProfile().getOutputFormat());
    }

    @Test
    public void testCreateCancelPerformanceJob() throws Exception {
        PerformanceJob job = new PerformanceJob();
        job.setId("16f8255c-3149-4550-8ea3-b58ce422e782");
        job.setPerformanceProfile(new PerformanceProfileValue().outputFormat(OutputFormat.JSON));
        job.setState(PerformanceJobStateType.SCHEDULED);
        mongoTemplate.save(job);

        this.mockMvc.perform(post("/psi-api/performanceMonitoring/v2/cancelPerformanceJob")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{"
                        + "\"performanceJob\": {\"id\": \"16f8255c-3149-4550-8ea3-b58ce422e782\", \"@type\": \"PerformanceJobRef\"}"
                        + "}"))
                .andDo(print())
                .andExpect(status().isCreated());

        final PerformanceJob persistedJob = mongoTemplate.findById(job.getId(), PerformanceJob.class);
        assertEquals(PerformanceJobStateType.CANCELLED, persistedJob.getState());
    }

    @Test
    public void testRetrievePerformanceJob() throws Exception {
        PerformanceJob job = new PerformanceJob();
        job.setId("e3bd37e0-94be-49bb-a878-cc9208a52e27");
        job.setPerformanceProfile(new PerformanceProfileValue().outputFormat(OutputFormat.JSON));
        mongoTemplate.save(job);

        this.mockMvc.perform(get("/psi-api/performanceMonitoring/v2/performanceJob/e3bd37e0-94be-49bb-a878-cc9208a52e27"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("e3bd37e0-94be-49bb-a878-cc9208a52e27"))
                .andExpect(jsonPath("performanceProfile.outputFormat").value("json"));
    }

    @Test
    public void testListPerformanceJob() throws Exception {
        PerformanceJob job = new PerformanceJob();
        job.setId("f880e413-4a15-4c51-8b0b-a529f101a1c0");
        job.setPerformanceProfile(new PerformanceProfileValue().outputFormat(OutputFormat.JSON));
        mongoTemplate.save(job);

        this.mockMvc.perform(get("/psi-api/performanceMonitoring/v2/performanceJob"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("f880e413-4a15-4c51-8b0b-a529f101a1c0"))
                .andExpect(jsonPath("$[0].performanceProfile.outputFormat").value("json"));
    }

    @Test
    public void testCreatePerformanceReportComplexQueryByConsumingAppId() throws Exception {
        PerformanceJob job = new PerformanceJob();
        job.setId("88f76383-8a7a-4e92-a53d-efbf2073ae62");
        job.setConsumingApplicationId("Test-App");
        job.setPerformanceProfile(new PerformanceProfileValue().granularity(Interval._1_YEAR));
        mongoTemplate.save(job);
        job = new PerformanceJob();
        job.setId(UUID.randomUUID().toString());
        mongoTemplate.save(job);

        this.mockMvc.perform(post("/psi-api/performanceMonitoring/v2/performanceJobComplexQuery")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                        + "  \"consumingApplicationId\": \"Test-App\"\n"
                        + "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].granularity").value("1 year"))
                .andExpect(jsonPath("$[0].performanceJob.id").value("88f76383-8a7a-4e92-a53d-efbf2073ae62"));
    }

}
