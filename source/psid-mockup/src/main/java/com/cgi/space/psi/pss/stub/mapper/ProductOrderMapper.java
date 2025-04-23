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

import com.cgi.space.psi.common.model.ProductOrder;
import com.cgi.space.psi.common.model.ProductOrderFVO;
import com.cgi.space.psi.common.model.ProductOrderMVO;

/**
 * Mapper interface for {@link ProductOrder ProductOrders}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { PartyRefMapper.class, TMFactory.class })
public interface ProductOrderMapper {

    /**
     * The method maps a {@link ProductOrderFVO} object to a {@link ProductOrder}.
     *
     * @param productOrderCreate The {@link ProductOrderFVO} object to be mapped.
     * @return The resulting {@link ProductOrder}.
     */
    ProductOrder toProductOrder(ProductOrderFVO productOrderCreate);

    /**
     * The method updates a {@link ProductOrder} given a {@link ProductOrderMVO} object.
     *
     * @param productOrderUpdate The {@link ProductOrderMVO} object holding the fields to be updated.
     * @param productOrder       The {@link ProductOrder} to be updated based on the {@link ProductOrderMVO} object.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductOrder(ProductOrderMVO productOrderUpdate, @MappingTarget ProductOrder productOrder);
}
