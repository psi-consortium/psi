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

import com.cgi.space.psi.common.config.OperationLoggerAutoConfiguration;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AitfTestAwareRunnableIT {

    @Test
    public void testRun() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        ServletRequestAttributes requestAttributes = new ServletRequestAttributes(request);
        when(request.getHeader(OperationLoggerAutoConfiguration.AITF_TEST_HEADER)).thenReturn("PSI-A-Test");
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            new AitfTestAwareRunnable(() -> {
                assertThat(MDC.get(OperationLoggerAutoConfiguration.AITF_TEST_HEADER), is("PSI-A-Test"));
            }).run();
            assertThat(MDC.get(OperationLoggerAutoConfiguration.AITF_TEST_HEADER), is(nullValue()));
        }
        finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

    @Test
    public void testRunWithoutHeader() {
        try {
            new AitfTestAwareRunnable(() -> {
                assertThat(MDC.get(OperationLoggerAutoConfiguration.AITF_TEST_HEADER), is(nullValue()));
            }).run();
        }
        finally {
        }
    }

}
