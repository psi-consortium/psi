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
 * Is a event channel provided by the Event Streaming API
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "Topic", description = "Is a event channel provided by the Event Streaming API")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Topic {

  @JsonProperty("id")
  private String id;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("contentQuery")
  private String contentQuery;

  @JsonProperty("headerQuery")
  private String headerQuery;

  @JsonProperty("name")
  private String name;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public Topic id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The identifier of the notification.
   * @return id
  */
  
  @Schema(name = "id", description = "The identifier of the notification.", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Topic href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the related entity.
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "Reference of the related entity.", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public Topic contentQuery(String contentQuery) {
    this.contentQuery = contentQuery;
    return this;
  }

  /**
   * is the filter that will be applied on the content of the Event.
   * @return contentQuery
  */
  
  @Schema(name = "contentQuery", description = "is the filter that will be applied on the content of the Event.", required = false)
  public String getContentQuery() {
    return contentQuery;
  }

  public void setContentQuery(String contentQuery) {
    this.contentQuery = contentQuery;
  }

  public Topic headerQuery(String headerQuery) {
    this.headerQuery = headerQuery;
    return this;
  }

  /**
   * is the filter that will be applied on the Event header properties.
   * @return headerQuery
  */
  
  @Schema(name = "headerQuery", description = "is the filter that will be applied on the Event header properties.", required = false)
  public String getHeaderQuery() {
    return headerQuery;
  }

  public void setHeaderQuery(String headerQuery) {
    this.headerQuery = headerQuery;
  }

  public Topic name(String name) {
    this.name = name;
    return this;
  }

  /**
   * use to identify grouping of events, per domain, per event types, per access control-right and so on.
   * @return name
  */
  
  @Schema(name = "name", description = "use to identify grouping of events, per domain, per event types, per access control-right and so on.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Topic atBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
    return this;
  }

  /**
   * When sub-classing, this defines the super-class
   * @return atBaseType
  */
  
  @Schema(name = "@baseType", description = "When sub-classing, this defines the super-class", required = false)
  public String getAtBaseType() {
    return atBaseType;
  }

  public void setAtBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
  }

  public Topic atSchemaLocation(URI atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships
   * @return atSchemaLocation
  */
  @Valid 
  @Schema(name = "@schemaLocation", description = "A URI to a JSON-Schema file that defines additional attributes and relationships", required = false)
  public URI getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(URI atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public Topic atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class Extensible name
   * @return atType
  */
  
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class Extensible name", required = false)
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
    Topic topic = (Topic) o;
    return Objects.equals(this.id, topic.id) &&
        Objects.equals(this.href, topic.href) &&
        Objects.equals(this.contentQuery, topic.contentQuery) &&
        Objects.equals(this.headerQuery, topic.headerQuery) &&
        Objects.equals(this.name, topic.name) &&
        Objects.equals(this.atBaseType, topic.atBaseType) &&
        Objects.equals(this.atSchemaLocation, topic.atSchemaLocation) &&
        Objects.equals(this.atType, topic.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, contentQuery, headerQuery, name, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Topic {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    contentQuery: ").append(toIndentedString(contentQuery)).append("\n");
    sb.append("    headerQuery: ").append(toIndentedString(headerQuery)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
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

