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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cgi.space.psi.common.HubSubscriptionWrapper;
import com.cgi.space.psi.common.model.CustomerCharacteristic;
import com.cgi.space.psi.common.model.CustomerInquiry;
import com.cgi.space.psi.common.model.HubSubscription;
import com.cgi.space.psi.common.model.HubSubscriptionCreate;
import com.cgi.space.psi.common.model.InquiredProduct;
import com.cgi.space.psi.common.model.InquiredProvider;
import com.cgi.space.psi.common.model.InquiryStateType;
import com.cgi.space.psi.common.model.Money;
import com.cgi.space.psi.common.model.ProductOffering;
import com.cgi.space.psi.common.model.ProductOfferingPrice;
import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.ProductSpecificationRef;
import com.cgi.space.psi.common.model.ResponseTime;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.demodata.DemoPssData;
import com.cgi.space.psi.pss.stub.service.HubService;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

@SpringBootTest
public class PssCustomerInquiryApiIT {

    private static MockWebServer mockBackEnd;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private HubService hubService;
    private MockMvc mockMvc;

    private HubSubscription hubSubscription;

    @BeforeEach
    public void initialize() throws IOException {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterEach
    public void release() throws IOException {
        mongoTemplate.remove(CustomerInquiry.class).all();
        mongoTemplate.remove(HubSubscriptionWrapper.class).all();

        if (hubSubscription != null) {
            hubService.unregisterSubscription(DemoPssData.TOPIC_ID_INQUIRY, hubSubscription.getId());
            hubSubscription = null;
        }

        mockBackEnd.shutdown();
    }

    @DynamicPropertySource
    static void registerActiveProfile(DynamicPropertyRegistry registry) {
        registry.add("spring.profiles.active", () -> Profiles.PSS);
        registry.add("psi.pss.inquiry.maxResponseTime", () -> "PT5S");
    }

    @Test
    public void testCreateCustomerInquiry() throws Exception {
        hubSubscription = hubService.registerSubscription(DemoPssData.TOPIC_ID_INQUIRY, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort())));
        mockBackEnd.enqueue(new MockResponse().setResponseCode(200));

        this.mockMvc.perform(post("/psi-api/customerInquiry/v2/customerInquiry")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content("{\"customerProfile\": [{\"name\": \"foo\", \"value\": \"bar\"}], \"inquiredProvider\": ["
                        + "{\"href\": \"" + URI.create("http://localhost:" + mockBackEnd.getPort()) + "/test\"},"
                        + "{\"href\": \"https://foo.bar.com/test\"}"
                        + "], \"@type\": \"CustomerInquiry\"}"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("state").value("pending"))
                .andExpect(jsonPath("customerProfile[0].name").value("foo"))
                .andExpect(jsonPath("customerProfile[0].value").value("bar"));

        RecordedRequest publishedCreateRequest = mockBackEnd.takeRequest();
        assertThat(publishedCreateRequest.getMethod(), is("POST"));
        String body = publishedCreateRequest.getBody().readUtf8();
        assertThat(body, containsString(InquiryStateType.PENDING.toString()));
        assertThat(body, not(containsString("foo.bar.com")));

        // 5 seconds maxResponseTime + 1s buffer
        Thread.sleep(6_000);
        CustomerInquiry result = mongoTemplate.findAll(CustomerInquiry.class).get(0);
        // verify that after the max response time is reached, the overall state of the inquiry is COMPLETED
        assertThat(result.getState(), is(InquiryStateType.COMPLETED));
    }

    @Test
    public void testUpdateCustomerInquiryViaEventApi_whenProviderStateCompleted() throws Exception {
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId("f63974a8-5d4f-4383-9b85-601318dbe1b0");
        inquiry.setState(InquiryStateType.PENDING);
        inquiry.setResponseTime(new ResponseTime().maximum(OffsetDateTime.now().plusSeconds(5)));

        InquiredProvider provider = new InquiredProvider();
        provider.setHref(URI.create("http://localhost/psi-api/provider/123"));
        inquiry.setInquiredProvider(List.of(provider));
        mongoTemplate.save(inquiry);

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + DemoPssData.TOPIC_ID_INQUIRY + "/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{ \"domain\": \"inquiry\", \"eventType\": \"updated\", \"event\": { \"id\": \"f63974a8-5d4f-4383-9b85-601318dbe1b0\", \"inquiredProvider\": [{ \"state\": \"completed\", \"href\": \"http://localhost/psi-api/provider/123\" }], \"@type\": \"CustomerInquiry\" }}"))
                .andDo(print())
                .andExpect(status().isCreated());

        CustomerInquiry result = mongoTemplate.findById(inquiry.getId(), CustomerInquiry.class);
        // the internal state of the provider is updated
        assertThat(result.getInquiredProvider().get(0).getState(), is(InquiryStateType.COMPLETED));
        // the overall state of the inquiry is also updated to COMPLETED
        assertThat(result.getState(), is(InquiryStateType.COMPLETED));
    }

    @Test
    public void testUpdateCustomerInquiryViaEventApi_whenProviderStateInProgress() throws Exception {
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId("f63974a8-5d4f-4383-9b85-601318dbe1b0");
        inquiry.setState(InquiryStateType.PENDING);
        inquiry.setResponseTime(new ResponseTime().maximum(OffsetDateTime.now().plusSeconds(5)));

        InquiredProvider provider = new InquiredProvider();
        provider.setHref(URI.create("http://localhost/psi-api/provider/123"));
        inquiry.setInquiredProvider(List.of(provider));
        mongoTemplate.save(inquiry);

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + DemoPssData.TOPIC_ID_INQUIRY + "/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{ \"domain\": \"inquiry\", \"eventType\": \"updated\", \"event\": { \"id\": \"f63974a8-5d4f-4383-9b85-601318dbe1b0\", \"inquiredProvider\": [{ \"state\": \"inProgress\", \"href\": \"http://localhost/psi-api/provider/123\" }], \"@type\": \"CustomerInquiry\" }}"))
                .andDo(print())
                .andExpect(status().isCreated());

        CustomerInquiry result = mongoTemplate.findById(inquiry.getId(), CustomerInquiry.class);
        // the internal state of the provider is updated
        assertThat(result.getInquiredProvider().get(0).getState(), is(InquiryStateType.INPROGRESS));
        // the overall state of the provider changes to INPROGRESS
        assertThat(result.getState(), is(InquiryStateType.INPROGRESS));
    }

    @Test
    public void testUpdateCustomerInquiryViaEventApi_whenProviderRespondsTooLate() throws Exception {
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId("5415bbe3-d0c5-4e39-9975-2c30a900b2fe");
        inquiry.setState(InquiryStateType.PENDING);
        inquiry.setResponseTime(new ResponseTime().maximum(OffsetDateTime.now().minusSeconds(5)));

        InquiredProvider provider = new InquiredProvider();
        provider.setHref(URI.create("http://localhost/psi-api/provider/123"));
        inquiry.setInquiredProvider(List.of(provider));
        mongoTemplate.save(inquiry);

        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + DemoPssData.TOPIC_ID_INQUIRY + "/event")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{ \"domain\": \"inquiry\", \"eventType\": \"updated\", \"event\": { \"id\": \"5415bbe3-d0c5-4e39-9975-2c30a900b2fe\", \"inquiredProvider\": [{ \"state\": \"completed\", \"href\": \"http://localhost/psi-api/provider/123\" }], \"@type\": \"CustomerInquiry\" }}"))
                .andDo(print())
                .andExpect(status().isCreated());

        CustomerInquiry result = mongoTemplate.findById(inquiry.getId(), CustomerInquiry.class);
        // the PSS marks the internal state of the provider as CANCELLED, because the response came late
        assertThat(result.getInquiredProvider().get(0).getState(), is(InquiryStateType.CANCELLED));
        // the overall state of the inquiry is completed because the maximum response time was exceeded
        assertThat(result.getState(), is(InquiryStateType.COMPLETED));
    }

    private CustomerInquiry createCustomerInquiry() {
        final String inquiryId = UUID.randomUUID().toString();
        OffsetDateTime responseTimeMaximum = OffsetDateTime.now().plusSeconds(5);

        CustomerCharacteristic customerCharacteristic = new CustomerCharacteristic();
        customerCharacteristic.setName("characteristic mock name 1");
        customerCharacteristic.setValue("characteristic mock value 1");

        InquiredProduct inquiredFeature = new InquiredProduct();
        inquiredFeature.setName("Inquired Feature Name Mock");

        InquiredProvider inquiredProvider = new InquiredProvider();
        inquiredProvider.setName("Provider Name Mock1");
        inquiredProvider.setHref(URI.create("http://localhost:" + mockBackEnd.getPort() + "/mock"));

        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId(inquiryId);
        inquiry.setBundlesOnly(true);
        inquiry.setCustomerProfile(List.of(customerCharacteristic));
        inquiry.setInquiredProduct(List.of(inquiredFeature));
        inquiry.setInquiredProvider(List.of(inquiredProvider));
        inquiry.setState(InquiryStateType.PENDING);
        inquiry.setResponseTime(new ResponseTime().maximum(responseTimeMaximum));
        return inquiry;
    }

    @Test
    public void testUpdateCustomerInquiry_WithoutInquiredProviderChange() throws Exception {
        CustomerInquiry inquiry = createCustomerInquiry();
        mongoTemplate.save(inquiry);

        this.mockMvc.perform(patch("/psi-api/customerInquiry/v2/customerInquiry/" + inquiry.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content("{"
                        + "\"customerProfile\": [{\"name\": \"foo\", \"value\": \"bar\"}], "
                        + "\"bundlesOnly\": false, "
                        + "\"@type\": \"CustomerInquiry\""
                        + "}"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("id").value(inquiry.getId()))
                .andExpect(jsonPath("bundlesOnly").value(true))
                .andExpect(jsonPath("customerProfile").isArray())
                .andExpect(jsonPath("customerProfile.length()").value(1))
                .andExpect(jsonPath("customerProfile[0].name").value(inquiry.getCustomerProfile().get(0).getName()))
                .andExpect(jsonPath("customerProfile[0].value").value(inquiry.getCustomerProfile().get(0).getValue()))
                .andExpect(jsonPath("responseTime.maximum").exists())
                .andExpect(jsonPath("inquiredProduct").exists())
                .andExpect(jsonPath("inquiredProduct.length()").value(1))
                .andExpect(jsonPath("inquiredProduct[0].name").value(inquiry.getInquiredProduct().get(0).getName()))
                .andExpect(jsonPath("inquiredProvider").exists())
                .andExpect(jsonPath("inquiredProvider.length()").value(1))
                .andExpect(jsonPath("inquiredProvider[0].name").value(inquiry.getInquiredProvider().get(0).getName()));
    }

    @Test
    public void testUpdateCustomerInquiry_WithInquiredProviderChange() throws Exception {
        CustomerInquiry inquiry = createCustomerInquiry();
        final String inquiredProviderName1 = inquiry.getInquiredProvider().get(0).getName();
        mongoTemplate.save(inquiry);

        hubSubscription = hubService.registerSubscription(DemoPssData.TOPIC_ID_INQUIRY, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort())));
        mockBackEnd.enqueue(new MockResponse().setResponseCode(200));
        mockBackEnd.enqueue(new MockResponse().setResponseCode(200));

        this.mockMvc.perform(patch("/psi-api/customerInquiry/v2/customerInquiry/" + inquiry.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content("{"
                        + "\"bundlesOnly\": false, "
                        + "\"inquiredProvider\": ["
                        + "  {\"name\": \"Update mock name 1\", \"href\": \"" + URI.create("http://localhost:" + mockBackEnd.getPort()) + "/test\"},"
                        + "  {\"name\": \"Update mock name 2\", \"href\": \"https://foo.bar.com/test\"}"
                        + "], "
                        + "\"@type\": \"CustomerInquiry\""
                        + "}"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("id").value(inquiry.getId()))
                .andExpect(jsonPath("state").value(InquiryStateType.PENDING.toString()))
                .andExpect(jsonPath("bundlesOnly").value(true))
                .andExpect(jsonPath("inquiredProvider").exists())
                .andExpect(jsonPath("inquiredProvider.length()").value(2));

        // Hub sends a cancel-event to the removed provider from the inquiredProvider list (if the host is registered on the hub - see above)
        RecordedRequest publishedCancelRequest = mockBackEnd.takeRequest();
        assertThat(publishedCancelRequest.getMethod(), is("POST"));
        String cancelBody = publishedCancelRequest.getBody().readUtf8();
        assertThat(cancelBody, containsString(inquiredProviderName1));
        assertThat(cancelBody, containsString(InquiryStateType.CANCELLED.toString()));

        // Hub sends an update event to the new (and unchanged) provider from the inquiredProvider list (if the host is registered on the hub - see above)
        RecordedRequest publishedCreateRequest = mockBackEnd.takeRequest();
        assertThat(publishedCreateRequest.getMethod(), is("POST"));
        String updateBody = publishedCreateRequest.getBody().readUtf8();
        assertThat(updateBody, containsString("Update mock name 1"));
        assertThat(updateBody, containsString(InquiryStateType.PENDING.toString()));
        assertThat(updateBody, not(containsString("Update mock name 2")));
    }

    @Test
    public void testUpdateCustomerInquiry_WithInvalidId() throws Exception {
        final String inquiryId = UUID.randomUUID().toString();

        this.mockMvc.perform(patch("/psi-api/customerInquiry/v2/customerInquiry/" + inquiryId)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content("{ \"bundlesOnly\": false, \"@type\": \"CustomerInquiry\" }"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateCustomerInquiry_WithCompletedInquiry() throws Exception {
        final String inquiryId = UUID.randomUUID().toString();
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId(inquiryId);
        inquiry.setState(InquiryStateType.COMPLETED);
        mongoTemplate.save(inquiry);

        this.mockMvc.perform(patch("/psi-api/customerInquiry/v2/customerInquiry/" + inquiryId)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content("{ \"bundlesOnly\": false, \"@type\": \"CustomerInquiry\" }"))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testUpdateCustomerInquiry_WithCancelledInquiry() throws Exception {
        final String inquiryId = UUID.randomUUID().toString();
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId(inquiryId);
        inquiry.setState(InquiryStateType.CANCELLED);
        mongoTemplate.save(inquiry);

        this.mockMvc.perform(patch("/psi-api/customerInquiry/v2/customerInquiry/" + inquiryId)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(Charset.defaultCharset())
                .content("{ \"bundlesOnly\": false, \"@type\": \"CustomerInquiry\" }"))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testDeleteCustomerInquiry() throws Exception {
        CustomerInquiry inquiry = createCustomerInquiry();
        mongoTemplate.save(inquiry);
        final String inquiredProviderName1 = inquiry.getInquiredProvider().get(0).getName();

        hubSubscription = hubService.registerSubscription(DemoPssData.TOPIC_ID_INQUIRY, new HubSubscriptionCreate()
                .callback(URI.create("http://localhost:" + mockBackEnd.getPort())));
        mockBackEnd.enqueue(new MockResponse().setResponseCode(200));

        this.mockMvc.perform(delete("/psi-api/customerInquiry/v2/customerInquiry/" + inquiry.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        // Hub sends a cancel-event to the removed provider (if the host is registered on the hub - see above)
        RecordedRequest publishedCancelRequest = mockBackEnd.takeRequest();
        assertThat(publishedCancelRequest.getMethod(), is("POST"));
        String cancelBody = publishedCancelRequest.getBody().readUtf8();
        assertThat(cancelBody, containsString(inquiredProviderName1));
        assertThat(cancelBody, containsString(InquiryStateType.CANCELLED.toString()));

        this.mockMvc.perform(get("/psi-api/customerInquiry/v2/customerInquiry/" + inquiry.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(inquiry.getId()))
                .andExpect(jsonPath("state").value(InquiryStateType.CANCELLED.toString()))
                .andExpect(jsonPath("inquiredProvider[0].state").value(InquiryStateType.CANCELLED.toString()));
    }

    @Test
    public void testDeleteCustomerInquiry_WithUnknownId() throws Exception {
        final String inquiryId = UUID.randomUUID().toString();

        this.mockMvc.perform(delete("/psi-api/customerInquiry/v2/customerInquiry/" + inquiryId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRetrieveCustomerInquiry() throws Exception {
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId("1b4c08ab-1cfe-4db9-8aa3-c20991ac1f20");
        inquiry.setState(InquiryStateType.INPROGRESS);
        mongoTemplate.save(inquiry);

        this.mockMvc.perform(get("/psi-api/customerInquiry/v2/customerInquiry/1b4c08ab-1cfe-4db9-8aa3-c20991ac1f20"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("1b4c08ab-1cfe-4db9-8aa3-c20991ac1f20"))
                .andExpect(jsonPath("state").value("inProgress"));
    }

    @Test
    public void testRetrieveCustomerInquiry_WithUnknownId() throws Exception {
        final String inquiryId = UUID.randomUUID().toString();

        this.mockMvc.perform(get("/psi-api/customerInquiry/v2/customerInquiry/" + inquiryId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRetrieveCustomerInquiryOfferings() throws Exception {
        String inquiredProductId = UUID.randomUUID().toString();
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId("4ac91f2c-25bd-42fe-a5c9-26c1b416df17");
        inquiry.setState(InquiryStateType.COMPLETED);
        inquiry.addInquiredProductItem(new InquiredProduct()
                .id(inquiredProductId)
                .name("InquiredProductItem-Name"));
        mongoTemplate.save(inquiry);

        // ProductSpec 1
        String productSpec1Id = UUID.randomUUID().toString();
        mongoTemplate.save(new ProductSpecification().id(productSpec1Id));

        // ProductSpec 2 with PoductOffering
        String productSpec2Id = UUID.randomUUID().toString();
        mongoTemplate.save(new ProductSpecification().id(productSpec2Id));
        String productOfferingId = UUID.randomUUID().toString();
        Float price = 1234.56F;
        mongoTemplate.save(new ProductOffering()
                .id(productOfferingId)
                .productSpecification(new ProductSpecificationRef().id(productSpec2Id))
                .productOfferingPrice(List.of(new ProductOfferingPrice()
                        .priceType("recurring")
                        .recurringChargePeriodType("Month")
                        .recurringChargePeriodLength(1)
                        .price(new Money().unit("EUR").value(price)))));

        this.mockMvc.perform(get("/psi-api/customerInquiry/v2/customerInquiry/4ac91f2c-25bd-42fe-a5c9-26c1b416df17/results"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))

                .andExpect(jsonPath("$[0].priority").value(1))
                .andExpect(jsonPath("$[0].note").isArray())
                .andExpect(jsonPath("$[0].productSpecification").isArray())
                .andExpect(jsonPath("$[0].productSpecification.length()").value(1))
                .andExpect(jsonPath("$[0].productSpecification[0].id").value(productSpec1Id))
                .andExpect(jsonPath("$[0].productOffering.length()").value(0))
                .andExpect(jsonPath("$[0].inquiredProductRelationship").isArray())
                .andExpect(jsonPath("$[0].inquiredProductRelationship.length()").value(1))
                .andExpect(jsonPath("$[0].inquiredProductRelationship[0].inquiredProductRef.id").value(inquiredProductId))
                .andExpect(jsonPath("$[0].inquiredProductRelationship[0].productSpecificationRef.id").value(productSpec1Id))
                .andExpect(jsonPath("$[0].inquiredProductRelationship[0].totalPrice").doesNotExist())

                .andExpect(jsonPath("$[1].priority").value(2))
                .andExpect(jsonPath("$[1].note").doesNotExist())
                .andExpect(jsonPath("$[1].productSpecification").isArray())
                .andExpect(jsonPath("$[1].productSpecification.length()").value(1))
                .andExpect(jsonPath("$[1].productSpecification[0].id").value(productSpec2Id))
                .andExpect(jsonPath("$[1].productOffering.length()").value(1))
                .andExpect(jsonPath("$[1].productOffering[0].id").value(productOfferingId))
                .andExpect(jsonPath("$[1].productOffering[0].productSpecification.id").value(productSpec2Id))
                .andExpect(jsonPath("$[1].inquiredProductRelationship").isArray())
                .andExpect(jsonPath("$[1].inquiredProductRelationship.length()").value(1))
                .andExpect(jsonPath("$[1].inquiredProductRelationship[0].inquiredProductRef.id").value(inquiredProductId))
                .andExpect(jsonPath("$[1].inquiredProductRelationship[0].productSpecificationRef.id").value(productSpec2Id))
                .andExpect(jsonPath("$[1].inquiredProductRelationship[0].productOfferingRef.id").value(productOfferingId))
                .andExpect(jsonPath("$[1].inquiredProductRelationship[0].totalPrice").isArray())
                .andExpect(jsonPath("$[1].inquiredProductRelationship[0].totalPrice.length()").value(1))
                .andExpect(jsonPath("$[1].inquiredProductRelationship[0].totalPrice[0].price.dutyFreeAmount.value").value(price.toString()));
    }

    @Test
    public void testRetrieveResultNoContent() throws Exception {
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId("e2f726ba-1f6f-453a-8bd6-1c53c73207c3");
        inquiry.setState(InquiryStateType.INPROGRESS);
        mongoTemplate.save(inquiry);

        this.mockMvc.perform(get("/psi-api/customerInquiry/v2/customerInquiry/e2f726ba-1f6f-453a-8bd6-1c53c73207c3/results"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testRetrieveResultGone() throws Exception {
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setId("324357a0-f4e2-425f-a483-a8d9cde5e708");
        inquiry.setState(InquiryStateType.CANCELLED);
        mongoTemplate.save(inquiry);

        this.mockMvc.perform(get("/psi-api/customerInquiry/v2/customerInquiry/324357a0-f4e2-425f-a483-a8d9cde5e708/results"))
                .andDo(print())
                .andExpect(status().isGone());
    }

    @Test
    public void testRetrieveResultNotFound() throws Exception {
        this.mockMvc.perform(get("/psi-api/customerInquiry/v2/customerInquiry/2cd6e627-95cb-4004-9667-f9e37f57ee06/results"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
