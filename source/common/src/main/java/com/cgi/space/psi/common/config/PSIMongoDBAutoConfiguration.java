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

import com.mongodb.MongoClientSettings;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;
import javax.annotation.Nullable;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import static java.util.Arrays.asList;

/**
 * Automatically customizes the MongoDB configuration, providing support for OffsetDateTime.
 */
@Configuration
public class PSIMongoDBAutoConfiguration {

    private static final String DATE_TIME = "dateTime";
    private static final String OFFSET = "offset";

    /**
     * Registers custom conversions in MongoDB to be able to store {@link OffsetDateTime} properties
     * correctly.
     * These are used by the spring-data layer and must be compatible with the MongoDB codecs.
     *
     * @see #psiMongoClientSettingsBuilderCustomizer()
     * @return the CustomConversions
     */
    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        return new MongoCustomConversions(asList(
                new OffsetDateTimeToDocumentConverter(),
                new DocumentToOffsetDateTimeConverter()));
    }

    /**
     * Registers codecs in MongoDB to be able to store {@link OffsetDateTime} properties correctly.
     * These are used by the MongoDB driver and must be compatible with the spring-data conversions.
     *
     * @see #customConversions()
     * @return the MongoClientSettingsBuilderCustomizer
     */
    @Bean
    public MongoClientSettingsBuilderCustomizer psiMongoClientSettingsBuilderCustomizer() {
        return (builder) -> {
            CodecRegistry customCodecRegistry = CodecRegistries.fromCodecs(
                    new OffsetDateTimeCodec());
            CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                    customCodecRegistry,
                    MongoClientSettings.getDefaultCodecRegistry());
            builder.codecRegistry(codecRegistry);
        };
    }

    /**
     * A codec for MongoDB to store and read {@link OffsetDateTime} as sub-documents.
     */
    public static class OffsetDateTimeCodec implements Codec<OffsetDateTime> {

        @Override
        public void encode(final BsonWriter writer, final OffsetDateTime value, final EncoderContext encoderContext) {
            writer.writeStartDocument();
            writer.writeDateTime(DATE_TIME, value.toInstant().toEpochMilli());
            writer.writeString(OFFSET, value.getOffset().getId());
            writer.writeEndDocument();
        }

        @Override
        public OffsetDateTime decode(final BsonReader reader, final DecoderContext decoderContext) {
            reader.readStartDocument();
            long epochMilli = reader.readDateTime(DATE_TIME);
            String zoneOffset = reader.readString(OFFSET);
            reader.readEndDocument();

            return OffsetDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneOffset.of(zoneOffset));
        }

        @Override
        public Class<OffsetDateTime> getEncoderClass() {
            return OffsetDateTime.class;
        }

    }

    /**
     * A spring-data converter to read {@link OffsetDateTime} as sub-documents in MongoDB.
     */
    @ReadingConverter
    public static class DocumentToOffsetDateTimeConverter implements Converter<Document, OffsetDateTime> {

        @Override
        public OffsetDateTime convert(@Nullable Document document) {
            if (document == null) {
                return null;
            }

            Date dateTime = document.getDate(DATE_TIME);
            String offsetId = document.getString(OFFSET);
            ZoneOffset offset = ZoneOffset.of(offsetId);

            return OffsetDateTime.ofInstant(dateTime.toInstant(), offset);
        }

    }

    /**
     * A spring-data converter to write {@link OffsetDateTime} as sub-documents in MongoDB.
     */
    @WritingConverter
    public static class OffsetDateTimeToDocumentConverter implements Converter<OffsetDateTime, Document> {

        @Override
        public Document convert(@Nullable OffsetDateTime zonedDateTime) {
            if (zonedDateTime == null) {
                return null;
            }

            Document document = new Document();
            document.put(DATE_TIME, Date.from(zonedDateTime.toInstant()));
            document.put(OFFSET, zonedDateTime.getOffset().getId());
            return document;
        }

    }

}
