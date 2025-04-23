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
package com.cgi.space.psi.pss.stub.mapper;

import com.cgi.space.psi.common.model.CancelPerformanceJob;
import com.cgi.space.psi.common.model.CancelPerformanceJobCreate;
import com.cgi.space.psi.common.model.ModifyPerformanceJob;
import com.cgi.space.psi.common.model.ModifyPerformanceJobCreate;
import com.cgi.space.psi.common.model.ModifyPerformanceJobProfileValue;
import com.cgi.space.psi.common.model.PerformanceJob;
import com.cgi.space.psi.common.model.PerformanceJobCreate;
import com.cgi.space.psi.common.model.PerformanceJobFind;
import com.cgi.space.psi.common.model.PerformanceProfileValue;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for {@link PerformanceJob PerformanceJobs}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PerformanceJobMapper {

    /**
     * The method maps a {@link PerformanceJobCreate} object to a {@link PerformanceJob}.
     *
     * @param performanceJobCreate The {@link PerformanceJobCreate} object to be mapped.
     * @return The resulting {@link PerformanceJob}.
     */
    PerformanceJob toPerformanceJob(PerformanceJobCreate performanceJobCreate);

    /**
     * The method maps a {@link PerformanceJob} object to a {@link PerformanceJobFind}.
     *
     * @param report The {@link PerformanceJob} object to be mapped.
     * @return The resulting {@link PerformanceJobFind}.
     */
    PerformanceJobFind toPerformanceJobFind(PerformanceJob report);

    /**
     * The method updates an {@link PerformanceProfileValue} object with the values contained in the {@link ModifyPerformanceJobCreate} object.
     *
     * @param modifyPerformanceJob The {@link ModifyPerformanceJobProfileValue} object that holds the fields to be updated.
     * @param job The {@link PerformanceProfileValue} object to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePerformanceProfile(ModifyPerformanceJobProfileValue modifyPerformanceJob, @MappingTarget PerformanceProfileValue job);

    /**
     * The method maps a {@link ModifyPerformanceJobCreate} object to a {@link ModifyPerformanceJob}.
     *
     * @param modifyPerformanceJobCreate The {@link ModifyPerformanceJobCreate} object to be mapped.
     * @return The resulting {@link ModifyPerformanceJob}.
     */
    ModifyPerformanceJob toModifyPerformanceJob(ModifyPerformanceJobCreate modifyPerformanceJobCreate);

    /**
     * The method maps a {@link CancelPerformanceJobCreate} object to a {@link CancelPerformanceJob}.
     *
     * @param cancelPerformanceJobCreate The {@link CancelPerformanceJobCreate} object to be mapped.
     * @return The resulting {@link CancelPerformanceJob}.
     */
    public CancelPerformanceJob toCancelPerformanceJob(CancelPerformanceJobCreate cancelPerformanceJobCreate);

}
