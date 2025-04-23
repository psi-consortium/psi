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

import com.cgi.space.psi.common.model.CustomerInquiry;
import com.cgi.space.psi.common.model.CustomerInquiryFVO;
import com.cgi.space.psi.common.model.CustomerInquiryMVO;
import com.cgi.space.psi.common.model.InquiredProvider;
import com.cgi.space.psi.common.model.InquiryStateType;
import com.cgi.space.psi.common.model.ResponseTime;
import com.cgi.space.psi.common.request.AitfTestAwareRunnable;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.controller.CustomerInquiryController;
import com.cgi.space.psi.pss.stub.event.PublishedEvent;
import com.cgi.space.psi.pss.stub.event.ReceivedEvent;
import com.cgi.space.psi.pss.stub.mapper.CustomerInquiryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link CustomerInquiry CustomerInquiries}.
 */
@Slf4j
@Service
public class CustomerInquiryService {

    @Autowired
    private CustomerInquiryMapper inquiryMapper;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private Profiles profiles;
    private Duration minResponseTime;
    private Duration avgResponseTime;
    private final Duration maxResponseTime;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final Set<String> inquiriesInProgress = new HashSet<>();

    /**
     * Constructs the service and calculates the response time defaults.
     *
     * @param maxResponseTimeStr the configured maximum response time (ISO-8601)
     */
    public CustomerInquiryService(@Value("${psi.pss.inquiry.maxResponseTime:PT15S}") String maxResponseTimeStr) {
        maxResponseTime = Duration.parse(maxResponseTimeStr).withNanos(0);
        if (maxResponseTime.isNegative()) {
            throw new IllegalArgumentException("Invalid duration: " + maxResponseTimeStr);
        }
        minResponseTime = maxResponseTime.dividedBy(2).withNanos(0);
        avgResponseTime = minResponseTime.plus(maxResponseTime).dividedBy(2);
    }

    /**
     * The method creates a new {@link CustomerInquiry} object.
     *
     * @param inquiryCreate the {@link CustomerInquiryFVO} object based on which
     *            the {@link CustomerInquiry} is created.
     * @return the newly created {@link CustomerInquiry} object.
     */
    public CustomerInquiry createCustomerInquiry(CustomerInquiryFVO inquiryCreate) {
        OffsetDateTime now = OffsetDateTime.now();
        CustomerInquiry inquiry = inquiryMapper.toCustomerInquiry(inquiryCreate);
        inquiry.setId(UUID.randomUUID().toString());
        inquiry.setState(InquiryStateType.PENDING);
        inquiry.setResponseTime(new ResponseTime()
                .minimum(now.plus(minResponseTime))
                .average(now.plus(avgResponseTime))
                .maximum(now.plus(maxResponseTime)));

        Link self = linkTo(methodOn(CustomerInquiryController.class)
                .retrieveCustomerInquiry(inquiry.getId())).withSelfRel();
        inquiry.setHref(self.toUri());

        synchronized (inquiriesInProgress) {
            inquiriesInProgress.add(inquiry.getId());
        }
        template.save(inquiry);
        applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_INQUIRY, PublishedEvent.TYPE_CREATED, inquiry));

        // check after the max response time if all the providers responded to the inquiry
        executor.schedule(new AitfTestAwareRunnable(() -> checkProvidersResponse(inquiry.getId())), maxResponseTime.toNanos(), TimeUnit.NANOSECONDS);
        return inquiry;
    }

    /**
     * The method updates an existing {@link CustomerInquiry CustomerInquiry}.
     * The only possible changes are the list of inquired providers. No other fields can get changed.
     *
     * @param id The Identifier of the {@link CustomerInquiry} to be updated.
     * @param inquiryUpdate The {@link CustomerInquiryMVO} object containing updates of the inquired providers.
     * @return the updated {@link CustomerInquiry CustomerInquiry}
     */
    public CustomerInquiry updateCustomerInquiry(String id, CustomerInquiryMVO inquiryUpdate) {
        CustomerInquiry pssInquiry = template.findById(id, CustomerInquiry.class);
        if (pssInquiry == null) {
            return null;
        }

        if (inquiryUpdate.getInquiredProvider() == null) {
            return pssInquiry;
        }

        List<InquiredProvider> cancelledProviders = determineCancelledProviders(pssInquiry, inquiryUpdate);
        List<InquiredProvider> addedProviders = determineAddedProviders(pssInquiry, inquiryUpdate);

        pssInquiry.setInquiredProvider(inquiryUpdate.getInquiredProvider());
        var result = template.save(pssInquiry);

        if (cancelledProviders.size() > 0) {
            CustomerInquiry inquiryCancellation = inquiryMapper.copy(pssInquiry);
            // updates the individual state of the providers to CANCELLED
            cancelledProviders.forEach(provider -> provider.setState(InquiryStateType.CANCELLED));
            inquiryCancellation.setInquiredProvider(cancelledProviders);
            inquiryCancellation.setState(InquiryStateType.CANCELLED);
            applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_INQUIRY, PublishedEvent.TYPE_UPDATED, inquiryCancellation));
        }

        if (addedProviders.size() > 0) {
            CustomerInquiry inquiryCreation = inquiryMapper.copy(pssInquiry);
            inquiryCreation.setInquiredProvider(addedProviders);
            applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_INQUIRY, PublishedEvent.TYPE_CREATED, inquiryCreation));
        }

        return result;
    }

    private List<InquiredProvider> determineCancelledProviders(CustomerInquiry pssInquiry, CustomerInquiryMVO inquiryUpdate) {
        return minus(pssInquiry.getInquiredProvider().stream(), inquiryUpdate.getInquiredProvider().stream());
    }

    private List<InquiredProvider> determineAddedProviders(CustomerInquiry pssInquiry, CustomerInquiryMVO inquiryUpdate) {
        return minus(inquiryUpdate.getInquiredProvider().stream(), pssInquiry.getInquiredProvider().stream());
    }

    private List<InquiredProvider> minus(Stream<InquiredProvider> a, Stream<InquiredProvider> b) {
        List<URI> hrefs = b.map(InquiredProvider::getHref).collect(Collectors.toList());
        return a.filter(provider -> !hrefs.contains(provider.getHref()))
                .collect(Collectors.toList());
    }

    /**
     * Cancels an existing {@link CustomerInquiry CustomerInquiry}.
     *
     * @param id The Identifier of the {@link CustomerInquiry} to be cancelled.
     * @return <code>true</code> if {@link CustomerInquiry} successfully marked as cancelled, else
     *         <code>false</code>.
     */
    public boolean cancelCustomerInquiry(String id) {
        CustomerInquiry inquiry = template.findById(id, CustomerInquiry.class);
        if (inquiry == null) {
            return false;
        }
        // updates the individual state of the providers to CANCELLED
        inquiry.getInquiredProvider().forEach(provider -> provider.setState(InquiryStateType.CANCELLED));
        // updates the overall state of the inquiry to CANCELLED
        inquiry.setState(InquiryStateType.CANCELLED);
        template.save(inquiry);

        if (inquiry.getInquiredProvider() != null && inquiry.getInquiredProvider().size() > 0) {
            applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_INQUIRY, PublishedEvent.TYPE_UPDATED, inquiry));
        }
        return true;
    }

    /**
     * The method retrieves an existing {@link CustomerInquiry}.
     *
     * @param id The Identifier of the {@link CustomerInquiry} to be retrieved.
     * @return The retrieved {@link CustomerInquiry}.
     */
    public CustomerInquiry getCustomerInquiry(String id) {
        return template.findById(id, CustomerInquiry.class);
    }

    /**
     * Waits for a CustomerInquiry to be processed.
     * If the inquiry is already finished, this method returns immediately.
     *
     * @param inquiry the inquiry to wait for
     * @throws InterruptedException if the thread gets interrupted
     */
    public void waitUntilComplete(CustomerInquiry inquiry) throws InterruptedException {
        synchronized (inquiriesInProgress) {
            while (inquiriesInProgress.contains(inquiry.getId())) {
                inquiriesInProgress.wait();
            }
        }
    }

    /**
     * The method updates the internal state of the inquiry for a provider and its actual response
     * time.
     * The method happens only on the provider side.
     *
     * @param id the Identifier of the {@link CustomerInquiry}.
     * @param newState the new state of the {@link CustomerInquiry} for the current provider.
     */
    private void updateProviderState(String id, InquiryStateType newState) {
        final CustomerInquiry inquiry = template.findById(id, CustomerInquiry.class);
        if (inquiry == null) {
            return;
        }
        if (finished(inquiry)) {
            log.error("PVD - automated {}-state trigger for inquiry {}: already finished with state {}", newState, id, inquiry.getState(), newState);
            return;
        }

        List<InquiredProvider> inquiredProviders = inquiry.getInquiredProvider();
        if (!inquiredProviders.isEmpty()) {
            // assume that the actual provider is the first and only one in the list
            InquiredProvider provider = inquiredProviders.get(0);
            // update the internal inquiry state and actual response time of the provider
            provider.setState(newState);
            if (provider.getActualResponseTime() == null && newState == InquiryStateType.COMPLETED) {
                provider.setActualResponseTime(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
            }
            template.save(inquiry);
            applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_INQUIRY, PublishedEvent.TYPE_UPDATED, inquiry));
            log.info("PVD - automated {}-state trigger for inquiry {}: Setting provider {} state to {}", newState, id, provider.getName(), newState);
        }
    }

    /**
     * Listens for received events concerning inquiries.
     *
     * @param event the received event
     */
    @EventListener
    public void onApplicationEvent(ReceivedEvent event) {
        log.info("Received event: {} {}", event.getTopic(), event.getType());
        if (PublishedEvent.TOPIC_INQUIRY.equals(event.getTopic())) {
            CustomerInquiry inquiry = (CustomerInquiry) event.getValue();
            if (PublishedEvent.TYPE_CREATED.equals(event.getType())) {
                template.save(inquiry);
                if (profiles.isProviderActive()) {
                    executor.schedule(new AitfTestAwareRunnable(() -> {
                        updateProviderState(inquiry.getId(), InquiryStateType.INPROGRESS);
                    }), 1, TimeUnit.SECONDS);

                    executor.schedule(new AitfTestAwareRunnable(() -> {
                        updateProviderState(inquiry.getId(), InquiryStateType.COMPLETED);
                    }), 10, TimeUnit.SECONDS);
                }
            }
            else if (PublishedEvent.TYPE_UPDATED.equals(event.getType())) {
                if (profiles.isPssActive()) {
                    CustomerInquiry pssInquiry = template.findById(inquiry.getId(), CustomerInquiry.class);
                    InquiredProvider respondedProvider = inquiry.getInquiredProvider().get(0);

                    log.info("PSS - update event: Inquiry {}, response from {}, available provider {}",
                            inquiry.getId(), respondedProvider, pssInquiry.getInquiredProvider());

                    InquiredProvider provider = pssInquiry.getInquiredProvider()
                            .stream()
                            .filter(inquiredProvider -> inquiredProvider.getHref().equals(respondedProvider.getHref()))
                            .findFirst()
                            .orElseThrow(IllegalStateException::new);

                    // updates only the individual state and response time of the provider
                    provider.setState(respondedProvider.getState());
                    provider.setActualResponseTime(respondedProvider.getActualResponseTime());

                    updateOverallState(pssInquiry);
                    template.save(pssInquiry);

                    // notify the providers that the PSS has updated the inquiry
                    applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_INQUIRY, PublishedEvent.TYPE_UPDATED, pssInquiry));
                }
                else {
                    template.save(inquiry);
                }
            }
            else if (PublishedEvent.TYPE_DELETED.equals(event.getType())) {
                template.remove(inquiry);
            }
        }
    }

    /**
     * Listens for published events concerning inquiries to notify waiting threads.
     *
     * @param event the published event
     */
    @EventListener
    public void onApplicationEvent(PublishedEvent event) {
        Object eventValue = event.getValue();
        if (eventValue instanceof CustomerInquiry) {
            CustomerInquiry inquiry = (CustomerInquiry) eventValue;
            log.info("Received event for {} with state {}", inquiry.getId(), inquiry.getState());
            if (finished(inquiry)) {
                synchronized (inquiriesInProgress) {
                    inquiriesInProgress.remove(inquiry.getId());
                    inquiriesInProgress.notifyAll();
                }
            }
        }
    }

    /**
     * The method updated the overall state of the {@link CustomerInquiry}.
     * It checks if all the requested providers has responded.
     *
     * @param pssInquiry The {@link CustomerInquiry} for which the overall state will be updated.
     */
    private void updateOverallState(CustomerInquiry pssInquiry) {
        final OffsetDateTime now = OffsetDateTime.now();
        for (InquiredProvider inquiredProvider : pssInquiry.getInquiredProvider()) {
            // the provider didn't respond within the max response time, therefore its state will be CANCELLED.
            if (inquiredProvider.getActualResponseTime() == null &&
                    now.isAfter(pssInquiry.getResponseTime().getMaximum())) {
                inquiredProvider.setState(InquiryStateType.CANCELLED);
            }
            if (!(InquiryStateType.CANCELLED.equals(inquiredProvider.getState()) ||
                    InquiryStateType.COMPLETED.equals(inquiredProvider.getState()))) {

                if (shouldChangeInquiryStateToInProgress(pssInquiry.getState(), inquiredProvider.getState())) {
                    pssInquiry.setState(inquiredProvider.getState());
                }
                return;
            }
        }
        completeCustomerInquiry(pssInquiry);
    }

    /**
     * The method checks of the state of the {@link CustomerInquiry} should be changed to
     * INPROGRESS.
     * <p>
     * This is the case when the current state of the {@link CustomerInquiry} is PENDING,
     * and a provider responded with an internal state INPROGRESS.
     *
     * @param inquiryState The overall state of the {@link CustomerInquiry}.
     * @param providerState The internal inquiry state of the provider.
     * @return <code>true</code> if the state of the inquiry is PENDING and a provider's internal
     *         state is INPROGRESS,
     *         <code>false</code> otherwise.
     */
    private boolean shouldChangeInquiryStateToInProgress(InquiryStateType inquiryState, InquiryStateType providerState) {
        return InquiryStateType.PENDING.equals(inquiryState) && InquiryStateType.INPROGRESS.equals(providerState);
    }

    /**
     * For a {@link CustomerInquiry} which is still in progress, it checks if the inquired providers
     * have responded with an offer.
     * If this is not the case, the inquiry to the provider is CANCELLED.
     * When all providers are checked, the {@link CustomerInquiry} is marked as COMPLETED.
     *
     * @param inquiryId The Identifier of the {@link CustomerInquiry} to be checked.
     */
    private void checkProvidersResponse(String inquiryId) {
        final CustomerInquiry inquiry = template.findById(inquiryId, CustomerInquiry.class);
        if (!finished(inquiry)) {
            inquiry.getInquiredProvider().stream().forEach(inquiredProvider -> {
                if (inquiredProvider.getActualResponseTime() == null ||
                        !InquiryStateType.COMPLETED.equals(inquiredProvider.getState())) {
                    inquiredProvider.setActualResponseTime(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
                    inquiredProvider.setState(InquiryStateType.CANCELLED);
                    log.error("PSS - {} reached max response time for provider {}. Setting provider state to 'cancelled'!",
                            inquiryId, inquiredProvider.getName());
                }
            });
            completeCustomerInquiry(inquiry);
            template.save(inquiry);
            // notify the providers that the CustomerInquiry has been completed
            applicationEventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_INQUIRY, PublishedEvent.TYPE_UPDATED, inquiry));
        }
    }

    /**
     * Completes a {@link CustomerInquiry}.
     * <p>
     * The overall inquiry state is set to COMPLETED and the actual response time is updated.
     *
     * @param inquiry The {@link CustomerInquiry} to be completed.
     */
    private void completeCustomerInquiry(CustomerInquiry inquiry) {
        inquiry.setState(InquiryStateType.COMPLETED);
        inquiry.getResponseTime().setActual(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));
    }

    private boolean finished(CustomerInquiry inquiry) {
        return inquiry.getState().equals(InquiryStateType.CANCELLED)
                || inquiry.getState().equals(InquiryStateType.COMPLETED);
    }
}
