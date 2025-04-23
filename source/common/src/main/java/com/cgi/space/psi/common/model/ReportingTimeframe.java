package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
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
 * Specifies the date range between which data points will be included in the report.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ReportingTimeframe", description = "Specifies the date range between which data points will be included in the report.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ReportingTimeframe {

  @JsonProperty("reportingStartDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime reportingStartDate;

  @JsonProperty("reportingEndDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime reportingEndDate;

  public ReportingTimeframe reportingStartDate(OffsetDateTime reportingStartDate) {
    this.reportingStartDate = reportingStartDate;
    return this;
  }

  /**
   * Get reportingStartDate
   * @return reportingStartDate
  */
  @Valid 
  @Schema(name = "reportingStartDate", required = false)
  public OffsetDateTime getReportingStartDate() {
    return reportingStartDate;
  }

  public void setReportingStartDate(OffsetDateTime reportingStartDate) {
    this.reportingStartDate = reportingStartDate;
  }

  public ReportingTimeframe reportingEndDate(OffsetDateTime reportingEndDate) {
    this.reportingEndDate = reportingEndDate;
    return this;
  }

  /**
   * Get reportingEndDate
   * @return reportingEndDate
  */
  @Valid 
  @Schema(name = "reportingEndDate", required = false)
  public OffsetDateTime getReportingEndDate() {
    return reportingEndDate;
  }

  public void setReportingEndDate(OffsetDateTime reportingEndDate) {
    this.reportingEndDate = reportingEndDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportingTimeframe reportingTimeframe = (ReportingTimeframe) o;
    return Objects.equals(this.reportingStartDate, reportingTimeframe.reportingStartDate) &&
        Objects.equals(this.reportingEndDate, reportingTimeframe.reportingEndDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reportingStartDate, reportingEndDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReportingTimeframe {\n");
    sb.append("    reportingStartDate: ").append(toIndentedString(reportingStartDate)).append("\n");
    sb.append("    reportingEndDate: ").append(toIndentedString(reportingEndDate)).append("\n");
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

