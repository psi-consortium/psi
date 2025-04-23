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
 * Defines for a BundledProductOfferingGroup (i.e. a group of multiple child offerings of a parent product offering), how many instances from the child offerings can be chosen in total. For example facilitate the choice of between 2 and 7 channel packs from a list, and cause certain items to be selected by default
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "BundledGroupProductOfferingOption", description = "Defines for a BundledProductOfferingGroup (i.e. a group of multiple child offerings of a parent product offering), how many instances from the child offerings can be chosen in total. For example facilitate the choice of between 2 and 7 channel packs from a list, and cause certain items to be selected by default")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class BundledGroupProductOfferingOption {

  @JsonProperty("numberRelOfferLowerLimit")
  private Integer numberRelOfferLowerLimit;

  @JsonProperty("numberRelOfferUpperLimit")
  private Integer numberRelOfferUpperLimit;

  public BundledGroupProductOfferingOption numberRelOfferLowerLimit(Integer numberRelOfferLowerLimit) {
    this.numberRelOfferLowerLimit = numberRelOfferLowerLimit;
    return this;
  }

  /**
   * The minimum total number of instances of the child offerings directly of hierarchically in the group that should be instantiated
   * @return numberRelOfferLowerLimit
  */
  
  @Schema(name = "numberRelOfferLowerLimit", example = "2", description = "The minimum total number of instances of the child offerings directly of hierarchically in the group that should be instantiated", required = false)
  public Integer getNumberRelOfferLowerLimit() {
    return numberRelOfferLowerLimit;
  }

  public void setNumberRelOfferLowerLimit(Integer numberRelOfferLowerLimit) {
    this.numberRelOfferLowerLimit = numberRelOfferLowerLimit;
  }

  public BundledGroupProductOfferingOption numberRelOfferUpperLimit(Integer numberRelOfferUpperLimit) {
    this.numberRelOfferUpperLimit = numberRelOfferUpperLimit;
    return this;
  }

  /**
   * The maximum total number of instances of the child offerings directly of hierarchically in the group that should be instantiated
   * @return numberRelOfferUpperLimit
  */
  
  @Schema(name = "numberRelOfferUpperLimit", example = "7", description = "The maximum total number of instances of the child offerings directly of hierarchically in the group that should be instantiated", required = false)
  public Integer getNumberRelOfferUpperLimit() {
    return numberRelOfferUpperLimit;
  }

  public void setNumberRelOfferUpperLimit(Integer numberRelOfferUpperLimit) {
    this.numberRelOfferUpperLimit = numberRelOfferUpperLimit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BundledGroupProductOfferingOption bundledGroupProductOfferingOption = (BundledGroupProductOfferingOption) o;
    return Objects.equals(this.numberRelOfferLowerLimit, bundledGroupProductOfferingOption.numberRelOfferLowerLimit) &&
        Objects.equals(this.numberRelOfferUpperLimit, bundledGroupProductOfferingOption.numberRelOfferUpperLimit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberRelOfferLowerLimit, numberRelOfferUpperLimit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BundledGroupProductOfferingOption {\n");
    sb.append("    numberRelOfferLowerLimit: ").append(toIndentedString(numberRelOfferLowerLimit)).append("\n");
    sb.append("    numberRelOfferUpperLimit: ").append(toIndentedString(numberRelOfferUpperLimit)).append("\n");
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

