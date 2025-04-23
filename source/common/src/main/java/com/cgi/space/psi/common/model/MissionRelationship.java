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
 * MissionRelationship
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MissionRelationship {

  @JsonProperty("id")
  private String id;

  @JsonProperty("href")
  private String href;

  @JsonProperty("name")
  private String name;

  @JsonProperty("relationshipType")
  private String relationshipType;

  public MissionRelationship id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the Mission
   * @return id
  */
  
  @Schema(name = "id", description = "Unique identifier of the Mission", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MissionRelationship href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the Mission
   * @return href
  */
  
  @Schema(name = "href", description = "Reference of the Mission", required = false)
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public MissionRelationship name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the Mission
   * @return name
  */
  
  @Schema(name = "name", description = "Name of the Mission", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MissionRelationship relationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
    return this;
  }

  /**
   * Type of the Mission relationship can be isChild, dependent etc...
   * @return relationshipType
  */
  
  @Schema(name = "relationshipType", description = "Type of the Mission relationship can be isChild, dependent etc...", required = false)
  public String getRelationshipType() {
    return relationshipType;
  }

  public void setRelationshipType(String relationshipType) {
    this.relationshipType = relationshipType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MissionRelationship missionRelationship = (MissionRelationship) o;
    return Objects.equals(this.id, missionRelationship.id) &&
        Objects.equals(this.href, missionRelationship.href) &&
        Objects.equals(this.name, missionRelationship.name) &&
        Objects.equals(this.relationshipType, missionRelationship.relationshipType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, name, relationshipType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MissionRelationship {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    relationshipType: ").append(toIndentedString(relationshipType)).append("\n");
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

