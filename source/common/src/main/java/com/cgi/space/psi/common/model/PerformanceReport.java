package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AttachmentURL;
import com.cgi.space.psi.common.model.PerformanceJobRefOrValue;
import com.cgi.space.psi.common.model.PerformanceReportStateType;
import com.cgi.space.psi.common.model.ReportContentItem;
import com.cgi.space.psi.common.model.ReportingTimeframe;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PerformanceReport
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceReport {

  @JsonProperty("description")
  private String description;

  @JsonProperty("reportingTimeframe")
  private ReportingTimeframe reportingTimeframe;

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("failureReason")
  private String failureReason;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("id")
  private String id;

  @JsonProperty("lastModifiedDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastModifiedDate;

  @JsonProperty("performanceJob")
  private PerformanceJobRefOrValue performanceJob;

  @JsonProperty("reportContent")
  @Valid
  private List<ReportContentItem> reportContent = null;

  @JsonProperty("reportUrl")
  private AttachmentURL reportUrl;

  @JsonProperty("state")
  private PerformanceReportStateType state;

  public PerformanceReport description(String description) {
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

  public PerformanceReport reportingTimeframe(ReportingTimeframe reportingTimeframe) {
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

  public PerformanceReport creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the Performance Report was created.
   * @return creationDate
  */
  @NotNull @Valid 
  @Schema(name = "creationDate", description = "Date when the Performance Report was created.", required = true)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public PerformanceReport failureReason(String failureReason) {
    this.failureReason = failureReason;
    return this;
  }

  /**
   * Reason in case report generation failed.
   * @return failureReason
  */
  
  @Schema(name = "failureReason", description = "Reason in case report generation failed.", required = false)
  public String getFailureReason() {
    return failureReason;
  }

  public void setFailureReason(String failureReason) {
    this.failureReason = failureReason;
  }

  public PerformanceReport href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * Hyperlink reference
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "Hyperlink reference", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public PerformanceReport id(String id) {
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

  public PerformanceReport lastModifiedDate(OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    return this;
  }

  /**
   * Date when the report was last modified.
   * @return lastModifiedDate
  */
  @Valid 
  @Schema(name = "lastModifiedDate", description = "Date when the report was last modified.", required = false)
  public OffsetDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public PerformanceReport performanceJob(PerformanceJobRefOrValue performanceJob) {
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

  public PerformanceReport reportContent(List<ReportContentItem> reportContent) {
    this.reportContent = reportContent;
    return this;
  }

  public PerformanceReport addReportContentItem(ReportContentItem reportContentItem) {
    if (this.reportContent == null) {
      this.reportContent = new ArrayList<>();
    }
    this.reportContent.add(reportContentItem);
    return this;
  }

  /**
   * Get reportContent
   * @return reportContent
  */
  @Valid 
  @Schema(name = "reportContent", required = false)
  public List<ReportContentItem> getReportContent() {
    return reportContent;
  }

  public void setReportContent(List<ReportContentItem> reportContent) {
    this.reportContent = reportContent;
  }

  public PerformanceReport reportUrl(AttachmentURL reportUrl) {
    this.reportUrl = reportUrl;
    return this;
  }

  /**
   * Get reportUrl
   * @return reportUrl
  */
  @Valid 
  @Schema(name = "reportUrl", required = false)
  public AttachmentURL getReportUrl() {
    return reportUrl;
  }

  public void setReportUrl(AttachmentURL reportUrl) {
    this.reportUrl = reportUrl;
  }

  public PerformanceReport state(PerformanceReportStateType state) {
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
    PerformanceReport performanceReport = (PerformanceReport) o;
    return Objects.equals(this.description, performanceReport.description) &&
        Objects.equals(this.reportingTimeframe, performanceReport.reportingTimeframe) &&
        Objects.equals(this.creationDate, performanceReport.creationDate) &&
        Objects.equals(this.failureReason, performanceReport.failureReason) &&
        Objects.equals(this.href, performanceReport.href) &&
        Objects.equals(this.id, performanceReport.id) &&
        Objects.equals(this.lastModifiedDate, performanceReport.lastModifiedDate) &&
        Objects.equals(this.performanceJob, performanceReport.performanceJob) &&
        Objects.equals(this.reportContent, performanceReport.reportContent) &&
        Objects.equals(this.reportUrl, performanceReport.reportUrl) &&
        Objects.equals(this.state, performanceReport.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, reportingTimeframe, creationDate, failureReason, href, id, lastModifiedDate, performanceJob, reportContent, reportUrl, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceReport {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    reportingTimeframe: ").append(toIndentedString(reportingTimeframe)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    failureReason: ").append(toIndentedString(failureReason)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastModifiedDate: ").append(toIndentedString(lastModifiedDate)).append("\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
    sb.append("    reportContent: ").append(toIndentedString(reportContent)).append("\n");
    sb.append("    reportUrl: ").append(toIndentedString(reportUrl)).append("\n");
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

