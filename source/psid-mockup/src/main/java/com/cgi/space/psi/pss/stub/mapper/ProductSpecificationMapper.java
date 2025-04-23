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

import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.ProductSpecificationFVO;
import com.cgi.space.psi.common.model.ProductSpecificationMVO;

/**
 * Mapper interface for {@link ProductSpecification ProductSpecifications}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { CharacteristicValueSpecMapper.class, PartyRefMapper.class,
    TMFactory.class })
public interface ProductSpecificationMapper {

    /**
     * The method maps a {@link ProductSpecificationFVO} object to a {@link ProductSpecification}.
     *
     * @param productSpecificationCreate The {@link ProductSpecificationFVO} to be mapped.
     * @return The resulting {@link ProductSpecification}.
     */
    ProductSpecification toProductSpecification(ProductSpecificationFVO productSpecificationCreate);

    /**
     * The method updates a {@link ProductSpecification} object based on a provided {@link ProductSpecificationMVO}
     * object.
     *
     * @param productSpecificationUpdate The {@link ProductSpecificationMVO} object containing the fields to be updated.
     * @param productSpecification       The {@link ProductSpecification} that needs to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductSpecification(ProductSpecificationMVO productSpecificationUpdate,
                                    @MappingTarget ProductSpecification productSpecification);

}
