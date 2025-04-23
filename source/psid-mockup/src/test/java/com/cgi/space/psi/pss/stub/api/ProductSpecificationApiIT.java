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
import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.RelatedPartyRefOrPartyRoleRef;
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
public class ProductSpecificationApiIT {

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
        mongoTemplate.remove(ProductSpecification.class).all();
    }

    @Test
    public void testCreateProductSpecification() throws Exception {
        this.mockMvc.perform(post("/psi-api/productCatalog/v2/productSpecification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"lifecycleStatus\": \"\", \"@type\": \"ProductSpecification\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testDeleteProductSpecification() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(specification);

        this.mockMvc.perform(delete("/psi-api/productCatalog/v2/productSpecification/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListProductSpecification() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListProductSpecificationWithFields() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        specification.setName("Foobar");
        specification.setVersion("1.1");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?fields=name"))
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
    public void testListProductSpecificationWithOffsetAndLimit() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?offset=" + offset + "&limit=" + limit))
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
    public void testListProductSpecificationWithLimitOnly() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?limit=" + limit))
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
    public void testListProductSpecificationWithNameFilters() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setName("Product Specification" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?name=Product Specification1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Product Specification1"));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?name=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?nameContains=specification5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Product Specification5"));
    }

    @Test
    public void testListProductSpecificationWithDescriptionFilters() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setDescription("Product Specification Description" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?descriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?descriptionContains=description5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Product Specification Description5"));
    }

    @Test
    public void testListProductSpecificationWithNameOrDescriptionFilters() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setName("Product Specification Name" + num);
                    specification.setDescription("Product Specification Description" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?nameOrDescriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?nameOrDescriptionContains=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?nameOrDescriptionContains=desc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));
    }

    @Test
    public void testListProductSpecificationWithVersionFilters() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setVersion("1." + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?version=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?version=1.5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].version").value("1.5"));
    }

    @Test
    public void testListProductSpecificationWithProductNumberFilters() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setProductNumber("PROD-NUM-" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?productNumber=PROD-NUM-2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].productNumber").value("PROD-NUM-2"));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?productNumber=PROD-NUM-2&productNumber=PROD-NUM-5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].productNumber").value("PROD-NUM-2"))
                .andExpect(jsonPath("$.[1].productNumber").value("PROD-NUM-5"));
    }

    @Test
    public void testListProductSpecificationWithLifecycleStatusFilters() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setLifecycleStatus("Status" + num);
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?lifecycleStatus=Status2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].lifecycleStatus").value("Status2"));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?lifecycleStatus=Status1&lifecycleStatus=Status3&lifecycleStatus=Status5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$.[0].lifecycleStatus").value("Status1"))
                .andExpect(jsonPath("$.[1].lifecycleStatus").value("Status3"))
                .andExpect(jsonPath("$.[2].lifecycleStatus").value("Status5"));
    }

    @Test
    public void testListProductSpecificationWithRelatedPartyFilters() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    PartyRef party = new PartyRef();
                    party.setId(UUID.randomUUID().toString());
                    party.setName("Party" + num);
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.addRelatedPartyItem(new RelatedPartyRefOrPartyRoleRef().partyOrPartyRole(party));
                    specifications.add(mongoTemplate.save(specification));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?relatedPartyContains=party1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].relatedParty[0].partyOrPartyRole.name").value("Party1"));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?relatedPartyContains=party"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(specifications.size()));
    }

    @Test
    public void testListProductSpecificationWithValidForFilters() throws Exception {

        List<ProductSpecification> specifications = new ArrayList<>();
        IntStream.range(0, 5).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
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

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?validBefore=2020-09-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].validFor").doesNotExist())
                .andExpect(jsonPath("$[1].validFor.startDateTime").value("2020-09-21T00:00:00Z"))
                .andExpect(jsonPath("$[2].validFor.startDateTime").value("2020-09-22T00:00:00Z"))
                .andExpect(jsonPath("$[3].validFor.startDateTime").value("2020-09-23T00:00:00Z"));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification?validAfter=2020-10-23T00:00:00Z"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].validFor").doesNotExist())
                .andExpect(jsonPath("$[1].validFor.endDateTime").value("2020-10-24T00:00:00Z"));
    }

    @Test
    public void testPatchProductSpecification() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        specification.setName("Boobar");
        specification.setDescription("test description");
        mongoTemplate.save(specification);

        this.mockMvc.perform(patch("/psi-api/productCatalog/v2/productSpecification/85e0810a-1a59-4002-926c-62de3651dcb9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"ProductSpecification\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("description").value("test description"));
    }

    @Test
    public void testRetrieveProductSpecification() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

    @Test
    public void testRetrieveProductSpecificationWithFields() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        specification.setName("Foobar");
        specification.setVersion("1.1");
        mongoTemplate.save(specification);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productSpecification/c37baf9c-8770-4594-8424-cd5767358ea8?fields=version,name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("version").exists())
                .andExpect(jsonPath("version").value("1.1"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

}
