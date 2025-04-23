package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AlternateProductStock;
import com.cgi.space.psi.common.model.AvailabilityResultType;
import com.cgi.space.psi.common.model.ProductStock;
import com.cgi.space.psi.common.model.Quantity;
import com.cgi.space.psi.common.model.TaskStateType;
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
 * CheckProductStockItem is used to log and execute query about one product (or configured product) stock availability
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "CheckProductStockItem", description = "CheckProductStockItem is used to log and execute query about one product (or configured product) stock availability")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CheckProductStockItem {

  @JsonProperty("id")
  private String id;

  @JsonProperty("availabilityResult")
  private AvailabilityResultType availabilityResult;

  @JsonProperty("provideAlternative")
  private Boolean provideAlternative = false;

  @JsonProperty("alternate")
  @Valid
  private List<AlternateProductStock> alternate = null;

  @JsonProperty("checkedProductStock")
  private ProductStock checkedProductStock;

  @JsonProperty("requestedQuantity")
  private Quantity requestedQuantity;

  @JsonProperty("state")
  private TaskStateType state;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public CheckProductStockItem id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the Check Product Stock item
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier of the Check Product Stock item", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CheckProductStockItem availabilityResult(AvailabilityResultType availabilityResult) {
    this.availabilityResult = availabilityResult;
    return this;
  }

  /**
   * Get availabilityResult
   * @return availabilityResult
  */
  @Valid 
  @Schema(name = "availabilityResult", required = false)
  public AvailabilityResultType getAvailabilityResult() {
    return availabilityResult;
  }

  public void setAvailabilityResult(AvailabilityResultType availabilityResult) {
    this.availabilityResult = availabilityResult;
  }

  public CheckProductStockItem provideAlternative(Boolean provideAlternative) {
    this.provideAlternative = provideAlternative;
    return this;
  }

  /**
   * When the value is TRUE means that alternative proposal should be provided (from other product stock, different date or quantity)
   * @return provideAlternative
  */
  
  @Schema(name = "provideAlternative", description = "When the value is TRUE means that alternative proposal should be provided (from other product stock, different date or quantity)", required = false)
  public Boolean getProvideAlternative() {
    return provideAlternative;
  }

  public void setProvideAlternative(Boolean provideAlternative) {
    this.provideAlternative = provideAlternative;
  }

  public CheckProductStockItem alternate(List<AlternateProductStock> alternate) {
    this.alternate = alternate;
    return this;
  }

  public CheckProductStockItem addAlternateItem(AlternateProductStock alternateItem) {
    if (this.alternate == null) {
      this.alternate = new ArrayList<>();
    }
    this.alternate.add(alternateItem);
    return this;
  }

  /**
   * A list of alternate availability - from other product stock , different date or quantity
   * @return alternate
  */
  @Valid 
  @Schema(name = "alternate", description = "A list of alternate availability - from other product stock , different date or quantity", required = false)
  public List<AlternateProductStock> getAlternate() {
    return alternate;
  }

  public void setAlternate(List<AlternateProductStock> alternate) {
    this.alternate = alternate;
  }

  public CheckProductStockItem checkedProductStock(ProductStock checkedProductStock) {
    this.checkedProductStock = checkedProductStock;
    return this;
  }

  /**
   * Get checkedProductStock
   * @return checkedProductStock
  */
  @NotNull @Valid 
  @Schema(name = "checkedProductStock", required = true)
  public ProductStock getCheckedProductStock() {
    return checkedProductStock;
  }

  public void setCheckedProductStock(ProductStock checkedProductStock) {
    this.checkedProductStock = checkedProductStock;
  }

  public CheckProductStockItem requestedQuantity(Quantity requestedQuantity) {
    this.requestedQuantity = requestedQuantity;
    return this;
  }

  /**
   * Get requestedQuantity
   * @return requestedQuantity
  */
  @NotNull @Valid 
  @Schema(name = "requestedQuantity", required = true)
  public Quantity getRequestedQuantity() {
    return requestedQuantity;
  }

  public void setRequestedQuantity(Quantity requestedQuantity) {
    this.requestedQuantity = requestedQuantity;
  }

  public CheckProductStockItem state(TaskStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @Valid 
  @Schema(name = "state", required = false)
  public TaskStateType getState() {
    return state;
  }

  public void setState(TaskStateType state) {
    this.state = state;
  }

  public CheckProductStockItem atBaseType(String atBaseType) {
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

  public CheckProductStockItem atSchemaLocation(URI atSchemaLocation) {
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

  public CheckProductStockItem atType(String atType) {
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
    CheckProductStockItem checkProductStockItem = (CheckProductStockItem) o;
    return Objects.equals(this.id, checkProductStockItem.id) &&
        Objects.equals(this.availabilityResult, checkProductStockItem.availabilityResult) &&
        Objects.equals(this.provideAlternative, checkProductStockItem.provideAlternative) &&
        Objects.equals(this.alternate, checkProductStockItem.alternate) &&
        Objects.equals(this.checkedProductStock, checkProductStockItem.checkedProductStock) &&
        Objects.equals(this.requestedQuantity, checkProductStockItem.requestedQuantity) &&
        Objects.equals(this.state, checkProductStockItem.state) &&
        Objects.equals(this.atBaseType, checkProductStockItem.atBaseType) &&
        Objects.equals(this.atSchemaLocation, checkProductStockItem.atSchemaLocation) &&
        Objects.equals(this.atType, checkProductStockItem.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, availabilityResult, provideAlternative, alternate, checkedProductStock, requestedQuantity, state, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CheckProductStockItem {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    availabilityResult: ").append(toIndentedString(availabilityResult)).append("\n");
    sb.append("    provideAlternative: ").append(toIndentedString(provideAlternative)).append("\n");
    sb.append("    alternate: ").append(toIndentedString(alternate)).append("\n");
    sb.append("    checkedProductStock: ").append(toIndentedString(checkedProductStock)).append("\n");
    sb.append("    requestedQuantity: ").append(toIndentedString(requestedQuantity)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

