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

import com.cgi.space.psi.common.model.PartyRef;
import com.cgi.space.psi.common.model.RelatedPartyRefOrPartyRoleRef;
import com.cgi.space.psi.common.model.Service;
import com.cgi.space.psi.common.model.ServiceStateType;

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
public class ServiceInventoryApiIT {

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
        mongoTemplate.remove(Service.class).all();
    }

    @Test
    public void testCreateService() throws Exception {
        this.mockMvc.perform(post("/psi-api/serviceInventory/v2/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"state\": \"active\", \"serviceSpecification\": { \"id\": \"spec-id\", \"href\": \"href-url\", \"@type\": \"ServiceSpecificationRef\"}, \"@type\": \"Service\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("state").value("active"))
                .andExpect(jsonPath("serviceSpecification.id").value("spec-id"))
                .andExpect(jsonPath("serviceSpecification.href").value("href-url"));
    }

    @Test
    public void testDeleteService() throws Exception {
        Service service = new Service();
        service.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(service);

        this.mockMvc.perform(delete("/psi-api/serviceInventory/v2/service/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListService() throws Exception {
        Service service = new Service();
        service.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(service);

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListServiceWithFields() throws Exception {
        Service service = new Service();
        service.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        service.setName("Foobar");
        service.setDescription("Desc");
        mongoTemplate.save(service);

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?fields=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].name").value("Foobar"))
                .andExpect(jsonPath("$[0].description").doesNotExist());
    }

    @Test
    public void testListServiceWithOffsetAndLimit() throws Exception {

        List<Service> services = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Service service = new Service();
                    service.setId(UUID.randomUUID().toString());
                    services.add(mongoTemplate.save(service));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(services.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(services.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(services.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(services.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(services.get(offset + 4).getId()));
    }

    @Test
    public void testListServiceWithLimitOnly() throws Exception {

        List<Service> services = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Service service = new Service();
                    service.setId(UUID.randomUUID().toString());
                    services.add(mongoTemplate.save(service));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(services.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(services.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(services.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(services.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(services.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListServiceWithNameFilters() throws Exception {

        List<Service> services = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Service service = new Service();
                    service.setId(UUID.randomUUID().toString());
                    service.setName("Service" + num);
                    services.add(mongoTemplate.save(service));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?name=Service1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Service1"));

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?name=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?nameContains=service5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Service5"));
    }

    @Test
    public void testListServiceWithDescriptionFilters() throws Exception {

        List<Service> services = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Service service = new Service();
                    service.setId(UUID.randomUUID().toString());
                    service.setDescription("Service Description" + num);
                    services.add(mongoTemplate.save(service));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?descriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?descriptionContains=description5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Service Description5"));
    }

    @Test
    public void testListServiceWithNameOrDescriptionFilters() throws Exception {

        List<Service> services = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Service service = new Service();
                    service.setId(UUID.randomUUID().toString());
                    service.setName("Service Name" + num);
                    service.setDescription("Service Description" + num);
                    services.add(mongoTemplate.save(service));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?nameOrDescriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?nameOrDescriptionContains=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(services.size()));

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?nameOrDescriptionContains=desc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(services.size()));
    }

    @Test
    public void testListServiceWithStateFilters() throws Exception {

        List<Service> services = new ArrayList<>();
        Service activeState = new Service();
        activeState.setId(UUID.randomUUID().toString());
        activeState.setState(ServiceStateType.ACTIVE);
        services.add(mongoTemplate.save(activeState));

        Service inactiveState = new Service();
        inactiveState.setId(UUID.randomUUID().toString());
        inactiveState.setState(ServiceStateType.INACTIVE);
        services.add(mongoTemplate.save(inactiveState));

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?state=active"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].state").value("active"));

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?state=active&state=inactive"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].state").value("active"))
                .andExpect(jsonPath("$.[1].state").value("inactive"));
    }

    @Test
    public void testListServiceWithRelatedPartyFilters() throws Exception {

        List<Service> services = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    PartyRef party = new PartyRef();
                    party.setId(UUID.randomUUID().toString());
                    party.setName("Party" + num);
                    Service service = new Service();
                    service.setId(UUID.randomUUID().toString());
                    service.addRelatedPartyItem(new RelatedPartyRefOrPartyRoleRef().partyOrPartyRole(party));
                    services.add(mongoTemplate.save(service));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?relatedPartyContains=party1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].relatedParty[0].partyOrPartyRole.name").value("Party1"));

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?relatedPartyContains=party"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(services.size()));
    }

    @Test
    public void testListServiceWithCategoryFilters() throws Exception {

        Service service = new Service();
        service.setId(UUID.randomUUID().toString());
        service.setCategory("CustomCategory");
        mongoTemplate.save(service);

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?category=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service?category=CustomCategory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].category").value("CustomCategory"));
    }

    @Test
    public void testPatchService() throws Exception {
        Service service = new Service();
        service.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        service.setName("Boobar");
        service.setDescription("mock description");
        mongoTemplate.save(service);

        this.mockMvc.perform(patch("/psi-api/serviceInventory/v2/service/85e0810a-1a59-4002-926c-62de3651dcb9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"Service\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("description").value("mock description"));
    }

    @Test
    public void testRetrieveService() throws Exception {
        Service service = new Service();
        service.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(service);

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

    @Test
    public void testRetrieveServiceWithFields() throws Exception {
        Service service = new Service();
        service.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        service.setName("Foobar");
        service.setDescription("Desc");
        mongoTemplate.save(service);

        this.mockMvc.perform(get("/psi-api/serviceInventory/v2/service/c37baf9c-8770-4594-8424-cd5767358ea8?fields=description,name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("description").exists())
                .andExpect(jsonPath("description").value("Desc"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }
}
