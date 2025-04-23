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

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cgi.space.psi.common.model.CustomerInquiry;
import com.cgi.space.psi.common.model.InquiredProduct;
import com.cgi.space.psi.common.model.InquiredProductRef;
import com.cgi.space.psi.common.model.InquiredProductRelationship;
import com.cgi.space.psi.common.model.InquiryResult;
import com.cgi.space.psi.common.model.Money;
import com.cgi.space.psi.common.model.Note;
import com.cgi.space.psi.common.model.Price;
import com.cgi.space.psi.common.model.Product;
import com.cgi.space.psi.common.model.ProductOffering;
import com.cgi.space.psi.common.model.ProductOfferingRef;
import com.cgi.space.psi.common.model.ProductRef;
import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.ProductSpecificationRef;
import com.cgi.space.psi.common.model.TimePeriod;
import com.cgi.space.psi.common.model.TotalPrice;

/**
 * Service class that demonstrates the interface of a matchmaking algorithm.
 *
 * All methods return the full list of available entities. A real algorithm would:
 * <ol>
 * <li>Look up resource and service specifications that implement at least one feature requested by
 * the customer with the given characteristics.
 * The result is used for the following steps, but can also be directly fetched by another PSS to
 * find candidate providers for a ITT.
 * <li>Look up product specifications containing these resources and services.
 * <li>Look up offerings which use the correct characteristic values and
 * <ul>
 * <li>contain at least one of the products (bundlesOnly = false)
 * <li>contain a set of products that fullfills all required features (bundlesOnly = true)
 * </ul>
 * <li>Look up available product, service and resource instances from the inventory
 * </ol>
 * The definition of "using correct characteristics" may vary depending on the actual
 * characteristic.
 * Enumerable characteristics (e.g. the band) have to match exactly, while others may allow the next
 * higher/lower value (e.g. bandwidth/latency).
 */
@Service
public class MatchmakingService {

    @Autowired
    private ResourceSpecificationService resourceSpecificationService;
    @Autowired
    private ServiceSpecificationService serviceSpecificationService;
    @Autowired
    private ProductSpecificationService productSpecificationService;
    @Autowired
    private ProductOfferingService productOfferingService;
    @Autowired
    private ProductInventoryService productInventoryService;

    /**
     * Returns all {@link ProductSpecification ProductSpecifications} with their corresponding
     * {@link ProductOffering ProductOfferings} matching the inquiry.
     *
     * @param inquiry the request for product specifications by the customer
     * @param pageable Collection of pagination parameters
     * @return the inquiry results
     */
    public Page<InquiryResult> getInquiryResults(CustomerInquiry inquiry, Pageable pageable) {

        InquiredProduct inquiredProduct = inquiry.getInquiredProduct().get(0); // Restricted to the first item only - for the mockup.
        Page<ProductSpecification> productSpecifications =
                productSpecificationService.getAllProductSpecifications(pageable, Optional.empty());
        List<InquiryResult> result = new ArrayList<>(productSpecifications.getSize());
        long rank = pageable.isPaged() ? (pageable.getOffset() + 1) : 1;

        for (ProductSpecification productSpecification : productSpecifications) {

            List<Product> products = productInventoryService.getAllByProductSpecificationId(productSpecification.getId());
            var productOfferings = productOfferingService.getAllByProductSpecificationId(productSpecification.getId());

            if (productOfferings.isEmpty()) {
                result.add(createInquiryResult(inquiredProduct, productSpecification, Collections.emptyList(), products, rank));
                rank++;
            }
            else {
                for (ProductOffering productOffering : productOfferings) {
                    result.add(createInquiryResult(inquiredProduct, productSpecification, List.of(productOffering), products, rank));
                    rank++;
                }
            }
        }
        return new PageImpl<>(result, pageable, productSpecifications.getTotalElements());
    }

    private InquiryResult createInquiryResult(
            InquiredProduct inquiredProduct,
            ProductSpecification productSpecification,
            List<ProductOffering> productOfferings,
            List<Product> products,
            long rank) {
        InquiryResult inquiryResult = new InquiryResult();
        inquiryResult.id(UUID.randomUUID().toString());

        inquiryResult.addProductSpecificationItem(productSpecification);
        inquiryResult.productOffering(productOfferings);
        inquiryResult.product(products.isEmpty() ? products : products.subList(0, products.size() / 2 + 1));

        inquiryResult.setInquiredProductRelationship(
                createRelationships(inquiredProduct, productSpecification, productOfferings, products));

        inquiryResult.setPriority(BigDecimal.valueOf(rank));
        if (rank == 1) {
            inquiryResult.addNoteItem(new Note().text("This is the best product."));
        }
        if (inquiryResult.getProductOffering().isEmpty()) {
            inquiryResult.addNoteItem(new Note().text("Contact us to receive a customized offering."));
        }

        // Set a fake total price
        Optional<Float> totalPrice = getFakeTotalPrice(inquiryResult.getInquiredProductRelationship());
        if (totalPrice.isPresent()) {
            inquiryResult.setTotalPrice(List.of(
                    new TotalPrice()
                            .price(new Price()
                                    .dutyFreeAmount(new Money()
                                            .unit("EUR")
                                            .value(totalPrice.get())))));
        }

        return inquiryResult;
    }

    private List<InquiredProductRelationship> createRelationships(
            InquiredProduct inquiredProduct,
            ProductSpecification productSpec,
            List<ProductOffering> productOfferings,
            List<Product> products) {
        int max = Math.max(1, Math.max(productOfferings.size(), products.size()));

        return IntStream.range(0, max)
                .mapToObj(i -> createRelationship(
                        inquiredProduct,
                        productSpec,
                        getIndex(productOfferings, i),
                        getIndex(products, i)))
                .collect((Collectors.toList()));
    }

    private <T> T getIndex(List<T> data, int index) {
        return (data != null && data.size() > index) ? data.get(index) : null;
    }

    private InquiredProductRelationship createRelationship(
            InquiredProduct inquiredProduct,
            ProductSpecification productSpec,
            ProductOffering productOffering,
            Product product) {
        var relationship = new InquiredProductRelationship()
                .inquiredProductRef(new InquiredProductRef()
                        .id(inquiredProduct.getId())
                        .name(inquiredProduct.getName()));

        if (productSpec != null) {
            relationship.productSpecificationRef(new ProductSpecificationRef()
                    .id(productSpec.getId())
                    .name(productSpec.getName()));

        }
        if (productOffering != null) {
            relationship.setProductOfferingRef(new ProductOfferingRef()
                    .id(productOffering.getId())
                    .name(productOffering.getName()));
        }
        if (product != null) {
            relationship.setProductRef(new ProductRef()
                    .id(product.getId())
                    .name(product.getName()));
        }

        if (productOffering != null) {
            // fallback for offerings that do not provide a valid dutyFreeAmount
            Money money = new Money().unit("EUR").value(99.88F);

            var poPrice = productOffering.getProductOfferingPrice();
            if (productOffering != null
                    && poPrice != null
                    && poPrice.size() > 0
                    && poPrice.get(0).getPrice() != null) {
                money = productOffering.getProductOfferingPrice().get(0).getPrice();
            }

            relationship.setTotalPrice(List.of(new TotalPrice()
                    .priceType("total")
                    .price(new Price().dutyFreeAmount(money))));
        }

        var servicePeriod = inquiredProduct.getServicePeriod() != null
                ? inquiredProduct.getServicePeriod()
                : new TimePeriod()
                        .startDateTime(OffsetDateTime.now())
                        .endDateTime(OffsetDateTime.now());
        relationship.servicePeriod(new TimePeriod().startDateTime(servicePeriod.getStartDateTime()).endDateTime(servicePeriod.getEndDateTime()));

        relationship.quantity(1);
        relationship.coverage(InquiredProductRelationship.CoverageEnum.COMPLETE);
        // relationship.relationshipCharacteristic(...)

        return relationship;
    }

    private Optional<Float> getFakeTotalPrice(List<InquiredProductRelationship> relationships) {
        var totalPrice = (float) relationships
                .stream()
                .map(i -> i.getTotalPrice() != null
                        && i.getTotalPrice().size() > 0
                        && i.getTotalPrice().get(0).getPrice() != null
                        && i.getTotalPrice().get(0).getPrice().getDutyFreeAmount() != null
                        && i.getTotalPrice().get(0).getPrice().getDutyFreeAmount().getValue() != null
                                ? i.getTotalPrice().get(0).getPrice().getDutyFreeAmount().getValue() : 0F)
                .mapToDouble(i -> i)
                .sum();

        return totalPrice == 0F ? Optional.empty() : Optional.of(Float.valueOf(totalPrice));
    }
}
