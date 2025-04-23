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
import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.ProductSpecificationFVO;
import com.cgi.space.psi.common.model.ProductSpecificationMVO;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.ProductTemplateApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.ProductTemplateFilter;
import com.cgi.space.psi.pss.stub.service.ProductTemplateService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for product templates.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/productCatalog/v2")
@Profile("!" + Profiles.PROVIDER)
public class ProductTemplateController implements ProductTemplateApi {

    @Autowired
    private ProductTemplateService service;

    /**
     * An endpoint for creating a new product template.
     *
     * @param productTemplate The {@link ProductSpecificationFVO} to be used to create a product
     *            template (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the created template.
     */
    @Override
    @PsiOperation("TOD-04-03-01")
    public ResponseEntity<ProductSpecification> createProductTemplate(ProductSpecificationFVO productTemplate, Optional<String> fields) {
        ProductSpecification result = service.createProductTemplate(productTemplate);
        log.info("Created new product template: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint that removes an existing product template with given identifier.
     *
     * @param id Identifier of the product template (required)
     */
    @Override
    @PsiOperation("TOD-04-03-03")
    public ResponseEntity<Void> deleteProductTemplate(String id) {
        boolean result = service.deleteProductTemplate(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }

    /**
     * An endpoint for listing all product templates.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return All product templates.
     */
    @Override
    @PsiOperation("TOD-04-03-05")
    public ResponseEntity<List<ProductSpecification>> listProductTemplate(Optional<ProductTemplateFilter> filter,
            Optional<String> fields, Optional<Integer> offset, Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<ProductSpecification> allProductTemplates = service.getAllProductTemplates(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allProductTemplates);
    }

    /**
     * An endpoint that updates a product template with given identifier.
     *
     * @param id Identifier of the product template (required)
     * @param productTemplate The {@link ProductSpecificationMVO} to be used to update the
     *            product template (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return ResponseEntity with the updated product template
     */
    @Override
    @PsiOperation("TOD-04-03-02")
    public ResponseEntity<ProductSpecification> patchProductTemplate(String id, ProductSpecificationMVO productTemplate, Optional<String> fields) {
        return new ResponseEntity<>(service.updateProductTemplate(id, productTemplate), HttpStatus.OK);
    }

    /**
     * An endpoint for retrieving the product template with given identifier.
     *
     * @param id Identifier of the product template (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved product template.
     */
    @Override
    @PsiOperation("TOD-04-03-04")
    public ResponseEntity<ProductSpecification> retrieveProductTemplate(String id, Optional<String> fields) {
        return okOrNotFound(service.getProductTemplate(id));
    }

}
