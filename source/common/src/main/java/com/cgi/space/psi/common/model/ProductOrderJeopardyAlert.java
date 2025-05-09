package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.JeopardyAlert;
import com.cgi.space.psi.common.model.ProductOrderItemRef;
import com.cgi.space.psi.common.model.ProductOrderJeopardyAlert;
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
 * ProductOrderJeopardyAlert
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = JeopardyAlert.class, name = "JeopardyAlert"),
  @JsonSubTypes.Type(value = ProductOrderJeopardyAlert.class, name = "ProductOrderJeopardyAlert")
})

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ProductOrderJeopardyAlert extends JeopardyAlert {

  @JsonProperty("productOrderItem")
  @Valid
  private List<ProductOrderItemRef> productOrderItem = null;

  public ProductOrderJeopardyAlert productOrderItem(List<ProductOrderItemRef> productOrderItem) {
    this.productOrderItem = productOrderItem;
    return this;
  }

  public ProductOrderJeopardyAlert addProductOrderItemItem(ProductOrderItemRef productOrderItemItem) {
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
  public List<ProductOrderItemRef> getProductOrderItem() {
    return productOrderItem;
  }

  public void setProductOrderItem(List<ProductOrderItemRef> productOrderItem) {
    this.productOrderItem = productOrderItem;
  }

  public ProductOrderJeopardyAlert atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public ProductOrderJeopardyAlert atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public ProductOrderJeopardyAlert atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public ProductOrderJeopardyAlert id(String id) {
    super.setId(id);
    return this;
  }

  public ProductOrderJeopardyAlert alertDate(OffsetDateTime alertDate) {
    super.setAlertDate(alertDate);
    return this;
  }

  public ProductOrderJeopardyAlert name(String name) {
    super.setName(name);
    return this;
  }

  public ProductOrderJeopardyAlert jeopardyType(String jeopardyType) {
    super.setJeopardyType(jeopardyType);
    return this;
  }

  public ProductOrderJeopardyAlert exception(String exception) {
    super.setException(exception);
    return this;
  }

  public ProductOrderJeopardyAlert message(String message) {
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
    ProductOrderJeopardyAlert productOrderJeopardyAlert = (ProductOrderJeopardyAlert) o;
    return Objects.equals(this.productOrderItem, productOrderJeopardyAlert.productOrderItem) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productOrderItem, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOrderJeopardyAlert {\n");
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

