package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AccountRefFVO;
import com.cgi.space.psi.common.model.AgreementRefFVO;
import com.cgi.space.psi.common.model.BusinessPartnerFVO;
import com.cgi.space.psi.common.model.CharacteristicFVO;
import com.cgi.space.psi.common.model.ConsumerFVO;
import com.cgi.space.psi.common.model.ContactMediumFVO;
import com.cgi.space.psi.common.model.CreditProfileFVO;
import com.cgi.space.psi.common.model.PartyRefFVO;
import com.cgi.space.psi.common.model.PartyRoleFVO;
import com.cgi.space.psi.common.model.PartyRoleSpecificationRefFVO;
import com.cgi.space.psi.common.model.PaymentMethodRefFVO;
import com.cgi.space.psi.common.model.ProducerFVO;
import com.cgi.space.psi.common.model.RelatedPartyOrPartyRoleFVO;
import com.cgi.space.psi.common.model.SupplierFVO;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * When business partner is the BusinessPartner 
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "BusinessPartner_FVO", description = "When business partner is the BusinessPartner ")
@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = BusinessPartnerFVO.class, name = "BusinessPartner"),
  @JsonSubTypes.Type(value = ConsumerFVO.class, name = "Consumer"),
  @JsonSubTypes.Type(value = PartyRoleFVO.class, name = "PartyRole"),
  @JsonSubTypes.Type(value = ProducerFVO.class, name = "Producer"),
  @JsonSubTypes.Type(value = SupplierFVO.class, name = "Supplier")
})

@JsonTypeName("BusinessPartner_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class BusinessPartnerFVO extends PartyRoleFVO implements PartyOrPartyRoleFVO {

  public BusinessPartnerFVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public BusinessPartnerFVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public BusinessPartnerFVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public BusinessPartnerFVO id(String id) {
    super.setId(id);
    return this;
  }

  public BusinessPartnerFVO name(String name) {
    super.setName(name);
    return this;
  }

  public BusinessPartnerFVO description(String description) {
    super.setDescription(description);
    return this;
  }

  public BusinessPartnerFVO role(String role) {
    super.setRole(role);
    return this;
  }

  public BusinessPartnerFVO engagedParty(PartyRefFVO engagedParty) {
    super.setEngagedParty(engagedParty);
    return this;
  }

  public BusinessPartnerFVO partyRoleSpecification(PartyRoleSpecificationRefFVO partyRoleSpecification) {
    super.setPartyRoleSpecification(partyRoleSpecification);
    return this;
  }

  public BusinessPartnerFVO characteristic(List<CharacteristicFVO> characteristic) {
    super.setCharacteristic(characteristic);
    return this;
  }

  public BusinessPartnerFVO addCharacteristicItem(CharacteristicFVO characteristicItem) {
    super.addCharacteristicItem(characteristicItem);
    return this;
  }

  public BusinessPartnerFVO account(List<AccountRefFVO> account) {
    super.setAccount(account);
    return this;
  }

  public BusinessPartnerFVO addAccountItem(AccountRefFVO accountItem) {
    super.addAccountItem(accountItem);
    return this;
  }

  public BusinessPartnerFVO agreement(List<AgreementRefFVO> agreement) {
    super.setAgreement(agreement);
    return this;
  }

  public BusinessPartnerFVO addAgreementItem(AgreementRefFVO agreementItem) {
    super.addAgreementItem(agreementItem);
    return this;
  }

  public BusinessPartnerFVO contactMedium(List<ContactMediumFVO> contactMedium) {
    super.setContactMedium(contactMedium);
    return this;
  }

  public BusinessPartnerFVO addContactMediumItem(ContactMediumFVO contactMediumItem) {
    super.addContactMediumItem(contactMediumItem);
    return this;
  }

  public BusinessPartnerFVO paymentMethod(List<PaymentMethodRefFVO> paymentMethod) {
    super.setPaymentMethod(paymentMethod);
    return this;
  }

  public BusinessPartnerFVO addPaymentMethodItem(PaymentMethodRefFVO paymentMethodItem) {
    super.addPaymentMethodItem(paymentMethodItem);
    return this;
  }

  public BusinessPartnerFVO creditProfile(List<CreditProfileFVO> creditProfile) {
    super.setCreditProfile(creditProfile);
    return this;
  }

  public BusinessPartnerFVO addCreditProfileItem(CreditProfileFVO creditProfileItem) {
    super.addCreditProfileItem(creditProfileItem);
    return this;
  }

  public BusinessPartnerFVO relatedParty(List<RelatedPartyOrPartyRoleFVO> relatedParty) {
    super.setRelatedParty(relatedParty);
    return this;
  }

  public BusinessPartnerFVO addRelatedPartyItem(RelatedPartyOrPartyRoleFVO relatedPartyItem) {
    super.addRelatedPartyItem(relatedPartyItem);
    return this;
  }

  public BusinessPartnerFVO status(String status) {
    super.setStatus(status);
    return this;
  }

  public BusinessPartnerFVO statusReason(String statusReason) {
    super.setStatusReason(statusReason);
    return this;
  }

  public BusinessPartnerFVO validFor(TimePeriod validFor) {
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
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessPartnerFVO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

