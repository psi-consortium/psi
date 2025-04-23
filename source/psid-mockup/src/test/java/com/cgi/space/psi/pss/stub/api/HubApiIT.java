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

import com.cgi.space.psi.common.HubSubscriptionWrapper;
import com.cgi.space.psi.common.model.HubSubscription;
import com.cgi.space.psi.common.model.Topic;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class HubApiIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // remove explicitly the topics created on application startup for demo purposes
        mongoTemplate.remove(Topic.class).all();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(HubSubscriptionWrapper.class).all();
        mongoTemplate.remove(Topic.class).all();
    }

    @Test
    public void testSubscribeCallbackToHub() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + topicId + "/hub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"callback\": \"http://callback.url\" }"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("callback").value("http://callback.url"));
    }

    @Test
    public void testUnsubscribeCallbackFromHub() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        HubSubscription subscription = new HubSubscription();
        subscription.setId("9826892f-f694-4c3f-a012-f2fb85be9ef1");
        subscription.setCallback(URI.create("http://callback.url"));
        HubSubscriptionWrapper wrapper = new HubSubscriptionWrapper(subscription, topicId);
        mongoTemplate.save(wrapper);

        this.mockMvc.perform(delete("/psi-api/eventManagement/v2/topic/" + topicId + "/hub/9826892f-f694-4c3f-a012-f2fb85be9ef1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testListAllTopics() throws Exception {
        Topic firstTopic = new Topic();
        firstTopic.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        firstTopic.setName("orders");

        Topic secondTopic = new Topic();
        secondTopic.setId("b25767ba-2033-40a8-9132-1a7370561e2f");
        secondTopic.setName("inquiries");

        mongoTemplate.save(firstTopic);
        mongoTemplate.save(secondTopic);

        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("28f0b4cf-40ff-49c1-9957-8d1a8e154444"))
                .andExpect(jsonPath("$[0].name").value("orders"))
                .andExpect(jsonPath("$[1].id").value("b25767ba-2033-40a8-9132-1a7370561e2f"))
                .andExpect(jsonPath("$[1].name").value("inquiries"));
    }

    @Test
    public void testListAllTopicsWithFields() throws Exception {
        Topic topic = new Topic();
        topic.setId("28f0b4cf-40ff-49c1-9957-8d1a8e154444");
        topic.setName("orders");
        topic.setContentQuery("contentOrdersQuery");

        mongoTemplate.save(topic);

        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic?fields=name,contentQuery"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].name").exists())
                .andExpect(jsonPath("$[0].name").value("orders"))
                .andExpect(jsonPath("$[0].contentQuery").exists())
                .andExpect(jsonPath("$[0].contentQuery").value("contentOrdersQuery"));
    }

    @Test
    public void testListTopicsWithOffsetAndLimit() throws Exception {
        List<Topic> topics = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Topic topic = new Topic();
                    topic.setId(UUID.randomUUID().toString());
                    topics.add(mongoTemplate.save(topic));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(topics.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(topics.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(topics.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(topics.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(topics.get(offset + 4).getId()));
    }

    @Test
    public void testListTopicsWithLimitOnly() throws Exception {
        List<Topic> topics = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    Topic topic = new Topic();
                    topic.setId(UUID.randomUUID().toString());
                    topics.add(mongoTemplate.save(topic));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(topics.get(defaultOffset).getId()))
                .andExpect(jsonPath("$[1].id").value(topics.get(defaultOffset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(topics.get(defaultOffset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(topics.get(defaultOffset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(topics.get(defaultOffset + 4).getId()));
    }

    @Test
    public void testListAllHubSubscriptions() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        HubSubscription firstSubscription = new HubSubscription();
        firstSubscription.setId("c9198cff-9b2d-4aa5-ad18-ffe54f22cd4a");
        firstSubscription.setCallback(URI.create("http://callback1.url"));
        mongoTemplate.save(new HubSubscriptionWrapper(firstSubscription, topicId));

        HubSubscription secondSubscription = new HubSubscription();
        secondSubscription.setId("d604bec9-1f69-42e6-832a-035bce59a6cb");
        secondSubscription.setCallback(URI.create("http://callback2.url"));
        mongoTemplate.save(new HubSubscriptionWrapper(secondSubscription, topicId));

        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/hub"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("c9198cff-9b2d-4aa5-ad18-ffe54f22cd4a"))
                .andExpect(jsonPath("$[0].callback").value("http://callback1.url"))
                .andExpect(jsonPath("$[1].id").value("d604bec9-1f69-42e6-832a-035bce59a6cb"))
                .andExpect(jsonPath("$[1].callback").value("http://callback2.url"));
    }

    @Test
    public void testListAllHubSubscriptionsWithFields() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        HubSubscription subscription = new HubSubscription();
        subscription.setId("c9198cff-9b2d-4aa5-ad18-ffe54f22cd4a");
        subscription.setCallback(URI.create("http://callback1.url"));
        subscription.setQuery("eventType=OrderCreateEvent");
        mongoTemplate.save(new HubSubscriptionWrapper(subscription, topicId));

        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/hub?fields=query"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].callback").doesNotExist())
                .andExpect(jsonPath("$[0].query").value("eventType=OrderCreateEvent"));
    }

    @Test
    public void testHubSubscriptionsWithOffsetAndLimit() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        List<HubSubscriptionWrapper> hubSubscriptions = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    HubSubscription subscription = new HubSubscription();
                    subscription.setId(UUID.randomUUID().toString());
                    subscription.setCallback(URI.create("http://callback" + num + ".url"));
                    hubSubscriptions.add(mongoTemplate.save(new HubSubscriptionWrapper(subscription, topicId)));
                }
        );

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/hub?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(hubSubscriptions.get(offset).getHubSubscription().getId()))
                .andExpect(jsonPath("$[1].id").value(hubSubscriptions.get(offset + 1).getHubSubscription().getId()))
                .andExpect(jsonPath("$[2].id").value(hubSubscriptions.get(offset + 2).getHubSubscription().getId()))
                .andExpect(jsonPath("$[3].id").value(hubSubscriptions.get(offset + 3).getHubSubscription().getId()))
                .andExpect(jsonPath("$[4].id").value(hubSubscriptions.get(offset + 4).getHubSubscription().getId()));
    }

    @Test
    public void testHubSubscriptionsWithLimitOnly() throws Exception {
        String topicId = "0ef6a3cd-d64f-402e-9124-87ab6643cd8a";
        Topic topic = new Topic();
        topic.setId(topicId);
        mongoTemplate.save(topic);

        List<HubSubscriptionWrapper> hubSubscriptions = new ArrayList<>();
        IntStream.range(0, 10).forEach(num -> {
                    HubSubscription subscription = new HubSubscription();
                    subscription.setId(UUID.randomUUID().toString());
                    subscription.setCallback(URI.create("http://callback" + num + ".url"));
                    hubSubscriptions.add(mongoTemplate.save(new HubSubscriptionWrapper(subscription, topicId)));
                }
        );

        int defaultOffset = 0;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/eventManagement/v2/topic/" + topicId + "/hub?limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(hubSubscriptions.get(defaultOffset).getHubSubscription().getId()))
                .andExpect(jsonPath("$[1].id").value(hubSubscriptions.get(defaultOffset + 1).getHubSubscription().getId()))
                .andExpect(jsonPath("$[2].id").value(hubSubscriptions.get(defaultOffset + 2).getHubSubscription().getId()))
                .andExpect(jsonPath("$[3].id").value(hubSubscriptions.get(defaultOffset + 3).getHubSubscription().getId()))
                .andExpect(jsonPath("$[4].id").value(hubSubscriptions.get(defaultOffset + 4).getHubSubscription().getId()));
    }
}
