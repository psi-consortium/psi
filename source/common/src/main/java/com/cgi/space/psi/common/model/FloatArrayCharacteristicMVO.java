package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.BooleanArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.BooleanCharacteristicMVO;
import com.cgi.space.psi.common.model.CharacteristicMVO;
import com.cgi.space.psi.common.model.CharacteristicRelationshipMVO;
import com.cgi.space.psi.common.model.FloatArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.FloatCharacteristicMVO;
import com.cgi.space.psi.common.model.IntegerArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.IntegerCharacteristicMVO;
import com.cgi.space.psi.common.model.NumberArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.NumberCharacteristicMVO;
import com.cgi.space.psi.common.model.ObjectArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.ObjectCharacteristicMVO;
import com.cgi.space.psi.common.model.StringArrayCharacteristicMVO;
import com.cgi.space.psi.common.model.StringCharacteristicMVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * FloatArrayCharacteristicMVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = BooleanArrayCharacteristicMVO.class, name = "BooleanArrayCharacteristic"),
  @JsonSubTypes.Type(value = BooleanCharacteristicMVO.class, name = "BooleanCharacteristic"),
  @JsonSubTypes.Type(value = CharacteristicMVO.class, name = "Characteristic"),
  @JsonSubTypes.Type(value = FloatArrayCharacteristicMVO.class, name = "FloatArrayCharacteristic"),
  @JsonSubTypes.Type(value = FloatCharacteristicMVO.class, name = "FloatCharacteristic"),
  @JsonSubTypes.Type(value = IntegerArrayCharacteristicMVO.class, name = "IntegerArrayCharacteristic"),
  @JsonSubTypes.Type(value = IntegerCharacteristicMVO.class, name = "IntegerCharacteristic"),
  @JsonSubTypes.Type(value = NumberArrayCharacteristicMVO.class, name = "NumberArrayCharacteristic"),
  @JsonSubTypes.Type(value = NumberCharacteristicMVO.class, name = "NumberCharacteristic"),
  @JsonSubTypes.Type(value = ObjectArrayCharacteristicMVO.class, name = "ObjectArrayCharacteristic"),
  @JsonSubTypes.Type(value = ObjectCharacteristicMVO.class, name = "ObjectCharacteristic"),
  @JsonSubTypes.Type(value = StringArrayCharacteristicMVO.class, name = "StringArrayCharacteristic"),
  @JsonSubTypes.Type(value = StringCharacteristicMVO.class, name = "StringCharacteristic")
})

@JsonTypeName("FloatArrayCharacteristic_MVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class FloatArrayCharacteristicMVO extends CharacteristicMVO {

  @JsonProperty("value")
  @Valid
  private List<Float> value = new ArrayList<>();

  public FloatArrayCharacteristicMVO value(List<Float> value) {
    this.value = value;
    return this;
  }

  public FloatArrayCharacteristicMVO addValueItem(Float valueItem) {
    if (this.value == null) {
      this.value = new ArrayList<>();
    }
    this.value.add(valueItem);
    return this;
  }

  /**
   * Get value
   * @return value
  */
  @NotNull 
  @Schema(name = "value", required = true)
  public List<Float> getValue() {
    return value;
  }

  public void setValue(List<Float> value) {
    this.value = value;
  }

  public FloatArrayCharacteristicMVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public FloatArrayCharacteristicMVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public FloatArrayCharacteristicMVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public FloatArrayCharacteristicMVO id(String id) {
    super.setId(id);
    return this;
  }

  public FloatArrayCharacteristicMVO name(String name) {
    super.setName(name);
    return this;
  }

  public FloatArrayCharacteristicMVO valueType(String valueType) {
    super.setValueType(valueType);
    return this;
  }

  public FloatArrayCharacteristicMVO characteristicRelationship(List<CharacteristicRelationshipMVO> characteristicRelationship) {
    super.setCharacteristicRelationship(characteristicRelationship);
    return this;
  }

  public FloatArrayCharacteristicMVO addCharacteristicRelationshipItem(CharacteristicRelationshipMVO characteristicRelationshipItem) {
    super.addCharacteristicRelationshipItem(characteristicRelationshipItem);
    return this;
  }

  public FloatArrayCharacteristicMVO unitOfMeasure(String unitOfMeasure) {
    super.setUnitOfMeasure(unitOfMeasure);
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
    FloatArrayCharacteristicMVO floatArrayCharacteristicMVO = (FloatArrayCharacteristicMVO) o;
    return Objects.equals(this.value, floatArrayCharacteristicMVO.value) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FloatArrayCharacteristicMVO {\n");
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

