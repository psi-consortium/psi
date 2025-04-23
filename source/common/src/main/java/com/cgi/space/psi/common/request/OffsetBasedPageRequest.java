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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The class is responsible for selecting the right page to be provided in the response based on offset and limit.
 */
public class OffsetBasedPageRequest implements Pageable {

    private final long offset;
    private final int limit;
    private final Sort sort;

    
    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters.
     *
     * @param offset Requested index for start of entries to be provided in response
     * @param limit  Requested number of entries to be provided in response
     * @param sort   Requested sort direction of entries to be provided in response
     */
    public OffsetBasedPageRequest(long offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero");
        }
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one");
        }
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    /**
     * Creates a new {@link OffsetBasedPageRequest}, unsorted.
     *
     * @param offset Requested index for start of entries to be provided in response
     * @param limit Requested number of entries to be provided in response
     */
    public OffsetBasedPageRequest(long offset, int limit) {
        this(offset, limit, Sort.unsorted());
    }
    
    /**
     * Constructs a pageable from optional request parameters.
     * If limit is not set, the result will be unpaged.
     *
     * @param offset the offset (optional)
     * @param limit the limit (optional)
     * @return a pageable representing the given parameters
     */
    public static Pageable of(Optional<Integer> offset, Optional<Integer> limit) {
        return limit.map(size -> (Pageable) new OffsetBasedPageRequest(offset.orElse(0), size)).orElse(Pageable.unpaged());
    }

    /**
     * Transforms the {@link Page} into a TMF conformant ResponseEntity.
     *
     * @param <T> the content type
     * @param page the page
     * @return the response with correct header and content
     */
    public static <T> ResponseEntity<List<T>> toResponse(Page<T> page) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @Override
    public int getPageNumber() {
        return (int) offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(getOffset() + getPageSize(), getPageSize(), getSort());
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    private Pageable previous() {
        return hasPrevious() ? new OffsetBasedPageRequest(getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0, getPageSize(), getSort());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new OffsetBasedPageRequest(pageNumber * getPageSize(), getPageSize(), getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}
