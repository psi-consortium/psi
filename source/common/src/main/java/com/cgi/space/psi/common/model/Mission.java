package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.Characteristic;
import com.cgi.space.psi.common.model.Mission;
import com.cgi.space.psi.common.model.MissionAsset;
import com.cgi.space.psi.common.model.MissionRelationship;
import com.cgi.space.psi.common.model.MissionStatusType;
import com.cgi.space.psi.common.model.PlaceRefOrValue;
import com.cgi.space.psi.common.model.RelatedParty;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Mission
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = Mission.class, name = "Mission")
})

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Mission {

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("category")
  private String category;

  @JsonProperty("timeframe")
  private TimePeriod timeframe;

  @JsonProperty("lastUpdate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastUpdate;

  @JsonProperty("status")
  private MissionStatusType status;

  @JsonProperty("characteristic")
  @Valid
  private List<Characteristic> characteristic = null;

  @JsonProperty("place")
  @Valid
  private List<PlaceRefOrValue> place = null;

  @JsonProperty("asset")
  @Valid
  private List<MissionAsset> asset = null;

  @JsonProperty("missionRelationship")
  @Valid
  private List<MissionRelationship> missionRelationship = null;

  @JsonProperty("relatedParty")
  @Valid
  private List<RelatedParty> relatedParty = null;

  public Mission atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class Extensible name
   * @return atType
  */
  @NotNull 
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class Extensible name", required = true)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  public Mission atBaseType(String atBaseType) {
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

  public Mission atSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships
   * @return atSchemaLocation
  */
  
  @Schema(name = "@schemaLocation", description = "A URI to a JSON-Schema file that defines additional attributes and relationships", required = false)
  public String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public Mission href(URI href) {
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

  public Mission id(String id) {
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

  public Mission name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the mission
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "Name of the mission", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Mission description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the mission
   * @return description
  */
  
  @Schema(name = "description", description = "Description of the mission", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Mission category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Category of the mission like 'Humanitarian Aid', 'Wildfire Fighting' and so on.
   * @return category
  */
  
  @Schema(name = "category", description = "Category of the mission like 'Humanitarian Aid', 'Wildfire Fighting' and so on.", required = false)
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Mission timeframe(TimePeriod timeframe) {
    this.timeframe = timeframe;
    return this;
  }

  /**
   * Get timeframe
   * @return timeframe
  */
  @Valid 
  @Schema(name = "timeframe", required = false)
  public TimePeriod getTimeframe() {
    return timeframe;
  }

  public void setTimeframe(TimePeriod timeframe) {
    this.timeframe = timeframe;
  }

  public Mission lastUpdate(OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
    return this;
  }

  /**
   * Date and time of the last update of this REST resource
   * @return lastUpdate
  */
  @Valid 
  @Schema(name = "lastUpdate", description = "Date and time of the last update of this REST resource", required = false)
  public OffsetDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public Mission status(MissionStatusType status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  */
  @Valid 
  @Schema(name = "status", required = false)
  public MissionStatusType getStatus() {
    return status;
  }

  public void setStatus(MissionStatusType status) {
    this.status = status;
  }

  public Mission characteristic(List<Characteristic> characteristic) {
    this.characteristic = characteristic;
    return this;
  }

  public Mission addCharacteristicItem(Characteristic characteristicItem) {
    if (this.characteristic == null) {
      this.characteristic = new ArrayList<>();
    }
    this.characteristic.add(characteristicItem);
    return this;
  }

  /**
   * Get characteristic
   * @return characteristic
  */
  @Valid 
  @Schema(name = "characteristic", required = false)
  public List<Characteristic> getCharacteristic() {
    return characteristic;
  }

  public void setCharacteristic(List<Characteristic> characteristic) {
    this.characteristic = characteristic;
  }

  public Mission place(List<PlaceRefOrValue> place) {
    this.place = place;
    return this;
  }

  public Mission addPlaceItem(PlaceRefOrValue placeItem) {
    if (this.place == null) {
      this.place = new ArrayList<>();
    }
    this.place.add(placeItem);
    return this;
  }

  /**
   * List of places where the mission is conducted.
   * @return place
  */
  @Valid 
  @Schema(name = "place", description = "List of places where the mission is conducted.", required = false)
  public List<PlaceRefOrValue> getPlace() {
    return place;
  }

  public void setPlace(List<PlaceRefOrValue> place) {
    this.place = place;
  }

  public Mission asset(List<MissionAsset> asset) {
    this.asset = asset;
    return this;
  }

  public Mission addAssetItem(MissionAsset assetItem) {
    if (this.asset == null) {
      this.asset = new ArrayList<>();
    }
    this.asset.add(assetItem);
    return this;
  }

  /**
   * List of assets for this mission.
   * @return asset
  */
  @Valid 
  @Schema(name = "asset", description = "List of assets for this mission.", required = false)
  public List<MissionAsset> getAsset() {
    return asset;
  }

  public void setAsset(List<MissionAsset> asset) {
    this.asset = asset;
  }

  public Mission missionRelationship(List<MissionRelationship> missionRelationship) {
    this.missionRelationship = missionRelationship;
    return this;
  }

  public Mission addMissionRelationshipItem(MissionRelationship missionRelationshipItem) {
    if (this.missionRelationship == null) {
      this.missionRelationship = new ArrayList<>();
    }
    this.missionRelationship.add(missionRelationshipItem);
    return this;
  }

  /**
   * Relation to other missions.
   * @return missionRelationship
  */
  @Valid 
  @Schema(name = "missionRelationship", description = "Relation to other missions.", required = false)
  public List<MissionRelationship> getMissionRelationship() {
    return missionRelationship;
  }

  public void setMissionRelationship(List<MissionRelationship> missionRelationship) {
    this.missionRelationship = missionRelationship;
  }

  public Mission relatedParty(List<RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public Mission addRelatedPartyItem(RelatedParty relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

  /**
   * A related party defines party or party role linked to a specific entity.
   * @return relatedParty
  */
  @Valid 
  @Schema(name = "relatedParty", description = "A related party defines party or party role linked to a specific entity.", required = false)
  public List<RelatedParty> getRelatedParty() {
    return relatedParty;
  }

  public void setRelatedParty(List<RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Mission mission = (Mission) o;
    return Objects.equals(this.atType, mission.atType) &&
        Objects.equals(this.atBaseType, mission.atBaseType) &&
        Objects.equals(this.atSchemaLocation, mission.atSchemaLocation) &&
        Objects.equals(this.href, mission.href) &&
        Objects.equals(this.id, mission.id) &&
        Objects.equals(this.name, mission.name) &&
        Objects.equals(this.description, mission.description) &&
        Objects.equals(this.category, mission.category) &&
        Objects.equals(this.timeframe, mission.timeframe) &&
        Objects.equals(this.lastUpdate, mission.lastUpdate) &&
        Objects.equals(this.status, mission.status) &&
        Objects.equals(this.characteristic, mission.characteristic) &&
        Objects.equals(this.place, mission.place) &&
        Objects.equals(this.asset, mission.asset) &&
        Objects.equals(this.missionRelationship, mission.missionRelationship) &&
        Objects.equals(this.relatedParty, mission.relatedParty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType, atBaseType, atSchemaLocation, href, id, name, description, category, timeframe, lastUpdate, status, characteristic, place, asset, missionRelationship, relatedParty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Mission {\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    timeframe: ").append(toIndentedString(timeframe)).append("\n");
    sb.append("    lastUpdate: ").append(toIndentedString(lastUpdate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    characteristic: ").append(toIndentedString(characteristic)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    asset: ").append(toIndentedString(asset)).append("\n");
    sb.append("    missionRelationship: ").append(toIndentedString(missionRelationship)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
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

