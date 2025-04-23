package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ApplicableTimePeriod;
import com.cgi.space.psi.common.model.KeyIndicator;
import com.cgi.space.psi.common.model.ServiceLevelObjectiveThreshold;
import com.cgi.space.psi.common.model.ServiceLevelSpecConsequence;
import com.cgi.space.psi.common.model.TimeInterval;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * Service level objectives are defined in terms of parameters and metrics, thresholds, and tolerances associated with the parameters.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ServiceLevelObjective_Create", description = "Service level objectives are defined in terms of parameters and metrics, thresholds, and tolerances associated with the parameters.")
@JsonTypeName("ServiceLevelObjective_Create")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceLevelObjectiveCreate {

  @JsonProperty("conformancePeriod")
  private TimePeriod conformancePeriod;

  @JsonProperty("conformanceTarget")
  private String conformanceTarget;

  @JsonProperty("graceTimes")
  private String graceTimes;

  @JsonProperty("name")
  private String name;

  @JsonProperty("thresholdTarget")
  private String thresholdTarget;

  @JsonProperty("tolerancePeriod")
  private TimeInterval tolerancePeriod;

  @JsonProperty("toleranceTarget")
  private String toleranceTarget;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  @JsonProperty("keyIndicator")
  private KeyIndicator keyIndicator;

  @JsonProperty("consequence")
  @Valid
  private List<ServiceLevelSpecConsequence> consequence = null;

  @JsonProperty("threshold")
  @Valid
  private List<ServiceLevelObjectiveThreshold> threshold = null;

  @JsonProperty("applicability")
  @Valid
  private List<ApplicableTimePeriod> applicability = null;

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  public ServiceLevelObjectiveCreate conformancePeriod(TimePeriod conformancePeriod) {
    this.conformancePeriod = conformancePeriod;
    return this;
  }

  /**
   * Get conformancePeriod
   * @return conformancePeriod
  */
  @Valid 
  @Schema(name = "conformancePeriod", required = false)
  public TimePeriod getConformancePeriod() {
    return conformancePeriod;
  }

  public void setConformancePeriod(TimePeriod conformancePeriod) {
    this.conformancePeriod = conformancePeriod;
  }

  public ServiceLevelObjectiveCreate conformanceTarget(String conformanceTarget) {
    this.conformanceTarget = conformanceTarget;
    return this;
  }

  /**
   * A value used to determine if Service Level Objective is met.  The data type should be adjusted case by case.
   * @return conformanceTarget
  */
  @NotNull 
  @Schema(name = "conformanceTarget", description = "A value used to determine if Service Level Objective is met.  The data type should be adjusted case by case.", required = true)
  public String getConformanceTarget() {
    return conformanceTarget;
  }

  public void setConformanceTarget(String conformanceTarget) {
    this.conformanceTarget = conformanceTarget;
  }

  public ServiceLevelObjectiveCreate graceTimes(String graceTimes) {
    this.graceTimes = graceTimes;
    return this;
  }

  /**
   * The number of times an objective can remain un-updated without  a violation of a Service Level Agreement in reference to a measurement period and/or Service Level Agreement reporting period.
   * @return graceTimes
  */
  
  @Schema(name = "graceTimes", description = "The number of times an objective can remain un-updated without  a violation of a Service Level Agreement in reference to a measurement period and/or Service Level Agreement reporting period.", required = false)
  public String getGraceTimes() {
    return graceTimes;
  }

  public void setGraceTimes(String graceTimes) {
    this.graceTimes = graceTimes;
  }

  public ServiceLevelObjectiveCreate name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the service level objectives.
   * @return name
  */
  
  @Schema(name = "name", description = "The name of the service level objectives.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ServiceLevelObjectiveCreate thresholdTarget(String thresholdTarget) {
    this.thresholdTarget = thresholdTarget;
    return this;
  }

  /**
   * A value that used to specify when a warning should be used  that indicates an objective is danger of not being met. Notice, the data type should be adjusted case by case.
   * @return thresholdTarget
  */
  
  @Schema(name = "thresholdTarget", description = "A value that used to specify when a warning should be used  that indicates an objective is danger of not being met. Notice, the data type should be adjusted case by case.", required = false)
  public String getThresholdTarget() {
    return thresholdTarget;
  }

  public void setThresholdTarget(String thresholdTarget) {
    this.thresholdTarget = thresholdTarget;
  }

  public ServiceLevelObjectiveCreate tolerancePeriod(TimeInterval tolerancePeriod) {
    this.tolerancePeriod = tolerancePeriod;
    return this;
  }

  /**
   * Get tolerancePeriod
   * @return tolerancePeriod
  */
  @Valid 
  @Schema(name = "tolerancePeriod", required = false)
  public TimeInterval getTolerancePeriod() {
    return tolerancePeriod;
  }

  public void setTolerancePeriod(TimeInterval tolerancePeriod) {
    this.tolerancePeriod = tolerancePeriod;
  }

  public ServiceLevelObjectiveCreate toleranceTarget(String toleranceTarget) {
    this.toleranceTarget = toleranceTarget;
    return this;
  }

  /**
   * A value that specifies the allowable variation of a conformance  Target. The data type should be adjusted case by case.
   * @return toleranceTarget
  */
  
  @Schema(name = "toleranceTarget", description = "A value that specifies the allowable variation of a conformance  Target. The data type should be adjusted case by case.", required = false)
  public String getToleranceTarget() {
    return toleranceTarget;
  }

  public void setToleranceTarget(String toleranceTarget) {
    this.toleranceTarget = toleranceTarget;
  }

  public ServiceLevelObjectiveCreate validFor(TimePeriod validFor) {
    this.validFor = validFor;
    return this;
  }

  /**
   * Get validFor
   * @return validFor
  */
  @Valid 
  @Schema(name = "validFor", required = false)
  public TimePeriod getValidFor() {
    return validFor;
  }

  public void setValidFor(TimePeriod validFor) {
    this.validFor = validFor;
  }

  public ServiceLevelObjectiveCreate keyIndicator(KeyIndicator keyIndicator) {
    this.keyIndicator = keyIndicator;
    return this;
  }

  /**
   * Get keyIndicator
   * @return keyIndicator
  */
  @NotNull @Valid 
  @Schema(name = "keyIndicator", required = true)
  public KeyIndicator getKeyIndicator() {
    return keyIndicator;
  }

  public void setKeyIndicator(KeyIndicator keyIndicator) {
    this.keyIndicator = keyIndicator;
  }

  public ServiceLevelObjectiveCreate consequence(List<ServiceLevelSpecConsequence> consequence) {
    this.consequence = consequence;
    return this;
  }

  public ServiceLevelObjectiveCreate addConsequenceItem(ServiceLevelSpecConsequence consequenceItem) {
    if (this.consequence == null) {
      this.consequence = new ArrayList<>();
    }
    this.consequence.add(consequenceItem);
    return this;
  }

  /**
   * A list of consequences for this objective.
   * @return consequence
  */
  @Valid 
  @Schema(name = "consequence", description = "A list of consequences for this objective.", required = false)
  public List<ServiceLevelSpecConsequence> getConsequence() {
    return consequence;
  }

  public void setConsequence(List<ServiceLevelSpecConsequence> consequence) {
    this.consequence = consequence;
  }

  public ServiceLevelObjectiveCreate threshold(List<ServiceLevelObjectiveThreshold> threshold) {
    this.threshold = threshold;
    return this;
  }

  public ServiceLevelObjectiveCreate addThresholdItem(ServiceLevelObjectiveThreshold thresholdItem) {
    if (this.threshold == null) {
      this.threshold = new ArrayList<>();
    }
    this.threshold.add(thresholdItem);
    return this;
  }

  /**
   * A list of thresholds that applies to this objective.
   * @return threshold
  */
  @Valid @Size(min = 1) 
  @Schema(name = "threshold", description = "A list of thresholds that applies to this objective.", required = false)
  public List<ServiceLevelObjectiveThreshold> getThreshold() {
    return threshold;
  }

  public void setThreshold(List<ServiceLevelObjectiveThreshold> threshold) {
    this.threshold = threshold;
  }

  public ServiceLevelObjectiveCreate applicability(List<ApplicableTimePeriod> applicability) {
    this.applicability = applicability;
    return this;
  }

  public ServiceLevelObjectiveCreate addApplicabilityItem(ApplicableTimePeriod applicabilityItem) {
    if (this.applicability == null) {
      this.applicability = new ArrayList<>();
    }
    this.applicability.add(applicabilityItem);
    return this;
  }

  /**
   * The SLO applicability in terms of timing.
   * @return applicability
  */
  @Valid 
  @Schema(name = "applicability", description = "The SLO applicability in terms of timing.", required = false)
  public List<ApplicableTimePeriod> getApplicability() {
    return applicability;
  }

  public void setApplicability(List<ApplicableTimePeriod> applicability) {
    this.applicability = applicability;
  }

  public ServiceLevelObjectiveCreate atType(String atType) {
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

  public ServiceLevelObjectiveCreate atBaseType(String atBaseType) {
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

  public ServiceLevelObjectiveCreate atSchemaLocation(String atSchemaLocation) {
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
    ServiceLevelObjectiveCreate serviceLevelObjectiveCreate = (ServiceLevelObjectiveCreate) o;
    return Objects.equals(this.conformancePeriod, serviceLevelObjectiveCreate.conformancePeriod) &&
        Objects.equals(this.conformanceTarget, serviceLevelObjectiveCreate.conformanceTarget) &&
        Objects.equals(this.graceTimes, serviceLevelObjectiveCreate.graceTimes) &&
        Objects.equals(this.name, serviceLevelObjectiveCreate.name) &&
        Objects.equals(this.thresholdTarget, serviceLevelObjectiveCreate.thresholdTarget) &&
        Objects.equals(this.tolerancePeriod, serviceLevelObjectiveCreate.tolerancePeriod) &&
        Objects.equals(this.toleranceTarget, serviceLevelObjectiveCreate.toleranceTarget) &&
        Objects.equals(this.validFor, serviceLevelObjectiveCreate.validFor) &&
        Objects.equals(this.keyIndicator, serviceLevelObjectiveCreate.keyIndicator) &&
        Objects.equals(this.consequence, serviceLevelObjectiveCreate.consequence) &&
        Objects.equals(this.threshold, serviceLevelObjectiveCreate.threshold) &&
        Objects.equals(this.applicability, serviceLevelObjectiveCreate.applicability) &&
        Objects.equals(this.atType, serviceLevelObjectiveCreate.atType) &&
        Objects.equals(this.atBaseType, serviceLevelObjectiveCreate.atBaseType) &&
        Objects.equals(this.atSchemaLocation, serviceLevelObjectiveCreate.atSchemaLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conformancePeriod, conformanceTarget, graceTimes, name, thresholdTarget, tolerancePeriod, toleranceTarget, validFor, keyIndicator, consequence, threshold, applicability, atType, atBaseType, atSchemaLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceLevelObjectiveCreate {\n");
    sb.append("    conformancePeriod: ").append(toIndentedString(conformancePeriod)).append("\n");
    sb.append("    conformanceTarget: ").append(toIndentedString(conformanceTarget)).append("\n");
    sb.append("    graceTimes: ").append(toIndentedString(graceTimes)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    thresholdTarget: ").append(toIndentedString(thresholdTarget)).append("\n");
    sb.append("    tolerancePeriod: ").append(toIndentedString(tolerancePeriod)).append("\n");
    sb.append("    toleranceTarget: ").append(toIndentedString(toleranceTarget)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
    sb.append("    keyIndicator: ").append(toIndentedString(keyIndicator)).append("\n");
    sb.append("    consequence: ").append(toIndentedString(consequence)).append("\n");
    sb.append("    threshold: ").append(toIndentedString(threshold)).append("\n");
    sb.append("    applicability: ").append(toIndentedString(applicability)).append("\n");
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

