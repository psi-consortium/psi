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

import com.cgi.space.psi.common.model.ProductOrder;
import com.cgi.space.psi.common.model.ProductOrderFVO;
import com.cgi.space.psi.common.model.ProductOrderStateType;
import com.cgi.space.psi.common.model.ProductOrderMVO;
import com.cgi.space.psi.common.request.AitfTestAwareRunnable;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.controller.ProductOrderController;
import com.cgi.space.psi.pss.stub.event.PublishedEvent;
import com.cgi.space.psi.pss.stub.event.ReceivedEvent;
import com.cgi.space.psi.pss.stub.mapper.ProductOrderMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link ProductOrder}.
 */
@Slf4j
@Service
public class ProductOrderService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private ProductOrderMapper productOrderMapper;
    @Autowired
    private PageableMongoTemplate template;
    @Autowired
    private Profiles profiles;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    /**
     * The method creates a new {@link ProductOrder} object.
     *
     * @param productOrderCreate the {@link ProductOrderFVO} object based on which
     *            the {@link ProductOrder} is created.
     * @return the newly created {@link ProductOrder} object.
     */
    public ProductOrder createProductOrder(ProductOrderFVO productOrderCreate) {
        ProductOrder order = productOrderMapper.toProductOrder(productOrderCreate);
        order.setId(UUID.randomUUID().toString());
        order.setState(ProductOrderStateType.PENDING);
        order.setCreationDate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));

        Link self = linkTo(methodOn(ProductOrderController.class)
                .retrieveProductOrder(order.getId(), Optional.empty())).withSelfRel();
        order.setHref(self.toUri());

        template.save(order);
        applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_ORDER, PublishedEvent.TYPE_CREATED, order));
        return order;
    }

    /**
     * The method updates an existing {@link ProductOrder ProductOrder}.
     *
     * @param productOrder The {@link ProductOrder} that is currently persisted and to be updated.
     * @param productOrderUpdate The {@link ProductOrderMVO} object containing the updates to be
     *            applied on the {@link ProductOrder} object.
     * @return the updated {@link ProductOrder ProductOrder}
     */
    public ProductOrder updateProductOrder(ProductOrder productOrder, ProductOrderMVO productOrderUpdate) {
        productOrderMapper.updateProductOrder(productOrderUpdate, productOrder);
        template.save(productOrder);
        applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_ORDER, PublishedEvent.TYPE_UPDATED, productOrder));
        return productOrder;
    }

    /**
     * The method retrieves an existing {@link ProductOrder}.
     *
     * @param id The Identifier of the {@link ProductOrder} to be retrieved.
     * @return The retrieved {@link ProductOrder}.
     */
    public ProductOrder getProductOrder(String id) {
        return template.findById(id, ProductOrder.class);
    }

    /**
     * The method retrieves all {@link ProductOrder ProductOrders}.
     *
     * @param pageable Collection of pagination parameters
     * @return all {@link ProductOrder ProductOrders}
     */
    public Page<ProductOrder> getAllProductOrders(Pageable pageable) {
        return template.find(new Query(), pageable, ProductOrder.class);
    }

    private void updateState(String id, ProductOrderStateType stateType) {
        final ProductOrder order = template.findById(id, ProductOrder.class);
        if (order != null) {
            order.setState(stateType);
            template.save(order);
            applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_ORDER, PublishedEvent.TYPE_UPDATED, order));
            log.info("Set status of ProductOrder {} to {}", id, stateType);
        }
        else {
            log.error("ProductOrder {} not found", id);
        }
    }

    /**
     * Listens for received events concerning product orders.
     *
     * @param event the received event
     */
    @EventListener
    public void onApplicationEvent(ReceivedEvent event) {
        log.info("Received event: {} {}", event.getTopic(), event.getType());
        if (PublishedEvent.TOPIC_ORDER.equals(event.getTopic())) {
            if (PublishedEvent.TYPE_CREATED.equals(event.getType())) {
                ProductOrder order = (ProductOrder) event.getValue();
                template.save(order);
                if (profiles.isProviderActive()) {
                    executor.schedule(new AitfTestAwareRunnable(() -> {
                        updateState(order.getId(), ProductOrderStateType.ACKNOWLEDGED);
                    }), 5, TimeUnit.SECONDS);

                    executor.schedule(new AitfTestAwareRunnable(() -> {
                        updateState(order.getId(), ProductOrderStateType.COMPLETED);
                    }), 10, TimeUnit.SECONDS);
                }
            }
            else if (PublishedEvent.TYPE_UPDATED.equals(event.getType())) {
                template.save(event.getValue());
            }
            else if (PublishedEvent.TYPE_DELETED.equals(event.getType())) {
                template.remove(event.getValue());
            }
        }
    }
}
