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

import com.cgi.space.psi.common.model.BooleanArrayCharacteristic;
import com.cgi.space.psi.common.model.BooleanArrayCharacteristicFVO;
import com.cgi.space.psi.common.model.BooleanArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.BooleanCharacteristic;
import com.cgi.space.psi.common.model.BooleanCharacteristicFVO;
import com.cgi.space.psi.common.model.BooleanCharacteristicMVO;
import com.cgi.space.psi.common.model.Characteristic;
import com.cgi.space.psi.common.model.CharacteristicFVO;
import com.cgi.space.psi.common.model.CharacteristicMVO;
import com.cgi.space.psi.common.model.FloatCharacteristic;
import com.cgi.space.psi.common.model.FloatCharacteristicFVO;
import com.cgi.space.psi.common.model.FloatCharacteristicMVO;
import com.cgi.space.psi.common.model.IntegerArrayCharacteristic;
import com.cgi.space.psi.common.model.IntegerArrayCharacteristicFVO;
import com.cgi.space.psi.common.model.IntegerArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.IntegerCharacteristic;
import com.cgi.space.psi.common.model.IntegerCharacteristicFVO;
import com.cgi.space.psi.common.model.IntegerCharacteristicMVO;
import com.cgi.space.psi.common.model.NumberArrayCharacteristic;
import com.cgi.space.psi.common.model.NumberArrayCharacteristicFVO;
import com.cgi.space.psi.common.model.NumberArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.NumberCharacteristic;
import com.cgi.space.psi.common.model.NumberCharacteristicFVO;
import com.cgi.space.psi.common.model.NumberCharacteristicMVO;
import com.cgi.space.psi.common.model.ObjectArrayCharacteristic;
import com.cgi.space.psi.common.model.ObjectArrayCharacteristicFVO;
import com.cgi.space.psi.common.model.ObjectArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.ObjectCharacteristic;
import com.cgi.space.psi.common.model.ObjectCharacteristicFVO;
import com.cgi.space.psi.common.model.ObjectCharacteristicMVO;
import com.cgi.space.psi.common.model.StringArrayCharacteristic;
import com.cgi.space.psi.common.model.StringArrayCharacteristicFVO;
import com.cgi.space.psi.common.model.StringArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.StringCharacteristic;
import com.cgi.space.psi.common.model.StringCharacteristicFVO;
import com.cgi.space.psi.common.model.StringCharacteristicMVO;

/**
 * Mapper interface for {@link Characteristic Characteristics}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CharacteristicMapper {

    /**
     * The method maps a {@link CharacteristicFVO} instance to a {@link Characteristic} object.
     *
     * @param characteristicFVO The {@link CharacteristicFVO} object to be mapped.
     * @return The mapped {@link Characteristic}.
     */
    @SubclassMapping(source = BooleanArrayCharacteristicFVO.class, target = BooleanArrayCharacteristic.class)
    @SubclassMapping(source = BooleanCharacteristicFVO.class, target = BooleanCharacteristic.class)
    @SubclassMapping(source = FloatCharacteristicFVO.class, target = FloatCharacteristic.class)
    @SubclassMapping(source = IntegerArrayCharacteristicFVO.class, target = IntegerArrayCharacteristic.class)
    @SubclassMapping(source = IntegerCharacteristicFVO.class, target = IntegerCharacteristic.class)
    @SubclassMapping(source = NumberArrayCharacteristicFVO.class, target = NumberArrayCharacteristic.class)
    @SubclassMapping(source = NumberCharacteristicFVO.class, target = NumberCharacteristic.class)
    @SubclassMapping(source = ObjectArrayCharacteristicFVO.class, target = ObjectArrayCharacteristic.class)
    @SubclassMapping(source = ObjectCharacteristicFVO.class, target = ObjectCharacteristic.class)
    @SubclassMapping(source = StringArrayCharacteristicFVO.class, target = StringArrayCharacteristic.class)
    @SubclassMapping(source = StringCharacteristicFVO.class, target = StringCharacteristic.class)
    public Characteristic characteristicFVOToCharacteristic(CharacteristicFVO characteristicFVO);

    /**
     * The method maps a {@link CharacteristicMVO} instance to a {@link Characteristic} object.
     *
     * @param characteristicMVO The {@link CharacteristicMVO} object to be mapped.
     * @return The mapped {@link Characteristic}.
     */
    @SubclassMapping(source = BooleanArrayCharacteristicMVO.class, target = BooleanArrayCharacteristic.class)
    @SubclassMapping(source = BooleanCharacteristicMVO.class, target = BooleanCharacteristic.class)
    @SubclassMapping(source = FloatCharacteristicMVO.class, target = FloatCharacteristic.class)
    @SubclassMapping(source = IntegerArrayCharacteristicMVO.class, target = IntegerArrayCharacteristic.class)
    @SubclassMapping(source = IntegerCharacteristicMVO.class, target = IntegerCharacteristic.class)
    @SubclassMapping(source = NumberArrayCharacteristicMVO.class, target = NumberArrayCharacteristic.class)
    @SubclassMapping(source = NumberCharacteristicMVO.class, target = NumberCharacteristic.class)
    @SubclassMapping(source = ObjectArrayCharacteristicMVO.class, target = ObjectArrayCharacteristic.class)
    @SubclassMapping(source = ObjectCharacteristicMVO.class, target = ObjectCharacteristic.class)
    @SubclassMapping(source = StringArrayCharacteristicMVO.class, target = StringArrayCharacteristic.class)
    @SubclassMapping(source = StringCharacteristicMVO.class, target = StringCharacteristic.class)
    public Characteristic characteristicMVOToCharacteristic(CharacteristicMVO characteristicMVO);

}
