package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.EntityRefFVO;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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

@Schema(name = "EntityRelationship_FVO", description = "A uni-directionmal relationship from this entity to a target entity instance")
@JsonTypeName("EntityRelationship_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class EntityRelationshipFVO {

  @JsonProperty("href")
  private URI href;

  @JsonProperty("name")
  private String name;

  @JsonProperty("role")
  private String role;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  @JsonProperty("associationSpec")
  private EntityRefFVO associationSpec;

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

  public EntityRelationshipFVO href(URI href) {
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

  public EntityRelationshipFVO name(String name) {
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

  public EntityRelationshipFVO role(String role) {
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

  public EntityRelationshipFVO validFor(TimePeriod validFor) {
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

  public EntityRelationshipFVO associationSpec(EntityRefFVO associationSpec) {
    this.associationSpec = associationSpec;
    return this;
  }

  /**
   * Get associationSpec
   * @return associationSpec
  */
  @Valid 
  @Schema(name = "associationSpec", required = false)
  public EntityRefFVO getAssociationSpec() {
    return associationSpec;
  }

  public void setAssociationSpec(EntityRefFVO associationSpec) {
    this.associationSpec = associationSpec;
  }

  public EntityRelationshipFVO atBaseType(String atBaseType) {
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

  public EntityRelationshipFVO atSchemaLocation(String atSchemaLocation) {
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

  public EntityRelationshipFVO relationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
    return this;
  }

  /**
   * Type of relationship such as migration, substitution, dependency, exclusivity
   * @return relationshipType
  */
  @NotNull 
  @Schema(name = "relationshipType", description = "Type of relationship such as migration, substitution, dependency, exclusivity", required = true)
  public String getRelationshipType() {
    return relationshipType;
  }

  public void setRelationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
  }

  public EntityRelationshipFVO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EntityRelationshipFVO atReferredType(String atReferredType) {
    this.atReferredType = atReferredType;
    return this;
  }

  /**
   * Get atReferredType
   * @return atReferredType
  */
  @NotNull 
  @Schema(name = "@referredType", required = true)
  public String getAtReferredType() {
    return atReferredType;
  }

  public void setAtReferredType(String atReferredType) {
    this.atReferredType = atReferredType;
  }

  public EntityRelationshipFVO atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * Get atType
   * @return atType
  */
  @NotNull 
  @Schema(name = "@type", required = true)
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
    EntityRelationshipFVO entityRelationshipFVO = (EntityRelationshipFVO) o;
    return Objects.equals(this.href, entityRelationshipFVO.href) &&
        Objects.equals(this.name, entityRelationshipFVO.name) &&
        Objects.equals(this.role, entityRelationshipFVO.role) &&
        Objects.equals(this.validFor, entityRelationshipFVO.validFor) &&
        Objects.equals(this.associationSpec, entityRelationshipFVO.associationSpec) &&
        Objects.equals(this.atBaseType, entityRelationshipFVO.atBaseType) &&
        Objects.equals(this.atSchemaLocation, entityRelationshipFVO.atSchemaLocation) &&
        Objects.equals(this.relationshipType, entityRelationshipFVO.relationshipType) &&
        Objects.equals(this.id, entityRelationshipFVO.id) &&
        Objects.equals(this.atReferredType, entityRelationshipFVO.atReferredType) &&
        Objects.equals(this.atType, entityRelationshipFVO.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, name, role, validFor, associationSpec, atBaseType, atSchemaLocation, relationshipType, id, atReferredType, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntityRelationshipFVO {\n");
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

