package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ApplicableTimePeriod;
import com.cgi.space.psi.common.model.Quantity;
import com.cgi.space.psi.common.model.ServiceLevelSpecConsequence;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * A threshold base entity containing threshold rules.This entity is mandatory.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ServiceLevelObjectiveThreshold", description = "A threshold base entity containing threshold rules.This entity is mandatory.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceLevelObjectiveThreshold {

  @JsonProperty("description")
  private String description;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  /**
   * The Threshold Type may be either LogicDefinition or PreDefined.
   */
  public enum ThresholdTypeEnum {
    LOGICDEFINITION("LogicDefinition"),
    
    PREDEFINED("PreDefined");

    private String value;

    ThresholdTypeEnum(String value) {
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
    public static ThresholdTypeEnum fromValue(String value) {
      for (ThresholdTypeEnum b : ThresholdTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("thresholdType")
  private ThresholdTypeEnum thresholdType;

  @JsonProperty("consequence")
  @Valid
  private List<ServiceLevelSpecConsequence> consequence = null;

  /**
   * A concrete threshold may have two possible values: \\\"Raise\\\" - a threshold was crossed or \\\"Clear\\\" - a threshold ceased crossing.
   */
  public enum ThresholdConditionEnum {
    RAISE("Raise"),
    
    CLEAR("Clear");

    private String value;

    ThresholdConditionEnum(String value) {
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
    public static ThresholdConditionEnum fromValue(String value) {
      for (ThresholdConditionEnum b : ThresholdConditionEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("thresholdCondition")
  private ThresholdConditionEnum thresholdCondition;

  @JsonProperty("applicability")
  @Valid
  private List<ApplicableTimePeriod> applicability = null;

  @JsonProperty("upperBound")
  private Quantity upperBound;

  @JsonProperty("lowerBound")
  private Quantity lowerBound;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public ServiceLevelObjectiveThreshold description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A description of the threshold.
   * @return description
  */
  
  @Schema(name = "description", description = "A description of the threshold.", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ServiceLevelObjectiveThreshold id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The unique identifier.
   * @return id
  */
  
  @Schema(name = "id", description = "The unique identifier.", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ServiceLevelObjectiveThreshold name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A word, term, or phrase by which a threshold is known and distinguished from other thresholds.
   * @return name
  */
  
  @Schema(name = "name", description = "A word, term, or phrase by which a threshold is known and distinguished from other thresholds.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServiceLevelObjectiveThreshold thresholdType(ThresholdTypeEnum thresholdType) {
    this.thresholdType = thresholdType;
    return this;
  }

  /**
   * The Threshold Type may be either LogicDefinition or PreDefined.
   * @return thresholdType
  */
  
  @Schema(name = "thresholdType", description = "The Threshold Type may be either LogicDefinition or PreDefined.", required = false)
  public ThresholdTypeEnum getThresholdType() {
    return thresholdType;
  }

  public void setThresholdType(ThresholdTypeEnum thresholdType) {
    this.thresholdType = thresholdType;
  }

  public ServiceLevelObjectiveThreshold consequence(List<ServiceLevelSpecConsequence> consequence) {
    this.consequence = consequence;
    return this;
  }

  public ServiceLevelObjectiveThreshold addConsequenceItem(ServiceLevelSpecConsequence consequenceItem) {
    if (this.consequence == null) {
      this.consequence = new ArrayList<>();
    }
    this.consequence.add(consequenceItem);
    return this;
  }

  /**
   * A threshold crossing or a threshold ceased to be crossing results in a consequence.
   * @return consequence
  */
  @Valid 
  @Schema(name = "consequence", description = "A threshold crossing or a threshold ceased to be crossing results in a consequence.", required = false)
  public List<ServiceLevelSpecConsequence> getConsequence() {
    return consequence;
  }

  public void setConsequence(List<ServiceLevelSpecConsequence> consequence) {
    this.consequence = consequence;
  }

  public ServiceLevelObjectiveThreshold thresholdCondition(ThresholdConditionEnum thresholdCondition) {
    this.thresholdCondition = thresholdCondition;
    return this;
  }

  /**
   * A concrete threshold may have two possible values: \\\"Raise\\\" - a threshold was crossed or \\\"Clear\\\" - a threshold ceased crossing.
   * @return thresholdCondition
  */
  
  @Schema(name = "thresholdCondition", description = "A concrete threshold may have two possible values: \\\"Raise\\\" - a threshold was crossed or \\\"Clear\\\" - a threshold ceased crossing.", required = false)
  public ThresholdConditionEnum getThresholdCondition() {
    return thresholdCondition;
  }

  public void setThresholdCondition(ThresholdConditionEnum thresholdCondition) {
    this.thresholdCondition = thresholdCondition;
  }

  public ServiceLevelObjectiveThreshold applicability(List<ApplicableTimePeriod> applicability) {
    this.applicability = applicability;
    return this;
  }

  public ServiceLevelObjectiveThreshold addApplicabilityItem(ApplicableTimePeriod applicabilityItem) {
    if (this.applicability == null) {
      this.applicability = new ArrayList<>();
    }
    this.applicability.add(applicabilityItem);
    return this;
  }

  /**
   * The threshold applicability in terms of timing.
   * @return applicability
  */
  @Valid 
  @Schema(name = "applicability", description = "The threshold applicability in terms of timing.", required = false)
  public List<ApplicableTimePeriod> getApplicability() {
    return applicability;
  }

  public void setApplicability(List<ApplicableTimePeriod> applicability) {
    this.applicability = applicability;
  }

  public ServiceLevelObjectiveThreshold upperBound(Quantity upperBound) {
    this.upperBound = upperBound;
    return this;
  }

  /**
   * Get upperBound
   * @return upperBound
  */
  @Valid 
  @Schema(name = "upperBound", required = false)
  public Quantity getUpperBound() {
    return upperBound;
  }

  public void setUpperBound(Quantity upperBound) {
    this.upperBound = upperBound;
  }

  public ServiceLevelObjectiveThreshold lowerBound(Quantity lowerBound) {
    this.lowerBound = lowerBound;
    return this;
  }

  /**
   * Get lowerBound
   * @return lowerBound
  */
  @Valid 
  @Schema(name = "lowerBound", required = false)
  public Quantity getLowerBound() {
    return lowerBound;
  }

  public void setLowerBound(Quantity lowerBound) {
    this.lowerBound = lowerBound;
  }

  public ServiceLevelObjectiveThreshold atBaseType(String atBaseType) {
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

  public ServiceLevelObjectiveThreshold atSchemaLocation(String atSchemaLocation) {
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

  public ServiceLevelObjectiveThreshold atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * Get atType
   * @return atType
  */
  
  @Schema(name = "@type", required = false)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceLevelObjectiveThreshold serviceLevelObjectiveThreshold = (ServiceLevelObjectiveThreshold) o;
    return Objects.equals(this.description, serviceLevelObjectiveThreshold.description) &&
        Objects.equals(this.id, serviceLevelObjectiveThreshold.id) &&
        Objects.equals(this.name, serviceLevelObjectiveThreshold.name) &&
        Objects.equals(this.thresholdType, serviceLevelObjectiveThreshold.thresholdType) &&
        Objects.equals(this.consequence, serviceLevelObjectiveThreshold.consequence) &&
        Objects.equals(this.thresholdCondition, serviceLevelObjectiveThreshold.thresholdCondition) &&
        Objects.equals(this.applicability, serviceLevelObjectiveThreshold.applicability) &&
        Objects.equals(this.upperBound, serviceLevelObjectiveThreshold.upperBound) &&
        Objects.equals(this.lowerBound, serviceLevelObjectiveThreshold.lowerBound) &&
        Objects.equals(this.atBaseType, serviceLevelObjectiveThreshold.atBaseType) &&
        Objects.equals(this.atSchemaLocation, serviceLevelObjectiveThreshold.atSchemaLocation) &&
        Objects.equals(this.atType, serviceLevelObjectiveThreshold.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, id, name, thresholdType, consequence, thresholdCondition, applicability, upperBound, lowerBound, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceLevelObjectiveThreshold {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    thresholdType: ").append(toIndentedString(thresholdType)).append("\n");
    sb.append("    consequence: ").append(toIndentedString(consequence)).append("\n");
    sb.append("    thresholdCondition: ").append(toIndentedString(thresholdCondition)).append("\n");
    sb.append("    applicability: ").append(toIndentedString(applicability)).append("\n");
    sb.append("    upperBound: ").append(toIndentedString(upperBound)).append("\n");
    sb.append("    lowerBound: ").append(toIndentedString(lowerBound)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
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

