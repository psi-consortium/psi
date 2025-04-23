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

import com.cgi.space.psi.common.model.ServiceLevelSpecification;
import com.cgi.space.psi.common.model.TimePeriod;

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

import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ServiceLevelSpecificationApiIT {

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
        mongoTemplate.remove(ServiceLevelSpecification.class).all();
    }

    @Test
    public void testCreateServiceLevelSpecification() throws Exception {
        this.mockMvc.perform(post("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(Charset.defaultCharset())
                        .content("{\n" +
                                "    \"description\": \"Maximum download/upload speed service level\",\n" +
                                "    \"name\": \"Access bandwidth\",\n" +
                                "    \"validFor\": {\n" +
                                "        \"endDateTime\": \"2021-05-01T00:00:00+01:00\",\n" +
                                "        \"startDateTime\": \"2020-03-01T00:00:00+01:00\"\n" +
                                "    },\n" +
                                "    \"relatedServiceLevelObjective\": [\n" +
                                "        {\n" +
                                "            \"id\": \"99998fb0-7e40-4e3d-bdea-03ba4b149e7e\"\n" +
                                "        }\n" +
                                "    ]\n" +
                                "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Access bandwidth"))
                .andExpect(jsonPath("relatedServiceLevelObjective").exists())
                .andExpect(jsonPath("relatedServiceLevelObjective").isArray())
                .andExpect(jsonPath("relatedServiceLevelObjective.length()").value(1));
    }

    @Test
    public void testDeleteServiceLevelSpecification() throws Exception {
        ServiceLevelSpecification sls = new ServiceLevelSpecification();
        sls.setId(UUID.randomUUID().toString());
        sls.setName("Foobar");
        mongoTemplate.save(sls);

        this.mockMvc.perform(delete("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification/" + sls.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteServiceLevelSpecificationWithUnkonwnId() throws Exception {
        this.mockMvc.perform(delete("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification/" + UUID.randomUUID().toString()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testListServiceLevelSpecification() throws Exception {
        ServiceLevelSpecification sls = new ServiceLevelSpecification();
        sls.setId(UUID.randomUUID().toString());
        mongoTemplate.save(sls);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(sls.getId()));
    }

    @Test
    public void testListServiceLevelSpecificationWithFields() throws Exception {
        ServiceLevelSpecification sls = new ServiceLevelSpecification();
        sls.setId(UUID.randomUUID().toString());
        sls.setName("Foobar");
        sls.setDescription("Foobar Description");
        TimePeriod validFor = new TimePeriod();
        validFor.setStartDateTime(OffsetDateTime.parse("2023-07-06T00:00:00Z"));
        validFor.setEndDateTime(OffsetDateTime.parse("2023-07-07T00:00:00Z"));
        sls.setValidFor(validFor);
        mongoTemplate.save(sls);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification?fields=name,validFor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].name").value(sls.getName()))
                .andExpect(jsonPath("$[0].description").doesNotExist())
                .andExpect(jsonPath("$[0].validFor").exists());
    }

    @Test
    public void testListServiceLevelSpecificationWithOffsetAndLimit() throws Exception {
        List<ServiceLevelSpecification> slss = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            ServiceLevelSpecification sls = new ServiceLevelSpecification();
            sls.setId(UUID.randomUUID().toString());
            sls.setName("SLS Name " + num);
            slss.add(mongoTemplate.save(sls));
        });

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(slss.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(slss.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(slss.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(slss.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(slss.get(offset + 4).getId()));
    }

    @Test
    public void testListServiceLevelSpecificationWithLimitOnly() throws Exception {
        List<ServiceLevelSpecification> slss = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            ServiceLevelSpecification sls = new ServiceLevelSpecification();
            sls.setId(UUID.randomUUID().toString());
            sls.setName("SLS Name " + num);
            slss.add(mongoTemplate.save(sls));
        });

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(slss.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(slss.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(slss.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(slss.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(slss.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListServiceLevelSpecificationWithNameFilters() throws Exception {
        List<ServiceLevelSpecification> slss = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            ServiceLevelSpecification sls = new ServiceLevelSpecification();
            sls.setId(UUID.randomUUID().toString());
            sls.setName("SLS Name " + num);
            slss.add(mongoTemplate.save(sls));
        });

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification?name=SLS Name 1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification?nameContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification?nameContains=SLS Name 3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification?nameContains=SLS Name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(slss.size()));

    }

    @Test
    public void testPatchServiceLevelSpecification() throws Exception {
        ServiceLevelSpecification sls = new ServiceLevelSpecification();
        sls.setId(UUID.randomUUID().toString());
        sls.setName("Boobar");
        mongoTemplate.save(sls);

        this.mockMvc.perform(patch("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification/" + sls.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testPatchServiceLevelSpecificationWithUnkonwnId() throws Exception {
        this.mockMvc.perform(patch("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\"}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRetrieveServiceLevelSpecification() throws Exception {
        ServiceLevelSpecification sls = new ServiceLevelSpecification();
        sls.setId(UUID.randomUUID().toString());
        sls.setName("Foobar");
        mongoTemplate.save(sls);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification/" + sls.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testRetrieveServiceLevelSpecificationWithFields() throws Exception {
        ServiceLevelSpecification sls = new ServiceLevelSpecification();
        sls.setId(UUID.randomUUID().toString());
        sls.setName("Foobar");
        sls.setDescription("Foobar Description");
        TimePeriod validFor = new TimePeriod();
        validFor.setStartDateTime(OffsetDateTime.parse("2023-07-06T00:00:00Z"));
        validFor.setEndDateTime(OffsetDateTime.parse("2023-07-07T00:00:00Z"));
        sls.setValidFor(validFor);
        mongoTemplate.save(sls);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelSpecification/" + sls.getId() + "?fields=name,validFor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value(sls.getName()))
                .andExpect(jsonPath("description").doesNotExist())
                .andExpect(jsonPath("validFor").exists());
    }
}
