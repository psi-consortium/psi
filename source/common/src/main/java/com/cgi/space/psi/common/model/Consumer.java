package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AccountRef;
import com.cgi.space.psi.common.model.AgreementRef;
import com.cgi.space.psi.common.model.BusinessPartner;
import com.cgi.space.psi.common.model.Characteristic;
import com.cgi.space.psi.common.model.Consumer;
import com.cgi.space.psi.common.model.ContactMedium;
import com.cgi.space.psi.common.model.CreditProfile;
import com.cgi.space.psi.common.model.PartyRef;
import com.cgi.space.psi.common.model.PartyRole;
import com.cgi.space.psi.common.model.PartyRoleSpecificationRef;
import com.cgi.space.psi.common.model.PaymentMethodRef;
import com.cgi.space.psi.common.model.Producer;
import com.cgi.space.psi.common.model.RelatedPartyOrPartyRole;
import com.cgi.space.psi.common.model.Supplier;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Consumer
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = BusinessPartner.class, name = "BusinessPartner"),
  @JsonSubTypes.Type(value = Consumer.class, name = "Consumer"),
  @JsonSubTypes.Type(value = PartyRole.class, name = "PartyRole"),
  @JsonSubTypes.Type(value = Producer.class, name = "Producer"),
  @JsonSubTypes.Type(value = Supplier.class, name = "Supplier")
})

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Consumer extends PartyRole implements PartyOrPartyRole {

  public Consumer atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public Consumer atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public Consumer atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public Consumer href(URI href) {
    super.setHref(href);
    return this;
  }

  public Consumer id(String id) {
    super.setId(id);
    return this;
  }

  public Consumer name(String name) {
    super.setName(name);
    return this;
  }

  public Consumer description(String description) {
    super.setDescription(description);
    return this;
  }

  public Consumer role(String role) {
    super.setRole(role);
    return this;
  }

  public Consumer engagedParty(PartyRef engagedParty) {
    super.setEngagedParty(engagedParty);
    return this;
  }

  public Consumer partyRoleSpecification(PartyRoleSpecificationRef partyRoleSpecification) {
    super.setPartyRoleSpecification(partyRoleSpecification);
    return this;
  }

  public Consumer characteristic(List<Characteristic> characteristic) {
    super.setCharacteristic(characteristic);
    return this;
  }

  public Consumer addCharacteristicItem(Characteristic characteristicItem) {
    super.addCharacteristicItem(characteristicItem);
    return this;
  }

  public Consumer account(List<AccountRef> account) {
    super.setAccount(account);
    return this;
  }

  public Consumer addAccountItem(AccountRef accountItem) {
    super.addAccountItem(accountItem);
    return this;
  }

  public Consumer agreement(List<AgreementRef> agreement) {
    super.setAgreement(agreement);
    return this;
  }

  public Consumer addAgreementItem(AgreementRef agreementItem) {
    super.addAgreementItem(agreementItem);
    return this;
  }

  public Consumer contactMedium(List<ContactMedium> contactMedium) {
    super.setContactMedium(contactMedium);
    return this;
  }

  public Consumer addContactMediumItem(ContactMedium contactMediumItem) {
    super.addContactMediumItem(contactMediumItem);
    return this;
  }

  public Consumer paymentMethod(List<PaymentMethodRef> paymentMethod) {
    super.setPaymentMethod(paymentMethod);
    return this;
  }

  public Consumer addPaymentMethodItem(PaymentMethodRef paymentMethodItem) {
    super.addPaymentMethodItem(paymentMethodItem);
    return this;
  }

  public Consumer creditProfile(List<CreditProfile> creditProfile) {
    super.setCreditProfile(creditProfile);
    return this;
  }

  public Consumer addCreditProfileItem(CreditProfile creditProfileItem) {
    super.addCreditProfileItem(creditProfileItem);
    return this;
  }

  public Consumer relatedParty(List<RelatedPartyOrPartyRole> relatedParty) {
    super.setRelatedParty(relatedParty);
    return this;
  }

  public Consumer addRelatedPartyItem(RelatedPartyOrPartyRole relatedPartyItem) {
    super.addRelatedPartyItem(relatedPartyItem);
    return this;
  }

  public Consumer status(String status) {
    super.setStatus(status);
    return this;
  }

  public Consumer statusReason(String statusReason) {
    super.setStatusReason(statusReason);
    return this;
  }

  public Consumer validFor(TimePeriod validFor) {
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
    sb.append("class Consumer {\n");
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

