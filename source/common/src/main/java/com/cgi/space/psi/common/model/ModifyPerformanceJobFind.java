package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.PerformanceJobProcessStateType;
import com.cgi.space.psi.common.model.PerformanceJobRef;
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
 * This class represents a single list item for the response of  &#x60;listModifyPerformanceJob&#x60;
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ModifyPerformanceJob_Find", description = "This class represents a single list item for the response of  `listModifyPerformanceJob`")
@JsonTypeName("ModifyPerformanceJob_Find")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ModifyPerformanceJobFind {

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("id")
  private String id;

  @JsonProperty("performanceJob")
  private PerformanceJobRef performanceJob;

  @JsonProperty("state")
  private PerformanceJobProcessStateType state;

  public ModifyPerformanceJobFind creationDate(OffsetDateTime creationDate) {
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

  public ModifyPerformanceJobFind id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for the Modify Performance Job that is generated by the Seller/Server when the Modify Performance Job request `state` is set to `acknowledged`.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier for the Modify Performance Job that is generated by the Seller/Server when the Modify Performance Job request `state` is set to `acknowledged`.", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ModifyPerformanceJobFind performanceJob(PerformanceJobRef performanceJob) {
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

  public ModifyPerformanceJobFind state(PerformanceJobProcessStateType state) {
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
    ModifyPerformanceJobFind modifyPerformanceJobFind = (ModifyPerformanceJobFind) o;
    return Objects.equals(this.creationDate, modifyPerformanceJobFind.creationDate) &&
        Objects.equals(this.id, modifyPerformanceJobFind.id) &&
        Objects.equals(this.performanceJob, modifyPerformanceJobFind.performanceJob) &&
        Objects.equals(this.state, modifyPerformanceJobFind.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creationDate, id, performanceJob, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ModifyPerformanceJobFind {\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    performanceJob: ").append(toIndentedString(performanceJob)).append("\n");
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

