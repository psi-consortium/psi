package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ChannelRef;
import com.cgi.space.psi.common.model.MarketSegmentRef;
import com.cgi.space.psi.common.model.ProductRefOrValue;
import com.cgi.space.psi.common.model.ProductStockRelationship;
import com.cgi.space.psi.common.model.ProductStockStatusType;
import com.cgi.space.psi.common.model.ProductStockUsageType;
import com.cgi.space.psi.common.model.Quantity;
import com.cgi.space.psi.common.model.RelatedParty;
import com.cgi.space.psi.common.model.RelatedPlaceRefOrValue;
import com.cgi.space.psi.common.model.ResourceRef;
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
 * ProductStock is a base class for defining a product (or configured product with values characteristic) stock level.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ProductStock", description = "ProductStock is a base class for defining a product (or configured product with values characteristic) stock level.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ProductStock {

  @JsonProperty("id")
  private String id;

  @JsonProperty("href")
  private URI href;

  @JsonProperty("creationDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creationDate;

  @JsonProperty("description")
  private String description;

  @JsonProperty("lastInventoryDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastInventoryDate;

  @JsonProperty("lastUpdate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastUpdate;

  @JsonProperty("name")
  private String name;

  @JsonProperty("replenishmentDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime replenishmentDate;

  @JsonProperty("stockLevelCategory")
  private String stockLevelCategory;

  @JsonProperty("channel")
  @Valid
  private List<ChannelRef> channel = null;

  @JsonProperty("marketSegment")
  @Valid
  private List<MarketSegmentRef> marketSegment = null;

  @JsonProperty("maxStockLevel")
  private Quantity maxStockLevel;

  @JsonProperty("minStockLevel")
  private Quantity minStockLevel;

  @JsonProperty("place")
  private RelatedPlaceRefOrValue place;

  @JsonProperty("productStockLevel")
  private Quantity productStockLevel;

  @JsonProperty("productStockRelationship")
  @Valid
  private List<ProductStockRelationship> productStockRelationship = null;

  @JsonProperty("productStockStatusType")
  private ProductStockStatusType productStockStatusType;

  @JsonProperty("productStockUsageType")
  private ProductStockUsageType productStockUsageType;

  @JsonProperty("relatedParty")
  @Valid
  private List<RelatedParty> relatedParty = null;

  @JsonProperty("reorderQuantity")
  private Quantity reorderQuantity;

  @JsonProperty("resource")
  @Valid
  private List<ResourceRef> resource = null;

  @JsonProperty("stockLevelAlert")
  private Quantity stockLevelAlert;

  @JsonProperty("stockedProduct")
  private ProductRefOrValue stockedProduct;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private URI atSchemaLocation;

  @JsonProperty("@type")
  private String atType;

  public ProductStock id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the ProductStock
   * @return id
  */
  
  @Schema(name = "id", description = "Unique identifier of the ProductStock", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProductStock href(URI href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the ProductStock
   * @return href
  */
  @Valid 
  @Schema(name = "href", description = "Reference of the ProductStock", required = false)
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public ProductStock creationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Date when the ProductStock was created (whatever its status).
   * @return creationDate
  */
  @Valid 
  @Schema(name = "creationDate", description = "Date when the ProductStock was created (whatever its status).", required = false)
  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public ProductStock description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Free-text description of the ProductStock
   * @return description
  */
  
  @Schema(name = "description", description = "Free-text description of the ProductStock", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductStock lastInventoryDate(OffsetDateTime lastInventoryDate) {
    this.lastInventoryDate = lastInventoryDate;
    return this;
  }

  /**
   * Last inventory count date for this ProductStock.
   * @return lastInventoryDate
  */
  @Valid 
  @Schema(name = "lastInventoryDate", description = "Last inventory count date for this ProductStock.", required = false)
  public OffsetDateTime getLastInventoryDate() {
    return lastInventoryDate;
  }

  public void setLastInventoryDate(OffsetDateTime lastInventoryDate) {
    this.lastInventoryDate = lastInventoryDate;
  }

  public ProductStock lastUpdate(OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
    return this;
  }

  /**
   * Last date when the ProductStock record was updated.
   * @return lastUpdate
  */
  @Valid 
  @Schema(name = "lastUpdate", description = "Last date when the ProductStock record was updated.", required = false)
  public OffsetDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public ProductStock name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the ProductStock
   * @return name
  */
  
  @Schema(name = "name", description = "Name of the ProductStock", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductStock replenishmentDate(OffsetDateTime replenishmentDate) {
    this.replenishmentDate = replenishmentDate;
    return this;
  }

  /**
   * Planned date for future replenishment for this ProductStock.
   * @return replenishmentDate
  */
  @Valid 
  @Schema(name = "replenishmentDate", description = "Planned date for future replenishment for this ProductStock.", required = false)
  public OffsetDateTime getReplenishmentDate() {
    return replenishmentDate;
  }

  public void setReplenishmentDate(OffsetDateTime replenishmentDate) {
    this.replenishmentDate = replenishmentDate;
  }

  public ProductStock stockLevelCategory(String stockLevelCategory) {
    this.stockLevelCategory = stockLevelCategory;
    return this;
  }

  /**
   * Category of the ProductStock
   * @return stockLevelCategory
  */
  
  @Schema(name = "stockLevelCategory", description = "Category of the ProductStock", required = false)
  public String getStockLevelCategory() {
    return stockLevelCategory;
  }

  public void setStockLevelCategory(String stockLevelCategory) {
    this.stockLevelCategory = stockLevelCategory;
  }

  public ProductStock channel(List<ChannelRef> channel) {
    this.channel = channel;
    return this;
  }

  public ProductStock addChannelItem(ChannelRef channelItem) {
    if (this.channel == null) {
      this.channel = new ArrayList<>();
    }
    this.channel.add(channelItem);
    return this;
  }

  /**
   * Get channel
   * @return channel
  */
  @Valid 
  @Schema(name = "channel", required = false)
  public List<ChannelRef> getChannel() {
    return channel;
  }

  public void setChannel(List<ChannelRef> channel) {
    this.channel = channel;
  }

  public ProductStock marketSegment(List<MarketSegmentRef> marketSegment) {
    this.marketSegment = marketSegment;
    return this;
  }

  public ProductStock addMarketSegmentItem(MarketSegmentRef marketSegmentItem) {
    if (this.marketSegment == null) {
      this.marketSegment = new ArrayList<>();
    }
    this.marketSegment.add(marketSegmentItem);
    return this;
  }

  /**
   * provides references to the corresponding market segment as target of a product stock. A market segment is grouping of Parties, GeographicAreas, SalesChannels, and so forth.
   * @return marketSegment
  */
  @Valid 
  @Schema(name = "marketSegment", description = "provides references to the corresponding market segment as target of a product stock. A market segment is grouping of Parties, GeographicAreas, SalesChannels, and so forth.", required = false)
  public List<MarketSegmentRef> getMarketSegment() {
    return marketSegment;
  }

  public void setMarketSegment(List<MarketSegmentRef> marketSegment) {
    this.marketSegment = marketSegment;
  }

  public ProductStock maxStockLevel(Quantity maxStockLevel) {
    this.maxStockLevel = maxStockLevel;
    return this;
  }

  /**
   * Get maxStockLevel
   * @return maxStockLevel
  */
  @Valid 
  @Schema(name = "maxStockLevel", required = false)
  public Quantity getMaxStockLevel() {
    return maxStockLevel;
  }

  public void setMaxStockLevel(Quantity maxStockLevel) {
    this.maxStockLevel = maxStockLevel;
  }

  public ProductStock minStockLevel(Quantity minStockLevel) {
    this.minStockLevel = minStockLevel;
    return this;
  }

  /**
   * Get minStockLevel
   * @return minStockLevel
  */
  @Valid 
  @Schema(name = "minStockLevel", required = false)
  public Quantity getMinStockLevel() {
    return minStockLevel;
  }

  public void setMinStockLevel(Quantity minStockLevel) {
    this.minStockLevel = minStockLevel;
  }

  public ProductStock place(RelatedPlaceRefOrValue place) {
    this.place = place;
    return this;
  }

  /**
   * Get place
   * @return place
  */
  @Valid 
  @Schema(name = "place", required = false)
  public RelatedPlaceRefOrValue getPlace() {
    return place;
  }

  public void setPlace(RelatedPlaceRefOrValue place) {
    this.place = place;
  }

  public ProductStock productStockLevel(Quantity productStockLevel) {
    this.productStockLevel = productStockLevel;
    return this;
  }

  /**
   * Get productStockLevel
   * @return productStockLevel
  */
  @Valid 
  @Schema(name = "productStockLevel", required = false)
  public Quantity getProductStockLevel() {
    return productStockLevel;
  }

  public void setProductStockLevel(Quantity productStockLevel) {
    this.productStockLevel = productStockLevel;
  }

  public ProductStock productStockRelationship(List<ProductStockRelationship> productStockRelationship) {
    this.productStockRelationship = productStockRelationship;
    return this;
  }

  public ProductStock addProductStockRelationshipItem(ProductStockRelationship productStockRelationshipItem) {
    if (this.productStockRelationship == null) {
      this.productStockRelationship = new ArrayList<>();
    }
    this.productStockRelationship.add(productStockRelationshipItem);
    return this;
  }

  /**
   * relationship to other product stock
   * @return productStockRelationship
  */
  @Valid 
  @Schema(name = "productStockRelationship", description = "relationship to other product stock", required = false)
  public List<ProductStockRelationship> getProductStockRelationship() {
    return productStockRelationship;
  }

  public void setProductStockRelationship(List<ProductStockRelationship> productStockRelationship) {
    this.productStockRelationship = productStockRelationship;
  }

  public ProductStock productStockStatusType(ProductStockStatusType productStockStatusType) {
    this.productStockStatusType = productStockStatusType;
    return this;
  }

  /**
   * Get productStockStatusType
   * @return productStockStatusType
  */
  @Valid 
  @Schema(name = "productStockStatusType", required = false)
  public ProductStockStatusType getProductStockStatusType() {
    return productStockStatusType;
  }

  public void setProductStockStatusType(ProductStockStatusType productStockStatusType) {
    this.productStockStatusType = productStockStatusType;
  }

  public ProductStock productStockUsageType(ProductStockUsageType productStockUsageType) {
    this.productStockUsageType = productStockUsageType;
    return this;
  }

  /**
   * Get productStockUsageType
   * @return productStockUsageType
  */
  @Valid 
  @Schema(name = "productStockUsageType", required = false)
  public ProductStockUsageType getProductStockUsageType() {
    return productStockUsageType;
  }

  public void setProductStockUsageType(ProductStockUsageType productStockUsageType) {
    this.productStockUsageType = productStockUsageType;
  }

  public ProductStock relatedParty(List<RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public ProductStock addRelatedPartyItem(RelatedParty relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

  /**
   * A list of related party references (RelatedParty [*]). A related party defines party or party role linked to a specific entity
   * @return relatedParty
  */
  @Valid 
  @Schema(name = "relatedParty", description = "A list of related party references (RelatedParty [*]). A related party defines party or party role linked to a specific entity", required = false)
  public List<RelatedParty> getRelatedParty() {
    return relatedParty;
  }

  public void setRelatedParty(List<RelatedParty> relatedParty) {
    this.relatedParty = relatedParty;
  }

  public ProductStock reorderQuantity(Quantity reorderQuantity) {
    this.reorderQuantity = reorderQuantity;
    return this;
  }

  /**
   * Get reorderQuantity
   * @return reorderQuantity
  */
  @Valid 
  @Schema(name = "reorderQuantity", required = false)
  public Quantity getReorderQuantity() {
    return reorderQuantity;
  }

  public void setReorderQuantity(Quantity reorderQuantity) {
    this.reorderQuantity = reorderQuantity;
  }

  public ProductStock resource(List<ResourceRef> resource) {
    this.resource = resource;
    return this;
  }

  public ProductStock addResourceItem(ResourceRef resourceItem) {
    if (this.resource == null) {
      this.resource = new ArrayList<>();
    }
    this.resource.add(resourceItem);
    return this;
  }

  /**
   * The Resource managed through this ProductStock is used
   * @return resource
  */
  @Valid 
  @Schema(name = "resource", description = "The Resource managed through this ProductStock is used", required = false)
  public List<ResourceRef> getResource() {
    return resource;
  }

  public void setResource(List<ResourceRef> resource) {
    this.resource = resource;
  }

  public ProductStock stockLevelAlert(Quantity stockLevelAlert) {
    this.stockLevelAlert = stockLevelAlert;
    return this;
  }

  /**
   * Get stockLevelAlert
   * @return stockLevelAlert
  */
  @Valid 
  @Schema(name = "stockLevelAlert", required = false)
  public Quantity getStockLevelAlert() {
    return stockLevelAlert;
  }

  public void setStockLevelAlert(Quantity stockLevelAlert) {
    this.stockLevelAlert = stockLevelAlert;
  }

  public ProductStock stockedProduct(ProductRefOrValue stockedProduct) {
    this.stockedProduct = stockedProduct;
    return this;
  }

  /**
   * Get stockedProduct
   * @return stockedProduct
  */
  @Valid 
  @Schema(name = "stockedProduct", required = false)
  public ProductRefOrValue getStockedProduct() {
    return stockedProduct;
  }

  public void setStockedProduct(ProductRefOrValue stockedProduct) {
    this.stockedProduct = stockedProduct;
  }

  public ProductStock atBaseType(String atBaseType) {
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

  public ProductStock atSchemaLocation(URI atSchemaLocation) {
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

  public ProductStock atType(String atType) {
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
    ProductStock productStock = (ProductStock) o;
    return Objects.equals(this.id, productStock.id) &&
        Objects.equals(this.href, productStock.href) &&
        Objects.equals(this.creationDate, productStock.creationDate) &&
        Objects.equals(this.description, productStock.description) &&
        Objects.equals(this.lastInventoryDate, productStock.lastInventoryDate) &&
        Objects.equals(this.lastUpdate, productStock.lastUpdate) &&
        Objects.equals(this.name, productStock.name) &&
        Objects.equals(this.replenishmentDate, productStock.replenishmentDate) &&
        Objects.equals(this.stockLevelCategory, productStock.stockLevelCategory) &&
        Objects.equals(this.channel, productStock.channel) &&
        Objects.equals(this.marketSegment, productStock.marketSegment) &&
        Objects.equals(this.maxStockLevel, productStock.maxStockLevel) &&
        Objects.equals(this.minStockLevel, productStock.minStockLevel) &&
        Objects.equals(this.place, productStock.place) &&
        Objects.equals(this.productStockLevel, productStock.productStockLevel) &&
        Objects.equals(this.productStockRelationship, productStock.productStockRelationship) &&
        Objects.equals(this.productStockStatusType, productStock.productStockStatusType) &&
        Objects.equals(this.productStockUsageType, productStock.productStockUsageType) &&
        Objects.equals(this.relatedParty, productStock.relatedParty) &&
        Objects.equals(this.reorderQuantity, productStock.reorderQuantity) &&
        Objects.equals(this.resource, productStock.resource) &&
        Objects.equals(this.stockLevelAlert, productStock.stockLevelAlert) &&
        Objects.equals(this.stockedProduct, productStock.stockedProduct) &&
        Objects.equals(this.atBaseType, productStock.atBaseType) &&
        Objects.equals(this.atSchemaLocation, productStock.atSchemaLocation) &&
        Objects.equals(this.atType, productStock.atType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, href, creationDate, description, lastInventoryDate, lastUpdate, name, replenishmentDate, stockLevelCategory, channel, marketSegment, maxStockLevel, minStockLevel, place, productStockLevel, productStockRelationship, productStockStatusType, productStockUsageType, relatedParty, reorderQuantity, resource, stockLevelAlert, stockedProduct, atBaseType, atSchemaLocation, atType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductStock {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    lastInventoryDate: ").append(toIndentedString(lastInventoryDate)).append("\n");
    sb.append("    lastUpdate: ").append(toIndentedString(lastUpdate)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    replenishmentDate: ").append(toIndentedString(replenishmentDate)).append("\n");
    sb.append("    stockLevelCategory: ").append(toIndentedString(stockLevelCategory)).append("\n");
    sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
    sb.append("    marketSegment: ").append(toIndentedString(marketSegment)).append("\n");
    sb.append("    maxStockLevel: ").append(toIndentedString(maxStockLevel)).append("\n");
    sb.append("    minStockLevel: ").append(toIndentedString(minStockLevel)).append("\n");
    sb.append("    place: ").append(toIndentedString(place)).append("\n");
    sb.append("    productStockLevel: ").append(toIndentedString(productStockLevel)).append("\n");
    sb.append("    productStockRelationship: ").append(toIndentedString(productStockRelationship)).append("\n");
    sb.append("    productStockStatusType: ").append(toIndentedString(productStockStatusType)).append("\n");
    sb.append("    productStockUsageType: ").append(toIndentedString(productStockUsageType)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
    sb.append("    reorderQuantity: ").append(toIndentedString(reorderQuantity)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
    sb.append("    stockLevelAlert: ").append(toIndentedString(stockLevelAlert)).append("\n");
    sb.append("    stockedProduct: ").append(toIndentedString(stockedProduct)).append("\n");
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

