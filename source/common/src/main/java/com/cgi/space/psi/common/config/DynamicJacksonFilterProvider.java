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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static java.util.Set.of;

/**
 * Filters returned fields based on the TMF OpenAPI "fields" query-param.
 * See TMF630 REST API Design Guidelines chapter "partial Resource representation or attribute
 * selection":
 *
 * <ul>
 * <li>In order to indicate that no resource properties should be returned the following directive
 * can be used: "fields=none".
 * <li>ID and HREF MUST be returned in the resource body representation.
 * </ul>
 */
public class DynamicJacksonFilterProvider extends FilterProvider {

    public static final String NAME = "DynamicJacksonFilter";

    @Override
    @SuppressWarnings("deprecation")
    public BeanPropertyFilter findFilter(Object filterId) {
        throw new UnsupportedOperationException("Deprecated method called.");
    }

    @Override
    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
            if (Objects.equals(filterId, NAME)) {
                final String fieldsParameter = request.getParameter("fields");
                if ("none".equalsIgnoreCase(fieldsParameter)) {
                    return SimpleBeanPropertyFilter.filterOutAllExcept("id", "href");
                }
                else if (StringUtils.isNoneBlank(fieldsParameter)) {
                    Set<String> fields = new HashSet<>(of(fieldsParameter.split(",")));
                    fields.add("id");
                    fields.add("href");
                    return new DynamicPropertyFilter(fields);
                }
            }
        }
        return null;
    }

    private static final class DynamicPropertyFilter extends SimpleBeanPropertyFilter.FilterExceptFilter {

        private static final long serialVersionUID = 1L;
        private static final ThreadLocal<List<String>> serializationPath = ThreadLocal.withInitial(ArrayList::new);

        public DynamicPropertyFilter(Set<String> propertiesToInclude) {
            super(propertiesToInclude);
        }

        @Override
        public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
            final List<String> currentPath = DynamicPropertyFilter.serializationPath.get();
            try {
                currentPath.add(writer.getName());
                if (currentPath.size() > 1 || include(writer)) {
                    writer.serializeAsField(pojo, jgen, provider);
                }
                else if (!jgen.canOmitFields()) { // since 2.3
                    writer.serializeAsOmittedField(pojo, jgen, provider);
                }
            }
            finally {
                currentPath.remove(currentPath.size() - 1);
            }
        }

    }

}
