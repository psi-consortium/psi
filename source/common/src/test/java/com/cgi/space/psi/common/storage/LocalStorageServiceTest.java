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

import com.cgi.space.psi.common.config.LocalStorageServiceAutoConfiguration;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LocalStorageServiceTest {

    public static final String TEST_FILENAME = "localStorageTest.bin";

    private final StorageService storageService;

    public LocalStorageServiceTest() throws IOException {
        this.storageService = new LocalStorageServiceAutoConfiguration().localStorageService("build/storage");
    }

    @Test
    public void testStoreRetrieveDelete() throws Exception {
        final byte[] data = "Foobar".getBytes(Charset.defaultCharset());
        long storedBytes = storageService.store(TEST_FILENAME, new ByteArrayInputStream(data), Optional.empty());
        assertThat(storedBytes, is((long) data.length));

        final InputStream retrieve = storageService.retrieve(TEST_FILENAME);
        final byte[] read = retrieve.readAllBytes();
        assertThat(read, is(data));

        storageService.delete(TEST_FILENAME);
    }

    @Test
    public void testGetRedirectUri() {
        final Optional<URI> redirectUri = storageService.getRedirectUri("something");
        assertThat(redirectUri.isEmpty(), is(true));
    }

}
