package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.OrderItemRelationshipFVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * OrderItemRelationshipFVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = OrderItemRelationshipFVO.class, name = "OrderItemRelationship")
})

@JsonTypeName("OrderItemRelationship_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class OrderItemRelationshipFVO {

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("id")
  private String id;

  @JsonProperty("relationshipType")
  private String relationshipType;

  public OrderItemRelationshipFVO atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class Extensible name
   * @return atType
  */
  @NotNull 
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class Extensible name", required = true)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  public OrderItemRelationshipFVO atBaseType(String atBaseType) {
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

  public OrderItemRelationshipFVO atSchemaLocation(String atSchemaLocation) {
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

  public OrderItemRelationshipFVO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Id of the related Order item (must be in the same Order)
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Id of the related Order item (must be in the same Order)", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public OrderItemRelationshipFVO relationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
    return this;
  }

  /**
   * Relationship type as relies on, bundles, etc...
   * @return relationshipType
  */
  @NotNull 
  @Schema(name = "relationshipType", description = "Relationship type as relies on, bundles, etc...", required = true)
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
    OrderItemRelationshipFVO orderItemRelationshipFVO = (OrderItemRelationshipFVO) o;
    return Objects.equals(this.atType, orderItemRelationshipFVO.atType) &&
        Objects.equals(this.atBaseType, orderItemRelationshipFVO.atBaseType) &&
        Objects.equals(this.atSchemaLocation, orderItemRelationshipFVO.atSchemaLocation) &&
        Objects.equals(this.id, orderItemRelationshipFVO.id) &&
        Objects.equals(this.relationshipType, orderItemRelationshipFVO.relationshipType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType, atBaseType, atSchemaLocation, id, relationshipType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderItemRelationshipFVO {\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

