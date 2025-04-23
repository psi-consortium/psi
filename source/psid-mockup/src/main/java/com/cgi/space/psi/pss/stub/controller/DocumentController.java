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

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.Document;
import com.cgi.space.psi.common.model.DocumentFVO;
import com.cgi.space.psi.common.model.DocumentMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.DocumentApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.DocumentFilter;
import com.cgi.space.psi.pss.stub.service.DocumentService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link Document}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/documentManagement/v2")
@Profile("!" + Profiles.PROVIDER)
public class DocumentController implements DocumentApi {

    @Autowired
    private DocumentService service;

    /**
     * An endpoint for creating a new {@link Document}.
     *
     * @param documentCreate The {@link DocumentFVO} to be used to create {@link Document} (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link Document}
     */
    @Override
    @PsiOperation("TOD-01-03-01")
    public ResponseEntity<Document> createDocument(DocumentFVO documentCreate, Optional<String> fields) {
        Document result = service.createDocument(documentCreate);
        log.info("Created new document: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for listing all {@link Document Documents}.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit  Requested number of resources to be provided in response (optional)
     * @return All {@link Document Documents}.
     */
    @Override
    @PsiOperation("TOD-01-03-05")
    public ResponseEntity<List<Document>> listDocument(Optional<DocumentFilter> filter, Optional<String> fields,
                                                           Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Document> allDocuments = service.getAllDocuments(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allDocuments);
    }

    /**
     * An endpoint for retrieving the {@link Document} with given identifier.
     *
     * @param id     Identifier of the {@link Document} (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link Document}
     */
    @Override
    @PsiOperation("TOD-01-03-04")
    public ResponseEntity<Document> retrieveDocument(String id, Optional<String> fields) {
        return okOrNotFound(service.getDocument(id));
    }

    /**
     * An endpoint that removes an existing {@link Document} with given identifier.
     *
     * @param id Identifier of the Document (required)
     */
    @Override
    @PsiOperation("TOD-01-03-03")
    public ResponseEntity<Void> deleteDocument(String id) {
        boolean result = service.deleteDocument(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint that updates an {@link Document} with given identifier.
     *
     * @param id               Identifier of the Document (required)
     * @param documentUpdate The Document to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link Document}
     */
    @Override
    @PsiOperation("TOD-01-03-02")
    public ResponseEntity<Document> patchDocument(String id, DocumentMVO documentUpdate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateDocument(id, documentUpdate), HttpStatus.OK);
    }
}
