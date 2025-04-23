package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ServiceLevelObjectiveRef;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * A Service Level Specification represents a pre-defined or negotiated set of Service Level  Objectives. In addition, certain consequences are associated with not meeting the Service Level  Objectives. Service Level Agreements are expressed in terms of Service Level Specifications. Skipped properties: id,href,validFor
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ServiceLevelSpecification_Update", description = "A Service Level Specification represents a pre-defined or negotiated set of Service Level  Objectives. In addition, certain consequences are associated with not meeting the Service Level  Objectives. Service Level Agreements are expressed in terms of Service Level Specifications. Skipped properties: id,href,validFor")
@JsonTypeName("ServiceLevelSpecification_Update")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceLevelSpecificationUpdate {

  @JsonProperty("description")
  private String description;

  @JsonProperty("name")
  private String name;

  @JsonProperty("relatedServiceLevelObjective")
  @Valid
  private List<ServiceLevelObjectiveRef> relatedServiceLevelObjective = null;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public ServiceLevelSpecificationUpdate description(String description) {
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

  public ServiceLevelSpecificationUpdate name(String name) {
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

  public ServiceLevelSpecificationUpdate relatedServiceLevelObjective(List<ServiceLevelObjectiveRef> relatedServiceLevelObjective) {
    this.relatedServiceLevelObjective = relatedServiceLevelObjective;
    return this;
  }

  public ServiceLevelSpecificationUpdate addRelatedServiceLevelObjectiveItem(ServiceLevelObjectiveRef relatedServiceLevelObjectiveItem) {
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

  public ServiceLevelSpecificationUpdate atBaseType(String atBaseType) {
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

  public ServiceLevelSpecificationUpdate atSchemaLocation(URI atSchemaLocation) {
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

  public ServiceLevelSpecificationUpdate atType(String atType) {
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
    ServiceLevelSpecificationUpdate serviceLevelSpecificationUpdate = (ServiceLevelSpecificationUpdate) o;
    return Objects.equals(this.description, serviceLevelSpecificationUpdate.description) &&
        Objects.equals(this.name, serviceLevelSpecificationUpdate.name) &&
        Objects.equals(this.relatedServiceLevelObjective, serviceLevelSpecificationUpdate.relatedServiceLevelObjective) &&
        Objects.equals(this.atBaseType, serviceLevelSpecificationUpdate.atBaseType) &&
        Objects.equals(this.atSchemaLocation, serviceLevelSpecificationUpdate.atSchemaLocation) &&
        Objects.equals(this.atType, serviceLevelSpecificationUpdate.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, name, relatedServiceLevelObjective, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceLevelSpecificationUpdate {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    relatedServiceLevelObjective: ").append(toIndentedString(relatedServiceLevelObjective)).append("\n");
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

