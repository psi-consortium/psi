package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A target/threshold crossing or a target/threshold ceased to be crossing results in a consequence.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ServiceLevelSpecConsequence", description = "A target/threshold crossing or a target/threshold ceased to be crossing results in a consequence.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceLevelSpecConsequence {

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("prescribedAction")
  private String prescribedAction;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public ServiceLevelSpecConsequence name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A word, term, or phrase by which a Consequence is known and distinguished from other measurements.
   * @return name
  */
  
  @Schema(name = "name", description = "A word, term, or phrase by which a Consequence is known and distinguished from other measurements.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServiceLevelSpecConsequence description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A description of a Consequence.
   * @return description
  */
  
  @Schema(name = "description", description = "A description of a Consequence.", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ServiceLevelSpecConsequence prescribedAction(String prescribedAction) {
    this.prescribedAction = prescribedAction;
    return this;
  }

  /**
   * Contains information on a prescribed action that should be applied.
   * @return prescribedAction
  */
  
  @Schema(name = "prescribedAction", description = "Contains information on a prescribed action that should be applied.", required = false)
  public String getPrescribedAction() {
    return prescribedAction;
  }

  public void setPrescribedAction(String prescribedAction) {
    this.prescribedAction = prescribedAction;
  }

  public ServiceLevelSpecConsequence atBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
    return this;
  }

  /**
   * When sub-classing, this defines the super-class.
   * @return atBaseType
  */
  
  @Schema(name = "@baseType", description = "When sub-classing, this defines the super-class.", required = false)
  public String getAtBaseType() {
    return atBaseType;
  }

  public void setAtBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
  }

  public ServiceLevelSpecConsequence atSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships.
   * @return atSchemaLocation
  */
  
  @Schema(name = "@schemaLocation", description = "A URI to a JSON-Schema file that defines additional attributes and relationships.", required = false)
  public String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public ServiceLevelSpecConsequence atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * Get atType
   * @return atType
  */
  
  @Schema(name = "@type", required = false)
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
    ServiceLevelSpecConsequence serviceLevelSpecConsequence = (ServiceLevelSpecConsequence) o;
    return Objects.equals(this.name, serviceLevelSpecConsequence.name) &&
        Objects.equals(this.description, serviceLevelSpecConsequence.description) &&
        Objects.equals(this.prescribedAction, serviceLevelSpecConsequence.prescribedAction) &&
        Objects.equals(this.atBaseType, serviceLevelSpecConsequence.atBaseType) &&
        Objects.equals(this.atSchemaLocation, serviceLevelSpecConsequence.atSchemaLocation) &&
        Objects.equals(this.atType, serviceLevelSpecConsequence.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, prescribedAction, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceLevelSpecConsequence {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    prescribedAction: ").append(toIndentedString(prescribedAction)).append("\n");
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

