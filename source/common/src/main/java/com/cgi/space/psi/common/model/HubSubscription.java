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
 * A Hub is used to subscribe to an event notification
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "HubSubscription", description = "A Hub is used to subscribe to an event notification")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class HubSubscription {

  @JsonProperty("id")
  private String id;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("callback")
  private URI callback;

  @JsonProperty("query")
  private String query;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public HubSubscription id(String id) {
    this.id = id;
    return this;
  }

  /**
   * The unique-id for your subscription - referenced when updating or deleting a subscription
   * @return id
  */
  @NotNull 
  @Schema(name = "id", example = "4aafacbd-11ff-4dc8-b445-305f2215715f", description = "The unique-id for your subscription - referenced when updating or deleting a subscription", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public HubSubscription href(URI href) {
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

  public HubSubscription callback(URI callback) {
    this.callback = callback;
    return this;
  }

  /**
   * The URI that will be POSTed to when a notification is triggered
   * @return callback
  */
  @NotNull @Valid 
  @Schema(name = "callback", example = "http://host/resource/id/listener", description = "The URI that will be POSTed to when a notification is triggered", required = true)
  public URI getCallback() {
    return callback;
  }

  public void setCallback(URI callback) {
    this.callback = callback;
  }

  public HubSubscription query(String query) {
    this.query = query;
    return this;
  }

  /**
   * This is a query string used to filter notifications in the context of the notifier
   * @return query
  */
  
  @Schema(name = "query", example = "status=active", description = "This is a query string used to filter notifications in the context of the notifier", required = false)
  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public HubSubscription atBaseType(String atBaseType) {
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

  public HubSubscription atSchemaLocation(URI atSchemaLocation) {
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

  public HubSubscription atType(String atType) {
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
    HubSubscription hubSubscription = (HubSubscription) o;
    return Objects.equals(this.id, hubSubscription.id) &&
        Objects.equals(this.href, hubSubscription.href) &&
        Objects.equals(this.callback, hubSubscription.callback) &&
        Objects.equals(this.query, hubSubscription.query) &&
        Objects.equals(this.atBaseType, hubSubscription.atBaseType) &&
        Objects.equals(this.atSchemaLocation, hubSubscription.atSchemaLocation) &&
        Objects.equals(this.atType, hubSubscription.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, callback, query, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HubSubscription {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    callback: ").append(toIndentedString(callback)).append("\n");
    sb.append("    query: ").append(toIndentedString(query)).append("\n");
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

