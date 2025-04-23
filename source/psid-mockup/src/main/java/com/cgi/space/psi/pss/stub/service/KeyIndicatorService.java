/*
 * Copyright 2024 CGI Deutschland B.V. & Co. KG
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

import com.cgi.space.psi.common.model.KeyIndicator;
import com.cgi.space.psi.common.model.KeyIndicatorCreate;
import com.cgi.space.psi.common.model.KeyIndicatorUpdate;
import com.cgi.space.psi.pss.stub.controller.KeyIndicatorController;
import com.cgi.space.psi.pss.stub.mapper.KeyIndicatorMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link KeyIndicator}s.
 */
@Slf4j
@Service
public class KeyIndicatorService {

    @Autowired
    private KeyIndicatorMapper keyIndicatorMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link KeyIndicator} object.
     *
     * @param keyIndicatorCreate the {@link KeyIndicatorCreate} object based on which
     *            the {@link KeyIndicator} is created.
     * @return the newly created {@link KeyIndicator} object.
     */
    public KeyIndicator createKeyIndicator(KeyIndicatorCreate keyIndicatorCreate) {
        KeyIndicator keyIndicator = keyIndicatorMapper.toKeyIndicator(keyIndicatorCreate);
        keyIndicator.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(KeyIndicatorController.class)
                .retrieveKeyIndicator(keyIndicator.getId(), Optional.empty())).withSelfRel();
        keyIndicator.setHref(self.toUri());

        return template.save(keyIndicator);
    }

    /**
     * The method retrieves an existing {@link KeyIndicator}.
     *
     * @param id The Identifier of the {@link KeyIndicator} to be retrieved.
     * @return The retrieved {@link KeyIndicator}.
     */
    public KeyIndicator getKeyIndicator(String id) {
        return template.findById(id, KeyIndicator.class);
    }

    /**
     * The method retrieves all {@link KeyIndicator}s.
     *
     * @param pageable Collection of pagination parameters
     * @return all {@link KeyIndicator}s
     */
    public Page<KeyIndicator> getAllKeyIndicators(Pageable pageable) {
        return template.find(new Query(), pageable, KeyIndicator.class);
    }

    /**
     * The method updates an existing {@link KeyIndicator}.
     *
     * @param id The Identifier of the {@link KeyIndicator} to be updated.
     * @param keyIndicatorUpdate The {@link KeyIndicator} object containing the updates
     *            to be applied on the {@link KeyIndicator} object.
     * @return the updated {@link KeyIndicator keyIndicator}
     */
    public KeyIndicator updateKeyIndicator(String id, KeyIndicatorUpdate keyIndicatorUpdate) {
        KeyIndicator keyIndicator = template.findOne(Query.query(Criteria.where("id").is(id)), KeyIndicator.class);
        if (keyIndicator != null) {
            keyIndicatorMapper.updateKeyIndicator(keyIndicatorUpdate, keyIndicator);
            return template.save(keyIndicator);
        }
        else {
            return null;
        }
    }

    /**
     * The method deletes an existing {@link KeyIndicator}.
     *
     * @param id The Identifier of the {@link KeyIndicator} to be deleted.
     * @return <code>true</code> if {@link KeyIndicator} successfully deleted,
     *         <code>false</code> otherwise.
     */
    public boolean deleteKeyIndicator(String id) {
        KeyIndicator keyIndicator = template.findById(id, KeyIndicator.class);
        if (keyIndicator == null) {
            return false;
        }
        return template.remove(keyIndicator).getDeletedCount() > 0;
    }

}
