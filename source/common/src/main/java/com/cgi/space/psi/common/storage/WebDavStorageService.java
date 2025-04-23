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

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/**
 * {@link StorageService} that stores all data in a WebDav folder.
 */
@RequiredArgsConstructor
public class WebDavStorageService implements StorageService {

    private final Sardine sardine;
    private final String baseUrl;

    @Override
    public long store(String name, InputStream inputStream, Optional<String> contentType) throws IOException {
        if (contentType.isPresent()) {
            sardine.put(baseUrl + name, inputStream, contentType.get());
        }
        else {
            sardine.put(baseUrl + name, inputStream);
        }

        final List<DavResource> list = sardine.list(baseUrl + name);
        if (list.isEmpty()) {
            throw new IOException(name + " was not written to " + baseUrl);
        }
        return list.get(0).getContentLength();
    }

    @Override
    public InputStream retrieve(String name) throws IOException {
        return sardine.get(baseUrl + name);
    }

    @Override
    public Optional<URI> getRedirectUri(String name) {
        return Optional.empty();
    }

    @Override
    public void delete(String name) throws IOException {
        sardine.delete(baseUrl + name);
    }

}
