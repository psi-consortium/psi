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

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.BeanMapping;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.space.psi.common.model.LogicalResource;
import com.cgi.space.psi.common.model.PhysicalResource;
import com.cgi.space.psi.common.model.Resource;
import com.cgi.space.psi.common.model.ResourceFVO;
import com.cgi.space.psi.common.model.ResourceMVO;

/**
 * Mapper interface for {@link Resource Resources}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { CharacteristicMapper.class, PartyRefMapper.class, TMFactory.class })
@DecoratedWith(ResourceMapper.ResourceMapperDecorator.class)
public interface ResourceMapper {

    /**
     * The method maps a {@link ResourceFVO} object to a {@link Resource}.
     *
     * @param resourceCreate The {@link ResourceFVO} object to be mapped.
     * @return The resulting {@link Resource} object.
     */
    Resource toResource(ResourceFVO resourceCreate);

    /**
     * The method updates a {@link Resource} object based on a provided {@link ResourceMVO} object.
     *
     * @param resourceUpdate The {@link ResourceMVO} object that contains the fields to be updated.
     * @param resource       The {@link Resource} object that is updated based on the {@link ResourceMVO} object.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateResource(ResourceMVO resourceUpdate, @MappingTarget Resource resource);

    /**
     * A Decorator class which decides on the right mapper to be used for mapping
     * {@link Resource Resources}.
     */
    abstract class ResourceMapperDecorator implements ResourceMapper {

        @Autowired
        private ResourceMapper delegate;
        @Autowired
        private LogicalResourceMapper logicalResourceMapper;
        @Autowired
        private PhysicalResourceMapper physicalResourceMapper;

        /**
         * Based on the type of the {@link ResourceFVO} object, uses the right mapper
         * to map a {@link ResourceFVO} object to a {@link Resource}.
         * Sample mappers are the {@link LogicalResourceMapper} and the {@link PhysicalResourceMapper}.
         *
         * @param resourceCreate The {@link ResourceFVO} object to be mapped.
         * @return The resulting {@link Resource}.
         */
        @Override
        public Resource toResource(ResourceFVO resourceCreate) {
            final String type = resourceCreate.getAtType();
            if (StringUtils.isNotEmpty(type) && type.equals(LogicalResource.class.getSimpleName())) {
                return logicalResourceMapper.toLogicalResource(resourceCreate);
            } else if (StringUtils.isNotEmpty(type) && type.equals(PhysicalResource.class.getSimpleName())) {
                return physicalResourceMapper.toPhysicalResource(resourceCreate);
            } else {
                return delegate.toResource(resourceCreate);
            }
        }
    }
}