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
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.servlet.HandlerMapping;

/**
 * Configures an OperationLogger if the property "psi.operation.logfile" is set.
 */
@Configuration
@ConditionalOnProperty("psi.operation.logfile")
public class OperationLoggerAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(OperationLoggerAutoConfiguration.class);
    private static final String UNKNOWN = "UNKNOWN";
    public static final String AITF_TEST_HEADER = "X-AITF-TEST";
    public static final String CSV_DELIMITER = ";";

    @Value("${psi.operation.logfile}")
    private String logfile;

    @Bean
    OperationLogger operationLogger() throws IOException {
        return new OperationLogger(Path.of(logfile));
    }

    @Bean
    WebClientCustomizer aitfTestWebClientCustomizer() {
        return (webClientBuilder) -> {
            webClientBuilder.defaultRequest(new RequestAitfTestInjector());
        };
    }

    @Aspect
    static class OperationLogger {

        private final Path logPath;
        private final Set<String> writtenLines = new HashSet<>();

        OperationLogger(Path logPath) throws IOException {
            this.logPath = logPath.toAbsolutePath();
            try (final BufferedWriter out = Files.newBufferedWriter(logPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                out.write("Test;Method;Path;Operation");
                out.write("\n");
            }
            log.info("Logging operations to {}", logPath.toRealPath());
        }

        @Before("@annotation(com.cgi.space.psi.common.api.PsiOperation)")
        void logOperation(JoinPoint joinPoint) throws IOException {
            final Signature signature = joinPoint.getSignature();
            if (signature instanceof MethodSignature) {
                final Method method = ((MethodSignature) signature).getMethod();
                try (final BufferedWriter writer = Files.newBufferedWriter(logPath, StandardOpenOption.APPEND)) {
                    writeOperations(method, writer);
                }
            }
        }

        private void writeOperations(final Method method, final BufferedWriter writer) throws IOException {
            String aitfTest = UNKNOWN;
            String httpMethod = UNKNOWN;
            String httpPath = UNKNOWN;
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                aitfTest = request.getHeader(AITF_TEST_HEADER);
                httpMethod = request.getMethod();
                httpPath = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
            }

            if (aitfTest == null) {
                return;
            }

            PsiOperation operations = method.getAnnotation(PsiOperation.class);
            for (String operation : operations.value()) {
                StringBuilder sb = new StringBuilder();
                sb.append(aitfTest).append(CSV_DELIMITER);
                sb.append(httpMethod).append(CSV_DELIMITER);
                sb.append(httpPath).append(CSV_DELIMITER);
                sb.append(operation).append("\n");

                String line = sb.toString();
                if (!writtenLines.contains(line)) {
                    writer.write(line);
                    writtenLines.add(line);
                }
            }
        }

    }

    /**
     * Injects the correlationId as a header into outgoing requests.
     */
    private static final class RequestAitfTestInjector implements Consumer<RequestHeadersSpec<?>> {

        @Override
        public void accept(RequestHeadersSpec<?> outgoingRequest) {
            String aitfTest = MDC.get(AITF_TEST_HEADER);
            if (aitfTest == null) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest incomingRequest = attributes.getRequest();
                    aitfTest = incomingRequest.getHeader(AITF_TEST_HEADER);
                }
            }
            outgoingRequest.header(AITF_TEST_HEADER, aitfTest);
        }

    }

}
