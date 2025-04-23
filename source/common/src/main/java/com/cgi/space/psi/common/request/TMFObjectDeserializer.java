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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cgi.space.psi.common.model.Alarm;
import com.cgi.space.psi.common.model.CustomerBill;
import com.cgi.space.psi.common.model.CustomerInquiry;
import com.cgi.space.psi.common.model.GeoJson;
import com.cgi.space.psi.common.model.GeoJsonFeature;
import com.cgi.space.psi.common.model.GeoJsonFeatureCollection;
import com.cgi.space.psi.common.model.Geometry;
import com.cgi.space.psi.common.model.ProductOrder;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.TokenBuffer;

import lombok.extern.slf4j.Slf4j;

/**
 * A JsonDeserializer that introspects several type-markers to find the best target type to
 * deserialize to.
 */
@Slf4j
public class TMFObjectDeserializer extends JsonDeserializer<Object> {

    private static final Map<String, Class<?>> TOKEN_TO_CLASS = new HashMap<String, Class<?>>();

    static {
        TOKEN_TO_CLASS.putAll(Map.of(
                "Alarm", Alarm.class,
                "GeoJson", GeoJson.class,
                "Feature", GeoJsonFeature.class,
                "GeoJsonFeature", GeoJsonFeature.class,
                "FeatureCollection", GeoJsonFeatureCollection.class,
                "GeoJsonFeatureCollection", GeoJsonFeatureCollection.class,
                "Polygon", Geometry.class,
                "Point", Geometry.class,
                "Geometry", Geometry.class));
        TOKEN_TO_CLASS.putAll(Map.of(
                ProductOrder.class.getSimpleName(), ProductOrder.class,
                CustomerInquiry.class.getSimpleName(), CustomerInquiry.class,
                CustomerBill.class.getSimpleName(), CustomerBill.class));
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        assert p.getCurrentToken() != null;

        Object result;
        switch (p.getCurrentToken()) {
            case VALUE_TRUE:
                result = Boolean.TRUE;
                break;
            case VALUE_FALSE:
                result = Boolean.FALSE;
                break;
            case VALUE_STRING:
                result = p.readValueAs(String.class);
                break;
            case VALUE_NUMBER_INT:
                result = p.readValueAs(Integer.class);
                break;
            case VALUE_NUMBER_FLOAT:
                result = p.readValueAs(Double.class);
                break;
            case START_OBJECT:
            case END_OBJECT:
                final TokenBuffer buffer = ctxt.bufferAsCopyOfValue(p); // copy the whole node so we can read it multiple times
                result = readByType(buffer, ctxt);
                break;
            default:
                log.error("deserializing of json token:{} with value:{} currently not supported '{}'",
                        p.getCurrentToken(), p.getValueAsString(), p.currentValue().toString());
                throw new JsonParseException(p, "Unexpected token: " + p.getCurrentToken());
        }
        return result;
    }

    private Object readByType(TokenBuffer buffer, DeserializationContext ctxt) throws IOException {
        final String type = findType(buffer.asParser(), ctxt);
        if (type == null) {
            return readAsAnyKnownTypeOrMapToList(buffer);
        }

        final var valueType = TOKEN_TO_CLASS.get(type);
        if (valueType != null) {
            return buffer.asParser().readValueAs(valueType);
        }

        log.warn("deserializing as type:{} currently not implemented/supported!", type);
        return ctxt.reportBadDefinition(ctxt.getContextualType(), "Type " + type + " not supported");

    }

    private Object readAsAnyKnownTypeOrMapToList(TokenBuffer buffer) throws IOException {
        var object = buffer.asParser().readValueAsTree();
        if (object instanceof ObjectNode) {
            // best alternative mapping of undefined input
            return buffer.asParser().readValueAs(Map.class);
        }
        return object;
    }

    private String findType(JsonParser p, DeserializationContext ctxt) throws IOException {
        final JsonToken token = p.nextToken();
        if (!token.isStructStart()) {
            ctxt.reportWrongTokenException(this, JsonToken.START_OBJECT, null);
        }
        String result = null;
        String fieldName;
        while ((fieldName = p.nextFieldName()) != null) {
            p.nextToken(); // always go to next token, which is either read or skipped
            if ("@baseType".equals(fieldName) || "valueType".equals(fieldName)) {
                result = p.readValueAs(String.class);
            }
            else if ("@type".equals(fieldName) || "type".equals(fieldName)) {
                result = p.readValueAs(String.class);
                break;
            }
            else {
                p.skipChildren();
            }
        }
        return result;
    }

}
