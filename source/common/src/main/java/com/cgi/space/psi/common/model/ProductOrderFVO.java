package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AgreementRefFVO;
import com.cgi.space.psi.common.model.BillingAccountRefFVO;
import com.cgi.space.psi.common.model.ExternalIdentifierFVO;
import com.cgi.space.psi.common.model.InitialProductOrderStateType;
import com.cgi.space.psi.common.model.NoteFVO;
import com.cgi.space.psi.common.model.OrderPriceFVO;
import com.cgi.space.psi.common.model.OrderRelationshipFVO;
import com.cgi.space.psi.common.model.PaymentRefFVO;
import com.cgi.space.psi.common.model.ProductOfferingQualificationRefFVO;
import com.cgi.space.psi.common.model.ProductOrderErrorMessageFVO;
import com.cgi.space.psi.common.model.ProductOrderFVO;
import com.cgi.space.psi.common.model.ProductOrderItemFVO;
import com.cgi.space.psi.common.model.ProductOrderJeopardyAlertFVO;
import com.cgi.space.psi.common.model.ProductOrderMilestoneFVO;
import com.cgi.space.psi.common.model.QuoteRefFVO;
import com.cgi.space.psi.common.model.RelatedChannelFVO;
import com.cgi.space.psi.common.model.RelatedPartyRefOrPartyRoleRefFVO;
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
 * ProductOrderFVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ProductOrderFVO.class, name = "ProductOrder")
})

@JsonTypeName("ProductOrder_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ProductOrderFVO {

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("id")
  private String id;

  @JsonProperty("agreement")
  @Valid
  private List<AgreementRefFVO> agreement = null;

  @JsonProperty("billingAccount")
  private BillingAccountRefFVO billingAccount;

  @JsonProperty("requestedInitialState")
  private InitialProductOrderStateType requestedInitialState;

  @JsonProperty("category")
  private String category;

  @JsonProperty("channel")
  @Valid
  private List<RelatedChannelFVO> channel = null;

  @JsonProperty("description")
  private String description;

  @JsonProperty("externalId")
  @Valid
  private List<ExternalIdentifierFVO> externalId = null;

  @JsonProperty("note")
  @Valid
  private List<NoteFVO> note = null;

  @JsonProperty("notificationContact")
  private String notificationContact;

  @JsonProperty("orderTotalPrice")
  @Valid
  private List<OrderPriceFVO> orderTotalPrice = null;

  @JsonProperty("payment")
  @Valid
  private List<PaymentRefFVO> payment = null;

  @JsonProperty("orderRelationship")
  @Valid
  private List<OrderRelationshipFVO> orderRelationship = null;

  @JsonProperty("priority")
  private String priority;

  @JsonProperty("productOfferingQualification")
  @Valid
  private List<ProductOfferingQualificationRefFVO> productOfferingQualification = null;

  @JsonProperty("quote")
  @Valid
  private List<QuoteRefFVO> quote = null;

  @JsonProperty("productOrderErrorMessage")
  @Valid
  private List<ProductOrderErrorMessageFVO> productOrderErrorMessage = null;

  @JsonProperty("productOrderJeopardyAlert")
  @Valid
  private List<ProductOrderJeopardyAlertFVO> productOrderJeopardyAlert = null;

  @JsonProperty("productOrderMilestone")
  @Valid
  private List<ProductOrderMilestoneFVO> productOrderMilestone = null;

  @JsonProperty("productOrderItem")
  @Valid
  private List<ProductOrderItemFVO> productOrderItem = new ArrayList<>();

  @JsonProperty("relatedParty")
  @Valid
  private List<RelatedPartyRefOrPartyRoleRefFVO> relatedParty = null;

  @JsonProperty("requestedCompletionDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime requestedCompletionDate;

  @JsonProperty("requestedStartDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime requestedStartDate;

  public ProductOrderFVO atType(String atType) {
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

  public ProductOrderFVO atBaseType(String atBaseType) {
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

  public ProductOrderFVO atSchemaLocation(String atSchemaLocation) {
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

  public ProductOrderFVO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * unique identifier
   * @return id
  */
  
  @Schema(name = "id", description = "unique identifier", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProductOrderFVO agreement(List<AgreementRefFVO> agreement) {
    this.agreement = agreement;
    return this;
  }

  public ProductOrderFVO addAgreementItem(AgreementRefFVO agreementItem) {
    if (this.agreement == null) {
      this.agreement = new ArrayList<>();
    }
    this.agreement.add(agreementItem);
    return this;
  }

  /**
   * A reference to an agreement defined in the context of the product order
   * @return agreement
  */
  @Valid 
  @Schema(name = "agreement", description = "A reference to an agreement defined in the context of the product order", required = false)
  public List<AgreementRefFVO> getAgreement() {
    return agreement;
  }

  public void setAgreement(List<AgreementRefFVO> agreement) {
    this.agreement = agreement;
  }

  public ProductOrderFVO billingAccount(BillingAccountRefFVO billingAccount) {
    this.billingAccount = billingAccount;
    return this;
  }

  /**
   * Get billingAccount
   * @return billingAccount
  */
  @Valid 
  @Schema(name = "billingAccount", required = false)
  public BillingAccountRefFVO getBillingAccount() {
    return billingAccount;
  }

  public void setBillingAccount(BillingAccountRefFVO billingAccount) {
    this.billingAccount = billingAccount;
  }

  public ProductOrderFVO requestedInitialState(InitialProductOrderStateType requestedInitialState) {
    this.requestedInitialState = requestedInitialState;
    return this;
  }

  /**
   * Get requestedInitialState
   * @return requestedInitialState
  */
  @Valid 
  @Schema(name = "requestedInitialState", required = false)
  public InitialProductOrderStateType getRequestedInitialState() {
    return requestedInitialState;
  }

  public void setRequestedInitialState(InitialProductOrderStateType requestedInitialState) {
    this.requestedInitialState = requestedInitialState;
  }

  public ProductOrderFVO category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Used to categorize the order from a business perspective that can be useful for the OM system (e.g. \"enterprise\", \"residential\", ...)
   * @return category
  */
  
  @Schema(name = "category", description = "Used to categorize the order from a business perspective that can be useful for the OM system (e.g. \"enterprise\", \"residential\", ...)", required = false)
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public ProductOrderFVO channel(List<RelatedChannelFVO> channel) {
    this.channel = channel;
    return this;
  }

  public ProductOrderFVO addChannelItem(RelatedChannelFVO channelItem) {
    if (this.channel == null) {
      this.channel = new ArrayList<>();
    }
    this.channel.add(channelItem);
    return this;
  }

  /**
   * Get channel
   * @return channel
  */
  @Valid 
  @Schema(name = "channel", required = false)
  public List<RelatedChannelFVO> getChannel() {
    return channel;
  }

  public void setChannel(List<RelatedChannelFVO> channel) {
    this.channel = channel;
  }

  public ProductOrderFVO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the product order
   * @return description
  */
  
  @Schema(name = "description", description = "Description of the product order", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductOrderFVO externalId(List<ExternalIdentifierFVO> externalId) {
    this.externalId = externalId;
    return this;
  }

  public ProductOrderFVO addExternalIdItem(ExternalIdentifierFVO externalIdItem) {
    if (this.externalId == null) {
      this.externalId = new ArrayList<>();
    }
    this.externalId.add(externalIdItem);
    return this;
  }

  /**
   * Get externalId
   * @return externalId
  */
  @Valid 
  @Schema(name = "externalId", required = false)
  public List<ExternalIdentifierFVO> getExternalId() {
    return externalId;
  }

  public void setExternalId(List<ExternalIdentifierFVO> externalId) {
    this.externalId = externalId;
  }

  public ProductOrderFVO note(List<NoteFVO> note) {
    this.note = note;
    return this;
  }

  public ProductOrderFVO addNoteItem(NoteFVO noteItem) {
    if (this.note == null) {
      this.note = new ArrayList<>();
    }
    this.note.add(noteItem);
    return this;
  }

  /**
   * Get note
   * @return note
  */
  @Valid 
  @Schema(name = "note", required = false)
  public List<NoteFVO> getNote() {
    return note;
  }

  public void setNote(List<NoteFVO> note) {
    this.note = note;
  }

  public ProductOrderFVO notificationContact(String notificationContact) {
    this.notificationContact = notificationContact;
    return this;
  }

  /**
   * Contact attached to the order to send back information regarding this order
   * @return notificationContact
  */
  
  @Schema(name = "notificationContact", description = "Contact attached to the order to send back information regarding this order", required = false)
  public String getNotificationContact() {
    return notificationContact;
  }

  public void setNotificationContact(String notificationContact) {
    this.notificationContact = notificationContact;
  }

  public ProductOrderFVO orderTotalPrice(List<OrderPriceFVO> orderTotalPrice) {
    this.orderTotalPrice = orderTotalPrice;
    return this;
  }

  public ProductOrderFVO addOrderTotalPriceItem(OrderPriceFVO orderTotalPriceItem) {
    if (this.orderTotalPrice == null) {
      this.orderTotalPrice = new ArrayList<>();
    }
    this.orderTotalPrice.add(orderTotalPriceItem);
    return this;
  }

  /**
   * Get orderTotalPrice
   * @return orderTotalPrice
  */
  @Valid 
  @Schema(name = "orderTotalPrice", required = false)
  public List<OrderPriceFVO> getOrderTotalPrice() {
    return orderTotalPrice;
  }

  public void setOrderTotalPrice(List<OrderPriceFVO> orderTotalPrice) {
    this.orderTotalPrice = orderTotalPrice;
  }

  public ProductOrderFVO payment(List<PaymentRefFVO> payment) {
    this.payment = payment;
    return this;
  }

  public ProductOrderFVO addPaymentItem(PaymentRefFVO paymentItem) {
    if (this.payment == null) {
      this.payment = new ArrayList<>();
    }
    this.payment.add(paymentItem);
    return this;
  }

  /**
   * Get payment
   * @return payment
  */
  @Valid 
  @Schema(name = "payment", required = false)
  public List<PaymentRefFVO> getPayment() {
    return payment;
  }

  public void setPayment(List<PaymentRefFVO> payment) {
    this.payment = payment;
  }

  public ProductOrderFVO orderRelationship(List<OrderRelationshipFVO> orderRelationship) {
    this.orderRelationship = orderRelationship;
    return this;
  }

  public ProductOrderFVO addOrderRelationshipItem(OrderRelationshipFVO orderRelationshipItem) {
    if (this.orderRelationship == null) {
      this.orderRelationship = new ArrayList<>();
    }
    this.orderRelationship.add(orderRelationshipItem);
    return this;
  }

  /**
   * Get orderRelationship
   * @return orderRelationship
  */
  @Valid 
  @Schema(name = "orderRelationship", required = false)
  public List<OrderRelationshipFVO> getOrderRelationship() {
    return orderRelationship;
  }

  public void setOrderRelationship(List<OrderRelationshipFVO> orderRelationship) {
    this.orderRelationship = orderRelationship;
  }

  public ProductOrderFVO priority(String priority) {
    this.priority = priority;
    return this;
  }

  /**
   * A way that can be used by consumers to prioritize orders in OM system (from 0 to 4 : 0 is the highest priority, and 4 the lowest)
   * @return priority
  */
  
  @Schema(name = "priority", description = "A way that can be used by consumers to prioritize orders in OM system (from 0 to 4 : 0 is the highest priority, and 4 the lowest)", required = false)
  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public ProductOrderFVO productOfferingQualification(List<ProductOfferingQualificationRefFVO> productOfferingQualification) {
    this.productOfferingQualification = productOfferingQualification;
    return this;
  }

  public ProductOrderFVO addProductOfferingQualificationItem(ProductOfferingQualificationRefFVO productOfferingQualificationItem) {
    if (this.productOfferingQualification == null) {
      this.productOfferingQualification = new ArrayList<>();
    }
    this.productOfferingQualification.add(productOfferingQualificationItem);
    return this;
  }

  /**
   * Get productOfferingQualification
   * @return productOfferingQualification
  */
  @Valid 
  @Schema(name = "productOfferingQualification", required = false)
  public List<ProductOfferingQualificationRefFVO> getProductOfferingQualification() {
    return productOfferingQualification;
  }

  public void setProductOfferingQualification(List<ProductOfferingQualificationRefFVO> productOfferingQualification) {
    this.productOfferingQualification = productOfferingQualification;
  }

  public ProductOrderFVO quote(List<QuoteRefFVO> quote) {
    this.quote = quote;
    return this;
  }

  public ProductOrderFVO addQuoteItem(QuoteRefFVO quoteItem) {
    if (this.quote == null) {
      this.quote = new ArrayList<>();
    }
    this.quote.add(quoteItem);
    return this;
  }

  /**
   * Get quote
   * @return quote
  */
  @Valid 
  @Schema(name = "quote", required = false)
  public List<QuoteRefFVO> getQuote() {
    return quote;
  }

  public void setQuote(List<QuoteRefFVO> quote) {
    this.quote = quote;
  }

  public ProductOrderFVO productOrderErrorMessage(List<ProductOrderErrorMessageFVO> productOrderErrorMessage) {
    this.productOrderErrorMessage = productOrderErrorMessage;
    return this;
  }

  public ProductOrderFVO addProductOrderErrorMessageItem(ProductOrderErrorMessageFVO productOrderErrorMessageItem) {
    if (this.productOrderErrorMessage == null) {
      this.productOrderErrorMessage = new ArrayList<>();
    }
    this.productOrderErrorMessage.add(productOrderErrorMessageItem);
    return this;
  }

  /**
   * Get productOrderErrorMessage
   * @return productOrderErrorMessage
  */
  @Valid 
  @Schema(name = "productOrderErrorMessage", required = false)
  public List<ProductOrderErrorMessageFVO> getProductOrderErrorMessage() {
    return productOrderErrorMessage;
  }

  public void setProductOrderErrorMessage(List<ProductOrderErrorMessageFVO> productOrderErrorMessage) {
    this.productOrderErrorMessage = productOrderErrorMessage;
  }

  public ProductOrderFVO productOrderJeopardyAlert(List<ProductOrderJeopardyAlertFVO> productOrderJeopardyAlert) {
    this.productOrderJeopardyAlert = productOrderJeopardyAlert;
    return this;
  }

  public ProductOrderFVO addProductOrderJeopardyAlertItem(ProductOrderJeopardyAlertFVO productOrderJeopardyAlertItem) {
    if (this.productOrderJeopardyAlert == null) {
      this.productOrderJeopardyAlert = new ArrayList<>();
    }
    this.productOrderJeopardyAlert.add(productOrderJeopardyAlertItem);
    return this;
  }

  /**
   * Get productOrderJeopardyAlert
   * @return productOrderJeopardyAlert
  */
  @Valid 
  @Schema(name = "productOrderJeopardyAlert", required = false)
  public List<ProductOrderJeopardyAlertFVO> getProductOrderJeopardyAlert() {
    return productOrderJeopardyAlert;
  }

  public void setProductOrderJeopardyAlert(List<ProductOrderJeopardyAlertFVO> productOrderJeopardyAlert) {
    this.productOrderJeopardyAlert = productOrderJeopardyAlert;
  }

  public ProductOrderFVO productOrderMilestone(List<ProductOrderMilestoneFVO> productOrderMilestone) {
    this.productOrderMilestone = productOrderMilestone;
    return this;
  }

  public ProductOrderFVO addProductOrderMilestoneItem(ProductOrderMilestoneFVO productOrderMilestoneItem) {
    if (this.productOrderMilestone == null) {
      this.productOrderMilestone = new ArrayList<>();
    }
    this.productOrderMilestone.add(productOrderMilestoneItem);
    return this;
  }

  /**
   * Get productOrderMilestone
   * @return productOrderMilestone
  */
  @Valid 
  @Schema(name = "productOrderMilestone", required = false)
  public List<ProductOrderMilestoneFVO> getProductOrderMilestone() {
    return productOrderMilestone;
  }

  public void setProductOrderMilestone(List<ProductOrderMilestoneFVO> productOrderMilestone) {
    this.productOrderMilestone = productOrderMilestone;
  }

  public ProductOrderFVO productOrderItem(List<ProductOrderItemFVO> productOrderItem) {
    this.productOrderItem = productOrderItem;
    return this;
  }

  public ProductOrderFVO addProductOrderItemItem(ProductOrderItemFVO productOrderItemItem) {
    if (this.productOrderItem == null) {
      this.productOrderItem = new ArrayList<>();
    }
    this.productOrderItem.add(productOrderItemItem);
    return this;
  }

  /**
   * Get productOrderItem
   * @return productOrderItem
  */
  @NotNull @Valid @Size(min = 1) 
  @Schema(name = "productOrderItem", required = true)
  public List<ProductOrderItemFVO> getProductOrderItem() {
    return productOrderItem;
  }

  public void setProductOrderItem(List<ProductOrderItemFVO> productOrderItem) {
    this.productOrderItem = productOrderItem;
  }

  public ProductOrderFVO relatedParty(List<RelatedPartyRefOrPartyRoleRefFVO> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public ProductOrderFVO addRelatedPartyItem(RelatedPartyRefOrPartyRoleRefFVO relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

  /**
   * Get relatedParty
   * @return relatedParty
  */
  @Valid 
  @Schema(name = "relatedParty", required = false)
  public List<RelatedPartyRefOrPartyRoleRefFVO> getRelatedParty() {
    return relatedParty;
  }

  public void setRelatedParty(List<RelatedPartyRefOrPartyRoleRefFVO> relatedParty) {
    this.relatedParty = relatedParty;
  }

  public ProductOrderFVO requestedCompletionDate(OffsetDateTime requestedCompletionDate) {
    this.requestedCompletionDate = requestedCompletionDate;
    return this;
  }

  /**
   * Requested delivery date from the requestor perspective
   * @return requestedCompletionDate
  */
  @Valid 
  @Schema(name = "requestedCompletionDate", description = "Requested delivery date from the requestor perspective", required = false)
  public OffsetDateTime getRequestedCompletionDate() {
    return requestedCompletionDate;
  }

  public void setRequestedCompletionDate(OffsetDateTime requestedCompletionDate) {
    this.requestedCompletionDate = requestedCompletionDate;
  }

  public ProductOrderFVO requestedStartDate(OffsetDateTime requestedStartDate) {
    this.requestedStartDate = requestedStartDate;
    return this;
  }

  /**
   * Order fulfillment start date wished by the requestor. This is used when, for any reason, requestor cannot allow seller to begin to operationally begin the fulfillment before a date. 
   * @return requestedStartDate
  */
  @Valid 
  @Schema(name = "requestedStartDate", description = "Order fulfillment start date wished by the requestor. This is used when, for any reason, requestor cannot allow seller to begin to operationally begin the fulfillment before a date. ", required = false)
  public OffsetDateTime getRequestedStartDate() {
    return requestedStartDate;
  }

  public void setRequestedStartDate(OffsetDateTime requestedStartDate) {
    this.requestedStartDate = requestedStartDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductOrderFVO productOrderFVO = (ProductOrderFVO) o;
    return Objects.equals(this.atType, productOrderFVO.atType) &&
        Objects.equals(this.atBaseType, productOrderFVO.atBaseType) &&
        Objects.equals(this.atSchemaLocation, productOrderFVO.atSchemaLocation) &&
        Objects.equals(this.id, productOrderFVO.id) &&
        Objects.equals(this.agreement, productOrderFVO.agreement) &&
        Objects.equals(this.billingAccount, productOrderFVO.billingAccount) &&
        Objects.equals(this.requestedInitialState, productOrderFVO.requestedInitialState) &&
        Objects.equals(this.category, productOrderFVO.category) &&
        Objects.equals(this.channel, productOrderFVO.channel) &&
        Objects.equals(this.description, productOrderFVO.description) &&
        Objects.equals(this.externalId, productOrderFVO.externalId) &&
        Objects.equals(this.note, productOrderFVO.note) &&
        Objects.equals(this.notificationContact, productOrderFVO.notificationContact) &&
        Objects.equals(this.orderTotalPrice, productOrderFVO.orderTotalPrice) &&
        Objects.equals(this.payment, productOrderFVO.payment) &&
        Objects.equals(this.orderRelationship, productOrderFVO.orderRelationship) &&
        Objects.equals(this.priority, productOrderFVO.priority) &&
        Objects.equals(this.productOfferingQualification, productOrderFVO.productOfferingQualification) &&
        Objects.equals(this.quote, productOrderFVO.quote) &&
        Objects.equals(this.productOrderErrorMessage, productOrderFVO.productOrderErrorMessage) &&
        Objects.equals(this.productOrderJeopardyAlert, productOrderFVO.productOrderJeopardyAlert) &&
        Objects.equals(this.productOrderMilestone, productOrderFVO.productOrderMilestone) &&
        Objects.equals(this.productOrderItem, productOrderFVO.productOrderItem) &&
        Objects.equals(this.relatedParty, productOrderFVO.relatedParty) &&
        Objects.equals(this.requestedCompletionDate, productOrderFVO.requestedCompletionDate) &&
        Objects.equals(this.requestedStartDate, productOrderFVO.requestedStartDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType, atBaseType, atSchemaLocation, id, agreement, billingAccount, requestedInitialState, category, channel, description, externalId, note, notificationContact, orderTotalPrice, payment, orderRelationship, priority, productOfferingQualification, quote, productOrderErrorMessage, productOrderJeopardyAlert, productOrderMilestone, productOrderItem, relatedParty, requestedCompletionDate, requestedStartDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOrderFVO {\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    agreement: ").append(toIndentedString(agreement)).append("\n");
    sb.append("    billingAccount: ").append(toIndentedString(billingAccount)).append("\n");
    sb.append("    requestedInitialState: ").append(toIndentedString(requestedInitialState)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    channel: ").append(toIndentedString(channel)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    notificationContact: ").append(toIndentedString(notificationContact)).append("\n");
    sb.append("    orderTotalPrice: ").append(toIndentedString(orderTotalPrice)).append("\n");
    sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
    sb.append("    orderRelationship: ").append(toIndentedString(orderRelationship)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    productOfferingQualification: ").append(toIndentedString(productOfferingQualification)).append("\n");
    sb.append("    quote: ").append(toIndentedString(quote)).append("\n");
    sb.append("    productOrderErrorMessage: ").append(toIndentedString(productOrderErrorMessage)).append("\n");
    sb.append("    productOrderJeopardyAlert: ").append(toIndentedString(productOrderJeopardyAlert)).append("\n");
    sb.append("    productOrderMilestone: ").append(toIndentedString(productOrderMilestone)).append("\n");
    sb.append("    productOrderItem: ").append(toIndentedString(productOrderItem)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
    sb.append("    requestedCompletionDate: ").append(toIndentedString(requestedCompletionDate)).append("\n");
    sb.append("    requestedStartDate: ").append(toIndentedString(requestedStartDate)).append("\n");
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

