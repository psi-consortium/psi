package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.InquiredCharacteristicValueSpecification;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A characteristic quality or distinctive feature of an InquiredProduct. The characteristic can take a discrete value, such as color, can take a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) &#x3D; 30 - talk time *3).
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "InquiredProductCharacteristic", description = "A characteristic quality or distinctive feature of an InquiredProduct. The characteristic can take a discrete value, such as color, can take a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3).")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InquiredProductCharacteristic {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("priority")
  private BigDecimal priority;

  @JsonProperty("valueType")
  private String valueType;

  @JsonProperty("inquiredProductCharacteristicValue")
  @Valid
  private List<InquiredCharacteristicValueSpecification> inquiredProductCharacteristicValue = new ArrayList<>();

  public InquiredProductCharacteristic id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique ID for the characteristic
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Unique ID for the characteristic", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public InquiredProductCharacteristic name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A word, term, or phrase by which this characteristic specification is known and distinguished from other characteristic specifications.
   * @return name
  */
  
  @Schema(name = "name", description = "A word, term, or phrase by which this characteristic specification is known and distinguished from other characteristic specifications.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public InquiredProductCharacteristic priority(BigDecimal priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Priority of this characteristic compared to others in ascending order (the most important one is 1). Multiple characteristics can be equally important. If the attribute is not set, the characteristic has lowest priority.
   * @return priority
  */
  @Valid 
  @Schema(name = "priority", description = "Priority of this characteristic compared to others in ascending order (the most important one is 1). Multiple characteristics can be equally important. If the attribute is not set, the characteristic has lowest priority.", required = false)
  public BigDecimal getPriority() {
    return priority;
  }

  public void setPriority(BigDecimal priority) {
    this.priority = priority;
  }

  public InquiredProductCharacteristic valueType(String valueType) {
    this.valueType = valueType;
    return this;
  }

  /**
   * A kind of value that the characteristic can take on, such as numeric, text and so forth
   * @return valueType
  */
  
  @Schema(name = "valueType", description = "A kind of value that the characteristic can take on, such as numeric, text and so forth", required = false)
  public String getValueType() {
    return valueType;
  }

  public void setValueType(String valueType) {
    this.valueType = valueType;
  }

  public InquiredProductCharacteristic inquiredProductCharacteristicValue(List<InquiredCharacteristicValueSpecification> inquiredProductCharacteristicValue) {
    this.inquiredProductCharacteristicValue = inquiredProductCharacteristicValue;
    return this;
  }

  public InquiredProductCharacteristic addInquiredProductCharacteristicValueItem(InquiredCharacteristicValueSpecification inquiredProductCharacteristicValueItem) {
    if (this.inquiredProductCharacteristicValue == null) {
      this.inquiredProductCharacteristicValue = new ArrayList<>();
    }
    this.inquiredProductCharacteristicValue.add(inquiredProductCharacteristicValueItem);
    return this;
  }

  /**
   * The values of the attributes in the InquiredProductCharacteristicValue object describe the values of the attributes that the customer is requesting for the corresponding InquiredProduct.
   * @return inquiredProductCharacteristicValue
  */
  @NotNull @Valid 
  @Schema(name = "inquiredProductCharacteristicValue", description = "The values of the attributes in the InquiredProductCharacteristicValue object describe the values of the attributes that the customer is requesting for the corresponding InquiredProduct.", required = true)
  public List<InquiredCharacteristicValueSpecification> getInquiredProductCharacteristicValue() {
    return inquiredProductCharacteristicValue;
  }

  public void setInquiredProductCharacteristicValue(List<InquiredCharacteristicValueSpecification> inquiredProductCharacteristicValue) {
    this.inquiredProductCharacteristicValue = inquiredProductCharacteristicValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InquiredProductCharacteristic inquiredProductCharacteristic = (InquiredProductCharacteristic) o;
    return Objects.equals(this.id, inquiredProductCharacteristic.id) &&
        Objects.equals(this.name, inquiredProductCharacteristic.name) &&
        Objects.equals(this.priority, inquiredProductCharacteristic.priority) &&
        Objects.equals(this.valueType, inquiredProductCharacteristic.valueType) &&
        Objects.equals(this.inquiredProductCharacteristicValue, inquiredProductCharacteristic.inquiredProductCharacteristicValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, priority, valueType, inquiredProductCharacteristicValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InquiredProductCharacteristic {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    valueType: ").append(toIndentedString(valueType)).append("\n");
    sb.append("    inquiredProductCharacteristicValue: ").append(toIndentedString(inquiredProductCharacteristicValue)).append("\n");
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

