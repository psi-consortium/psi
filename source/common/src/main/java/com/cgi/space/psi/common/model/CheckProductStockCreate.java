package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.CheckProductStockItem;
import com.cgi.space.psi.common.model.PlaceRefOrValue;
import com.cgi.space.psi.common.model.RelatedParty;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * CheckProductStock is used to log and execute check about product stock availability Skipped properties: id,href
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "CheckProductStock_Create", description = "CheckProductStock is used to log and execute check about product stock availability Skipped properties: id,href")
@JsonTypeName("CheckProductStock_Create")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CheckProductStockCreate {

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
  private List<CheckProductStockItem> checkProductStockItem = new ArrayList<>();

  @JsonProperty("place")
  private PlaceRefOrValue place;

  @JsonProperty("relatedParty")
  @Valid
  private List<RelatedParty> relatedParty = null;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public CheckProductStockCreate instantSyncCheck(Boolean instantSyncCheck) {
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

  public CheckProductStockCreate provideAlternative(Boolean provideAlternative) {
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

  public CheckProductStockCreate requestedAvailability(TimePeriod requestedAvailability) {
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

  public CheckProductStockCreate requestedCheckProductStockDate(OffsetDateTime requestedCheckProductStockDate) {
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

  public CheckProductStockCreate checkProductStockItem(List<CheckProductStockItem> checkProductStockItem) {
    this.checkProductStockItem = checkProductStockItem;
    return this;
  }

  public CheckProductStockCreate addCheckProductStockItemItem(CheckProductStockItem checkProductStockItemItem) {
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
  @NotNull @Valid @Size(min = 1) 
  @Schema(name = "checkProductStockItem", description = "A list of check product stock  item", required = true)
  public List<CheckProductStockItem> getCheckProductStockItem() {
    return checkProductStockItem;
  }

  public void setCheckProductStockItem(List<CheckProductStockItem> checkProductStockItem) {
    this.checkProductStockItem = checkProductStockItem;
  }

  public CheckProductStockCreate place(PlaceRefOrValue place) {
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

  public CheckProductStockCreate relatedParty(List<RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public CheckProductStockCreate addRelatedPartyItem(RelatedParty relatedPartyItem) {
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

  public CheckProductStockCreate atBaseType(String atBaseType) {
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

  public CheckProductStockCreate atSchemaLocation(URI atSchemaLocation) {
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

  public CheckProductStockCreate atType(String atType) {
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
    CheckProductStockCreate checkProductStockCreate = (CheckProductStockCreate) o;
    return Objects.equals(this.instantSyncCheck, checkProductStockCreate.instantSyncCheck) &&
        Objects.equals(this.provideAlternative, checkProductStockCreate.provideAlternative) &&
        Objects.equals(this.requestedAvailability, checkProductStockCreate.requestedAvailability) &&
        Objects.equals(this.requestedCheckProductStockDate, checkProductStockCreate.requestedCheckProductStockDate) &&
        Objects.equals(this.checkProductStockItem, checkProductStockCreate.checkProductStockItem) &&
        Objects.equals(this.place, checkProductStockCreate.place) &&
        Objects.equals(this.relatedParty, checkProductStockCreate.relatedParty) &&
        Objects.equals(this.atBaseType, checkProductStockCreate.atBaseType) &&
        Objects.equals(this.atSchemaLocation, checkProductStockCreate.atSchemaLocation) &&
        Objects.equals(this.atType, checkProductStockCreate.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(instantSyncCheck, provideAlternative, requestedAvailability, requestedCheckProductStockDate, checkProductStockItem, place, relatedParty, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CheckProductStockCreate {\n");
    sb.append("    instantSyncCheck: ").append(toIndentedString(instantSyncCheck)).append("\n");
    sb.append("    provideAlternative: ").append(toIndentedString(provideAlternative)).append("\n");
    sb.append("    requestedAvailability: ").append(toIndentedString(requestedAvailability)).append("\n");
    sb.append("    requestedCheckProductStockDate: ").append(toIndentedString(requestedCheckProductStockDate)).append("\n");
    sb.append("    checkProductStockItem: ").append(toIndentedString(checkProductStockItem)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
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

