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
package com.cgi.space.psi.pss.stub.controller;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.KeyIndicatorCreate;
import com.cgi.space.psi.common.model.KeyIndicatorUpdate;
import com.cgi.space.psi.common.model.KeyIndicator;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.KeyIndicatorApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.service.KeyIndicatorService;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cgi.space.psi.pss.stub.util.ControllerUtil.okOrNotFound;

/**
 * REST Controller for managing endpoints for key indicators.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/serviceQualityManagement/v2")
@Profile("!" + Profiles.PROVIDER)
public class KeyIndicatorController implements KeyIndicatorApi {

    @Autowired
    private KeyIndicatorService service;

    @Override
    @PsiOperation("TOD-06-03-01")
    public ResponseEntity<KeyIndicator> createKeyIndicator(KeyIndicatorCreate keyIndicatorCreate) {
        KeyIndicator result = service.createKeyIndicator(keyIndicatorCreate);
        log.info("Created new SLS: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    @PsiOperation("TOD-06-03-04")
    public ResponseEntity<KeyIndicator> retrieveKeyIndicator(String id, Optional<String> fields) {
        return okOrNotFound(service.getKeyIndicator(id));
    }

    @Override
    @PsiOperation("TOD-06-03-05")
    public ResponseEntity<List<KeyIndicator>> listKeyIndicator(Optional<String> fields, Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<KeyIndicator> allKeyIndicators = service.getAllKeyIndicators(pageable);
        return OffsetBasedPageRequest.toResponse(allKeyIndicators);
    }

    @Override
    @PsiOperation("TOD-06-03-02")
    public ResponseEntity<KeyIndicator> patchKeyIndicator(String id, KeyIndicatorUpdate keyIndicatorUpdate) {
        KeyIndicator patchedSls = service.updateKeyIndicator(id, keyIndicatorUpdate);
        HttpStatus status = patchedSls == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(patchedSls, status);
    }

    @Override
    @PsiOperation("TOD-06-03-03")
    public ResponseEntity<Void> deleteKeyIndicator(String id) {
        boolean result = service.deleteKeyIndicator(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

}
