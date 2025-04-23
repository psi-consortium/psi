package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.HashMap;
import java.util.Map;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * MapArrayCharacteristicValueSpecificationAllOfValue
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonTypeName("MapArrayCharacteristicValueSpecification_allOf_value")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MapArrayCharacteristicValueSpecificationAllOfValue {

  @JsonProperty("value")
  @Valid
  private Map<String, Object> value = null;

  public MapArrayCharacteristicValueSpecificationAllOfValue value(Map<String, Object> value) {
    this.value = value;
    return this;
  }

  public MapArrayCharacteristicValueSpecificationAllOfValue putValueItem(String key, Object valueItem) {
    if (this.value == null) {
      this.value = new HashMap<>();
    }
    this.value.put(key, valueItem);
    return this;
  }

  /**
   * Get value
   * @return value
  */
  
  @Schema(name = "value", required = false)
  public Map<String, Object> getValue() {
    return value;
  }

  public void setValue(Map<String, Object> value) {
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
    MapArrayCharacteristicValueSpecificationAllOfValue mapArrayCharacteristicValueSpecificationAllOfValue = (MapArrayCharacteristicValueSpecificationAllOfValue) o;
    return Objects.equals(this.value, mapArrayCharacteristicValueSpecificationAllOfValue.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MapArrayCharacteristicValueSpecificationAllOfValue {\n");
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

