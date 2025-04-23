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
import com.cgi.space.psi.common.model.Product;
import com.cgi.space.psi.common.model.ProductStatusType;
import com.cgi.space.psi.common.model.RelatedPartyOrPartyRole;

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
public class ProductInventoryApiIT {

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
        mongoTemplate.remove(Product.class).all();
    }

    @Test
    public void testCreateProduct() throws Exception {
        this.mockMvc.perform(post("/psi-api/productInventory/v2/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"status\": \"cancelled\", \"@type\": \"Product\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("status").value("cancelled"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product();
        product.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        mongoTemplate.save(product);

        this.mockMvc.perform(delete("/psi-api/productInventory/v2/product/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListProduct() throws Exception {
        Product product = new Product();
        product.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(product);

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListProductWithFields() throws Exception {
        Product product = new Product();
        product.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        product.setName("Foobar");
        product.setDescription("Desc");
        mongoTemplate.save(product);

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?fields=name"))
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
    public void testListProductWithOffsetAndLimit() throws Exception {

        List<Product> products = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Product product = new Product();
                    product.setId(UUID.randomUUID().toString());
                    products.add(mongoTemplate.save(product));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(products.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(products.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(products.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(products.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(products.get(offset + 4).getId()));
    }

    @Test
    public void testListProductWithLimitOnly() throws Exception {

        List<Product> products = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Product product = new Product();
                    product.setId(UUID.randomUUID().toString());
                    products.add(mongoTemplate.save(product));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(products.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(products.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(products.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(products.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(products.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListProductWithNameFilters() throws Exception {

        List<Product> products = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Product product = new Product();
                    product.setId(UUID.randomUUID().toString());
                    product.setName("Product" + num);
                    products.add(mongoTemplate.save(product));
                }
        );

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?name=Product1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Product1"));

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?name=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?nameContains=product5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Product5"));
    }

    @Test
    public void testListProductWithDescriptionFilters() throws Exception {

        List<Product> products = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Product product = new Product();
                    product.setId(UUID.randomUUID().toString());
                    product.setDescription("Product Description" + num);
                    products.add(mongoTemplate.save(product));
                }
        );

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?descriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?descriptionContains=description5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].description").value("Product Description5"));
    }

    @Test
    public void testListProductWithNameOrDescriptionFilters() throws Exception {

        List<Product> products = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Product product = new Product();
                    product.setId(UUID.randomUUID().toString());
                    product.setName("Product Name" + num);
                    product.setDescription("Product Description" + num);
                    products.add(mongoTemplate.save(product));
                }
        );

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?nameOrDescriptionContains=nonExistent"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?nameOrDescriptionContains=name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(products.size()));

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?nameOrDescriptionContains=desc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(products.size()));
    }

    @Test
    public void testListProductWithProductSerialNumberFilters() throws Exception {

        List<Product> products = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Product product = new Product();
                    product.setId(UUID.randomUUID().toString());
                    product.setProductSerialNumber("PROD-SERIAL-NUM-" + num);
                    products.add(mongoTemplate.save(product));
                }
        );

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?serialNumber=PROD-SERIAL-NUM-2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].productSerialNumber").value("PROD-SERIAL-NUM-2"));

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?serialNumber=PROD-SERIAL-NUM-2&serialNumber=PROD-SERIAL-NUM-5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].productSerialNumber").value("PROD-SERIAL-NUM-2"))
                .andExpect(jsonPath("$.[1].productSerialNumber").value("PROD-SERIAL-NUM-5"));
    }

    @Test
    public void testListProductWithStatusFilters() throws Exception {

        List<Product> products = new ArrayList<>();
        Product activeProduct = new Product();
        activeProduct.setId(UUID.randomUUID().toString());
        activeProduct.setStatus(ProductStatusType.ACTIVE);
        products.add(mongoTemplate.save(activeProduct));

        Product cancelledProduct = new Product();
        cancelledProduct.setId(UUID.randomUUID().toString());
        cancelledProduct.setStatus(ProductStatusType.CANCELLED);
        products.add(mongoTemplate.save(cancelledProduct));

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?status=active"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].status").value("active"));

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?status=active&status=cancelled"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].status").value("active"))
                .andExpect(jsonPath("$.[1].status").value("cancelled"));
    }

    @Test
    public void testListProductWithRelatedPartyFilters() throws Exception {

        List<Product> products = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    PartyRef party = new PartyRef();
                    party.setId(UUID.randomUUID().toString());
                    party.setName("Party" + num);
                    Product product = new Product();
                    product.setId(UUID.randomUUID().toString());
                    product.addRelatedPartyItem(new RelatedPartyOrPartyRole().partyOrPartyRole(party));
                    products.add(mongoTemplate.save(product));
                }
        );

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?relatedPartyContains=party1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].relatedParty[0].partyOrPartyRole.name").value("Party1"));

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product?relatedPartyContains=party"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(products.size()));
    }

    @Test
    public void testPatchProduct() throws Exception {
        Product product = new Product();
        product.setId("85e0810a-1a59-4002-926c-62de3651dcb9");
        product.setName("Boobar");
        product.setDescription("mock description");
        mongoTemplate.save(product);

        this.mockMvc.perform(patch("/psi-api/productInventory/v2/product/85e0810a-1a59-4002-926c-62de3651dcb9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Foobar\", \"@type\": \"Product\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Foobar"))
                .andExpect(jsonPath("description").value("mock description"));
    }

    @Test
    public void testRetrieveProduct() throws Exception {
        Product product = new Product();
        product.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(product);

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

    @Test
    public void testRetrieveProductWithFields() throws Exception {
        Product product = new Product();
        product.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        product.setName("Foobar");
        product.setDescription("Desc");
        mongoTemplate.save(product);

        this.mockMvc.perform(get("/psi-api/productInventory/v2/product/c37baf9c-8770-4594-8424-cd5767358ea8?fields=description,name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("description").exists())
                .andExpect(jsonPath("description").value("Desc"))
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("name").value("Foobar"));
    }
}
