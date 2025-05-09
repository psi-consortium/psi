package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.CustomerInquiryMVO;
import com.cgi.space.psi.common.model.InquiredProvider;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * CustomerInquiryMVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = CustomerInquiryMVO.class, name = "CustomerInquiry")
})

@JsonTypeName("CustomerInquiry_MVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CustomerInquiryMVO {

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("inquiredProvider")
  @Valid
  private List<InquiredProvider> inquiredProvider = null;

  public CustomerInquiryMVO atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class Extensible name
   * @return atType
  */
  @NotNull 
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class Extensible name", required = true)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  public CustomerInquiryMVO atBaseType(String atBaseType) {
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

  public CustomerInquiryMVO atSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships
   * @return atSchemaLocation
  */
  
  @Schema(name = "@schemaLocation", description = "A URI to a JSON-Schema file that defines additional attributes and relationships", required = false)
  public String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public CustomerInquiryMVO inquiredProvider(List<InquiredProvider> inquiredProvider) {
    this.inquiredProvider = inquiredProvider;
    return this;
  }

  public CustomerInquiryMVO addInquiredProviderItem(InquiredProvider inquiredProviderItem) {
    if (this.inquiredProvider == null) {
      this.inquiredProvider = new ArrayList<>();
    }
    this.inquiredProvider.add(inquiredProviderItem);
    return this;
  }

  /**
   * The list of providers that are inquired to respond with a product offering.
   * @return inquiredProvider
  */
  @Valid 
  @Schema(name = "inquiredProvider", description = "The list of providers that are inquired to respond with a product offering.", required = false)
  public List<InquiredProvider> getInquiredProvider() {
    return inquiredProvider;
  }

  public void setInquiredProvider(List<InquiredProvider> inquiredProvider) {
    this.inquiredProvider = inquiredProvider;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerInquiryMVO customerInquiryMVO = (CustomerInquiryMVO) o;
    return Objects.equals(this.atType, customerInquiryMVO.atType) &&
        Objects.equals(this.atBaseType, customerInquiryMVO.atBaseType) &&
        Objects.equals(this.atSchemaLocation, customerInquiryMVO.atSchemaLocation) &&
        Objects.equals(this.inquiredProvider, customerInquiryMVO.inquiredProvider);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType, atBaseType, atSchemaLocation, inquiredProvider);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerInquiryMVO {\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    inquiredProvider: ").append(toIndentedString(inquiredProvider)).append("\n");
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

