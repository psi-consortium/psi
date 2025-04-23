package com.cgi.space.psi.pss.stub.service;

import com.cgi.space.psi.common.model.ServiceLevelSpecification;
import com.cgi.space.psi.common.model.ServiceLevelSpecificationCreate;
import com.cgi.space.psi.common.model.ServiceLevelSpecificationUpdate;
import com.cgi.space.psi.pss.stub.controller.ServiceLevelSpecificationController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.ServiceLevelSpecificationFilter;
import com.cgi.space.psi.pss.stub.mapper.ServiceLevelSpecificationMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link ServiceLevelSpecification}s.
 */
@Service
@Slf4j
public class ServiceLevelSpecificationService {

    @Autowired
    private ServiceLevelSpecificationMapper serviceLevelSpecificationMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link ServiceLevelSpecification} object.
     *
     * @param slsCreate the {@link ServiceLevelSpecificationCreate} object based on which
     *            the {@link ServiceLevelSpecification} is created.
     * @return the newly created {@link ServiceLevelSpecification} object.
     */
    public ServiceLevelSpecification createServiceLevelSpecification(ServiceLevelSpecificationCreate slsCreate) {
        ServiceLevelSpecification sls = serviceLevelSpecificationMapper.toServiceLevelSpecification(slsCreate);
        sls.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(ServiceLevelSpecificationController.class)
                .retrieveServiceLevelSpecification(sls.getId(), Optional.empty())).withSelfRel();
        sls.setHref(self.toUri());

        return template.save(sls);
    }

    /**
     * The method retrieves an existing {@link ServiceLevelSpecification}.
     *
     * @param id The Identifier of the {@link ServiceLevelSpecification} to be retrieved.
     * @return The retrieved {@link ServiceLevelSpecification}.
     */
    public ServiceLevelSpecification getServiceLevelSpecification(String id) {
        return template.findById(id, ServiceLevelSpecification.class);
    }

    /**
     * The method retrieves all {@link ServiceLevelSpecification}s.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link ServiceLevelSpecification}s
     */
    public Page<ServiceLevelSpecification> getAllServiceLevelSpecifications(Pageable pageable, Optional<ServiceLevelSpecificationFilter> filter) {
        return template.find(buildQuery(filter), pageable, ServiceLevelSpecification.class);
    }

    private Query buildQuery(Optional<ServiceLevelSpecificationFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
            builder.createContains(List.of("description"), filterProps.getDescriptionContains());
            builder.createContains(List.of("name", "description"), filterProps.getNameOrDescriptionContains());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing {@link ServiceLevelSpecification}.
     *
     * @param id The Identifier of the {@link ServiceLevelSpecification} to be updated.
     * @param slsUpdate The {@link ServiceLevelSpecification} object containing the updates
     *            to be applied on the {@link ServiceLevelSpecification} object.
     * @return the updated {@link ServiceLevelSpecification sls}
     */
    public ServiceLevelSpecification updateServiceLevelSpecification(String id, ServiceLevelSpecificationUpdate slsUpdate) {
        ServiceLevelSpecification sls = template.findOne(Query.query(Criteria.where("id").is(id)), ServiceLevelSpecification.class);
        if (sls != null) {
            serviceLevelSpecificationMapper.updateServiceLevelSpecification(slsUpdate, sls);
            return template.save(sls);
        }
        else {
            return null;
        }
    }

    /**
     * The method deletes an existing {@link ServiceLevelSpecification}.
     *
     * @param id The Identifier of the {@link ServiceLevelSpecification} to be deleted.
     * @return <code>true</code> if {@link ServiceLevelSpecification} successfully deleted,
     *         <code>false</code>
     *         otherwise.
     */
    public boolean deleteServiceLevelSpecification(String id) {
        ServiceLevelSpecification sls = template.findById(id, ServiceLevelSpecification.class);
        if (sls == null) {
            return false;
        }
        return template.remove(sls).getDeletedCount() > 0;
    }
}
