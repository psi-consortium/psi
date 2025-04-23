package com.cgi.space.psi.pss.stub.service;

import com.cgi.space.psi.common.model.CustomerBill;
import com.cgi.space.psi.common.model.CustomerBillFVO;
import com.cgi.space.psi.common.model.CustomerBillStateType;
import com.cgi.space.psi.common.model.CustomerBillMVO;
import com.cgi.space.psi.pss.stub.config.Profiles;
import com.cgi.space.psi.pss.stub.controller.CustomerBillController;
import com.cgi.space.psi.pss.stub.event.PublishedEvent;
import com.cgi.space.psi.pss.stub.event.ReceivedEvent;
import com.cgi.space.psi.pss.stub.filter.CustomerBillFilter;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.mapper.CustomerBillMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link CustomerBill CustomerBills}.
 */
@Slf4j
@Service
public class CustomerBillService {

    @Autowired
    private CustomerBillMapper customerBillMapper;
    @Autowired
    private PageableMongoTemplate template;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private Profiles profiles;

    /**
     * The method creates a new {@link CustomerBill}.
     *
     * @param customerBillCreate The {@link CustomerBillFVO} object based on which
     *                           the {@link CustomerBill} is created.
     * @return The newly created {@link CustomerBill}.
     */
    public CustomerBill createCustomerBill(CustomerBillFVO customerBillCreate) {
        CustomerBill customerBill = customerBillMapper.toCustomerBill(customerBillCreate);
        customerBill.setId(UUID.randomUUID().toString());
        if (customerBill.getState() == null) {
            customerBill.setState(CustomerBillStateType.NEW);
        }

        Link self = linkTo(methodOn(CustomerBillController.class)
                .retrieveCustomerBill(customerBill.getId(), Optional.empty())).withSelfRel();
        customerBill.setHref(self.toUri());

        var result = template.save(customerBill);
        if (isPublishedBillState(result.getState())) {
            publishEvent(PublishedEvent.TYPE_CREATED, result);
        }
        return result;
    }

    /**
     * The method retrieves an existing {@link CustomerBill}.
     *
     * @param id The Identifier of the {@link CustomerBill} to be retrieved.
     * @return The retrieved {@link CustomerBill}.
     */
    public CustomerBill getCustomerBill(String id) {
        return template.findById(id, CustomerBill.class);
    }

    /**
     * The method retrieves all {@link CustomerBill CustomerBills}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link CustomerBill CustomerBills}
     */
    public Page<CustomerBill> getAllCustomerBills(Pageable pageable, Optional<CustomerBillFilter> filter) {
        return template.find(buildQuery(filter), pageable, CustomerBill.class);
    }

    private Query buildQuery(Optional<CustomerBillFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("billNo", filterProps.getBillNo());
            builder.createContains(List.of("billNo"), filterProps.getBillNoContains());
            builder.createEquals("state", StringUtils.upperCase(filterProps.getState()));
        });
        return builder.getQuery();
    }

    /**
     * The method updates the state only of an existing {@link CustomerBill CustomerBill}.
     *
     * @param id                 The Identifier of the {@link CustomerBill} to be updated.
     * @param customerBillUpdate The {@link CustomerBillMVO} object containing the state update to be applied on the {@link CustomerBill} object.
     * @return the updated {@link CustomerBill CustomerBill}.
     */
    public CustomerBill updateCustomerBill(String id, CustomerBillMVO customerBillUpdate) {
        CustomerBill customerBill = getCustomerBill(id);
        if (customerBill == null) {
            return null;
        }

        // Update of a CustomerBill only allows the state to be updated
        if (customerBill.getState() == customerBillUpdate.getState()) {
            return customerBill;
        }

        var previousState = customerBill.getState();
        customerBill.setState(customerBillUpdate.getState());
        var result = template.save(customerBill);

        if (isPublishedBillState(previousState)) {
            if (CustomerBillStateType.WITHDRAWN == result.getState()) {
                publishEvent(PublishedEvent.TYPE_DELETED, result);
            }
            else {
                publishEvent(PublishedEvent.TYPE_UPDATED, result);
            }
        }
        else if (isPublishedBillState(result.getState())) {
            publishEvent(PublishedEvent.TYPE_CREATED, result);
        }
        return result;
    }

    /**
     * Cancels an existing {@link CustomerBill CustomerBill}.
     *
     * @param id The Identifier of the {@link CustomerBill} to be cancelled.
     * @return <code>true</code> if {@link CustomerBill} successfully marked as cancelled, else
     *         <code>false</code>.
     */
    public boolean cancelCustomerBill(String id) {
        CustomerBill bill = getCustomerBill(id);
        if (bill == null) {
            return false;
        }
        var previousState = bill.getState();
        bill.setState(CustomerBillStateType.WITHDRAWN);
        template.save(bill);

        if (isPublishedBillState(previousState)) {
            publishEvent(PublishedEvent.TYPE_DELETED, bill);
        }
        return true;
    }

    // TODO: How can it be determined whether a bill has already been sent to the customer?
    private boolean isPublishedBillState(CustomerBillStateType state) {
        return List.of(CustomerBillStateType.SENT, CustomerBillStateType.SETTLED, CustomerBillStateType.PARTIALLYPAID).contains(state);
    }

    private void publishEvent(String eventType, CustomerBill customerBill) {
        eventPublisher.publishEvent(new PublishedEvent<>(PublishedEvent.TOPIC_BILL, eventType, customerBill));
    }

    /**
     * Listens for received events concerning customer bills.
     *
     * @param event the received event
     */
    @EventListener
    public void onApplicationEvent(ReceivedEvent event) {
        log.info("Received event: {} {}", event.getTopic(), event.getType());
        if (PublishedEvent.TOPIC_BILL.equals(event.getTopic())) {
            if (PublishedEvent.TYPE_CREATED.equals(event.getType())) {
                CustomerBill bill = (CustomerBill) event.getValue();
                template.save(bill);
            }
            else if (PublishedEvent.TYPE_UPDATED.equals(event.getType())) {
                template.save(event.getValue());
            }
            else if (PublishedEvent.TYPE_DELETED.equals(event.getType())) {
                CustomerBill bill = (CustomerBill) event.getValue();
                bill.setState(CustomerBillStateType.WITHDRAWN);
                template.save(bill);
            }
        }
    }

}
