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
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/**
 * {@link StorageService} that stores all data in a minio bucket.
 */
@RequiredArgsConstructor
public class MinioStorageService implements StorageService {

    private final MinioClient minioClient;
    private final String bucket;

    @Override
    public long store(String name, InputStream inputStream, Optional<String> contentType) throws IOException {
        try {
            final PutObjectArgs.Builder args = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(name)
                    .stream(inputStream, -1, 10485760);
            contentType.ifPresent(args::contentType);
            minioClient.putObject(args.build());

            return minioClient.statObject(StatObjectArgs.builder().bucket(bucket).object(name).build()).size();
        }
        catch (MinioException | GeneralSecurityException ex) {
            throw new IOException("Could not write " + name + " to minio bucket " + bucket, ex);
        }
    }

    @Override
    public InputStream retrieve(String name) throws IOException {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(name)
                    .build()
            );
        }
        catch (MinioException | GeneralSecurityException ex) {
            throw new IOException("Could not read " + name + " from minio bucket " + bucket, ex);
        }
    }

    @Override
    public Optional<URI> getRedirectUri(String name) {
        try {
            final String presignedObjectUrl = minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucket)
                    .object(name)
                    .build()
            );
            return Optional.of(URI.create(presignedObjectUrl));
        }
        catch (MinioException | GeneralSecurityException | IOException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(String name) throws IOException {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(name)
                    .build());
        }
        catch (MinioException | GeneralSecurityException ex) {
            throw new IOException("Could not delete " + name + " to minio bucket " + bucket, ex);
        }
    }

}
