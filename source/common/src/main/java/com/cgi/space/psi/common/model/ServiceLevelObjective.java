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
import java.net.URI;
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

@Schema(name = "ServiceLevelObjective", description = "Service level objectives are defined in terms of parameters and metrics, thresholds, and tolerances associated with the parameters.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServiceLevelObjective {

  @JsonProperty("href")
  private URI href;

  @JsonProperty("id")
  private String id;

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

  public ServiceLevelObjective href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * The hyperlink reference
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "The hyperlink reference", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public ServiceLevelObjective id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The unique identifier
   * @return id
  */
  
  @Schema(name = "id", description = "The unique identifier", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ServiceLevelObjective conformancePeriod(TimePeriod conformancePeriod) {
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

  public ServiceLevelObjective conformanceTarget(String conformanceTarget) {
    this.conformanceTarget = conformanceTarget;
    return this;
  }

  /**
   * A value used to determine if Service Level Objective is met.  The data type should be adjusted case by case.
   * @return conformanceTarget
  */
  
  @Schema(name = "conformanceTarget", description = "A value used to determine if Service Level Objective is met.  The data type should be adjusted case by case.", required = false)
  public String getConformanceTarget() {
    return conformanceTarget;
  }

  public void setConformanceTarget(String conformanceTarget) {
    this.conformanceTarget = conformanceTarget;
  }

  public ServiceLevelObjective graceTimes(String graceTimes) {
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

  public ServiceLevelObjective name(String name) {
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

  public ServiceLevelObjective thresholdTarget(String thresholdTarget) {
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

  public ServiceLevelObjective tolerancePeriod(TimeInterval tolerancePeriod) {
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

  public ServiceLevelObjective toleranceTarget(String toleranceTarget) {
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

  public ServiceLevelObjective validFor(TimePeriod validFor) {
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

  public ServiceLevelObjective keyIndicator(KeyIndicator keyIndicator) {
    this.keyIndicator = keyIndicator;
    return this;
  }

  /**
   * Get keyIndicator
   * @return keyIndicator
  */
  @Valid 
  @Schema(name = "keyIndicator", required = false)
  public KeyIndicator getKeyIndicator() {
    return keyIndicator;
  }

  public void setKeyIndicator(KeyIndicator keyIndicator) {
    this.keyIndicator = keyIndicator;
  }

  public ServiceLevelObjective consequence(List<ServiceLevelSpecConsequence> consequence) {
    this.consequence = consequence;
    return this;
  }

  public ServiceLevelObjective addConsequenceItem(ServiceLevelSpecConsequence consequenceItem) {
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

  public ServiceLevelObjective threshold(List<ServiceLevelObjectiveThreshold> threshold) {
    this.threshold = threshold;
    return this;
  }

  public ServiceLevelObjective addThresholdItem(ServiceLevelObjectiveThreshold thresholdItem) {
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

  public ServiceLevelObjective applicability(List<ApplicableTimePeriod> applicability) {
    this.applicability = applicability;
    return this;
  }

  public ServiceLevelObjective addApplicabilityItem(ApplicableTimePeriod applicabilityItem) {
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

  public ServiceLevelObjective atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class extensible name.
   * @return atType
  */
  
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class extensible name.", required = false)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  public ServiceLevelObjective atBaseType(String atBaseType) {
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

  public ServiceLevelObjective atSchemaLocation(String atSchemaLocation) {
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
    ServiceLevelObjective serviceLevelObjective = (ServiceLevelObjective) o;
    return Objects.equals(this.href, serviceLevelObjective.href) &&
        Objects.equals(this.id, serviceLevelObjective.id) &&
        Objects.equals(this.conformancePeriod, serviceLevelObjective.conformancePeriod) &&
        Objects.equals(this.conformanceTarget, serviceLevelObjective.conformanceTarget) &&
        Objects.equals(this.graceTimes, serviceLevelObjective.graceTimes) &&
        Objects.equals(this.name, serviceLevelObjective.name) &&
        Objects.equals(this.thresholdTarget, serviceLevelObjective.thresholdTarget) &&
        Objects.equals(this.tolerancePeriod, serviceLevelObjective.tolerancePeriod) &&
        Objects.equals(this.toleranceTarget, serviceLevelObjective.toleranceTarget) &&
        Objects.equals(this.validFor, serviceLevelObjective.validFor) &&
        Objects.equals(this.keyIndicator, serviceLevelObjective.keyIndicator) &&
        Objects.equals(this.consequence, serviceLevelObjective.consequence) &&
        Objects.equals(this.threshold, serviceLevelObjective.threshold) &&
        Objects.equals(this.applicability, serviceLevelObjective.applicability) &&
        Objects.equals(this.atType, serviceLevelObjective.atType) &&
        Objects.equals(this.atBaseType, serviceLevelObjective.atBaseType) &&
        Objects.equals(this.atSchemaLocation, serviceLevelObjective.atSchemaLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, id, conformancePeriod, conformanceTarget, graceTimes, name, thresholdTarget, tolerancePeriod, toleranceTarget, validFor, keyIndicator, consequence, threshold, applicability, atType, atBaseType, atSchemaLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceLevelObjective {\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

