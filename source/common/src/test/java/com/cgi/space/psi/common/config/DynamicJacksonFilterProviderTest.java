package com.cgi.space.psi.common.config;

import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.web.context.request.RequestContextHolder.setRequestAttributes;

/**
 * JUnit test for {@link DynamicJacksonFilterProvider}
 */
@SuppressWarnings("unchecked")

public class DynamicJacksonFilterProviderTest {

    @Test
    public void testFindPropertyFilterWithHttpRequest() {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setParameter("fields", "test");
        setRequestAttributes(new ServletRequestAttributes(httpServletRequest));

        DynamicJacksonFilterProvider providerUnderTest = new DynamicJacksonFilterProvider();
        PropertyFilter result = providerUnderTest.findPropertyFilter(DynamicJacksonFilterProvider.NAME, null);
        assertThat(result, is(notNullValue()));
        assertThat(result, is(instanceOf(SimpleBeanPropertyFilter.FilterExceptFilter.class)));
        assertThat((Collection<String>) ReflectionTestUtils.getField(result, "_propertiesToInclude"), containsInAnyOrder("id", "href", "test"));
    }

    @Test
    public void testFindPropertyFilterWithHttpRequestAndFieldsParamterNone() {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.setParameter("fields", "none");
        setRequestAttributes(new ServletRequestAttributes(httpServletRequest));

        DynamicJacksonFilterProvider providerUnderTest = new DynamicJacksonFilterProvider();
        PropertyFilter result = providerUnderTest.findPropertyFilter(DynamicJacksonFilterProvider.NAME, null);
        assertThat(result, is(notNullValue()));
        assertThat(result, is(instanceOf(SimpleBeanPropertyFilter.FilterExceptFilter.class)));
        assertThat((Collection<String>) ReflectionTestUtils.getField(result, "_propertiesToInclude"), containsInAnyOrder("id", "href"));
    }

    @Test
    public void testFindPropertyFilterWithHttpRequestAndNoFieldsParameter() {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        setRequestAttributes(new ServletRequestAttributes(httpServletRequest));

        DynamicJacksonFilterProvider providerUnderTest = new DynamicJacksonFilterProvider();
        PropertyFilter result = providerUnderTest.findPropertyFilter(DynamicJacksonFilterProvider.NAME, null);
        assertThat(result, is(nullValue()));
    }

    @Test
    public void testFindPropertyFilterWithoutHttpRequest() {
        setRequestAttributes(null);

        DynamicJacksonFilterProvider providerUnderTest = new DynamicJacksonFilterProvider();
        PropertyFilter result = providerUnderTest.findPropertyFilter(DynamicJacksonFilterProvider.NAME, null);
        assertThat(result, is(nullValue()));
    }
}
