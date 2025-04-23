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

import com.cgi.space.psi.common.model.CustomerInquiry;
import com.cgi.space.psi.common.model.InquiryStateType;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.demodata.DemoPssData;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PvdCustomerInquiryApiIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;

    private MockMvc mockMvc;

    @BeforeEach
    public void createContext() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(CustomerInquiry.class).all();
    }

    @DynamicPropertySource
    static void registerActiveProfile(DynamicPropertyRegistry registry) {
        registry.add("spring.profiles.active", () -> Profiles.PROVIDER);
    }
    @Test
    public void testCreateCustomerInquiryViaEventApi() throws Exception {
        this.mockMvc.perform(post("/psi-api/eventManagement/v2/topic/" + DemoPssData.TOPIC_ID_INQUIRY + "/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"domain\": \"inquiry\", \"eventType\": \"created\", \"event\": { \"id\": \"e7fcd72d-079c-45a9-bf00-ca70b7042ef2\", \"state\": \"pending\", \"inquiredProvider\": [{ \"href\": \"http://test/123\" }], \"@type\": \"CustomerInquiry\" } }"))
                .andDo(print())
                .andExpect(status().isCreated());

        String inquiryId = "e7fcd72d-079c-45a9-bf00-ca70b7042ef2";
        CustomerInquiry result1 = mongoTemplate.findById(inquiryId, CustomerInquiry.class);
        assertThat(result1.getState(), is(InquiryStateType.PENDING));
        assertThat(result1.getInquiredProvider().get(0).getState(), is(nullValue()));

        Thread.sleep(10_000);
        CustomerInquiry result2 = mongoTemplate.findById(inquiryId, CustomerInquiry.class);
        assertThat(result2.getInquiredProvider().get(0).getState(), is(InquiryStateType.COMPLETED));
    }
}
