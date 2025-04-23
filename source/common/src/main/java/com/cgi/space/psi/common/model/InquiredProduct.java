package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.InquiredProductCharacteristic;
import com.cgi.space.psi.common.model.RelatedPlaceRefOrValue;
import com.cgi.space.psi.common.model.TargetProductSchema;
import com.cgi.space.psi.common.model.TimePeriod;
import com.cgi.space.psi.common.model.UsageEstimation;
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
 * A product inquired by the customer.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "InquiredProduct", description = "A product inquired by the customer.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InquiredProduct {

  @JsonProperty("id")
  private String id;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("name")
  private String name;

  @JsonProperty("targetProductSchema")
  private TargetProductSchema targetProductSchema;

  @JsonProperty("servicePeriod")
  private TimePeriod servicePeriod;

  @JsonProperty("place")
  @Valid
  private List<RelatedPlaceRefOrValue> place = null;

  @JsonProperty("inquiredProductCharacteristic")
  @Valid
  private List<InquiredProductCharacteristic> inquiredProductCharacteristic = null;

  @JsonProperty("estimatedUsage")
  @Valid
  private List<UsageEstimation> estimatedUsage = null;

  public InquiredProduct id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Optional: Unique identifier of a known product specification this inquiry is based on.
   * @return id
  */
  
  @Schema(name = "id", description = "Optional: Unique identifier of a known product specification this inquiry is based on.", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public InquiredProduct href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * Optional: Reference of the product specification this inquiry is based on.
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "Optional: Reference of the product specification this inquiry is based on.", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public InquiredProduct name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Optional: Name of the product specification this inquiry is based on.
   * @return name
  */
  
  @Schema(name = "name", description = "Optional: Name of the product specification this inquiry is based on.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public InquiredProduct targetProductSchema(TargetProductSchema targetProductSchema) {
    this.targetProductSchema = targetProductSchema;
    return this;
  }

  /**
   * Get targetProductSchema
   * @return targetProductSchema
  */
  @Valid 
  @Schema(name = "targetProductSchema", required = false)
  public TargetProductSchema getTargetProductSchema() {
    return targetProductSchema;
  }

  public void setTargetProductSchema(TargetProductSchema targetProductSchema) {
    this.targetProductSchema = targetProductSchema;
  }

  public InquiredProduct servicePeriod(TimePeriod servicePeriod) {
    this.servicePeriod = servicePeriod;
    return this;
  }

  /**
   * Get servicePeriod
   * @return servicePeriod
  */
  @Valid 
  @Schema(name = "servicePeriod", required = false)
  public TimePeriod getServicePeriod() {
    return servicePeriod;
  }

  public void setServicePeriod(TimePeriod servicePeriod) {
    this.servicePeriod = servicePeriod;
  }

  public InquiredProduct place(List<RelatedPlaceRefOrValue> place) {
    this.place = place;
    return this;
  }

  public InquiredProduct addPlaceItem(RelatedPlaceRefOrValue placeItem) {
    if (this.place == null) {
      this.place = new ArrayList<>();
    }
    this.place.add(placeItem);
    return this;
  }

  /**
   * Get place
   * @return place
  */
  @Valid 
  @Schema(name = "place", required = false)
  public List<RelatedPlaceRefOrValue> getPlace() {
    return place;
  }

  public void setPlace(List<RelatedPlaceRefOrValue> place) {
    this.place = place;
  }

  public InquiredProduct inquiredProductCharacteristic(List<InquiredProductCharacteristic> inquiredProductCharacteristic) {
    this.inquiredProductCharacteristic = inquiredProductCharacteristic;
    return this;
  }

  public InquiredProduct addInquiredProductCharacteristicItem(InquiredProductCharacteristic inquiredProductCharacteristicItem) {
    if (this.inquiredProductCharacteristic == null) {
      this.inquiredProductCharacteristic = new ArrayList<>();
    }
    this.inquiredProductCharacteristic.add(inquiredProductCharacteristicItem);
    return this;
  }

  /**
   * A characteristic quality or distinctive feature of an InquiredProduct. The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3).
   * @return inquiredProductCharacteristic
  */
  @Valid 
  @Schema(name = "inquiredProductCharacteristic", description = "A characteristic quality or distinctive feature of an InquiredProduct. The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3).", required = false)
  public List<InquiredProductCharacteristic> getInquiredProductCharacteristic() {
    return inquiredProductCharacteristic;
  }

  public void setInquiredProductCharacteristic(List<InquiredProductCharacteristic> inquiredProductCharacteristic) {
    this.inquiredProductCharacteristic = inquiredProductCharacteristic;
  }

  public InquiredProduct estimatedUsage(List<UsageEstimation> estimatedUsage) {
    this.estimatedUsage = estimatedUsage;
    return this;
  }

  public InquiredProduct addEstimatedUsageItem(UsageEstimation estimatedUsageItem) {
    if (this.estimatedUsage == null) {
      this.estimatedUsage = new ArrayList<>();
    }
    this.estimatedUsage.add(estimatedUsageItem);
    return this;
  }

  /**
   * The estimated usage of a product. The customer may provide this to allow the provider to optimize the returned products.
   * @return estimatedUsage
  */
  @Valid 
  @Schema(name = "estimatedUsage", description = "The estimated usage of a product. The customer may provide this to allow the provider to optimize the returned products.", required = false)
  public List<UsageEstimation> getEstimatedUsage() {
    return estimatedUsage;
  }

  public void setEstimatedUsage(List<UsageEstimation> estimatedUsage) {
    this.estimatedUsage = estimatedUsage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InquiredProduct inquiredProduct = (InquiredProduct) o;
    return Objects.equals(this.id, inquiredProduct.id) &&
        Objects.equals(this.href, inquiredProduct.href) &&
        Objects.equals(this.name, inquiredProduct.name) &&
        Objects.equals(this.targetProductSchema, inquiredProduct.targetProductSchema) &&
        Objects.equals(this.servicePeriod, inquiredProduct.servicePeriod) &&
        Objects.equals(this.place, inquiredProduct.place) &&
        Objects.equals(this.inquiredProductCharacteristic, inquiredProduct.inquiredProductCharacteristic) &&
        Objects.equals(this.estimatedUsage, inquiredProduct.estimatedUsage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, name, targetProductSchema, servicePeriod, place, inquiredProductCharacteristic, estimatedUsage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InquiredProduct {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    targetProductSchema: ").append(toIndentedString(targetProductSchema)).append("\n");
    sb.append("    servicePeriod: ").append(toIndentedString(servicePeriod)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    inquiredProductCharacteristic: ").append(toIndentedString(inquiredProductCharacteristic)).append("\n");
    sb.append("    estimatedUsage: ").append(toIndentedString(estimatedUsage)).append("\n");
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

