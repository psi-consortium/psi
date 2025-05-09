package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AttachmentOrDocumentRef;
import com.cgi.space.psi.common.model.CharacteristicSpecification;
import com.cgi.space.psi.common.model.ExternalIdentifier;
import com.cgi.space.psi.common.model.FeatureSpecification;
import com.cgi.space.psi.common.model.IntentSpecificationRef;
import com.cgi.space.psi.common.model.LogicalResourceSpecification;
import com.cgi.space.psi.common.model.PhysicalResourceSpecification;
import com.cgi.space.psi.common.model.RelatedPartyRefOrPartyRoleRef;
import com.cgi.space.psi.common.model.ResourceFunctionSpecification;
import com.cgi.space.psi.common.model.ResourceSpecification;
import com.cgi.space.psi.common.model.ResourceSpecificationRelationship;
import com.cgi.space.psi.common.model.TargetResourceSchema;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
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
 * This is a derived class of ResourceSpecification, and is used to define the invariant characteristics and behavior (attributes, methods, constraints, and relationships) of a LogicalResource.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "LogicalResourceSpecification", description = "This is a derived class of ResourceSpecification, and is used to define the invariant characteristics and behavior (attributes, methods, constraints, and relationships) of a LogicalResource.")
@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = LogicalResourceSpecification.class, name = "LogicalResourceSpecification"),
  @JsonSubTypes.Type(value = PhysicalResourceSpecification.class, name = "PhysicalResourceSpecification"),
  @JsonSubTypes.Type(value = ResourceFunctionSpecification.class, name = "ResourceFunctionSpecification"),
  @JsonSubTypes.Type(value = ResourceSpecification.class, name = "ResourceSpecification")
})

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class LogicalResourceSpecification extends ResourceSpecification {

  public LogicalResourceSpecification atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public LogicalResourceSpecification atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public LogicalResourceSpecification atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public LogicalResourceSpecification href(URI href) {
    super.setHref(href);
    return this;
  }

  public LogicalResourceSpecification id(String id) {
    super.setId(id);
    return this;
  }

  public LogicalResourceSpecification description(String description) {
    super.setDescription(description);
    return this;
  }

  public LogicalResourceSpecification version(String version) {
    super.setVersion(version);
    return this;
  }

  public LogicalResourceSpecification validFor(TimePeriod validFor) {
    super.setValidFor(validFor);
    return this;
  }

  public LogicalResourceSpecification isBundle(Boolean isBundle) {
    super.setIsBundle(isBundle);
    return this;
  }

  public LogicalResourceSpecification lastUpdate(OffsetDateTime lastUpdate) {
    super.setLastUpdate(lastUpdate);
    return this;
  }

  public LogicalResourceSpecification lifecycleStatus(String lifecycleStatus) {
    super.setLifecycleStatus(lifecycleStatus);
    return this;
  }

  public LogicalResourceSpecification name(String name) {
    super.setName(name);
    return this;
  }

  public LogicalResourceSpecification category(String category) {
    super.setCategory(category);
    return this;
  }

  public LogicalResourceSpecification targetResourceSchema(TargetResourceSchema targetResourceSchema) {
    super.setTargetResourceSchema(targetResourceSchema);
    return this;
  }

  public LogicalResourceSpecification featureSpecification(List<FeatureSpecification> featureSpecification) {
    super.setFeatureSpecification(featureSpecification);
    return this;
  }

  public LogicalResourceSpecification addFeatureSpecificationItem(FeatureSpecification featureSpecificationItem) {
    super.addFeatureSpecificationItem(featureSpecificationItem);
    return this;
  }

  public LogicalResourceSpecification attachment(List<AttachmentOrDocumentRef> attachment) {
    super.setAttachment(attachment);
    return this;
  }

  public LogicalResourceSpecification addAttachmentItem(AttachmentOrDocumentRef attachmentItem) {
    super.addAttachmentItem(attachmentItem);
    return this;
  }

  public LogicalResourceSpecification relatedParty(List<RelatedPartyRefOrPartyRoleRef> relatedParty) {
    super.setRelatedParty(relatedParty);
    return this;
  }

  public LogicalResourceSpecification addRelatedPartyItem(RelatedPartyRefOrPartyRoleRef relatedPartyItem) {
    super.addRelatedPartyItem(relatedPartyItem);
    return this;
  }

  public LogicalResourceSpecification resourceSpecCharacteristic(List<CharacteristicSpecification> resourceSpecCharacteristic) {
    super.setResourceSpecCharacteristic(resourceSpecCharacteristic);
    return this;
  }

  public LogicalResourceSpecification addResourceSpecCharacteristicItem(CharacteristicSpecification resourceSpecCharacteristicItem) {
    super.addResourceSpecCharacteristicItem(resourceSpecCharacteristicItem);
    return this;
  }

  public LogicalResourceSpecification resourceSpecRelationship(List<ResourceSpecificationRelationship> resourceSpecRelationship) {
    super.setResourceSpecRelationship(resourceSpecRelationship);
    return this;
  }

  public LogicalResourceSpecification addResourceSpecRelationshipItem(ResourceSpecificationRelationship resourceSpecRelationshipItem) {
    super.addResourceSpecRelationshipItem(resourceSpecRelationshipItem);
    return this;
  }

  public LogicalResourceSpecification intentSpecification(IntentSpecificationRef intentSpecification) {
    super.setIntentSpecification(intentSpecification);
    return this;
  }

  public LogicalResourceSpecification externalIdentifier(List<ExternalIdentifier> externalIdentifier) {
    super.setExternalIdentifier(externalIdentifier);
    return this;
  }

  public LogicalResourceSpecification addExternalIdentifierItem(ExternalIdentifier externalIdentifierItem) {
    super.addExternalIdentifierItem(externalIdentifierItem);
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
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogicalResourceSpecification {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

