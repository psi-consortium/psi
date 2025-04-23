package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.Interval;
import com.cgi.space.psi.common.model.JobType;
import com.cgi.space.psi.common.model.PerformanceJobRef;
import com.cgi.space.psi.common.model.PerformanceJobStateType;
import com.cgi.space.psi.common.model.PerformanceProfileValue;
import com.cgi.space.psi.common.model.ScheduleDefinition;
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
 * Performance Job Complex Query entity is used to perform  searches on Performance Job entities, including clauses based on ScheduleDefinition and ServicePayloadSpecificAttributes.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "PerformanceJobComplexQuery", description = "Performance Job Complex Query entity is used to perform  searches on Performance Job entities, including clauses based on ScheduleDefinition and ServicePayloadSpecificAttributes.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceJobComplexQuery {

  @JsonProperty("buyerJobId")
  private String buyerJobId;

  @JsonProperty("consumingApplicationId")
  private String consumingApplicationId;

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("description")
  private String description;

  @JsonProperty("granularity")
  private Interval granularity;

  @JsonProperty("jobPriority")
  private Integer jobPriority = 5;

  @JsonProperty("jobType")
  private JobType jobType;

  @JsonProperty("performanceJob")
  private PerformanceJobRef performanceJob;

  @JsonProperty("performanceProfile")
  private PerformanceProfileValue performanceProfile;

  @JsonProperty("producingApplicationId")
  private String producingApplicationId;

  @JsonProperty("reportingPeriod")
  private Interval reportingPeriod;

  @JsonProperty("scheduleDefinition")
  private ScheduleDefinition scheduleDefinition;

  @JsonProperty("servicePayloadSpecificAttributes")
  private ServicePayloadSpecificAttributes servicePayloadSpecificAttributes;

  @JsonProperty("state")
  private PerformanceJobStateType state;

  public PerformanceJobComplexQuery buyerJobId(String buyerJobId) {
    this.buyerJobId = buyerJobId;
    return this;
  }

  /**
   * Identifier of the job understood and assigned by the Buyer/Client.
   * @return buyerJobId
  */
  
  @Schema(name = "buyerJobId", description = "Identifier of the job understood and assigned by the Buyer/Client.", required = false)
  public String getBuyerJobId() {
    return buyerJobId;
  }

  public void setBuyerJobId(String buyerJobId) {
    this.buyerJobId = buyerJobId;
  }

  public PerformanceJobComplexQuery consumingApplicationId(String consumingApplicationId) {
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

  public PerformanceJobComplexQuery creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the Performance Job was created.
   * @return creationDate
  */
  @Valid 
  @Schema(name = "creationDate", description = "Date when the Performance Job was created.", required = false)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public PerformanceJobComplexQuery description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A free-text description of the Performance Job
   * @return description
  */
  
  @Schema(name = "description", description = "A free-text description of the Performance Job", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PerformanceJobComplexQuery granularity(Interval granularity) {
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

  public PerformanceJobComplexQuery jobPriority(Integer jobPriority) {
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

  public PerformanceJobComplexQuery jobType(JobType jobType) {
    this.jobType = jobType;
    return this;
  }

  /**
   * Get jobType
   * @return jobType
  */
  @Valid 
  @Schema(name = "jobType", required = false)
  public JobType getJobType() {
    return jobType;
  }

  public void setJobType(JobType jobType) {
    this.jobType = jobType;
  }

  public PerformanceJobComplexQuery performanceJob(PerformanceJobRef performanceJob) {
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

  public PerformanceJobComplexQuery performanceProfile(PerformanceProfileValue performanceProfile) {
    this.performanceProfile = performanceProfile;
    return this;
  }

  /**
   * Get performanceProfile
   * @return performanceProfile
  */
  @Valid 
  @Schema(name = "performanceProfile", required = false)
  public PerformanceProfileValue getPerformanceProfile() {
    return performanceProfile;
  }

  public void setPerformanceProfile(PerformanceProfileValue performanceProfile) {
    this.performanceProfile = performanceProfile;
  }

  public PerformanceJobComplexQuery producingApplicationId(String producingApplicationId) {
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

  public PerformanceJobComplexQuery reportingPeriod(Interval reportingPeriod) {
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

  public PerformanceJobComplexQuery scheduleDefinition(ScheduleDefinition scheduleDefinition) {
    this.scheduleDefinition = scheduleDefinition;
    return this;
  }

  /**
   * Get scheduleDefinition
   * @return scheduleDefinition
  */
  @Valid 
  @Schema(name = "scheduleDefinition", required = false)
  public ScheduleDefinition getScheduleDefinition() {
    return scheduleDefinition;
  }

  public void setScheduleDefinition(ScheduleDefinition scheduleDefinition) {
    this.scheduleDefinition = scheduleDefinition;
  }

  public PerformanceJobComplexQuery servicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
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

  public PerformanceJobComplexQuery state(PerformanceJobStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @Valid 
  @Schema(name = "state", required = false)
  public PerformanceJobStateType getState() {
    return state;
  }

  public void setState(PerformanceJobStateType state) {
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
    PerformanceJobComplexQuery performanceJobComplexQuery = (PerformanceJobComplexQuery) o;
    return Objects.equals(this.buyerJobId, performanceJobComplexQuery.buyerJobId) &&
        Objects.equals(this.consumingApplicationId, performanceJobComplexQuery.consumingApplicationId) &&
        Objects.equals(this.creationDate, performanceJobComplexQuery.creationDate) &&
        Objects.equals(this.description, performanceJobComplexQuery.description) &&
        Objects.equals(this.granularity, performanceJobComplexQuery.granularity) &&
        Objects.equals(this.jobPriority, performanceJobComplexQuery.jobPriority) &&
        Objects.equals(this.jobType, performanceJobComplexQuery.jobType) &&
        Objects.equals(this.performanceJob, performanceJobComplexQuery.performanceJob) &&
        Objects.equals(this.performanceProfile, performanceJobComplexQuery.performanceProfile) &&
        Objects.equals(this.producingApplicationId, performanceJobComplexQuery.producingApplicationId) &&
        Objects.equals(this.reportingPeriod, performanceJobComplexQuery.reportingPeriod) &&
        Objects.equals(this.scheduleDefinition, performanceJobComplexQuery.scheduleDefinition) &&
        Objects.equals(this.servicePayloadSpecificAttributes, performanceJobComplexQuery.servicePayloadSpecificAttributes) &&
        Objects.equals(this.state, performanceJobComplexQuery.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyerJobId, consumingApplicationId, creationDate, description, granularity, jobPriority, jobType, performanceJob, performanceProfile, producingApplicationId, reportingPeriod, scheduleDefinition, servicePayloadSpecificAttributes, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceJobComplexQuery {\n");
    sb.append("    buyerJobId: ").append(toIndentedString(buyerJobId)).append("\n");
    sb.append("    consumingApplicationId: ").append(toIndentedString(consumingApplicationId)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    granularity: ").append(toIndentedString(granularity)).append("\n");
    sb.append("    jobPriority: ").append(toIndentedString(jobPriority)).append("\n");
    sb.append("    jobType: ").append(toIndentedString(jobType)).append("\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
    sb.append("    performanceProfile: ").append(toIndentedString(performanceProfile)).append("\n");
    sb.append("    producingApplicationId: ").append(toIndentedString(producingApplicationId)).append("\n");
    sb.append("    reportingPeriod: ").append(toIndentedString(reportingPeriod)).append("\n");
    sb.append("    scheduleDefinition: ").append(toIndentedString(scheduleDefinition)).append("\n");
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

