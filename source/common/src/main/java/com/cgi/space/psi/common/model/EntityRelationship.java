package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.EntityRef;
import com.cgi.space.psi.common.model.TimePeriod;
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
 * A uni-directionmal relationship from this entity to a target entity instance
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "EntityRelationship", description = "A uni-directionmal relationship from this entity to a target entity instance")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class EntityRelationship {

  @JsonProperty("href")
  private URI href;

  @JsonProperty("name")
  private String name;

  @JsonProperty("role")
  private String role;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  @JsonProperty("associationSpec")
  private EntityRef associationSpec;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("relationshipType")
  private String relationshipType;

  @JsonProperty("id")
  private String id;

  @JsonProperty("@referredType")
  private String atReferredType;

  @JsonProperty("@type")
  private String atType;

  public EntityRelationship href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * Get href
   * @return href
  */
  @Valid 
  @Schema(name = "href", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public EntityRelationship name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  
  @Schema(name = "name", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EntityRelationship role(String role) {
    this.role = role;
    return this;
  }

  /**
   * The association role for this entity
   * @return role
  */
  
  @Schema(name = "role", description = "The association role for this entity", required = false)
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public EntityRelationship validFor(TimePeriod validFor) {
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

  public EntityRelationship associationSpec(EntityRef associationSpec) {
    this.associationSpec = associationSpec;
    return this;
  }

  /**
   * Get associationSpec
   * @return associationSpec
  */
  @Valid 
  @Schema(name = "associationSpec", required = false)
  public EntityRef getAssociationSpec() {
    return associationSpec;
  }

  public void setAssociationSpec(EntityRef associationSpec) {
    this.associationSpec = associationSpec;
  }

  public EntityRelationship atBaseType(String atBaseType) {
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

  public EntityRelationship atSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships
   * @return atSchemaLocation
  */
  
  @Schema(name = "@schemaLocation", description = "A URI to a JSON-Schema file that defines additional attributes and relationships", required = false)
  public String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public EntityRelationship relationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
    return this;
  }

  /**
   * Type of relationship such as migration, substitution, dependency, exclusivity
   * @return relationshipType
  */
  
  @Schema(name = "relationshipType", description = "Type of relationship such as migration, substitution, dependency, exclusivity", required = false)
  public String getRelationshipType() {
    return relationshipType;
  }

  public void setRelationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
  }

  public EntityRelationship id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EntityRelationship atReferredType(String atReferredType) {
    this.atReferredType = atReferredType;
    return this;
  }

  /**
   * Get atReferredType
   * @return atReferredType
  */
  
  @Schema(name = "@referredType", required = false)
  public String getAtReferredType() {
    return atReferredType;
  }

  public void setAtReferredType(String atReferredType) {
    this.atReferredType = atReferredType;
  }

  public EntityRelationship atType(String atType) {
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
    EntityRelationship entityRelationship = (EntityRelationship) o;
    return Objects.equals(this.href, entityRelationship.href) &&
        Objects.equals(this.name, entityRelationship.name) &&
        Objects.equals(this.role, entityRelationship.role) &&
        Objects.equals(this.validFor, entityRelationship.validFor) &&
        Objects.equals(this.associationSpec, entityRelationship.associationSpec) &&
        Objects.equals(this.atBaseType, entityRelationship.atBaseType) &&
        Objects.equals(this.atSchemaLocation, entityRelationship.atSchemaLocation) &&
        Objects.equals(this.relationshipType, entityRelationship.relationshipType) &&
        Objects.equals(this.id, entityRelationship.id) &&
        Objects.equals(this.atReferredType, entityRelationship.atReferredType) &&
        Objects.equals(this.atType, entityRelationship.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, name, role, validFor, associationSpec, atBaseType, atSchemaLocation, relationshipType, id, atReferredType, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntityRelationship {\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
    sb.append("    associationSpec: ").append(toIndentedString(associationSpec)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    relationshipType: ").append(toIndentedString(relationshipType)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    atReferredType: ").append(toIndentedString(atReferredType)).append("\n");
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

