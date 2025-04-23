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

import com.cgi.space.psi.common.model.GeographicAddress;
import com.cgi.space.psi.common.model.Mission;
import com.cgi.space.psi.common.model.MissionFVO;
import com.cgi.space.psi.common.model.MissionMVO;
import com.cgi.space.psi.common.model.MissionStatusType;
import com.cgi.space.psi.pss.stub.controller.MissionController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.MissionFilter;
import com.cgi.space.psi.pss.stub.mapper.MissionMapper;
import com.cgi.space.psi.pss.stub.util.StreamUtils;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import org.apache.commons.lang3.StringUtils;
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
import lombok.experimental.ExtensionMethod;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link Mission}s.
 */
@Service
@ExtensionMethod({ StreamUtils.class })
public class MissionService {

    @Autowired
    private MissionMapper missionMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link Mission} object.
     *
     * @param missionFVO the {@link MissionFVO} object based on which
     *            the {@link Mission} is created.
     * @return the newly created {@link Mission} object.
     */
    public Mission createMission(MissionFVO missionFVO) {
        Mission mission = missionMapper.toMission(missionFVO);
        mission.setId(UUID.randomUUID().toString());
        mission.setAtType(mission.getClass().getSimpleName());
        mission.setAtBaseType("Mission");
        mission.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        if (mission.getStatus() == null) {
            mission.setStatus(MissionStatusType.DRAFT);
        }
        setInnerIds(mission);

        Link self = linkTo(methodOn(MissionController.class)
                .retrieveMission(mission.getId(), Optional.empty())).withSelfRel();
        mission.setHref(self.toUri());

        return template.save(mission);
    }

    private void setInnerIds(Mission mission) {
        mission.getPlace().stream().filter(GeographicAddress.class).forEach(address -> {
            if (address.getId() == null) {
                address.setId(UUID.randomUUID().toString());
            }
        });
        mission.getAsset().forEach(asset -> {
            if (asset.getId() == null) {
                asset.setId(UUID.randomUUID().toString());
            }
        });
    }

    /**
     * The method retrieves an existing {@link Mission}.
     *
     * @param id The Identifier of the {@link Mission} to be retrieved.
     * @return The retrieved {@link Mission}.
     */
    public Mission getMission(String id) {
        return template.findById(id, Mission.class);
    }

    /**
     * The method retrieves all {@link Mission Missions}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering missions to be returned to the response (optional)
     * @return all {@link Mission Missions}
     */
    public Page<Mission> getAllMissions(Pageable pageable, Optional<MissionFilter> filter) {
        return template.find(buildQuery(filter), pageable, Mission.class);
    }

    private Query buildQuery(Optional<MissionFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("description"), filterProps.getDescriptionContains());
            builder.createContains(List.of("name", "description"), filterProps.getNameOrDescriptionContains());
            builder.createEqualsAny("status", filterProps.getStatus());
            builder.createContains(List.of("relatedParty.partyOrPartyRole.name"), filterProps.getRelatedPartyContains());
            if (StringUtils.isNotBlank(filterProps.getRunningBefore())) {
                builder.orOperator(List.of(
                        Criteria.where("timeframe").isNull(),
                        Criteria.where("timeframe.startDateTime").isNull(),
                        Criteria.where("timeframe.startDateTime").lte(OffsetDateTime.parse(filterProps.getRunningBefore()))));
            }
            if (StringUtils.isNotBlank(filterProps.getRunningAfter())) {
                builder.orOperator(List.of(
                        Criteria.where("timeframe").isNull(),
                        Criteria.where("timeframe.endDateTime").isNull(),
                        Criteria.where("timeframe.endDateTime").gt(OffsetDateTime.parse(filterProps.getRunningAfter()))));
            }
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing {@link Mission Mission}.
     *
     * @param id The Identifier of the {@link Mission} to be updated.
     * @param missionMVO The {@link MissionMVO} object containing the updates to be applied to
     *            the {@link Mission} object.
     * @return the updated {@link Mission Mission}
     */
    public Mission updateMission(String id, MissionMVO missionMVO) {
        Mission mission = template.findOne(Query.query(Criteria.where("id").is(id)), Mission.class);
        missionMapper.updateMission(missionMVO, mission);
        mission.setLastUpdate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        setInnerIds(mission);

        return template.save(mission);
    }

    /**
     * The method deletes an existing {@link Mission Mission}.
     *
     * @param id The Identifier of the {@link Mission} to be deleted.
     * @return <code>true</code> if {@link Mission} successfully deleted, <code>false</code>
     *         otherwise.
     */
    public boolean deleteMission(String id) {
        Mission mission = template.findById(id, Mission.class);
        return template.remove(mission).getDeletedCount() > 0;
    }
}
