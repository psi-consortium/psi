package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AssociationSpecificationRefFVO;
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
 * A migration, substitution, dependency or exclusivity relationship between/among entity specifications.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "EntitySpecificationRelationship_FVO", description = "A migration, substitution, dependency or exclusivity relationship between/among entity specifications.")
@JsonTypeName("EntitySpecificationRelationship_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class EntitySpecificationRelationshipFVO {

  @JsonProperty("href")
  private URI href;

  @JsonProperty("name")
  private String name;

  @JsonProperty("role")
  private String role;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  @JsonProperty("associationSpec")
  private AssociationSpecificationRefFVO associationSpec;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("relationshipType")
  private String relationshipType;

  public EntitySpecificationRelationshipFVO href(URI href) {
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

  public EntitySpecificationRelationshipFVO name(String name) {
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

  public EntitySpecificationRelationshipFVO role(String role) {
    this.role = role;
    return this;
  }

  /**
   * The association role for this entity specification
   * @return role
  */
  @NotNull 
  @Schema(name = "role", description = "The association role for this entity specification", required = true)
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public EntitySpecificationRelationshipFVO validFor(TimePeriod validFor) {
    this.validFor = validFor;
    return this;
  }

  /**
   * Get validFor
   * @return validFor
  */
  @NotNull @Valid 
  @Schema(name = "validFor", required = true)
  public TimePeriod getValidFor() {
    return validFor;
  }

  public void setValidFor(TimePeriod validFor) {
    this.validFor = validFor;
  }

  public EntitySpecificationRelationshipFVO associationSpec(AssociationSpecificationRefFVO associationSpec) {
    this.associationSpec = associationSpec;
    return this;
  }

  /**
   * Get associationSpec
   * @return associationSpec
  */
  @NotNull @Valid 
  @Schema(name = "associationSpec", required = true)
  public AssociationSpecificationRefFVO getAssociationSpec() {
    return associationSpec;
  }

  public void setAssociationSpec(AssociationSpecificationRefFVO associationSpec) {
    this.associationSpec = associationSpec;
  }

  public EntitySpecificationRelationshipFVO atBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
    return this;
  }

  /**
   * Get atBaseType
   * @return atBaseType
  */
  
  @Schema(name = "@baseType", required = false)
  public String getAtBaseType() {
    return atBaseType;
  }

  public void setAtBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
  }

  public EntitySpecificationRelationshipFVO atSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * Get atSchemaLocation
   * @return atSchemaLocation
  */
  
  @Schema(name = "@schemaLocation", required = false)
  public String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public EntitySpecificationRelationshipFVO relationshipType(String relationshipType) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntitySpecificationRelationshipFVO entitySpecificationRelationshipFVO = (EntitySpecificationRelationshipFVO) o;
    return Objects.equals(this.href, entitySpecificationRelationshipFVO.href) &&
        Objects.equals(this.name, entitySpecificationRelationshipFVO.name) &&
        Objects.equals(this.role, entitySpecificationRelationshipFVO.role) &&
        Objects.equals(this.validFor, entitySpecificationRelationshipFVO.validFor) &&
        Objects.equals(this.associationSpec, entitySpecificationRelationshipFVO.associationSpec) &&
        Objects.equals(this.atBaseType, entitySpecificationRelationshipFVO.atBaseType) &&
        Objects.equals(this.atSchemaLocation, entitySpecificationRelationshipFVO.atSchemaLocation) &&
        Objects.equals(this.relationshipType, entitySpecificationRelationshipFVO.relationshipType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, name, role, validFor, associationSpec, atBaseType, atSchemaLocation, relationshipType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntitySpecificationRelationshipFVO {\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
    sb.append("    associationSpec: ").append(toIndentedString(associationSpec)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    relationshipType: ").append(toIndentedString(relationshipType)).append("\n");
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

