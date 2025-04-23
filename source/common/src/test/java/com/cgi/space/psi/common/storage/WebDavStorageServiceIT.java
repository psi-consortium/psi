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
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WebDavStorageServiceIT {

    public static final String TEST_BASE_URL = "https://www.google.com/";
    public static final String TEST_FILENAME = "minioStorageTest.bin";

    private final Sardine sardine = mock(Sardine.class);
    private final StorageService storageService;

    public WebDavStorageServiceIT() {
        storageService = new WebDavStorageService(sardine, TEST_BASE_URL);
    }

    @Test
    public void testStore() throws Exception {
        final byte[] data = "Foobar".getBytes(Charset.defaultCharset());
        DavResource davResource = mock(DavResource.class);
        when(davResource.getContentLength()).thenReturn((long) data.length);
        when(sardine.list(any(String.class))).thenReturn(List.of(davResource));
        long storedBytes = storageService.store(TEST_FILENAME, new ByteArrayInputStream(data), Optional.empty());
        assertThat(storedBytes, is((long) data.length));
    }

    @Test
    public void testStoreWithContentType() throws Exception {
        final byte[] data = "Foobar".getBytes(Charset.defaultCharset());
        DavResource davResource = mock(DavResource.class);
        when(davResource.getContentLength()).thenReturn((long) data.length);
        when(sardine.list(any(String.class))).thenReturn(List.of(davResource));
        long storedBytes = storageService.store(TEST_FILENAME, new ByteArrayInputStream(data), Optional.of("text/plain"));
        assertThat(storedBytes, is((long) data.length));
        verify(sardine).put(any(String.class), any(InputStream.class), eq("text/plain"));
    }

    @Test
    public void testRetrieve() throws Exception {
        final byte[] data = "Foobar".getBytes(Charset.defaultCharset());
        when(sardine.get(any(String.class))).thenReturn(new ByteArrayInputStream(data));
        final InputStream retrieve = storageService.retrieve(TEST_FILENAME);
        final byte[] read = retrieve.readAllBytes();
        assertThat(read, is(data));
    }

    @Test
    public void testGetRedirectUri() {
        final Optional<URI> redirectUri = storageService.getRedirectUri("something");
        assertThat(redirectUri.isEmpty(), is(true));
    }

    @Test
    public void testDelete() throws Exception {
        storageService.delete(TEST_FILENAME);
        verify(sardine).delete(any(String.class));
    }

}
