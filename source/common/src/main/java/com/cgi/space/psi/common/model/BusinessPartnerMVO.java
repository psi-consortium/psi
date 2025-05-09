package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AccountRefMVO;
import com.cgi.space.psi.common.model.AgreementRefMVO;
import com.cgi.space.psi.common.model.BusinessPartnerMVO;
import com.cgi.space.psi.common.model.CharacteristicMVO;
import com.cgi.space.psi.common.model.ConsumerMVO;
import com.cgi.space.psi.common.model.ContactMediumMVO;
import com.cgi.space.psi.common.model.CreditProfileMVO;
import com.cgi.space.psi.common.model.PartyRefMVO;
import com.cgi.space.psi.common.model.PartyRoleMVO;
import com.cgi.space.psi.common.model.PartyRoleSpecificationRefMVO;
import com.cgi.space.psi.common.model.PaymentMethodRefMVO;
import com.cgi.space.psi.common.model.ProducerMVO;
import com.cgi.space.psi.common.model.RelatedPartyOrPartyRoleMVO;
import com.cgi.space.psi.common.model.SupplierMVO;
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

@Schema(name = "BusinessPartner_MVO", description = "When business partner is the BusinessPartner ")
@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = BusinessPartnerMVO.class, name = "BusinessPartner"),
  @JsonSubTypes.Type(value = ConsumerMVO.class, name = "Consumer"),
  @JsonSubTypes.Type(value = PartyRoleMVO.class, name = "PartyRole"),
  @JsonSubTypes.Type(value = ProducerMVO.class, name = "Producer"),
  @JsonSubTypes.Type(value = SupplierMVO.class, name = "Supplier")
})

@JsonTypeName("BusinessPartner_MVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class BusinessPartnerMVO extends PartyRoleMVO implements PartyOrPartyRoleMVO {

  public BusinessPartnerMVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public BusinessPartnerMVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public BusinessPartnerMVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public BusinessPartnerMVO name(String name) {
    super.setName(name);
    return this;
  }

  public BusinessPartnerMVO description(String description) {
    super.setDescription(description);
    return this;
  }

  public BusinessPartnerMVO role(String role) {
    super.setRole(role);
    return this;
  }

  public BusinessPartnerMVO engagedParty(PartyRefMVO engagedParty) {
    super.setEngagedParty(engagedParty);
    return this;
  }

  public BusinessPartnerMVO partyRoleSpecification(PartyRoleSpecificationRefMVO partyRoleSpecification) {
    super.setPartyRoleSpecification(partyRoleSpecification);
    return this;
  }

  public BusinessPartnerMVO characteristic(List<CharacteristicMVO> characteristic) {
    super.setCharacteristic(characteristic);
    return this;
  }

  public BusinessPartnerMVO addCharacteristicItem(CharacteristicMVO characteristicItem) {
    super.addCharacteristicItem(characteristicItem);
    return this;
  }

  public BusinessPartnerMVO account(List<AccountRefMVO> account) {
    super.setAccount(account);
    return this;
  }

  public BusinessPartnerMVO addAccountItem(AccountRefMVO accountItem) {
    super.addAccountItem(accountItem);
    return this;
  }

  public BusinessPartnerMVO agreement(List<AgreementRefMVO> agreement) {
    super.setAgreement(agreement);
    return this;
  }

  public BusinessPartnerMVO addAgreementItem(AgreementRefMVO agreementItem) {
    super.addAgreementItem(agreementItem);
    return this;
  }

  public BusinessPartnerMVO contactMedium(List<ContactMediumMVO> contactMedium) {
    super.setContactMedium(contactMedium);
    return this;
  }

  public BusinessPartnerMVO addContactMediumItem(ContactMediumMVO contactMediumItem) {
    super.addContactMediumItem(contactMediumItem);
    return this;
  }

  public BusinessPartnerMVO paymentMethod(List<PaymentMethodRefMVO> paymentMethod) {
    super.setPaymentMethod(paymentMethod);
    return this;
  }

  public BusinessPartnerMVO addPaymentMethodItem(PaymentMethodRefMVO paymentMethodItem) {
    super.addPaymentMethodItem(paymentMethodItem);
    return this;
  }

  public BusinessPartnerMVO creditProfile(List<CreditProfileMVO> creditProfile) {
    super.setCreditProfile(creditProfile);
    return this;
  }

  public BusinessPartnerMVO addCreditProfileItem(CreditProfileMVO creditProfileItem) {
    super.addCreditProfileItem(creditProfileItem);
    return this;
  }

  public BusinessPartnerMVO relatedParty(List<RelatedPartyOrPartyRoleMVO> relatedParty) {
    super.setRelatedParty(relatedParty);
    return this;
  }

  public BusinessPartnerMVO addRelatedPartyItem(RelatedPartyOrPartyRoleMVO relatedPartyItem) {
    super.addRelatedPartyItem(relatedPartyItem);
    return this;
  }

  public BusinessPartnerMVO status(String status) {
    super.setStatus(status);
    return this;
  }

  public BusinessPartnerMVO statusReason(String statusReason) {
    super.setStatusReason(statusReason);
    return this;
  }

  public BusinessPartnerMVO validFor(TimePeriod validFor) {
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
    sb.append("class BusinessPartnerMVO {\n");
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

