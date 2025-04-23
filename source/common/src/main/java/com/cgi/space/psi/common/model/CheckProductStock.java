package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.CheckProductStockItem;
import com.cgi.space.psi.common.model.PlaceRefOrValue;
import com.cgi.space.psi.common.model.RelatedParty;
import com.cgi.space.psi.common.model.TaskStateType;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * CheckProductStock is used to log and execute check about product stock availability
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "CheckProductStock", description = "CheckProductStock is used to log and execute check about product stock availability")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CheckProductStock {

  @JsonProperty("id")
  private String id;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("completedCheckProductStockDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime completedCheckProductStockDate;

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("instantSyncCheck")
  private Boolean instantSyncCheck;

  @JsonProperty("provideAlternative")
  private Boolean provideAlternative = false;

  @JsonProperty("requestedAvailability")
  private TimePeriod requestedAvailability;

  @JsonProperty("requestedCheckProductStockDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime requestedCheckProductStockDate;

  @JsonProperty("checkProductStockItem")
  @Valid
  private List<CheckProductStockItem> checkProductStockItem = null;

  @JsonProperty("place")
  private PlaceRefOrValue place;

  @JsonProperty("relatedParty")
  @Valid
  private List<RelatedParty> relatedParty = null;

  @JsonProperty("state")
  private TaskStateType state;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public CheckProductStock id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the CheckProductStock
   * @return id
  */
  
  @Schema(name = "id", description = "Unique identifier of the CheckProductStock", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public CheckProductStock href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the CheckProductStock
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "Reference of the CheckProductStock", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public CheckProductStock completedCheckProductStockDate(OffsetDateTime completedCheckProductStockDate) {
    this.completedCheckProductStockDate = completedCheckProductStockDate;
    return this;
  }

  /**
   * Date when the CheckProductStock was completed.
   * @return completedCheckProductStockDate
  */
  @Valid 
  @Schema(name = "completedCheckProductStockDate", description = "Date when the CheckProductStock was completed.", required = false)
  public OffsetDateTime getCompletedCheckProductStockDate() {
    return completedCheckProductStockDate;
  }

  public void setCompletedCheckProductStockDate(OffsetDateTime completedCheckProductStockDate) {
    this.completedCheckProductStockDate = completedCheckProductStockDate;
  }

  public CheckProductStock creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the CheckProductStock was created (whatever its status).
   * @return creationDate
  */
  @Valid 
  @Schema(name = "creationDate", description = "Date when the CheckProductStock was created (whatever its status).", required = false)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public CheckProductStock instantSyncCheck(Boolean instantSyncCheck) {
    this.instantSyncCheck = instantSyncCheck;
    return this;
  }

  /**
   * An indicator which when the value is \"true\" means that requester expects to get result immediately in the response. If the indicator is true then the response code of 200 indicates the operation is successful otherwise a task is created with a response 201.
   * @return instantSyncCheck
  */
  
  @Schema(name = "instantSyncCheck", description = "An indicator which when the value is \"true\" means that requester expects to get result immediately in the response. If the indicator is true then the response code of 200 indicates the operation is successful otherwise a task is created with a response 201.", required = false)
  public Boolean getInstantSyncCheck() {
    return instantSyncCheck;
  }

  public void setInstantSyncCheck(Boolean instantSyncCheck) {
    this.instantSyncCheck = instantSyncCheck;
  }

  public CheckProductStock provideAlternative(Boolean provideAlternative) {
    this.provideAlternative = provideAlternative;
    return this;
  }

  /**
   * When the value is TRUE means that alternative proposal should be provided (from other product stock, different date or quantity)
   * @return provideAlternative
  */
  
  @Schema(name = "provideAlternative", description = "When the value is TRUE means that alternative proposal should be provided (from other product stock, different date or quantity)", required = false)
  public Boolean getProvideAlternative() {
    return provideAlternative;
  }

  public void setProvideAlternative(Boolean provideAlternative) {
    this.provideAlternative = provideAlternative;
  }

  public CheckProductStock requestedAvailability(TimePeriod requestedAvailability) {
    this.requestedAvailability = requestedAvailability;
    return this;
  }

  /**
   * Get requestedAvailability
   * @return requestedAvailability
  */
  @Valid 
  @Schema(name = "requestedAvailability", required = false)
  public TimePeriod getRequestedAvailability() {
    return requestedAvailability;
  }

  public void setRequestedAvailability(TimePeriod requestedAvailability) {
    this.requestedAvailability = requestedAvailability;
  }

  public CheckProductStock requestedCheckProductStockDate(OffsetDateTime requestedCheckProductStockDate) {
    this.requestedCheckProductStockDate = requestedCheckProductStockDate;
    return this;
  }

  /**
   * Date when the requester wished to have a response for this product stock check query.
   * @return requestedCheckProductStockDate
  */
  @Valid 
  @Schema(name = "requestedCheckProductStockDate", description = "Date when the requester wished to have a response for this product stock check query.", required = false)
  public OffsetDateTime getRequestedCheckProductStockDate() {
    return requestedCheckProductStockDate;
  }

  public void setRequestedCheckProductStockDate(OffsetDateTime requestedCheckProductStockDate) {
    this.requestedCheckProductStockDate = requestedCheckProductStockDate;
  }

  public CheckProductStock checkProductStockItem(List<CheckProductStockItem> checkProductStockItem) {
    this.checkProductStockItem = checkProductStockItem;
    return this;
  }

  public CheckProductStock addCheckProductStockItemItem(CheckProductStockItem checkProductStockItemItem) {
    if (this.checkProductStockItem == null) {
      this.checkProductStockItem = new ArrayList<>();
    }
    this.checkProductStockItem.add(checkProductStockItemItem);
    return this;
  }

  /**
   * A list of check product stock  item
   * @return checkProductStockItem
  */
  @Valid 
  @Schema(name = "checkProductStockItem", description = "A list of check product stock  item", required = false)
  public List<CheckProductStockItem> getCheckProductStockItem() {
    return checkProductStockItem;
  }

  public void setCheckProductStockItem(List<CheckProductStockItem> checkProductStockItem) {
    this.checkProductStockItem = checkProductStockItem;
  }

  public CheckProductStock place(PlaceRefOrValue place) {
    this.place = place;
    return this;
  }

  /**
   * Get place
   * @return place
  */
  @Valid 
  @Schema(name = "place", required = false)
  public PlaceRefOrValue getPlace() {
    return place;
  }

  public void setPlace(PlaceRefOrValue place) {
    this.place = place;
  }

  public CheckProductStock relatedParty(List<RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public CheckProductStock addRelatedPartyItem(RelatedParty relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

  /**
   * A list of related party references (RelatedParty [*]). A related party defines party or party role linked to this CheckProductStock
   * @return relatedParty
  */
  @Valid 
  @Schema(name = "relatedParty", description = "A list of related party references (RelatedParty [*]). A related party defines party or party role linked to this CheckProductStock", required = false)
  public List<RelatedParty> getRelatedParty() {
    return relatedParty;
  }

  public void setRelatedParty(List<RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
  }

  public CheckProductStock state(TaskStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @Valid 
  @Schema(name = "state", required = false)
  public TaskStateType getState() {
    return state;
  }

  public void setState(TaskStateType state) {
    this.state = state;
  }

  public CheckProductStock atBaseType(String atBaseType) {
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

  public CheckProductStock atSchemaLocation(URI atSchemaLocation) {
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

  public CheckProductStock atType(String atType) {
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
    CheckProductStock checkProductStock = (CheckProductStock) o;
    return Objects.equals(this.id, checkProductStock.id) &&
        Objects.equals(this.href, checkProductStock.href) &&
        Objects.equals(this.completedCheckProductStockDate, checkProductStock.completedCheckProductStockDate) &&
        Objects.equals(this.creationDate, checkProductStock.creationDate) &&
        Objects.equals(this.instantSyncCheck, checkProductStock.instantSyncCheck) &&
        Objects.equals(this.provideAlternative, checkProductStock.provideAlternative) &&
        Objects.equals(this.requestedAvailability, checkProductStock.requestedAvailability) &&
        Objects.equals(this.requestedCheckProductStockDate, checkProductStock.requestedCheckProductStockDate) &&
        Objects.equals(this.checkProductStockItem, checkProductStock.checkProductStockItem) &&
        Objects.equals(this.place, checkProductStock.place) &&
        Objects.equals(this.relatedParty, checkProductStock.relatedParty) &&
        Objects.equals(this.state, checkProductStock.state) &&
        Objects.equals(this.atBaseType, checkProductStock.atBaseType) &&
        Objects.equals(this.atSchemaLocation, checkProductStock.atSchemaLocation) &&
        Objects.equals(this.atType, checkProductStock.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, completedCheckProductStockDate, creationDate, instantSyncCheck, provideAlternative, requestedAvailability, requestedCheckProductStockDate, checkProductStockItem, place, relatedParty, state, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CheckProductStock {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    completedCheckProductStockDate: ").append(toIndentedString(completedCheckProductStockDate)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    instantSyncCheck: ").append(toIndentedString(instantSyncCheck)).append("\n");
    sb.append("    provideAlternative: ").append(toIndentedString(provideAlternative)).append("\n");
    sb.append("    requestedAvailability: ").append(toIndentedString(requestedAvailability)).append("\n");
    sb.append("    requestedCheckProductStockDate: ").append(toIndentedString(requestedCheckProductStockDate)).append("\n");
    sb.append("    checkProductStockItem: ").append(toIndentedString(checkProductStockItem)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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

