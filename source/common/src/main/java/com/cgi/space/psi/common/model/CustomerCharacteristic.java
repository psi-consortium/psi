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
 * A characteristic describing the customer. Used to scope results of a CustomerInquiry.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "CustomerCharacteristic", description = "A characteristic describing the customer. Used to scope results of a CustomerInquiry.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CustomerCharacteristic {

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("valueType")
  private String valueType;

  @JsonProperty("value")
  private String value;

  public CustomerCharacteristic name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A word, term, or phrase by which this characteristic specification is known and distinguished from other characteristic specifications.
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "country | securityGrade | ...", description = "A word, term, or phrase by which this characteristic specification is known and distinguished from other characteristic specifications.", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CustomerCharacteristic description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A narrative that explains the CustomerCharacteristic.
   * @return description
  */
  
  @Schema(name = "description", description = "A narrative that explains the CustomerCharacteristic.", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CustomerCharacteristic valueType(String valueType) {
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

  public CustomerCharacteristic value(String value) {
    this.value = value;
    return this;
  }

  /**
   * The actual value of the characteristic
   * @return value
  */
  
  @Schema(name = "value", example = "DE, ES, FR, ... | UNCLASSIFIED, RESTRICTED, CONFIDENTIAL, SECRET, TOP_SECRET", description = "The actual value of the characteristic", required = false)
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerCharacteristic customerCharacteristic = (CustomerCharacteristic) o;
    return Objects.equals(this.name, customerCharacteristic.name) &&
        Objects.equals(this.description, customerCharacteristic.description) &&
        Objects.equals(this.valueType, customerCharacteristic.valueType) &&
        Objects.equals(this.value, customerCharacteristic.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, valueType, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerCharacteristic {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    valueType: ").append(toIndentedString(valueType)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

