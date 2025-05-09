package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AttachmentOrDocumentRef;
import com.cgi.space.psi.common.model.CharacteristicSpecificationFVO;
import com.cgi.space.psi.common.model.ConstraintRefFVO;
import com.cgi.space.psi.common.model.EntityRelationshipFVO;
import com.cgi.space.psi.common.model.EntitySpecificationFVO;
import com.cgi.space.psi.common.model.EntitySpecificationRelationshipFVO;
import com.cgi.space.psi.common.model.FeatureSpecificationFVO;
import com.cgi.space.psi.common.model.IntentSpecificationRefFVO;
import com.cgi.space.psi.common.model.RelatedPartyRefOrPartyRoleRefFVO;
import com.cgi.space.psi.common.model.ResourceSpecificationRefFVO;
import com.cgi.space.psi.common.model.ServiceLevelSpecificationRefFVO;
import com.cgi.space.psi.common.model.ServiceSpecificationFVO;
import com.cgi.space.psi.common.model.TargetEntitySchemaFVO;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ServiceSpecificationFVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = EntitySpecificationFVO.class, name = "EntitySpecification"),
  @JsonSubTypes.Type(value = ServiceSpecificationFVO.class, name = "ServiceSpecification")
})

@JsonTypeName("ServiceSpecification_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceSpecificationFVO extends EntitySpecificationFVO {

  @JsonProperty("resourceSpecification")
  @Valid
  private List<ResourceSpecificationRefFVO> resourceSpecification = null;

  @JsonProperty("serviceLevelSpecification")
  @Valid
  private List<ServiceLevelSpecificationRefFVO> serviceLevelSpecification = null;

  @JsonProperty("serviceSpecRelationship")
  @Valid
  private List<EntityRelationshipFVO> serviceSpecRelationship = null;

  @JsonProperty("intentSpecification")
  private IntentSpecificationRefFVO intentSpecification;

  @JsonProperty("featureSpecification")
  @Valid
  private List<FeatureSpecificationFVO> featureSpecification = null;

  @JsonProperty("category")
  private String category;

  public ServiceSpecificationFVO resourceSpecification(List<ResourceSpecificationRefFVO> resourceSpecification) {
    this.resourceSpecification = resourceSpecification;
    return this;
  }

  public ServiceSpecificationFVO addResourceSpecificationItem(ResourceSpecificationRefFVO resourceSpecificationItem) {
    if (this.resourceSpecification == null) {
      this.resourceSpecification = new ArrayList<>();
    }
    this.resourceSpecification.add(resourceSpecificationItem);
    return this;
  }

  /**
   * A list of resource specification references (ResourceSpecificationRef [*]). The ResourceSpecification is required for a service specification with type ResourceFacingServiceSpecification (RFSS).
   * @return resourceSpecification
  */
  @Valid 
  @Schema(name = "resourceSpecification", description = "A list of resource specification references (ResourceSpecificationRef [*]). The ResourceSpecification is required for a service specification with type ResourceFacingServiceSpecification (RFSS).", required = false)
  public List<ResourceSpecificationRefFVO> getResourceSpecification() {
    return resourceSpecification;
  }

  public void setResourceSpecification(List<ResourceSpecificationRefFVO> resourceSpecification) {
    this.resourceSpecification = resourceSpecification;
  }

  public ServiceSpecificationFVO serviceLevelSpecification(List<ServiceLevelSpecificationRefFVO> serviceLevelSpecification) {
    this.serviceLevelSpecification = serviceLevelSpecification;
    return this;
  }

  public ServiceSpecificationFVO addServiceLevelSpecificationItem(ServiceLevelSpecificationRefFVO serviceLevelSpecificationItem) {
    if (this.serviceLevelSpecification == null) {
      this.serviceLevelSpecification = new ArrayList<>();
    }
    this.serviceLevelSpecification.add(serviceLevelSpecificationItem);
    return this;
  }

  /**
   * A list of service level specifications related to this service specification, and which will need to be satisifiable for corresponding service instances; e.g. Gold, Platinum
   * @return serviceLevelSpecification
  */
  @Valid 
  @Schema(name = "serviceLevelSpecification", description = "A list of service level specifications related to this service specification, and which will need to be satisifiable for corresponding service instances; e.g. Gold, Platinum", required = false)
  public List<ServiceLevelSpecificationRefFVO> getServiceLevelSpecification() {
    return serviceLevelSpecification;
  }

  public void setServiceLevelSpecification(List<ServiceLevelSpecificationRefFVO> serviceLevelSpecification) {
    this.serviceLevelSpecification = serviceLevelSpecification;
  }

  public ServiceSpecificationFVO serviceSpecRelationship(List<EntityRelationshipFVO> serviceSpecRelationship) {
    this.serviceSpecRelationship = serviceSpecRelationship;
    return this;
  }

  public ServiceSpecificationFVO addServiceSpecRelationshipItem(EntityRelationshipFVO serviceSpecRelationshipItem) {
    if (this.serviceSpecRelationship == null) {
      this.serviceSpecRelationship = new ArrayList<>();
    }
    this.serviceSpecRelationship.add(serviceSpecRelationshipItem);
    return this;
  }

  /**
   * A list of service specification relationships related to this specification, e.g. migration, substitution, dependency or exclusivity relationship
   * @return serviceSpecRelationship
  */
  @Valid 
  @Schema(name = "serviceSpecRelationship", description = "A list of service specification relationships related to this specification, e.g. migration, substitution, dependency or exclusivity relationship", required = false)
  public List<EntityRelationshipFVO> getServiceSpecRelationship() {
    return serviceSpecRelationship;
  }

  public void setServiceSpecRelationship(List<EntityRelationshipFVO> serviceSpecRelationship) {
    this.serviceSpecRelationship = serviceSpecRelationship;
  }

  public ServiceSpecificationFVO intentSpecification(IntentSpecificationRefFVO intentSpecification) {
    this.intentSpecification = intentSpecification;
    return this;
  }

  /**
   * Get intentSpecification
   * @return intentSpecification
  */
  @Valid 
  @Schema(name = "intentSpecification", required = false)
  public IntentSpecificationRefFVO getIntentSpecification() {
    return intentSpecification;
  }

  public void setIntentSpecification(IntentSpecificationRefFVO intentSpecification) {
    this.intentSpecification = intentSpecification;
  }

  public ServiceSpecificationFVO featureSpecification(List<FeatureSpecificationFVO> featureSpecification) {
    this.featureSpecification = featureSpecification;
    return this;
  }

  public ServiceSpecificationFVO addFeatureSpecificationItem(FeatureSpecificationFVO featureSpecificationItem) {
    if (this.featureSpecification == null) {
      this.featureSpecification = new ArrayList<>();
    }
    this.featureSpecification.add(featureSpecificationItem);
    return this;
  }

  /**
   * A list of Features for this specification.
   * @return featureSpecification
  */
  @Valid 
  @Schema(name = "featureSpecification", description = "A list of Features for this specification.", required = false)
  public List<FeatureSpecificationFVO> getFeatureSpecification() {
    return featureSpecification;
  }

  public void setFeatureSpecification(List<FeatureSpecificationFVO> featureSpecification) {
    this.featureSpecification = featureSpecification;
  }

  public ServiceSpecificationFVO category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Category of the target service like Telephony, InternetAccess, IP-Trunk and so on.
   * @return category
  */
  
  @Schema(name = "category", description = "Category of the target service like Telephony, InternetAccess, IP-Trunk and so on.", required = false)
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public ServiceSpecificationFVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public ServiceSpecificationFVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public ServiceSpecificationFVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public ServiceSpecificationFVO id(String id) {
    super.setId(id);
    return this;
  }

  public ServiceSpecificationFVO name(String name) {
    super.setName(name);
    return this;
  }

  public ServiceSpecificationFVO description(String description) {
    super.setDescription(description);
    return this;
  }

  public ServiceSpecificationFVO lastUpdate(OffsetDateTime lastUpdate) {
    super.setLastUpdate(lastUpdate);
    return this;
  }

  public ServiceSpecificationFVO lifecycleStatus(String lifecycleStatus) {
    super.setLifecycleStatus(lifecycleStatus);
    return this;
  }

  public ServiceSpecificationFVO isBundle(Boolean isBundle) {
    super.setIsBundle(isBundle);
    return this;
  }

  public ServiceSpecificationFVO validFor(TimePeriod validFor) {
    super.setValidFor(validFor);
    return this;
  }

  public ServiceSpecificationFVO version(String version) {
    super.setVersion(version);
    return this;
  }

  public ServiceSpecificationFVO attachment(List<AttachmentOrDocumentRef> attachment) {
    super.setAttachment(attachment);
    return this;
  }

  public ServiceSpecificationFVO addAttachmentItem(AttachmentOrDocumentRef attachmentItem) {
    super.addAttachmentItem(attachmentItem);
    return this;
  }

  public ServiceSpecificationFVO targetEntitySchema(TargetEntitySchemaFVO targetEntitySchema) {
    super.setTargetEntitySchema(targetEntitySchema);
    return this;
  }

  public ServiceSpecificationFVO specCharacteristic(List<CharacteristicSpecificationFVO> specCharacteristic) {
    super.setSpecCharacteristic(specCharacteristic);
    return this;
  }

  public ServiceSpecificationFVO addSpecCharacteristicItem(CharacteristicSpecificationFVO specCharacteristicItem) {
    super.addSpecCharacteristicItem(specCharacteristicItem);
    return this;
  }

  public ServiceSpecificationFVO relatedParty(List<RelatedPartyRefOrPartyRoleRefFVO> relatedParty) {
    super.setRelatedParty(relatedParty);
    return this;
  }

  public ServiceSpecificationFVO addRelatedPartyItem(RelatedPartyRefOrPartyRoleRefFVO relatedPartyItem) {
    super.addRelatedPartyItem(relatedPartyItem);
    return this;
  }

  public ServiceSpecificationFVO constraint(List<ConstraintRefFVO> constraint) {
    super.setConstraint(constraint);
    return this;
  }

  public ServiceSpecificationFVO addConstraintItem(ConstraintRefFVO constraintItem) {
    super.addConstraintItem(constraintItem);
    return this;
  }

  public ServiceSpecificationFVO entitySpecRelationship(List<EntitySpecificationRelationshipFVO> entitySpecRelationship) {
    super.setEntitySpecRelationship(entitySpecRelationship);
    return this;
  }

  public ServiceSpecificationFVO addEntitySpecRelationshipItem(EntitySpecificationRelationshipFVO entitySpecRelationshipItem) {
    super.addEntitySpecRelationshipItem(entitySpecRelationshipItem);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceSpecificationFVO serviceSpecificationFVO = (ServiceSpecificationFVO) o;
    return Objects.equals(this.resourceSpecification, serviceSpecificationFVO.resourceSpecification) &&
        Objects.equals(this.serviceLevelSpecification, serviceSpecificationFVO.serviceLevelSpecification) &&
        Objects.equals(this.serviceSpecRelationship, serviceSpecificationFVO.serviceSpecRelationship) &&
        Objects.equals(this.intentSpecification, serviceSpecificationFVO.intentSpecification) &&
        Objects.equals(this.featureSpecification, serviceSpecificationFVO.featureSpecification) &&
        Objects.equals(this.category, serviceSpecificationFVO.category) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resourceSpecification, serviceLevelSpecification, serviceSpecRelationship, intentSpecification, featureSpecification, category, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceSpecificationFVO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    resourceSpecification: ").append(toIndentedString(resourceSpecification)).append("\n");
    sb.append("    serviceLevelSpecification: ").append(toIndentedString(serviceLevelSpecification)).append("\n");
    sb.append("    serviceSpecRelationship: ").append(toIndentedString(serviceSpecRelationship)).append("\n");
    sb.append("    intentSpecification: ").append(toIndentedString(intentSpecification)).append("\n");
    sb.append("    featureSpecification: ").append(toIndentedString(featureSpecification)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

