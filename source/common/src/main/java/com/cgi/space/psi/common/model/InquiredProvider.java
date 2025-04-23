package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.InquiryStateType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.net.URI;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A provider to which the customer inquiry will be sent.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "InquiredProvider", description = "A provider to which the customer inquiry will be sent.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InquiredProvider {

  @JsonProperty("id")
  private String id;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("name")
  private String name;

  @JsonProperty("state")
  private InquiryStateType state;

  @JsonProperty("actualResponseTime")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime actualResponseTime;

  public InquiredProvider id(String id) {
    this.id = id;
    return this;
  }

  /**
   * unique identifier
   * @return id
  */
  
  @Schema(name = "id", description = "unique identifier", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public InquiredProvider href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * Hyperlink reference
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "Hyperlink reference", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public InquiredProvider name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the related provider.
   * @return name
  */
  
  @Schema(name = "name", description = "Name of the related provider.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public InquiredProvider state(InquiryStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @Valid 
  @Schema(name = "state", required = false)
  public InquiryStateType getState() {
    return state;
  }

  public void setState(InquiryStateType state) {
    this.state = state;
  }

  public InquiredProvider actualResponseTime(OffsetDateTime actualResponseTime) {
    this.actualResponseTime = actualResponseTime;
    return this;
  }

  /**
   * The actual response time of the provider for the inquiry
   * @return actualResponseTime
  */
  @Valid 
  @Schema(name = "actualResponseTime", example = "1985-04-12T23:20:50.520Z", description = "The actual response time of the provider for the inquiry", required = false)
  public OffsetDateTime getActualResponseTime() {
    return actualResponseTime;
  }

  public void setActualResponseTime(OffsetDateTime actualResponseTime) {
    this.actualResponseTime = actualResponseTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InquiredProvider inquiredProvider = (InquiredProvider) o;
    return Objects.equals(this.id, inquiredProvider.id) &&
        Objects.equals(this.href, inquiredProvider.href) &&
        Objects.equals(this.name, inquiredProvider.name) &&
        Objects.equals(this.state, inquiredProvider.state) &&
        Objects.equals(this.actualResponseTime, inquiredProvider.actualResponseTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, name, state, actualResponseTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InquiredProvider {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    actualResponseTime: ").append(toIndentedString(actualResponseTime)).append("\n");
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

