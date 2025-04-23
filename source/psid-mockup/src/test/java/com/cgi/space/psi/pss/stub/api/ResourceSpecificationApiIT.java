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
import com.cgi.space.psi.common.model.ResourceSpecification;
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
public class ResourceSpecificationApiIT {

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
        mongoTemplate.remove(ResourceSpecification.class).all();
    }

    @Test
    public void testCreateResourceSpecification() throws Exception {
        this.mockMvc.perform(post("/psi-api/resourceCatalog/v2/resourceSpecification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"ResourceSpecification\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testDeleteResourceSpecification() throws Exception {
        ResourceSpecification specification = new ResourceSpecification();
        specification.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(specification);

        this.mockMvc.perform(delete("/psi-api/resourceCatalog/v2/resourceSpecification/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListResourceSpecification() throws Exception {
        ResourceSpecification specification = new ResourceSpecification();
        specification.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListResourceSpecificationWithFields() throws Exception {
        ResourceSpecification specification = new ResourceSpecification();
        specification.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        specification.setName("Foobar");
        specification.setVersion("1.1");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?fields=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].name").value("Foobar"))
                .andExpect(jsonPath("$[0].version").doesNotExist());
    }

    @Test
    public void testListResourceSpecificationWithOffsetAndLimit() throws Exception {

        List<ResourceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ResourceSpecification specification = new ResourceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?offset=" + offset + "&limit=" + limit))
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
    public void testListResourceSpecificationWithLimitOnly() throws Exception {

        List<ResourceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ResourceSpecification specification = new ResourceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?limit=" + limit))
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
    public void testListResourceSpecificationWithNameFilters() throws Exception {

        List<ResourceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ResourceSpecification specification = new ResourceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setName("Resource Specification" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?name=Resource Specification1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Resource Specification1"));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?name=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?nameContains=specification5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Resource Specification5"));
    }

    @Test
    public void testListResourceSpecificationWithDescriptionFilters() throws Exception {

        List<ResourceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ResourceSpecification specification = new ResourceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setDescription("Resource Specification Description" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?descriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?descriptionContains=description5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Resource Specification Description5"));
    }

    @Test
    public void testListResourceSpecificationWithNameOrDescriptionFilters() throws Exception {

        List<ResourceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ResourceSpecification specification = new ResourceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setName("Resource Specification Name" + num);
                    specification.setDescription("Resource Specification Description" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?nameOrDescriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?nameOrDescriptionContains=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?nameOrDescriptionContains=desc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));
    }

    @Test
    public void testListResourceSpecificationWithVersionFilters() throws Exception {

        List<ResourceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ResourceSpecification specification = new ResourceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setVersion("1." + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?version=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?version=1.5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].version").value("1.5"));
    }

    @Test
    public void testListResourceSpecificationWithLifecycleStatusFilters() throws Exception {

        List<ResourceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ResourceSpecification specification = new ResourceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setLifecycleStatus("Status" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?lifecycleStatus=Status2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].lifecycleStatus").value("Status2"));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?lifecycleStatus=Status1&lifecycleStatus=Status3&lifecycleStatus=Status5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].lifecycleStatus").value("Status1"))
                .andExpect(jsonPath("$.[1].lifecycleStatus").value("Status3"))
                .andExpect(jsonPath("$.[2].lifecycleStatus").value("Status5"));
    }

    @Test
    public void testListResourceSpecificationWithRelatedPartyFilters() throws Exception {

        List<ResourceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    PartyRef party = new PartyRef();
                    party.setId(UUID.randomUUID().toString());
                    party.setName("Party" + num);
                    ResourceSpecification specification = new ResourceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.addRelatedPartyItem(new RelatedPartyRefOrPartyRoleRef().partyOrPartyRole(party));
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?relatedPartyContains=party1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].relatedParty[0].partyOrPartyRole.name").value("Party1"));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?relatedPartyContains=party"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));
    }

    @Test
    public void testListResourceSpecificationWithValidForFilters() throws Exception {

        List<ResourceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 5).forEach(num -> {
                    ResourceSpecification specification = new ResourceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    if (num > 0) {
                        TimePeriod validFor = new TimePeriod();
                        validFor.setStartDateTime(OffsetDateTime.parse("2020-09-2" + num + "T00:00:00Z"));
                        validFor.setEndDateTime(OffsetDateTime.parse("2020-10-2" + num + "T00:00:00Z"));
                        specification.setValidFor(validFor);
                    }
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?validBefore=2020-09-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].validFor").doesNotExist())
                .andExpect(jsonPath("$[1].validFor.startDateTime").value("2020-09-21T00:00:00Z"))
                .andExpect(jsonPath("$[2].validFor.startDateTime").value("2020-09-22T00:00:00Z"))
                .andExpect(jsonPath("$[3].validFor.startDateTime").value("2020-09-23T00:00:00Z"));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?validAfter=2020-10-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].validFor").doesNotExist())
                .andExpect(jsonPath("$[1].validFor.endDateTime").value("2020-10-24T00:00:00Z"));
    }

    @Test
    public void testListResourceSpecificationWithCategoryFilters() throws Exception {

        ResourceSpecification specification = new ResourceSpecification();
        specification.setId(UUID.randomUUID().toString());
        specification.setCategory("CustomCategory");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?category=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification?category=CustomCategory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].category").value("CustomCategory"));
    }

    @Test
    public void testPatchResourceSpecification() throws Exception {
        ResourceSpecification specification = new ResourceSpecification();
        specification.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        specification.setName("Boobar");
        specification.setDescription("test description");
        mongoTemplate.save(specification);

        this.mockMvc.perform(patch("/psi-api/resourceCatalog/v2/resourceSpecification/85e0810a-1a59-4002-926c-62de3651dcb9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"ResourceSpecification\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("description").value("test description"));
    }

    @Test
    public void testRetrieveResourceSpecification() throws Exception {
        ResourceSpecification specification = new ResourceSpecification();
        specification.setId("7e769e94-23fd-4826-a97f-45725c21899f");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification/7e769e94-23fd-4826-a97f-45725c21899f"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("7e769e94-23fd-4826-a97f-45725c21899f"));
    }

    @Test
    public void testRetrieveResourceSpecificationWithFields() throws Exception {
        ResourceSpecification specification = new ResourceSpecification();
        specification.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        specification.setName("Foobar");
        specification.setVersion("1.1");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/resourceCatalog/v2/resourceSpecification/c37baf9c-8770-4594-8424-cd5767358ea8?fields=version,name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("version").exists())
                .andExpect(jsonPath("version").value("1.1"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

}
