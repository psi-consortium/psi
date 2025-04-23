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
package com.cgi.space.psi.pss.stub.mapper;

import com.cgi.space.psi.common.model.Mission;
import com.cgi.space.psi.common.model.MissionFVO;
import com.cgi.space.psi.common.model.MissionMVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for {@link Mission Missions}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MissionMapper {

    /**
     * The method maps a {@link MissionFVO} object to a {@link Mission}.
     *
     * @param missionFVO The {@link MissionFVO} object to be mapped.
     * @return The resulting {@link Mission} object.
     */
    Mission toMission(MissionFVO missionFVO);

    /**
     * The method updates a {@link Mission} object based on a provided {@link MissionMVO} object.
     *
     * @param missionMVO The {@link MissionMVO} object that contains the fields to be updated.
     * @param mission The {@link Mission} object that is updated based on the
     *            {@link MissionMVO} object.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMission(MissionMVO missionMVO, @MappingTarget Mission mission);

}
