package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.net.URI;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * The reference object to the schema and type of target product which is described by product specification
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "TargetProductSchema_MVO", description = "The reference object to the schema and type of target product which is described by product specification")
@JsonTypeName("TargetProductSchema_MVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class TargetProductSchemaMVO {

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  public TargetProductSchemaMVO atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * Class type of the target product
   * @return atType
  */
  
  @Schema(name = "@type", description = "Class type of the target product", required = false)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  public TargetProductSchemaMVO atSchemaLocation(URI atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * This field provides a link to the schema describing the target product
   * @return atSchemaLocation
  */
  @Valid 
  @Schema(name = "@schemaLocation", description = "This field provides a link to the schema describing the target product", required = false)
  public URI getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(URI atSchemaLocation) {
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
    TargetProductSchemaMVO targetProductSchemaMVO = (TargetProductSchemaMVO) o;
    return Objects.equals(this.atType, targetProductSchemaMVO.atType) &&
        Objects.equals(this.atSchemaLocation, targetProductSchemaMVO.atSchemaLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType, atSchemaLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TargetProductSchemaMVO {\n");
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

