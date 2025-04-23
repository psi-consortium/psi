package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.Interval;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Timeframe boundary for collected data
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "MeasurementTime", description = "Timeframe boundary for collected data")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MeasurementTime {

  @JsonProperty("measurementStartDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime measurementStartDate;

  @JsonProperty("measurementEndDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime measurementEndDate;

  @JsonProperty("measurementInterval")
  private Interval measurementInterval;

  public MeasurementTime measurementStartDate(OffsetDateTime measurementStartDate) {
    this.measurementStartDate = measurementStartDate;
    return this;
  }

  /**
   * Start date of the period to which collected data points belong.
   * @return measurementStartDate
  */
  @Valid 
  @Schema(name = "measurementStartDate", description = "Start date of the period to which collected data points belong.", required = false)
  public OffsetDateTime getMeasurementStartDate() {
    return measurementStartDate;
  }

  public void setMeasurementStartDate(OffsetDateTime measurementStartDate) {
    this.measurementStartDate = measurementStartDate;
  }

  public MeasurementTime measurementEndDate(OffsetDateTime measurementEndDate) {
    this.measurementEndDate = measurementEndDate;
    return this;
  }

  /**
   * Start date of the period to which collected data points belong.
   * @return measurementEndDate
  */
  @Valid 
  @Schema(name = "measurementEndDate", description = "Start date of the period to which collected data points belong.", required = false)
  public OffsetDateTime getMeasurementEndDate() {
    return measurementEndDate;
  }

  public void setMeasurementEndDate(OffsetDateTime measurementEndDate) {
    this.measurementEndDate = measurementEndDate;
  }

  public MeasurementTime measurementInterval(Interval measurementInterval) {
    this.measurementInterval = measurementInterval;
    return this;
  }

  /**
   * Get measurementInterval
   * @return measurementInterval
  */
  @Valid 
  @Schema(name = "measurementInterval", required = false)
  public Interval getMeasurementInterval() {
    return measurementInterval;
  }

  public void setMeasurementInterval(Interval measurementInterval) {
    this.measurementInterval = measurementInterval;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MeasurementTime measurementTime = (MeasurementTime) o;
    return Objects.equals(this.measurementStartDate, measurementTime.measurementStartDate) &&
        Objects.equals(this.measurementEndDate, measurementTime.measurementEndDate) &&
        Objects.equals(this.measurementInterval, measurementTime.measurementInterval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(measurementStartDate, measurementEndDate, measurementInterval);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MeasurementTime {\n");
    sb.append("    measurementStartDate: ").append(toIndentedString(measurementStartDate)).append("\n");
    sb.append("    measurementEndDate: ").append(toIndentedString(measurementEndDate)).append("\n");
    sb.append("    measurementInterval: ").append(toIndentedString(measurementInterval)).append("\n");
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

