package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.CalendarPeriodMVO;
import com.cgi.space.psi.common.model.ExternalIdentifierMVO;
import com.cgi.space.psi.common.model.GeographicAddressMVO;
import com.cgi.space.psi.common.model.GeographicLocationMVO;
import com.cgi.space.psi.common.model.GeographicSiteMVO;
import com.cgi.space.psi.common.model.GeographicSiteRelationshipMVO;
import com.cgi.space.psi.common.model.PlaceMVO;
import com.cgi.space.psi.common.model.PlaceRefOrValueMVO;
import com.cgi.space.psi.common.model.RelatedPartyOrPartyRoleMVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.net.URI;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * GeographicSiteMVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = GeographicAddressMVO.class, name = "GeographicAddress"),
  @JsonSubTypes.Type(value = GeographicLocationMVO.class, name = "GeographicLocation"),
  @JsonSubTypes.Type(value = GeographicSiteMVO.class, name = "GeographicSite"),
  @JsonSubTypes.Type(value = PlaceMVO.class, name = "Place")
})

@JsonTypeName("GeographicSite_MVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GeographicSiteMVO extends PlaceMVO implements PlaceRefOrValueMVO {

  @JsonProperty("code")
  private String code;

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("description")
  private String description;

  @JsonProperty("status")
  private String status;

  @JsonProperty("relatedParty")
  @Valid
  private List<RelatedPartyOrPartyRoleMVO> relatedParty = null;

  @JsonProperty("externalIdentifier")
  @Valid
  private List<ExternalIdentifierMVO> externalIdentifier = null;

  @JsonProperty("calendar")
  @Valid
  private List<CalendarPeriodMVO> calendar = null;

  @JsonProperty("place")
  @Valid
  private List<PlaceRefOrValueMVO> place = null;

  @JsonProperty("siteRelationship")
  @Valid
  private List<GeographicSiteRelationshipMVO> siteRelationship = null;

  public GeographicSiteMVO code(String code) {
    this.code = code;
    return this;
  }

  /**
   * A code that may be used for some addressing schemes eg: [ANSI T1.253-1999]
   * @return code
  */
  
  @Schema(name = "code", description = "A code that may be used for some addressing schemes eg: [ANSI T1.253-1999]", required = false)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public GeographicSiteMVO creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date and time when the GeographicSite was created
   * @return creationDate
  */
  @Valid 
  @Schema(name = "creationDate", description = "Date and time when the GeographicSite was created", required = false)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public GeographicSiteMVO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Text describing additional information regarding the site
   * @return description
  */
  
  @Schema(name = "description", description = "Text describing additional information regarding the site", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public GeographicSiteMVO status(String status) {
    this.status = status;
    return this;
  }

  /**
   * The condition of the GeographicSite, such as planned, underConstruction, cancelled, active, inactive, former
   * @return status
  */
  
  @Schema(name = "status", description = "The condition of the GeographicSite, such as planned, underConstruction, cancelled, active, inactive, former", required = false)
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public GeographicSiteMVO relatedParty(List<RelatedPartyOrPartyRoleMVO> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public GeographicSiteMVO addRelatedPartyItem(RelatedPartyOrPartyRoleMVO relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

  /**
   * Get relatedParty
   * @return relatedParty
  */
  @Valid 
  @Schema(name = "relatedParty", required = false)
  public List<RelatedPartyOrPartyRoleMVO> getRelatedParty() {
    return relatedParty;
  }

  public void setRelatedParty(List<RelatedPartyOrPartyRoleMVO> relatedParty) {
    this.relatedParty = relatedParty;
  }

  public GeographicSiteMVO externalIdentifier(List<ExternalIdentifierMVO> externalIdentifier) {
    this.externalIdentifier = externalIdentifier;
    return this;
  }

  public GeographicSiteMVO addExternalIdentifierItem(ExternalIdentifierMVO externalIdentifierItem) {
    if (this.externalIdentifier == null) {
      this.externalIdentifier = new ArrayList<>();
    }
    this.externalIdentifier.add(externalIdentifierItem);
    return this;
  }

  /**
   * Get externalIdentifier
   * @return externalIdentifier
  */
  @Valid 
  @Schema(name = "externalIdentifier", required = false)
  public List<ExternalIdentifierMVO> getExternalIdentifier() {
    return externalIdentifier;
  }

  public void setExternalIdentifier(List<ExternalIdentifierMVO> externalIdentifier) {
    this.externalIdentifier = externalIdentifier;
  }

  public GeographicSiteMVO calendar(List<CalendarPeriodMVO> calendar) {
    this.calendar = calendar;
    return this;
  }

  public GeographicSiteMVO addCalendarItem(CalendarPeriodMVO calendarItem) {
    if (this.calendar == null) {
      this.calendar = new ArrayList<>();
    }
    this.calendar.add(calendarItem);
    return this;
  }

  /**
   * Get calendar
   * @return calendar
  */
  @Valid 
  @Schema(name = "calendar", required = false)
  public List<CalendarPeriodMVO> getCalendar() {
    return calendar;
  }

  public void setCalendar(List<CalendarPeriodMVO> calendar) {
    this.calendar = calendar;
  }

  public GeographicSiteMVO place(List<PlaceRefOrValueMVO> place) {
    this.place = place;
    return this;
  }

  public GeographicSiteMVO addPlaceItem(PlaceRefOrValueMVO placeItem) {
    if (this.place == null) {
      this.place = new ArrayList<>();
    }
    this.place.add(placeItem);
    return this;
  }

  /**
   * Get place
   * @return place
  */
  @Valid 
  @Schema(name = "place", required = false)
  public List<PlaceRefOrValueMVO> getPlace() {
    return place;
  }

  public void setPlace(List<PlaceRefOrValueMVO> place) {
    this.place = place;
  }

  public GeographicSiteMVO siteRelationship(List<GeographicSiteRelationshipMVO> siteRelationship) {
    this.siteRelationship = siteRelationship;
    return this;
  }

  public GeographicSiteMVO addSiteRelationshipItem(GeographicSiteRelationshipMVO siteRelationshipItem) {
    if (this.siteRelationship == null) {
      this.siteRelationship = new ArrayList<>();
    }
    this.siteRelationship.add(siteRelationshipItem);
    return this;
  }

  /**
   * Get siteRelationship
   * @return siteRelationship
  */
  @Valid 
  @Schema(name = "siteRelationship", required = false)
  public List<GeographicSiteRelationshipMVO> getSiteRelationship() {
    return siteRelationship;
  }

  public void setSiteRelationship(List<GeographicSiteRelationshipMVO> siteRelationship) {
    this.siteRelationship = siteRelationship;
  }

  public GeographicSiteMVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public GeographicSiteMVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public GeographicSiteMVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public GeographicSiteMVO name(String name) {
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
    GeographicSiteMVO geographicSiteMVO = (GeographicSiteMVO) o;
    return Objects.equals(this.code, geographicSiteMVO.code) &&
        Objects.equals(this.creationDate, geographicSiteMVO.creationDate) &&
        Objects.equals(this.description, geographicSiteMVO.description) &&
        Objects.equals(this.status, geographicSiteMVO.status) &&
        Objects.equals(this.relatedParty, geographicSiteMVO.relatedParty) &&
        Objects.equals(this.externalIdentifier, geographicSiteMVO.externalIdentifier) &&
        Objects.equals(this.calendar, geographicSiteMVO.calendar) &&
        Objects.equals(this.place, geographicSiteMVO.place) &&
        Objects.equals(this.siteRelationship, geographicSiteMVO.siteRelationship) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, creationDate, description, status, relatedParty, externalIdentifier, calendar, place, siteRelationship, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeographicSiteMVO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
    sb.append("    externalIdentifier: ").append(toIndentedString(externalIdentifier)).append("\n");
    sb.append("    calendar: ").append(toIndentedString(calendar)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    siteRelationship: ").append(toIndentedString(siteRelationship)).append("\n");
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

