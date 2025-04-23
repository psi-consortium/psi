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

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.cgi.space.psi.common.model.ServiceLevelObjective;
import com.cgi.space.psi.common.model.ServiceLevelObjectiveCreate;
import com.cgi.space.psi.common.model.ServiceLevelObjectiveUpdate;

/**
 * Mapper interface for {@link ServiceLevelObjective ServiceLevelObjectives}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceLevelObjectiveMapper {

    /**
     * The method maps a {@link ServiceLevelObjectiveCreate} object to a {@link ServiceLevelObjective}.
     *
     * @param sloCreate The {@link ServiceLevelObjectiveCreate} object to be mapped.
     * @return The resulting {@link ServiceLevelObjective}.
     */
    ServiceLevelObjective toServiceLevelObjective(ServiceLevelObjectiveCreate sloCreate);

    /**
     * The method updates a {@link ServiceLevelObjective} given a {@link ServiceLevelObjectiveUpdate} object.
     *
     * @param sloUpdate The {@link ServiceLevelObjectiveUpdate} object holding the fields to be updated.
     * @param slo       The {@link ServiceLevelObjective} to be updated based on the {@link ServiceLevelObjectiveUpdate} object.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateServiceLevelObjective(ServiceLevelObjectiveUpdate sloUpdate, @MappingTarget ServiceLevelObjective slo);
}
