package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.PerformanceJobValue;
import com.cgi.space.psi.common.model.ReportingTimeframe;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PerformanceReportCreate
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonTypeName("PerformanceReport_Create")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceReportCreate {

  @JsonProperty("description")
  private String description;

  @JsonProperty("reportingTimeframe")
  private ReportingTimeframe reportingTimeframe;

  @JsonProperty("performanceJob")
  private PerformanceJobValue performanceJob;

  public PerformanceReportCreate description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A free-text description of the performance report
   * @return description
  */
  
  @Schema(name = "description", description = "A free-text description of the performance report", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PerformanceReportCreate reportingTimeframe(ReportingTimeframe reportingTimeframe) {
    this.reportingTimeframe = reportingTimeframe;
    return this;
  }

  /**
   * Get reportingTimeframe
   * @return reportingTimeframe
  */
  @Valid 
  @Schema(name = "reportingTimeframe", required = false)
  public ReportingTimeframe getReportingTimeframe() {
    return reportingTimeframe;
  }

  public void setReportingTimeframe(ReportingTimeframe reportingTimeframe) {
    this.reportingTimeframe = reportingTimeframe;
  }

  public PerformanceReportCreate performanceJob(PerformanceJobValue performanceJob) {
    this.performanceJob = performanceJob;
    return this;
  }

  /**
   * Get performanceJob
   * @return performanceJob
  */
  @NotNull @Valid 
  @Schema(name = "performanceJob", required = true)
  public PerformanceJobValue getPerformanceJob() {
    return performanceJob;
  }

  public void setPerformanceJob(PerformanceJobValue performanceJob) {
    this.performanceJob = performanceJob;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PerformanceReportCreate performanceReportCreate = (PerformanceReportCreate) o;
    return Objects.equals(this.description, performanceReportCreate.description) &&
        Objects.equals(this.reportingTimeframe, performanceReportCreate.reportingTimeframe) &&
        Objects.equals(this.performanceJob, performanceReportCreate.performanceJob);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, reportingTimeframe, performanceJob);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceReportCreate {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    reportingTimeframe: ").append(toIndentedString(reportingTimeframe)).append("\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
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

