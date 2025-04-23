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
package com.cgi.space.psi.pss.stub.service;

import com.cgi.space.psi.common.HubSubscriptionWrapper;
import com.cgi.space.psi.common.model.HubSubscriptionCreate;
import com.cgi.space.psi.common.model.PartyRef;
import com.cgi.space.psi.common.model.RelatedPartyRefOrPartyRoleRef;
import com.cgi.space.psi.common.model.ResourceSpecification;
import com.cgi.space.psi.common.model.Topic;
import com.cgi.space.psi.pss.stub.event.PublishedEvent;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
public class HubServiceTest {

    private static final String TOPIC_ID = "74aa91ac-8ee3-4915-8249-97a65658d4cb";
    private static final String PARTY_ID = "1cd9352a-a69f-455a-b9f2-b7a037e419e1";
    private static MockWebServer mockBackEnd;

    @Autowired
    private HubService hubService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeAll
    public static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    public void createTopic() throws IOException {
        Topic topic = new Topic();
        topic.setId(TOPIC_ID);
        topic.setName("test");
        mongoTemplate.save(topic);
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(HubSubscriptionWrapper.class).all();
        mongoTemplate.remove(Topic.class).all();
    }

    @AfterAll
    public static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    public void testOnApplicationEventNoQuery() throws InterruptedException {
        hubService.registerSubscription(TOPIC_ID, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort()))
                .query(null));
        hubService.registerSubscription(TOPIC_ID, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort()))
                .query(""));
        mockBackEnd.enqueue(new MockResponse());
        mockBackEnd.enqueue(new MockResponse());

        hubService.onApplicationEvent(createPublishedEvent());
        RecordedRequest request = mockBackEnd.takeRequest(1, TimeUnit.SECONDS);
        assertThat(request.getMethod(), is("POST"));
        request = mockBackEnd.takeRequest(1, TimeUnit.SECONDS);
        assertThat(request.getMethod(), is("POST"));
    }

    @Test
    public void testOnApplicationEventSimpleQuery() throws InterruptedException {
        hubService.registerSubscription(TOPIC_ID, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort()))
                .query("event.category=FooBar"));
        hubService.registerSubscription(TOPIC_ID, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort()))
                .query("event.category=Hello"));
        hubService.registerSubscription(TOPIC_ID, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort()))
                .query("event.attachment.name=Test"));
        hubService.registerSubscription(TOPIC_ID, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort()))
                .query("event.thisDoesNotExist=Hello"));
        mockBackEnd.enqueue(new MockResponse());

        hubService.onApplicationEvent(createPublishedEvent());
        RecordedRequest request = mockBackEnd.takeRequest(1, TimeUnit.SECONDS);
        assertThat(request.getMethod(), is("POST"));
        request = mockBackEnd.takeRequest(100, TimeUnit.MILLISECONDS);
        assertThat(request, is(nullValue()));
    }

    @Test
    public void testOnApplicationEventListQuery() throws InterruptedException {
        hubService.registerSubscription(TOPIC_ID, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort()))
                .query("event.category=FooBar&event.relatedParty.partyOrPartyRole.id=" + PARTY_ID));
        hubService.registerSubscription(TOPIC_ID, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort()))
                .query("event.relatedParty.id=" + UUID.randomUUID()));
        mockBackEnd.enqueue(new MockResponse());

        hubService.onApplicationEvent(createPublishedEvent());
        RecordedRequest request = mockBackEnd.takeRequest(1, TimeUnit.SECONDS);
        assertThat(request, is(notNullValue()));
        assertThat(request.getMethod(), is("POST"));
        request = mockBackEnd.takeRequest(100, TimeUnit.MILLISECONDS);
        assertThat(request, is(nullValue()));
    }

    private PublishedEvent createPublishedEvent() {
        ResourceSpecification eventObject = new ResourceSpecification()
                .category("FooBar")
                .relatedParty(List.of(new RelatedPartyRefOrPartyRoleRef().partyOrPartyRole(new PartyRef().id(PARTY_ID))));
        return new PublishedEvent("test", "test", eventObject);
    }

}
