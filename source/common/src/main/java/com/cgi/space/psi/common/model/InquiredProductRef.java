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
 * A reference to an InquiredProduct.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "InquiredProductRef", description = "A reference to an InquiredProduct.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InquiredProductRef {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  public InquiredProductRef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of an InquiredProduct.
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier of an InquiredProduct.", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public InquiredProductRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Optional: Name of the InquiredProduct.
   * @return name
  */
  
  @Schema(name = "name", description = "Optional: Name of the InquiredProduct.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InquiredProductRef inquiredProductRef = (InquiredProductRef) o;
    return Objects.equals(this.id, inquiredProductRef.id) &&
        Objects.equals(this.name, inquiredProductRef.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InquiredProductRef {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

