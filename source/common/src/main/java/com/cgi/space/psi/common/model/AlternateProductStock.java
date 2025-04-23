package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.PlaceRefOrValue;
import com.cgi.space.psi.common.model.ProductRefOrValue;
import com.cgi.space.psi.common.model.ProductStockRef;
import com.cgi.space.psi.common.model.Quantity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * AlternateProductStock is used to log and execute Alternate about product  stock availability
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "AlternateProductStock", description = "AlternateProductStock is used to log and execute Alternate about product  stock availability")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AlternateProductStock {

  @JsonProperty("id")
  private String id;

  @JsonProperty("alternateAvailabilityDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime alternateAvailabilityDate;

  @JsonProperty("alternatePlace")
  private PlaceRefOrValue alternatePlace;

  @JsonProperty("alternateProduct")
  private ProductRefOrValue alternateProduct;

  @JsonProperty("alternateQuantity")
  private Quantity alternateQuantity;

  @JsonProperty("alternateStock")
  private ProductStockRef alternateStock;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public AlternateProductStock id(String id) {
    this.id = id;
    return this;
  }

  /**
   * identifier of the Alternate product stock 
   * @return id
  */
  
  @Schema(name = "id", description = "identifier of the Alternate product stock ", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AlternateProductStock alternateAvailabilityDate(OffsetDateTime alternateAvailabilityDate) {
    this.alternateAvailabilityDate = alternateAvailabilityDate;
    return this;
  }

  /**
   * Alternate date when the product stock is available.
   * @return alternateAvailabilityDate
  */
  @Valid 
  @Schema(name = "alternateAvailabilityDate", description = "Alternate date when the product stock is available.", required = false)
  public OffsetDateTime getAlternateAvailabilityDate() {
    return alternateAvailabilityDate;
  }

  public void setAlternateAvailabilityDate(OffsetDateTime alternateAvailabilityDate) {
    this.alternateAvailabilityDate = alternateAvailabilityDate;
  }

  public AlternateProductStock alternatePlace(PlaceRefOrValue alternatePlace) {
    this.alternatePlace = alternatePlace;
    return this;
  }

  /**
   * Get alternatePlace
   * @return alternatePlace
  */
  @Valid 
  @Schema(name = "alternatePlace", required = false)
  public PlaceRefOrValue getAlternatePlace() {
    return alternatePlace;
  }

  public void setAlternatePlace(PlaceRefOrValue alternatePlace) {
    this.alternatePlace = alternatePlace;
  }

  public AlternateProductStock alternateProduct(ProductRefOrValue alternateProduct) {
    this.alternateProduct = alternateProduct;
    return this;
  }

  /**
   * Get alternateProduct
   * @return alternateProduct
  */
  @Valid 
  @Schema(name = "alternateProduct", required = false)
  public ProductRefOrValue getAlternateProduct() {
    return alternateProduct;
  }

  public void setAlternateProduct(ProductRefOrValue alternateProduct) {
    this.alternateProduct = alternateProduct;
  }

  public AlternateProductStock alternateQuantity(Quantity alternateQuantity) {
    this.alternateQuantity = alternateQuantity;
    return this;
  }

  /**
   * Get alternateQuantity
   * @return alternateQuantity
  */
  @Valid 
  @Schema(name = "alternateQuantity", required = false)
  public Quantity getAlternateQuantity() {
    return alternateQuantity;
  }

  public void setAlternateQuantity(Quantity alternateQuantity) {
    this.alternateQuantity = alternateQuantity;
  }

  public AlternateProductStock alternateStock(ProductStockRef alternateStock) {
    this.alternateStock = alternateStock;
    return this;
  }

  /**
   * Get alternateStock
   * @return alternateStock
  */
  @Valid 
  @Schema(name = "alternateStock", required = false)
  public ProductStockRef getAlternateStock() {
    return alternateStock;
  }

  public void setAlternateStock(ProductStockRef alternateStock) {
    this.alternateStock = alternateStock;
  }

  public AlternateProductStock atBaseType(String atBaseType) {
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

  public AlternateProductStock atSchemaLocation(URI atSchemaLocation) {
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

  public AlternateProductStock atType(String atType) {
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
    AlternateProductStock alternateProductStock = (AlternateProductStock) o;
    return Objects.equals(this.id, alternateProductStock.id) &&
        Objects.equals(this.alternateAvailabilityDate, alternateProductStock.alternateAvailabilityDate) &&
        Objects.equals(this.alternatePlace, alternateProductStock.alternatePlace) &&
        Objects.equals(this.alternateProduct, alternateProductStock.alternateProduct) &&
        Objects.equals(this.alternateQuantity, alternateProductStock.alternateQuantity) &&
        Objects.equals(this.alternateStock, alternateProductStock.alternateStock) &&
        Objects.equals(this.atBaseType, alternateProductStock.atBaseType) &&
        Objects.equals(this.atSchemaLocation, alternateProductStock.atSchemaLocation) &&
        Objects.equals(this.atType, alternateProductStock.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, alternateAvailabilityDate, alternatePlace, alternateProduct, alternateQuantity, alternateStock, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlternateProductStock {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    alternateAvailabilityDate: ").append(toIndentedString(alternateAvailabilityDate)).append("\n");
    sb.append("    alternatePlace: ").append(toIndentedString(alternatePlace)).append("\n");
    sb.append("    alternateProduct: ").append(toIndentedString(alternateProduct)).append("\n");
    sb.append("    alternateQuantity: ").append(toIndentedString(alternateQuantity)).append("\n");
    sb.append("    alternateStock: ").append(toIndentedString(alternateStock)).append("\n");
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

