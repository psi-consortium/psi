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

import com.cgi.space.psi.common.storage.LocalStorageService;
import com.cgi.space.psi.common.storage.StorageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures a local storage backend if the property "psi.storage.type" is "local".
 */
@Configuration
@ConditionalOnProperty(name = "psi.storage.type", havingValue = "local")
public class LocalStorageServiceAutoConfiguration {

    /**
     * Creates a {@link LocalStorageService}.
     *
     * @param pathStr the path where files will be stored
     * @return the configured {@link LocalStorageService}
     * @throws IOException when the path can not be created
     */
    @Bean
    public StorageService localStorageService(
            @Value("${psi.storage.path}") String pathStr
    ) throws IOException {
        Path path = Path.of(pathStr);
        Files.createDirectories(path);
        return new LocalStorageService(path.toAbsolutePath());
    }

}
