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

import com.cgi.space.psi.common.model.Organization;

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
public class OrganizationApiIT {

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
        mongoTemplate.remove(Organization.class).all();
    }

    @Test
    public void testCreateOrganization() throws Exception {
        this.mockMvc.perform(post("/psi-api/partyManagement/v2/organization")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"Organization\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testDeleteOrganization() throws Exception {
        Organization organization = new Organization();
        organization.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(organization);

        this.mockMvc.perform(delete("/psi-api/partyManagement/v2/organization/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListOrganization() throws Exception {
        Organization organization = new Organization();
        organization.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(organization);

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListOrganizationWithFields() throws Exception {
        Organization organization = new Organization();
        organization.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        organization.setTradingName("Foobar");
        mongoTemplate.save(organization);

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization?fields=tradingName"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].tradingName").exists())
                .andExpect(jsonPath("$[0].tradingName").value("Foobar"));
    }

    @Test
    public void testListOrganizationWithOffsetAndLimit() throws Exception {

        List<Organization> organizations = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Organization organization = new Organization();
                    organization.setId(UUID.randomUUID().toString());
                    organizations.add(mongoTemplate.save(organization));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(organizations.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(organizations.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(organizations.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(organizations.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(organizations.get(offset + 4).getId()));
    }

    @Test
    public void testListOrganizationWithLimitOnly() throws Exception {

        List<Organization> organizations = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Organization organization = new Organization();
                    organization.setId(UUID.randomUUID().toString());
                    organizations.add(mongoTemplate.save(organization));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(organizations.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(organizations.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(organizations.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(organizations.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(organizations.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListOrganizationWithNameFilters() throws Exception {

        List<Organization> organizations = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Organization organization = new Organization();
                    organization.setId(UUID.randomUUID().toString());
                    organization.setName("Organization Name" + num);
                    organizations.add(mongoTemplate.save(organization));
                }
        );

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization?name=Organization Name1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Organization Name1"));

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization?nameContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization?nameContains=org"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(10));
    }

    @Test
    public void testListOrganizationWithTradingNameFilters() throws Exception {

        List<Organization> organizations = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Organization organization = new Organization();
                    organization.setId(UUID.randomUUID().toString());
                    organization.setTradingName("Trading Name" + num);
                    organizations.add(mongoTemplate.save(organization));
                }
        );

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization?tradingName=Trading name1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].tradingName").value("Trading Name1"));

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization?tradingNameContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization?tradingNameContains=radi"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(10));
    }

    @Test
    public void testPatchOrganization() throws Exception {
        Organization organization = new Organization();
        organization.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        organization.setTradingName("Boobar");
        organization.setName("test name");
        mongoTemplate.save(organization);

        this.mockMvc.perform(patch("/psi-api/partyManagement/v2/organization/85e0810a-1a59-4002-926c-62de3651dcb9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tradingName\": \"Foobar\", \"@type\": \"Organization\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("tradingName").value("Foobar"))
                .andExpect(jsonPath("name").value("test name"));
    }

    @Test
    public void testRetrieveOrganization() throws Exception {
        Organization organization = new Organization();
        organization.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(organization);

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

    @Test
    public void testRetrieveOrganizationWithFields() throws Exception {
        Organization organization = new Organization();
        organization.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        organization.setTradingName("Foobar");
        organization.setIsHeadOffice(true);
        mongoTemplate.save(organization);

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/organization/c37baf9c-8770-4594-8424-cd5767358ea8?fields=isHeadOffice,tradingName"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("isHeadOffice").exists())
                .andExpect(jsonPath("isHeadOffice").value(true))
                .andExpect(jsonPath("tradingName").exists())
                .andExpect(jsonPath("tradingName").value("Foobar"));
    }

}
