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

import com.cgi.space.psi.common.model.ProductOffering;
import com.cgi.space.psi.common.model.ProductOfferingFVO;
import com.cgi.space.psi.common.model.ProductOfferingMVO;

/**
 * Mapper interface for {@link ProductOffering ProductOfferings}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductOfferingMapper {

    /**
     * The method maps a {@link ProductOfferingFVO} object to a {@link ProductOffering}.
     *
     * @param productOfferingCreate The {@link ProductOfferingFVO} object to be mapped.
     * @return The resulting {@link ProductOffering}.
     */
    ProductOffering toProductOffering(ProductOfferingFVO productOfferingCreate);

    /**
     * The method updates a {@link ProductOffering} given a {@link ProductOfferingMVO} object.
     *
     * @param productOfferingUpdate The {@link ProductOfferingMVO} object holding the fields to be updated.
     * @param productOffering       The {@link ProductOffering} to be updated based on the {@link ProductOfferingMVO} object.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductOffering(ProductOfferingMVO productOfferingUpdate, @MappingTarget ProductOffering productOffering);
}
