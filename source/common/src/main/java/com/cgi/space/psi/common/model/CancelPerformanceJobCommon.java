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
 * Request for cancellation of an existing Performance Job
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "CancelPerformanceJob_Common", description = "Request for cancellation of an existing Performance Job")
@JsonTypeName("CancelPerformanceJob_Common")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CancelPerformanceJobCommon {

  @JsonProperty("cancellationReason")
  private String cancellationReason;

  @JsonProperty("performanceJob")
  private PerformanceJobRef performanceJob;

  public CancelPerformanceJobCommon cancellationReason(String cancellationReason) {
    this.cancellationReason = cancellationReason;
    return this;
  }

  /**
   * An optional attribute that allows the Buyer/Client to provide additional detail to the Seller/Server on the reason for cancelling Performance Job.
   * @return cancellationReason
  */
  
  @Schema(name = "cancellationReason", description = "An optional attribute that allows the Buyer/Client to provide additional detail to the Seller/Server on the reason for cancelling Performance Job.", required = false)
  public String getCancellationReason() {
    return cancellationReason;
  }

  public void setCancellationReason(String cancellationReason) {
    this.cancellationReason = cancellationReason;
  }

  public CancelPerformanceJobCommon performanceJob(PerformanceJobRef performanceJob) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CancelPerformanceJobCommon cancelPerformanceJobCommon = (CancelPerformanceJobCommon) o;
    return Objects.equals(this.cancellationReason, cancelPerformanceJobCommon.cancellationReason) &&
        Objects.equals(this.performanceJob, cancelPerformanceJobCommon.performanceJob);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cancellationReason, performanceJob);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CancelPerformanceJobCommon {\n");
    sb.append("    cancellationReason: ").append(toIndentedString(cancellationReason)).append("\n");
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

