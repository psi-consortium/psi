package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.InquiredProductRef;
import com.cgi.space.psi.common.model.ProductOfferingRef;
import com.cgi.space.psi.common.model.ProductRef;
import com.cgi.space.psi.common.model.ProductSpecificationRef;
import com.cgi.space.psi.common.model.RelationshipCharacteristic;
import com.cgi.space.psi.common.model.TimePeriod;
import com.cgi.space.psi.common.model.TotalPrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * InquiredProductRelationship
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InquiredProductRelationship {

  @JsonProperty("inquiredProductRef")
  private InquiredProductRef inquiredProductRef;

  /**
   * Provides details on how the given ProductOffering/ProductSpecification/Product coveres the related InquiredProduct.
   */
  public enum CoverageEnum {
    COMPLETE("complete"),
    
    PARTIAL("partial");

    private String value;

    CoverageEnum(String value) {
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
    public static CoverageEnum fromValue(String value) {
      for (CoverageEnum b : CoverageEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("coverage")
  private CoverageEnum coverage;

  @JsonProperty("quantity")
  private Integer quantity = 1;

  @JsonProperty("totalPrice")
  @Valid
  private List<TotalPrice> totalPrice = null;

  @JsonProperty("servicePeriod")
  private TimePeriod servicePeriod;

  @JsonProperty("relationshipCharacteristic")
  @Valid
  private List<RelationshipCharacteristic> relationshipCharacteristic = null;

  @JsonProperty("productOfferingRef")
  private ProductOfferingRef productOfferingRef;

  @JsonProperty("productSpecificationRef")
  private ProductSpecificationRef productSpecificationRef;

  @JsonProperty("productRef")
  private ProductRef productRef;

  public InquiredProductRelationship inquiredProductRef(InquiredProductRef inquiredProductRef) {
    this.inquiredProductRef = inquiredProductRef;
    return this;
  }

  /**
   * Get inquiredProductRef
   * @return inquiredProductRef
  */
  @NotNull @Valid 
  @Schema(name = "inquiredProductRef", required = true)
  public InquiredProductRef getInquiredProductRef() {
    return inquiredProductRef;
  }

  public void setInquiredProductRef(InquiredProductRef inquiredProductRef) {
    this.inquiredProductRef = inquiredProductRef;
  }

  public InquiredProductRelationship coverage(CoverageEnum coverage) {
    this.coverage = coverage;
    return this;
  }

  /**
   * Provides details on how the given ProductOffering/ProductSpecification/Product coveres the related InquiredProduct.
   * @return coverage
  */
  
  @Schema(name = "coverage", description = "Provides details on how the given ProductOffering/ProductSpecification/Product coveres the related InquiredProduct.", required = false)
  public CoverageEnum getCoverage() {
    return coverage;
  }

  public void setCoverage(CoverageEnum coverage) {
    this.coverage = coverage;
  }

  public InquiredProductRelationship quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * minimum: 1
   * @return quantity
  */
  @Min(1) 
  @Schema(name = "quantity", required = false)
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public InquiredProductRelationship totalPrice(List<TotalPrice> totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  public InquiredProductRelationship addTotalPriceItem(TotalPrice totalPriceItem) {
    if (this.totalPrice == null) {
      this.totalPrice = new ArrayList<>();
    }
    this.totalPrice.add(totalPriceItem);
    return this;
  }

  /**
   * Get totalPrice
   * @return totalPrice
  */
  @Valid 
  @Schema(name = "totalPrice", required = false)
  public List<TotalPrice> getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(List<TotalPrice> totalPrice) {
    this.totalPrice = totalPrice;
  }

  public InquiredProductRelationship servicePeriod(TimePeriod servicePeriod) {
    this.servicePeriod = servicePeriod;
    return this;
  }

  /**
   * Get servicePeriod
   * @return servicePeriod
  */
  @Valid 
  @Schema(name = "servicePeriod", required = false)
  public TimePeriod getServicePeriod() {
    return servicePeriod;
  }

  public void setServicePeriod(TimePeriod servicePeriod) {
    this.servicePeriod = servicePeriod;
  }

  public InquiredProductRelationship relationshipCharacteristic(List<RelationshipCharacteristic> relationshipCharacteristic) {
    this.relationshipCharacteristic = relationshipCharacteristic;
    return this;
  }

  public InquiredProductRelationship addRelationshipCharacteristicItem(RelationshipCharacteristic relationshipCharacteristicItem) {
    if (this.relationshipCharacteristic == null) {
      this.relationshipCharacteristic = new ArrayList<>();
    }
    this.relationshipCharacteristic.add(relationshipCharacteristicItem);
    return this;
  }

  /**
   * A characteristic property or distinguishing feature for this relationship.
   * @return relationshipCharacteristic
  */
  @Valid 
  @Schema(name = "relationshipCharacteristic", description = "A characteristic property or distinguishing feature for this relationship.", required = false)
  public List<RelationshipCharacteristic> getRelationshipCharacteristic() {
    return relationshipCharacteristic;
  }

  public void setRelationshipCharacteristic(List<RelationshipCharacteristic> relationshipCharacteristic) {
    this.relationshipCharacteristic = relationshipCharacteristic;
  }

  public InquiredProductRelationship productOfferingRef(ProductOfferingRef productOfferingRef) {
    this.productOfferingRef = productOfferingRef;
    return this;
  }

  /**
   * Get productOfferingRef
   * @return productOfferingRef
  */
  @Valid 
  @Schema(name = "productOfferingRef", required = false)
  public ProductOfferingRef getProductOfferingRef() {
    return productOfferingRef;
  }

  public void setProductOfferingRef(ProductOfferingRef productOfferingRef) {
    this.productOfferingRef = productOfferingRef;
  }

  public InquiredProductRelationship productSpecificationRef(ProductSpecificationRef productSpecificationRef) {
    this.productSpecificationRef = productSpecificationRef;
    return this;
  }

  /**
   * Get productSpecificationRef
   * @return productSpecificationRef
  */
  @Valid 
  @Schema(name = "productSpecificationRef", required = false)
  public ProductSpecificationRef getProductSpecificationRef() {
    return productSpecificationRef;
  }

  public void setProductSpecificationRef(ProductSpecificationRef productSpecificationRef) {
    this.productSpecificationRef = productSpecificationRef;
  }

  public InquiredProductRelationship productRef(ProductRef productRef) {
    this.productRef = productRef;
    return this;
  }

  /**
   * Get productRef
   * @return productRef
  */
  @Valid 
  @Schema(name = "productRef", required = false)
  public ProductRef getProductRef() {
    return productRef;
  }

  public void setProductRef(ProductRef productRef) {
    this.productRef = productRef;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InquiredProductRelationship inquiredProductRelationship = (InquiredProductRelationship) o;
    return Objects.equals(this.inquiredProductRef, inquiredProductRelationship.inquiredProductRef) &&
        Objects.equals(this.coverage, inquiredProductRelationship.coverage) &&
        Objects.equals(this.quantity, inquiredProductRelationship.quantity) &&
        Objects.equals(this.totalPrice, inquiredProductRelationship.totalPrice) &&
        Objects.equals(this.servicePeriod, inquiredProductRelationship.servicePeriod) &&
        Objects.equals(this.relationshipCharacteristic, inquiredProductRelationship.relationshipCharacteristic) &&
        Objects.equals(this.productOfferingRef, inquiredProductRelationship.productOfferingRef) &&
        Objects.equals(this.productSpecificationRef, inquiredProductRelationship.productSpecificationRef) &&
        Objects.equals(this.productRef, inquiredProductRelationship.productRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inquiredProductRef, coverage, quantity, totalPrice, servicePeriod, relationshipCharacteristic, productOfferingRef, productSpecificationRef, productRef);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InquiredProductRelationship {\n");
    sb.append("    inquiredProductRef: ").append(toIndentedString(inquiredProductRef)).append("\n");
    sb.append("    coverage: ").append(toIndentedString(coverage)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
    sb.append("    servicePeriod: ").append(toIndentedString(servicePeriod)).append("\n");
    sb.append("    relationshipCharacteristic: ").append(toIndentedString(relationshipCharacteristic)).append("\n");
    sb.append("    productOfferingRef: ").append(toIndentedString(productOfferingRef)).append("\n");
    sb.append("    productSpecificationRef: ").append(toIndentedString(productSpecificationRef)).append("\n");
    sb.append("    productRef: ").append(toIndentedString(productRef)).append("\n");
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

