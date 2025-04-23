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

import com.cgi.space.psi.common.model.StatusChange;
import com.cgi.space.psi.common.model.TroubleTicket;
import com.cgi.space.psi.common.model.TroubleTicketFVO;
import com.cgi.space.psi.common.model.TroubleTicketStatusType;
import com.cgi.space.psi.common.model.TroubleTicketMVO;
import com.cgi.space.psi.pss.stub.controller.TroubleTicketController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.TroubleTicketFilter;
import com.cgi.space.psi.pss.stub.mapper.TroubleTicketMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for trouble tickets.
 */
@Service
public class TroubleTicketService {

    private static final String TICKET_ID = "id";
    @Autowired
    private TroubleTicketMapper troubleTicketMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new trouble ticket object.
     *
     * @param troubleTicketCreate the {@link TroubleTicketFVO} object based on
     *            which
     *            the trouble ticket is created.
     * @return the newly created {@link TroubleTicket} object of the trouble ticket.
     */
    public TroubleTicket createTroubleTicket(TroubleTicketFVO troubleTicketCreate) {
        OffsetDateTime now = OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        TroubleTicket troubleTicket = troubleTicketMapper.toTroubleTicket(troubleTicketCreate);
        troubleTicket.setId(UUID.randomUUID().toString());
        troubleTicket.setExpectedResolutionDate(troubleTicket.getRequestedResolutionDate());
        troubleTicket.setStatus(TroubleTicketStatusType.PENDING);
        troubleTicket.setLastUpdate(now);
        troubleTicket.addStatusChangeHistoryItem(new StatusChange()
                .statusChangeDate(now)
                .status(troubleTicket.getStatus())
                .statusChangeReason(troubleTicket.getStatusChangeReason()));

        Link self = linkTo(methodOn(TroubleTicketController.class)
                .retrieveTroubleTicket(troubleTicket.getId(), Optional.empty())).withSelfRel();
        troubleTicket.setHref(self.toUri());

        return template.save(troubleTicket);
    }

    /**
     * The method retrieves an existing trouble ticket.
     *
     * @param id The Identifier of the trouble ticket to be retrieved.
     * @return The retrieved {@link TroubleTicket} from the trouble ticket.
     */
    public TroubleTicket getTroubleTicket(String id) {
        return template.findOne(Query.query(Criteria.where(TICKET_ID).is(id)), TroubleTicket.class);
    }

    /**
     * The method retrieves all trouble tickets.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all trouble tickets.
     */
    public Page<TroubleTicket> getAllTroubleTickets(Pageable pageable, Optional<TroubleTicketFilter> filter) {
        return template.find(buildQuery(filter), pageable, TroubleTicket.class);
    }

    private Query buildQuery(Optional<TroubleTicketFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("description"), filterProps.getDescriptionContains());
            builder.createContains(List.of("name", "description"), filterProps.getNameOrDescriptionContains());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing trouble ticket.
     *
     * @param id The Identifier of the trouble ticket to be updated.
     * @param troubleTicketUpdate The {@link TroubleTicketMVO} object containing
     *            the updates to be applied to the trouble ticket object.
     * @return the updated {@link TroubleTicket} of the trouble ticket.
     */
    public TroubleTicket updateTroubleTicket(String id, TroubleTicketMVO troubleTicketUpdate) {
        TroubleTicket troubleTicket =
                template.findOne(Query.query(Criteria.where(TICKET_ID).is(id)), TroubleTicket.class);
        if (troubleTicket != null) {
            OffsetDateTime now = OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS);
            TroubleTicketStatusType oldStatus = troubleTicket.getStatus();

            troubleTicketMapper.updateTroubleTicket(troubleTicketUpdate, troubleTicket);
            troubleTicket.setLastUpdate(now);

            if (oldStatus != troubleTicket.getStatus()) {
                troubleTicket.setStatusChangeDate(now);
                troubleTicket.addStatusChangeHistoryItem(new StatusChange()
                        .statusChangeDate(now)
                        .status(troubleTicket.getStatus())
                        .statusChangeReason(troubleTicket.getStatusChangeReason()));
            }

            return template.save(troubleTicket);
        }
        else {
            return null;
        }
    }

    /**
     * The method deletes an existing trouble ticket.
     *
     * @param id The Identifier of the trouble ticket to be deleted.
     * @return <code>true</code> if trouble ticket is successfully deleted,
     *         <code>false</code> otherwise.
     */
    public boolean deleteTroubleTicket(String id) {
        return template.remove(Query.query(Criteria.where(TICKET_ID).is(id)), TroubleTicket.class).getDeletedCount() > 0;
    }

}
