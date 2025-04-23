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
import java.net.URI;
import java.util.Optional;
import org.springframework.lang.NonNull;

/**
 * Common interface for all storage services.
 *
 * @see LocalStorageService
 * @see MinioStorageService
 * @see WebDavStorageService
 */
public interface StorageService {

    /**
     * Stores data in the storage.
     *
     * @param name        the object name
     * @param inputStream the input stream
     * @param contentType the mime type (optional, may not be supported by all providers)
     * @return the number of written bytes
     * @throws IOException if an I/O error occurs
     */
    long store(@NonNull String name, @NonNull InputStream inputStream, Optional<String> contentType) throws IOException;

    /**
     * Retrieves data from the storage.
     *
     * @param name the object name
     * @return a stream to read the data
     * @throws IOException if an I/O error occurs
     */
    InputStream retrieve(String name) throws IOException;

    /**
     * Creates a redirect URI where the data can be downloaded.
     * Can be used as an alternative to reduce load on the API server, e.g. when using a cloud storage backend.
     * The link may expire after some time (e.g. minutes or hours), so do not store it.
     * Note that not all implementations support this and therefore return an empty optional (especially {@link LocalStorageService} for obvious reasons).
     *
     * @param name the object name
     * @return the redirect uri (or empty)
     */
    Optional<URI> getRedirectUri(String name);

    /**
     * Deletes data in the storage.
     *
     * @param name the object name
     * @throws IOException if an I/O error occurs
     */
    void delete(String name) throws IOException;

}
