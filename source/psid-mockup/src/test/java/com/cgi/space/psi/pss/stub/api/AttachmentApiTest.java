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
import com.cgi.space.psi.common.model.Quantity;
import com.cgi.space.psi.common.storage.StorageService;
import com.cgi.space.psi.pss.stub.service.AttachmentService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Optional;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.anyOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AttachmentApiTest {

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
    public void testCreateAttachmentWithUrl() throws Exception {
        createDocument("1a49414b-6311-41d2-97e0-e1b60adbf6d6", null);
        this.mockMvc.perform(post("/psi-api/documentManagement/v2/document/1a49414b-6311-41d2-97e0-e1b60adbf6d6/attachment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"example.jpg\", \"url\": \"https://www.example.com/example.jpg\", \"attachmentType\": \"Test\", \"mimeType\": \"image/jpg\", \"@type\": \"Attachment\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("example.jpg"))
                .andExpect(jsonPath("url").value("https://www.example.com/example.jpg"));
        verify(storageService, never()).store(any(String.class), any(InputStream.class), any(Optional.class));
        assertThat(mongoTemplate.findById("1a49414b-6311-41d2-97e0-e1b60adbf6d6", Document.class).getAttachment(), hasSize(1));
    }

    @Test
    public void testCreateAttachmentWithContent() throws Exception {
        createDocument("aca349a5-f249-4dec-9374-6f32a50ef526", null);
        when(storageService.store(any(String.class), any(InputStream.class), any(Optional.class)))
                .thenReturn(123L);
        this.mockMvc.perform(post("/psi-api/documentManagement/v2/document/aca349a5-f249-4dec-9374-6f32a50ef526/attachment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"example.jpg\", \"content\": \"Rm9vYmFy\", \"attachmentType\": \"Test\", \"mimeType\": \"text/plain\", \"@type\": \"Attachment\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("example.jpg"))
                .andExpect(jsonPath("url").value(containsString("/psi-api/documentManagement/v2/document/aca349a5-f249-4dec-9374-6f32a50ef526/attachment/")))
                .andExpect(jsonPath("size.amount").value(123));
        verify(storageService).store(any(String.class), any(InputStream.class), eq(Optional.of("text/plain")));
        assertThat(mongoTemplate.findById("aca349a5-f249-4dec-9374-6f32a50ef526", Document.class).getAttachment(), hasSize(1));
    }

    @Test
    public void testCreateAttachmentWithMultipart() throws Exception {
        createDocument("4589a9d8-cefe-4327-bf1e-d6301be8651b", null);
        when(storageService.store(any(String.class), any(InputStream.class), any(Optional.class)))
                .thenReturn(123L);
        this.mockMvc.perform(multipart("/psi-api/documentManagement/v2/document/4589a9d8-cefe-4327-bf1e-d6301be8651b/attachment")
                .file("content", "Foobar".getBytes())
                .param("name", "example.jpg"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("example.jpg"))
                .andExpect(jsonPath("url").value(containsString("/psi-api/documentManagement/v2/document/4589a9d8-cefe-4327-bf1e-d6301be8651b/attachment/")))
                .andExpect(jsonPath("size.amount").value(123));
        verify(storageService).store(any(String.class), any(InputStream.class), eq(Optional.empty()));
        assertThat(mongoTemplate.findById("4589a9d8-cefe-4327-bf1e-d6301be8651b", Document.class).getAttachment(), hasSize(1));
    }

    @Test
    public void testCreateAttachmentStorageError() throws Exception {
        createDocument("25775742-ac62-4d2e-bbf7-dc676d9a9950", null);
        when(storageService.store(any(String.class), any(InputStream.class), any(Optional.class)))
                .thenThrow(IOException.class);
        this.mockMvc.perform(post("/psi-api/documentManagement/v2/document/25775742-ac62-4d2e-bbf7-dc676d9a9950/attachment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"example.jpg\", \"content\": \"Rm9vYmFy\", \"attachmentType\": \"Test\", \"mimeType\": \"text/plain\", \"@type\": \"Attachment\"}"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
        assertThat(mongoTemplate.findById("25775742-ac62-4d2e-bbf7-dc676d9a9950", Document.class).getAttachment(), anyOf(hasSize(0), is(nullValue())));
    }

    @Test
    public void testCreateAttachmentWithoutDocument() throws Exception {
        this.mockMvc.perform(post("/psi-api/documentManagement/v2/document/124f0b8e-f220-460c-8be7-eb55c4ed5bbe/attachment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"example.jpg\", \"attachmentType\": \"Test\", \"mimeType\": \"image/jpg\", \"@type\": \"Attachment\"}"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(storageService, never()).store(any(String.class), any(InputStream.class), any(Optional.class));
    }

    @Test
    public void testDeleteAttachment() throws Exception {
        Attachment attachment = new Attachment();
        attachment.setId("2ecef7a2-8741-40b5-9630-76099a14f210");
        attachment.setUrl(URI.create("http://localhost/psi-api/documentManagement/v2/document/c603fbb4-6243-4cf8-958d-be4c9d8bf11c/attachment/2ecef7a2-8741-40b5-9630-76099a14f210/content"));
        createDocument("c603fbb4-6243-4cf8-958d-be4c9d8bf11c", attachment);

        this.mockMvc.perform(delete("/psi-api/documentManagement/v2/document/c603fbb4-6243-4cf8-958d-be4c9d8bf11c/attachment/2ecef7a2-8741-40b5-9630-76099a14f210"))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(storageService).delete("2ecef7a2-8741-40b5-9630-76099a14f210");
        assertThat(mongoTemplate.findById("c603fbb4-6243-4cf8-958d-be4c9d8bf11c", Document.class).getAttachment(), hasSize(0));
    }

    @Test
    public void testDeleteUnknownDocument() throws Exception {
        this.mockMvc.perform(delete("/psi-api/documentManagement/v2/document/7ce7c465-80f3-462a-9698-851251d783b1/attachment/d23dc7b7-e832-4d0b-9980-bd7b77f3ed5c"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(storageService, never()).delete(any(String.class));
    }

    @Test
    public void testDeleteUnknownAttachment() throws Exception {
        createDocument("fc641fef-b21b-4f81-bac0-7b11db140135", new Attachment().id("83aeaa2b-2109-404e-a0d7-788159c0f9ed"));
        this.mockMvc.perform(delete("/psi-api/documentManagement/v2/document/fc641fef-b21b-4f81-bac0-7b11db140135/attachment/d23dc7b7-e832-4d0b-9980-bd7b77f3ed5c"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(storageService, never()).delete(any(String.class));
        assertThat(mongoTemplate.findById("fc641fef-b21b-4f81-bac0-7b11db140135", Document.class).getAttachment(), hasSize(1));
    }

    @Test
    public void testDeleteAttachmentError() throws Exception {
        doThrow(IOException.class).when(storageService).delete("ad6d115e-7c45-4aef-a5b6-3ef8300e305c");
        Attachment attachment = new Attachment();
        attachment.setId("ad6d115e-7c45-4aef-a5b6-3ef8300e305c");
        attachment.setUrl(URI.create("http://localhost/psi-api/documentManagement/v2/document/20f5678b-82ef-4fab-9875-6f2f9d78ad80/attachment/ad6d115e-7c45-4aef-a5b6-3ef8300e305c/content"));
        createDocument("20f5678b-82ef-4fab-9875-6f2f9d78ad80", attachment);

        this.mockMvc.perform(delete("/psi-api/documentManagement/v2/document/20f5678b-82ef-4fab-9875-6f2f9d78ad80/attachment/ad6d115e-7c45-4aef-a5b6-3ef8300e305c"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
        assertThat(mongoTemplate.findById("20f5678b-82ef-4fab-9875-6f2f9d78ad80", Document.class).getAttachment(), hasSize(1));
    }

    @Test
    public void testListAttachment() throws Exception {
        Attachment attachment = new Attachment();
        attachment.setId("8b7d1c7e-ded0-4388-b936-5acf0e58c501");
        createDocument("319f99f1-1bce-4df0-adde-9f921885d103", attachment);

        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document/319f99f1-1bce-4df0-adde-9f921885d103/attachment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("8b7d1c7e-ded0-4388-b936-5acf0e58c501"));
    }

    @Test
    public void testListAttachmentWithoutDocument() throws Exception {
        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document/bf3ab40d-340d-4af7-be1e-7bc65c06dac3/attachment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testPatchAttachment() throws Exception {
        when(storageService.store(any(String.class), any(InputStream.class), any(Optional.class)))
                .thenReturn(456L);
        Attachment attachment = new Attachment();
        attachment.setId("17898062-9f51-4dd1-a6be-2bd7cd0ebbc2");
        attachment.setName("example.jpg");
        attachment.setDescription("test description");
        createDocument("92ec14f5-842d-4a4f-b6d0-bf39983e08c0", attachment);

        this.mockMvc.perform(patch("/psi-api/documentManagement/v2/document/92ec14f5-842d-4a4f-b6d0-bf39983e08c0/attachment/17898062-9f51-4dd1-a6be-2bd7cd0ebbc2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"17898062-9f51-4dd1-a6be-2bd7cd0ebbc2\", \"name\": \"updated.jpeg\", \"content\": \"Rm9vYmFy\", \"mimeType\": \"text/plain\", \"@type\": \"Attachment\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("17898062-9f51-4dd1-a6be-2bd7cd0ebbc2"))
                .andExpect(jsonPath("name").value("updated.jpeg"))
                .andExpect(jsonPath("description").value("test description"))
                .andExpect(jsonPath("url").value(containsString("/psi-api/documentManagement/v2/document/92ec14f5-842d-4a4f-b6d0-bf39983e08c0/attachment/")))
                .andExpect(jsonPath("size.amount").value(456));
        verify(storageService).store(any(String.class), any(InputStream.class), eq(Optional.of("text/plain")));
        assertThat(mongoTemplate.findById("92ec14f5-842d-4a4f-b6d0-bf39983e08c0", Document.class).getAttachment(), hasSize(1));
    }

    @Test
    public void testPatchAttachmentError() throws Exception {
        doThrow(IOException.class).when(storageService).store(eq("17898062-9f51-4dd1-a6be-2bd7cd0ebbc2"), any(InputStream.class), any(Optional.class));
        Attachment attachment = new Attachment();
        attachment.setId("17898062-9f51-4dd1-a6be-2bd7cd0ebbc2");
        attachment.setName("example.jpg");
        createDocument("c690bf6b-4ef4-442c-bf36-e4054c995b83", attachment);

        this.mockMvc.perform(patch("/psi-api/documentManagement/v2/document/c690bf6b-4ef4-442c-bf36-e4054c995b83/attachment/17898062-9f51-4dd1-a6be-2bd7cd0ebbc2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"17898062-9f51-4dd1-a6be-2bd7cd0ebbc2\", \"name\": \"updated.jpeg\", \"content\": \"Rm9vYmFy\", \"mimeType\": \"text/plain\", \"@type\": \"Attachment\"}"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
        assertThat(mongoTemplate.findById("c690bf6b-4ef4-442c-bf36-e4054c995b83", Document.class).getAttachment(), hasSize(1));
    }

    @Test
    public void testPatchAttachmentWithoutAttachment() throws Exception {
        Attachment attachment = new Attachment();
        attachment.setId("5f1a8f4d-cfe8-437c-a005-9224298536ea");
        attachment.setName("example.jpg");
        createDocument("38b28043-22da-4d20-b582-4a6a646315d8", attachment);
        this.mockMvc.perform(patch("/psi-api/documentManagement/v2/document/38b28043-22da-4d20-b582-4a6a646315d8/attachment/dece2638-b499-4391-a5eb-ea177835ae2c")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"5ee57ebf-21cf-48cc-b902-7c38ab586073\", \"name\": \"updated.jpeg\", \"@type\": \"Attachment\"}"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(storageService, never()).store(any(String.class), any(InputStream.class), any(Optional.class));
    }

    @Test
    public void testPatchAttachmentWithoutDocument() throws Exception {
        this.mockMvc.perform(patch("/psi-api/documentManagement/v2/document/3bb4b591-94ff-48b0-96aa-b1ae3012969b/attachment/5ee57ebf-21cf-48cc-b902-7c38ab586073")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"5ee57ebf-21cf-48cc-b902-7c38ab586073\", \"name\": \"updated.jpeg\", \"@type\": \"Attachment\"}"))
                .andDo(print())
                .andExpect(status().isNotFound());
        verify(storageService, never()).store(any(String.class), any(InputStream.class), any(Optional.class));
    }

    @Test
    public void testRetrieveAttachment() throws Exception {
        Attachment attachment = new Attachment();
        attachment.setId("462c0316-91ac-4343-aa6b-761b4b27b8f3");
        attachment.setName("example.jpg");
        createDocument("639c435d-87be-42f7-9386-18233b04ca4e", attachment);

        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document/639c435d-87be-42f7-9386-18233b04ca4e/attachment/462c0316-91ac-4343-aa6b-761b4b27b8f3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("462c0316-91ac-4343-aa6b-761b4b27b8f3"))
                .andExpect(jsonPath("name").value("example.jpg"));
    }

    @Test
    public void testRetrieveAttachmentContent() throws Exception {
        when(storageService.retrieve("e56982c5-6a5e-43c1-987d-efae86894ac2"))
                .thenReturn(new ByteArrayInputStream("Foobar".getBytes()));
        Attachment attachment = new Attachment();
        attachment.setId("e56982c5-6a5e-43c1-987d-efae86894ac2");
        attachment.setName("example.jpg");
        attachment.setMimeType("text/plain");
        attachment.setSize(new Quantity().amount(6f).units(AttachmentService.UNIT_BYTE));
        attachment.setUrl(URI.create("http://localhost/psi-api/documentManagement/v2/document/32ab44fc-0f35-43d9-84eb-4b3d5223c242/attachment/e56982c5-6a5e-43c1-987d-efae86894ac2/content"));
        createDocument("32ab44fc-0f35-43d9-84eb-4b3d5223c242", attachment);

        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document/32ab44fc-0f35-43d9-84eb-4b3d5223c242/attachment/e56982c5-6a5e-43c1-987d-efae86894ac2/content"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=\"example.jpg\""))
                .andExpect(header().string("Content-Length", "6"))
                .andExpect(header().string("Content-Type", "text/plain"));
    }

    @Test
    public void testRetrieveAttachmentContentError() throws Exception {
        when(storageService.retrieve("c0d054e5-a02e-4bcc-bd23-c7e03cdc90ca")).thenThrow(IOException.class);
        Attachment attachment = new Attachment();
        attachment.setId("c0d054e5-a02e-4bcc-bd23-c7e03cdc90ca");
        attachment.setName("example.jpg");
        attachment.setMimeType("text/plain");
        attachment.setSize(new Quantity().amount(6f).units(AttachmentService.UNIT_BYTE));
        attachment.setUrl(URI.create("http://localhost/psi-api/documentManagement/v2/document/457f6ba3-70b6-491c-a8d9-8f9e293a5190/attachment/c0d054e5-a02e-4bcc-bd23-c7e03cdc90ca/content"));
        createDocument("457f6ba3-70b6-491c-a8d9-8f9e293a5190", attachment);

        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document/457f6ba3-70b6-491c-a8d9-8f9e293a5190/attachment/c0d054e5-a02e-4bcc-bd23-c7e03cdc90ca/content"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testRetrieveAttachmentContentRedirect() throws Exception {
        Attachment attachment = new Attachment();
        attachment.setId("d5f7adbf-905f-4f5c-a3d5-7a800601b63f");
        attachment.setName("example.jpg");
        attachment.setUrl(URI.create("https://www.google.com/"));
        createDocument("5db0280e-efb1-4e84-a0e1-aaa882891444", attachment);

        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document/5db0280e-efb1-4e84-a0e1-aaa882891444/attachment/d5f7adbf-905f-4f5c-a3d5-7a800601b63f/content"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "https://www.google.com/"));
    }

    @Test
    public void testRetrieveAttachmentContentNotFound() throws Exception {
        this.mockMvc.perform(get("/psi-api/documentManagement/v2/document/82a302d2-df54-4365-9c65-01809d0b5ad7/attachment/416cae69-17a4-4442-b3bb-9de7ef300404/content"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateAttachmentContent() throws Exception {
        when(storageService.store(eq("8e370c85-c5c3-45e8-97a7-7897026499c8"), any(InputStream.class), any(Optional.class)))
                .thenReturn(789L);
        Attachment attachment = new Attachment();
        attachment.setId("8e370c85-c5c3-45e8-97a7-7897026499c8");
        attachment.setName("example.jpg");
        attachment.setMimeType("image/jpg");
        attachment.setSize(new Quantity().amount(6f).units(AttachmentService.UNIT_BYTE));
        attachment.setUrl(URI.create("http://localhost/psi-api/documentManagement/v2/attachment/8e370c85-c5c3-45e8-97a7-7897026499c8/content"));
        createDocument("e30b5489-d6a4-4ed2-be84-d42287e50952", attachment);

        this.mockMvc.perform(put("/psi-api/documentManagement/v2/document/e30b5489-d6a4-4ed2-be84-d42287e50952/attachment/8e370c85-c5c3-45e8-97a7-7897026499c8/content")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Foobar".getBytes()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("8e370c85-c5c3-45e8-97a7-7897026499c8"))
                .andExpect(jsonPath("size.amount").value(789));
    }

    @Test
    public void testUpdateAttachmentContentError() throws Exception {
        when(storageService.store(eq("15d4b707-233b-4794-be68-883ff58f233e"), any(InputStream.class), any(Optional.class)))
                .thenThrow(IOException.class);
        Attachment attachment = new Attachment();
        attachment.setId("15d4b707-233b-4794-be68-883ff58f233e");
        attachment.setName("example.jpg");
        attachment.setMimeType("image/jpg");
        attachment.setSize(new Quantity().amount(6f).units(AttachmentService.UNIT_BYTE));
        attachment.setUrl(URI.create("http://localhost/psi-api/documentManagement/v2/attachment/15d4b707-233b-4794-be68-883ff58f233e/content"));
        createDocument("038d2c41-5c33-4ee0-be52-c2b779d8e77a", attachment);

        this.mockMvc.perform(put("/psi-api/documentManagement/v2/document/038d2c41-5c33-4ee0-be52-c2b779d8e77a/attachment/15d4b707-233b-4794-be68-883ff58f233e/content")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Foobar".getBytes()))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void testUpdateAttachmentContentWithoutAttachment() throws Exception {
        Attachment attachment = new Attachment();
        attachment.setId("52eb75f6-05de-4fa4-8522-c068f414fd6d");
        attachment.setName("example.jpg");
        createDocument("84f81cf8-542b-4274-9c31-1e1344083d72", attachment);
        this.mockMvc.perform(put("/psi-api/documentManagement/v2/document/84f81cf8-542b-4274-9c31-1e1344083d72/attachment/c449afc7-5ce4-4433-996b-41b247fe4745/content")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Foobar".getBytes()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateAttachmentContentWithoutDocument() throws Exception {
        this.mockMvc.perform(put("/psi-api/documentManagement/v2/document/d010f0e0-6e45-45c5-b95f-21e45668bbbd/attachment/42e9c9c5-6172-4da4-9f7c-1345083a9c79/content")
                .contentType(MediaType.TEXT_PLAIN)
                .content("Foobar".getBytes()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    private void createDocument(String id, Attachment initialAttachment) {
        Document document = new Document().id(id);
        if (initialAttachment != null) {
            document.addAttachmentItem(initialAttachment);
        }
        mongoTemplate.save(document);
    }

}
