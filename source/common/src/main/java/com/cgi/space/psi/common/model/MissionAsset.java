package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.InquiredProductCharacteristic;
import com.cgi.space.psi.common.model.MissionAssetRelationship;
import com.cgi.space.psi.common.model.ProductRef;
import com.cgi.space.psi.common.model.RelatedPlaceRefOrValue;
import com.cgi.space.psi.common.model.RelativeTimePeriod;
import com.cgi.space.psi.common.model.ResourceRef;
import com.cgi.space.psi.common.model.ServiceRef;
import com.cgi.space.psi.common.model.TargetProductSchema;
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
 * Planning of a required asset in a mission. May contain any combination of user-defined characteristics for an inquiry, an ordered product or a service/resource that is already in the customer inventory.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "MissionAsset", description = "Planning of a required asset in a mission. May contain any combination of user-defined characteristics for an inquiry, an ordered product or a service/resource that is already in the customer inventory.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MissionAsset {

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("targetProductSchema")
  private TargetProductSchema targetProductSchema;

  @JsonProperty("servicePeriod")
  private RelativeTimePeriod servicePeriod;

  @JsonProperty("place")
  @Valid
  private List<RelatedPlaceRefOrValue> place = null;

  @JsonProperty("inquiredProductCharacteristic")
  @Valid
  private List<InquiredProductCharacteristic> inquiredProductCharacteristic = null;

  @JsonProperty("realizingProduct")
  @Valid
  private List<ProductRef> realizingProduct = null;

  @JsonProperty("realizingService")
  @Valid
  private List<ServiceRef> realizingService = null;

  @JsonProperty("realizingResource")
  @Valid
  private List<ResourceRef> realizingResource = null;

  @JsonProperty("assetRelationship")
  @Valid
  private List<MissionAssetRelationship> assetRelationship = null;

  public MissionAsset id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier for this asset
   * @return id
  */
  
  @Schema(name = "id", description = "Identifier for this asset", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MissionAsset name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name for this asset
   * @return name
  */
  
  @Schema(name = "name", description = "Name for this asset", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MissionAsset targetProductSchema(TargetProductSchema targetProductSchema) {
    this.targetProductSchema = targetProductSchema;
    return this;
  }

  /**
   * Get targetProductSchema
   * @return targetProductSchema
  */
  @Valid 
  @Schema(name = "targetProductSchema", required = false)
  public TargetProductSchema getTargetProductSchema() {
    return targetProductSchema;
  }

  public void setTargetProductSchema(TargetProductSchema targetProductSchema) {
    this.targetProductSchema = targetProductSchema;
  }

  public MissionAsset servicePeriod(RelativeTimePeriod servicePeriod) {
    this.servicePeriod = servicePeriod;
    return this;
  }

  /**
   * Get servicePeriod
   * @return servicePeriod
  */
  @Valid 
  @Schema(name = "servicePeriod", required = false)
  public RelativeTimePeriod getServicePeriod() {
    return servicePeriod;
  }

  public void setServicePeriod(RelativeTimePeriod servicePeriod) {
    this.servicePeriod = servicePeriod;
  }

  public MissionAsset place(List<RelatedPlaceRefOrValue> place) {
    this.place = place;
    return this;
  }

  public MissionAsset addPlaceItem(RelatedPlaceRefOrValue placeItem) {
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
  public List<RelatedPlaceRefOrValue> getPlace() {
    return place;
  }

  public void setPlace(List<RelatedPlaceRefOrValue> place) {
    this.place = place;
  }

  public MissionAsset inquiredProductCharacteristic(List<InquiredProductCharacteristic> inquiredProductCharacteristic) {
    this.inquiredProductCharacteristic = inquiredProductCharacteristic;
    return this;
  }

  public MissionAsset addInquiredProductCharacteristicItem(InquiredProductCharacteristic inquiredProductCharacteristicItem) {
    if (this.inquiredProductCharacteristic == null) {
      this.inquiredProductCharacteristic = new ArrayList<>();
    }
    this.inquiredProductCharacteristic.add(inquiredProductCharacteristicItem);
    return this;
  }

  /**
   * A characteristic quality or distinctive feature of an InquiredProduct. The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3).
   * @return inquiredProductCharacteristic
  */
  @Valid 
  @Schema(name = "inquiredProductCharacteristic", description = "A characteristic quality or distinctive feature of an InquiredProduct. The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3).", required = false)
  public List<InquiredProductCharacteristic> getInquiredProductCharacteristic() {
    return inquiredProductCharacteristic;
  }

  public void setInquiredProductCharacteristic(List<InquiredProductCharacteristic> inquiredProductCharacteristic) {
    this.inquiredProductCharacteristic = inquiredProductCharacteristic;
  }

  public MissionAsset realizingProduct(List<ProductRef> realizingProduct) {
    this.realizingProduct = realizingProduct;
    return this;
  }

  public MissionAsset addRealizingProductItem(ProductRef realizingProductItem) {
    if (this.realizingProduct == null) {
      this.realizingProduct = new ArrayList<>();
    }
    this.realizingProduct.add(realizingProductItem);
    return this;
  }

  /**
   * A Product that is used to implement the defined specification. Usually set after creating a ProductOrder.
   * @return realizingProduct
  */
  @Valid 
  @Schema(name = "realizingProduct", description = "A Product that is used to implement the defined specification. Usually set after creating a ProductOrder.", required = false)
  public List<ProductRef> getRealizingProduct() {
    return realizingProduct;
  }

  public void setRealizingProduct(List<ProductRef> realizingProduct) {
    this.realizingProduct = realizingProduct;
  }

  public MissionAsset realizingService(List<ServiceRef> realizingService) {
    this.realizingService = realizingService;
    return this;
  }

  public MissionAsset addRealizingServiceItem(ServiceRef realizingServiceItem) {
    if (this.realizingService == null) {
      this.realizingService = new ArrayList<>();
    }
    this.realizingService.add(realizingServiceItem);
    return this;
  }

  /**
   * A Service that is part of the customers inventory. Either selected manually or found by the Inquiry API.
   * @return realizingService
  */
  @Valid 
  @Schema(name = "realizingService", description = "A Service that is part of the customers inventory. Either selected manually or found by the Inquiry API.", required = false)
  public List<ServiceRef> getRealizingService() {
    return realizingService;
  }

  public void setRealizingService(List<ServiceRef> realizingService) {
    this.realizingService = realizingService;
  }

  public MissionAsset realizingResource(List<ResourceRef> realizingResource) {
    this.realizingResource = realizingResource;
    return this;
  }

  public MissionAsset addRealizingResourceItem(ResourceRef realizingResourceItem) {
    if (this.realizingResource == null) {
      this.realizingResource = new ArrayList<>();
    }
    this.realizingResource.add(realizingResourceItem);
    return this;
  }

  /**
   * A Resource that is part of the customers inventory. Either selected manually or found by the Inquiry API.
   * @return realizingResource
  */
  @Valid 
  @Schema(name = "realizingResource", description = "A Resource that is part of the customers inventory. Either selected manually or found by the Inquiry API.", required = false)
  public List<ResourceRef> getRealizingResource() {
    return realizingResource;
  }

  public void setRealizingResource(List<ResourceRef> realizingResource) {
    this.realizingResource = realizingResource;
  }

  public MissionAsset assetRelationship(List<MissionAssetRelationship> assetRelationship) {
    this.assetRelationship = assetRelationship;
    return this;
  }

  public MissionAsset addAssetRelationshipItem(MissionAssetRelationship assetRelationshipItem) {
    if (this.assetRelationship == null) {
      this.assetRelationship = new ArrayList<>();
    }
    this.assetRelationship.add(assetRelationshipItem);
    return this;
  }

  /**
   * Relation to other assets.
   * @return assetRelationship
  */
  @Valid 
  @Schema(name = "assetRelationship", description = "Relation to other assets.", required = false)
  public List<MissionAssetRelationship> getAssetRelationship() {
    return assetRelationship;
  }

  public void setAssetRelationship(List<MissionAssetRelationship> assetRelationship) {
    this.assetRelationship = assetRelationship;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MissionAsset missionAsset = (MissionAsset) o;
    return Objects.equals(this.id, missionAsset.id) &&
        Objects.equals(this.name, missionAsset.name) &&
        Objects.equals(this.targetProductSchema, missionAsset.targetProductSchema) &&
        Objects.equals(this.servicePeriod, missionAsset.servicePeriod) &&
        Objects.equals(this.place, missionAsset.place) &&
        Objects.equals(this.inquiredProductCharacteristic, missionAsset.inquiredProductCharacteristic) &&
        Objects.equals(this.realizingProduct, missionAsset.realizingProduct) &&
        Objects.equals(this.realizingService, missionAsset.realizingService) &&
        Objects.equals(this.realizingResource, missionAsset.realizingResource) &&
        Objects.equals(this.assetRelationship, missionAsset.assetRelationship);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, targetProductSchema, servicePeriod, place, inquiredProductCharacteristic, realizingProduct, realizingService, realizingResource, assetRelationship);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MissionAsset {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    targetProductSchema: ").append(toIndentedString(targetProductSchema)).append("\n");
    sb.append("    servicePeriod: ").append(toIndentedString(servicePeriod)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    inquiredProductCharacteristic: ").append(toIndentedString(inquiredProductCharacteristic)).append("\n");
    sb.append("    realizingProduct: ").append(toIndentedString(realizingProduct)).append("\n");
    sb.append("    realizingService: ").append(toIndentedString(realizingService)).append("\n");
    sb.append("    realizingResource: ").append(toIndentedString(realizingResource)).append("\n");
    sb.append("    assetRelationship: ").append(toIndentedString(assetRelationship)).append("\n");
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

