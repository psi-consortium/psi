package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.PerformanceJobRefOrValue;
import com.cgi.space.psi.common.model.PerformanceReportStateType;
import com.cgi.space.psi.common.model.ReportingTimeframe;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * This class represents a single list item for the response of the &#x60;listPerformanceReport&#x60; operation.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "PerformanceReport_Find", description = "This class represents a single list item for the response of the `listPerformanceReport` operation.")
@JsonTypeName("PerformanceReport_Find")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceReportFind {

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("description")
  private String description;

  @JsonProperty("id")
  private String id;

  @JsonProperty("performanceJob")
  private PerformanceJobRefOrValue performanceJob;

  @JsonProperty("reportingTimeframe")
  private ReportingTimeframe reportingTimeframe;

  @JsonProperty("state")
  private PerformanceReportStateType state;

  public PerformanceReportFind creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the report was created.
   * @return creationDate
  */
  @NotNull @Valid 
  @Schema(name = "creationDate", description = "Date when the report was created.", required = true)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public PerformanceReportFind description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A free-text description of the Performance Report
   * @return description
  */
  
  @Schema(name = "description", description = "A free-text description of the Performance Report", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PerformanceReportFind id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PerformanceReportFind performanceJob(PerformanceJobRefOrValue performanceJob) {
    this.performanceJob = performanceJob;
    return this;
  }

  /**
   * Get performanceJob
   * @return performanceJob
  */
  @Valid 
  @Schema(name = "performanceJob", required = false)
  public PerformanceJobRefOrValue getPerformanceJob() {
    return performanceJob;
  }

  public void setPerformanceJob(PerformanceJobRefOrValue performanceJob) {
    this.performanceJob = performanceJob;
  }

  public PerformanceReportFind reportingTimeframe(ReportingTimeframe reportingTimeframe) {
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

  public PerformanceReportFind state(PerformanceReportStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @NotNull @Valid 
  @Schema(name = "state", required = true)
  public PerformanceReportStateType getState() {
    return state;
  }

  public void setState(PerformanceReportStateType state) {
    this.state = state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PerformanceReportFind performanceReportFind = (PerformanceReportFind) o;
    return Objects.equals(this.creationDate, performanceReportFind.creationDate) &&
        Objects.equals(this.description, performanceReportFind.description) &&
        Objects.equals(this.id, performanceReportFind.id) &&
        Objects.equals(this.performanceJob, performanceReportFind.performanceJob) &&
        Objects.equals(this.reportingTimeframe, performanceReportFind.reportingTimeframe) &&
        Objects.equals(this.state, performanceReportFind.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creationDate, description, id, performanceJob, reportingTimeframe, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceReportFind {\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
    sb.append("    reportingTimeframe: ").append(toIndentedString(reportingTimeframe)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

