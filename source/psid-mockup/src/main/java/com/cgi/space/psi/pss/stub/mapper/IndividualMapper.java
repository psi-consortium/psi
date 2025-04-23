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

import com.cgi.space.psi.common.model.Individual;
import com.cgi.space.psi.common.model.IndividualFVO;
import com.cgi.space.psi.common.model.IndividualMVO;

/**
 * Mapper interface for {@link Individual Individuals}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = TMFactory.class)
public interface IndividualMapper {

    /**
     * The method maps an {@link IndividualFVO} instance to an {@link Individual} object.
     *
     * @param individualCreate The {@link IndividualFVO} object to be mapped.
     * @return The mapped {@link Individual}.
     */
    Individual toIndividual(IndividualFVO individualCreate);

    /**
     * The method updates an {@link Individual} object with the values contained in the {@link IndividualMVO} object.
     *
     * @param individualUpdate The {@link IndividualMVO} object that holds the fields to be updated.
     * @param individual       The {@link Individual} object to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateIndividual(IndividualMVO individualUpdate, @MappingTarget Individual individual);
}
