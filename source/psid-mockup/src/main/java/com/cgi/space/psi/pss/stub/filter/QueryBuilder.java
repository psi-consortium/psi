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
package com.cgi.space.psi.pss.stub.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Class responsible for building {@link Query} for searching MongoDB documents.
 */
public class QueryBuilder {

    public static final String CASE_INSENSITIVE_OPTION = "i";

    private Query query;

    /**
     * Creates a new {@link QueryBuilder} for the provided {@link Query}.
     *
     * @param query The {@link Query} where the different {@link Criteria} are added.
     */
    public QueryBuilder(Query query) {
        this.query = query;
    }

    /**
     * Creates a new {@link QueryBuilder}.
     */
    public QueryBuilder() {
        this(new Query());
    }

    /**
     * Creates criteria where the value provided equals the value of a given field.
     *
     * @param field The field in the document where the value is looked for
     * @param value The searched value
     * @param <T>   The type of the value
     */
    public <T> void createEquals(String field, T value) {
        if (value != null) {
            query.addCriteria(Criteria.where(field).is(value));
        }
    }

    /**
     * Creates criteria where any of the provided values equals the value of a given field.
     *
     * @param field  The field in the document where the value is looked for
     * @param values The list of searched values
     * @param <T>    The type of the value
     */
    public <T> void createEqualsAny(String field, List<T> values) {
        if (!CollectionUtils.isEmpty(values)) {
            query.addCriteria(Criteria.where(field).in(values));
        }
    }

    /**
     * Creates criteria where any of the provided values equals the value of a given field.
     *
     * @param field The field in the document where the value is looked for
     * @param values The list of searched values
     * @param mapper A mapper to apply to every item before querying
     * @param <T> The type of the value
     */
    public <T> void createEqualsAny(String field, List<T> values, Function<T, ?> mapper) {
        if (!CollectionUtils.isEmpty(values)) {
            query.addCriteria(Criteria.where(field).in(values.stream().map(mapper).collect(toList())));
        }
    }

    /**
     * Creates criteria where the value provided is contained in the value of given field(s).
     *
     * @param fields The fields in the document where the value is looked for
     * @param value  The searched value
     */
    public void createContains(List<String> fields, String value) {
        if (StringUtils.isNotEmpty(value)) {
            List<Criteria> criteriaList = new ArrayList<>();
            for (String field : fields) {
                criteriaList.add(Criteria.where(field).regex(value, CASE_INSENSITIVE_OPTION));
            }
            orOperator(criteriaList);
        }
    }

    /**
     * Creates criteria where the value in the field is less than the given value.
     *
     * @param field The field in the document where the value is looked for
     * @param value The searched value
     * @param <T>   The type of the value
     */
    public <T> void createLessThan(String field, T value) {
        if (value != null) {
            query.addCriteria(Criteria.where(field).lt(value));
        }
    }

    /**
     * Creates criteria where the value in the field is less or equal to the given value.
     *
     * @param field The field in the document where the value is looked for
     * @param value The searched value
     * @param <T>   The type of the value
     */
    public <T> void createLessThanOrEqualTo(String field, T value) {
        if (value != null) {
            query.addCriteria(Criteria.where(field).lte(value));
        }
    }

    /**
     * Creates criteria where the value in the field is greater than the given value.
     *
     * @param field The field in the document where the value is looked for
     * @param value The searched value
     * @param <T>   The type of the value
     */
    public <T> void createGreaterThan(String field, T value) {
        if (value != null) {
            query.addCriteria(Criteria.where(field).gt(value));
        }
    }

    /**
     * Creates criteria where the value in the field is greater than or equal to the given value.
     *
     * @param field The field in the document where the value is looked for
     * @param value The searched value
     * @param <T>   The type of the value
     */
    public <T> void createGreaterThanOrEqualTo(String field, T value) {
        if (value != null) {
            query.addCriteria(Criteria.where(field).gte(value));
        }
    }

    /**
     * Connects multiple {@link Criteria} with OR operator.
     *
     * @param criteria The {@link List} of {@link Criteria} to be combined with OR operator.
     */
    public void orOperator(List<Criteria> criteria) {
        if (!CollectionUtils.isEmpty(criteria)) {
            query.addCriteria(new Criteria().orOperator(criteria));
        }
    }

    /**
     * @return The {@link Query} built with this {@link QueryBuilder}.
     */
    public Query getQuery() {
        return query;
    }
}
