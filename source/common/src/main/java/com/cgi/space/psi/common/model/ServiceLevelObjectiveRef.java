package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A set of Service Level Objectives that are contained in the Service Level Specification.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ServiceLevelObjectiveRef", description = "A set of Service Level Objectives that are contained in the Service Level Specification.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceLevelObjectiveRef {

  @JsonProperty("id")
  private String id;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@referredType")
  private String atReferredType;

  @JsonProperty("name")
  private String name;

  public ServiceLevelObjectiveRef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of a service level object.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "The identifier of a service level object.", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ServiceLevelObjectiveRef href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * The hyperlink to access a service level object.
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "The hyperlink to access a service level object.", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public ServiceLevelObjectiveRef atBaseType(String atBaseType) {
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

  public ServiceLevelObjectiveRef atSchemaLocation(URI atSchemaLocation) {
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

  public ServiceLevelObjectiveRef atType(String atType) {
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

  public ServiceLevelObjectiveRef atReferredType(String atReferredType) {
    this.atReferredType = atReferredType;
    return this;
  }

  /**
   * The actual type of the target instance when needed for disambiguation.
   * @return atReferredType
  */
  
  @Schema(name = "@referredType", description = "The actual type of the target instance when needed for disambiguation.", required = false)
  public String getAtReferredType() {
    return atReferredType;
  }

  public void setAtReferredType(String atReferredType) {
    this.atReferredType = atReferredType;
  }

  public ServiceLevelObjectiveRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of Service Level Objective.
   * @return name
  */
  
  @Schema(name = "name", description = "The name of Service Level Objective.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceLevelObjectiveRef serviceLevelObjectiveRef = (ServiceLevelObjectiveRef) o;
    return Objects.equals(this.id, serviceLevelObjectiveRef.id) &&
        Objects.equals(this.href, serviceLevelObjectiveRef.href) &&
        Objects.equals(this.atBaseType, serviceLevelObjectiveRef.atBaseType) &&
        Objects.equals(this.atSchemaLocation, serviceLevelObjectiveRef.atSchemaLocation) &&
        Objects.equals(this.atType, serviceLevelObjectiveRef.atType) &&
        Objects.equals(this.atReferredType, serviceLevelObjectiveRef.atReferredType) &&
        Objects.equals(this.name, serviceLevelObjectiveRef.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, atBaseType, atSchemaLocation, atType, atReferredType, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceLevelObjectiveRef {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atReferredType: ").append(toIndentedString(atReferredType)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

