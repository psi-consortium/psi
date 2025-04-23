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
package com.cgi.space.psi.pss.stub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.function.client.ReactorNettyHttpClientMapper;
import org.springframework.context.annotation.Bean;

/**
 * The Spring Boot application class for the PSID-Mockup.
 */
@SpringBootApplication
public class PSIDApplicationMockup {

    /**
     * The main method of the {@link PSIDApplicationMockup}.
     *
     * @param args The program arguments needed for the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(PSIDApplicationMockup.class, args);
    }

    /**
     * Forces remote connection pool to load by forcing a failed first http request to localhost
     *
     * @return the ReactorNettyHttpClientMapper required by spring boot to force the loading of this
     * bean
     */
    @Bean
    public ReactorNettyHttpClientMapper psiReactorNettyHttpClientMapper() {
        return (client) -> {
            client.warmup().block();
            client.get().uri("http://127.0.0.1/").response().subscribe(null, (ignore) -> {});
            return client;
        };
    }
}
