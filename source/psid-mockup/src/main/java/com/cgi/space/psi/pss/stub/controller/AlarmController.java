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

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.model.Alarm;
import com.cgi.space.psi.common.request.OffsetBasedPageRequest;
import com.cgi.space.psi.pss.stub.api.AlarmApi;
import com.cgi.space.psi.pss.stub.service.AlarmService;

import lombok.extern.slf4j.Slf4j;

/**
 * REST Controller for retrieve {@link Alarm Alarms}.
 */
@Slf4j
@RestController
@RequestMapping("/psi-api/alarmManagement/v2")
public class AlarmController implements AlarmApi {

    @Autowired
    private AlarmService service;

    @Override
    @PsiOperation("TOD-06-06-02")
    public ResponseEntity<List<Alarm>> listAlarm(@Valid Optional<String> fields, @Valid Optional<Integer> offset, @Valid Optional<Integer> limit) {
        final Pageable pageable = OffsetBasedPageRequest.of(offset, limit);
        final Page<Alarm> allAlarms = service.getAllAlarms(pageable);
        return OffsetBasedPageRequest.toResponse(allAlarms);
    }

    @Override
    @PsiOperation("TOD-06-06-01")
    public ResponseEntity<Alarm> retrieveAlarm(String id, @Valid Optional<String> fields) {
        Alarm alarm = service.getAlarmById(id);
        HttpStatus status = alarm != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(alarm, status);
    }

}
