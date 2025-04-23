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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class PSIJacksonAutoConfigurationTest {

    @Test
    public void testResourceDeserializer() throws IOException {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        new PSIJacksonAutoConfiguration().jacksonFilterCustomizer().customize(builder);
        final ObjectMapper objectMapper = builder.build();
        final TestBean bean = objectMapper.readValue("{\"resource\": \"Rm9vYmFy\"}", TestBean.class);
        assertThat(bean, is(notNullValue()));
        assertThat(bean.resource, is(notNullValue()));

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(bean.resource.getInputStream()))) {
            final String content = reader.readLine();
            assertThat(content, is(notNullValue()));
            assertThat(content, is("Foobar"));
        }
    }

    @Test
    public void testResourceDeserializerEmptyString() throws IOException {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        new PSIJacksonAutoConfiguration().jacksonFilterCustomizer().customize(builder);
        final ObjectMapper objectMapper = builder.build();
        final TestBean bean = objectMapper.readValue("{\"resource\": \"\"}", TestBean.class);
        assertThat(bean, is(notNullValue()));
        assertThat(bean.resource, is(nullValue()));
    }

    public static final class TestBean {

        public Resource resource;

    }

}
