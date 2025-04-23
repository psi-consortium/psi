package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.FileTransferData;
import com.cgi.space.psi.common.model.ModifyPerformanceJobProfileValue;
import com.cgi.space.psi.common.model.PerformanceJobRef;
import com.cgi.space.psi.common.model.ScheduleDefinition;
import com.cgi.space.psi.common.model.ServicePayloadSpecificAttributes;
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
 * ModifyPerformanceJobCreate
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonTypeName("ModifyPerformanceJob_Create")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ModifyPerformanceJobCreate {

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

  public ModifyPerformanceJobCreate buyerJobId(String buyerJobId) {
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

  public ModifyPerformanceJobCreate consumingApplicationId(String consumingApplicationId) {
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

  public ModifyPerformanceJobCreate description(String description) {
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

  public ModifyPerformanceJobCreate fileTransferData(FileTransferData fileTransferData) {
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

  public ModifyPerformanceJobCreate modificationReason(String modificationReason) {
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

  public ModifyPerformanceJobCreate performanceJob(PerformanceJobRef performanceJob) {
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

  public ModifyPerformanceJobCreate performanceProfile(ModifyPerformanceJobProfileValue performanceProfile) {
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

  public ModifyPerformanceJobCreate producingApplicationId(String producingApplicationId) {
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

  public ModifyPerformanceJobCreate scheduleDefinition(ScheduleDefinition scheduleDefinition) {
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

  public ModifyPerformanceJobCreate servicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModifyPerformanceJobCreate modifyPerformanceJobCreate = (ModifyPerformanceJobCreate) o;
    return Objects.equals(this.buyerJobId, modifyPerformanceJobCreate.buyerJobId) &&
        Objects.equals(this.consumingApplicationId, modifyPerformanceJobCreate.consumingApplicationId) &&
        Objects.equals(this.description, modifyPerformanceJobCreate.description) &&
        Objects.equals(this.fileTransferData, modifyPerformanceJobCreate.fileTransferData) &&
        Objects.equals(this.modificationReason, modifyPerformanceJobCreate.modificationReason) &&
        Objects.equals(this.performanceJob, modifyPerformanceJobCreate.performanceJob) &&
        Objects.equals(this.performanceProfile, modifyPerformanceJobCreate.performanceProfile) &&
        Objects.equals(this.producingApplicationId, modifyPerformanceJobCreate.producingApplicationId) &&
        Objects.equals(this.scheduleDefinition, modifyPerformanceJobCreate.scheduleDefinition) &&
        Objects.equals(this.servicePayloadSpecificAttributes, modifyPerformanceJobCreate.servicePayloadSpecificAttributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyerJobId, consumingApplicationId, description, fileTransferData, modificationReason, performanceJob, performanceProfile, producingApplicationId, scheduleDefinition, servicePayloadSpecificAttributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModifyPerformanceJobCreate {\n");
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

