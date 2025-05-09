package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ErrorMessageMVO;
import com.cgi.space.psi.common.model.ProductOrderErrorMessageMVO;
import com.cgi.space.psi.common.model.ProductOrderItemRefMVO;
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
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ProductOrderErrorMessageMVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ProductOrderErrorMessageMVO.class, name = "ProductOrderErrorMessage")
})

@JsonTypeName("ProductOrderErrorMessage_MVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ProductOrderErrorMessageMVO extends ErrorMessageMVO {

  @JsonProperty("timestamp")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  @JsonProperty("productOrderItem")
  @Valid
  private List<ProductOrderItemRefMVO> productOrderItem = null;

  public ProductOrderErrorMessageMVO timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Date when the error happened
   * @return timestamp
  */
  @Valid 
  @Schema(name = "timestamp", description = "Date when the error happened", required = false)
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public ProductOrderErrorMessageMVO productOrderItem(List<ProductOrderItemRefMVO> productOrderItem) {
    this.productOrderItem = productOrderItem;
    return this;
  }

  public ProductOrderErrorMessageMVO addProductOrderItemItem(ProductOrderItemRefMVO productOrderItemItem) {
    if (this.productOrderItem == null) {
      this.productOrderItem = new ArrayList<>();
    }
    this.productOrderItem.add(productOrderItemItem);
    return this;
  }

  /**
   * A list of order item references corresponded to this error
   * @return productOrderItem
  */
  @Valid 
  @Schema(name = "productOrderItem", description = "A list of order item references corresponded to this error", required = false)
  public List<ProductOrderItemRefMVO> getProductOrderItem() {
    return productOrderItem;
  }

  public void setProductOrderItem(List<ProductOrderItemRefMVO> productOrderItem) {
    this.productOrderItem = productOrderItem;
  }

  public ProductOrderErrorMessageMVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public ProductOrderErrorMessageMVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public ProductOrderErrorMessageMVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public ProductOrderErrorMessageMVO code(String code) {
    super.setCode(code);
    return this;
  }

  public ProductOrderErrorMessageMVO reason(String reason) {
    super.setReason(reason);
    return this;
  }

  public ProductOrderErrorMessageMVO message(String message) {
    super.setMessage(message);
    return this;
  }

  public ProductOrderErrorMessageMVO status(String status) {
    super.setStatus(status);
    return this;
  }

  public ProductOrderErrorMessageMVO referenceError(String referenceError) {
    super.setReferenceError(referenceError);
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
    ProductOrderErrorMessageMVO productOrderErrorMessageMVO = (ProductOrderErrorMessageMVO) o;
    return Objects.equals(this.timestamp, productOrderErrorMessageMVO.timestamp) &&
        Objects.equals(this.productOrderItem, productOrderErrorMessageMVO.productOrderItem) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, productOrderItem, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOrderErrorMessageMVO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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

