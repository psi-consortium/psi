package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.CharacteristicValuePrecedence;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * specification of a value (number or text or an object) that can be assigned to a Characteristic.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "InquiredCharacteristicValueSpecification", description = "specification of a value (number or text or an object) that can be assigned to a Characteristic.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InquiredCharacteristicValueSpecification {

  @JsonProperty("precedence")
  private CharacteristicValuePrecedence precedence;

  @JsonProperty("unitOfMeasure")
  private String unitOfMeasure;

  @JsonProperty("valueType")
  private String valueType;

  @JsonProperty("value")
  private Object value = null;

  public InquiredCharacteristicValueSpecification precedence(CharacteristicValuePrecedence precedence) {
    this.precedence = precedence;
    return this;
  }

  /**
   * Get precedence
   * @return precedence
  */
  @Valid 
  @Schema(name = "precedence", required = false)
  public CharacteristicValuePrecedence getPrecedence() {
    return precedence;
  }

  public void setPrecedence(CharacteristicValuePrecedence precedence) {
    this.precedence = precedence;
  }

  public InquiredCharacteristicValueSpecification unitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
    return this;
  }

  /**
   * A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. In general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.
   * @return unitOfMeasure
  */
  
  @Schema(name = "unitOfMeasure", description = "A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. In general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.", required = false)
  public String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  public void setUnitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  public InquiredCharacteristicValueSpecification valueType(String valueType) {
    this.valueType = valueType;
    return this;
  }

  /**
   * A kind of value that the characteristic value can take on, such as numeric, text and so forth
   * @return valueType
  */
  
  @Schema(name = "valueType", description = "A kind of value that the characteristic value can take on, such as numeric, text and so forth", required = false)
  public String getValueType() {
    return valueType;
  }

  public void setValueType(String valueType) {
    this.valueType = valueType;
  }

  public InquiredCharacteristicValueSpecification value(Object value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  */
  
  @Schema(name = "value", required = false)
  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
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
    InquiredCharacteristicValueSpecification inquiredCharacteristicValueSpecification = (InquiredCharacteristicValueSpecification) o;
    return Objects.equals(this.precedence, inquiredCharacteristicValueSpecification.precedence) &&
        Objects.equals(this.unitOfMeasure, inquiredCharacteristicValueSpecification.unitOfMeasure) &&
        Objects.equals(this.valueType, inquiredCharacteristicValueSpecification.valueType) &&
        Objects.equals(this.value, inquiredCharacteristicValueSpecification.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(precedence, unitOfMeasure, valueType, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InquiredCharacteristicValueSpecification {\n");
    sb.append("    precedence: ").append(toIndentedString(precedence)).append("\n");
    sb.append("    unitOfMeasure: ").append(toIndentedString(unitOfMeasure)).append("\n");
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

