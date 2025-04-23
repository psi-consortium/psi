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

import com.cgi.space.psi.common.model.CustomerInquiry;
import com.cgi.space.psi.common.model.CustomerInquiryFVO;
import com.cgi.space.psi.common.model.CustomerInquiryMVO;

/**
 * Mapper interface for {@link CustomerInquiry CustomerInquiries}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerInquiryMapper {

    /**
     * The method maps a {@link CustomerInquiryFVO} instance to a {@link CustomerInquiry} object.
     *
     * @param customerInquiryFVO The {@link CustomerInquiryFVO} object to be mapped.
     * @return The mapped {@link CustomerInquiry}.
     */
    CustomerInquiry toCustomerInquiry(CustomerInquiryFVO customerInquiryFVO);

    /**
     * Creates a copy of the provided {@link CustomerInquiry}.
     *
     * @param customerInquiry The {@link CustomerInquiry} to be copied.
     * @return The new {@link CustomerInquiry}.
     */
    CustomerInquiry copy(CustomerInquiry customerInquiry);

    /**
     * The method updates a {@link CustomerInquiry} given a {@link CustomerInquiryMVO} object.
     *
     * @param customerInquiryMVO The {@link CustomerInquiryMVO} object holding the fields to be updated.
     * @param customerInquiry       The {@link CustomerInquiry} to be updated based on the {@link CustomerInquiryMVO} object.
     * @return The updated {@link CustomerInquiry}, this allows for fluent invocations.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CustomerInquiry updateCustomerInquiry(CustomerInquiryMVO customerInquiryMVO, @MappingTarget CustomerInquiry customerInquiry);
}
