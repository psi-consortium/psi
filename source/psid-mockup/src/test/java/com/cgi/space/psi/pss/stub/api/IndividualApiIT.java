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

import com.cgi.space.psi.common.model.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class IndividualApiIT {

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
        mongoTemplate.remove(Individual.class).all();
    }

    @Test
    public void testCreateIndividual() throws Exception {
        this.mockMvc.perform(post("/psi-api/partyManagement/v2/individual")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"givenName\": \"John\", \"familyName\": \"Doe\", \"@type\": \"Individual\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("givenName").value("John"))
                .andExpect(jsonPath("familyName").value("Doe"));
    }

    @Test
    public void testDeleteIndividual() throws Exception {
        Individual individual = new Individual();
        individual.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(individual);

        this.mockMvc.perform(delete("/psi-api/partyManagement/v2/individual/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListIndividual() throws Exception {
        Individual individual = new Individual();
        individual.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(individual);

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListIndividualWithFields() throws Exception {
        Individual individual = new Individual();
        individual.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        individual.setGivenName("John");
        individual.setFamilyName("Doe");
        individual.setGender("male");
        mongoTemplate.save(individual);

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?fields=familyName,gender"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].familyName").exists())
                .andExpect(jsonPath("$[0].familyName").value("Doe"))
                .andExpect(jsonPath("$[0].gender").exists())
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].givenName").doesNotExist());
    }

    @Test
    public void testListIndividualWithOffsetAndLimit() throws Exception {

        List<Individual> individuals = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Individual individual = new Individual();
                    individual.setId(UUID.randomUUID().toString());
                    individuals.add(mongoTemplate.save(individual));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("X-Total-Count", is("10")))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(individuals.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(individuals.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(individuals.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(individuals.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(individuals.get(offset + 4).getId()));
    }

    @Test
    public void testListIndividualWithLimitOnly() throws Exception {

        List<Individual> individuals = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Individual individual = new Individual();
                    individual.setId(UUID.randomUUID().toString());
                    individuals.add(mongoTemplate.save(individual));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(individuals.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(individuals.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(individuals.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(individuals.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(individuals.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListIndividualWithGivenNameFilters() throws Exception {

        List<Individual> individuals = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Individual individual = new Individual();
                    individual.setId(UUID.randomUUID().toString());
                    individual.setGivenName("GivenName" + num);
                    individuals.add(mongoTemplate.save(individual));
                }
        );

        // givenName equals
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?givenName=GivenName1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].givenName").value("GivenName1"));

        // givenNameContains (nonExistent)
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?givenNameContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        // givenNameContains (existent)
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?givenNameContains=Name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(individuals.size()));
    }

    @Test
    public void testListIndividualWithFamilyNameFilters() throws Exception {

        List<Individual> individuals = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Individual individual = new Individual();
                    individual.setId(UUID.randomUUID().toString());
                    individual.setFamilyName("FamilyName" + num);
                    individuals.add(mongoTemplate.save(individual));
                }
        );

        // familyName equals
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?familyName=FamilyName1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].familyName").value("FamilyName1"));

        // familyNameContains (nonExistent)
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?familyNameContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        // familyNameContains (existent)
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?familyNameContains=family"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(individuals.size()));
    }

    @Test
    public void testListIndividualWithNameFilters() throws Exception {

        List<Individual> individuals = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Individual individual = new Individual();
                    individual.setId(UUID.randomUUID().toString());
                    individual.setName("Name" + num);
                    individuals.add(mongoTemplate.save(individual));
                }
        );

        // name equals
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?name=Name1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Name1"));

        // nameContains (nonExistent)
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?nameContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        // nameContains (existent)
        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual?nameContains=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(individuals.size()));
    }

    @Test
    public void testPatchIndividual() throws Exception {
        Individual individual = new Individual();
        individual.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        individual.setGivenName("John");
        individual.setFamilyName("Dough");
        individual.setLocation("test location");
        mongoTemplate.save(individual);

        this.mockMvc.perform(patch("/psi-api/partyManagement/v2/individual/85e0810a-1a59-4002-926c-62de3651dcb9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"givenName\": \"Jane\", \"familyName\": \"Doe\", \"@type\": \"Individual\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("givenName").value("Jane"))
                .andExpect(jsonPath("familyName").value("Doe"))
                .andExpect(jsonPath("location").value("test location"));
    }

    @Test
    public void testRetrieveIndividual() throws Exception {
        Individual individual = new Individual();
        individual.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(individual);

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

    @Test
    public void testRetrieveIndividualWithFields() throws Exception {
        Individual individual = new Individual();
        individual.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        individual.setFamilyName("Doe");
        individual.setGivenName("John");
        mongoTemplate.save(individual);

        this.mockMvc.perform(get("/psi-api/partyManagement/v2/individual/c37baf9c-8770-4594-8424-cd5767358ea8?fields=id,givenName"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("id").value("c37baf9c-8770-4594-8424-cd5767358ea8"))
                .andExpect(jsonPath("familyName").doesNotExist())
                .andExpect(jsonPath("givenName").exists())
                .andExpect(jsonPath("givenName").value("John"));
    }

}
