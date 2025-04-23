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
import org.mapstruct.SubclassMapping;

import com.cgi.space.psi.common.model.CharacteristicValueSpecification;
import com.cgi.space.psi.common.model.CharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.CharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.IntegerArrayCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.IntegerArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.IntegerArrayCharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.IntegerCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.IntegerCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.IntegerCharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.MapArrayCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.MapArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.MapArrayCharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.MapCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.MapCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.MapCharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.NumberArrayCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.NumberArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.NumberArrayCharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.NumberCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.NumberCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.NumberCharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.ObjectArrayCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.ObjectArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.ObjectArrayCharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.ObjectCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.ObjectCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.ObjectCharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.StringArrayCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.StringArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.StringArrayCharacteristicValueSpecificationMVO;
import com.cgi.space.psi.common.model.StringCharacteristicValueSpecification;
import com.cgi.space.psi.common.model.StringCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.StringCharacteristicValueSpecificationMVO;

/**
 * Mapper interface for {@link CharacteristicValueSpecification CharacteristicValueSpecifications}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharacteristicValueSpecMapper {

    /**
     * The method maps a {@link CharacteristicValueSpecificationFVO} instance to a
     * {@link CharacteristicValueSpecification} object.
     *
     * @param characteristicValueSpecificationFVO The {@link CharacteristicValueSpecificationFVO}
     *            object to be mapped.
     * @return The mapped {@link CharacteristicValueSpecification}.
     */
    @SubclassMapping(source = IntegerArrayCharacteristicValueSpecificationFVO.class, target = IntegerArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = IntegerCharacteristicValueSpecificationFVO.class, target = IntegerCharacteristicValueSpecification.class)
    @SubclassMapping(source = MapArrayCharacteristicValueSpecificationFVO.class, target = MapArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = MapCharacteristicValueSpecificationFVO.class, target = MapCharacteristicValueSpecification.class)
    @SubclassMapping(source = NumberArrayCharacteristicValueSpecificationFVO.class, target = NumberArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = NumberCharacteristicValueSpecificationFVO.class, target = NumberCharacteristicValueSpecification.class)
    @SubclassMapping(source = ObjectArrayCharacteristicValueSpecificationFVO.class, target = ObjectArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = ObjectCharacteristicValueSpecificationFVO.class, target = ObjectCharacteristicValueSpecification.class)
    @SubclassMapping(source = StringArrayCharacteristicValueSpecificationFVO.class, target = StringArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = StringCharacteristicValueSpecificationFVO.class, target = StringCharacteristicValueSpecification.class)
    CharacteristicValueSpecification
            characteristicValueSpecificationFVOToCharacteristicValueSpecification(CharacteristicValueSpecificationFVO characteristicValueSpecificationFVO);

    /**
     * The method maps a {@link CharacteristicValueSpecificationMVO} instance to a
     * {@link CharacteristicValueSpecification} object.
     *
     * @param characteristicValueSpecificationMVO The {@link CharacteristicValueSpecificationMVO}
     *            object to be mapped.
     * @return The mapped {@link CharacteristicValueSpecification}.
     */
    @SubclassMapping(source = IntegerArrayCharacteristicValueSpecificationMVO.class, target = IntegerArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = IntegerCharacteristicValueSpecificationMVO.class, target = IntegerCharacteristicValueSpecification.class)
    @SubclassMapping(source = MapArrayCharacteristicValueSpecificationMVO.class, target = MapArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = MapCharacteristicValueSpecificationMVO.class, target = MapCharacteristicValueSpecification.class)
    @SubclassMapping(source = NumberArrayCharacteristicValueSpecificationMVO.class, target = NumberArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = NumberCharacteristicValueSpecificationMVO.class, target = NumberCharacteristicValueSpecification.class)
    @SubclassMapping(source = ObjectArrayCharacteristicValueSpecificationMVO.class, target = ObjectArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = ObjectCharacteristicValueSpecificationMVO.class, target = ObjectCharacteristicValueSpecification.class)
    @SubclassMapping(source = StringArrayCharacteristicValueSpecificationMVO.class, target = StringArrayCharacteristicValueSpecification.class)
    @SubclassMapping(source = StringCharacteristicValueSpecificationMVO.class, target = StringCharacteristicValueSpecification.class)
    CharacteristicValueSpecification
            characteristicValueSpecificationMVOToCharacteristicValueSpecification(CharacteristicValueSpecificationMVO characteristicValueSpecificationMVO);

}
