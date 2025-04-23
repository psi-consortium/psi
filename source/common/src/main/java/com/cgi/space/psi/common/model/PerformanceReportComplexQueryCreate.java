package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.Interval;
import com.cgi.space.psi.common.model.OutputFormat;
import com.cgi.space.psi.common.model.PerformanceJobRef;
import com.cgi.space.psi.common.model.PerformanceReportStateType;
import com.cgi.space.psi.common.model.ResultFormat;
import com.cgi.space.psi.common.model.ServicePayloadSpecificAttributes;
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
 * Performance Report Complex Query entity is used to perform  searches on Performance Report entities, including clauses based on ServicePayloadSpecificAttributes.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "PerformanceReportComplexQuery_Create", description = "Performance Report Complex Query entity is used to perform  searches on Performance Report entities, including clauses based on ServicePayloadSpecificAttributes.")
@JsonTypeName("PerformanceReportComplexQuery_Create")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceReportComplexQueryCreate {

  @JsonProperty("consumingApplicationId")
  private String consumingApplicationId;

  @JsonProperty("creationDate.gt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDateGt;

  @JsonProperty("creationDate.lt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDateLt;

  @JsonProperty("granularity")
  private Interval granularity;

  @JsonProperty("outputFormat")
  private OutputFormat outputFormat;

  @JsonProperty("performanceJob")
  private PerformanceJobRef performanceJob;

  @JsonProperty("producingApplicationId")
  private String producingApplicationId;

  @JsonProperty("reportingTimeframe.startDate.gt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime reportingTimeframeStartDateGt;

  @JsonProperty("reportingTimeframe.startDate.lt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime reportingTimeframeStartDateLt;

  @JsonProperty("reportingTimeframe.endDate.gt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime reportingTimeframeEndDateGt;

  @JsonProperty("reportingTimeframe.endDate.lt")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime reportingTimeframeEndDateLt;

  @JsonProperty("resultFormat")
  private ResultFormat resultFormat;

  @JsonProperty("servicePayloadSpecificAttributes")
  private ServicePayloadSpecificAttributes servicePayloadSpecificAttributes;

  @JsonProperty("state")
  private PerformanceReportStateType state;

  public PerformanceReportComplexQueryCreate consumingApplicationId(String consumingApplicationId) {
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

  public PerformanceReportComplexQueryCreate creationDateGt(OffsetDateTime creationDateGt) {
    this.creationDateGt = creationDateGt;
    return this;
  }

  /**
   * Date when the Performance Report was created - greater than.
   * @return creationDateGt
  */
  @Valid 
  @Schema(name = "creationDate.gt", description = "Date when the Performance Report was created - greater than.", required = false)
  public OffsetDateTime getCreationDateGt() {
    return creationDateGt;
  }

  public void setCreationDateGt(OffsetDateTime creationDateGt) {
    this.creationDateGt = creationDateGt;
  }

  public PerformanceReportComplexQueryCreate creationDateLt(OffsetDateTime creationDateLt) {
    this.creationDateLt = creationDateLt;
    return this;
  }

  /**
   * Date when the Performance Report was created - lower than.
   * @return creationDateLt
  */
  @Valid 
  @Schema(name = "creationDate.lt", description = "Date when the Performance Report was created - lower than.", required = false)
  public OffsetDateTime getCreationDateLt() {
    return creationDateLt;
  }

  public void setCreationDateLt(OffsetDateTime creationDateLt) {
    this.creationDateLt = creationDateLt;
  }

  public PerformanceReportComplexQueryCreate granularity(Interval granularity) {
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

  public PerformanceReportComplexQueryCreate outputFormat(OutputFormat outputFormat) {
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

  public PerformanceReportComplexQueryCreate performanceJob(PerformanceJobRef performanceJob) {
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

  public PerformanceReportComplexQueryCreate producingApplicationId(String producingApplicationId) {
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

  public PerformanceReportComplexQueryCreate reportingTimeframeStartDateGt(OffsetDateTime reportingTimeframeStartDateGt) {
    this.reportingTimeframeStartDateGt = reportingTimeframeStartDateGt;
    return this;
  }

  /**
   * Start date of reporting timeframe - greater than.
   * @return reportingTimeframeStartDateGt
  */
  @Valid 
  @Schema(name = "reportingTimeframe.startDate.gt", description = "Start date of reporting timeframe - greater than.", required = false)
  public OffsetDateTime getReportingTimeframeStartDateGt() {
    return reportingTimeframeStartDateGt;
  }

  public void setReportingTimeframeStartDateGt(OffsetDateTime reportingTimeframeStartDateGt) {
    this.reportingTimeframeStartDateGt = reportingTimeframeStartDateGt;
  }

  public PerformanceReportComplexQueryCreate reportingTimeframeStartDateLt(OffsetDateTime reportingTimeframeStartDateLt) {
    this.reportingTimeframeStartDateLt = reportingTimeframeStartDateLt;
    return this;
  }

  /**
   * Start date of reporting timeframe - lower than.
   * @return reportingTimeframeStartDateLt
  */
  @Valid 
  @Schema(name = "reportingTimeframe.startDate.lt", description = "Start date of reporting timeframe - lower than.", required = false)
  public OffsetDateTime getReportingTimeframeStartDateLt() {
    return reportingTimeframeStartDateLt;
  }

  public void setReportingTimeframeStartDateLt(OffsetDateTime reportingTimeframeStartDateLt) {
    this.reportingTimeframeStartDateLt = reportingTimeframeStartDateLt;
  }

  public PerformanceReportComplexQueryCreate reportingTimeframeEndDateGt(OffsetDateTime reportingTimeframeEndDateGt) {
    this.reportingTimeframeEndDateGt = reportingTimeframeEndDateGt;
    return this;
  }

  /**
   * End date of reporting timeframe - greater than.
   * @return reportingTimeframeEndDateGt
  */
  @Valid 
  @Schema(name = "reportingTimeframe.endDate.gt", description = "End date of reporting timeframe - greater than.", required = false)
  public OffsetDateTime getReportingTimeframeEndDateGt() {
    return reportingTimeframeEndDateGt;
  }

  public void setReportingTimeframeEndDateGt(OffsetDateTime reportingTimeframeEndDateGt) {
    this.reportingTimeframeEndDateGt = reportingTimeframeEndDateGt;
  }

  public PerformanceReportComplexQueryCreate reportingTimeframeEndDateLt(OffsetDateTime reportingTimeframeEndDateLt) {
    this.reportingTimeframeEndDateLt = reportingTimeframeEndDateLt;
    return this;
  }

  /**
   * End date of reporting timeframe - lower than.
   * @return reportingTimeframeEndDateLt
  */
  @Valid 
  @Schema(name = "reportingTimeframe.endDate.lt", description = "End date of reporting timeframe - lower than.", required = false)
  public OffsetDateTime getReportingTimeframeEndDateLt() {
    return reportingTimeframeEndDateLt;
  }

  public void setReportingTimeframeEndDateLt(OffsetDateTime reportingTimeframeEndDateLt) {
    this.reportingTimeframeEndDateLt = reportingTimeframeEndDateLt;
  }

  public PerformanceReportComplexQueryCreate resultFormat(ResultFormat resultFormat) {
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

  public PerformanceReportComplexQueryCreate servicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
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

  public PerformanceReportComplexQueryCreate state(PerformanceReportStateType state) {
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
    PerformanceReportComplexQueryCreate performanceReportComplexQueryCreate = (PerformanceReportComplexQueryCreate) o;
    return Objects.equals(this.consumingApplicationId, performanceReportComplexQueryCreate.consumingApplicationId) &&
        Objects.equals(this.creationDateGt, performanceReportComplexQueryCreate.creationDateGt) &&
        Objects.equals(this.creationDateLt, performanceReportComplexQueryCreate.creationDateLt) &&
        Objects.equals(this.granularity, performanceReportComplexQueryCreate.granularity) &&
        Objects.equals(this.outputFormat, performanceReportComplexQueryCreate.outputFormat) &&
        Objects.equals(this.performanceJob, performanceReportComplexQueryCreate.performanceJob) &&
        Objects.equals(this.producingApplicationId, performanceReportComplexQueryCreate.producingApplicationId) &&
        Objects.equals(this.reportingTimeframeStartDateGt, performanceReportComplexQueryCreate.reportingTimeframeStartDateGt) &&
        Objects.equals(this.reportingTimeframeStartDateLt, performanceReportComplexQueryCreate.reportingTimeframeStartDateLt) &&
        Objects.equals(this.reportingTimeframeEndDateGt, performanceReportComplexQueryCreate.reportingTimeframeEndDateGt) &&
        Objects.equals(this.reportingTimeframeEndDateLt, performanceReportComplexQueryCreate.reportingTimeframeEndDateLt) &&
        Objects.equals(this.resultFormat, performanceReportComplexQueryCreate.resultFormat) &&
        Objects.equals(this.servicePayloadSpecificAttributes, performanceReportComplexQueryCreate.servicePayloadSpecificAttributes) &&
        Objects.equals(this.state, performanceReportComplexQueryCreate.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(consumingApplicationId, creationDateGt, creationDateLt, granularity, outputFormat, performanceJob, producingApplicationId, reportingTimeframeStartDateGt, reportingTimeframeStartDateLt, reportingTimeframeEndDateGt, reportingTimeframeEndDateLt, resultFormat, servicePayloadSpecificAttributes, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceReportComplexQueryCreate {\n");
    sb.append("    consumingApplicationId: ").append(toIndentedString(consumingApplicationId)).append("\n");
    sb.append("    creationDateGt: ").append(toIndentedString(creationDateGt)).append("\n");
    sb.append("    creationDateLt: ").append(toIndentedString(creationDateLt)).append("\n");
    sb.append("    granularity: ").append(toIndentedString(granularity)).append("\n");
    sb.append("    outputFormat: ").append(toIndentedString(outputFormat)).append("\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
    sb.append("    producingApplicationId: ").append(toIndentedString(producingApplicationId)).append("\n");
    sb.append("    reportingTimeframeStartDateGt: ").append(toIndentedString(reportingTimeframeStartDateGt)).append("\n");
    sb.append("    reportingTimeframeStartDateLt: ").append(toIndentedString(reportingTimeframeStartDateLt)).append("\n");
    sb.append("    reportingTimeframeEndDateGt: ").append(toIndentedString(reportingTimeframeEndDateGt)).append("\n");
    sb.append("    reportingTimeframeEndDateLt: ").append(toIndentedString(reportingTimeframeEndDateLt)).append("\n");
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

