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

import com.cgi.space.psi.common.model.KeyIndicator;
import com.cgi.space.psi.common.model.KeyIndicatorCreate;
import com.cgi.space.psi.common.model.KeyIndicatorUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for {@link KeyIndicator KeyIndicators}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KeyIndicatorMapper {

    /**
     * The method maps an {@link KeyIndicatorCreate} instance to an {@link KeyIndicator} object.
     *
     * @param keyIndicatorCreate The {@link KeyIndicatorCreate} object to be mapped.
     * @return The mapped {@link KeyIndicator}.
     */
    KeyIndicator toKeyIndicator(KeyIndicatorCreate keyIndicatorCreate);

    /**
     * The method updates an {@link KeyIndicator} object with the values contained in the {@link KeyIndicatorUpdate} object.
     *
     * @param keyIndicatorUpdate The {@link KeyIndicatorUpdate} object that holds the fields to be updated.
     * @param keyIndicator       The {@link KeyIndicator} object to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateKeyIndicator(KeyIndicatorUpdate keyIndicatorUpdate, @MappingTarget KeyIndicator keyIndicator);

}
