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
package com.cgi.space.psi.common.request;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Junit test for {@link OffsetBasedPageRequest}.
 */
public class OffsetBasedPageRequestTest {
    private OffsetBasedPageRequest offsetBasedPageRequest;

    @Test
    public void whenOffsetLessThanZero_thenExceptionThrown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            offsetBasedPageRequest = new OffsetBasedPageRequest(-5, 3);
        });

        String expectedMessage = "Offset index must not be less than zero";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenLimitLessThanOne_thenExceptionThrown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            offsetBasedPageRequest = new OffsetBasedPageRequest(4, -3);
        });

        String expectedMessage = "Limit must not be less than one";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testOffsetLessThanLimit() {
        long offset = 2;
        int limit = 10;
        offsetBasedPageRequest = new OffsetBasedPageRequest(offset, limit);

        assertFalse(offsetBasedPageRequest.hasPrevious());
        assertTrue(offsetBasedPageRequest.getPageNumber() == 0);
        assertSame(limit, offsetBasedPageRequest.getPageSize());
        assertSame(offset, offsetBasedPageRequest.getOffset());

        // Next page
        assertTrue(offsetBasedPageRequest.next().hasPrevious());
        assertTrue(offsetBasedPageRequest.next().getPageNumber() == 1);
        assertSame(limit, offsetBasedPageRequest.next().getPageSize());
        assertSame(offsetBasedPageRequest.getOffset() + limit, offsetBasedPageRequest.next().getOffset());

        // There is no previous, therefore first page is returned
        assertFalse(offsetBasedPageRequest.previousOrFirst().hasPrevious());
        assertTrue(offsetBasedPageRequest.previousOrFirst().getPageNumber() == 0);
        assertSame(limit, offsetBasedPageRequest.previousOrFirst().getPageSize());
        assertSame(0L, offsetBasedPageRequest.previousOrFirst().getOffset());
    }

    @Test
    public void testOffsetEqualsLimit() {
        long offset = 10;
        int limit = 10;
        offsetBasedPageRequest = new OffsetBasedPageRequest(offset, limit);

        assertFalse(offsetBasedPageRequest.hasPrevious());
        assertTrue(offsetBasedPageRequest.getPageNumber() == 1);
        assertSame(limit, offsetBasedPageRequest.getPageSize());
        assertSame(offset, offsetBasedPageRequest.getOffset());

        // Next page
        assertTrue(offsetBasedPageRequest.next().hasPrevious());
        assertTrue(offsetBasedPageRequest.next().getPageNumber() == 2);
        assertSame(limit, offsetBasedPageRequest.next().getPageSize());
        assertSame(offsetBasedPageRequest.getOffset() + limit, offsetBasedPageRequest.next().getOffset());

        // Previous is the first page
        assertFalse(offsetBasedPageRequest.previousOrFirst().hasPrevious());
        assertTrue(offsetBasedPageRequest.previousOrFirst().getPageNumber() == 0);
        assertSame(limit, offsetBasedPageRequest.previousOrFirst().getPageSize());
        assertSame(offsetBasedPageRequest.getOffset() - limit, offsetBasedPageRequest.previousOrFirst().getOffset());
    }

    @Test
    public void testOffsetGreaterThanLimit() {
        long offset = 20;
        int limit = 10;
        offsetBasedPageRequest = new OffsetBasedPageRequest(offset, limit);

        assertTrue(offsetBasedPageRequest.hasPrevious());
        assertSame(2, offsetBasedPageRequest.getPageNumber());
        assertSame(limit, offsetBasedPageRequest.getPageSize());
        assertSame(offset, offsetBasedPageRequest.getOffset());

        // Next page
        assertTrue(offsetBasedPageRequest.next().hasPrevious());
        assertTrue(offsetBasedPageRequest.next().getPageNumber() == 3);
        assertSame(limit, offsetBasedPageRequest.next().getPageSize());
        assertSame(offsetBasedPageRequest.getOffset() + limit, offsetBasedPageRequest.next().getOffset());

        // Previous page
        assertFalse(offsetBasedPageRequest.previousOrFirst().hasPrevious());
        assertTrue(offsetBasedPageRequest.previousOrFirst().getPageNumber() == 1);
        assertSame(limit, offsetBasedPageRequest.previousOrFirst().getPageSize());
        assertSame(offsetBasedPageRequest.getOffset() - limit, offsetBasedPageRequest.previousOrFirst().getOffset());
    }

    @Test
    public void testOf() {
        final Pageable pageable = OffsetBasedPageRequest.of(Optional.of(1), Optional.of(10));
        assertEquals(1, pageable.getOffset());
        assertEquals(10, pageable.getPageSize());
    }

    @Test
    public void testToResponse() {
        final Pageable pageable = OffsetBasedPageRequest.of(Optional.empty(), Optional.empty());
        final List<String> content = List.of("Test1", "Test2");
        final Page<String> page = new PageImpl<>(content, pageable, 10);
        final ResponseEntity<List<String>> response = OffsetBasedPageRequest.toResponse(page);
        assertEquals(content, response.getBody());
        assertEquals("10", response.getHeaders().getFirst("X-Total-Count"));
    }

}
