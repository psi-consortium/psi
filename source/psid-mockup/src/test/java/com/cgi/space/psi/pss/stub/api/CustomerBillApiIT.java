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

import com.cgi.space.psi.common.model.CustomerBill;
import com.cgi.space.psi.common.model.CustomerBillStateType;
import com.cgi.space.psi.common.model.Topic;
import com.cgi.space.psi.pss.stub.event.AbstractEvent;
import com.cgi.space.psi.pss.stub.event.PublishedEvent;
import com.cgi.space.psi.pss.stub.service.CustomerBillService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.test.util.ReflectionTestUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CustomerBillApiIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CustomerBillService customerBillService;
    private MockMvc mockMvc;

    @MockBean
    private ApplicationEventPublisher eventPublisher;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        ReflectionTestUtils.setField(customerBillService, "eventPublisher", eventPublisher);
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(CustomerBill.class).all();
    }

    @Nested
    @DisplayName("CustomerBill Create")
    class CustomerBillCreate {

        @Test
        public void testCreateCustomerBill() throws Exception {
            mockMvc.perform(post("/psi-api/customerBillManagement/v2/customerBill")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"billNo\": \"billNumber\", \"@type\": \"CustomerBill\"}"))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("id").exists())
                    .andExpect(jsonPath("billNo").value("billNumber"))
                    .andExpect(jsonPath("state").value(CustomerBillStateType.NEW.toString()));

            verify(eventPublisher, never()).publishEvent(any(PublishedEvent.class));
        }

        @Test
        public void testCreateCustomerBill_WithStateSent() throws Exception {
            mockMvc.perform(post("/psi-api/customerBillManagement/v2/customerBill")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"state\": \"sent\", \"@type\": \"CustomerBill\"}"))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("id").exists())
                    .andExpect(jsonPath("state").value(CustomerBillStateType.SENT.toString()));

            verify(eventPublisher, times(1)).publishEvent(any(PublishedEvent.class));
        }

        @Test
        public void testCreateCustomerBill_WithStateWithdrawn() throws Exception {
            mockMvc.perform(post("/psi-api/customerBillManagement/v2/customerBill")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"state\": \"withdrawn\", \"@type\": \"CustomerBill\"}"))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("id").exists())
                    .andExpect(jsonPath("state").value(CustomerBillStateType.WITHDRAWN.toString()));

            verify(eventPublisher, never()).publishEvent(any(PublishedEvent.class));
        }

        @Test
        public void testCreateCustomerBill_ViaEventApi() throws Exception {
            String id = UUID.randomUUID().toString();
            Topic billTopic = getBillTopic();
            assertThat(billTopic, is(notNullValue()));

            mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + billTopic.getId() + "/event")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(getEventAsString(AbstractEvent.TYPE_CREATED, id, CustomerBillStateType.NEW.toString())))
                    .andDo(print())
                    .andExpect(status().isCreated());

            assertThat(mongoTemplate.findById(id, CustomerBill.class).getState(), is(CustomerBillStateType.NEW));
        }

        @Test
        public void testListCustomerBill() throws Exception {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
            mongoTemplate.save(customerBill);

            mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"));
        }
    }

    @Nested
    @DisplayName("CustomerBill List")
    class CustomerBillList {

        @Test
        public void testListCustomerBillWithFields() throws Exception {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setId("6f3d5d6d-3ce1-41ee-a3e2-b71cdd63bda2");
            customerBill.setBillNo("billNumber");
            customerBill.setCategory("Test");
            customerBill.setState(CustomerBillStateType.PARTIALLYPAID);
            mongoTemplate.save(customerBill);

            mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill?fields=billNo"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].id").exists())
                    .andExpect(jsonPath("$[0].billNo").exists())
                    .andExpect(jsonPath("$[0].billNo").value("billNumber"))
                    .andExpect(jsonPath("$[0].category").doesNotExist())
                    .andExpect(jsonPath("$[0].state").doesNotExist());
        }

        @Test
        public void testListCustomerBillWithOffsetAndLimit() throws Exception {

            List<CustomerBill> customerBills = new ArrayList<>();
            IntStream.range(0, 10).forEach(num -> {
                CustomerBill customerBill = new CustomerBill();
                customerBill.setId(UUID.randomUUID().toString());
                customerBills.add(mongoTemplate.save(customerBill));
            });

            int offset = 2;
            int limit = 5;
            mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill?offset=" + offset + "&limit=" + limit))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(limit))
                    .andExpect(jsonPath("$[0].id").value(customerBills.get(offset).getId()))
                    .andExpect(jsonPath("$[1].id").value(customerBills.get(offset + 1).getId()))
                    .andExpect(jsonPath("$[2].id").value(customerBills.get(offset + 2).getId()))
                    .andExpect(jsonPath("$[3].id").value(customerBills.get(offset + 3).getId()))
                    .andExpect(jsonPath("$[4].id").value(customerBills.get(offset + 4).getId()));
        }

        @Test
        public void testListCustomerBillWithLimitOnly() throws Exception {

            List<CustomerBill> customerBills = new ArrayList<>();
            IntStream.range(0, 10).forEach(num -> {
                CustomerBill customerBill = new CustomerBill();
                customerBill.setId(UUID.randomUUID().toString());
                customerBills.add(mongoTemplate.save(customerBill));
            });

            int defaultOffset = 0;
            int limit = 5;
            mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill?limit=" + limit))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(limit))
                    .andExpect(jsonPath("$[0].id").value(customerBills.get(defaultOffset).getId()))
                    .andExpect(jsonPath("$[1].id").value(customerBills.get(defaultOffset + 1).getId()))
                    .andExpect(jsonPath("$[2].id").value(customerBills.get(defaultOffset + 2).getId()))
                    .andExpect(jsonPath("$[3].id").value(customerBills.get(defaultOffset + 3).getId()))
                    .andExpect(jsonPath("$[4].id").value(customerBills.get(defaultOffset + 4).getId()));
        }

        @Test
        public void testListCustomerBillWithBillNoFilters() throws Exception {

            List<CustomerBill> customerBills = new ArrayList<>();
            IntStream.range(0, 10).forEach(num -> {
                CustomerBill customerBill = new CustomerBill();
                customerBill.setId(UUID.randomUUID().toString());
                customerBill.setBillNo("billNo" + num);
                customerBills.add(mongoTemplate.save(customerBill));
            });

            mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill?billNo=billNo1"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].billNo").value("billNo1"));

            mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill?billNoContains=nonExistent"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));

            mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill?billNoContains=NO5"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].billNo").value("billNo5"));
        }

        @Test
        public void testListCustomerBillWithStateFilters() throws Exception {
            CustomerBill customerBill1 = new CustomerBill();
            customerBill1.setId(UUID.randomUUID().toString());
            customerBill1.setState(CustomerBillStateType.PARTIALLYPAID);
            mongoTemplate.save(customerBill1);

            CustomerBill customerBill2 = new CustomerBill();
            customerBill2.setId(UUID.randomUUID().toString());
            customerBill2.setState(CustomerBillStateType.ONHOLD);
            mongoTemplate.save(customerBill2);

            mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill?state=nonExistent"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(0));

            mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill?state=onHold"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(1))
                    .andExpect(jsonPath("$[0].state").value("onHold"));
        }
    }

    @Nested
    @DisplayName("CustomerBill Patch")
    class CustomerBillPatch {

        @Test
        public void testPatchCustomerBill() throws Exception {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
            customerBill.setState(CustomerBillStateType.ONHOLD);
            mongoTemplate.save(customerBill);

            mockMvc.perform(patch("/psi-api/customerBillManagement/v2/customerBill/28f0b4cf-40ff-49c1-9957-8d1a8e154444")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"state\": \"validated\", \"@type\": \"CustomerBill\"}"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("state").value("validated"));

            verify(eventPublisher, never()).publishEvent(any(PublishedEvent.class));
        }

        @Test
        public void testPatchCustomerBill_WithoutStateChange() throws Exception {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setId(UUID.randomUUID().toString());
            customerBill.setState(CustomerBillStateType.SENT);
            mongoTemplate.save(customerBill);

            mockMvc.perform(patch("/psi-api/customerBillManagement/v2/customerBill/" + customerBill.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"state\": \"sent\", \"@type\": \"CustomerBill\"}"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("state").value("sent"));

            verify(eventPublisher, never()).publishEvent(any(PublishedEvent.class));
        }

        @Test
        public void testPatchCustomerBill_WithStateSentOnNew() throws Exception {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setId(UUID.randomUUID().toString());
            customerBill.setState(CustomerBillStateType.NEW);
            mongoTemplate.save(customerBill);

            mockMvc.perform(patch("/psi-api/customerBillManagement/v2/customerBill/" + customerBill.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"state\": \"sent\", \"@type\": \"CustomerBill\"}"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("state").value(CustomerBillStateType.SENT.toString()));

            verify(eventPublisher, times(1)).publishEvent(any(PublishedEvent.class));
        }

        @Test
        public void testPatchCustomerBill_WithStateWithdrawnOnNew() throws Exception {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setId(UUID.randomUUID().toString());
            customerBill.setState(CustomerBillStateType.NEW);
            mongoTemplate.save(customerBill);

            mockMvc.perform(patch("/psi-api/customerBillManagement/v2/customerBill/" + customerBill.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"state\": \"withdrawn\", \"@type\": \"CustomerBill\"}"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("state").value(CustomerBillStateType.WITHDRAWN.toString()));

            verify(eventPublisher, never()).publishEvent(any(PublishedEvent.class));
        }

        @Test
        public void testPatchCustomerBill_WithStateWithdrawnOnSent() throws Exception {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setId(UUID.randomUUID().toString());
            customerBill.setState(CustomerBillStateType.SENT);
            mongoTemplate.save(customerBill);

            mockMvc.perform(patch("/psi-api/customerBillManagement/v2/customerBill/" + customerBill.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"state\": \"withdrawn\", \"@type\": \"CustomerBill\"}"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("state").value(CustomerBillStateType.WITHDRAWN.toString()));

            verify(eventPublisher, times(1)).publishEvent(any(PublishedEvent.class));
        }

        @Test
        public void testPatchCustomerBill_WithStateSettledOnSent() throws Exception {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setId(UUID.randomUUID().toString());
            customerBill.setState(CustomerBillStateType.SENT);
            mongoTemplate.save(customerBill);

            mockMvc.perform(patch("/psi-api/customerBillManagement/v2/customerBill/" + customerBill.getId())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"state\": \"settled\", \"@type\": \"CustomerBill\"}"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("state").value(CustomerBillStateType.SETTLED.toString()));

            verify(eventPublisher, times(1)).publishEvent(any(PublishedEvent.class));
        }

        @Test
        public void testPatchCustomerBill_ViaEventApi() throws Exception {
            CustomerBill customerBill = new CustomerBill();
            customerBill.setId(UUID.randomUUID().toString());
            customerBill.setState(CustomerBillStateType.ONHOLD);
            mongoTemplate.save(customerBill);
            Topic billTopic = getBillTopic();

            mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + billTopic.getId() + "/event")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(getEventAsString(AbstractEvent.TYPE_UPDATED, customerBill.getId(), CustomerBillStateType.VALIDATED.toString())))
                    .andDo(print())
                    .andExpect(status().isCreated());

            assertThat(mongoTemplate.findById(customerBill.getId(), CustomerBill.class).getState(), is(CustomerBillStateType.VALIDATED));
        }
    }

    @Test
    public void testRetrieveCustomerBill() throws Exception {
        CustomerBill customerBill = new CustomerBill();
        customerBill.setId("173432a7-74a6-4ae2-8701-f4fd178444b4");
        mongoTemplate.save(customerBill);

        this.mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill/173432a7-74a6-4ae2-8701-f4fd178444b4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("173432a7-74a6-4ae2-8701-f4fd178444b4"));
    }

    @Test
    public void testRetrieveCustomerBillWithFields() throws Exception {
        CustomerBill customerBill = new CustomerBill();
        customerBill.setId("c37baf9c-8770-4594-8424-cd5767358ea8");
        customerBill.setBillNo("billNumber");
        customerBill.setCategory("Test");
        customerBill.setState(CustomerBillStateType.ONHOLD);
        mongoTemplate.save(customerBill);

        this.mockMvc.perform(get("/psi-api/customerBillManagement/v2/customerBill/c37baf9c-8770-4594-8424-cd5767358ea8?fields=billNo,state"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("billNo").exists())
                .andExpect(jsonPath("billNo").value("billNumber"))
                .andExpect(jsonPath("category").doesNotExist())
                .andExpect(jsonPath("state").exists())
                .andExpect(jsonPath("state").value("onHold"));
    }

    @Test
    public void testWithdrawCustomerBill() throws Exception {
        CustomerBill customerBill = new CustomerBill();
        customerBill.setId(UUID.randomUUID().toString());
        customerBill.setState(CustomerBillStateType.SENT);
        mongoTemplate.save(customerBill);

        this.mockMvc.perform(delete("/psi-api/customerBillManagement/v2/customerBill/" + customerBill.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
        verify(eventPublisher, times(1)).publishEvent(any(PublishedEvent.class));
    }

    @Test
    public void testWithdrawCustomerBill_WithNew() throws Exception {
        CustomerBill customerBill = new CustomerBill();
        customerBill.setId(UUID.randomUUID().toString());
        customerBill.setState(CustomerBillStateType.NEW);
        mongoTemplate.save(customerBill);

        this.mockMvc.perform(delete("/psi-api/customerBillManagement/v2/customerBill/" + customerBill.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$").doesNotExist());
        verify(eventPublisher, never()).publishEvent(any(PublishedEvent.class));
    }

    @Test
    public void testWithdrawCustomerBill_WithUnknownId() throws Exception {
        this.mockMvc.perform(delete("/psi-api/customerBillManagement/v2/customerBill/" + UUID.randomUUID()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testWithdrawCustomerBill_ViaEventApi() throws Exception {
        CustomerBill customerBill = new CustomerBill();
        customerBill.setId(UUID.randomUUID().toString());
        customerBill.setState(CustomerBillStateType.NEW);
        mongoTemplate.save(customerBill);
        Topic billTopic = getBillTopic();

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + billTopic.getId() + "/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getEventAsString(AbstractEvent.TYPE_DELETED, customerBill.getId(), CustomerBillStateType.NEW.toString())))
                .andDo(print())
                .andExpect(status().isCreated());

        // The provided state of the bill is ignored. The deletion results in state withdrawn
        assertThat(mongoTemplate.findById(customerBill.getId(), CustomerBill.class).getState(), is(CustomerBillStateType.WITHDRAWN));
    }

    // Helper methods

    private Topic getBillTopic() {
        List<Topic> topics = mongoTemplate.findAll(Topic.class);
        return topics.stream()
                .filter(t -> t.getName().equals(AbstractEvent.TOPIC_BILL))
                .findFirst()
                .orElse(null);
    }

    private String getEventAsString(String type, String id, String state) {
        return "{"
                + "\"domain\": \"bill\", "
                + "\"eventType\": \"" + type + "\", "
                + "\"event\": { "
                + "  \"id\": \"" + id + "\", "
                + "  \"state\": \"" + state + "\", "
                + "  \"@type\": \"CustomerBill\""
                + "}}";
    }
}
