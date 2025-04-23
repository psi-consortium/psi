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
package com.cgi.space.psi.pss.stub.demodata;

import com.cgi.space.psi.common.model.BundledProductOfferingFVO;
import com.cgi.space.psi.common.model.BundledProductSpecificationFVO;
import com.cgi.space.psi.common.model.EntityRelationshipFVO;
import com.cgi.space.psi.common.model.Individual;
import com.cgi.space.psi.common.model.IndividualFVO;
import com.cgi.space.psi.common.model.Organization;
import com.cgi.space.psi.common.model.OrganizationFVO;
import com.cgi.space.psi.common.model.PartyRefFVO;
import com.cgi.space.psi.common.model.ProductOffering;
import com.cgi.space.psi.common.model.ProductOfferingFVO;
import com.cgi.space.psi.common.model.ProductOfferingRelationshipFVO;
import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.ProductSpecificationFVO;
import com.cgi.space.psi.common.model.ProductSpecificationRefFVO;
import com.cgi.space.psi.common.model.ProductSpecificationRelationshipFVO;
import com.cgi.space.psi.common.model.RelatedPartyOrPartyRoleFVO;
import com.cgi.space.psi.common.model.RelatedPartyRefOrPartyRoleRefFVO;
import com.cgi.space.psi.common.model.ResourceSpecification;
import com.cgi.space.psi.common.model.ResourceSpecificationFVO;
import com.cgi.space.psi.common.model.ResourceSpecificationRefFVO;
import com.cgi.space.psi.common.model.ResourceSpecificationRelationshipFVO;
import com.cgi.space.psi.common.model.ServiceSpecification;
import com.cgi.space.psi.common.model.ServiceSpecificationFVO;
import com.cgi.space.psi.common.model.ServiceSpecificationRefFVO;
import com.cgi.space.psi.pss.stub.service.IndividualService;
import com.cgi.space.psi.pss.stub.service.OrganizationService;
import com.cgi.space.psi.pss.stub.service.ProductOfferingService;
import com.cgi.space.psi.pss.stub.service.ProductSpecificationService;
import com.cgi.space.psi.pss.stub.service.ResourceSpecificationService;
import com.cgi.space.psi.pss.stub.service.ServiceSpecificationService;
import com.cgi.space.psi.pss.stub.util.StreamUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationStartupAware;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Loads demo data from the path defined in property "psi.demo-data.path".
 */
@Slf4j
@Component
@ExtensionMethod({ StreamUtils.class })
public class DemoDataLoader implements ApplicationStartupAware {

    @Value("${psi.demo-data.path:}")
    private String demoDataPathStr;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MongoTemplate template;
    @Autowired
    private IndividualService individualService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private ResourceSpecificationService resourceSpecificationService;
    @Autowired
    private ServiceSpecificationService serviceSpecificationService;
    @Autowired
    private ProductSpecificationService productSpecificationService;
    @Autowired
    private ProductOfferingService productOfferingService;

    @Override
    public void setApplicationStartup(ApplicationStartup applicationStartup) {
        if (!StringUtils.isEmpty(demoDataPathStr) && !template.exists(new Query(), Individual.class)) {
            try {
                Path demoDataPath = Path.of(demoDataPathStr);
                log.info("Loading test data from {}", demoDataPath.toRealPath());
                loadIndividuals(demoDataPath.resolve("partyIndividuals"));
                loadOrganizations(demoDataPath.resolve("partyOrganizations"));
                loadResourceSpecs(demoDataPath.resolve("resourceSpecifications"));
                loadServiceSpecs(demoDataPath.resolve("serviceSpecifications"));
                loadProductSpecs(demoDataPath.resolve("productSpecifications"));
                loadProductOfferings(demoDataPath.resolve("productOfferings"));
            }
            catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            log.info("Demo data skipped");
        }
    }

    private void loadIndividuals(Path path) throws IOException {
        Files.list(path).flatMap((p) -> {
            try {
                return objectMapper.readValue(Files.newBufferedReader(p), new TypeReference<List<IndividualFVO>>() {
                }).stream();
            }
            catch (IOException ex) {
                throw new RuntimeException("Failure on loading individuals from file:" + p.toString(), ex);
            }
        }).forEach(individualService::createIndividual);
    }

    private void loadOrganizations(Path path) throws IOException {
        Files.list(path).flatMap((p) -> {
            try {
                return objectMapper.readValue(Files.newBufferedReader(p), new TypeReference<List<OrganizationFVO>>() {
                }).stream();
            }
            catch (IOException ex) {
                throw new RuntimeException("Failure on loading organizations from file:" + p.toString(), ex);
            }
        }).forEach(organization -> {
            if (organization.getRelatedParty() != null) {
                fillRelatedPartyIds(organization.getRelatedParty());
            }
            organizationService.createOrganization(organization);
        });
    }

    private void fillRelatedPartyIds(List<RelatedPartyOrPartyRoleFVO> relatedParties) {
        relatedParties.stream().map(RelatedPartyOrPartyRoleFVO::getPartyOrPartyRole).filter(PartyRefFVO.class).forEach(this::fillRelatedPartyId);
    }

    private void fillRelatedPartyId(PartyRefFVO relatedParty) {
        final Individual relIndividual = template.findOne(new Query().addCriteria(new Criteria("fullName").is(relatedParty.getName())), Individual.class);
        if (relIndividual != null) {
            relatedParty.setId(relIndividual.getId());
            relatedParty.setHref(relIndividual.getHref());
        }
        final Organization relOrganization
                = template.findOne(new Query().addCriteria(new Criteria("tradingName").is(relatedParty.getName())), Organization.class);
        if (relOrganization != null) {
            relatedParty.setId(relOrganization.getId());
            relatedParty.setHref(relOrganization.getHref());
        }
    }

    private void loadResourceSpecs(Path path) throws IOException {
        Files.list(path).flatMap((p) -> {
            try {
                return objectMapper.readValue(Files.newBufferedReader(p), new TypeReference<List<ResourceSpecificationFVO>>() {
                }).stream();
            }
            catch (IOException ex) {
                throw new RuntimeException("Failure on loading resourceSpecs from file:" + p.toString(), ex);
            }
        }).forEach(resourceSpecification -> {
            if (resourceSpecification.getResourceSpecRelationship() != null) {
                fillResourceSpecRelationshipIds(resourceSpecification.getResourceSpecRelationship());
            }
            resourceSpecificationService.createResourceSpecification(resourceSpecification);
        });
    }

    private void fillResourceSpecRelationshipIds(List<ResourceSpecificationRelationshipFVO> resourceSpecRelationships) {
        for (ResourceSpecificationRelationshipFVO resourceSpecRelationship : resourceSpecRelationships) {
            Query q = new Query().addCriteria(new Criteria("name").is(resourceSpecRelationship.getName()));
            final ResourceSpecification relResourceSpec = template.findOne(q, ResourceSpecification.class);
            if (relResourceSpec != null) {
                resourceSpecRelationship.setId(relResourceSpec.getId());
                resourceSpecRelationship.setHref(relResourceSpec.getHref());
            }
        }
    }

    private void loadServiceSpecs(Path path) throws IOException {
        Files.list(path).flatMap((p) -> {
            try {
                return objectMapper.readValue(Files.newBufferedReader(p), new TypeReference<List<ServiceSpecificationFVO>>() {
                }).stream();
            }
            catch (IOException ex) {
                throw new RuntimeException("Failure on loading serviceSpecs from file:" + p.toString(), ex);
            }
        }).forEach(serviceSpecification -> {
            if (serviceSpecification.getResourceSpecification() != null) {
                fillResourceSpecificationIds(serviceSpecification.getResourceSpecification());
            }
            if (serviceSpecification.getServiceSpecRelationship() != null) {
                fillServiceSpecRelationshipIds(serviceSpecification.getServiceSpecRelationship());
            }
            serviceSpecificationService.createServiceSpecification(serviceSpecification);
        });
    }

    private void fillResourceSpecificationIds(List<ResourceSpecificationRefFVO> resourceSpecificationRefs) {
        for (ResourceSpecificationRefFVO resourceSpecificationRef : resourceSpecificationRefs) {
            Query q = new Query().addCriteria(new Criteria("name").is(resourceSpecificationRef.getName()));
            final ResourceSpecification relResourceSpec = template.findOne(q, ResourceSpecification.class);
            if (relResourceSpec != null) {
                resourceSpecificationRef.setId(relResourceSpec.getId());
                resourceSpecificationRef.setHref(relResourceSpec.getHref());
            }
        }
    }

    private void fillServiceSpecRelationshipIds(List<EntityRelationshipFVO> serviceSpecRelationships) {
        for (EntityRelationshipFVO serviceSpecRelationship : serviceSpecRelationships) {
            Query q = new Query().addCriteria(new Criteria("name").is(serviceSpecRelationship.getName()));
            final ServiceSpecification relServiceSpec = template.findOne(q, ServiceSpecification.class);
            if (relServiceSpec != null) {
                serviceSpecRelationship.setId(relServiceSpec.getId());
                serviceSpecRelationship.setHref(relServiceSpec.getHref());
            }
        }
    }

    private void loadProductSpecs(Path path) throws IOException {
        Files.list(path).flatMap((p) -> {
            try {
                return objectMapper.readValue(Files.newBufferedReader(p), new TypeReference<List<ProductSpecificationFVO>>() {
                }).stream();
            }
            catch (IOException ex) {
                throw new RuntimeException("Failure on loading productSpecs from file:" + p.toString(), ex);
            }
        }).forEach(productSpecification -> {
            if (productSpecification.getResourceSpecification() != null) {
                fillResourceSpecificationIds(productSpecification.getResourceSpecification());
            }
            if (productSpecification.getServiceSpecification() != null) {
                fillServiceSpecificationRefIds(productSpecification.getServiceSpecification());
            }
            if (productSpecification.getBundledProductSpecification() != null) {
                fillBundledProductSpecificationIds(productSpecification.getBundledProductSpecification());
            }
            if (productSpecification.getProductSpecificationRelationship() != null) {
                fillProductSpecificationRelationshipIds(productSpecification.getProductSpecificationRelationship());
            }
            if (productSpecification.getRelatedParty() != null) {
                fillRelatedPartyRefIds(productSpecification.getRelatedParty());
            }
            productSpecificationService.createProductSpecification(productSpecification);
        });
    }

    private void fillRelatedPartyRefIds(List<RelatedPartyRefOrPartyRoleRefFVO> relatedParties) {
        relatedParties.stream().map(RelatedPartyRefOrPartyRoleRefFVO::getPartyOrPartyRole).filter(PartyRefFVO.class).forEach(this::fillRelatedPartyId);
    }

    private void fillServiceSpecificationRefIds(List<ServiceSpecificationRefFVO> serviceSpecificationRefs) {
        for (ServiceSpecificationRefFVO serviceSpecificationRef : serviceSpecificationRefs) {
            Query q = new Query().addCriteria(new Criteria("name").is(serviceSpecificationRef.getName()));
            final ServiceSpecification relServiceSpec = template.findOne(q, ServiceSpecification.class);
            if (relServiceSpec != null) {
                serviceSpecificationRef.setId(relServiceSpec.getId());
                serviceSpecificationRef.setHref(relServiceSpec.getHref());
            }
        }
    }

    private void fillBundledProductSpecificationIds(List<BundledProductSpecificationFVO> bundledProductSpecifications) {
        for (BundledProductSpecificationFVO bundledProductSpecification : bundledProductSpecifications) {
            Query q = new Query().addCriteria(new Criteria("name").is(bundledProductSpecification.getName()));
            final ProductSpecification bundledProductSpec = template.findOne(q, ProductSpecification.class);
            if (bundledProductSpec != null) {
                bundledProductSpecification.setId(bundledProductSpec.getId());
                bundledProductSpecification.setHref(bundledProductSpec.getHref());
            }
        }
    }

    private void fillProductSpecificationRelationshipIds(List<ProductSpecificationRelationshipFVO> productSpecificationRelationships) {
        for (ProductSpecificationRelationshipFVO productSpecificationRelationship : productSpecificationRelationships) {
            Query q = new Query().addCriteria(new Criteria("name").is(productSpecificationRelationship.getName()));
            final ProductSpecification bundledProductSpec = template.findOne(q, ProductSpecification.class);
            if (bundledProductSpec != null) {
                productSpecificationRelationship.setId(bundledProductSpec.getId());
                productSpecificationRelationship.setHref(bundledProductSpec.getHref());
            }
        }
    }

    private void loadProductOfferings(Path path) throws IOException {
        Files.list(path).flatMap((p) -> {
            try {
                return objectMapper.readValue(Files.newBufferedReader(p), new TypeReference<List<ProductOfferingFVO>>() {
                }).stream();
            }
            catch (IOException ex) {
                throw new RuntimeException("Failure on loading productOfferings from file:" + p.toString(), ex);
            }
        }).forEach(productOffering -> {
            if (productOffering.getProductSpecification() != null) {
                fillProductSpecificationRefIds(List.of(productOffering.getProductSpecification()));
            }
            if (productOffering.getProductOfferingRelationship() != null) {
                fillProductOfferingRelationshipIds(productOffering.getProductOfferingRelationship());
            }
            if (productOffering.getBundledProductOffering() != null) {
                fillBundledProductOfferingIds(productOffering.getBundledProductOffering());
            }
            productOfferingService.createProductOffering(productOffering);
        });
    }

    private void fillProductSpecificationRefIds(List<ProductSpecificationRefFVO> productSpecificationRefs) {
        for (ProductSpecificationRefFVO productSpecificationRef : productSpecificationRefs) {
            Query q = new Query().addCriteria(new Criteria("name").is(productSpecificationRef.getName()));
            final ProductSpecification relProductOffering = template.findOne(q, ProductSpecification.class);
            if (relProductOffering != null) {
                productSpecificationRef.setId(relProductOffering.getId());
                productSpecificationRef.setHref(relProductOffering.getHref());
            }
        }
    }

    private void fillProductOfferingRelationshipIds(List<ProductOfferingRelationshipFVO> productOfferingRelationships) {
        for (ProductOfferingRelationshipFVO productOfferingRelationship : productOfferingRelationships) {
            Query q = new Query().addCriteria(new Criteria("name").is(productOfferingRelationship.getName()));
            final ProductOffering relProductOffering = template.findOne(q, ProductOffering.class);
            if (relProductOffering != null) {
                productOfferingRelationship.setId(relProductOffering.getId());
                productOfferingRelationship.setHref(relProductOffering.getHref());
            }
        }
    }

    private void fillBundledProductOfferingIds(List<BundledProductOfferingFVO> bundledProductOfferings) {
        for (BundledProductOfferingFVO bundledProductOffering : bundledProductOfferings) {
            Query q = new Query().addCriteria(new Criteria("name").is(bundledProductOffering.getName()));
            final ProductOffering relProductOffering = template.findOne(q, ProductOffering.class);
            if (relProductOffering != null) {
                bundledProductOffering.setId(relProductOffering.getId());
                bundledProductOffering.setHref(relProductOffering.getHref());
            }
        }
    }

}
