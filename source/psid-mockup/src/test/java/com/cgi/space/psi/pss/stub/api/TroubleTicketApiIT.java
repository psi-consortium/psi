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

import com.cgi.space.psi.common.model.TroubleTicket;
import com.cgi.space.psi.common.model.TroubleTicketStatusType;

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
public class TroubleTicketApiIT {

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
        mongoTemplate.remove(TroubleTicket.class).all();
    }

    @Test
    public void testCreateTroubleTicket() throws Exception {
        this.mockMvc.perform(post("/psi-api/troubleTicket/v2/troubleTicket")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Foobar\", \"description\": \"Random Description\", \"severity\": \"Low\", \"ticketType\": \"request\", \"@type\": \"TroubleTicket\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testDeleteTroubleTicket() throws Exception {
        TroubleTicket specification = new TroubleTicket();
        specification.setId("a0e0abc8-edbc-4b71-bfe2-eeeff4942b9f");
        mongoTemplate.save(specification);

        this.mockMvc.perform(delete("/psi-api/troubleTicket/v2/troubleTicket/a0e0abc8-edbc-4b71-bfe2-eeeff4942b9f"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListTroubleTicket() throws Exception {
        TroubleTicket specification = new TroubleTicket();
        specification.setId("e2db0643-9611-495b-b4fd-84b1ae2ef967");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("e2db0643-9611-495b-b4fd-84b1ae2ef967"));
    }

    @Test
    public void testListTroubleTicketWithFields() throws Exception {
        TroubleTicket specification = new TroubleTicket();
        specification.setId("7f97a238-d683-4ec7-beb6-25798d874c92");
        specification.setName("Foobar");
        specification.setStatus(TroubleTicketStatusType.INPROGRESS);
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?fields=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].name").value("Foobar"))
                .andExpect(jsonPath("$[0].status").doesNotExist());
    }

    @Test
    public void testListTroubleTicketWithOffsetAndLimit() throws Exception {

        List<TroubleTicket> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            TroubleTicket specification = new TroubleTicket();
            specification.setId(UUID.randomUUID().toString());
            specifications.add(mongoTemplate.save(specification));
        });

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(specifications.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(specifications.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(specifications.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(specifications.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(specifications.get(offset + 4).getId()));
    }

    @Test
    public void testListTroubleTicketWithLimitOnly() throws Exception {

        List<TroubleTicket> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            TroubleTicket specification = new TroubleTicket();
            specification.setId(UUID.randomUUID().toString());
            specifications.add(mongoTemplate.save(specification));
        });

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(specifications.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(specifications.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(specifications.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(specifications.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(specifications.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListTroubleTicketWithNameFilters() throws Exception {

        List<TroubleTicket> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            TroubleTicket specification = new TroubleTicket();
            specification.setId(UUID.randomUUID().toString());
            specification.setName("Trouble Ticket " + num);
            specifications.add(mongoTemplate.save(specification));
        });

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?name=Trouble Ticket 1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Trouble Ticket 1"));

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?name=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?nameContains=Trouble Ticket 5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Trouble Ticket 5"));
    }

    @Test
    public void testListTroubleTicketWithDescriptionFilters() throws Exception {

        List<TroubleTicket> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            TroubleTicket specification = new TroubleTicket();
            specification.setId(UUID.randomUUID().toString());
            specification.setDescription("Trouble Ticket Description " + num);
            specifications.add(mongoTemplate.save(specification));
        });

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?descriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?descriptionContains=description 5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Trouble Ticket Description 5"));
    }

    @Test
    public void testListTroubleTicketWithNameOrDescriptionFilters() throws Exception {

        List<TroubleTicket> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
            TroubleTicket specification = new TroubleTicket();
            specification.setId(UUID.randomUUID().toString());
            specification.setName("Product Specification Name" + num);
            specification.setDescription("Product Specification Description" + num);
            specifications.add(mongoTemplate.save(specification));
        });

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?nameOrDescriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?nameOrDescriptionContains=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket?nameOrDescriptionContains=desc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));
    }

    @Test
    public void testPatchTroubleTicket() throws Exception {
        TroubleTicket specification = new TroubleTicket();
        specification.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        specification.setName("Boobar");
        specification.setDescription("test description");
        mongoTemplate.save(specification);

        this.mockMvc.perform(patch("/psi-api/troubleTicket/v2/troubleTicket/85e0810a-1a59-4002-926c-62de3651dcb9")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Foobar\", \"@type\": \"TroubleTicket\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("description").value("test description"));
    }

    @Test
    public void testPatchTroubleTicketStatus() throws Exception {
        TroubleTicket specification = new TroubleTicket();
        specification.setId("81f4327b-77d3-4229-ace2-44b8e7473930");
        specification.setName("Foobar");
        specification.setDescription("test description");
        specification.setStatus(TroubleTicketStatusType.PENDING);
        mongoTemplate.save(specification);

        this.mockMvc.perform(patch("/psi-api/troubleTicket/v2/troubleTicket/81f4327b-77d3-4229-ace2-44b8e7473930")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\": \"inProgress\", \"@type\": \"TroubleTicket\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("status").value("inProgress"))
                .andExpect(jsonPath("statusChangeHistory").isArray())
                .andExpect(jsonPath("statusChangeHistory").isNotEmpty())
                .andExpect(jsonPath("statusChangeHistory[0].status").value("inProgress"));
    }

    @Test
    public void testRetrieveTroubleTicket() throws Exception {
        TroubleTicket specification = new TroubleTicket();
        specification.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

    @Test
    public void testRetrieveTroubleTicketWithFields() throws Exception {
        TroubleTicket specification = new TroubleTicket();
        specification.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        specification.setName("Foobar");
        specification.setStatus(TroubleTicketStatusType.CLOSED);
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/troubleTicket/v2/troubleTicket/c37baf9c-8770-4594-8424-cd5767358ea8?fields=status,name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("status").exists())
                .andExpect(jsonPath("status").value("closed"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

}
