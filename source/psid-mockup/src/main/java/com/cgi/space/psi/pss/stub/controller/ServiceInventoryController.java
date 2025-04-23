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
import com.cgi.space.psi.common.model.Service;
import com.cgi.space.psi.common.model.ServiceFVO;
import com.cgi.space.psi.common.model.ServiceMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.ServiceApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.ServiceFilter;
import com.cgi.space.psi.pss.stub.service.ServiceInventoryService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link Service}s.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/serviceInventory/v2")
@Profile("!" + Profiles.PROVIDER)
public class ServiceInventoryController implements ServiceApi {

    @Autowired
    private ServiceInventoryService service;

    /**
     * An endpoint for creating a new {@link Service}.
     *
     * @param serviceCreate The {@link ServiceFVO} to be used to create {@link Service}
     *            (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link Service}
     */
    @Override
    @PsiOperation("TOD-05-02-01")
    public ResponseEntity<Service> createService(ServiceFVO serviceCreate, Optional<String> fields) {
        Service result = service.createService(serviceCreate);
        log.info("Created new service: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for retrieving the {@link Service} with given identifier.
     *
     * @param id Identifier of the Service (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link Service}
     */
    @Override
    @PsiOperation("TOD-05-02-04")
    public ResponseEntity<Service> retrieveService(String id, Optional<String> fields) {
        return okOrNotFound(service.getService(id));
    }

    /**
     * An endpoint for listing all {@link Service}s.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return All {@link Service}s.
     */
    @Override
    @PsiOperation("TOD-05-02-05")
    public ResponseEntity<List<Service>> listService(Optional<ServiceFilter> filter,
            Optional<String> fields, Optional<Integer> offset,
            Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Service> allServices = service.getAllServices(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allServices);
    }

    /**
     * An endpoint that removes an existing {@link Service} with given identifier.
     *
     * @param id Identifier of the Service (required)
     */
    @Override
    @PsiOperation("TOD-05-02-03")
    public ResponseEntity<Void> deleteService(String id) {
        boolean result = service.deleteService(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint that updates an {@link Service} with given identifier.
     *
     * @param id Identifier of the Service (required)
     * @param serviceUpdate The Service to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link Service}
     */
    @Override
    @PsiOperation("TOD-05-02-02")
    public ResponseEntity<Service> patchService(String id, ServiceMVO serviceUpdate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateService(id, serviceUpdate), HttpStatus.OK);
    }
}
