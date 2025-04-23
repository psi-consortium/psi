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

import com.cgi.space.psi.common.model.ServiceLevelObjective;

import java.nio.charset.Charset;
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
public class ServiceLevelObjectiveApiIT {

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
        mongoTemplate.remove(ServiceLevelObjective.class).all();
    }

    @Test
    public void testCreateServiceLevelObjective() throws Exception {
        this.mockMvc.perform(post("/psi-api/serviceQualityManagement/v2/serviceLevelObjective")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content("{\n" +
                        "    \"graceTimes\": \"3\",\n" +
                        "    \"conformanceTarget\": \"32\",\n" +
                        "    \"name\": \"Foobar\",\n" +
                        "    \"keyIndicator\": {}\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("conformanceTarget").value("32"));
    }

    @Test
    public void testCreateServiceLevelObjectiveWithKeyIndicator() throws Exception {
        this.mockMvc.perform(post("/psi-api/serviceQualityManagement/v2/serviceLevelObjective")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content("{\n" +
                        "    \"name\": \"Upload bandwidth\",\n" +
                        "    \"graceTimes\": \"3\",\n" +
                        "    \"conformanceTarget\": \"32\",\n" +
                        "    \"keyIndicator\": {\n" +
                        "        \"name\": \"speed\", \n" +
                        "        \"indicatorType\": \"KPI\", \n" +
                        "        \"relatedEntity\": [ \n" +
                        "            {\n" +
                        "                \"entity\": { \n" +
                        "                    \"id\": \"3aa3c9a6-f4f5-4d92-a1ae-4ba5f887b8a9\", \n" +
                        "                    \"href\": \"http://localhost:8001/psi-api/productCatalog/v2/productSpecification/3aa3c9a6-f4f5-4d92-a1ae-4ba5f887b8a9\", \n" +
                        "                    \"name\": \"Internet\", \n" +
                        "                    \"@referredType\": \"ProductSpecification\", \n" +
                        "                    \"@type\": \"EntityRef\" \n" +
                        "                }, \n" +
                        "                \"role\": \"service\", \n" +
                        "                \"@type\": \"RelatedEntity\" \n" +
                        "            }\n" +
                        "        ]\n" +
                        "    },\n" +
                        "    \"consequence\": [\n" +
                        "                {\n" +
                        "                    \"name\": \"TroubleTicketCreation\",\n" +
                        "                    \"description\": \"Create a trouble Ticket\",\n" +
                        "                    \"prescribedAction\": \"Create a trouble Ticket\"\n" +
                        "                }\n" +
                        "    ]\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Upload bandwidth"))
                .andExpect(jsonPath("keyIndicator").exists())
                .andExpect(jsonPath("keyIndicator.name").value("speed"))
                .andExpect(jsonPath("keyIndicator.relatedEntity").exists())
                .andExpect(jsonPath("keyIndicator.relatedEntity").isArray())
                .andExpect(jsonPath("keyIndicator.relatedEntity[0].entity.name").value("Internet"))
                .andExpect(jsonPath("consequence").exists())
                .andExpect(jsonPath("consequence").isArray())
                .andExpect(jsonPath("consequence.length()").value(1))
                .andExpect(jsonPath("consequence.[0].prescribedAction").exists());
    }

    @Test
    public void testDeleteServiceLevelObjective() throws Exception {
        ServiceLevelObjective slo = new ServiceLevelObjective();
        slo.setId(UUID.randomUUID().toString());
        slo.setName("Foobar");
        mongoTemplate.save(slo);

        this.mockMvc.perform(delete("/psi-api/serviceQualityManagement/v2/serviceLevelObjective/" + slo.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteServiceLevelObjectiveWithUnkonwnId() throws Exception {
        this.mockMvc.perform(delete("/psi-api/serviceQualityManagement/v2/serviceLevelObjective/" + UUID.randomUUID().toString()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testListServiceLevelObjective() throws Exception {
        ServiceLevelObjective slo = new ServiceLevelObjective();
        slo.setId(UUID.randomUUID().toString());
        mongoTemplate.save(slo);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(slo.getId()));
    }

    @Test
    public void testListServiceLevelObjectiveWithFields() throws Exception {
        ServiceLevelObjective slo = new ServiceLevelObjective();
        slo.setId(UUID.randomUUID().toString());
        slo.setName("Foobar");
        slo.setGraceTimes("3");
        slo.setConformanceTarget("32");
        mongoTemplate.save(slo);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective?fields=name,conformanceTarget"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].name").value(slo.getName()))
                .andExpect(jsonPath("$[0].conformanceGraceTimes").doesNotExist())
                .andExpect(jsonPath("$[0].conformanceTarget").exists())
                .andExpect(jsonPath("$[0].conformanceTarget").value(slo.getConformanceTarget()));
    }

    @Test
    public void testListServiceLevelObjectiveWithOffsetAndLimit() throws Exception {
        List<ServiceLevelObjective> slos = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            ServiceLevelObjective slo = new ServiceLevelObjective();
            slo.setId(UUID.randomUUID().toString());
            slo.setName("SLO Name " + num);
            slos.add(mongoTemplate.save(slo));
        });

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(slos.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(slos.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(slos.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(slos.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(slos.get(offset + 4).getId()));
    }

    @Test
    public void testListServiceLevelObjectiveWithLimitOnly() throws Exception {
        List<ServiceLevelObjective> slos = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            ServiceLevelObjective slo = new ServiceLevelObjective();
            slo.setId(UUID.randomUUID().toString());
            slo.setName("SLO Name " + num);
            slos.add(mongoTemplate.save(slo));
        });

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(slos.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(slos.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(slos.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(slos.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(slos.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListServiceLevelObjectiveWithNameFilters() throws Exception {
        List<ServiceLevelObjective> slos = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            ServiceLevelObjective slo = new ServiceLevelObjective();
            slo.setId(UUID.randomUUID().toString());
            slo.setName("SLO Name " + num);
            slos.add(mongoTemplate.save(slo));
        });

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective?name=SLO Name 1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective?nameContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective?nameContains=SLO Name 3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective?nameContains=SLO Name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(slos.size()));

    }

    @Test
    public void testPatchServiceLevelObjective() throws Exception {
        ServiceLevelObjective slo = new ServiceLevelObjective();
        slo.setId(UUID.randomUUID().toString());
        slo.setName("Boobar");
        slo.conformanceTarget("32");
        mongoTemplate.save(slo);

        this.mockMvc.perform(patch("/psi-api/serviceQualityManagement/v2/serviceLevelObjective/" + slo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Foobar\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("conformanceTarget").value("32"));
    }

    @Test
    public void testPatchServiceLevelObjectiveWithUnkonwnId() throws Exception {
        this.mockMvc.perform(patch("/psi-api/serviceQualityManagement/v2/serviceLevelObjective/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Foobar\"}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRetrieveServiceLevelObjective() throws Exception {
        ServiceLevelObjective slo = new ServiceLevelObjective();
        slo.setId(UUID.randomUUID().toString());
        slo.setName("Foobar");
        mongoTemplate.save(slo);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective/" + slo.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testRetrieveServiceLevelObjectiveWithFields() throws Exception {
        ServiceLevelObjective slo = new ServiceLevelObjective();
        slo.setId(UUID.randomUUID().toString());
        slo.setName("Foobar");
        slo.setGraceTimes("3");
        slo.setConformanceTarget("32");
        mongoTemplate.save(slo);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/serviceLevelObjective/" + slo.getId() + "?fields=name,conformanceTarget"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value(slo.getName()))
                .andExpect(jsonPath("graceTimes").doesNotExist())
                .andExpect(jsonPath("conformanceTarget").exists())
                .andExpect(jsonPath("conformanceTarget").value(slo.getConformanceTarget()));
    }
}
