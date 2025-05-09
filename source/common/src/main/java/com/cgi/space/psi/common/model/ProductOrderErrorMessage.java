package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ErrorMessage;
import com.cgi.space.psi.common.model.ProductOrderErrorMessage;
import com.cgi.space.psi.common.model.ProductOrderItemRef;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
 * ProductOrderErrorMessage
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ProductOrderErrorMessage.class, name = "ProductOrderErrorMessage")
})

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ProductOrderErrorMessage extends ErrorMessage {

  @JsonProperty("timestamp")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  @JsonProperty("productOrderItem")
  @Valid
  private List<ProductOrderItemRef> productOrderItem = null;

  public ProductOrderErrorMessage timestamp(OffsetDateTime timestamp) {
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

  public ProductOrderErrorMessage productOrderItem(List<ProductOrderItemRef> productOrderItem) {
    this.productOrderItem = productOrderItem;
    return this;
  }

  public ProductOrderErrorMessage addProductOrderItemItem(ProductOrderItemRef productOrderItemItem) {
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
  public List<ProductOrderItemRef> getProductOrderItem() {
    return productOrderItem;
  }

  public void setProductOrderItem(List<ProductOrderItemRef> productOrderItem) {
    this.productOrderItem = productOrderItem;
  }

  public ProductOrderErrorMessage atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public ProductOrderErrorMessage atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public ProductOrderErrorMessage atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public ProductOrderErrorMessage code(String code) {
    super.setCode(code);
    return this;
  }

  public ProductOrderErrorMessage reason(String reason) {
    super.setReason(reason);
    return this;
  }

  public ProductOrderErrorMessage message(String message) {
    super.setMessage(message);
    return this;
  }

  public ProductOrderErrorMessage status(String status) {
    super.setStatus(status);
    return this;
  }

  public ProductOrderErrorMessage referenceError(String referenceError) {
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
    ProductOrderErrorMessage productOrderErrorMessage = (ProductOrderErrorMessage) o;
    return Objects.equals(this.timestamp, productOrderErrorMessage.timestamp) &&
        Objects.equals(this.productOrderItem, productOrderErrorMessage.productOrderItem) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, productOrderItem, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOrderErrorMessage {\n");
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

