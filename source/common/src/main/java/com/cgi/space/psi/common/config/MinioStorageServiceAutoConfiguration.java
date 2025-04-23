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

import com.cgi.space.psi.common.storage.MinioStorageService;
import com.cgi.space.psi.common.storage.StorageService;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures a minio storage backend if the property "psi.storage.type" is "minio".
 */
@Configuration
@ConditionalOnProperty(name = "psi.storage.type", havingValue = "minio")
public class MinioStorageServiceAutoConfiguration {

    /**
     * Creates a {@link MinioStorageService}.
     *
     * @param endpoint  the minio endpoint
     * @param accessKey the minio access key
     * @param secretKey the minio secret key
     * @param bucket    the bucket name (must exist!)
     * @return the configured {@link MinioStorageService}
     * @throws IOException              on any basic communication error or missing bucket
     * @throws MinioException           on misconfigurations of minio
     * @throws GeneralSecurityException when encryption is enabled and misconfigured
     */
    @Bean
    public StorageService minioStorageService(
            @Value("${psi.storage.endpoint}") String endpoint,
            @Value("${psi.storage.accessKey}") String accessKey,
            @Value("${psi.storage.secretKey}") String secretKey,
            @Value("${psi.storage.bucket}") String bucket
    ) throws IOException, MinioException, GeneralSecurityException {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();

        // Make bucket if not exist.
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if (!found) {
            throw new IOException("Bucket " + bucket + " not found at " + endpoint);
        }

        return new MinioStorageService(minioClient, bucket);
    }

}
