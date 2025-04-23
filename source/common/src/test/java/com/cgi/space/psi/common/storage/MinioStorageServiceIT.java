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

import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.Time;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.Optional;
import okhttp3.Headers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MinioStorageServiceIT {

    public static final String TEST_BUCKET = "test-bucket";
    public static final String TEST_FILENAME = "minioStorageTest.bin";

    private final MinioClient minioClient = mock(MinioClient.class);
    private final StorageService storageService;

    public MinioStorageServiceIT() {
        storageService = new MinioStorageService(minioClient, TEST_BUCKET);
    }

    @Test
    public void testStore() throws Exception {
        final byte[] data = "Foobar".getBytes(Charset.defaultCharset());
        when(minioClient.statObject(any(StatObjectArgs.class)))
                .thenReturn(new StatObjectResponse(Headers.of(
                        "Content-Length", String.valueOf(data.length),
                        "Last-Modified", Time.HTTP_HEADER_DATE_FORMAT.format(ZonedDateTime.now())
                ), TEST_BUCKET, null, null));
        long storedBytes = storageService.store(TEST_FILENAME, new ByteArrayInputStream(data), Optional.empty());
        assertThat(storedBytes, is((long) data.length));
    }

    @Test
    public void testRetrieve() throws Exception {
        final byte[] data = "Foobar".getBytes(Charset.defaultCharset());
        when(minioClient.getObject(any(GetObjectArgs.class)))
                .thenReturn(new GetObjectResponse(Headers.of(), TEST_BUCKET, null, null, new ByteArrayInputStream(data)));
        final InputStream retrieve = storageService.retrieve(TEST_FILENAME);
        final byte[] read = retrieve.readAllBytes();
        assertThat(read, is(data));
    }

    @Test
    public void testGetRedirectUri() throws Exception {
        when(minioClient.getPresignedObjectUrl(any(GetPresignedObjectUrlArgs.class)))
                .thenReturn("https://www.google.com/");
        final Optional<URI> redirectUri = storageService.getRedirectUri("something");
        assertThat(redirectUri.isEmpty(), is(false));
    }

    @Test
    public void testGetRedirectUriError() throws Exception {
        when(minioClient.getPresignedObjectUrl(any(GetPresignedObjectUrlArgs.class)))
                .thenThrow(IOException.class);
        final Optional<URI> redirectUri = storageService.getRedirectUri("something");
        assertThat(redirectUri.isEmpty(), is(true));
    }

    @Test
    public void testDelete() throws Exception {
        storageService.delete(TEST_FILENAME);
        verify(minioClient).removeObject(any(RemoveObjectArgs.class));
    }

}
