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

import com.cgi.space.psi.common.model.Document;
import com.cgi.space.psi.common.model.DocumentFVO;
import com.cgi.space.psi.common.model.DocumentMVO;

/**
 * Mapper interface for {@link Document Documents}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { CharacteristicMapper.class, PartyRefMapper.class, TMFactory.class })
public interface DocumentMapper {

    /**
     * The method maps a {@link DocumentFVO} instance to a {@link Document} object.
     *
     * @param documentCreate The {@link DocumentFVO} object to be mapped.
     * @return The mapped {@link Document}.
     */
    Document toDocument(DocumentFVO documentCreate);

    /**
     * The method updates an {@link Document} object with the values contained in the {@link DocumentMVO} object.
     *
     * @param documentUpdate The {@link DocumentMVO} object that holds the fields to be updated.
     * @param document       The {@link Document} object to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDocument(DocumentMVO documentUpdate, @MappingTarget Document document);
}
