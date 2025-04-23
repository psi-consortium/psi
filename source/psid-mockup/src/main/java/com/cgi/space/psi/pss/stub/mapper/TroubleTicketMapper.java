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

import com.cgi.space.psi.common.model.TroubleTicket;
import com.cgi.space.psi.common.model.TroubleTicketFVO;
import com.cgi.space.psi.common.model.TroubleTicketMVO;

/**
 * Mapper interface for {@link TroubleTicket TroubleTickets}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { PartyRefMapper.class, TMFactory.class })
public interface TroubleTicketMapper {

    /**
     * The method maps a {@link TroubleTicketFVO} object to a {@link TroubleTicket}.
     *
     * @param troubleTicketCreate The {@link TroubleTicketFVO} to be mapped.
     * @return The resulting {@link TroubleTicket}.
     */
    TroubleTicket toTroubleTicket(TroubleTicketFVO troubleTicketCreate);

    /**
     * The method updates a {@link TroubleTicket} object based on a provided
     * {@link TroubleTicketMVO}
     * object.
     *
     * @param troubleTicketUpdate The {@link TroubleTicketMVO} object containing the fields to be
     *            updated.
     * @param troubleTicket The {@link TroubleTicket} that needs to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTroubleTicket(TroubleTicketMVO troubleTicketUpdate,
            @MappingTarget TroubleTicket troubleTicket);

}
