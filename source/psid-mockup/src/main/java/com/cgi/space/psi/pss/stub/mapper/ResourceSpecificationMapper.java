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

import com.cgi.space.psi.common.model.LogicalResourceSpecification;
import com.cgi.space.psi.common.model.PhysicalResourceSpecification;
import com.cgi.space.psi.common.model.ResourceSpecification;
import com.cgi.space.psi.common.model.ResourceSpecificationFVO;
import com.cgi.space.psi.common.model.ResourceSpecificationMVO;
import com.cgi.space.psi.pss.stub.mapper.ResourceSpecificationMapper.ResourceSpecificationMapperDecorator;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.BeanMapping;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper interface for {@link ResourceSpecification ResourceSpecifications}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { CharacteristicValueSpecMapper.class, PartyRefMapper.class,
    TMFactory.class })
@DecoratedWith(ResourceSpecificationMapperDecorator.class)
public interface ResourceSpecificationMapper {

    /**
     * The method maps a {@link ResourceSpecificationFVO} object to a {@link ResourceSpecification}.
     *
     * @param resourceSpecificationCreate The {@link ResourceSpecificationFVO} object to be mapped.
     * @return The resulting {@link ResourceSpecification} object.
     */
    ResourceSpecification toResourceSpecification(ResourceSpecificationFVO resourceSpecificationCreate);

    /**
     * The method updates a {@link ResourceSpecification} object based on a provided {@link ResourceSpecificationMVO} object.
     *
     * @param resourceSpecificationUpdate The {@link ResourceSpecificationMVO} object that contains the fields to be updated.
     * @param resourceSpecification       The {@link ResourceSpecification} object that is updated based on the
     *                                    {@link ResourceSpecificationMVO} object.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateResourceSpecification(ResourceSpecificationMVO resourceSpecificationUpdate,
                                     @MappingTarget ResourceSpecification resourceSpecification);

    /**
     * A Decorator class which decides on the right mapper to be used for mapping
     * {@link ResourceSpecification ResourceSpecifications}.
     */
    abstract class ResourceSpecificationMapperDecorator implements ResourceSpecificationMapper {

        @Autowired
        private ResourceSpecificationMapper delegate;
        @Autowired
        private LogicalResourceSpecificationMapper logicalResourceSpecificationMapper;
        @Autowired
        private PhysicalResourceSpecificationMapper physicalResourceSpecificationMapper;

        /**
         * Based on the type of the {@link ResourceSpecificationFVO} object, uses the right mapper
         * to map a {@link ResourceSpecificationFVO} object to a {@link ResourceSpecification}.
         * Sample mappers are the {@link LogicalResourceSpecificationMapper} and the {@link PhysicalResourceSpecificationMapper}.
         *
         * @param resourceSpecificationCreate The {@link ResourceSpecificationFVO} object to be mapped.
         * @return The resulting {@link ResourceSpecification}.
         */
        @Override
        public ResourceSpecification toResourceSpecification(ResourceSpecificationFVO resourceSpecificationCreate) {
            final String type = resourceSpecificationCreate.getAtType();
            if (StringUtils.isNotEmpty(type) && type.equals(LogicalResourceSpecification.class.getSimpleName())) {
                return logicalResourceSpecificationMapper.toLogicalResourceSpecification(resourceSpecificationCreate);
            } else if (StringUtils.isNotEmpty(type) && type.equals(PhysicalResourceSpecification.class.getSimpleName())) {
                return physicalResourceSpecificationMapper.toPhysicalResourceSpecification(resourceSpecificationCreate);
            } else {
                return delegate.toResourceSpecification(resourceSpecificationCreate);
            }
        }

    }

}
