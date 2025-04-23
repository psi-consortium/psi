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

import com.cgi.space.psi.common.model.OutputFormat;
import com.cgi.space.psi.common.model.PerformanceJob;
import com.cgi.space.psi.common.model.PerformanceJobRef;
import com.cgi.space.psi.common.model.PerformanceJobValue;
import com.cgi.space.psi.common.model.PerformanceProfileValue;
import com.cgi.space.psi.common.model.PerformanceReport;
import com.cgi.space.psi.pss.stub.config.Profiles;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles(Profiles.PROVIDER)
public class PerformanceReportApiIT {

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
    public void testCreatePerformanceReport() throws Exception {
        this.mockMvc.perform(post("/psi-api/performanceMonitoring/v2/performanceReport")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                        + "  \"description\": \"on-demand report\",\n"
                        + "  \"reportingTimeframe\": {\n"
                        + "    \"reportingStartDate\": \"2024-08-19T13:59:13.850Z\",\n"
                        + "    \"reportingEndDate\": \"2024-08-20T13:59:13.850Z\"\n"
                        + "  },\n"
                        + "  \"performanceJob\": {\n"
                        + "    \"@type\": \"PerformanceJobValue\",\n"
                        + "    \"granularity\": \"1 hour\",\n"
                        + "    \"outputFormat\": \"json\",\n"
                        + "    \"resultFormat\": \"payload\",\n"
                        + "    \"servicePayloadSpecificAttributes\": {\n"
                        + "      \"@type\": \"urn:mef:lso:spec:legato:ip-performance-monitoring-configuration:v0.0.1:all\",\n"
                        + "      \"interface\": {\n"
                        + "        \"serviceEndpoint\": \"etc\"\n"
                        + "      },\n"
                        + "      \"vlan\": 100,\n"
                        + "      \"packetsIn\": true,\n"
                        + "      \"charsIn\": true,\n"
                        + "      \"packetsOut\": true,\n"
                        + "      \"charsOut\": true\n"
                        + "    }\n"
                        + "  }\n"
                        + "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("description").value("on-demand report"))
                .andExpect(jsonPath("state").value("completed"))
                .andExpect(jsonPath("reportContent").isArray())
                .andExpect(jsonPath("reportContent[0].measurementDataPoints").isArray())
                .andExpect(jsonPath("reportContent[0].measurementDataPoints[0].packetsIn").exists());
    }

    @Test
    public void testListPerformanceReport() throws Exception {
        PerformanceReport report = new PerformanceReport();
        report.setId("c7609687-0d44-497a-98fa-f3e4f9caf940");
        report.setPerformanceJob(new PerformanceJobValue().outputFormat(OutputFormat.JSON));
        mongoTemplate.save(report);

        this.mockMvc.perform(get("/psi-api/performanceMonitoring/v2/performanceReport"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("c7609687-0d44-497a-98fa-f3e4f9caf940"))
                .andExpect(jsonPath("$[0].performanceJob.outputFormat").value("json"));
    }

    @Test
    public void testCreatePerformanceReportComplexQueryByConsumingAppId() throws Exception {
        PerformanceReport report = new PerformanceReport();
        report.setId("88f76383-8a7a-4e92-a53d-efbf2073ae62");
        report.setPerformanceJob(new PerformanceJobValue().outputFormat(OutputFormat.JSON).consumingApplicationId("Test-App"));
        mongoTemplate.save(report);
        report = new PerformanceReport();
        report.setId(UUID.randomUUID().toString());
        report.setPerformanceJob(new PerformanceJobRef().id(UUID.randomUUID().toString()));
        mongoTemplate.save(report);

        this.mockMvc.perform(post("/psi-api/performanceMonitoring/v2/performanceReportComplexQuery")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                        + "  \"consumingApplicationId\": \"Test-App\"\n"
                        + "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].performanceReport.id").value("88f76383-8a7a-4e92-a53d-efbf2073ae62"));
    }

    @Test
    public void testCreatePerformanceReportComplexQueryByJobId() throws Exception {
        PerformanceJob job = new PerformanceJob();
        job.setId("04ef2814-575c-4faa-bf53-bdcdac0ebea9");
        job.setPerformanceProfile(new PerformanceProfileValue());
        mongoTemplate.save(job);

        PerformanceReport report = new PerformanceReport();
        report.setId(UUID.randomUUID().toString());
        report.setPerformanceJob(new PerformanceJobValue().outputFormat(OutputFormat.JSON));
        mongoTemplate.save(report);
        report = new PerformanceReport();
        report.setId(UUID.randomUUID().toString());
        report.setPerformanceJob(new PerformanceJobRef().id("04ef2814-575c-4faa-bf53-bdcdac0ebea9"));
        mongoTemplate.save(report);
        report = new PerformanceReport();
        report.setId(UUID.randomUUID().toString());
        report.setPerformanceJob(new PerformanceJobRef().id(UUID.randomUUID().toString()));
        mongoTemplate.save(report);

        this.mockMvc.perform(post("/psi-api/performanceMonitoring/v2/performanceReportComplexQuery")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n"
                        + "  \"performanceJob\": {\n"
                        + "    \"@type\": \"PerformanceJobRef\",\n"
                        + "    \"id\": \"04ef2814-575c-4faa-bf53-bdcdac0ebea9\"\n"
                        + "  }\n"
                        + "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].performanceJob.id").value("04ef2814-575c-4faa-bf53-bdcdac0ebea9"));
    }

    @Test
    public void testRetrievePerformanceReport() throws Exception {
        PerformanceReport report = new PerformanceReport();
        report.setId("cd10c8ed-314b-4097-b041-079809fe842f");
        report.setPerformanceJob(new PerformanceJobValue().outputFormat(OutputFormat.JSON));
        mongoTemplate.save(report);

        this.mockMvc.perform(get("/psi-api/performanceMonitoring/v2/performanceReport/cd10c8ed-314b-4097-b041-079809fe842f"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("cd10c8ed-314b-4097-b041-079809fe842f"))
                .andExpect(jsonPath("performanceJob.outputFormat").value("json"));
    }

}
