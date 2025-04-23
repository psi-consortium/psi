package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ResourceGraphSpecificationRef;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Describes link between resource graph specifications.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ResourceGraphSpecificationRelationship", description = "Describes link between resource graph specifications.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ResourceGraphSpecificationRelationship {

  /**
   * Semantic of the relationship.
   */
  public enum RelationshipTypeEnum {
    ADJACENCY("adjacency"),
    
    CONNECTIVITY("connectivity");

    private String value;

    RelationshipTypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RelationshipTypeEnum fromValue(String value) {
      for (RelationshipTypeEnum b : RelationshipTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("relationshipType")
  private RelationshipTypeEnum relationshipType;

  @JsonProperty("resourceGraph")
  private ResourceGraphSpecificationRef resourceGraph;

  public ResourceGraphSpecificationRelationship relationshipType(RelationshipTypeEnum relationshipType) {
    this.relationshipType = relationshipType;
    return this;
  }

  /**
   * Semantic of the relationship.
   * @return relationshipType
  */
  
  @Schema(name = "relationshipType", description = "Semantic of the relationship.", required = false)
  public RelationshipTypeEnum getRelationshipType() {
    return relationshipType;
  }

  public void setRelationshipType(RelationshipTypeEnum relationshipType) {
    this.relationshipType = relationshipType;
  }

  public ResourceGraphSpecificationRelationship resourceGraph(ResourceGraphSpecificationRef resourceGraph) {
    this.resourceGraph = resourceGraph;
    return this;
  }

  /**
   * Get resourceGraph
   * @return resourceGraph
  */
  @Valid 
  @Schema(name = "resourceGraph", required = false)
  public ResourceGraphSpecificationRef getResourceGraph() {
    return resourceGraph;
  }

  public void setResourceGraph(ResourceGraphSpecificationRef resourceGraph) {
    this.resourceGraph = resourceGraph;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceGraphSpecificationRelationship resourceGraphSpecificationRelationship = (ResourceGraphSpecificationRelationship) o;
    return Objects.equals(this.relationshipType, resourceGraphSpecificationRelationship.relationshipType) &&
        Objects.equals(this.resourceGraph, resourceGraphSpecificationRelationship.resourceGraph);
  }

  @Override
  public int hashCode() {
    return Objects.hash(relationshipType, resourceGraph);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceGraphSpecificationRelationship {\n");
    sb.append("    relationshipType: ").append(toIndentedString(relationshipType)).append("\n");
    sb.append("    resourceGraph: ").append(toIndentedString(resourceGraph)).append("\n");
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

