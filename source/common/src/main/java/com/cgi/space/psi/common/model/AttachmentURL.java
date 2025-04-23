package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * The AttachmentURL is used to get the PM report.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "AttachmentURL", description = "The AttachmentURL is used to get the PM report.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AttachmentURL {

  @JsonProperty("url")
  private String url;

  public AttachmentURL url(String url) {
    this.url = url;
    return this;
  }

  /**
   * 'Uniform Resource Locator, is a web page address (a subset of  URI).'
   * @return url
  */
  @NotNull 
  @Schema(name = "url", description = "'Uniform Resource Locator, is a web page address (a subset of  URI).'", required = true)
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttachmentURL attachmentURL = (AttachmentURL) o;
    return Objects.equals(this.url, attachmentURL.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttachmentURL {\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

