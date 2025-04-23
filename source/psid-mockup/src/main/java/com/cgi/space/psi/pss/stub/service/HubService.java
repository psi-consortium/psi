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
package com.cgi.space.psi.pss.stub.service;

import com.cgi.space.psi.common.HubSubscriptionWrapper;
import com.cgi.space.psi.common.model.CustomerInquiry;
import com.cgi.space.psi.common.model.EventCreate;
import com.cgi.space.psi.common.model.HubSubscription;
import com.cgi.space.psi.common.model.HubSubscriptionCreate;
import com.cgi.space.psi.common.model.InquiredProvider;
import com.cgi.space.psi.common.model.Topic;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.event.PublishedEvent;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.CustomerInquiryMapper;
import com.cgi.space.psi.pss.stub.mapper.HubSubscriptionMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service class for managing operations for {@link HubSubscription HubSubscriptions}.
 */
@Slf4j
@Service
public class HubService {

    private static final String FIELD_TOPIC_ID = "topicId";

    @Autowired
    private HubSubscriptionMapper hubSubscriptionMapper;
    @Autowired
    private PageableMongoTemplate template;
    @Autowired
    private Profiles profiles;
    @Autowired
    private CustomerInquiryMapper customerInquiryMapper;
    private final WebClient webClient;

    /**
     * Constructs the HubService using the provided WebClientBuilder
     *
     * @param webClientBuilder the WebClientBuilder
     */
    public HubService(WebClient.Builder webClientBuilder) {
        webClient = webClientBuilder.build();
    }

    /**
     * The method registers a callback listener as a new {@link HubSubscription}.
     *
     * @param topicId               The Identifier of the {@link Topic} to which the callback listener will be subscribed.
     * @param hubSubscriptionCreate The {@link HubSubscriptionCreate} object based on which the {@link HubSubscription} is created.
     * @return The newly created {@link HubSubscription} object.
     */
    public HubSubscription registerSubscription(String topicId, HubSubscriptionCreate hubSubscriptionCreate) {
        QueryBuilder builder = new QueryBuilder();
        builder.createEquals(FIELD_TOPIC_ID, topicId);
        builder.createEquals("hubSubscription.callback", hubSubscriptionCreate.getCallback());
        builder.createEquals("hubSubscription.query", hubSubscriptionCreate.getQuery());
        List<HubSubscriptionWrapper> existingSubscriptions = template.find(builder.getQuery(), HubSubscriptionWrapper.class);
        if (!existingSubscriptions.isEmpty()) {
            return existingSubscriptions.get(0).getHubSubscription();
        }

        HubSubscription subscription = hubSubscriptionMapper.toHubSubscription(hubSubscriptionCreate);
        subscription.setId(UUID.randomUUID().toString());
        HubSubscriptionWrapper wrapper = new HubSubscriptionWrapper(subscription, topicId);
        template.save(wrapper);
        return subscription;
    }

    /**
     * The method retrieves all available {@link Topic Topics}.
     *
     * @param pageable Collection of pagination parameters
     * @return all {@link Topic Topics}
     */
    public Page<Topic> getAllTopics(Pageable pageable) {
        return template.find(new Query(), pageable, Topic.class);
    }

    /**
     * The method retrieves all {@link HubSubscription HubSubscriptions} of a {@link Topic}.
     *
     * @param topicId The Identifier of the {@link Topic} for which the {@link HubSubscription HubSubscriptions} should be retrieved.
     * @param pageable Collection of pagination parameters
     * @return all {@link HubSubscription HubSubscriptions}
     */
    public Page<HubSubscription> getAllTopicSubscriptions(String topicId, Pageable pageable) {
        QueryBuilder builder = new QueryBuilder();
        builder.createEquals(FIELD_TOPIC_ID, topicId);
        final Page<HubSubscriptionWrapper> subscriptionWrappers = template.find(builder.getQuery(), pageable, HubSubscriptionWrapper.class);
        final List<HubSubscription> subscriptions = subscriptionWrappers.stream().map(s -> s.getHubSubscription()).collect(Collectors.toList());
        return new PageImpl<>(subscriptions, pageable, subscriptionWrappers.getTotalElements());
    }

    /**
     * The method unregisters an existing {@link HubSubscription}.
     *
     * @param topicId The Identifier of the {@link Topic} from which the {@link HubSubscription} should be unregistered.
     * @param id      The Identifier of the {@link HubSubscription} to be unregistered.
     * @return <code>true</code> if {@link HubSubscription} successfully unregistered, <code>false</code> otherwise.
     */
    public boolean unregisterSubscription(String topicId, String id) {
        QueryBuilder builder = new QueryBuilder();
        builder.createEquals(FIELD_TOPIC_ID, topicId);
        builder.createEquals("hubSubscription.id", id);
        return template.remove(builder.getQuery(), HubSubscriptionWrapper.class).getDeletedCount() > 0;
    }

    /**
     * Listens for PublishedEvents and sends them to all registered callbacks.
     *
     * @param event the event to be published
     */
    @EventListener
    public void onApplicationEvent(PublishedEvent<?> event) {
        EventCreate eventCreate = new EventCreate();
        eventCreate.setCorrelationId(UUID.randomUUID().toString());
        eventCreate.setDomain(event.getTopic());
        eventCreate.setEventId(UUID.randomUUID().toString());
        eventCreate.setEventTime(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        eventCreate.setEventType(event.getType());
        eventCreate.setTimeOccurred(eventCreate.getEventTime());
        eventCreate.setEvent(event.getValue());

        final Query query = new Query().addCriteria(Criteria.where("name").is(event.getTopic()));
        final Topic eventTopic = template.findOne(query, Topic.class);
        if (eventTopic == null) {
            throw new IllegalArgumentException("No topic '" + event.getTopic() + "' found!");
        }

        QueryBuilder builder = new QueryBuilder();
        builder.createEquals(FIELD_TOPIC_ID, eventTopic.getId());
        List<HubSubscriptionWrapper> subscriptions = template.find(builder.getQuery(), HubSubscriptionWrapper.class);
        // The real implementation should ensure that the events are sent only to the relevant party (PSS/Provider)
        for (HubSubscriptionWrapper subscription : subscriptions) {
            if (profiles.isPssActive() && PublishedEvent.TOPIC_INQUIRY.equals(event.getTopic())) {
                // When PSS, filter the list of inquiredProviders in the CustomerInquiry object that is sent to the provider
                CustomerInquiry inquiry = customerInquiryMapper.copy((CustomerInquiry) event.getValue());
                List<InquiredProvider> filteredProviders = inquiry.getInquiredProvider()
                        .stream()
                        .filter(provider -> subscription.getHubSubscription().getCallback().getHost().equals(provider.getHref().getHost()))
                        .collect(Collectors.toList());
                if (!filteredProviders.isEmpty()) {
                    inquiry.setInquiredProvider(filteredProviders);
                    eventCreate.setEvent(inquiry);
                    postEvent(subscription.getHubSubscription(), eventCreate);
                }
            }
            else {
                postEvent(subscription.getHubSubscription(), eventCreate);
            }
        }
    }

    private void postEvent(HubSubscription subscription, EventCreate eventCreate) {
        // real implementations should rather do this asynchronously, e.g. using
        // .toBodilessEntity().subscribeOn(Schedulers.parallel()).toFuture()
        if (matchesQuery(eventCreate, subscription.getQuery())) {
            try {
                webClient.post()
                        .uri(subscription.getCallback())
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(eventCreate)
                        .retrieve()
                        .toBodilessEntity()
                        .block(Duration.ofSeconds(5));
            }
            catch (WebClientException ex) {
                log.error("Failed to call listener " + subscription.getCallback(), ex);
            }
        }
    }

    private boolean matchesQuery(EventCreate event, String query) {
        if (StringUtils.isNotBlank(query)) {
            MultiValueMap<String, String> parameters = UriComponentsBuilder.fromUriString("?" + query).build().getQueryParams();
            for (Entry<String, List<String>> entry : parameters.entrySet()) {
                for (String queryValue : entry.getValue()) {
                    if (!matchesQueryParam(event, entry.getKey().split("\\."), 0, queryValue)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean matchesQueryParam(Object obj, String[] path, int idx, String queryValue) {
        if (idx >= path.length) {
            return Objects.toString(obj).equals(queryValue);
        }
        else if (obj == null) {
            return false;
        }

        Object value;
        final Field field = ReflectionUtils.findField(obj.getClass(), path[idx]);
        if (field == null) {
            value = null;
        }
        else {
            ReflectionUtils.makeAccessible(field);
            try {
                value = field.get(obj);
            }
            catch (IllegalAccessException ex) {
                value = false;
            }
        }

        if (value instanceof Collection) {
            return ((Collection) value).stream().anyMatch((subEntity) -> matchesQueryParam(subEntity, path, idx + 1, queryValue));
        }
        else {
            return matchesQueryParam(value, path, idx + 1, queryValue);
        }
    }

}
