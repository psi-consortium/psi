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

import com.cgi.space.psi.common.HubSubscriptionWrapper;
import com.cgi.space.psi.common.model.HubSubscriptionCreate;
import com.cgi.space.psi.common.model.ItemActionType;
import com.cgi.space.psi.common.model.ProductOrder;
import com.cgi.space.psi.common.model.ProductOrderItem;
import com.cgi.space.psi.common.model.ProductOrderStateType;
import com.cgi.space.psi.common.model.Topic;
import com.cgi.space.psi.pss.stub.demodata.DemoPssData;
import com.cgi.space.psi.pss.stub.event.AbstractEvent;
import com.cgi.space.psi.pss.stub.service.HubService;
import java.io.IOException;
import java.net.URI;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(profiles = "pss")
public class ProductOrderApiIT {

    private static MockWebServer mockBackEnd;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private HubService hubService;
    private MockMvc mockMvc;

    /**
     * initializes the mock of the backend
     *
     * @throws IOException in case of an error an IOException is thrown
     */
    @BeforeAll
    public static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    public void createContext() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(ProductOrder.class).all();
        mongoTemplate.remove(HubSubscriptionWrapper.class).all();
    }

    /**
     * Shutdown the backend mock after all the tests are finished
     *
     * @throws IOException in case of an error an IOException is thrown
     */
    @AfterAll
    public static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    public void testCreateProductOrder() throws Exception {
        mongoTemplate.remove(HubSubscriptionWrapper.class).all();
        hubService.registerSubscription(DemoPssData.TOPIC_ID_ORDER, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort())));
        mockBackEnd.enqueue(new MockResponse().setResponseCode(200));

        this.mockMvc.perform(post("/psi-api/productOrdering/v2/productOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"Foobar\", \"productOrderItem\": [ { \"id\": \"100\", \"action\": \"add\", \"@type\": \"ProductOrderItem\" }], \"@type\": \"ProductOrder\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("state").value("pending"))
                .andExpect(jsonPath("description").value("Foobar"))
                .andExpect(jsonPath("productOrderItem").isArray())
                .andExpect(jsonPath("productOrderItem.length()").value(1))
                .andExpect(jsonPath("productOrderItem[0].id").value("100"))
                .andExpect(jsonPath("productOrderItem[0].action").value("add"));
        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        assertThat(recordedRequest.getMethod(), is("POST"));
    }

    @Test
    public void testCreateProductOrderViaEventApi() throws Exception {
        List<Topic> topics = mongoTemplate.findAll(Topic.class);
        Topic orderTopic = topics.stream().filter(t -> t.getName().equals(AbstractEvent.TOPIC_ORDER))
                .findFirst()
                .orElse(null);

        assertThat(orderTopic, is(notNullValue()));

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + orderTopic.getId() + "/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"domain\": \"order\", \"eventType\": \"created\", \"event\": { \"id\": \"e7fcd72d-079c-45a9-bf00-ca70b7042ef2\", \"state\": \"pending\", \"@type\": \"ProductOrder\" } }"))
                .andDo(print())
                .andExpect(status().isCreated());

        assertThat(mongoTemplate.findById("e7fcd72d-079c-45a9-bf00-ca70b7042ef2", ProductOrder.class).getState(), is(ProductOrderStateType.PENDING));
    }

    @Test
    public void testDeleteProductOrder() throws Exception {
        this.mockMvc.perform(delete("/psi-api/productOrdering/v2/productOrder/6c828c9c-04ef-4972-9a87-c1a2c684052c"))
                .andDo(print())
                .andExpect(status().isNotImplemented());
    }

    @Test
    public void testListProductOrder() throws Exception {
        ProductOrder order = new ProductOrder();
        order.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        mongoTemplate.save(order);

        this.mockMvc.perform(get("/psi-api/productOrdering/v2/productOrder"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
    }

    @Test
    public void testListProductOrderWithFields() throws Exception {
        ProductOrder order = new ProductOrder();
        order.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        order.setState(ProductOrderStateType.PENDING);
        order.setCreationDate(OffsetDateTime.now());
        mongoTemplate.save(order);

        this.mockMvc.perform(get("/psi-api/productOrdering/v2/productOrder?fields=state"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].state").exists())
                .andExpect(jsonPath("$[0].state").value("pending"))
                .andExpect(jsonPath("$[0].orderDate").doesNotExist());
    }

    @Test
    public void testListProductOrderWithItems() throws Exception {
        ProductOrder order = new ProductOrder();
        order.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
        order.setState(ProductOrderStateType.PENDING);
        order.setCreationDate(OffsetDateTime.now());
        order.addProductOrderItemItem(new ProductOrderItem().action(ItemActionType.ADD));
        mongoTemplate.save(order);

        this.mockMvc.perform(get("/psi-api/productOrdering/v2/productOrder?fields=productOrderItem"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].productOrderItem").isArray())
                .andExpect(jsonPath("$[0].productOrderItem[0].action").value("add"))
                .andExpect(jsonPath("$[0].orderDate").doesNotExist());
    }

    @Test
    public void testListProductOrderWithOffsetAndLimit() throws Exception {

        List<ProductOrder> orders = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductOrder order = new ProductOrder();
                    order.setId(UUID.randomUUID().toString());
                    orders.add(mongoTemplate.save(order));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productOrdering/v2/productOrder?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(orders.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(orders.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(orders.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(orders.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(orders.get(offset + 4).getId()));
    }

    @Test
    public void testListProductOrderWithLimitOnly() throws Exception {

        List<ProductOrder> orders = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    ProductOrder order = new ProductOrder();
                    order.setId(UUID.randomUUID().toString());
                    orders.add(mongoTemplate.save(order));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/productOrdering/v2/productOrder?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(orders.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(orders.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(orders.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(orders.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(orders.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testPatchProductOrder() throws Exception {
        hubService.registerSubscription(DemoPssData.TOPIC_ID_ORDER, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort())));
        mockBackEnd.enqueue(new MockResponse().setResponseCode(404));

        ProductOrder order = new ProductOrder();
        order.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        order.setCategory("test_category");
        mongoTemplate.save(order);

        this.mockMvc.perform(patch("/psi-api/productOrdering/v2/productOrder/28f0b4cf-40ff-49c1-9957-8d1a8e154444")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"Foobar\", \"productOrderItem\": [ { \"id\": \"100\", \"action\": \"add\", \"@type\": \"ProductOrderItem\" }], \"@type\": \"ProductOrder\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("description").value("Foobar"))
                .andExpect(jsonPath("category").value("test_category"))
                .andExpect(jsonPath("productOrderItem").isArray())
                .andExpect(jsonPath("productOrderItem.length()").value(1))
                .andExpect(jsonPath("productOrderItem[0].id").value("100"))
                .andExpect(jsonPath("productOrderItem[0].action").value("add"));
        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        assertThat(recordedRequest.getMethod(), is("POST"));
    }

    @Test
    public void testPatchProductOrderThatIsCompleted() throws Exception {
        ProductOrder order = new ProductOrder();
        order.setId("3c7c3b96-f80e-40fe-bd83-258a85fd7153");
        order.setState(ProductOrderStateType.COMPLETED);
        order.setProductOrderItem(emptyList());
        mongoTemplate.save(order);

        this.mockMvc.perform(patch("/psi-api/productOrdering/v2/productOrder/3c7c3b96-f80e-40fe-bd83-258a85fd7153")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"Foobar\", \"productOrderItem\": [ { \"id\": \"100\", \"action\": \"add\", \"@type\": \"ProductOrderItem\" }], \"@type\": \"ProductOrder\"}"))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("description").doesNotExist())
                .andExpect(jsonPath("productOrderItem").isArray())
                .andExpect(jsonPath("productOrderItem.length()").value(0));
    }

    @Test
    public void testPatchProductOrderThatIsCancelled() throws Exception {
        ProductOrder order = new ProductOrder();
        order.setId("3c7c3b96-f80e-40fe-bd83-258a85fd7153");
        order.setState(ProductOrderStateType.CANCELLED);
        mongoTemplate.save(order);

        this.mockMvc.perform(patch("/psi-api/productOrdering/v2/productOrder/3c7c3b96-f80e-40fe-bd83-258a85fd7153")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\": \"Foobar\", \"productOrderItem\": [ { \"id\": \"100\", \"action\": \"add\", \"@type\": \"ProductOrderItem\" }], \"@type\": \"ProductOrder\"}"))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testPatchProductOrderViaEventApi() throws Exception {
        ProductOrder order = new ProductOrder();
        order.setId("f63974a8-5d4f-4383-9b85-601318dbe1b0");
        order.setState(ProductOrderStateType.PENDING);
        mongoTemplate.save(order);

        List<Topic> topics = mongoTemplate.findAll(Topic.class);
        Topic orderTopic = topics.stream().filter(t -> t.getName().equals(AbstractEvent.TOPIC_ORDER))
                .findFirst()
                .orElse(null);

        assertThat(orderTopic, is(notNullValue()));

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + orderTopic.getId() + "/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"domain\": \"order\", \"eventType\": \"updated\", \"event\": { \"id\": \"f63974a8-5d4f-4383-9b85-601318dbe1b0\", \"state\": \"acknowledged\", \"@type\": \"ProductOrder\" } }"))
                .andDo(print())
                .andExpect(status().isCreated());

        assertThat(mongoTemplate.findById(order.getId(), ProductOrder.class).getState(), is(ProductOrderStateType.ACKNOWLEDGED));
    }

    @Test
    public void testRetrieveProductOrder() throws Exception {
        ProductOrder order = new ProductOrder();
        order.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(order);

        this.mockMvc.perform(get("/psi-api/productOrdering/v2/productOrder/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

}
