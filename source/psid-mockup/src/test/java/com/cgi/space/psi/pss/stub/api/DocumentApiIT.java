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

import com.cgi.space.psi.common.model.Attachment;
import com.cgi.space.psi.common.model.Document;
import com.cgi.space.psi.common.storage.StorageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class DocumentApiIT {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MongoTemplate mongoTemplate;
    @MockBean
    private StorageService storageService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @AfterEach
    public void clean() {
        mongoTemplate.remove(Document.class).all();
    }

    @Test
    public void testCreateDocument() throws Exception {
        this.mockMvc.perform(post("/psi-api/documentManagement/v2/document")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Contract\", \"description\": \"Just a Test\", \"@type\": \"Document\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Contract"))
                .andExpect(jsonPath("description").value("Just a Test"));
    }

    @Test
    public void testDeleteDocumentWithoutAttachment() throws Exception {
        Document document = new Document();
        document.setId("48e3c247-34cb-4621-9130-75d6365f04ae");
        mongoTemplate.save(document);

        this.mockMvc.perform(delete("/psi-api/documentManagement/v2/document/48e3c247-34cb-4621-9130-75d6365f04ae"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(storageService, never()).delete(any(String.class));
    }

    @Test
    public void testDeleteDocumentWithAttachment() throws Exception {
        Attachment attachment = new Attachment();
        attachment.setId("2ecef7a2-8741-40b5-9630-76099a14f210");
        attachment.setUrl(URI.create("http://localhost/psi-api/documentManagement/v2/document/c603fbb4-6243-4cf8-958d-be4c9d8bf11c/attachment/2ecef7a2-8741-40b5-9630-76099a14f210/content"));

        Document document = new Document().id("c603fbb4-6243-4cf8-958d-be4c9d8bf11c");
        document.addAttachmentItem(attachment);
        mongoTemplate.save(document);

        this.mockMvc.perform(delete("/psi-api/documentManagement/v2/document/c603fbb4-6243-4cf8-958d-be4c9d8bf11c"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(storageService).delete("2ecef7a2-8741-40b5-9630-76099a14f210");
    }

    @Test
    public void testListDocument() throws Exception {
        Document document = new Document();
        document.setId("a4f8e12e-c001-4f8a-bc00-137aff1e121d");
        mongoTemplate.save(document);

        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("a4f8e12e-c001-4f8a-bc00-137aff1e121d"));
    }

    @Test
    public void testListDocumentWithOffsetAndLimit() throws Exception {
        List<Document> documents = IntStream.range(0, 10).mapToObj(num -> {
            Document document = new Document();
            document.setId(UUID.randomUUID().toString());
            return mongoTemplate.save(document);
        }
        ).collect(toList());

        int offset = 2;
        int limit = 5;
        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document?offset=" + offset + "&limit=" + limit))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(limit))
                .andExpect(jsonPath("$[0].id").value(documents.get(offset).getId()))
                .andExpect(jsonPath("$[1].id").value(documents.get(offset + 1).getId()))
                .andExpect(jsonPath("$[2].id").value(documents.get(offset + 2).getId()))
                .andExpect(jsonPath("$[3].id").value(documents.get(offset + 3).getId()))
                .andExpect(jsonPath("$[4].id").value(documents.get(offset + 4).getId()));
    }

    @Test
    public void testPatchDocument() throws Exception {
        Document document = new Document();
        document.setId("129c37da-92d5-49bc-b1f8-fc6e5a3081d1");
        document.setName("Contract A");
        document.setDescription("test description");
        mongoTemplate.save(document);

        this.mockMvc.perform(patch("/psi-api/documentManagement/v2/document/129c37da-92d5-49bc-b1f8-fc6e5a3081d1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Contract B\", \"@type\": \"Document\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Contract B"))
                .andExpect(jsonPath("description").value("test description"));
    }

    @Test
    public void testRetrieveDocument() throws Exception {
        Document document = new Document();
        document.setId("3fd06792-3b76-4144-b8e1-82016400d111");
        document.setName("Contract");
        mongoTemplate.save(document);

        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document/3fd06792-3b76-4144-b8e1-82016400d111"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("3fd06792-3b76-4144-b8e1-82016400d111"))
                .andExpect(jsonPath("name").value("Contract"));
    }

}
