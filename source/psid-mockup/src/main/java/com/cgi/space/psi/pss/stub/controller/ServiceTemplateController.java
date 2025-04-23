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
import com.cgi.space.psi.pss.stub.api.ServiceTemplateApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.ServiceTemplateFilter;
import com.cgi.space.psi.pss.stub.service.ServiceTemplateService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for service templates.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/serviceCatalog/v2")
@Profile("!" + Profiles.PROVIDER)
public class ServiceTemplateController implements ServiceTemplateApi {

    @Autowired
    private ServiceTemplateService service;

    /**
     * An endpoint for creating a new service template.
     *
     * @param serviceTemplate The {@link ServiceSpecificationFVO} to be used to create a service
     *            template (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created template.
     */
    @Override
    @PsiOperation("TOD-04-02-01")
    public ResponseEntity<ServiceSpecification> createServiceTemplate(ServiceSpecificationFVO serviceTemplate, Optional<String> fields) {
        ServiceSpecification result = service.createServiceTemplate(serviceTemplate);
        log.info("Created new service template: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint that removes an existing service template with given identifier.
     *
     * @param id Identifier of the service template (required)
     */
    @Override
    @PsiOperation("TOD-04-02-03")
    public ResponseEntity<Void> deleteServiceTemplate(String id) {
        boolean result = service.deleteServiceTemplate(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint for listing all service templates.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return All service templates.
     */
    @Override
    @PsiOperation("TOD-04-02-05")
    public ResponseEntity<List<ServiceSpecification>> listServiceTemplate(Optional<ServiceTemplateFilter> filter,
            Optional<String> fields, Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<ServiceSpecification> allServiceTemplates = service.getAllServiceTemplates(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allServiceTemplates);
    }

    /**
     * An endpoint that updates a service template with given identifier.
     *
     * @param id Identifier of the service template (required)
     * @param serviceTemplate The {@link ServiceSpecificationMVO} to be used to update the
     *            service template (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated service template
     */
    @Override
    @PsiOperation("TOD-04-02-02")
    public ResponseEntity<ServiceSpecification> patchServiceTemplate(String id, ServiceSpecificationMVO serviceTemplate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateServiceTemplate(id, serviceTemplate), HttpStatus.OK);
    }

    /**
     * An endpoint for retrieving the service template with given identifier.
     *
     * @param id Identifier of the service template (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved service template.
     */
    @Override
    @PsiOperation("TOD-04-02-04")
    public ResponseEntity<ServiceSpecification> retrieveServiceTemplate(String id, Optional<String> fields) {
        return okOrNotFound(service.getServiceTemplate(id));
    }

}
