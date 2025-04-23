/*
 * Copyright 2023 CGI Deutschland B.V. & Co. KG
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
package com.cgi.space.psi.pss.stub.util;

import java.util.List;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Extension of {@link MongoTemplate} to allow easy querying for {@link Page Pages}.
 */
@Component
@SuppressWarnings("deprecation")
public class PageableMongoTemplate {

    @Autowired
    @Delegate
    private MongoTemplate template;

    /**
     * Retrieves the total number of elements matching the given query and the slice specified by the {@link Pageable},
     * and returns the information as a {@link Page}.
     *
     * @param <T> the entity type
     * @param query the query
     * @param pageable the pageable
     * @param entityClass the entity class
     * @see MongoTemplate#find(org.springframework.data.mongodb.core.query.Query, java.lang.Class)
     * @return a page containing the requested slice of entities
     */
    public <T> Page<T> find(Query query, Pageable pageable, Class<T> entityClass) {
        final long total = template.count(query, entityClass);
        final List<T> content = template.find(query.with(pageable), entityClass);
        return new PageImpl<>(content, pageable, total);
    }


    /**
     * Retrieves the total number of elements matching the given query and the slice specified by the {@link Pageable},
     * and returns the information as a {@link Page}.
     *
     * @param <T> the entity type
     * @param query the query
     * @param pageable the pageable
     * @param entityClass the entity class
     * @param collectionName the collection name
     * @see MongoTemplate#find(org.springframework.data.mongodb.core.query.Query, java.lang.Class)
     * @return a page containing the requested slice of entities
     */
    public <T> Page<T> find(Query query, Pageable pageable, Class<T> entityClass, String collectionName) {
        final long total = template.count(query, entityClass);
        final List<T> content = template.find(query.with(pageable), entityClass, collectionName);
        return new PageImpl<>(content, pageable, total);
    }

}
