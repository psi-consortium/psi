package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.InquiredProductRelationship;
import com.cgi.space.psi.common.model.Note;
import com.cgi.space.psi.common.model.Product;
import com.cgi.space.psi.common.model.ProductOffering;
import com.cgi.space.psi.common.model.ProductSpecification;
import com.cgi.space.psi.common.model.TotalPrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A ranked result of an inquiry. Must contain at least one ProductSpecification, optionally complemented by an offering.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "InquiryResult", description = "A ranked result of an inquiry. Must contain at least one ProductSpecification, optionally complemented by an offering.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class InquiryResult {

  @JsonProperty("id")
  private String id;

  @JsonProperty("priority")
  private BigDecimal priority;

  @JsonProperty("note")
  @Valid
  private List<Note> note = null;

  @JsonProperty("totalPrice")
  @Valid
  private List<TotalPrice> totalPrice = null;

  @JsonProperty("inquiredProductRelationship")
  @Valid
  private List<InquiredProductRelationship> inquiredProductRelationship = null;

  @JsonProperty("productSpecification")
  @Valid
  private List<ProductSpecification> productSpecification = new ArrayList<>();

  @JsonProperty("product")
  @Valid
  private List<Product> product = null;

  @JsonProperty("productOffering")
  @Valid
  private List<ProductOffering> productOffering = null;

  public InquiryResult id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the result within its containing inquiry (may or may not be globally unique, depending on provider implementation)
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Identifier of the result within its containing inquiry (may or may not be globally unique, depending on provider implementation)", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public InquiryResult priority(BigDecimal priority) {
    this.priority = priority;
    return this;
  }

  /**
   * Rank of a InquiryResult compared to others in ascending order (the best result is 1).
   * @return priority
  */
  @Valid 
  @Schema(name = "priority", description = "Rank of a InquiryResult compared to others in ascending order (the best result is 1).", required = false)
  public BigDecimal getPriority() {
    return priority;
  }

  public void setPriority(BigDecimal priority) {
    this.priority = priority;
  }

  public InquiryResult note(List<Note> note) {
    this.note = note;
    return this;
  }

  public InquiryResult addNoteItem(Note noteItem) {
    if (this.note == null) {
      this.note = new ArrayList<>();
    }
    this.note.add(noteItem);
    return this;
  }

  /**
   * The note(s) that are associated to the result.
   * @return note
  */
  @Valid 
  @Schema(name = "note", description = "The note(s) that are associated to the result.", required = false)
  public List<Note> getNote() {
    return note;
  }

  public void setNote(List<Note> note) {
    this.note = note;
  }

  public InquiryResult totalPrice(List<TotalPrice> totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

  public InquiryResult addTotalPriceItem(TotalPrice totalPriceItem) {
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

  public InquiryResult inquiredProductRelationship(List<InquiredProductRelationship> inquiredProductRelationship) {
    this.inquiredProductRelationship = inquiredProductRelationship;
    return this;
  }

  public InquiryResult addInquiredProductRelationshipItem(InquiredProductRelationship inquiredProductRelationshipItem) {
    if (this.inquiredProductRelationship == null) {
      this.inquiredProductRelationship = new ArrayList<>();
    }
    this.inquiredProductRelationship.add(inquiredProductRelationshipItem);
    return this;
  }

  /**
   * Get inquiredProductRelationship
   * @return inquiredProductRelationship
  */
  @Valid 
  @Schema(name = "inquiredProductRelationship", required = false)
  public List<InquiredProductRelationship> getInquiredProductRelationship() {
    return inquiredProductRelationship;
  }

  public void setInquiredProductRelationship(List<InquiredProductRelationship> inquiredProductRelationship) {
    this.inquiredProductRelationship = inquiredProductRelationship;
  }

  public InquiryResult productSpecification(List<ProductSpecification> productSpecification) {
    this.productSpecification = productSpecification;
    return this;
  }

  public InquiryResult addProductSpecificationItem(ProductSpecification productSpecificationItem) {
    if (this.productSpecification == null) {
      this.productSpecification = new ArrayList<>();
    }
    this.productSpecification.add(productSpecificationItem);
    return this;
  }

  /**
   * Get productSpecification
   * @return productSpecification
  */
  @NotNull @Valid 
  @Schema(name = "productSpecification", required = true)
  public List<ProductSpecification> getProductSpecification() {
    return productSpecification;
  }

  public void setProductSpecification(List<ProductSpecification> productSpecification) {
    this.productSpecification = productSpecification;
  }

  public InquiryResult product(List<Product> product) {
    this.product = product;
    return this;
  }

  public InquiryResult addProductItem(Product productItem) {
    if (this.product == null) {
      this.product = new ArrayList<>();
    }
    this.product.add(productItem);
    return this;
  }

  /**
   * Get product
   * @return product
  */
  @Valid 
  @Schema(name = "product", required = false)
  public List<Product> getProduct() {
    return product;
  }

  public void setProduct(List<Product> product) {
    this.product = product;
  }

  public InquiryResult productOffering(List<ProductOffering> productOffering) {
    this.productOffering = productOffering;
    return this;
  }

  public InquiryResult addProductOfferingItem(ProductOffering productOfferingItem) {
    if (this.productOffering == null) {
      this.productOffering = new ArrayList<>();
    }
    this.productOffering.add(productOfferingItem);
    return this;
  }

  /**
   * Get productOffering
   * @return productOffering
  */
  @Valid 
  @Schema(name = "productOffering", required = false)
  public List<ProductOffering> getProductOffering() {
    return productOffering;
  }

  public void setProductOffering(List<ProductOffering> productOffering) {
    this.productOffering = productOffering;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InquiryResult inquiryResult = (InquiryResult) o;
    return Objects.equals(this.id, inquiryResult.id) &&
        Objects.equals(this.priority, inquiryResult.priority) &&
        Objects.equals(this.note, inquiryResult.note) &&
        Objects.equals(this.totalPrice, inquiryResult.totalPrice) &&
        Objects.equals(this.inquiredProductRelationship, inquiryResult.inquiredProductRelationship) &&
        Objects.equals(this.productSpecification, inquiryResult.productSpecification) &&
        Objects.equals(this.product, inquiryResult.product) &&
        Objects.equals(this.productOffering, inquiryResult.productOffering);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, priority, note, totalPrice, inquiredProductRelationship, productSpecification, product, productOffering);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InquiryResult {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
    sb.append("    inquiredProductRelationship: ").append(toIndentedString(inquiredProductRelationship)).append("\n");
    sb.append("    productSpecification: ").append(toIndentedString(productSpecification)).append("\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    productOffering: ").append(toIndentedString(productOffering)).append("\n");
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

