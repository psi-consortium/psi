package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.FileTransferData;
import com.cgi.space.psi.common.model.PerformanceProfileValue;
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
 * A Performance Monitoring Job specifies the performance monitoring objectives specific to each subject of monitoring which could be an  ordered pair (i.e., two UNIs) or an entity (i.e., port).
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "PerformanceJob_Common", description = "A Performance Monitoring Job specifies the performance monitoring objectives specific to each subject of monitoring which could be an  ordered pair (i.e., two UNIs) or an entity (i.e., port).")
@JsonTypeName("PerformanceJob_Common")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceJobCommon {

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

  public PerformanceJobCommon buyerJobId(String buyerJobId) {
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

  public PerformanceJobCommon consumingApplicationId(String consumingApplicationId) {
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

  public PerformanceJobCommon description(String description) {
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

  public PerformanceJobCommon fileTransferData(FileTransferData fileTransferData) {
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

  public PerformanceJobCommon performanceProfile(PerformanceProfileValue performanceProfile) {
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

  public PerformanceJobCommon producingApplicationId(String producingApplicationId) {
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

  public PerformanceJobCommon scheduleDefinition(ScheduleDefinition scheduleDefinition) {
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

  public PerformanceJobCommon servicePayloadSpecificAttributes(ServicePayloadSpecificAttributes servicePayloadSpecificAttributes) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PerformanceJobCommon performanceJobCommon = (PerformanceJobCommon) o;
    return Objects.equals(this.buyerJobId, performanceJobCommon.buyerJobId) &&
        Objects.equals(this.consumingApplicationId, performanceJobCommon.consumingApplicationId) &&
        Objects.equals(this.description, performanceJobCommon.description) &&
        Objects.equals(this.fileTransferData, performanceJobCommon.fileTransferData) &&
        Objects.equals(this.performanceProfile, performanceJobCommon.performanceProfile) &&
        Objects.equals(this.producingApplicationId, performanceJobCommon.producingApplicationId) &&
        Objects.equals(this.scheduleDefinition, performanceJobCommon.scheduleDefinition) &&
        Objects.equals(this.servicePayloadSpecificAttributes, performanceJobCommon.servicePayloadSpecificAttributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyerJobId, consumingApplicationId, description, fileTransferData, performanceProfile, producingApplicationId, scheduleDefinition, servicePayloadSpecificAttributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceJobCommon {\n");
    sb.append("    buyerJobId: ").append(toIndentedString(buyerJobId)).append("\n");
    sb.append("    consumingApplicationId: ").append(toIndentedString(consumingApplicationId)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    fileTransferData: ").append(toIndentedString(fileTransferData)).append("\n");
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

