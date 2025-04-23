package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ServicePayloadSpecificAttributes is used as an extension point for  MEF-specific service performance monitoring configuration. It includes a definition of service/entity and applicable performance monitoring objectives. The &#x60;@type&#x60; attribute is used as a discriminator.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ServicePayloadSpecificAttributes", description = "ServicePayloadSpecificAttributes is used as an extension point for  MEF-specific service performance monitoring configuration. It includes a definition of service/entity and applicable performance monitoring objectives. The `@type` attribute is used as a discriminator.")

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ServicePayloadSpecificAttributes {

  @JsonProperty("@type")
  private String atType;

  public ServicePayloadSpecificAttributes atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * Uniquely identifies the type of performance monitoring configuration that specifies PM objectives. In the case of MEF services, this is the URN provided in the performance monitoring configuration specification. The named type must be a subclass of ServicePayloadSpecificAttributes.
   * @return atType
  */
  @NotNull 
  @Schema(name = "@type", description = "Uniquely identifies the type of performance monitoring configuration that specifies PM objectives. In the case of MEF services, this is the URN provided in the performance monitoring configuration specification. The named type must be a subclass of ServicePayloadSpecificAttributes.", required = true)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServicePayloadSpecificAttributes servicePayloadSpecificAttributes = (ServicePayloadSpecificAttributes) o;
    return Objects.equals(this.atType, servicePayloadSpecificAttributes.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServicePayloadSpecificAttributes {\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
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

