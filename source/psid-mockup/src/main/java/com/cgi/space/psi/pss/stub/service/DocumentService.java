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

import com.cgi.space.psi.common.model.Document;
import com.cgi.space.psi.common.model.DocumentFVO;
import com.cgi.space.psi.common.model.DocumentMVO;
import com.cgi.space.psi.pss.stub.controller.DocumentController;
import com.cgi.space.psi.pss.stub.event.DocumentDeletedEvent;
import com.cgi.space.psi.pss.stub.filter.DocumentFilter;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.DocumentMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link Document Documents}.
 */
@Service
public class DocumentService {

    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private PageableMongoTemplate template;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * The method creates a new {@link Document} object.
     *
     * @param documentCreate the {@link DocumentFVO} object based on which
     *                         the {@link Document} is created.
     * @return the newly created {@link Document} object.
     */
    public Document createDocument(DocumentFVO documentCreate) {
        Document document = documentMapper.toDocument(documentCreate);
        document.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(DocumentController.class)
                .retrieveDocument(document.getId(), Optional.empty())).withSelfRel();
        document.setHref(self.toUri());

        return template.save(document);
    }

    /**
     * The method retrieves an existing {@link Document}.
     *
     * @param id The Identifier of the {@link Document} to be retrieved.
     * @return The retrieved {@link Document}.
     */
    public Document getDocument(String id) {
        return template.findById(id, Document.class);
    }

    /**
     * The method retrieves all {@link Document Documents}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link Document Documents}
     */
    public Page<Document> getAllDocuments(Pageable pageable, Optional<DocumentFilter> filter) {
        return template.find(buildQuery(filter), pageable, Document.class);
    }

    private Query buildQuery(Optional<DocumentFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing {@link Document Document}.
     *
     * @param id               The Identifier of the {@link Document} object to be updated.
     * @param documentUpdate The {@link DocumentMVO} object containing the updates necessary for the {@link Document}.
     * @return The updated {@link Document Document}.
     */
    public Document updateDocument(String id, DocumentMVO documentUpdate) {
        Document document = template.findOne(Query.query(Criteria.where("id").is(id)), Document.class);
        documentMapper.updateDocument(documentUpdate, document);
        return template.save(document);
    }

    /**
     * The method deletes an existing {@link Document Document}.
     * Additionally, it triggers deletion of all associated {@link com.cgi.space.psi.common.model.Attachment Attachments}.
     *
     * @param id The Identifier of the {@link Document} object to be deleted.
     * @return <code>true</code> if {@link Document} successfully deleted, <code>false</code> otherwise.
     */
    public boolean deleteDocument(String id) {
        Document document = template.findById(id, Document.class);
        if (document != null) {
            template.remove(document);
            applicationEventPublisher.publishEvent(new DocumentDeletedEvent(document));
            return true;
        }
        else {
            return false;
        }
    }

}
