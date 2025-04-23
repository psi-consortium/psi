package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.KeyIndicatorRelationship;
import com.cgi.space.psi.common.model.RelatedEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * KeyIndicator are defined in terms of parameters and metrics, thresholds, and tolerances  associated with the parameters.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "KeyIndicator_Update", description = "KeyIndicator are defined in terms of parameters and metrics, thresholds, and tolerances  associated with the parameters.")
@JsonTypeName("KeyIndicator_Update")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class KeyIndicatorUpdate {

  @JsonProperty("name")
  private String name;

  @JsonProperty("category")
  private String category;

  @JsonProperty("isBundled")
  private Boolean isBundled;

  @JsonProperty("transformationAlgorithmOfKQI")
  private String transformationAlgorithmOfKQI;

  /**
   * Type of Key Indicator are Key Quality Indicator (KQI) or Key Performance Indicator (KPI)
   */
  public enum IndicatorTypeEnum {
    KQI("KQI"),
    
    KPI("KPI");

    private String value;

    IndicatorTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static IndicatorTypeEnum fromValue(String value) {
      for (IndicatorTypeEnum b : IndicatorTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("indicatorType")
  private IndicatorTypeEnum indicatorType;

  @JsonProperty("keyIndicatorRelationship")
  @Valid
  private List<KeyIndicatorRelationship> keyIndicatorRelationship = null;

  @JsonProperty("relatedEntity")
  @Valid
  private List<RelatedEntity> relatedEntity = null;

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  public KeyIndicatorUpdate name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the key indicator.
   * @return name
  */
  
  @Schema(name = "name", description = "The name of the key indicator.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public KeyIndicatorUpdate category(String category) {
    this.category = category;
    return this;
  }

  /**
   * A string that specifies whether the key indicator is technology specific, service specific, or technology/service independent
   * @return category
  */
  
  @Schema(name = "category", description = "A string that specifies whether the key indicator is technology specific, service specific, or technology/service independent", required = false)
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public KeyIndicatorUpdate isBundled(Boolean isBundled) {
    this.isBundled = isBundled;
    return this;
  }

  /**
   * A boolean that specifies whether the key indicator represents a single key indicator (false) or a key indicator that represents an aggregation (true)
   * @return isBundled
  */
  
  @Schema(name = "isBundled", description = "A boolean that specifies whether the key indicator represents a single key indicator (false) or a key indicator that represents an aggregation (true)", required = false)
  public Boolean getIsBundled() {
    return isBundled;
  }

  public void setIsBundled(Boolean isBundled) {
    this.isBundled = isBundled;
  }

  public KeyIndicatorUpdate transformationAlgorithmOfKQI(String transformationAlgorithmOfKQI) {
    this.transformationAlgorithmOfKQI = transformationAlgorithmOfKQI;
    return this;
  }

  /**
   * The description of a logical step-by-step procedure used to calculate the value of a KQI
   * @return transformationAlgorithmOfKQI
  */
  
  @Schema(name = "transformationAlgorithmOfKQI", description = "The description of a logical step-by-step procedure used to calculate the value of a KQI", required = false)
  public String getTransformationAlgorithmOfKQI() {
    return transformationAlgorithmOfKQI;
  }

  public void setTransformationAlgorithmOfKQI(String transformationAlgorithmOfKQI) {
    this.transformationAlgorithmOfKQI = transformationAlgorithmOfKQI;
  }

  public KeyIndicatorUpdate indicatorType(IndicatorTypeEnum indicatorType) {
    this.indicatorType = indicatorType;
    return this;
  }

  /**
   * Type of Key Indicator are Key Quality Indicator (KQI) or Key Performance Indicator (KPI)
   * @return indicatorType
  */
  
  @Schema(name = "indicatorType", description = "Type of Key Indicator are Key Quality Indicator (KQI) or Key Performance Indicator (KPI)", required = false)
  public IndicatorTypeEnum getIndicatorType() {
    return indicatorType;
  }

  public void setIndicatorType(IndicatorTypeEnum indicatorType) {
    this.indicatorType = indicatorType;
  }

  public KeyIndicatorUpdate keyIndicatorRelationship(List<KeyIndicatorRelationship> keyIndicatorRelationship) {
    this.keyIndicatorRelationship = keyIndicatorRelationship;
    return this;
  }

  public KeyIndicatorUpdate addKeyIndicatorRelationshipItem(KeyIndicatorRelationship keyIndicatorRelationshipItem) {
    if (this.keyIndicatorRelationship == null) {
      this.keyIndicatorRelationship = new ArrayList<>();
    }
    this.keyIndicatorRelationship.add(keyIndicatorRelationshipItem);
    return this;
  }

  /**
   * A list of key indicator relationships related to this object
   * @return keyIndicatorRelationship
  */
  @Valid 
  @Schema(name = "keyIndicatorRelationship", description = "A list of key indicator relationships related to this object", required = false)
  public List<KeyIndicatorRelationship> getKeyIndicatorRelationship() {
    return keyIndicatorRelationship;
  }

  public void setKeyIndicatorRelationship(List<KeyIndicatorRelationship> keyIndicatorRelationship) {
    this.keyIndicatorRelationship = keyIndicatorRelationship;
  }

  public KeyIndicatorUpdate relatedEntity(List<RelatedEntity> relatedEntity) {
    this.relatedEntity = relatedEntity;
    return this;
  }

  public KeyIndicatorUpdate addRelatedEntityItem(RelatedEntity relatedEntityItem) {
    if (this.relatedEntity == null) {
      this.relatedEntity = new ArrayList<>();
    }
    this.relatedEntity.add(relatedEntityItem);
    return this;
  }

  /**
   * A list of entities related to this parameter
   * @return relatedEntity
  */
  @Valid 
  @Schema(name = "relatedEntity", description = "A list of entities related to this parameter", required = false)
  public List<RelatedEntity> getRelatedEntity() {
    return relatedEntity;
  }

  public void setRelatedEntity(List<RelatedEntity> relatedEntity) {
    this.relatedEntity = relatedEntity;
  }

  public KeyIndicatorUpdate atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class Extensible name.
   * @return atType
  */
  
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class Extensible name.", required = false)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  public KeyIndicatorUpdate atBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
    return this;
  }

  /**
   * When sub-classing, this defines the super-class.
   * @return atBaseType
  */
  
  @Schema(name = "@baseType", description = "When sub-classing, this defines the super-class.", required = false)
  public String getAtBaseType() {
    return atBaseType;
  }

  public void setAtBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
  }

  public KeyIndicatorUpdate atSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships.
   * @return atSchemaLocation
  */
  
  @Schema(name = "@schemaLocation", description = "A URI to a JSON-Schema file that defines additional attributes and relationships.", required = false)
  public String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KeyIndicatorUpdate keyIndicatorUpdate = (KeyIndicatorUpdate) o;
    return Objects.equals(this.name, keyIndicatorUpdate.name) &&
        Objects.equals(this.category, keyIndicatorUpdate.category) &&
        Objects.equals(this.isBundled, keyIndicatorUpdate.isBundled) &&
        Objects.equals(this.transformationAlgorithmOfKQI, keyIndicatorUpdate.transformationAlgorithmOfKQI) &&
        Objects.equals(this.indicatorType, keyIndicatorUpdate.indicatorType) &&
        Objects.equals(this.keyIndicatorRelationship, keyIndicatorUpdate.keyIndicatorRelationship) &&
        Objects.equals(this.relatedEntity, keyIndicatorUpdate.relatedEntity) &&
        Objects.equals(this.atType, keyIndicatorUpdate.atType) &&
        Objects.equals(this.atBaseType, keyIndicatorUpdate.atBaseType) &&
        Objects.equals(this.atSchemaLocation, keyIndicatorUpdate.atSchemaLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, category, isBundled, transformationAlgorithmOfKQI, indicatorType, keyIndicatorRelationship, relatedEntity, atType, atBaseType, atSchemaLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeyIndicatorUpdate {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    isBundled: ").append(toIndentedString(isBundled)).append("\n");
    sb.append("    transformationAlgorithmOfKQI: ").append(toIndentedString(transformationAlgorithmOfKQI)).append("\n");
    sb.append("    indicatorType: ").append(toIndentedString(indicatorType)).append("\n");
    sb.append("    keyIndicatorRelationship: ").append(toIndentedString(keyIndicatorRelationship)).append("\n");
    sb.append("    relatedEntity: ").append(toIndentedString(relatedEntity)).append("\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
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

