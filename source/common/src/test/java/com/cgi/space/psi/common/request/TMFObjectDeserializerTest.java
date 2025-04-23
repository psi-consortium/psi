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
package com.cgi.space.psi.common.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.cgi.space.psi.common.config.PSIJacksonAutoConfiguration;
import com.cgi.space.psi.common.model.CustomerInquiry;
import com.cgi.space.psi.common.model.EventCreate;
import com.cgi.space.psi.common.model.Geometry;
import com.cgi.space.psi.common.model.InquiredProductCharacteristic;
import com.cgi.space.psi.common.model.Polygon;
import com.cgi.space.psi.common.model.ProductOrder;
import com.cgi.space.psi.common.model.StringCharacteristic;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TMFObjectDeserializerTest {

    private final ObjectMapper objectMapper;

    public TMFObjectDeserializerTest() {
        var builder = new Jackson2ObjectMapperBuilder();
        new PSIJacksonAutoConfiguration().jacksonFilterCustomizer().customize(builder);
        objectMapper = builder.build();
    }

    @Test
    public void testDeserializeProductOrder() throws Exception {
        EventCreate result = objectMapper.readValue("{\"domain\": \"test\", \"event\": {\"category\": \"TestCat\", "
                + "\"billingAccount\": {\"id\": \"123\", \"@type\": \"BillingAccountRef\"}, \"@baseType\": \"Distraction\", \"@type\": \"ProductOrder\"}}", EventCreate.class);
        assertThat(result, is(notNullValue()));
        assertThat(result.getDomain(), is(equalTo("test")));
        assertThat(result.getEvent(), is(instanceOf(ProductOrder.class)));
    }

    @Test
    public void testDeserializeCustomerInquiry() throws Exception {
        EventCreate result = objectMapper.readValue(
                "{\"domain\": \"test\", \"event\": {\"customerProfile\": [{\"name\": \"foo\", \"value\": \"bar\"}], \"inquiredProvider\": [], \"@baseType\": \"Distraction\", \"@type\": \"CustomerInquiry\"}}",
                EventCreate.class);
        assertThat(result, is(notNullValue()));
        assertThat(result.getDomain(), is(equalTo("test")));
        assertThat(result.getEvent(), is(instanceOf(CustomerInquiry.class)));
    }

    @Test
    public void testDeserializeUnknownType() throws Exception {
        JacksonException result = assertThrows(JacksonException.class, () -> {
            objectMapper.readValue("{\"domain\": \"test\", \"event\": {\"@type\": \"Unknown\"}}", EventCreate.class);
        });
        assertThat(result.getMessage(), containsString("Unknown"));
    }

    @Test
    public void testDeserializeNoAtType() throws Exception {
        var json = "{\"domain\": \"test\", \"event\": {\"category\": \"TestCat\"}}";
        var result = objectMapper.readValue(json, EventCreate.class);
        assertThat(result, is(notNullValue()));
        assertThat(result.getDomain(), is(equalTo("test")));
        assertThat(result.getEvent(), is(instanceOf(LinkedHashMap.class)));
        log.debug("result:{}", result.toString());
    }

    @Test
    public void testDeserializeMemberToPrimitive() throws Exception {
        var json = "{\"domain\": \"test\", \"event\": 123}";
        var result = objectMapper.readValue(json, EventCreate.class);
        assertThat(result, is(notNullValue()));
        assertThat(result.getDomain(), is(equalTo("test")));
        assertThat(result.getEvent(), is(instanceOf(Integer.class)));
        assertThat(result.getEvent(), is(equalTo(123)));
        log.debug("result:{}", result.toString());
    }

    @Test
    public void testDeserializeCharacteristic() throws Exception {
        String json = "            {\r\n" + //
                "                \"@type\": \"StringCharacteristic\",\r\n" + //
                "                \"name\": \"fooName\",\r\n" + //
                "                \"valueType\": \"string\",\r\n" + //
                "                \"value\": \"barValue\"\r\n" + //
                "            }";
        StringCharacteristic result = objectMapper.readValue(json, StringCharacteristic.class);
        assertThat(result, is(notNullValue()));
        assertThat(result.getName(), is(equalTo("fooName")));
        assertThat(result.getValue(), is(equalTo("barValue")));
    }

    @Test
    public void testDeserializeGeometryInInquiredProductCharacteristic() throws Exception {
        String json = "{\r\n" + // InquiredProductCharacteristic
                "          \"id\": \"footprintGeometry\",\r\n" + //
                "          \"name\": \"footprintGeometry\",\r\n" + //
                "          \"inquiredProductCharacteristicValue\": [\r\n" + //
                "            {\r\n" + //
                "              \"unitOfMeasure\": \"degree\",\r\n" + //
                "              \"valueType\": \"Geometry\",\r\n" + //
                "              \"value\": {\r\n" + //
                "                \"type\": \"Polygon\",\r\n" + // <---- important
                "                \"coordinates\": [\r\n" + //
                "                  [\r\n" + //
                "                    [\r\n" + //
                "                      8.4375,\r\n" + //
                "                      46.49839225859763\r\n" + //
                "                    ],\r\n" + //
                "                    [\r\n" + //
                "                      3.427734375,\r\n" + //
                "                      37.09023980307208\r\n" + //
                "                    ],\r\n" + //
                "                    [\r\n" + //
                "                      23.73046875,\r\n" + //
                "                      36.03133177633187\r\n" + //
                "                    ],\r\n" + //
                "                    [\r\n" + //
                "                      8.4375,\r\n" + //
                "                      46.49839225859763\r\n" + //
                "                    ]\r\n" + //
                "                  ]\r\n" + //
                "                ]\r\n" + //
                "              }\r\n" + //
                "            }\r\n" + //
                "          ]\r\n" + //
                "        }";

        var result = objectMapper.readValue(json, InquiredProductCharacteristic.class);
        assertThat(result, is(notNullValue()));
        var asJson = objectMapper.writeValueAsString(result);
        Geometry innerObject = (Geometry) result.getInquiredProductCharacteristicValue().get(0).getValue();
        log.debug("{} (inner Geometry type:{}) as JSON:{}", result, innerObject.getType(), asJson);
        var redeserialized = objectMapper.readValue(asJson, InquiredProductCharacteristic.class);
        assertEquals(result, redeserialized);

        // var exceptionBasedOnMissingTypeByIgnoreAnnotation = assertThrows(JsonMappingException.class, () -> {
        //     objectMapper.readValue(asJson, InquiredProductCharacteristic.class);
        // });
        // assertThat(exceptionBasedOnMissingTypeByIgnoreAnnotation.getMessage(), containsString("java.util.LinkedHashMap[\"coordinates\"]"));

    }

    @Test
    public void testDeserializeGeometry() throws Exception {
        String json = "            {\r\n" + //
                "                \"type\": \"Polygon\",\r\n" + // <---- important
                "                \"coordinates\": [\r\n" + //
                "                  [\r\n" + //
                "                    [\r\n" + //
                "                      8.4375,\r\n" + //
                "                      46.49839225859763\r\n" + //
                "                    ],\r\n" + //
                "                    [\r\n" + //
                "                      3.427734375,\r\n" + //
                "                      37.09023980307208\r\n" + //
                "                    ],\r\n" + //
                "                    [\r\n" + //
                "                      23.73046875,\r\n" + //
                "                      36.03133177633187\r\n" + //
                "                    ],\r\n" + //
                "                    [\r\n" + //
                "                      8.4375,\r\n" + //
                "                      46.49839225859763\r\n" + //
                "                    ]\r\n" + //
                "                  ]\r\n" + //
                "                ]\r\n" + //
                "            }";

        var result = objectMapper.readValue(json, Geometry.class);
        assertThat(result, is(notNullValue()));
        log.debug("{} --> type:{}, class:{}", result.toString(), result.getType(), result.getClass());
        var asJson = objectMapper.writeValueAsString(result);
        log.debug("as JSON:{}", asJson);
        result = objectMapper.readValue(asJson, Polygon.class);
        assertThat(result, is(notNullValue()));
        log.debug("{} --> type:{}, class:{}", result.toString(), result.getType(), result.getClass());
    }

}
