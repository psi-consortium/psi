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
import com.cgi.space.psi.common.model.ServiceSpecification;
import com.cgi.space.psi.common.model.ServiceSpecificationFVO;
import com.cgi.space.psi.common.model.ServiceSpecificationMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.ServiceSpecificationApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.ServiceSpecificationFilter;
import com.cgi.space.psi.pss.stub.service.ServiceSpecificationService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link ServiceSpecification}s.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/serviceCatalog/v2")
@Profile("!" + Profiles.PROVIDER)
public class ServiceSpecificationController implements ServiceSpecificationApi {

    @Autowired
    private ServiceSpecificationService service;

    /**
     * An endpoint for creating a new {@link ServiceSpecification}.
     *
     * @param serviceSpecificationCreate The {@link ServiceSpecificationFVO} to be used to create {@link ServiceSpecification} (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created {@link ServiceSpecification}
     */
    @Override
    @PsiOperation("TOD-02-02-01")
    public ResponseEntity<ServiceSpecification> createServiceSpecification(ServiceSpecificationFVO serviceSpecificationCreate, Optional<String> fields) {
        ServiceSpecification result = service.createServiceSpecification(serviceSpecificationCreate);
        log.info("Created new service specification: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for retrieving the {@link ServiceSpecification} with given identifier.
     *
     * @param id     Identifier of the ServiceSpecification (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link ServiceSpecification}
     */
    @Override
    @PsiOperation("TOD-02-02-04")
    public ResponseEntity<ServiceSpecification> retrieveServiceSpecification(String id, Optional<String> fields) {
        return okOrNotFound(service.getServiceSpecification(id));
    }

    /**
     * An endpoint for listing all {@link ServiceSpecification}s.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit  Requested number of resources to be provided in response (optional)
     * @return All {@link ServiceSpecification}s.
     */
    @Override
    @PsiOperation("TOD-02-02-05")
    public ResponseEntity<List<ServiceSpecification>> listServiceSpecification(Optional<ServiceSpecificationFilter> filter,
                                                                               Optional<String> fields, Optional<Integer> offset,
                                                                               Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<ServiceSpecification> allServiceSpecifications = service.getAllServiceSpecifications(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allServiceSpecifications);
    }

    /**
     * An endpoint that removes an existing {@link ServiceSpecification} with given identifier.
     *
     * @param id Identifier of the ServiceSpecification (required)
     */
    @Override
    @PsiOperation("TOD-02-02-03")
    public ResponseEntity<Void> deleteServiceSpecification(String id) {
        boolean result = service.deleteServiceSpecification(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint that updates an {@link ServiceSpecification} with given identifier.
     *
     * @param id                         Identifier of the ServiceSpecification (required)
     * @param serviceSpecificationUpdate The ServiceSpecification to be updated (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated {@link ServiceSpecification}
     */
    @Override
    @PsiOperation("TOD-02-02-02")
    public ResponseEntity<ServiceSpecification> patchServiceSpecification(String id, ServiceSpecificationMVO serviceSpecificationUpdate,
            Optional<String> fields) {
        return new ResponseEntity<>(service.updateServiceSpecification(id, serviceSpecificationUpdate), HttpStatus.OK);
    }
}
