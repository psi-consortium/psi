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

import com.cgi.space.psi.common.model.Product;
import com.cgi.space.psi.common.model.ProductFVO;
import com.cgi.space.psi.common.model.ProductMVO;

/**
 * Mapper interface for {@link Product Products}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { CharacteristicMapper.class, TMFactory.class })
public interface ProductMapper {

    /**
     * The method maps a {@link ProductFVO} object to a {@link Product}.
     *
     * @param productCreate The {@link ProductFVO} to be mapped.
     * @return The resulting {@link Product}.
     */
    Product toProduct(ProductFVO productCreate);

    /**
     * The method updates a {@link Product} object based on a provided {@link ProductMVO}
     * object.
     *
     * @param productUpdate The {@link ProductMVO} object containing the fields to be updated.
     * @param product       The {@link Product} that needs to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(ProductMVO productUpdate, @MappingTarget Product product);

}
