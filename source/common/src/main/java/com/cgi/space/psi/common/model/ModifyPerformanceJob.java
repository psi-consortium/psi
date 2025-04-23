package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.FileTransferData;
import com.cgi.space.psi.common.model.ModifyPerformanceJobProfileValue;
import com.cgi.space.psi.common.model.PerformanceJobProcessStateType;
import com.cgi.space.psi.common.model.PerformanceJobRef;
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
 * ModifyPerformanceJob
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ModifyPerformanceJob {

  @JsonProperty("buyerJobId")
  private String buyerJobId;

  @JsonProperty("consumingApplicationId")
  private String consumingApplicationId;

  @JsonProperty("description")
  private String description;

  @JsonProperty("fileTransferData")
  private FileTransferData fileTransferData;

  @JsonProperty("modificationReason")
  private String modificationReason;

  @JsonProperty("performanceJob")
  private PerformanceJobRef performanceJob;

  @JsonProperty("performanceProfile")
  private ModifyPerformanceJobProfileValue performanceProfile;

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

  @JsonProperty("modificationDeniedReason")
  private String modificationDeniedReason;

  @JsonProperty("state")
  private PerformanceJobProcessStateType state;

  public ModifyPerformanceJob buyerJobId(String buyerJobId) {
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

  public ModifyPerformanceJob consumingApplicationId(String consumingApplicationId) {
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

  public ModifyPerformanceJob description(String description) {
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

  public ModifyPerformanceJob fileTransferData(FileTransferData fileTransferData) {
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

  public ModifyPerformanceJob modificationReason(String modificationReason) {
    this.modificationReason = modificationReason;
    return this;
  }

  /**
   * An optional attribute that allows the Buyer/Client to provide additional detail to the Seller/Server on the reason for modifying Performance Job.
   * @return modificationReason
  */
  
  @Schema(name = "modificationReason", description = "An optional attribute that allows the Buyer/Client to provide additional detail to the Seller/Server on the reason for modifying Performance Job.", required = false)
  public String getModificationReason() {
    return modificationReason;
  }

  public void setModificationReason(String modificationReason) {
    this.modificationReason = modificationReason;
  }

  public ModifyPerformanceJob performanceJob(PerformanceJobRef performanceJob) {
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

  public ModifyPerformanceJob performanceProfile(ModifyPerformanceJobProfileValue performanceProfile) {
    this.performanceProfile = performanceProfile;
    return this;
  }

  /**
   * Get performanceProfile
   * @return performanceProfile
  */
  @Valid 
  @Schema(name = "performanceProfile", required = false)
  public ModifyPerformanceJobProfileValue getPerformanceProfile() {
    return performanceProfile;
  }

  public void setPerformanceProfile(ModifyPerformanceJobProfileValue performanceProfile) {
    this.performanceProfile = performanceProfile;
  }

  public ModifyPerformanceJob producingApplicationId(String producingApplicationId) {
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

  public ModifyPerformanceJob scheduleDefinition(ScheduleDefinition scheduleDefinition) {
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

  public ModifyPerformanceJob servicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
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

  public ModifyPerformanceJob creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the Modify Performance Job was created.
   * @return creationDate
  */
  @NotNull @Valid 
  @Schema(name = "creationDate", description = "Date when the Modify Performance Job was created.", required = true)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public ModifyPerformanceJob href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * Hyperlink to the Modify Performance Job entity
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "Hyperlink to the Modify Performance Job entity", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public ModifyPerformanceJob id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for the Modify Performance Job that is generated by the Seller/Server when the Modify Performance Job request  `state` is set to `acknowledged`
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier for the Modify Performance Job that is generated by the Seller/Server when the Modify Performance Job request  `state` is set to `acknowledged`", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ModifyPerformanceJob modificationDeniedReason(String modificationDeniedReason) {
    this.modificationDeniedReason = modificationDeniedReason;
    return this;
  }

  /**
   * If the Modify Performance Job request is denied by the  Seller/Server, the Seller/Server provides a reason to the Buyer/Client using this attribute.
   * @return modificationDeniedReason
  */
  
  @Schema(name = "modificationDeniedReason", description = "If the Modify Performance Job request is denied by the  Seller/Server, the Seller/Server provides a reason to the Buyer/Client using this attribute.", required = false)
  public String getModificationDeniedReason() {
    return modificationDeniedReason;
  }

  public void setModificationDeniedReason(String modificationDeniedReason) {
    this.modificationDeniedReason = modificationDeniedReason;
  }

  public ModifyPerformanceJob state(PerformanceJobProcessStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @NotNull @Valid 
  @Schema(name = "state", required = true)
  public PerformanceJobProcessStateType getState() {
    return state;
  }

  public void setState(PerformanceJobProcessStateType state) {
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
    ModifyPerformanceJob modifyPerformanceJob = (ModifyPerformanceJob) o;
    return Objects.equals(this.buyerJobId, modifyPerformanceJob.buyerJobId) &&
        Objects.equals(this.consumingApplicationId, modifyPerformanceJob.consumingApplicationId) &&
        Objects.equals(this.description, modifyPerformanceJob.description) &&
        Objects.equals(this.fileTransferData, modifyPerformanceJob.fileTransferData) &&
        Objects.equals(this.modificationReason, modifyPerformanceJob.modificationReason) &&
        Objects.equals(this.performanceJob, modifyPerformanceJob.performanceJob) &&
        Objects.equals(this.performanceProfile, modifyPerformanceJob.performanceProfile) &&
        Objects.equals(this.producingApplicationId, modifyPerformanceJob.producingApplicationId) &&
        Objects.equals(this.scheduleDefinition, modifyPerformanceJob.scheduleDefinition) &&
        Objects.equals(this.servicePayloadSpecificAttributes, modifyPerformanceJob.servicePayloadSpecificAttributes) &&
        Objects.equals(this.creationDate, modifyPerformanceJob.creationDate) &&
        Objects.equals(this.href, modifyPerformanceJob.href) &&
        Objects.equals(this.id, modifyPerformanceJob.id) &&
        Objects.equals(this.modificationDeniedReason, modifyPerformanceJob.modificationDeniedReason) &&
        Objects.equals(this.state, modifyPerformanceJob.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyerJobId, consumingApplicationId, description, fileTransferData, modificationReason, performanceJob, performanceProfile, producingApplicationId, scheduleDefinition, servicePayloadSpecificAttributes, creationDate, href, id, modificationDeniedReason, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModifyPerformanceJob {\n");
    sb.append("    buyerJobId: ").append(toIndentedString(buyerJobId)).append("\n");
    sb.append("    consumingApplicationId: ").append(toIndentedString(consumingApplicationId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    fileTransferData: ").append(toIndentedString(fileTransferData)).append("\n");
    sb.append("    modificationReason: ").append(toIndentedString(modificationReason)).append("\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
    sb.append("    performanceProfile: ").append(toIndentedString(performanceProfile)).append("\n");
    sb.append("    producingApplicationId: ").append(toIndentedString(producingApplicationId)).append("\n");
    sb.append("    scheduleDefinition: ").append(toIndentedString(scheduleDefinition)).append("\n");
    sb.append("    servicePayloadSpecificAttributes: ").append(toIndentedString(servicePayloadSpecificAttributes)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    modificationDeniedReason: ").append(toIndentedString(modificationDeniedReason)).append("\n");
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

