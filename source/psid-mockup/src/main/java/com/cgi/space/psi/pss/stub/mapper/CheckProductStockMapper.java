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

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.cgi.space.psi.common.model.CheckProductStock;
import com.cgi.space.psi.common.model.CheckProductStockCreate;

/**
 * Mapper interface for {@link CheckProductStock CheckProductStocks}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CheckProductStockMapper {

    /**
     * The method maps a {@link CheckProductStockCreate} instance to a {@link CheckProductStock} object.
     *
     * @param customerBillCreate The {@link CheckProductStockCreate} object to be mapped.
     * @return The mapped {@link CheckProductStock}.
     */
    CheckProductStock toCheckProductStock(CheckProductStockCreate customerBillCreate);
}
