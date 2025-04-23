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
import com.cgi.space.psi.common.model.Mission;
import com.cgi.space.psi.common.model.MissionFVO;
import com.cgi.space.psi.common.model.MissionMVO;
import com.cgi.space.psi.common.model.Organization;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.MissionApi;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.filter.MissionFilter;
import com.cgi.space.psi.pss.stub.service.MissionService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for managing endpoints for {@link Mission}s.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/missionManagement/v2")
@Profile(Profiles.PSS)
public class MissionController implements MissionApi {

    @Autowired
    private MissionService service;

    /**
     * An endpoint for creating a new {@link Mission}.
     *
     * @param missionFVO The {@link MissionFVO} to be used to create {@link Mission}
     *            (required)
     * @return ResponseEntity with the created {@link Mission}
     */
    @Override
    @PsiOperation("TOD-03-04-01")
    public ResponseEntity<Mission> createMission(MissionFVO missionFVO) {
        Mission result = service.createMission(missionFVO);
        log.info("Created new mission: {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * An endpoint for listing all {@link Missio Organizations}.
     *
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return All {@link Organization Organizations}.
     */
    @Override
    @PsiOperation("TOD-03-04-05")
    public ResponseEntity<List<Mission>> listMission(Optional<MissionFilter> filter, Optional<String> fields, Optional<Integer> offset,
            Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Mission> allMissions = service.getAllMissions(pageable, filter);
        return OffsetBasedPageRequest.toResponse(allMissions);
    }

    /**
     * An endpoint for retrieving the {@link Mission} with given identifier.
     *
     * @param id Identifier of the Mission (required)
     * @param fields Comma-separated properties to provide in response (optional)
     * @return ResponseEntity with the retrieved {@link Mission}
     */
    @Override
    @PsiOperation("TOD-03-04-04")
    public ResponseEntity<Mission> retrieveMission(String id, Optional<String> fields) {
        return okOrNotFound(service.getMission(id));
    }

    /**
     * An endpoint that updates an {@link Mission} with given identifier.
     *
     * @param id Identifier of the Mission (required)
     * @param missionMVO The Mission to be updated (required)
     * @return ResponseEntity with the updated {@link Mission}
     */
    @Override
    @PsiOperation("TOD-03-04-02")
    public ResponseEntity<Mission> patchMission(String id, MissionMVO missionMVO) {
        return new ResponseEntity<>(service.updateMission(id, missionMVO), HttpStatus.OK);
    }

    /**
     * An endpoint that removes an existing {@link Mission} with given identifier.
     *
     * @param id Identifier of the Mission (required)
     */
    @Override
    @PsiOperation("TOD-03-04-03")
    public ResponseEntity<Void> deleteMission(String id) {
        boolean result = service.deleteMission(id);
        HttpStatus status = result ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(status);
    }
}
