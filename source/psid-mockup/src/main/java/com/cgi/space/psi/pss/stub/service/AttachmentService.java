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

import com.cgi.space.psi.common.model.Attachment;
import com.cgi.space.psi.common.model.AttachmentFVO;
import com.cgi.space.psi.common.model.AttachmentMVO;
import com.cgi.space.psi.common.model.Document;
import com.cgi.space.psi.common.model.Quantity;
import com.cgi.space.psi.common.storage.StorageService;
import com.cgi.space.psi.pss.stub.controller.AttachmentController;
import com.cgi.space.psi.pss.stub.event.DocumentDeletedEvent;
import com.cgi.space.psi.pss.stub.mapper.AttachmentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link Attachment Attachments}.
 */
@Slf4j
@Service
public class AttachmentService {

    public static final String UNIT_BYTE = "byte";

    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private StorageService storageService;

    /**
     * The method creates a new {@link Attachment} object.
     *
     * @param documentId       The Identifier of the {@link Document} object that owns the attachment.
     * @param attachmentCreate the {@link AttachmentFVO} object based on which
     *                         the {@link Attachment} is created.
     * @return the newly created {@link Attachment} object.
     * @throws IOException if an I/O error occurs
     */
    public Attachment createAttachment(String documentId, AttachmentFVO attachmentCreate) throws IOException {
        Document document = template.findById(documentId, Document.class);
        if (document == null) {
            return null;
        }

        Attachment attachment = attachmentMapper.toAttachment(attachmentCreate);
        attachment.setId(UUID.randomUUID().toString());

        Link retrieveAttachment = linkTo(methodOn(AttachmentController.class)
                .retrieveAttachment(documentId, attachment.getId(), Optional.empty())).withSelfRel();
        attachment.setHref(retrieveAttachment.toUri());

        if (attachmentCreate.getContent() != null) {
            uploadToStorage(documentId, attachment, attachmentCreate.getContent().getInputStream());
        }

        document.addAttachmentItem(attachment);
        template.save(document);
        return attachment;
    }

    /**
     * The method retrieves an existing {@link Attachment}.
     *
     * @param documentId   The Identifier of the {@link Document} object that owns the attachment.
     * @param attachmentId The Identifier of the {@link Attachment} to be retrieved.
     * @return The retrieved {@link Attachment}.
     */
    public Attachment getAttachment(String documentId, String attachmentId) {
        Document document = template.findById(documentId, Document.class);
        if (document == null || document.getAttachment() == null) {
            return null;
        }
        return document.getAttachment().stream()
                .filter((a) -> a.getId().equals(attachmentId))
                .findAny().orElse(null);
    }

    /**
     * The method retrieves all {@link Attachment Attachments}.
     *
     * @param documentId The Identifier of the {@link Document} object that owns the attachments.
     * @return all {@link Attachment Attachments}
     */
    public List<Attachment> getAllAttachments(String documentId) {
        Document document = template.findById(documentId, Document.class);
        if (document == null || document.getAttachment() == null) {
            return emptyList();
        }

        return document.getAttachment();
    }

    /**
     * The method updates an existing {@link Attachment}.
     *
     * @param documentId       The Identifier of the {@link Document} object that owns the attachment.
     * @param attachmentId     The Identifier of the {@link Attachment} object to be updated.
     * @param attachmentUpdate The {@link AttachmentMVO} object containing the updates necessary for the {@link Attachment}.
     * @return The updated {@link Attachment}.
     * @throws IOException if an I/O error occurs
     */
    public Attachment updateAttachment(String documentId, String attachmentId, AttachmentMVO attachmentUpdate) throws IOException {
        Document document = template.findById(documentId, Document.class);
        if (document == null || document.getAttachment() == null) {
            return null;
        }

        for (Attachment attachment : document.getAttachment()) {
            if (attachment.getId().equals(attachmentId)) {
                attachmentMapper.updateAttachment(attachmentUpdate, attachment);
                if (attachmentUpdate.getContent() != null) {
                    uploadToStorage(documentId, attachment, attachmentUpdate.getContent().getInputStream());
                }
                template.save(document);
                return attachment;
            }
        }
        return null;
    }

    /**
     * Updates the content of the attachment inside the storage.
     *
     * @param documentId   The Identifier of the {@link Document} object that owns the attachment.
     * @param attachmentId The Identifier of the {@link Attachment} object to be updated.
     * @param content      an input stream for the content to be stored
     * @return The updated {@link Attachment}.
     * @throws IOException if an I/O error occurs
     */
    public Attachment saveAttachmentContent(String documentId, String attachmentId, InputStream content) throws IOException {
        Document document = template.findById(documentId, Document.class);
        if (document == null || document.getAttachment() == null) {
            return null;
        }

        for (Attachment attachment : document.getAttachment()) {
            if (attachment.getId().equals(attachmentId)) {
                uploadToStorage(documentId, attachment, content);
                template.save(document);
                return attachment;
            }
        }
        return null;
    }

    /**
     * Returns an input stream to read the content of the attachment.
     *
     * @param attachment the attachment
     * @return the input stream to read the content of the attachment
     * @throws IOException if an I/O error occurs
     */
    public InputStream getAttachmentContent(Attachment attachment) throws IOException {
        return storageService.retrieve(attachment.getId());
    }

    /**
     * Returns a URI to redirect the user to in order to download the attachment content.
     * This is either the "url" property of the entity itself, a direct link to the storage provider or an empty Optional.
     *
     * @param documentId The Identifier of the {@link Document} object that owns the attachment.
     * @param attachment the attachment
     * @return the redirect URI (or empty optional)
     */
    public Optional<URI> getRedirectUri(String documentId, Attachment attachment) {
        if (isSelfLink(documentId, attachment)) {
            return storageService.getRedirectUri(attachment.getId());
        }
        else {
            return Optional.ofNullable(attachment.getUrl());
        }
    }

    /**
     * The method deletes an existing {@link Attachment}.
     *
     * @param documentId   The Identifier of the {@link Document} object that owns the attachment.
     * @param attachmentId The Identifier of the {@link Attachment} object to be deleted.
     * @return <code>true</code> if {@link Attachment} successfully deleted, <code>false</code> otherwise.
     * @throws IOException if an I/O error occurs
     */
    public boolean deleteAttachment(String documentId, String attachmentId) throws IOException {
        Document document = template.findById(documentId, Document.class);
        if (document == null || document.getAttachment() == null) {
            return false;
        }

        for (Iterator<Attachment> it = document.getAttachment().iterator(); it.hasNext();) {
            Attachment attachment = it.next();
            if (attachment.getId().equals(attachmentId)) {
                if (isSelfLink(documentId, attachment)) {
                    storageService.delete(attachment.getId());
                }
                it.remove();
                template.save(document);
                return true;
            }
        }
        return false;
    }

    private boolean isSelfLink(String documentId, Attachment attachment) {
        Link selfLink = linkTo(methodOn(AttachmentController.class)
                .retrieveAttachmentContent(documentId, attachment.getId())).withSelfRel();
        return selfLink.toUri().equals(attachment.getUrl());
    }

    /**
     * Uploads the attachment content to the configured storage and updates the metadata.
     *
     * @param documentId The Identifier of the {@link Document} object that owns the attachment.
     * @param attachment the attachment
     * @param content    an input stream for the content to be uploaded
     * @throws IOException if an I/O error occurs
     */
    private void uploadToStorage(String documentId, Attachment attachment, InputStream content) throws IOException {
        final long size = storageService.store(attachment.getId(), content, Optional.ofNullable(attachment.getMimeType()));
        Link retrieveAttachmentContent = linkTo(methodOn(AttachmentController.class)
                .retrieveAttachmentContent(documentId, attachment.getId())).withSelfRel();
        attachment.setUrl(retrieveAttachmentContent.toUri());
        attachment.setSize(new Quantity().amount((float) size).units(UNIT_BYTE));
    }

    /**
     * The method listens for {@link DocumentDeletedEvent} that is raised by the {@link DocumentService} when a
     * {@link Document} is deleted.
     * Then, it deletes all associated {@link Attachment Attachments} of the {@link Document} from the DB and
     * from the file storage.
     *
     * @param event The {@link DocumentDeletedEvent} containing information about the {@link Document} whose
     *              attachments should be deleted.
     */
    @EventListener
    public void handleDeleteAttachmentsEvent(DocumentDeletedEvent event) {
        log.debug("Removing associated attachments for document with ID: {}", event.getDocument().getId());
        List<Attachment> attachments = event.getDocument().getAttachment();
        if (attachments != null) {
            for (Attachment attachment : attachments) {
                try {
                    if (isSelfLink(event.getDocument().getId(), attachment)) {
                        storageService.delete(attachment.getId());
                    }
                }
                catch (IOException e) {
                    log.error("Failed to delete attachment with ID: {}", attachment.getId());
                }
            }
        }
    }

}
