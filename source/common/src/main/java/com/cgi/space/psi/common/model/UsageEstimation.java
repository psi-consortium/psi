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
 * The estimated usage of a product. The customer may provide this to allow the provider to optimize the returned products.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "UsageEstimation", description = "The estimated usage of a product. The customer may provide this to allow the provider to optimize the returned products.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class UsageEstimation {

  @JsonProperty("name")
  private String name;

  @JsonProperty("value")
  private Integer value;

  @JsonProperty("unitOfMeasure")
  private String unitOfMeasure;

  @JsonProperty("recurringChargePeriod")
  private String recurringChargePeriod;

  @JsonProperty("recurringChargePeriodLength")
  private Integer recurringChargePeriodLength = 1;

  public UsageEstimation name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A word, term, or phrase by which this usage type is known and distinguished from other usage types.
   * @return name
  */
  
  @Schema(name = "name", example = "bandwidth", description = "A word, term, or phrase by which this usage type is known and distinguished from other usage types.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UsageEstimation value(Integer value) {
    this.value = value;
    return this;
  }

  /**
   * The value of the estimated usage.
   * @return value
  */
  
  @Schema(name = "value", example = "500", description = "The value of the estimated usage.", required = false)
  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public UsageEstimation unitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
    return this;
  }

  /**
   * A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. In general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.
   * @return unitOfMeasure
  */
  
  @Schema(name = "unitOfMeasure", example = "MB", description = "A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. In general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.", required = false)
  public String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  public void setUnitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  public UsageEstimation recurringChargePeriod(String recurringChargePeriod) {
    this.recurringChargePeriod = recurringChargePeriod;
    return this;
  }

  /**
   * Could be month, week...
   * @return recurringChargePeriod
  */
  
  @Schema(name = "recurringChargePeriod", example = "month", description = "Could be month, week...", required = false)
  public String getRecurringChargePeriod() {
    return recurringChargePeriod;
  }

  public void setRecurringChargePeriod(String recurringChargePeriod) {
    this.recurringChargePeriod = recurringChargePeriod;
  }

  public UsageEstimation recurringChargePeriodLength(Integer recurringChargePeriodLength) {
    this.recurringChargePeriodLength = recurringChargePeriodLength;
    return this;
  }

  /**
   * How many months, weeks...
   * @return recurringChargePeriodLength
  */
  
  @Schema(name = "recurringChargePeriodLength", description = "How many months, weeks...", required = false)
  public Integer getRecurringChargePeriodLength() {
    return recurringChargePeriodLength;
  }

  public void setRecurringChargePeriodLength(Integer recurringChargePeriodLength) {
    this.recurringChargePeriodLength = recurringChargePeriodLength;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsageEstimation usageEstimation = (UsageEstimation) o;
    return Objects.equals(this.name, usageEstimation.name) &&
        Objects.equals(this.value, usageEstimation.value) &&
        Objects.equals(this.unitOfMeasure, usageEstimation.unitOfMeasure) &&
        Objects.equals(this.recurringChargePeriod, usageEstimation.recurringChargePeriod) &&
        Objects.equals(this.recurringChargePeriodLength, usageEstimation.recurringChargePeriodLength);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value, unitOfMeasure, recurringChargePeriod, recurringChargePeriodLength);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsageEstimation {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    unitOfMeasure: ").append(toIndentedString(unitOfMeasure)).append("\n");
    sb.append("    recurringChargePeriod: ").append(toIndentedString(recurringChargePeriod)).append("\n");
    sb.append("    recurringChargePeriodLength: ").append(toIndentedString(recurringChargePeriodLength)).append("\n");
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

