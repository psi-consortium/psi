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

import com.cgi.space.psi.common.model.Organization;
import com.cgi.space.psi.common.model.OrganizationFVO;
import com.cgi.space.psi.common.model.OrganizationMVO;

/**
 * Mapper interface for {@link Organization Organiizations}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = TMFactory.class)
public interface OrganizationMapper {

    /**
     * The method maps an {@link OrganizationFVO} object to an {@link Organization}.
     *
     * @param organizationCreate The {@link OrganizationFVO} object to be mapped.
     * @return The resulting {@link Organization} object.
     */
    Organization toOrganization(OrganizationFVO organizationCreate);

    /**
     * The method updates an {@link Organization} object based on the fields provided in an {@link OrganizationMVO} object.
     *
     * @param organizationUpdate The {@link OrganizationMVO} object containing the fields to be updated.
     * @param organization       The {@link Organization} object to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrganization(OrganizationMVO organizationUpdate, @MappingTarget Organization organization);
}
