package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.PerformanceJobRef;
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
 * ResumePerformanceJobCreate
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonTypeName("ResumePerformanceJob_Create")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ResumePerformanceJobCreate {

  @JsonProperty("performanceJob")
  private PerformanceJobRef performanceJob;

  @JsonProperty("resumptionReason")
  private String resumptionReason;

  public ResumePerformanceJobCreate performanceJob(PerformanceJobRef performanceJob) {
    this.performanceJob = performanceJob;
    return this;
  }

  /**
   * Get performanceJob
   * @return performanceJob
  */
  @NotNull @Valid 
  @Schema(name = "performanceJob", required = true)
  public PerformanceJobRef getPerformanceJob() {
    return performanceJob;
  }

  public void setPerformanceJob(PerformanceJobRef performanceJob) {
    this.performanceJob = performanceJob;
  }

  public ResumePerformanceJobCreate resumptionReason(String resumptionReason) {
    this.resumptionReason = resumptionReason;
    return this;
  }

  /**
   * An optional attribute that allows the Buyer/Client to provide additional detail to the Seller/Server on the reason for resuming Performance Job.
   * @return resumptionReason
  */
  
  @Schema(name = "resumptionReason", description = "An optional attribute that allows the Buyer/Client to provide additional detail to the Seller/Server on the reason for resuming Performance Job.", required = false)
  public String getResumptionReason() {
    return resumptionReason;
  }

  public void setResumptionReason(String resumptionReason) {
    this.resumptionReason = resumptionReason;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResumePerformanceJobCreate resumePerformanceJobCreate = (ResumePerformanceJobCreate) o;
    return Objects.equals(this.performanceJob, resumePerformanceJobCreate.performanceJob) &&
        Objects.equals(this.resumptionReason, resumePerformanceJobCreate.resumptionReason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(performanceJob, resumptionReason);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResumePerformanceJobCreate {\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
    sb.append("    resumptionReason: ").append(toIndentedString(resumptionReason)).append("\n");
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

