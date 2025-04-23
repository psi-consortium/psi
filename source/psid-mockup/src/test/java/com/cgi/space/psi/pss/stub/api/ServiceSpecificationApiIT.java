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
import com.cgi.space.psi.common.model.ServiceSpecification;
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
public class ServiceSpecificationApiIT {

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
        mongoTemplate.remove(ServiceSpecification.class).all();
    }

    @Test
    public void testCreateServiceSpecification() throws Exception {
        this.mockMvc.perform(post("/psi-api/serviceCatalog/v2/serviceSpecification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"ServiceSpecification\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testDeleteServiceSpecification() throws Exception {
        ServiceSpecification specification = new ServiceSpecification();
        specification.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(specification);

        this.mockMvc.perform(delete("/psi-api/serviceCatalog/v2/serviceSpecification/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListServiceSpecification() throws Exception {
        ServiceSpecification specification = new ServiceSpecification();
        specification.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListServiceSpecificationWithFields() throws Exception {
        ServiceSpecification specification = new ServiceSpecification();
        specification.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        specification.setName("Foobar");
        specification.setVersion("1.1");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?fields=name"))
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
    public void testListServiceSpecificationWithOffsetAndLimit() throws Exception {

        List<ServiceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ServiceSpecification specification = new ServiceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?offset=" + offset + "&limit=" + limit))
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
    public void testListServiceSpecificationWithLimitOnly() throws Exception {

        List<ServiceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ServiceSpecification specification = new ServiceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?limit=" + limit))
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
    public void testListServiceSpecificationWithNameFilters() throws Exception {

        List<ServiceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ServiceSpecification specification = new ServiceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setName("Service Specification" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?name=Service Specification1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Service Specification1"));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?name=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?nameContains=specification5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Service Specification5"));
    }

    @Test
    public void testListServiceSpecificationWithDescriptionFilters() throws Exception {

        List<ServiceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ServiceSpecification specification = new ServiceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setDescription("Service Specification Description" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?descriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?descriptionContains=description5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Service Specification Description5"));
    }

    @Test
    public void testListServiceSpecificationWithNameOrDescriptionFilters() throws Exception {

        List<ServiceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ServiceSpecification specification = new ServiceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setName("Service Specification Name" + num);
                    specification.setDescription("Service Specification Description" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?nameOrDescriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?nameOrDescriptionContains=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?nameOrDescriptionContains=desc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));
    }

    @Test
    public void testListServiceSpecificationWithVersionFilters() throws Exception {

        List<ServiceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ServiceSpecification specification = new ServiceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setVersion("1." + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?version=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?version=1.5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].version").value("1.5"));
    }

    @Test
    public void testListServiceSpecificationWithLifecycleStatusFilters() throws Exception {

        List<ServiceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ServiceSpecification specification = new ServiceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setLifecycleStatus("Status" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?lifecycleStatus=Status2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].lifecycleStatus").value("Status2"));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?lifecycleStatus=Status1&lifecycleStatus=Status3&lifecycleStatus=Status5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].lifecycleStatus").value("Status1"))
                .andExpect(jsonPath("$.[1].lifecycleStatus").value("Status3"))
                .andExpect(jsonPath("$.[2].lifecycleStatus").value("Status5"));
    }

    @Test
    public void testListServiceSpecificationWithRelatedPartyFilters() throws Exception {

        List<ServiceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    PartyRef party = new PartyRef();
                    party.setId(UUID.randomUUID().toString());
                    party.setName("Party" + num);
                    ServiceSpecification specification = new ServiceSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.addRelatedPartyItem(new RelatedPartyRefOrPartyRoleRef().partyOrPartyRole(party));
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?relatedPartyContains=party1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].relatedParty[0].partyOrPartyRole.name").value("Party1"));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?relatedPartyContains=party"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));
    }

    @Test
    public void testListServiceSpecificationWithValidForFilters() throws Exception {

        List<ServiceSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 5).forEach(num -> {
                    ServiceSpecification specification = new ServiceSpecification();
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

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?validBefore=2020-09-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].validFor").doesNotExist())
                .andExpect(jsonPath("$[1].validFor.startDateTime").value("2020-09-21T00:00:00Z"))
                .andExpect(jsonPath("$[2].validFor.startDateTime").value("2020-09-22T00:00:00Z"))
                .andExpect(jsonPath("$[3].validFor.startDateTime").value("2020-09-23T00:00:00Z"));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?validAfter=2020-10-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].validFor").doesNotExist())
                .andExpect(jsonPath("$[1].validFor.endDateTime").value("2020-10-24T00:00:00Z"));
    }

    @Test
    public void testListServiceSpecificationWithCategoryFilters() throws Exception {

        ServiceSpecification specification = new ServiceSpecification();
        specification.setId(UUID.randomUUID().toString());
        specification.setCategory("CustomCategory");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?category=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification?category=CustomCategory"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].category").value("CustomCategory"));
    }

    @Test
    public void testPatchServiceSpecification() throws Exception {
        ServiceSpecification specification = new ServiceSpecification();
        specification.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        specification.setName("Boobar");
        specification.setDescription("test description");
        mongoTemplate.save(specification);

        this.mockMvc.perform(patch("/psi-api/serviceCatalog/v2/serviceSpecification/85e0810a-1a59-4002-926c-62de3651dcb9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"ServiceSpecification\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("description").value("test description"));
    }

    @Test
    public void testRetrieveServiceSpecification() throws Exception {
        ServiceSpecification specification = new ServiceSpecification();
        specification.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

    @Test
    public void testRetrieveServiceSpecificationWithFields() throws Exception {
        ServiceSpecification specification = new ServiceSpecification();
        specification.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        specification.setName("Foobar");
        specification.setVersion("1.1");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/serviceCatalog/v2/serviceSpecification/c37baf9c-8770-4594-8424-cd5767358ea8?fields=version,name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("version").exists())
                .andExpect(jsonPath("version").value("1.1"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

}
