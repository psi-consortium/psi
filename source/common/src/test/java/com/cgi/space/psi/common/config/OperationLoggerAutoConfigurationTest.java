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

import com.cgi.space.psi.common.api.PsiOperation;
import com.cgi.space.psi.common.config.OperationLoggerAutoConfiguration.OperationLogger;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerMapping;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.util.ReflectionUtils.findRequiredField;
import static org.springframework.data.util.ReflectionUtils.setField;
import static org.springframework.util.ReflectionUtils.findMethod;

/**
 * JUnit test for {@link OperationLoggerAutoConfiguration}
 */
public class OperationLoggerAutoConfigurationTest {

    private static final String TMP_PATH = "build/tmp/Operations.csv";

    @Test
    public void testOperationLogger() throws Exception {
        OperationLoggerAutoConfiguration config = new OperationLoggerAutoConfiguration();
        setField(findRequiredField(OperationLoggerAutoConfiguration.class, "logfile"), config, TMP_PATH);

        Path tmpPath = Path.of(TMP_PATH);
        final OperationLogger operationLogger = config.operationLogger();
        assertThat(Files.lines(tmpPath).count(), is(1L));

        JoinPoint joinPoint = mock(JoinPoint.class);
        MethodSignature signature = mock(MethodSignature.class);
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getMethod()).thenReturn(findMethod(getClass(), "testEndpoint"));

        HttpServletRequest request = mock(HttpServletRequest.class);
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        when(request.getMethod()).thenReturn("PATCH");
        when(request.getHeader(OperationLoggerAutoConfiguration.AITF_TEST_HEADER)).thenReturn("PSI-A-Test");
        when(request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)).thenReturn("/foo/bar", "/foo/bar", "/another/endpoint");
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            operationLogger.logOperation(joinPoint);
            assertThat(Files.lines(tmpPath).count(), is(2L));

            // shouldn't log the same entry twice
            operationLogger.logOperation(joinPoint);
            assertThat(Files.lines(tmpPath).count(), is(2L));

            // now the endpoint changes => write
            operationLogger.logOperation(joinPoint);
            assertThat(Files.lines(tmpPath).count(), is(3L));
        }
        finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

    @Test
    public void testAitfTestWebClientCustomizer() throws Exception {
        final WebClientCustomizer webClientCustomizer = new OperationLoggerAutoConfiguration().aitfTestWebClientCustomizer();

        WebClient.Builder builder = WebClient.builder();
        webClientCustomizer.customize(builder);
        WebClient client = builder.build();

        HttpServletRequest request = mock(HttpServletRequest.class);
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        when(request.getHeader(OperationLoggerAutoConfiguration.AITF_TEST_HEADER)).thenReturn("PSI-A-Test");
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            client.get().retrieve();
            verify(request).getHeader(OperationLoggerAutoConfiguration.AITF_TEST_HEADER);
        }
        finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

    @PsiOperation("TOD-Test-Number")
    private void testEndpoint() {

    }

}
