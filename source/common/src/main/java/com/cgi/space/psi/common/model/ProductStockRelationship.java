package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ProductStockRef;
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
 * Linked product stock  with a type relationship.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ProductStockRelationship", description = "Linked product stock  with a type relationship.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ProductStockRelationship {

  @JsonProperty("relationshipType")
  private String relationshipType;

  @JsonProperty("stockLevel")
  private ProductStockRef stockLevel;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public ProductStockRelationship relationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
    return this;
  }

  /**
   * The type of relationship between product stock
   * @return relationshipType
  */
  @NotNull 
  @Schema(name = "relationshipType", description = "The type of relationship between product stock", required = true)
  public String getRelationshipType() {
    return relationshipType;
  }

  public void setRelationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
  }

  public ProductStockRelationship stockLevel(ProductStockRef stockLevel) {
    this.stockLevel = stockLevel;
    return this;
  }

  /**
   * Get stockLevel
   * @return stockLevel
  */
  @NotNull @Valid 
  @Schema(name = "stockLevel", required = true)
  public ProductStockRef getStockLevel() {
    return stockLevel;
  }

  public void setStockLevel(ProductStockRef stockLevel) {
    this.stockLevel = stockLevel;
  }

  public ProductStockRelationship atBaseType(String atBaseType) {
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

  public ProductStockRelationship atSchemaLocation(URI atSchemaLocation) {
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

  public ProductStockRelationship atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class Extensible name
   * @return atType
  */
  
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class Extensible name", required = false)
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
    ProductStockRelationship productStockRelationship = (ProductStockRelationship) o;
    return Objects.equals(this.relationshipType, productStockRelationship.relationshipType) &&
        Objects.equals(this.stockLevel, productStockRelationship.stockLevel) &&
        Objects.equals(this.atBaseType, productStockRelationship.atBaseType) &&
        Objects.equals(this.atSchemaLocation, productStockRelationship.atSchemaLocation) &&
        Objects.equals(this.atType, productStockRelationship.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(relationshipType, stockLevel, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductStockRelationship {\n");
    sb.append("    relationshipType: ").append(toIndentedString(relationshipType)).append("\n");
    sb.append("    stockLevel: ").append(toIndentedString(stockLevel)).append("\n");
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

