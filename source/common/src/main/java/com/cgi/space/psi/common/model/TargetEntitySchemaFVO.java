package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
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
 * The reference object to the schema and type of target entity which is described by a specification
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "TargetEntitySchema_FVO", description = "The reference object to the schema and type of target entity which is described by a specification")
@JsonTypeName("TargetEntitySchema_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TargetEntitySchemaFVO {

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  public TargetEntitySchemaFVO atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * Class type of the target entity
   * @return atType
  */
  @NotNull 
  @Schema(name = "@type", description = "Class type of the target entity", required = true)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  public TargetEntitySchemaFVO atSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * This field provides a link to the schema describing the target entity
   * @return atSchemaLocation
  */
  @NotNull 
  @Schema(name = "@schemaLocation", description = "This field provides a link to the schema describing the target entity", required = true)
  public String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TargetEntitySchemaFVO targetEntitySchemaFVO = (TargetEntitySchemaFVO) o;
    return Objects.equals(this.atType, targetEntitySchemaFVO.atType) &&
        Objects.equals(this.atSchemaLocation, targetEntitySchemaFVO.atSchemaLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType, atSchemaLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TargetEntitySchemaFVO {\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
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

