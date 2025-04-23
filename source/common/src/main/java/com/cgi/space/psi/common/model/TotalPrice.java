package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * An amount, usually of money, that represents the actual price to be paid by a Customer for current group of inquiry results.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "TotalPrice", description = "An amount, usually of money, that represents the actual price to be paid by a Customer for current group of inquiry results.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TotalPrice {

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("priceType")
  private String priceType;

  @JsonProperty("recurringChargePeriod")
  private String recurringChargePeriod;

  @JsonProperty("unitOfMeasure")
  private String unitOfMeasure;

  @JsonProperty("price")
  private Price price;

  public TotalPrice name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A short descriptive name.
   * @return name
  */
  
  @Schema(name = "name", description = "A short descriptive name.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TotalPrice description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A narrative that explains in detail the semantics of this total price.
   * @return description
  */
  
  @Schema(name = "description", description = "A narrative that explains in detail the semantics of this total price.", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public TotalPrice priceType(String priceType) {
    this.priceType = priceType;
    return this;
  }

  /**
   * A category that describes the price, such as recurring, discount, allowance, penalty, and so forth.
   * @return priceType
  */
  @NotNull 
  @Schema(name = "priceType", description = "A category that describes the price, such as recurring, discount, allowance, penalty, and so forth.", required = true)
  public String getPriceType() {
    return priceType;
  }

  public void setPriceType(String priceType) {
    this.priceType = priceType;
  }

  public TotalPrice recurringChargePeriod(String recurringChargePeriod) {
    this.recurringChargePeriod = recurringChargePeriod;
    return this;
  }

  /**
   * Could be month, week...
   * @return recurringChargePeriod
  */
  
  @Schema(name = "recurringChargePeriod", description = "Could be month, week...", required = false)
  public String getRecurringChargePeriod() {
    return recurringChargePeriod;
  }

  public void setRecurringChargePeriod(String recurringChargePeriod) {
    this.recurringChargePeriod = recurringChargePeriod;
  }

  public TotalPrice unitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
    return this;
  }

  /**
   * Could be minutes, GB...
   * @return unitOfMeasure
  */
  
  @Schema(name = "unitOfMeasure", description = "Could be minutes, GB...", required = false)
  public String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  public void setUnitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  public TotalPrice price(Price price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  */
  @NotNull @Valid 
  @Schema(name = "price", required = true)
  public Price getPrice() {
    return price;
  }

  public void setPrice(Price price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TotalPrice totalPrice = (TotalPrice) o;
    return Objects.equals(this.name, totalPrice.name) &&
        Objects.equals(this.description, totalPrice.description) &&
        Objects.equals(this.priceType, totalPrice.priceType) &&
        Objects.equals(this.recurringChargePeriod, totalPrice.recurringChargePeriod) &&
        Objects.equals(this.unitOfMeasure, totalPrice.unitOfMeasure) &&
        Objects.equals(this.price, totalPrice.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, priceType, recurringChargePeriod, unitOfMeasure, price);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TotalPrice {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    priceType: ").append(toIndentedString(priceType)).append("\n");
    sb.append("    recurringChargePeriod: ").append(toIndentedString(recurringChargePeriod)).append("\n");
    sb.append("    unitOfMeasure: ").append(toIndentedString(unitOfMeasure)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

