package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A reference to a Performance Report resource
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "PerformanceReportRef", description = "A reference to a Performance Report resource")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PerformanceReportRef {

  @JsonProperty("href")
  private URI href;

  @JsonProperty("id")
  private String id;

  public PerformanceReportRef href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * Hyperlink to the referenced Performance Report
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "Hyperlink to the referenced Performance Report", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public PerformanceReportRef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the referenced Performance Report
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Identifier of the referenced Performance Report", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PerformanceReportRef performanceReportRef = (PerformanceReportRef) o;
    return Objects.equals(this.href, performanceReportRef.href) &&
        Objects.equals(this.id, performanceReportRef.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PerformanceReportRef {\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

