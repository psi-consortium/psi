package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * An interval of time from start- to endTime. If a time zone is required, it must be determined from the context.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "TimeInterval", description = "An interval of time from start- to endTime. If a time zone is required, it must be determined from the context.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TimeInterval {

  @JsonProperty("startTime")
  private String startTime;

  @JsonProperty("endTime")
  private String endTime;

  public TimeInterval startTime(String startTime) {
    this.startTime = startTime;
    return this;
  }

  /**
   * Start of the time interval in hours, minutes and seconds.
   * @return startTime
  */
  @NotNull @Pattern(regexp = "^([0]?[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]") 
  @Schema(name = "startTime", example = "00:00:00", description = "Start of the time interval in hours, minutes and seconds.", required = true)
  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public TimeInterval endTime(String endTime) {
    this.endTime = endTime;
    return this;
  }

  /**
   * End of the time interval in hours, minutes and seconds.
   * @return endTime
  */
  @NotNull @Pattern(regexp = "^([0]?[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]") 
  @Schema(name = "endTime", example = "23:59:59", description = "End of the time interval in hours, minutes and seconds.", required = true)
  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimeInterval timeInterval = (TimeInterval) o;
    return Objects.equals(this.startTime, timeInterval.startTime) &&
        Objects.equals(this.endTime, timeInterval.endTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startTime, endTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TimeInterval {\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
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

