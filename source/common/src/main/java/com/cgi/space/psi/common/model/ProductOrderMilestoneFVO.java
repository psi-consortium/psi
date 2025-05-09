package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.MilestoneFVO;
import com.cgi.space.psi.common.model.ProductOrderItemRefFVO;
import com.cgi.space.psi.common.model.ProductOrderMilestoneFVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
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
 * ProductOrderMilestoneFVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = MilestoneFVO.class, name = "Milestone"),
  @JsonSubTypes.Type(value = ProductOrderMilestoneFVO.class, name = "ProductOrderMilestone")
})

@JsonTypeName("ProductOrderMilestone_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ProductOrderMilestoneFVO extends MilestoneFVO {

  @JsonProperty("productOrderItem")
  @Valid
  private List<ProductOrderItemRefFVO> productOrderItem = null;

  public ProductOrderMilestoneFVO productOrderItem(List<ProductOrderItemRefFVO> productOrderItem) {
    this.productOrderItem = productOrderItem;
    return this;
  }

  public ProductOrderMilestoneFVO addProductOrderItemItem(ProductOrderItemRefFVO productOrderItemItem) {
    if (this.productOrderItem == null) {
      this.productOrderItem = new ArrayList<>();
    }
    this.productOrderItem.add(productOrderItemItem);
    return this;
  }

  /**
   * A list of order item references corresponded to this alert
   * @return productOrderItem
  */
  @Valid 
  @Schema(name = "productOrderItem", description = "A list of order item references corresponded to this alert", required = false)
  public List<ProductOrderItemRefFVO> getProductOrderItem() {
    return productOrderItem;
  }

  public void setProductOrderItem(List<ProductOrderItemRefFVO> productOrderItem) {
    this.productOrderItem = productOrderItem;
  }

  public ProductOrderMilestoneFVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public ProductOrderMilestoneFVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public ProductOrderMilestoneFVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public ProductOrderMilestoneFVO description(String description) {
    super.setDescription(description);
    return this;
  }

  public ProductOrderMilestoneFVO id(String id) {
    super.setId(id);
    return this;
  }

  public ProductOrderMilestoneFVO status(StatusEnum status) {
    super.setStatus(status);
    return this;
  }

  public ProductOrderMilestoneFVO milestoneDate(OffsetDateTime milestoneDate) {
    super.setMilestoneDate(milestoneDate);
    return this;
  }

  public ProductOrderMilestoneFVO name(String name) {
    super.setName(name);
    return this;
  }

  public ProductOrderMilestoneFVO message(String message) {
    super.setMessage(message);
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
    ProductOrderMilestoneFVO productOrderMilestoneFVO = (ProductOrderMilestoneFVO) o;
    return Objects.equals(this.productOrderItem, productOrderMilestoneFVO.productOrderItem) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productOrderItem, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOrderMilestoneFVO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    productOrderItem: ").append(toIndentedString(productOrderItem)).append("\n");
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

