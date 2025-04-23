package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.Interval;
import com.cgi.space.psi.common.model.OutputFormat;
import com.cgi.space.psi.common.model.PerformanceJobRef;
import com.cgi.space.psi.common.model.PerformanceReportRef;
import com.cgi.space.psi.common.model.PerformanceReportStateType;
import com.cgi.space.psi.common.model.ReportingTimeframe;
import com.cgi.space.psi.common.model.ResultFormat;
import com.cgi.space.psi.common.model.ServicePayloadSpecificAttributes;
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
 * Performance Report Complex Query entity is used to perform  searches on Performance Report entities, including clauses based on ServicePayloadSpecificAttributes.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "PerformanceReportComplexQuery", description = "Performance Report Complex Query entity is used to perform  searches on Performance Report entities, including clauses based on ServicePayloadSpecificAttributes.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceReportComplexQuery {

  @JsonProperty("consumingApplicationId")
  private String consumingApplicationId;

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("description")
  private String description;

  @JsonProperty("granularity")
  private Interval granularity;

  @JsonProperty("outputFormat")
  private OutputFormat outputFormat;

  @JsonProperty("performanceJob")
  private PerformanceJobRef performanceJob;

  @JsonProperty("performanceReport")
  private PerformanceReportRef performanceReport;

  @JsonProperty("producingApplicationId")
  private String producingApplicationId;

  @JsonProperty("reportingTimeframe")
  private ReportingTimeframe reportingTimeframe;

  @JsonProperty("resultFormat")
  private ResultFormat resultFormat;

  @JsonProperty("servicePayloadSpecificAttributes")
  private ServicePayloadSpecificAttributes servicePayloadSpecificAttributes;

  @JsonProperty("state")
  private PerformanceReportStateType state;

  public PerformanceReportComplexQuery consumingApplicationId(String consumingApplicationId) {
    this.consumingApplicationId = consumingApplicationId;
    return this;
  }

  /**
   * Identifier of consuming application
   * @return consumingApplicationId
  */
  
  @Schema(name = "consumingApplicationId", description = "Identifier of consuming application", required = false)
  public String getConsumingApplicationId() {
    return consumingApplicationId;
  }

  public void setConsumingApplicationId(String consumingApplicationId) {
    this.consumingApplicationId = consumingApplicationId;
  }

  public PerformanceReportComplexQuery creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the Performance Report was created.
   * @return creationDate
  */
  @Valid 
  @Schema(name = "creationDate", description = "Date when the Performance Report was created.", required = false)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public PerformanceReportComplexQuery description(String description) {
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

  public PerformanceReportComplexQuery granularity(Interval granularity) {
    this.granularity = granularity;
    return this;
  }

  /**
   * Get granularity
   * @return granularity
  */
  @Valid 
  @Schema(name = "granularity", required = false)
  public Interval getGranularity() {
    return granularity;
  }

  public void setGranularity(Interval granularity) {
    this.granularity = granularity;
  }

  public PerformanceReportComplexQuery outputFormat(OutputFormat outputFormat) {
    this.outputFormat = outputFormat;
    return this;
  }

  /**
   * Get outputFormat
   * @return outputFormat
  */
  @Valid 
  @Schema(name = "outputFormat", required = false)
  public OutputFormat getOutputFormat() {
    return outputFormat;
  }

  public void setOutputFormat(OutputFormat outputFormat) {
    this.outputFormat = outputFormat;
  }

  public PerformanceReportComplexQuery performanceJob(PerformanceJobRef performanceJob) {
    this.performanceJob = performanceJob;
    return this;
  }

  /**
   * Get performanceJob
   * @return performanceJob
  */
  @Valid 
  @Schema(name = "performanceJob", required = false)
  public PerformanceJobRef getPerformanceJob() {
    return performanceJob;
  }

  public void setPerformanceJob(PerformanceJobRef performanceJob) {
    this.performanceJob = performanceJob;
  }

  public PerformanceReportComplexQuery performanceReport(PerformanceReportRef performanceReport) {
    this.performanceReport = performanceReport;
    return this;
  }

  /**
   * Get performanceReport
   * @return performanceReport
  */
  @Valid 
  @Schema(name = "performanceReport", required = false)
  public PerformanceReportRef getPerformanceReport() {
    return performanceReport;
  }

  public void setPerformanceReport(PerformanceReportRef performanceReport) {
    this.performanceReport = performanceReport;
  }

  public PerformanceReportComplexQuery producingApplicationId(String producingApplicationId) {
    this.producingApplicationId = producingApplicationId;
    return this;
  }

  /**
   * Identifier of producing application
   * @return producingApplicationId
  */
  
  @Schema(name = "producingApplicationId", description = "Identifier of producing application", required = false)
  public String getProducingApplicationId() {
    return producingApplicationId;
  }

  public void setProducingApplicationId(String producingApplicationId) {
    this.producingApplicationId = producingApplicationId;
  }

  public PerformanceReportComplexQuery reportingTimeframe(ReportingTimeframe reportingTimeframe) {
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

  public PerformanceReportComplexQuery resultFormat(ResultFormat resultFormat) {
    this.resultFormat = resultFormat;
    return this;
  }

  /**
   * Get resultFormat
   * @return resultFormat
  */
  @Valid 
  @Schema(name = "resultFormat", required = false)
  public ResultFormat getResultFormat() {
    return resultFormat;
  }

  public void setResultFormat(ResultFormat resultFormat) {
    this.resultFormat = resultFormat;
  }

  public PerformanceReportComplexQuery servicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
    this.servicePayloadSpecificAttributes = servicePayloadSpecificAttributes;
    return this;
  }

  /**
   * Get servicePayloadSpecificAttributes
   * @return servicePayloadSpecificAttributes
  */
  @Valid 
  @Schema(name = "servicePayloadSpecificAttributes", required = false)
  public ServicePayloadSpecificAttributes getServicePayloadSpecificAttributes() {
    return servicePayloadSpecificAttributes;
  }

  public void setServicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
    this.servicePayloadSpecificAttributes = servicePayloadSpecificAttributes;
  }

  public PerformanceReportComplexQuery state(PerformanceReportStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @Valid 
  @Schema(name = "state", required = false)
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
    PerformanceReportComplexQuery performanceReportComplexQuery = (PerformanceReportComplexQuery) o;
    return Objects.equals(this.consumingApplicationId, performanceReportComplexQuery.consumingApplicationId) &&
        Objects.equals(this.creationDate, performanceReportComplexQuery.creationDate) &&
        Objects.equals(this.description, performanceReportComplexQuery.description) &&
        Objects.equals(this.granularity, performanceReportComplexQuery.granularity) &&
        Objects.equals(this.outputFormat, performanceReportComplexQuery.outputFormat) &&
        Objects.equals(this.performanceJob, performanceReportComplexQuery.performanceJob) &&
        Objects.equals(this.performanceReport, performanceReportComplexQuery.performanceReport) &&
        Objects.equals(this.producingApplicationId, performanceReportComplexQuery.producingApplicationId) &&
        Objects.equals(this.reportingTimeframe, performanceReportComplexQuery.reportingTimeframe) &&
        Objects.equals(this.resultFormat, performanceReportComplexQuery.resultFormat) &&
        Objects.equals(this.servicePayloadSpecificAttributes, performanceReportComplexQuery.servicePayloadSpecificAttributes) &&
        Objects.equals(this.state, performanceReportComplexQuery.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(consumingApplicationId, creationDate, description, granularity, outputFormat, performanceJob, performanceReport, producingApplicationId, reportingTimeframe, resultFormat, servicePayloadSpecificAttributes, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceReportComplexQuery {\n");
    sb.append("    consumingApplicationId: ").append(toIndentedString(consumingApplicationId)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    granularity: ").append(toIndentedString(granularity)).append("\n");
    sb.append("    outputFormat: ").append(toIndentedString(outputFormat)).append("\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
    sb.append("    performanceReport: ").append(toIndentedString(performanceReport)).append("\n");
    sb.append("    producingApplicationId: ").append(toIndentedString(producingApplicationId)).append("\n");
    sb.append("    reportingTimeframe: ").append(toIndentedString(reportingTimeframe)).append("\n");
    sb.append("    resultFormat: ").append(toIndentedString(resultFormat)).append("\n");
    sb.append("    servicePayloadSpecificAttributes: ").append(toIndentedString(servicePayloadSpecificAttributes)).append("\n");
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

