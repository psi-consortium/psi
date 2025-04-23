package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.Interval;
import com.cgi.space.psi.common.model.JobType;
import com.cgi.space.psi.common.model.PerformanceJobStateType;
import com.cgi.space.psi.common.model.PerformanceProfileValue;
import com.cgi.space.psi.common.model.ScheduleDefinition;
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
 * Performance Job Complex Query entity is used to perform  searches on Performance Job entities, including clauses based on ScheduleDefinition and ServicePayloadSpecificAttributes.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "PerformanceJobComplexQuery_Create", description = "Performance Job Complex Query entity is used to perform  searches on Performance Job entities, including clauses based on ScheduleDefinition and ServicePayloadSpecificAttributes.")
@JsonTypeName("PerformanceJobComplexQuery_Create")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceJobComplexQueryCreate {

  @JsonProperty("buyerJobId")
  private String buyerJobId;

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

  @JsonProperty("jobPriority")
  private Integer jobPriority = 5;

  @JsonProperty("jobType")
  private JobType jobType;

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

  public PerformanceJobComplexQueryCreate buyerJobId(String buyerJobId) {
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

  public PerformanceJobComplexQueryCreate consumingApplicationId(String consumingApplicationId) {
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

  public PerformanceJobComplexQueryCreate creationDateGt(OffsetDateTime creationDateGt) {
    this.creationDateGt = creationDateGt;
    return this;
  }

  /**
   * Date when the Performance Job was created - greater than.
   * @return creationDateGt
  */
  @Valid 
  @Schema(name = "creationDate.gt", description = "Date when the Performance Job was created - greater than.", required = false)
  public OffsetDateTime getCreationDateGt() {
    return creationDateGt;
  }

  public void setCreationDateGt(OffsetDateTime creationDateGt) {
    this.creationDateGt = creationDateGt;
  }

  public PerformanceJobComplexQueryCreate creationDateLt(OffsetDateTime creationDateLt) {
    this.creationDateLt = creationDateLt;
    return this;
  }

  /**
   * Date when the Performance Job was created - lower than.
   * @return creationDateLt
  */
  @Valid 
  @Schema(name = "creationDate.lt", description = "Date when the Performance Job was created - lower than.", required = false)
  public OffsetDateTime getCreationDateLt() {
    return creationDateLt;
  }

  public void setCreationDateLt(OffsetDateTime creationDateLt) {
    this.creationDateLt = creationDateLt;
  }

  public PerformanceJobComplexQueryCreate granularity(Interval granularity) {
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

  public PerformanceJobComplexQueryCreate jobPriority(Integer jobPriority) {
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

  public PerformanceJobComplexQueryCreate jobType(JobType jobType) {
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

  public PerformanceJobComplexQueryCreate performanceProfile(PerformanceProfileValue performanceProfile) {
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

  public PerformanceJobComplexQueryCreate producingApplicationId(String producingApplicationId) {
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

  public PerformanceJobComplexQueryCreate reportingPeriod(Interval reportingPeriod) {
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

  public PerformanceJobComplexQueryCreate scheduleDefinition(ScheduleDefinition scheduleDefinition) {
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

  public PerformanceJobComplexQueryCreate servicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
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

  public PerformanceJobComplexQueryCreate state(PerformanceJobStateType state) {
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
    PerformanceJobComplexQueryCreate performanceJobComplexQueryCreate = (PerformanceJobComplexQueryCreate) o;
    return Objects.equals(this.buyerJobId, performanceJobComplexQueryCreate.buyerJobId) &&
        Objects.equals(this.consumingApplicationId, performanceJobComplexQueryCreate.consumingApplicationId) &&
        Objects.equals(this.creationDateGt, performanceJobComplexQueryCreate.creationDateGt) &&
        Objects.equals(this.creationDateLt, performanceJobComplexQueryCreate.creationDateLt) &&
        Objects.equals(this.granularity, performanceJobComplexQueryCreate.granularity) &&
        Objects.equals(this.jobPriority, performanceJobComplexQueryCreate.jobPriority) &&
        Objects.equals(this.jobType, performanceJobComplexQueryCreate.jobType) &&
        Objects.equals(this.performanceProfile, performanceJobComplexQueryCreate.performanceProfile) &&
        Objects.equals(this.producingApplicationId, performanceJobComplexQueryCreate.producingApplicationId) &&
        Objects.equals(this.reportingPeriod, performanceJobComplexQueryCreate.reportingPeriod) &&
        Objects.equals(this.scheduleDefinition, performanceJobComplexQueryCreate.scheduleDefinition) &&
        Objects.equals(this.servicePayloadSpecificAttributes, performanceJobComplexQueryCreate.servicePayloadSpecificAttributes) &&
        Objects.equals(this.state, performanceJobComplexQueryCreate.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyerJobId, consumingApplicationId, creationDateGt, creationDateLt, granularity, jobPriority, jobType, performanceProfile, producingApplicationId, reportingPeriod, scheduleDefinition, servicePayloadSpecificAttributes, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceJobComplexQueryCreate {\n");
    sb.append("    buyerJobId: ").append(toIndentedString(buyerJobId)).append("\n");
    sb.append("    consumingApplicationId: ").append(toIndentedString(consumingApplicationId)).append("\n");
    sb.append("    creationDateGt: ").append(toIndentedString(creationDateGt)).append("\n");
    sb.append("    creationDateLt: ").append(toIndentedString(creationDateLt)).append("\n");
    sb.append("    granularity: ").append(toIndentedString(granularity)).append("\n");
    sb.append("    jobPriority: ").append(toIndentedString(jobPriority)).append("\n");
    sb.append("    jobType: ").append(toIndentedString(jobType)).append("\n");
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

