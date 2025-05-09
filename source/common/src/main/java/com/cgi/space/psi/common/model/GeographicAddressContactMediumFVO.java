package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.ContactMediumFVO;
import com.cgi.space.psi.common.model.EmailContactMediumFVO;
import com.cgi.space.psi.common.model.FaxContactMediumFVO;
import com.cgi.space.psi.common.model.GeographicAddressContactMediumFVO;
import com.cgi.space.psi.common.model.GeographicAddressRefFVO;
import com.cgi.space.psi.common.model.PhoneContactMediumFVO;
import com.cgi.space.psi.common.model.SocialContactMediumFVO;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * GeographicAddressContactMediumFVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ContactMediumFVO.class, name = "ContactMedium"),
  @JsonSubTypes.Type(value = EmailContactMediumFVO.class, name = "EmailContactMedium"),
  @JsonSubTypes.Type(value = FaxContactMediumFVO.class, name = "FaxContactMedium"),
  @JsonSubTypes.Type(value = GeographicAddressContactMediumFVO.class, name = "GeographicAddressContactMedium"),
  @JsonSubTypes.Type(value = PhoneContactMediumFVO.class, name = "PhoneContactMedium"),
  @JsonSubTypes.Type(value = SocialContactMediumFVO.class, name = "SocialContactMedium")
})

@JsonTypeName("GeographicAddressContactMedium_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GeographicAddressContactMediumFVO extends ContactMediumFVO {

  @JsonProperty("city")
  private String city;

  @JsonProperty("country")
  private String country;

  @JsonProperty("postCode")
  private String postCode;

  @JsonProperty("stateOrProvince")
  private String stateOrProvince;

  @JsonProperty("street1")
  private String street1;

  @JsonProperty("street2")
  private String street2;

  @JsonProperty("geographicAddress")
  private GeographicAddressRefFVO geographicAddress;

  public GeographicAddressContactMediumFVO city(String city) {
    this.city = city;
    return this;
  }

  /**
   * The city
   * @return city
  */
  
  @Schema(name = "city", description = "The city", required = false)
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public GeographicAddressContactMediumFVO country(String country) {
    this.country = country;
    return this;
  }

  /**
   * The country
   * @return country
  */
  
  @Schema(name = "country", description = "The country", required = false)
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public GeographicAddressContactMediumFVO postCode(String postCode) {
    this.postCode = postCode;
    return this;
  }

  /**
   * Postcode
   * @return postCode
  */
  
  @Schema(name = "postCode", description = "Postcode", required = false)
  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public GeographicAddressContactMediumFVO stateOrProvince(String stateOrProvince) {
    this.stateOrProvince = stateOrProvince;
    return this;
  }

  /**
   * State or province
   * @return stateOrProvince
  */
  
  @Schema(name = "stateOrProvince", description = "State or province", required = false)
  public String getStateOrProvince() {
    return stateOrProvince;
  }

  public void setStateOrProvince(String stateOrProvince) {
    this.stateOrProvince = stateOrProvince;
  }

  public GeographicAddressContactMediumFVO street1(String street1) {
    this.street1 = street1;
    return this;
  }

  /**
   * Describes the street
   * @return street1
  */
  
  @Schema(name = "street1", description = "Describes the street", required = false)
  public String getStreet1() {
    return street1;
  }

  public void setStreet1(String street1) {
    this.street1 = street1;
  }

  public GeographicAddressContactMediumFVO street2(String street2) {
    this.street2 = street2;
    return this;
  }

  /**
   * Complementary street description
   * @return street2
  */
  
  @Schema(name = "street2", description = "Complementary street description", required = false)
  public String getStreet2() {
    return street2;
  }

  public void setStreet2(String street2) {
    this.street2 = street2;
  }

  public GeographicAddressContactMediumFVO geographicAddress(GeographicAddressRefFVO geographicAddress) {
    this.geographicAddress = geographicAddress;
    return this;
  }

  /**
   * Get geographicAddress
   * @return geographicAddress
  */
  @Valid 
  @Schema(name = "geographicAddress", required = false)
  public GeographicAddressRefFVO getGeographicAddress() {
    return geographicAddress;
  }

  public void setGeographicAddress(GeographicAddressRefFVO geographicAddress) {
    this.geographicAddress = geographicAddress;
  }

  public GeographicAddressContactMediumFVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public GeographicAddressContactMediumFVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public GeographicAddressContactMediumFVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public GeographicAddressContactMediumFVO id(String id) {
    super.setId(id);
    return this;
  }

  public GeographicAddressContactMediumFVO preferred(Boolean preferred) {
    super.setPreferred(preferred);
    return this;
  }

  public GeographicAddressContactMediumFVO contactType(String contactType) {
    super.setContactType(contactType);
    return this;
  }

  public GeographicAddressContactMediumFVO validFor(TimePeriod validFor) {
    super.setValidFor(validFor);
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
    GeographicAddressContactMediumFVO geographicAddressContactMediumFVO = (GeographicAddressContactMediumFVO) o;
    return Objects.equals(this.city, geographicAddressContactMediumFVO.city) &&
        Objects.equals(this.country, geographicAddressContactMediumFVO.country) &&
        Objects.equals(this.postCode, geographicAddressContactMediumFVO.postCode) &&
        Objects.equals(this.stateOrProvince, geographicAddressContactMediumFVO.stateOrProvince) &&
        Objects.equals(this.street1, geographicAddressContactMediumFVO.street1) &&
        Objects.equals(this.street2, geographicAddressContactMediumFVO.street2) &&
        Objects.equals(this.geographicAddress, geographicAddressContactMediumFVO.geographicAddress) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(city, country, postCode, stateOrProvince, street1, street2, geographicAddress, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeographicAddressContactMediumFVO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    postCode: ").append(toIndentedString(postCode)).append("\n");
    sb.append("    stateOrProvince: ").append(toIndentedString(stateOrProvince)).append("\n");
    sb.append("    street1: ").append(toIndentedString(street1)).append("\n");
    sb.append("    street2: ").append(toIndentedString(street2)).append("\n");
    sb.append("    geographicAddress: ").append(toIndentedString(geographicAddress)).append("\n");
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

