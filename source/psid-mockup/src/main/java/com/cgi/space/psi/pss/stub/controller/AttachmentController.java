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
package com.cgi.space.psi.pss.stub.controller;

import static com.cgi.space.psi.pss.stub.util.ControllerUtil.okOrNotFound;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.Attachment;
import com.cgi.space.psi.common.model.AttachmentFVO;
import com.cgi.space.psi.common.model.AttachmentMVO;
import com.cgi.space.psi.common.model.Quantity;
import com.cgi.space.psi.common.model.TimePeriod;
import com.cgi.space.psi.pss.stub.api.AttachmentApi;
import com.cgi.space.psi.pss.stub.service.AttachmentService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link Attachment}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/documentManagement/v2")
public class AttachmentController implements AttachmentApi {

    @Autowired
    private AttachmentService service;

    /**
     * An endpoint for creating an {@link Attachment} associated to a
     * {@link com.cgi.space.psi.common.model.Document}.
     *
     * @param documentId The identifier of the Document. (required)
     * @param name The name of the attachment (optional)
     * @param description A narrative text describing the content of the attachment (optional)
     * @param url Uniform Resource Locator, is a web page address (a subset of URI) (optional)
     * @param content The actual contents of the attachment object, if embedded, encoded as base64 (optional)
     * @param size  (optional)
     * @param validFor  (optional)
     * @param attachmentType a business characterization of the purpose of the attachment, for example logo, instructionManual, contractCopy (optional)
     * @param mimeType a technical characterization of the attachment content format using IETF Mime Types (optional)
     * @return                  ResponseEntity with the created {@link Attachment}.
     */
    @Override
    @PsiOperation("TOD-01-03-06")
    public ResponseEntity<Attachment> createAttachment(String documentId, String name, String description, URI url, MultipartFile content, Quantity size,
            TimePeriod validFor, String attachmentType, String mimeType) {
        return createAttachment(documentId, new AttachmentFVO()
                .attachmentType(attachmentType)
                .content(content.getResource())
                .description(description)
                .mimeType(mimeType == null ? content.getContentType() : mimeType)
                .name(name == null ? Path.of(content.getOriginalFilename()).getFileName().toString() : name)
                .url(url)
                .size(size)
                .validFor(validFor));
    }

    /**
     * An endpoint for creating an {@link Attachment}.
     *
     * @param documentId       Identifier of the Document (required)
     * @param attachmentCreate The Attachment to be created (required)
     * @return ResponseEntity with the created {@link Attachment}.
     */
    @Override
    @PsiOperation("TOD-01-03-06")
    public ResponseEntity<Attachment> createAttachment(String documentId, AttachmentFVO attachmentCreate) {
        try {
            Attachment result = service.createAttachment(documentId, attachmentCreate);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            log.info("Created new attachment: {}", result);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (IOException ex) {
            log.error("Failed to create attachment " + attachmentCreate.getName(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * An endpoint for deleting an {@link Attachment} with the given identifier that is associated to
     * a {@link com.cgi.space.psi.common.model.Document}.
     *
     * @param documentId   Identifier of the Document (required)
     * @param attachmentId Identifier of the Attachment (required)
     */
    @Override
    @PsiOperation("TOD-01-03-08")
    public ResponseEntity<Void> deleteAttachment(String documentId, String attachmentId) {
        try {
            boolean result = service.deleteAttachment(documentId, attachmentId);
            HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(status);
        }
        catch (IOException ex) {
            log.error("Failed to delete attachment " + attachmentId, ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * An endpoint for listing all {@link Attachment} that are associated to a {@link com.cgi.space.psi.common.model.Document}.
     *
     * @param documentId Identifier of the Document (required)
     * @param fields     Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the list of all {@link Attachment Attachments} associated to the document.
     */
    @Override
    @PsiOperation("TOD-01-03-10")
    public ResponseEntity<List<Attachment>> listAttachment(String documentId, Optional<String> fields) {
        return new ResponseEntity<>(service.getAllAttachments(documentId), HttpStatus.OK);
    }

    /**
     * An endpoint for updating an {@link Attachment} with the given identifier that is associated to a {@link com.cgi.space.psi.common.model.Document}.
     *
     * @param documentId     Identifier of the Document (required)
     * @param attachmentId   Identifier of the Attachment (required)
     * @param attachmentType Attachment type such as video, picture (optional)
     * @param content        The actual contents of the attachment object (optional)
     * @param description    A narrative text describing the content of the attachment (optional)
     * @param mimeType       Attachment mime type such as extension file for video, picture and document (optional)
     * @param name           The name of the attachment (optional)
     * @param url            The Uniform Resource Locator, is a web page address (a subset of URI). (optional)
     * @param size           (optional)
     * @param validFor       The validity period of the attachment (optional)
     * @return ResponseEntity with the updated {@link Attachment}.
     */
    @Override
    @PsiOperation("TOD-01-03-07")
    public ResponseEntity<Attachment> patchAttachment(String documentId, String attachmentId, String name, String description, URI url, MultipartFile content,
            Quantity size, TimePeriod validFor, String attachmentType, String mimeType) {
        return patchAttachment(documentId, attachmentId, new AttachmentMVO()
                .attachmentType(attachmentType)
                .content(content.getResource())
                .description(description)
                .mimeType(mimeType == null ? content.getContentType() : mimeType)
                .name(name == null ? Path.of(content.getOriginalFilename()).getFileName().toString() : name)
                .url(url)
                .size(size)
                .validFor(validFor));
    }

    /**
     * An endpoint for updating an {@link Attachment} with the given identifier that is associated to a
     * {@link com.cgi.space.psi.common.model.Document}.
     *
     * @param documentId       Identifier of the Document (required)
     * @param attachmentId     Identifier of the Attachment (required)
     * @param attachmentUpdate The Attachment to be updated (required)
     * @return ResponseEntity with the updated {@link Attachment}.
     */
    @Override
    @PsiOperation("TOD-01-03-07")
    public ResponseEntity<Attachment> patchAttachment(String documentId, String attachmentId, AttachmentMVO attachmentUpdate) {
        try {
            final Attachment attachment = service.updateAttachment(documentId, attachmentId, attachmentUpdate);
            if (attachment == null) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(attachment, HttpStatus.OK);
        }
        catch (IOException ex) {
            log.error("Failed to patch attachment " + attachmentId, ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * An endpoint for retrieving an {@link Attachment} with the given identifier that is associated to a
     * {@link com.cgi.space.psi.common.model.Document}.
     *
     * @param documentId   Identifier of the Document (required)
     * @param attachmentId Identifier of the Attachment (required)
     * @param fields       Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link Attachment}.
     */
    @Override
    @PsiOperation("TOD-01-03-09")
    public ResponseEntity<Attachment> retrieveAttachment(String documentId, String attachmentId, Optional<String> fields) {
        return okOrNotFound(service.getAttachment(documentId, attachmentId));
    }

    /**
     * An endpoint for fetching the binary content of an {@link Attachment} with the given identifier that is associated to a
     * {@link com.cgi.space.psi.common.model.Document}.
     *
     * @param documentId   Identifier of the Document (required)
     * @param attachmentId Identifier of the Attachment (required)
     * @return ResourceEntity with the fetched binary content.
     */
    @Override
    @PsiOperation("TOD-01-03-11")
    public ResponseEntity<Resource> retrieveAttachmentContent(String documentId, String attachmentId) {
        final Attachment attachment = service.getAttachment(documentId, attachmentId);
        if (attachment == null) {
            return ResponseEntity.notFound().build();
        }

        Optional<URI> redirectUri = service.getRedirectUri(documentId, attachment);
        if (redirectUri.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).location(redirectUri.get()).build();
        }
        else {
            try {
                InputStream inputStream = service.getAttachmentContent(attachment);
                final BodyBuilder response = ResponseEntity.status(HttpStatus.OK);
                if (StringUtils.isNotBlank(attachment.getMimeType())) {
                    response.contentType(MediaType.parseMediaType(attachment.getMimeType()));
                }
                if (attachment.getSize() != null && AttachmentService.UNIT_BYTE.equals(attachment.getSize().getUnits())) {
                    response.contentLength(attachment.getSize().getAmount().longValue());
                }
                response.header("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");
                return response.body(new InputStreamResource(inputStream, attachment.getName()));
            }
            catch (IOException ex) {
                log.error("Failed to retrieve content for attachment " + attachmentId, ex);
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    /**
     * An endpoint for updating the binary content of an {@link Attachment} with the given identifier that is associated to a
     * {@link com.cgi.space.psi.common.model.Document}.
     *
     * @param documentId   Identifier of the Document (required)
     * @param attachmentId Identifier of the Attachment (required)
     * @param body         The Attachment to be created (required)
     * @return ResponseEntity with the updated {@link Attachment}.
     */
    @Override
    @PsiOperation("TOD-01-03-12")
    public ResponseEntity<Attachment> updateAttachmentContent(String documentId, String attachmentId, Resource body) {
        try {
            Attachment attachment = service.saveAttachmentContent(documentId, attachmentId, body.getInputStream());
            if (attachment == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(attachment);
        }
        catch (IOException ex) {
            log.error("Failed to update content for attachment " + attachmentId, ex);
            return ResponseEntity.internalServerError().build();
        }
    }

}
