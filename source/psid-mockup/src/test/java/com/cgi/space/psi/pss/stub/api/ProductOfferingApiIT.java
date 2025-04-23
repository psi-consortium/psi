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

import com.cgi.space.psi.common.model.ProductOffering;
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
public class ProductOfferingApiIT {

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
        mongoTemplate.remove(ProductOffering.class).all();
    }

    @Test
    public void testCreateProductOffering() throws Exception {
        this.mockMvc.perform(post("/psi-api/productCatalog/v2/productOffering")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"lifecycleStatus\": \"\", \"@type\": \"ProductOffering\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testCreateProductOfferingWithBundles() throws Exception {
        this.mockMvc.perform(
                post("/psi-api/productCatalog/v2/productOffering")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\": \"Foobar\"," +
                                "\"lifecycleStatus\": \"active\"," +
                                "\"bundledProductOffering\": [" +
                                "{\"id\": \"" + UUID.randomUUID() + "\", \"name\": \"Terminal X - land\", \"@type\": \"BundledProductOffering\"}, " +
                                "{\"id\": \"" + UUID.randomUUID() + "\", \"name\": \"Terminal B3 - sea\", \"@type\": \"BundledProductOffering\"}]," +
                                "\"@type\": \"ProductOffering\"" +
                                "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("bundledProductOffering").isArray())
                .andExpect(jsonPath("bundledProductOffering.length()").value(2));
    }

    @Test
    public void testCreateProductOfferingWithBundleGroups() throws Exception {
        this.mockMvc.perform(post("/psi-api/productCatalog/v2/productOffering")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "    \"name\": \"Internet\"," +
                                "    \"lifecycleStatus\": \"active\"," +
                                "    \"bundledGroupProductOffering\": [" +
                                "        {" +
                                "            \"name\": \"Satellite Terminal\"," +
                                "            \"bundledProductOffering\": [" +
                                "                {\"id\": \"" + UUID.randomUUID() + "\", \"name\": \"Terminal X1 - land\", \"@type\": \"BundledProductOffering\"}," +
                                "                {\"id\": \"" + UUID.randomUUID() + "\", \"name\": \"Terminal X2 - land\", \"@type\": \"BundledProductOffering\"}" +
                                "            ]," +
                                "            \"bundledGroupProductOfferingOption\": { \"numberRelOfferLowerLimit\": 1, \"numberRelOfferUpperLimit\": 1 }" +
                                "        }" +
                                "    ]," +
                                "    \"@type\": \"ProductOffering\"" +
                                "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Internet"))
                .andExpect(jsonPath("bundledGroupProductOffering").isArray())
                .andExpect(jsonPath("bundledGroupProductOffering.length()").value(1))
                .andExpect(jsonPath("bundledGroupProductOffering[0].name").value("Satellite Terminal"))
                .andExpect(jsonPath("bundledGroupProductOffering[0].bundledProductOffering").isArray())
                .andExpect(jsonPath("bundledGroupProductOffering[0].bundledProductOffering.length()").value(2))
                .andExpect(jsonPath("bundledGroupProductOffering[0].bundledGroupProductOfferingOption.numberRelOfferLowerLimit").value(1));
    }

    @Test
    public void testDeleteProductOffering() throws Exception {
        ProductOffering offering = new ProductOffering();
        offering.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(offering);

        this.mockMvc.perform(delete("/psi-api/productCatalog/v2/productOffering/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListProductOffering() throws Exception {
        ProductOffering offering = new ProductOffering();
        offering.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(offering);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListProductOfferingWithFields() throws Exception {
        ProductOffering offering = new ProductOffering();
        offering.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        offering.setName("Foobar");
        offering.setVersion("1.1");
        mongoTemplate.save(offering);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?fields=name"))
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
    public void testListProductOfferingWithOffsetAndLimit() throws Exception {

        List<ProductOffering> offerings = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductOffering offering = new ProductOffering();
                    offering.setId(UUID.randomUUID().toString());
                    offerings.add(mongoTemplate.save(offering));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(offerings.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(offerings.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(offerings.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(offerings.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(offerings.get(offset + 4).getId()));
    }

    @Test
    public void testListProductOfferingWithLimitOnly() throws Exception {

        List<ProductOffering> offerings = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductOffering offering = new ProductOffering();
                    offering.setId(UUID.randomUUID().toString());
                    offerings.add(mongoTemplate.save(offering));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(offerings.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(offerings.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(offerings.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(offerings.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(offerings.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListProductOfferingWithNameFilters() throws Exception {

        List<ProductOffering> offerings = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductOffering offering = new ProductOffering();
                    offering.setId(UUID.randomUUID().toString());
                    offering.setName("Offering Name" + num);
                    offerings.add(mongoTemplate.save(offering));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?name=Offering Name1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Offering Name1"));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?nameContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?nameContains=name5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Offering Name5"));
    }

    @Test
    public void testListProductOfferingWithDescriptionFilters() throws Exception {

        List<ProductOffering> offerings = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductOffering offering = new ProductOffering();
                    offering.setId(UUID.randomUUID().toString());
                    offering.setDescription("Offering Description" + num);
                    offerings.add(mongoTemplate.save(offering));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?descriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?descriptionContains=description5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Offering Description5"));
    }

    @Test
    public void testListProductOfferingWithNameOrDescriptionContainsFilters() throws Exception {

        List<ProductOffering> offerings = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductOffering offering = new ProductOffering();
                    offering.setId(UUID.randomUUID().toString());
                    offering.setName("Offering Name" + num);
                    offering.setDescription("Offering Description" + num);
                    offerings.add(mongoTemplate.save(offering));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?nameOrDescriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?nameOrDescriptionContains=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(offerings.size()));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?nameOrDescriptionContains=descr"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(offerings.size()));
    }

    @Test
    public void testListProductOfferingWithVersionFilters() throws Exception {

        List<ProductOffering> offerings = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductOffering offering = new ProductOffering();
                    offering.setId(UUID.randomUUID().toString());
                    offering.setVersion("1." + num);
                    offerings.add(mongoTemplate.save(offering));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?version=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?version=1.5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].version").value("1.5"));
    }

    @Test
    public void testListProductOfferingWithLifecycleStatusFilters() throws Exception {

        List<ProductOffering> offerings = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductOffering offering = new ProductOffering();
                    offering.setId(UUID.randomUUID().toString());
                    offering.setLifecycleStatus("Status" + num);
                    offerings.add(mongoTemplate.save(offering));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?lifecycleStatus=Status1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].lifecycleStatus").value("Status1"));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?lifecycleStatus=Status1&lifecycleStatus=Status3&lifecycleStatus=Status5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].lifecycleStatus").value("Status1"))
                .andExpect(jsonPath("$[1].lifecycleStatus").value("Status3"))
                .andExpect(jsonPath("$[2].lifecycleStatus").value("Status5"));
    }

    @Test
    public void testListProductOfferingWithValidForFilters() throws Exception {

        List<ProductOffering> offerings = new ArrayList<>();
        IntStream.range(0, 5).forEach(num -> {
                    ProductOffering offering = new ProductOffering();
                    offering.setId(UUID.randomUUID().toString());
                    if (num > 0) {
                        TimePeriod validFor = new TimePeriod();
                        validFor.setStartDateTime(OffsetDateTime.parse("2020-09-2" + num + "T00:00:00Z"));
                        validFor.setEndDateTime(OffsetDateTime.parse("2020-10-2" + num + "T00:00:00Z"));
                        offering.setValidFor(validFor);
                    }
                    offerings.add(mongoTemplate.save(offering));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?validBefore=2020-09-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].validFor").doesNotExist())
                .andExpect(jsonPath("$[1].validFor.startDateTime").value("2020-09-21T00:00:00Z"))
                .andExpect(jsonPath("$[2].validFor.startDateTime").value("2020-09-22T00:00:00Z"))
                .andExpect(jsonPath("$[3].validFor.startDateTime").value("2020-09-23T00:00:00Z"));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering?validAfter=2020-10-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].validFor").doesNotExist())
                .andExpect(jsonPath("$[1].validFor.endDateTime").value("2020-10-24T00:00:00Z"));
    }

    @Test
    public void testPatchProductOffering() throws Exception {
        ProductOffering offering = new ProductOffering();
        offering.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        offering.setName("Boobar");
        offering.setDescription("test description");
        mongoTemplate.save(offering);

        this.mockMvc.perform(patch("/psi-api/productCatalog/v2/productOffering/28f0b4cf-40ff-49c1-9957-8d1a8e154444")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"ProductOffering\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("description").value("test description"));
    }

    @Test
    public void testRetrieveProductOffering() throws Exception {
        ProductOffering offering = new ProductOffering();
        offering.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(offering);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

    @Test
    public void testRetrieveProductOfferingWithFields() throws Exception {
        ProductOffering offering = new ProductOffering();
        offering.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        offering.setName("Foobar");
        offering.setVersion("1.1");
        mongoTemplate.save(offering);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productOffering/c37baf9c-8770-4594-8424-cd5767358ea8?fields=version,name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("version").exists())
                .andExpect(jsonPath("version").value("1.1"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }
}
