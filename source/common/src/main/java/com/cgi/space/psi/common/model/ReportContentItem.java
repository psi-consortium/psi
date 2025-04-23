package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.MeasurementTime;
import com.cgi.space.psi.common.model.ResultPayload;
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
 * Single item of the performance monitoring results in case result format was set to payload. Each item contains the timeframe of the collected data and a list of values measured in that timeframe.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ReportContentItem", description = "Single item of the performance monitoring results in case result format was set to payload. Each item contains the timeframe of the collected data and a list of values measured in that timeframe.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ReportContentItem {

  @JsonProperty("measurementTime")
  private MeasurementTime measurementTime;

  @JsonProperty("measurementDataPoints")
  @Valid
  private List<ResultPayload> measurementDataPoints = null;

  public ReportContentItem measurementTime(MeasurementTime measurementTime) {
    this.measurementTime = measurementTime;
    return this;
  }

  /**
   * Get measurementTime
   * @return measurementTime
  */
  @NotNull @Valid 
  @Schema(name = "measurementTime", required = true)
  public MeasurementTime getMeasurementTime() {
    return measurementTime;
  }

  public void setMeasurementTime(MeasurementTime measurementTime) {
    this.measurementTime = measurementTime;
  }

  public ReportContentItem measurementDataPoints(List<ResultPayload> measurementDataPoints) {
    this.measurementDataPoints = measurementDataPoints;
    return this;
  }

  public ReportContentItem addMeasurementDataPointsItem(ResultPayload measurementDataPointsItem) {
    if (this.measurementDataPoints == null) {
      this.measurementDataPoints = new ArrayList<>();
    }
    this.measurementDataPoints.add(measurementDataPointsItem);
    return this;
  }

  /**
   * List of performance monitoring values measured in the related timeframe.
   * @return measurementDataPoints
  */
  @Valid 
  @Schema(name = "measurementDataPoints", description = "List of performance monitoring values measured in the related timeframe.", required = false)
  public List<ResultPayload> getMeasurementDataPoints() {
    return measurementDataPoints;
  }

  public void setMeasurementDataPoints(List<ResultPayload> measurementDataPoints) {
    this.measurementDataPoints = measurementDataPoints;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportContentItem reportContentItem = (ReportContentItem) o;
    return Objects.equals(this.measurementTime, reportContentItem.measurementTime) &&
        Objects.equals(this.measurementDataPoints, reportContentItem.measurementDataPoints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(measurementTime, measurementDataPoints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReportContentItem {\n");
    sb.append("    measurementTime: ").append(toIndentedString(measurementTime)).append("\n");
    sb.append("    measurementDataPoints: ").append(toIndentedString(measurementDataPoints)).append("\n");
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

