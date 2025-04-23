package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.PerformanceJobStateType;
import com.cgi.space.psi.common.model.PerformanceProfileValue;
import com.cgi.space.psi.common.model.ScheduleDefinition;
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
 * This class represents a single list item for the response of the &#x60;listPerformanceJob&#x60; operation.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "PerformanceJob_Find", description = "This class represents a single list item for the response of the `listPerformanceJob` operation.")
@JsonTypeName("PerformanceJob_Find")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceJobFind {

  @JsonProperty("buyerJobId")
  private String buyerJobId;

  @JsonProperty("consumingApplicationId")
  private String consumingApplicationId;

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("description")
  private String description;

  @JsonProperty("id")
  private String id;

  @JsonProperty("performanceProfile")
  private PerformanceProfileValue performanceProfile;

  @JsonProperty("producingApplicationId")
  private String producingApplicationId;

  @JsonProperty("scheduleDefinition")
  private ScheduleDefinition scheduleDefinition;

  @JsonProperty("state")
  private PerformanceJobStateType state;

  public PerformanceJobFind buyerJobId(String buyerJobId) {
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

  public PerformanceJobFind consumingApplicationId(String consumingApplicationId) {
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

  public PerformanceJobFind creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the job was created.
   * @return creationDate
  */
  @NotNull @Valid 
  @Schema(name = "creationDate", description = "Date when the job was created.", required = true)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public PerformanceJobFind description(String description) {
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

  public PerformanceJobFind id(String id) {
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

  public PerformanceJobFind performanceProfile(PerformanceProfileValue performanceProfile) {
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

  public PerformanceJobFind producingApplicationId(String producingApplicationId) {
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

  public PerformanceJobFind scheduleDefinition(ScheduleDefinition scheduleDefinition) {
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

  public PerformanceJobFind state(PerformanceJobStateType state) {
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
    PerformanceJobFind performanceJobFind = (PerformanceJobFind) o;
    return Objects.equals(this.buyerJobId, performanceJobFind.buyerJobId) &&
        Objects.equals(this.consumingApplicationId, performanceJobFind.consumingApplicationId) &&
        Objects.equals(this.creationDate, performanceJobFind.creationDate) &&
        Objects.equals(this.description, performanceJobFind.description) &&
        Objects.equals(this.id, performanceJobFind.id) &&
        Objects.equals(this.performanceProfile, performanceJobFind.performanceProfile) &&
        Objects.equals(this.producingApplicationId, performanceJobFind.producingApplicationId) &&
        Objects.equals(this.scheduleDefinition, performanceJobFind.scheduleDefinition) &&
        Objects.equals(this.state, performanceJobFind.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyerJobId, consumingApplicationId, creationDate, description, id, performanceProfile, producingApplicationId, scheduleDefinition, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceJobFind {\n");
    sb.append("    buyerJobId: ").append(toIndentedString(buyerJobId)).append("\n");
    sb.append("    consumingApplicationId: ").append(toIndentedString(consumingApplicationId)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    performanceProfile: ").append(toIndentedString(performanceProfile)).append("\n");
    sb.append("    producingApplicationId: ").append(toIndentedString(producingApplicationId)).append("\n");
    sb.append("    scheduleDefinition: ").append(toIndentedString(scheduleDefinition)).append("\n");
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

