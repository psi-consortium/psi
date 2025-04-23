package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * The period of time for which Capacity or CapacityDemand applies.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ApplicableTimePeriod", description = "The period of time for which Capacity or CapacityDemand applies.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ApplicableTimePeriod {

  @JsonProperty("dayOfWeek")
  private String dayOfWeek;

  @JsonProperty("fromToDateTime")
  private TimePeriod fromToDateTime;

  @JsonProperty("rangeInterval")
  private String rangeInterval;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public ApplicableTimePeriod dayOfWeek(String dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    return this;
  }

  /**
   * A day or days representing when the schedule is applicable. For example 2, 3 represent Monday and Tuesday.
   * @return dayOfWeek
  */
  
  @Schema(name = "dayOfWeek", description = "A day or days representing when the schedule is applicable. For example 2, 3 represent Monday and Tuesday.", required = false)
  public String getDayOfWeek() {
    return dayOfWeek;
  }

  public void setDayOfWeek(String dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public ApplicableTimePeriod fromToDateTime(TimePeriod fromToDateTime) {
    this.fromToDateTime = fromToDateTime;
    return this;
  }

  /**
   * Get fromToDateTime
   * @return fromToDateTime
  */
  @Valid 
  @Schema(name = "fromToDateTime", required = false)
  public TimePeriod getFromToDateTime() {
    return fromToDateTime;
  }

  public void setFromToDateTime(TimePeriod fromToDateTime) {
    this.fromToDateTime = fromToDateTime;
  }

  public ApplicableTimePeriod rangeInterval(String rangeInterval) {
    this.rangeInterval = rangeInterval;
    return this;
  }

  /**
   * An indicator that specifies the inclusion or exclusion of the from and to DateTime attributes.   Possible values are \"open\", \"closed\", \"closedBottom\" and \"closedTop\".
   * @return rangeInterval
  */
  
  @Schema(name = "rangeInterval", description = "An indicator that specifies the inclusion or exclusion of the from and to DateTime attributes.   Possible values are \"open\", \"closed\", \"closedBottom\" and \"closedTop\".", required = false)
  public String getRangeInterval() {
    return rangeInterval;
  }

  public void setRangeInterval(String rangeInterval) {
    this.rangeInterval = rangeInterval;
  }

  public ApplicableTimePeriod validFor(TimePeriod validFor) {
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

  public ApplicableTimePeriod atBaseType(String atBaseType) {
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

  public ApplicableTimePeriod atSchemaLocation(String atSchemaLocation) {
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

  public ApplicableTimePeriod atType(String atType) {
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
    ApplicableTimePeriod applicableTimePeriod = (ApplicableTimePeriod) o;
    return Objects.equals(this.dayOfWeek, applicableTimePeriod.dayOfWeek) &&
        Objects.equals(this.fromToDateTime, applicableTimePeriod.fromToDateTime) &&
        Objects.equals(this.rangeInterval, applicableTimePeriod.rangeInterval) &&
        Objects.equals(this.validFor, applicableTimePeriod.validFor) &&
        Objects.equals(this.atBaseType, applicableTimePeriod.atBaseType) &&
        Objects.equals(this.atSchemaLocation, applicableTimePeriod.atSchemaLocation) &&
        Objects.equals(this.atType, applicableTimePeriod.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dayOfWeek, fromToDateTime, rangeInterval, validFor, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApplicableTimePeriod {\n");
    sb.append("    dayOfWeek: ").append(toIndentedString(dayOfWeek)).append("\n");
    sb.append("    fromToDateTime: ").append(toIndentedString(fromToDateTime)).append("\n");
    sb.append("    rangeInterval: ").append(toIndentedString(rangeInterval)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
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

