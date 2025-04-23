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
package com.cgi.space.psi.common.config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import com.cgi.space.psi.common.request.TMFObjectDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

/**
 * The class automatically customizes the default Jackson Object Mapper.
 */
@Configuration
public class PSIJacksonAutoConfiguration {

    private final JacksonAnnotationIntrospector ignoreJsonIgnorePropertiesIntrospector = new JacksonAnnotationIntrospector() {

        // to ignore JsonIgnoreProperties in Geometry 
        public com.fasterxml.jackson.annotation.JsonIgnoreProperties.Value findPropertyIgnoralByName(com.fasterxml.jackson.databind.cfg.MapperConfig<?> config,
                Annotated a) {
            return JsonIgnoreProperties.Value.empty();
        };
    };

    /**
     * Customizes the {@code Jackson2ObjectMapperBuilder} to use the
     * {@link DynamicJacksonFilterProvider}.
     *
     * @return the customizer
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonFilterCustomizer() {
        return (builder) -> {
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
            builder.filters(new DynamicJacksonFilterProvider());
            builder.annotationIntrospector(ignoreJsonIgnorePropertiesIntrospector);
            builder.deserializerByType(Resource.class, new ResourceDeserializer());
            builder.deserializerByType(Object.class, new TMFObjectDeserializer());
            builder.featuresToDisable(MapperFeature.REQUIRE_TYPE_ID_FOR_SUBTYPES, SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
            builder.featuresToEnable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
                    DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        };
    }

    private static final class ResourceDeserializer extends StdDeserializer<Resource> {

        private static final long serialVersionUID = 1L;

        ResourceDeserializer() {
            super(Resource.class);
        }

        @Override
        public Resource deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            final String encoded = p.getValueAsString();
            if (encoded == null || encoded.isBlank()) {
                return null;
            }
            final byte[] content = Base64.getDecoder().decode(encoded);
            return new InputStreamResource(new ByteArrayInputStream(content));
        }

    }

}
