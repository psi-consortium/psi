package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.GeographicAddressFVO;
import com.cgi.space.psi.common.model.GeographicLocationFVO;
import com.cgi.space.psi.common.model.GeographicSiteFVO;
import com.cgi.space.psi.common.model.Geometry;
import com.cgi.space.psi.common.model.PlaceFVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
 * GeographicLocationFVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = GeographicAddressFVO.class, name = "GeographicAddress"),
  @JsonSubTypes.Type(value = GeographicLocationFVO.class, name = "GeographicLocation"),
  @JsonSubTypes.Type(value = GeographicSiteFVO.class, name = "GeographicSite"),
  @JsonSubTypes.Type(value = PlaceFVO.class, name = "Place")
})

@JsonTypeName("GeographicLocation_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GeographicLocationFVO extends PlaceFVO implements GeographicLocationRefOrValueFVO, PlaceRefOrValueFVO {

  @JsonProperty("geoJson")
  private Geometry geoJson;

  @JsonProperty("bbox")
  @Valid
  private List<BigDecimal> bbox = null;

  public GeographicLocationFVO geoJson(Geometry geoJson) {
    this.geoJson = geoJson;
    return this;
  }

  /**
   * Get geoJson
   * @return geoJson
  */
  @Valid 
  @Schema(name = "geoJson", required = false)
  public Geometry getGeoJson() {
    return geoJson;
  }

  public void setGeoJson(Geometry geoJson) {
    this.geoJson = geoJson;
  }

  public GeographicLocationFVO bbox(List<BigDecimal> bbox) {
    this.bbox = bbox;
    return this;
  }

  public GeographicLocationFVO addBboxItem(BigDecimal bboxItem) {
    if (this.bbox == null) {
      this.bbox = new ArrayList<>();
    }
    this.bbox.add(bboxItem);
    return this;
  }

  /**
   * A bounding box array that contains the geometry. The axes order follows the axes order of the geometry
   * @return bbox
  */
  @Valid @Size(min = 4) 
  @Schema(name = "bbox", description = "A bounding box array that contains the geometry. The axes order follows the axes order of the geometry", required = false)
  public List<BigDecimal> getBbox() {
    return bbox;
  }

  public void setBbox(List<BigDecimal> bbox) {
    this.bbox = bbox;
  }

  public GeographicLocationFVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public GeographicLocationFVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public GeographicLocationFVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public GeographicLocationFVO id(String id) {
    super.setId(id);
    return this;
  }

  public GeographicLocationFVO name(String name) {
    super.setName(name);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeographicLocationFVO geographicLocationFVO = (GeographicLocationFVO) o;
    return Objects.equals(this.geoJson, geographicLocationFVO.geoJson) &&
        Objects.equals(this.bbox, geographicLocationFVO.bbox) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(geoJson, bbox, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeographicLocationFVO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    geoJson: ").append(toIndentedString(geoJson)).append("\n");
    sb.append("    bbox: ").append(toIndentedString(bbox)).append("\n");
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

