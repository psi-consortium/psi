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

import com.cgi.space.psi.common.storage.StorageService;
import com.cgi.space.psi.common.storage.WebDavStorageService;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.net.URI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures a WebDav storage backend if the property "psi.storage.type" is "webdav".
 */
@Configuration
@ConditionalOnProperty(name = "psi.storage.type", havingValue = "webdav")
public class WebDavStorageServiceAutoConfiguration {

    /**
     * Creates a {@link WebDavStorageService}.
     *
     * @param baseUrl        the base url
     * @param username       the username
     * @param password       the password
     * @param preemptiveAuth boolean to enable preemptive authentication (default: false)
     * @return the configured {@link WebDavStorageService}
     */
    @Bean
    public StorageService webDavStorageService(
            @Value("${psi.storage.baseUrl}") String baseUrl,
            @Value("${psi.storage.username}") String username,
            @Value("${psi.storage.password}") String password,
            @Value("${psi.storage.preemptiveAuth:false}") boolean preemptiveAuth
    ) {
        Sardine sardine = SardineFactory.begin(username, password);
        if (preemptiveAuth) {
            sardine.enablePreemptiveAuthentication(URI.create(baseUrl).getHost());
        }
        return new WebDavStorageService(sardine, StringUtils.appendIfMissing(baseUrl, "/"));
    }

}
