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

import com.cgi.space.psi.common.model.CheckProductStock;
import com.cgi.space.psi.common.model.TaskStateType;
import com.cgi.space.psi.pss.stub.service.CheckProductStockService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CheckProductStockApiIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CheckProductStockService checkProductStockService;
    private MockMvc mockMvc;

    @MockBean
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(CheckProductStockService.class).all();
    }

    @Test
    public void testCreateCheckProductStock_InstantSyncCheck() throws Exception {
        mockMvc.perform(post("/psi-api/stock/v2/checkProductStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCheckProductStockAsBody(true, false)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("href").exists())
                .andExpect(jsonPath("creationDate").exists())
                .andExpect(jsonPath("state").value(TaskStateType.DONE.toString()))
                .andExpect(jsonPath("completedCheckProductStockDate").exists())
                .andExpect(jsonPath("checkProductStockItem[0].state").value(TaskStateType.DONE.toString()))
                .andExpect(jsonPath("checkProductStockItem[0].availabilityResult").exists());
   }

    @Test
    public void testCreateCheckProductStock_Async() throws Exception {
        mockMvc.perform(post("/psi-api/stock/v2/checkProductStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCheckProductStockAsBody(false, false)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("href").exists())
                .andExpect(jsonPath("creationDate").exists())
                .andExpect(jsonPath("state").value(TaskStateType.ACCEPTED.toString()))
                .andExpect(jsonPath("completedCheckProductStockDate").doesNotExist())
                .andExpect(jsonPath("checkProductStockItem[0].state").value(TaskStateType.ACCEPTED.toString()))
                .andExpect(jsonPath("checkProductStockItem[0].availabilityResult").doesNotExist());
    }

    @Test
    public void testCreateCheckProductStock_AsyncAndAlternative() throws Exception {
        mockMvc.perform(post("/psi-api/stock/v2/checkProductStock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getCheckProductStockAsBody(false, true)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("href").exists())
                .andExpect(jsonPath("creationDate").exists())
                .andExpect(jsonPath("state").value(TaskStateType.ACCEPTED.toString()))
                .andExpect(jsonPath("completedCheckProductStockDate").doesNotExist())
                .andExpect(jsonPath("checkProductStockItem[0].state").value(TaskStateType.ACCEPTED.toString()))
                .andExpect(jsonPath("checkProductStockItem[0].availabilityResult").doesNotExist());
    }


    @Test
    public void testRetrieveCheckProductStock() throws Exception {
        CheckProductStock checkProductStock = new CheckProductStock()
                .id(UUID.randomUUID().toString())
                .state(TaskStateType.DONE)
                .instantSyncCheck(true)
                .provideAlternative(false)
                .creationDate(OffsetDateTime.now());
        mongoTemplate.save(checkProductStock);

        this.mockMvc.perform(get("/psi-api/stock/v2/checkProductStock/" + checkProductStock.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(checkProductStock.getId()))
                .andExpect(jsonPath("state").value(TaskStateType.DONE.toString()));
    }

    @Test
    public void testRetrieveCheckProductStock_UnknownId() throws Exception {
        this.mockMvc.perform(get("/psi-api/stock/v2/checkProductStock/" + UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    // Helper methods

    private String getCheckProductStockAsBody(boolean instantSyncCheck, boolean provideAlternative) {
        return "{"
                + "\"instantSyncCheck\": \"" + instantSyncCheck + "\","
                + "\"provideAlternative\": \"" + provideAlternative + "\","
                + "\"checkProductStockItem\": ["
                + "  {"
                + "    \"id\": \"1234\","
                + "    \"provideAlternative\": \"" + provideAlternative + "\","
                + "    \"requestedQuantity\": { \"amount\": 10, \"units\": \"PCE\" },"
                + "    \"checkedProductStock\": {"
                + "      \"stockedProduct\": {"
                + "        \"productSpecification\": {\"id\": \"23\", \"@type\": \"ProductSpecificationRef\"},"
                + "        \"@type\": \"Product\""
                + "      }"
                + "    }"
                + "  }"
                + "]"
                + "}";
    }
}
