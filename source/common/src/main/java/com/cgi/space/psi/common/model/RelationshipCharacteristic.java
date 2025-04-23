package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.CharacteristicValueSpecification;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A characteristic property or distinguishing feature of this relationship.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "RelationshipCharacteristic", description = "A characteristic property or distinguishing feature of this relationship.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RelationshipCharacteristic {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("deviation")
  private String deviation;

  @JsonProperty("valueType")
  private String valueType;

  @JsonProperty("relationshipCharacteristicValue")
  @Valid
  private List<CharacteristicValueSpecification> relationshipCharacteristicValue = null;

  public RelationshipCharacteristic id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique ID for the characteristic.
   * @return id
  */
  
  @Schema(name = "id", description = "Unique ID for the characteristic.", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RelationshipCharacteristic name(String name) {
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

  public RelationshipCharacteristic deviation(String deviation) {
    this.deviation = deviation;
    return this;
  }

  /**
   * Explains the deviation from the originally inquired characteristic, like 'below', 'alternative', etc.
   * @return deviation
  */
  
  @Schema(name = "deviation", description = "Explains the deviation from the originally inquired characteristic, like 'below', 'alternative', etc.", required = false)
  public String getDeviation() {
    return deviation;
  }

  public void setDeviation(String deviation) {
    this.deviation = deviation;
  }

  public RelationshipCharacteristic valueType(String valueType) {
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

  public RelationshipCharacteristic relationshipCharacteristicValue(List<CharacteristicValueSpecification> relationshipCharacteristicValue) {
    this.relationshipCharacteristicValue = relationshipCharacteristicValue;
    return this;
  }

  public RelationshipCharacteristic addRelationshipCharacteristicValueItem(CharacteristicValueSpecification relationshipCharacteristicValueItem) {
    if (this.relationshipCharacteristicValue == null) {
      this.relationshipCharacteristicValue = new ArrayList<>();
    }
    this.relationshipCharacteristicValue.add(relationshipCharacteristicValueItem);
    return this;
  }

  /**
   * A RelationshipCharacteristicValue object is used to define a set of attributes.
   * @return relationshipCharacteristicValue
  */
  @Valid 
  @Schema(name = "relationshipCharacteristicValue", description = "A RelationshipCharacteristicValue object is used to define a set of attributes.", required = false)
  public List<CharacteristicValueSpecification> getRelationshipCharacteristicValue() {
    return relationshipCharacteristicValue;
  }

  public void setRelationshipCharacteristicValue(List<CharacteristicValueSpecification> relationshipCharacteristicValue) {
    this.relationshipCharacteristicValue = relationshipCharacteristicValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelationshipCharacteristic relationshipCharacteristic = (RelationshipCharacteristic) o;
    return Objects.equals(this.id, relationshipCharacteristic.id) &&
        Objects.equals(this.name, relationshipCharacteristic.name) &&
        Objects.equals(this.deviation, relationshipCharacteristic.deviation) &&
        Objects.equals(this.valueType, relationshipCharacteristic.valueType) &&
        Objects.equals(this.relationshipCharacteristicValue, relationshipCharacteristic.relationshipCharacteristicValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, deviation, valueType, relationshipCharacteristicValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelationshipCharacteristic {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    deviation: ").append(toIndentedString(deviation)).append("\n");
    sb.append("    valueType: ").append(toIndentedString(valueType)).append("\n");
    sb.append("    relationshipCharacteristicValue: ").append(toIndentedString(relationshipCharacteristicValue)).append("\n");
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

