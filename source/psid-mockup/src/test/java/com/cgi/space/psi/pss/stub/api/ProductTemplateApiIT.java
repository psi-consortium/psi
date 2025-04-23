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

import java.net.URI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.TargetProductSchema;

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
public class ProductTemplateApiIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    private MockMvc mockMvc;

    private static final String TEMPLATE_COLLECTION = "productTemplate";

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(new Query(), TEMPLATE_COLLECTION);
    }

    @Test
    public void testCreateProductTemplate() throws Exception {
        this.mockMvc.perform(post("/psi-api/productCatalog/v2/productTemplate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"lifecycleStatus\": \"\", \"@type\": \"ProductSpecification\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }

    @Test
    public void testDeleteProductTemplate() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(specification, TEMPLATE_COLLECTION);

        this.mockMvc.perform(delete("/psi-api/productCatalog/v2/productTemplate/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListProductTemplate() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(specification, TEMPLATE_COLLECTION);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListProductTemplateWithFields() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        specification.setName("Foobar");
        specification.setVersion("1.1");
        mongoTemplate.save(specification, TEMPLATE_COLLECTION);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate?fields=name"))
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
    public void testListProductTemplateWithOffsetAndLimit() throws Exception {
        List<ProductSpecification> templates = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    templates.add(mongoTemplate.save(specification, TEMPLATE_COLLECTION));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(templates.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(templates.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(templates.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(templates.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(templates.get(offset + 4).getId()));
    }

    @Test
    public void testListProductTemplateWithLimitOnly() throws Exception {
        List<ProductSpecification> templates = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    templates.add(mongoTemplate.save(specification, TEMPLATE_COLLECTION));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(templates.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(templates.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(templates.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(templates.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(templates.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListProductTemplateWithNameFilters() throws Exception {
        List<ProductSpecification> templates = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductSpecification specification = new ProductSpecification();
                    specification.setId(UUID.randomUUID().toString());
                    specification.setName("Product Template" + num);
                    templates.add(mongoTemplate.save(specification, TEMPLATE_COLLECTION));
                }
        );

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate?name=Product Template1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Product Template1"));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate?name=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate?nameContains=template5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Product Template5"));
    }

    @Test
    public void testListProductTemplateWithCategoryFilter() throws Exception {
        ProductSpecification specification1 = new ProductSpecification();
        specification1.setId(UUID.randomUUID().toString());
        specification1.setTargetProductSchema(new TargetProductSchema()
                .atType("TelephonyProduct")
                .atSchemaLocation(URI.create("http://localhost/schemas/Product/TelephonyProduct.schema.json")));
        mongoTemplate.save(specification1, TEMPLATE_COLLECTION);

        ProductSpecification specification2 = new ProductSpecification();
        specification2.setId(UUID.randomUUID().toString());
        specification2.setTargetProductSchema(new TargetProductSchema()
                .atType("InternetAccessProduct")
                .atSchemaLocation(URI.create("http://localhost/schemas/Product/InternetAccessProduct.schema.json")));
        mongoTemplate.save(specification2, TEMPLATE_COLLECTION);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate?targetProductSchema=InternetAccessProduct"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].targetProductSchema.@type").value("InternetAccessProduct"));
    }

    @Test
    public void testPatchProductTemplate() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        specification.setName("Boobar");
        specification.setDescription("test description");
        mongoTemplate.save(specification, TEMPLATE_COLLECTION);

        this.mockMvc.perform(patch("/psi-api/productCatalog/v2/productTemplate/85e0810a-1a59-4002-926c-62de3651dcb9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"lifecycleStatus\": \"\", \"@type\": \"ProductSpecification\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("description").value("test description"));
    }

    @Test
    public void testRetrieveProductTemplate() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("7e769e94-23fd-4826-a97f-45725c21899f");
        mongoTemplate.save(specification, TEMPLATE_COLLECTION);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate/7e769e94-23fd-4826-a97f-45725c21899f"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("7e769e94-23fd-4826-a97f-45725c21899f"));
    }

    @Test
    public void testRetrieveProductTemplateWithFields() throws Exception {
        ProductSpecification specification = new ProductSpecification();
        specification.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        specification.setName("Foobar");
        specification.setVersion("1.1");
        mongoTemplate.save(specification, TEMPLATE_COLLECTION);

        this.mockMvc.perform(get("/psi-api/productCatalog/v2/productTemplate/c37baf9c-8770-4594-8424-cd5767358ea8?fields=version,name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("version").exists())
                .andExpect(jsonPath("version").value("1.1"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }
}
