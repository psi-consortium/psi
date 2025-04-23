package com.cgi.space.psi.pss.stub.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.cgi.space.psi.common.model.ServiceLevelObjective;
import com.cgi.space.psi.common.model.ServiceLevelObjectiveCreate;
import com.cgi.space.psi.common.model.ServiceLevelObjectiveUpdate;
import com.cgi.space.psi.pss.stub.controller.ServiceLevelObjectiveController;
import com.cgi.space.psi.pss.stub.filter.QueryBuilder;
import com.cgi.space.psi.pss.stub.filter.ServiceLevelObjectiveFilter;
import com.cgi.space.psi.pss.stub.mapper.ServiceLevelObjectiveMapper;
import com.cgi.space.psi.pss.stub.util.PageableMongoTemplate;

/**
 * Service class for managing CRUD operations for {@link ServiceLevelObjective}s.
 */
@Service
public class ServiceLevelObjectiveService {

    @Autowired
    private ServiceLevelObjectiveMapper serviceLevelObjectiveMapper;
    @Autowired
    private PageableMongoTemplate template;

    /**
     * The method creates a new {@link ServiceLevelObjective} object.
     *
     * @param sloCreate the {@link ServiceLevelObjectiveCreate} object based on which
     *            the {@link ServiceLevelObjective} is created.
     * @return the newly created {@link ServiceLevelObjective} object.
     */
    public ServiceLevelObjective createServiceLevelObjective(ServiceLevelObjectiveCreate sloCreate) {
        ServiceLevelObjective slo = serviceLevelObjectiveMapper.toServiceLevelObjective(sloCreate);
        slo.setId(UUID.randomUUID().toString());

        Link self = linkTo(methodOn(ServiceLevelObjectiveController.class)
                .retrieveServiceLevelObjective(slo.getId(), Optional.empty())).withSelfRel();
        slo.setHref(self.toUri());

        return template.save(slo);
    }

    /**
     * The method retrieves an existing {@link ServiceLevelObjective}.
     *
     * @param id The Identifier of the {@link ServiceLevelObjective} to be retrieved.
     * @return The retrieved {@link ServiceLevelObjective}.
     */
    public ServiceLevelObjective getServiceLevelObjective(String id) {
        return template.findById(id, ServiceLevelObjective.class);
    }

    /**
     * The method retrieves all {@link ServiceLevelObjective ServiceLevelObjectives}.
     *
     * @param pageable Collection of pagination parameters
     * @param filter Properties for filtering resources to be returned to the response (optional)
     * @return all {@link ServiceLevelObjective ServiceLevelObjectives}
     */
    public Page<ServiceLevelObjective> getAllServiceLevelObjectives(Pageable pageable, Optional<ServiceLevelObjectiveFilter> filter) {
        return template.find(buildQuery(filter), pageable, ServiceLevelObjective.class);
    }

    private Query buildQuery(Optional<ServiceLevelObjectiveFilter> filter) {
        QueryBuilder builder = new QueryBuilder();
        filter.ifPresent(filterProps -> {
            builder.createEquals("name", filterProps.getName());
            builder.createContains(List.of("name"), filterProps.getNameContains());
        });
        return builder.getQuery();
    }

    /**
     * The method updates an existing {@link ServiceLevelObjective ServiceLevelObjective}.
     *
     * @param id The Identifier of the {@link ServiceLevelObjective} to be updated.
     * @param sloUpdate The {@link ServiceLevelObjectiveUpdate} object containing the updates
     *            to be applied on the {@link ServiceLevelObjective} object.
     * @return the updated {@link ServiceLevelObjective ServiceLevelObjective}
     */
    public ServiceLevelObjective updateServiceLevelObjective(String id, ServiceLevelObjectiveUpdate sloUpdate) {
        ServiceLevelObjective slo = template.findOne(Query.query(Criteria.where("id").is(id)), ServiceLevelObjective.class);
        if (slo != null) {
            serviceLevelObjectiveMapper.updateServiceLevelObjective(sloUpdate, slo);
            return template.save(slo);
        }
        else {
            return null;
        }
    }

    /**
     * The method deletes an existing {@link ServiceLevelObjective ServiceLevelObjective}.
     *
     * @param id The Identifier of the {@link ServiceLevelObjective} to be deleted.
     * @return <code>true</code> if {@link ServiceLevelObjective} successfully deleted,
     *         <code>false</code>
     *         otherwise.
     */
    public boolean deleteServiceLevelObjective(String id) {
        ServiceLevelObjective slo = template.findById(id, ServiceLevelObjective.class);
        if (slo == null) {
            return false;
        }
        return template.remove(slo).getDeletedCount() > 0;
    }
}
