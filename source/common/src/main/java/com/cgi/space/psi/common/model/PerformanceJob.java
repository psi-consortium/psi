package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.FileTransferData;
import com.cgi.space.psi.common.model.PerformanceJobStateType;
import com.cgi.space.psi.common.model.PerformanceProfileValue;
import com.cgi.space.psi.common.model.ScheduleDefinition;
import com.cgi.space.psi.common.model.ServicePayloadSpecificAttributes;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * PerformanceJob
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceJob {

  @JsonProperty("buyerJobId")
  private String buyerJobId;

  @JsonProperty("consumingApplicationId")
  private String consumingApplicationId;

  @JsonProperty("description")
  private String description;

  @JsonProperty("fileTransferData")
  private FileTransferData fileTransferData;

  @JsonProperty("performanceProfile")
  private PerformanceProfileValue performanceProfile;

  @JsonProperty("producingApplicationId")
  private String producingApplicationId;

  @JsonProperty("scheduleDefinition")
  private ScheduleDefinition scheduleDefinition;

  @JsonProperty("servicePayloadSpecificAttributes")
  private ServicePayloadSpecificAttributes servicePayloadSpecificAttributes;

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("id")
  private String id;

  @JsonProperty("lastModifiedDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastModifiedDate;

  @JsonProperty("rejectionReason")
  private String rejectionReason;

  @JsonProperty("state")
  private PerformanceJobStateType state;

  public PerformanceJob buyerJobId(String buyerJobId) {
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

  public PerformanceJob consumingApplicationId(String consumingApplicationId) {
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

  public PerformanceJob description(String description) {
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

  public PerformanceJob fileTransferData(FileTransferData fileTransferData) {
    this.fileTransferData = fileTransferData;
    return this;
  }

  /**
   * Get fileTransferData
   * @return fileTransferData
  */
  @Valid 
  @Schema(name = "fileTransferData", required = false)
  public FileTransferData getFileTransferData() {
    return fileTransferData;
  }

  public void setFileTransferData(FileTransferData fileTransferData) {
    this.fileTransferData = fileTransferData;
  }

  public PerformanceJob performanceProfile(PerformanceProfileValue performanceProfile) {
    this.performanceProfile = performanceProfile;
    return this;
  }

  /**
   * Get performanceProfile
   * @return performanceProfile
  */
  @NotNull @Valid 
  @Schema(name = "performanceProfile", required = true)
  public PerformanceProfileValue getPerformanceProfile() {
    return performanceProfile;
  }

  public void setPerformanceProfile(PerformanceProfileValue performanceProfile) {
    this.performanceProfile = performanceProfile;
  }

  public PerformanceJob producingApplicationId(String producingApplicationId) {
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

  public PerformanceJob scheduleDefinition(ScheduleDefinition scheduleDefinition) {
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

  public PerformanceJob servicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
    this.servicePayloadSpecificAttributes = servicePayloadSpecificAttributes;
    return this;
  }

  /**
   * Get servicePayloadSpecificAttributes
   * @return servicePayloadSpecificAttributes
  */
  @NotNull @Valid 
  @Schema(name = "servicePayloadSpecificAttributes", required = true)
  public ServicePayloadSpecificAttributes getServicePayloadSpecificAttributes() {
    return servicePayloadSpecificAttributes;
  }

  public void setServicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
    this.servicePayloadSpecificAttributes = servicePayloadSpecificAttributes;
  }

  public PerformanceJob creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the Performance Job was created.
   * @return creationDate
  */
  @NotNull @Valid 
  @Schema(name = "creationDate", description = "Date when the Performance Job was created.", required = true)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public PerformanceJob href(URI href) {
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

  public PerformanceJob id(String id) {
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

  public PerformanceJob lastModifiedDate(OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    return this;
  }

  /**
   * Date when the job was last modified.
   * @return lastModifiedDate
  */
  @Valid 
  @Schema(name = "lastModifiedDate", description = "Date when the job was last modified.", required = false)
  public OffsetDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public PerformanceJob rejectionReason(String rejectionReason) {
    this.rejectionReason = rejectionReason;
    return this;
  }

  /**
   * Reason in case creation request was rejected.
   * @return rejectionReason
  */
  
  @Schema(name = "rejectionReason", description = "Reason in case creation request was rejected.", required = false)
  public String getRejectionReason() {
    return rejectionReason;
  }

  public void setRejectionReason(String rejectionReason) {
    this.rejectionReason = rejectionReason;
  }

  public PerformanceJob state(PerformanceJobStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @NotNull @Valid 
  @Schema(name = "state", required = true)
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
    PerformanceJob performanceJob = (PerformanceJob) o;
    return Objects.equals(this.buyerJobId, performanceJob.buyerJobId) &&
        Objects.equals(this.consumingApplicationId, performanceJob.consumingApplicationId) &&
        Objects.equals(this.description, performanceJob.description) &&
        Objects.equals(this.fileTransferData, performanceJob.fileTransferData) &&
        Objects.equals(this.performanceProfile, performanceJob.performanceProfile) &&
        Objects.equals(this.producingApplicationId, performanceJob.producingApplicationId) &&
        Objects.equals(this.scheduleDefinition, performanceJob.scheduleDefinition) &&
        Objects.equals(this.servicePayloadSpecificAttributes, performanceJob.servicePayloadSpecificAttributes) &&
        Objects.equals(this.creationDate, performanceJob.creationDate) &&
        Objects.equals(this.href, performanceJob.href) &&
        Objects.equals(this.id, performanceJob.id) &&
        Objects.equals(this.lastModifiedDate, performanceJob.lastModifiedDate) &&
        Objects.equals(this.rejectionReason, performanceJob.rejectionReason) &&
        Objects.equals(this.state, performanceJob.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyerJobId, consumingApplicationId, description, fileTransferData, performanceProfile, producingApplicationId, scheduleDefinition, servicePayloadSpecificAttributes, creationDate, href, id, lastModifiedDate, rejectionReason, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceJob {\n");
    sb.append("    buyerJobId: ").append(toIndentedString(buyerJobId)).append("\n");
    sb.append("    consumingApplicationId: ").append(toIndentedString(consumingApplicationId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    fileTransferData: ").append(toIndentedString(fileTransferData)).append("\n");
    sb.append("    performanceProfile: ").append(toIndentedString(performanceProfile)).append("\n");
    sb.append("    producingApplicationId: ").append(toIndentedString(producingApplicationId)).append("\n");
    sb.append("    scheduleDefinition: ").append(toIndentedString(scheduleDefinition)).append("\n");
    sb.append("    servicePayloadSpecificAttributes: ").append(toIndentedString(servicePayloadSpecificAttributes)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastModifiedDate: ").append(toIndentedString(lastModifiedDate)).append("\n");
    sb.append("    rejectionReason: ").append(toIndentedString(rejectionReason)).append("\n");
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

