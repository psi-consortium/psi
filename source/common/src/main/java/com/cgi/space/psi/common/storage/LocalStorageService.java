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
package com.cgi.space.psi.common.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/**
 * {@link StorageService} that stores all data locally.
 */
@RequiredArgsConstructor
public class LocalStorageService implements StorageService {

    private final Path basePath;

    @Override
    public long store(String name, InputStream inputStream, Optional<String> contentType) throws IOException {
        try (OutputStream outputStream = Files.newOutputStream(basePath.resolve(name), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            return inputStream.transferTo(outputStream);
        }
    }

    @Override
    public InputStream retrieve(String name) throws IOException {
        return Files.newInputStream(basePath.resolve(name), StandardOpenOption.READ);
    }

    @Override
    public Optional<URI> getRedirectUri(String name) {
        return Optional.empty();
    }

    @Override
    public void delete(String name) throws IOException {
        Files.delete(basePath.resolve(name));
    }

}
