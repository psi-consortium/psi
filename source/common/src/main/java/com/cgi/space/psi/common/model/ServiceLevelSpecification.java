package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ServiceLevelObjectiveRef;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A Service Level Specification represents a pre-defined or negotiated set of Service Level  Objectives. In addition, certain consequences are associated with not meeting the Service Level  Objectives. Service Level Agreements are expressed in terms of Service Level Specifications.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ServiceLevelSpecification", description = "A Service Level Specification represents a pre-defined or negotiated set of Service Level  Objectives. In addition, certain consequences are associated with not meeting the Service Level  Objectives. Service Level Agreements are expressed in terms of Service Level Specifications.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceLevelSpecification {

  @JsonProperty("id")
  private String id;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("description")
  private String description;

  @JsonProperty("name")
  private String name;

  @JsonProperty("relatedServiceLevelObjective")
  @Valid
  private List<ServiceLevelObjectiveRef> relatedServiceLevelObjective = null;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public ServiceLevelSpecification id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier to a service level specification.
   * @return id
  */
  
  @Schema(name = "id", description = "The identifier to a service level specification.", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ServiceLevelSpecification href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * The hyperlink to access a service level specification.
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "The hyperlink to access a service level specification.", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public ServiceLevelSpecification description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A brief introduction of a service level specification.
   * @return description
  */
  
  @Schema(name = "description", description = "A brief introduction of a service level specification.", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ServiceLevelSpecification name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of Service Level Specification
   * @return name
  */
  
  @Schema(name = "name", description = "The name of Service Level Specification", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServiceLevelSpecification relatedServiceLevelObjective(List<ServiceLevelObjectiveRef> relatedServiceLevelObjective) {
    this.relatedServiceLevelObjective = relatedServiceLevelObjective;
    return this;
  }

  public ServiceLevelSpecification addRelatedServiceLevelObjectiveItem(ServiceLevelObjectiveRef relatedServiceLevelObjectiveItem) {
    if (this.relatedServiceLevelObjective == null) {
      this.relatedServiceLevelObjective = new ArrayList<>();
    }
    this.relatedServiceLevelObjective.add(relatedServiceLevelObjectiveItem);
    return this;
  }

  /**
   * A list of objectives related to this service level specification
   * @return relatedServiceLevelObjective
  */
  @Valid 
  @Schema(name = "relatedServiceLevelObjective", description = "A list of objectives related to this service level specification", required = false)
  public List<ServiceLevelObjectiveRef> getRelatedServiceLevelObjective() {
    return relatedServiceLevelObjective;
  }

  public void setRelatedServiceLevelObjective(List<ServiceLevelObjectiveRef> relatedServiceLevelObjective) {
    this.relatedServiceLevelObjective = relatedServiceLevelObjective;
  }

  public ServiceLevelSpecification validFor(TimePeriod validFor) {
    this.validFor = validFor;
    return this;
  }

  /**
   * Get validFor
   * @return validFor
  */
  @Valid 
  @Schema(name = "validFor", required = false)
  public TimePeriod getValidFor() {
    return validFor;
  }

  public void setValidFor(TimePeriod validFor) {
    this.validFor = validFor;
  }

  public ServiceLevelSpecification atBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
    return this;
  }

  /**
   * When sub-classing, this defines the super-class
   * @return atBaseType
  */
  
  @Schema(name = "@baseType", description = "When sub-classing, this defines the super-class", required = false)
  public String getAtBaseType() {
    return atBaseType;
  }

  public void setAtBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
  }

  public ServiceLevelSpecification atSchemaLocation(URI atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships
   * @return atSchemaLocation
  */
  @Valid 
  @Schema(name = "@schemaLocation", description = "A URI to a JSON-Schema file that defines additional attributes and relationships", required = false)
  public URI getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(URI atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public ServiceLevelSpecification atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class entity name
   * @return atType
  */
  
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class entity name", required = false)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceLevelSpecification serviceLevelSpecification = (ServiceLevelSpecification) o;
    return Objects.equals(this.id, serviceLevelSpecification.id) &&
        Objects.equals(this.href, serviceLevelSpecification.href) &&
        Objects.equals(this.description, serviceLevelSpecification.description) &&
        Objects.equals(this.name, serviceLevelSpecification.name) &&
        Objects.equals(this.relatedServiceLevelObjective, serviceLevelSpecification.relatedServiceLevelObjective) &&
        Objects.equals(this.validFor, serviceLevelSpecification.validFor) &&
        Objects.equals(this.atBaseType, serviceLevelSpecification.atBaseType) &&
        Objects.equals(this.atSchemaLocation, serviceLevelSpecification.atSchemaLocation) &&
        Objects.equals(this.atType, serviceLevelSpecification.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, description, name, relatedServiceLevelObjective, validFor, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceLevelSpecification {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    relatedServiceLevelObjective: ").append(toIndentedString(relatedServiceLevelObjective)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
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

