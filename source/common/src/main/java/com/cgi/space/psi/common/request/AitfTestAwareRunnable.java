/*
 * Copyright 2022 CGI Deutschland B.V. & Co. KG
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

import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cgi.space.psi.common.config.OperationLoggerAutoConfiguration;

/**
 * Wraps another runnable, storing the X-AITF-TEST header in the logging context to retreive it
 * from there in the OperationLogger.
 */
public class AitfTestAwareRunnable implements Runnable {

    private final Runnable delegate;
    private final String aitfTest;

    /**
     * Constructs the runnable with a delegate.
     * 
     * @param delegate the delegated runnable
     */
    public AitfTestAwareRunnable(Runnable delegate) {
        this.delegate = delegate;
        var attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            var request = ((ServletRequestAttributes) attributes).getRequest();
            this.aitfTest = request.getHeader(OperationLoggerAutoConfiguration.AITF_TEST_HEADER);
        }
        else {
            this.aitfTest = null;
        }
    }

    @Override
    public void run() {
        try {
            MDC.put(OperationLoggerAutoConfiguration.AITF_TEST_HEADER, aitfTest);
            delegate.run();
        }
        finally {
            MDC.remove(OperationLoggerAutoConfiguration.AITF_TEST_HEADER);
        }
    }

}
