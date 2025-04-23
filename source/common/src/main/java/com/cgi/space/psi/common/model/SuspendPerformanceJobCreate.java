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
 * SuspendPerformanceJobCreate
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonTypeName("SuspendPerformanceJob_Create")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class SuspendPerformanceJobCreate {

  @JsonProperty("performanceJob")
  private PerformanceJobRef performanceJob;

  @JsonProperty("suspensionReason")
  private String suspensionReason;

  public SuspendPerformanceJobCreate performanceJob(PerformanceJobRef performanceJob) {
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

  public SuspendPerformanceJobCreate suspensionReason(String suspensionReason) {
    this.suspensionReason = suspensionReason;
    return this;
  }

  /**
   * An optional attribute that allows the Buyer/Client to provide additional detail to the Seller/Server on the reason for suspending Performance Job.
   * @return suspensionReason
  */
  
  @Schema(name = "suspensionReason", description = "An optional attribute that allows the Buyer/Client to provide additional detail to the Seller/Server on the reason for suspending Performance Job.", required = false)
  public String getSuspensionReason() {
    return suspensionReason;
  }

  public void setSuspensionReason(String suspensionReason) {
    this.suspensionReason = suspensionReason;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuspendPerformanceJobCreate suspendPerformanceJobCreate = (SuspendPerformanceJobCreate) o;
    return Objects.equals(this.performanceJob, suspendPerformanceJobCreate.performanceJob) &&
        Objects.equals(this.suspensionReason, suspendPerformanceJobCreate.suspensionReason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(performanceJob, suspensionReason);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SuspendPerformanceJobCreate {\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
    sb.append("    suspensionReason: ").append(toIndentedString(suspensionReason)).append("\n");
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

