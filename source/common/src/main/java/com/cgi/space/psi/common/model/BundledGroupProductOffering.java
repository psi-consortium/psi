package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.BundledGroupProductOfferingOption;
import com.cgi.space.psi.common.model.BundledProductOffering;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A group of product offerings that can be chosen for instantiation of children of the parent product offering, for example a list of channels for selection under a TV offering. Sometimes known as Selection Group. The group can also hierarchically contain other groups
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "BundledGroupProductOffering", description = "A group of product offerings that can be chosen for instantiation of children of the parent product offering, for example a list of channels for selection under a TV offering. Sometimes known as Selection Group. The group can also hierarchically contain other groups")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class BundledGroupProductOffering {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("bundledProductOffering")
  @Valid
  private List<BundledProductOffering> bundledProductOffering = null;

  @JsonProperty("bundledGroupProductOffering")
  @Valid
  private List<BundledGroupProductOffering> bundledGroupProductOffering = null;

  @JsonProperty("bundledGroupProductOfferingOption")
  private BundledGroupProductOfferingOption bundledGroupProductOfferingOption;

  public BundledGroupProductOffering id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Locally unique identifier of the group, useful in case the parent product offering or group includes multiple groups.
   * @return id
  */
  
  @Schema(name = "id", example = "1", description = "Locally unique identifier of the group, useful in case the parent product offering or group includes multiple groups.", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BundledGroupProductOffering name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the group of child offerings. Required to distinguish several choice groups.
   * @return name
  */
  
  @Schema(name = "name", example = "TV Channels", description = "The name of the group of child offerings. Required to distinguish several choice groups.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BundledGroupProductOffering bundledProductOffering(List<BundledProductOffering> bundledProductOffering) {
    this.bundledProductOffering = bundledProductOffering;
    return this;
  }

  public BundledGroupProductOffering addBundledProductOfferingItem(BundledProductOffering bundledProductOfferingItem) {
    if (this.bundledProductOffering == null) {
      this.bundledProductOffering = new ArrayList<>();
    }
    this.bundledProductOffering.add(bundledProductOfferingItem);
    return this;
  }

  /**
   * Child offerings, from which instances can be created as direct or hierarchically indirect children of the parent offering.
   * @return bundledProductOffering
  */
  @Valid 
  @Schema(name = "bundledProductOffering", description = "Child offerings, from which instances can be created as direct or hierarchically indirect children of the parent offering.", required = false)
  public List<BundledProductOffering> getBundledProductOffering() {
    return bundledProductOffering;
  }

  public void setBundledProductOffering(List<BundledProductOffering> bundledProductOffering) {
    this.bundledProductOffering = bundledProductOffering;
  }

  public BundledGroupProductOffering bundledGroupProductOffering(List<BundledGroupProductOffering> bundledGroupProductOffering) {
    this.bundledGroupProductOffering = bundledGroupProductOffering;
    return this;
  }

  public BundledGroupProductOffering addBundledGroupProductOfferingItem(BundledGroupProductOffering bundledGroupProductOfferingItem) {
    if (this.bundledGroupProductOffering == null) {
      this.bundledGroupProductOffering = new ArrayList<>();
    }
    this.bundledGroupProductOffering.add(bundledGroupProductOfferingItem);
    return this;
  }

  /**
   * Child groups of product offerings, to enable hierarchical sub-grouping.
   * @return bundledGroupProductOffering
  */
  @Valid 
  @Schema(name = "bundledGroupProductOffering", description = "Child groups of product offerings, to enable hierarchical sub-grouping.", required = false)
  public List<BundledGroupProductOffering> getBundledGroupProductOffering() {
    return bundledGroupProductOffering;
  }

  public void setBundledGroupProductOffering(List<BundledGroupProductOffering> bundledGroupProductOffering) {
    this.bundledGroupProductOffering = bundledGroupProductOffering;
  }

  public BundledGroupProductOffering bundledGroupProductOfferingOption(BundledGroupProductOfferingOption bundledGroupProductOfferingOption) {
    this.bundledGroupProductOfferingOption = bundledGroupProductOfferingOption;
    return this;
  }

  /**
   * Get bundledGroupProductOfferingOption
   * @return bundledGroupProductOfferingOption
  */
  @Valid 
  @Schema(name = "bundledGroupProductOfferingOption", required = false)
  public BundledGroupProductOfferingOption getBundledGroupProductOfferingOption() {
    return bundledGroupProductOfferingOption;
  }

  public void setBundledGroupProductOfferingOption(BundledGroupProductOfferingOption bundledGroupProductOfferingOption) {
    this.bundledGroupProductOfferingOption = bundledGroupProductOfferingOption;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BundledGroupProductOffering bundledGroupProductOffering = (BundledGroupProductOffering) o;
    return Objects.equals(this.id, bundledGroupProductOffering.id) &&
        Objects.equals(this.name, bundledGroupProductOffering.name) &&
        Objects.equals(this.bundledProductOffering, bundledGroupProductOffering.bundledProductOffering) &&
        Objects.equals(this.bundledGroupProductOffering, bundledGroupProductOffering.bundledGroupProductOffering) &&
        Objects.equals(this.bundledGroupProductOfferingOption, bundledGroupProductOffering.bundledGroupProductOfferingOption);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, bundledProductOffering, bundledGroupProductOffering, bundledGroupProductOfferingOption);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BundledGroupProductOffering {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    bundledProductOffering: ").append(toIndentedString(bundledProductOffering)).append("\n");
    sb.append("    bundledGroupProductOffering: ").append(toIndentedString(bundledGroupProductOffering)).append("\n");
    sb.append("    bundledGroupProductOfferingOption: ").append(toIndentedString(bundledGroupProductOfferingOption)).append("\n");
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

