package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.CharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.IntegerArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.IntegerCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.MapArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.MapCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.NumberArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.NumberCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.ObjectArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.ObjectCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.StringArrayCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.StringCharacteristicValueSpecificationFVO;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
 * MapCharacteristicValueSpecificationFVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = CharacteristicValueSpecificationFVO.class, name = "CharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = IntegerArrayCharacteristicValueSpecificationFVO.class, name = "IntegerArrayCharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = IntegerCharacteristicValueSpecificationFVO.class, name = "IntegerCharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = MapArrayCharacteristicValueSpecificationFVO.class, name = "MapArrayCharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = MapCharacteristicValueSpecificationFVO.class, name = "MapCharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = NumberArrayCharacteristicValueSpecificationFVO.class, name = "NumberArrayCharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = NumberCharacteristicValueSpecificationFVO.class, name = "NumberCharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = ObjectArrayCharacteristicValueSpecificationFVO.class, name = "ObjectArrayCharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = ObjectCharacteristicValueSpecificationFVO.class, name = "ObjectCharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = StringArrayCharacteristicValueSpecificationFVO.class, name = "StringArrayCharacteristicValueSpecification"),
  @JsonSubTypes.Type(value = StringCharacteristicValueSpecificationFVO.class, name = "StringCharacteristicValueSpecification")
})

@JsonTypeName("MapCharacteristicValueSpecification_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MapCharacteristicValueSpecificationFVO extends CharacteristicValueSpecificationFVO {

  @JsonProperty("value")
  @Valid
  private Map<String, Object> value = null;

  public MapCharacteristicValueSpecificationFVO value(Map<String, Object> value) {
    this.value = value;
    return this;
  }

  public MapCharacteristicValueSpecificationFVO putValueItem(String key, Object valueItem) {
    if (this.value == null) {
      this.value = new HashMap<>();
    }
    this.value.put(key, valueItem);
    return this;
  }

  /**
   * Value of the characteristic
   * @return value
  */
  
  @Schema(name = "value", description = "Value of the characteristic", required = false)
  public Map<String, Object> getValue() {
    return value;
  }

  public void setValue(Map<String, Object> value) {
    this.value = value;
  }

  public MapCharacteristicValueSpecificationFVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO valueType(String valueType) {
    super.setValueType(valueType);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO isDefault(Boolean isDefault) {
    super.setIsDefault(isDefault);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO unitOfMeasure(String unitOfMeasure) {
    super.setUnitOfMeasure(unitOfMeasure);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO validFor(TimePeriod validFor) {
    super.setValidFor(validFor);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO valueFrom(Integer valueFrom) {
    super.setValueFrom(valueFrom);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO valueTo(Integer valueTo) {
    super.setValueTo(valueTo);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO rangeInterval(String rangeInterval) {
    super.setRangeInterval(rangeInterval);
    return this;
  }

  public MapCharacteristicValueSpecificationFVO regex(String regex) {
    super.setRegex(regex);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MapCharacteristicValueSpecificationFVO mapCharacteristicValueSpecificationFVO = (MapCharacteristicValueSpecificationFVO) o;
    return Objects.equals(this.value, mapCharacteristicValueSpecificationFVO.value) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MapCharacteristicValueSpecificationFVO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

