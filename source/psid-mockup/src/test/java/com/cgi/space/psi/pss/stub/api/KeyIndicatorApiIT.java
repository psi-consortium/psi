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

import com.cgi.space.psi.common.model.KeyIndicator;

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
public class KeyIndicatorApiIT {

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
        mongoTemplate.remove(KeyIndicator.class).all();
    }

    @Test
    public void testCreateKeyIndicator() throws Exception {
        this.mockMvc.perform(post("/psi-api/serviceQualityManagement/v2/keyIndicator")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content("{\n" +
                        "    \"name\": \"Foobar\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testDeleteKeyIndicator() throws Exception {
        KeyIndicator keyIndicator = new KeyIndicator();
        keyIndicator.setId(UUID.randomUUID().toString());
        keyIndicator.setName("Foobar");
        mongoTemplate.save(keyIndicator);

        this.mockMvc.perform(delete("/psi-api/serviceQualityManagement/v2/keyIndicator/" + keyIndicator.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeleteKeyIndicatorWithUnkonwnId() throws Exception {
        this.mockMvc.perform(delete("/psi-api/serviceQualityManagement/v2/keyIndicator/" + UUID.randomUUID().toString()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testListKeyIndicator() throws Exception {
        KeyIndicator keyIndicator = new KeyIndicator();
        keyIndicator.setId(UUID.randomUUID().toString());
        mongoTemplate.save(keyIndicator);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/keyIndicator"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(keyIndicator.getId()));
    }

    @Test
    public void testListKeyIndicatorWithOffsetAndLimit() throws Exception {
        List<KeyIndicator> keyIndicators = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            KeyIndicator keyIndicator = new KeyIndicator();
            keyIndicator.setId(UUID.randomUUID().toString());
            keyIndicator.setName("SLO Name " + num);
            keyIndicators.add(mongoTemplate.save(keyIndicator));
        });

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/keyIndicator?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(keyIndicators.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(keyIndicators.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(keyIndicators.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(keyIndicators.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(keyIndicators.get(offset + 4).getId()));
    }

    @Test
    public void testListKeyIndicatorWithLimitOnly() throws Exception {
        List<KeyIndicator> keyIndicators = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            KeyIndicator keyIndicator = new KeyIndicator();
            keyIndicator.setId(UUID.randomUUID().toString());
            keyIndicator.setName("SLO Name " + num);
            keyIndicators.add(mongoTemplate.save(keyIndicator));
        });

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/keyIndicator?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(keyIndicators.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(keyIndicators.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(keyIndicators.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(keyIndicators.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(keyIndicators.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testPatchKeyIndicator() throws Exception {
        KeyIndicator keyIndicator = new KeyIndicator();
        keyIndicator.setId(UUID.randomUUID().toString());
        keyIndicator.setName("Boobar");
        mongoTemplate.save(keyIndicator);

        this.mockMvc.perform(patch("/psi-api/serviceQualityManagement/v2/keyIndicator/" + keyIndicator.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Foobar\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testPatchKeyIndicatorWithUnkonwnId() throws Exception {
        this.mockMvc.perform(patch("/psi-api/serviceQualityManagement/v2/keyIndicator/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Foobar\"}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRetrieveKeyIndicator() throws Exception {
        KeyIndicator keyIndicator = new KeyIndicator();
        keyIndicator.setId(UUID.randomUUID().toString());
        keyIndicator.setName("Foobar");
        mongoTemplate.save(keyIndicator);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/keyIndicator/" + keyIndicator.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testRetrieveKeyIndicatorWithFields() throws Exception {
        KeyIndicator keyIndicator = new KeyIndicator();
        keyIndicator.setId(UUID.randomUUID().toString());
        keyIndicator.setName("Foobar");
        mongoTemplate.save(keyIndicator);

        this.mockMvc.perform(get("/psi-api/serviceQualityManagement/v2/keyIndicator/" + keyIndicator.getId() + "?fields=name,conformanceTarget"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value(keyIndicator.getName()));
    }
}
