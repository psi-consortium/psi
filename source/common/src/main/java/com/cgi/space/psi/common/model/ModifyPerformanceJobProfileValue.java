package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.Interval;
import com.cgi.space.psi.common.model.OutputFormat;
import com.cgi.space.psi.common.model.ResultFormat;
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
 * Direct assignment of values defined by PerformanceProfile type to  PerformanceJob object. Necessary when PerformanceJob is created without reference to PerformanceProfile.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ModifyPerformanceJob_ProfileValue", description = "Direct assignment of values defined by PerformanceProfile type to  PerformanceJob object. Necessary when PerformanceJob is created without reference to PerformanceProfile.")
@JsonTypeName("ModifyPerformanceJob_ProfileValue")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ModifyPerformanceJobProfileValue {

  @JsonProperty("granularity")
  private Interval granularity;

  @JsonProperty("jobPriority")
  private Integer jobPriority = 5;

  @JsonProperty("outputFormat")
  private OutputFormat outputFormat;

  @JsonProperty("reportingPeriod")
  private Interval reportingPeriod;

  @JsonProperty("resultFormat")
  private ResultFormat resultFormat;

  public ModifyPerformanceJobProfileValue granularity(Interval granularity) {
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

  public ModifyPerformanceJobProfileValue jobPriority(Integer jobPriority) {
    this.jobPriority = jobPriority;
    return this;
  }

  /**
   * The priority of the Performance Job. The way the management application will use the Job priority to schedule Job execution is application-specific and out the scope.
   * @return jobPriority
  */
  
  @Schema(name = "jobPriority", description = "The priority of the Performance Job. The way the management application will use the Job priority to schedule Job execution is application-specific and out the scope.", required = false)
  public Integer getJobPriority() {
    return jobPriority;
  }

  public void setJobPriority(Integer jobPriority) {
    this.jobPriority = jobPriority;
  }

  public ModifyPerformanceJobProfileValue outputFormat(OutputFormat outputFormat) {
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

  public ModifyPerformanceJobProfileValue reportingPeriod(Interval reportingPeriod) {
    this.reportingPeriod = reportingPeriod;
    return this;
  }

  /**
   * Get reportingPeriod
   * @return reportingPeriod
  */
  @Valid 
  @Schema(name = "reportingPeriod", required = false)
  public Interval getReportingPeriod() {
    return reportingPeriod;
  }

  public void setReportingPeriod(Interval reportingPeriod) {
    this.reportingPeriod = reportingPeriod;
  }

  public ModifyPerformanceJobProfileValue resultFormat(ResultFormat resultFormat) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModifyPerformanceJobProfileValue modifyPerformanceJobProfileValue = (ModifyPerformanceJobProfileValue) o;
    return Objects.equals(this.granularity, modifyPerformanceJobProfileValue.granularity) &&
        Objects.equals(this.jobPriority, modifyPerformanceJobProfileValue.jobPriority) &&
        Objects.equals(this.outputFormat, modifyPerformanceJobProfileValue.outputFormat) &&
        Objects.equals(this.reportingPeriod, modifyPerformanceJobProfileValue.reportingPeriod) &&
        Objects.equals(this.resultFormat, modifyPerformanceJobProfileValue.resultFormat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(granularity, jobPriority, outputFormat, reportingPeriod, resultFormat);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModifyPerformanceJobProfileValue {\n");
    sb.append("    granularity: ").append(toIndentedString(granularity)).append("\n");
    sb.append("    jobPriority: ").append(toIndentedString(jobPriority)).append("\n");
    sb.append("    outputFormat: ").append(toIndentedString(outputFormat)).append("\n");
    sb.append("    reportingPeriod: ").append(toIndentedString(reportingPeriod)).append("\n");
    sb.append("    resultFormat: ").append(toIndentedString(resultFormat)).append("\n");
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

