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
import com.cgi.space.psi.common.model.Resource;
import com.cgi.space.psi.common.model.ResourceStatusType;

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
public class ResourceInventoryApiIT {

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
        mongoTemplate.remove(Resource.class).all();
    }

    @Test
    public void testCreateResource() throws Exception {
        this.mockMvc.perform(post("/psi-api/resourceInventory/v2/resource")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"Resource\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testDeleteResource() throws Exception {
        Resource resource = new Resource();
        resource.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(resource);

        this.mockMvc.perform(delete("/psi-api/resourceInventory/v2/resource/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListResource() throws Exception {
        Resource resource = new Resource();
        resource.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(resource);

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListResourceWithFields() throws Exception {
        Resource resource = new Resource();
        resource.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        resource.setName("Foobar");
        resource.setCategory("test");
        mongoTemplate.save(resource);

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?fields=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].name").value("Foobar"))
                .andExpect(jsonPath("$[0].category").doesNotExist());
    }

    @Test
    public void testListResourceWithOffsetAndLimit() throws Exception {

        List<Resource> resources = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Resource resource = new Resource();
                    resource.setId(UUID.randomUUID().toString());
                    resources.add(mongoTemplate.save(resource));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(resources.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(resources.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(resources.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(resources.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(resources.get(offset + 4).getId()));
    }

    @Test
    public void testListResourceWithLimitOnly() throws Exception {

        List<Resource> resources = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Resource resource = new Resource();
                    resource.setId(UUID.randomUUID().toString());
                    resources.add(mongoTemplate.save(resource));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(resources.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(resources.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(resources.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(resources.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(resources.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListResourceWithNameFilters() throws Exception {

        List<Resource> resources = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Resource resource = new Resource();
                    resource.setId(UUID.randomUUID().toString());
                    resource.setName("Resource " + num);
                    resources.add(mongoTemplate.save(resource));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?name=Resource 1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Resource 1"));

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?name=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?nameContains=resource 5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Resource 5"));
    }

    @Test
    public void testListResourceWithDescriptionFilters() throws Exception {

        List<Resource> resources = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Resource resource = new Resource();
                    resource.setId(UUID.randomUUID().toString());
                    resource.setDescription("Resource Description" + num);
                    resources.add(mongoTemplate.save(resource));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?descriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?descriptionContains=description5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Resource Description5"));
    }

    @Test
    public void testListResourceWithNameOrDescriptionFilters() throws Exception {

        List<Resource> resources = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Resource resource = new Resource();
                    resource.setId(UUID.randomUUID().toString());
                    resource.setName("Resource Name" + num);
                    resource.setDescription("Resource Description" + num);
                    resources.add(mongoTemplate.save(resource));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?nameOrDescriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?nameOrDescriptionContains=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(resources.size()));

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?nameOrDescriptionContains=desc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(resources.size()));
    }

    @Test
    public void testListResourceWithStatusFilters() throws Exception {

        List<Resource> resources = new ArrayList<>();

        Resource reservedResource = new Resource();
        reservedResource.setId(UUID.randomUUID().toString());
        reservedResource.setResourceStatus(ResourceStatusType.RESERVED);
        resources.add(mongoTemplate.save(reservedResource));

        Resource availableResource = new Resource();
        availableResource.setId(UUID.randomUUID().toString());
        availableResource.setResourceStatus(ResourceStatusType.AVAILABLE);
        resources.add(mongoTemplate.save(availableResource));

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?status=RESERVED"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].resourceStatus").value("reserved"));

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?status=RESERVED&status=AVAILABLE"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].resourceStatus").value("reserved"))
                .andExpect(jsonPath("$.[1].resourceStatus").value("available"));
    }

    @Test
    public void testListResourceWithRelatedPartyFilters() throws Exception {

        List<Resource> resources = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    PartyRef party = new PartyRef();
                    party.setId(UUID.randomUUID().toString());
                    party.setName("Party" + num);
                    Resource resource = new Resource();
                    resource.setId(UUID.randomUUID().toString());
                    resource.addRelatedPartyItem(new RelatedPartyRefOrPartyRoleRef().partyOrPartyRole(party));
                    resources.add(mongoTemplate.save(resource));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?relatedPartyContains=party1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].relatedParty[0].partyOrPartyRole.name").value("Party1"));

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?relatedPartyContains=party"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(resources.size()));
    }

    @Test
    public void testListResourceWithCategoryFilters() throws Exception {

        Resource resource = new Resource();
        resource.setId(UUID.randomUUID().toString());
        resource.setCategory("CustomCategory");
        mongoTemplate.save(resource);

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?category=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource?category=CustomCategory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].category").value("CustomCategory"));
    }

    @Test
    public void testPatchResource() throws Exception {
        Resource resource = new Resource();
        resource.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        resource.setName("Boobar");
        resource.description("mock description");
        mongoTemplate.save(resource);

        this.mockMvc.perform(patch("/psi-api/resourceInventory/v2/resource/85e0810a-1a59-4002-926c-62de3651dcb9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"Resource\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("description").value("mock description"));
    }

    @Test
    public void testRetrieveResource() throws Exception {
        Resource resource = new Resource();
        resource.setId("7e769e94-23fd-4826-a97f-45725c21899f");
        mongoTemplate.save(resource);

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource/7e769e94-23fd-4826-a97f-45725c21899f"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("7e769e94-23fd-4826-a97f-45725c21899f"));
    }

    @Test
    public void testRetrieveResourceWithFields() throws Exception {
        Resource resource = new Resource();
        resource.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        resource.setName("Foobar");
        resource.setDescription("Description");
        mongoTemplate.save(resource);

        this.mockMvc.perform(get("/psi-api/resourceInventory/v2/resource/c37baf9c-8770-4594-8424-cd5767358ea8?fields=description,name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("description").exists())
                .andExpect(jsonPath("description").value("Description"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }
}
