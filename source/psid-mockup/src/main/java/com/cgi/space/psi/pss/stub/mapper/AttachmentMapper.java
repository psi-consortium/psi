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

import com.cgi.space.psi.common.model.Attachment;
import com.cgi.space.psi.common.model.AttachmentFVO;
import com.cgi.space.psi.common.model.AttachmentMVO;

/**
 * Mapper interface for {@link Attachment Attachments}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttachmentMapper {

    /**
     * The method maps a {@link AttachmentFVO} instance to a {@link Attachment} object.
     *
     * @param attachmentCreate The {@link AttachmentFVO} object to be mapped.
     * @return The mapped {@link Attachment}.
     */
    Attachment toAttachment(AttachmentFVO attachmentCreate);

    /**
     * The method updates an {@link Attachment} object with the values contained in the {@link AttachmentMVO} object.
     *
     * @param attachmentUpdate The {@link AttachmentMVO} object that holds the fields to be updated.
     * @param attachment       The {@link Attachment} object to be updated.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAttachment(AttachmentMVO attachmentUpdate, @MappingTarget Attachment attachment);
}
