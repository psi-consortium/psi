package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Lack or inadequate strength or ability.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "Disability", description = "Lack or inadequate strength or ability.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Disability {

  @JsonProperty("disabilityCode")
  private String disabilityCode;

  @JsonProperty("disabilityName")
  private String disabilityName;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  public Disability disabilityCode(String disabilityCode) {
    this.disabilityCode = disabilityCode;
    return this;
  }

  /**
   * Code of the disability
   * @return disabilityCode
  */
  
  @Schema(name = "disabilityCode", description = "Code of the disability", required = false)
  public String getDisabilityCode() {
    return disabilityCode;
  }

  public void setDisabilityCode(String disabilityCode) {
    this.disabilityCode = disabilityCode;
  }

  public Disability disabilityName(String disabilityName) {
    this.disabilityName = disabilityName;
    return this;
  }

  /**
   * Name of the disability
   * @return disabilityName
  */
  
  @Schema(name = "disabilityName", description = "Name of the disability", required = false)
  public String getDisabilityName() {
    return disabilityName;
  }

  public void setDisabilityName(String disabilityName) {
    this.disabilityName = disabilityName;
  }

  public Disability validFor(TimePeriod validFor) {
    this.validFor = validFor;
    return this;
  }

  /**
   * Get validFor
   * @return validFor
  */
  @Valid 
  @Schema(name = "validFor", required = false)
  public TimePeriod getValidFor() {
    return validFor;
  }

  public void setValidFor(TimePeriod validFor) {
    this.validFor = validFor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Disability disability = (Disability) o;
    return Objects.equals(this.disabilityCode, disability.disabilityCode) &&
        Objects.equals(this.disabilityName, disability.disabilityName) &&
        Objects.equals(this.validFor, disability.validFor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(disabilityCode, disabilityName, validFor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Disability {\n");
    sb.append("    disabilityCode: ").append(toIndentedString(disabilityCode)).append("\n");
    sb.append("    disabilityName: ").append(toIndentedString(disabilityName)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
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

