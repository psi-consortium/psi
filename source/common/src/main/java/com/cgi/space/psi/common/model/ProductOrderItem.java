package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AppointmentRef;
import com.cgi.space.psi.common.model.BillingAccountRef;
import com.cgi.space.psi.common.model.ItemActionType;
import com.cgi.space.psi.common.model.Note;
import com.cgi.space.psi.common.model.OrderItemRelationship;
import com.cgi.space.psi.common.model.OrderPrice;
import com.cgi.space.psi.common.model.OrderTerm;
import com.cgi.space.psi.common.model.PaymentRef;
import com.cgi.space.psi.common.model.ProductOfferingQualificationItemRef;
import com.cgi.space.psi.common.model.ProductOfferingQualificationRef;
import com.cgi.space.psi.common.model.ProductOfferingRef;
import com.cgi.space.psi.common.model.ProductOrderItemStateType;
import com.cgi.space.psi.common.model.ProductRefOrValue;
import com.cgi.space.psi.common.model.QuoteItemRef;
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
 * ProductOrderItem
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ProductOrderItem.class, name = "ProductOrderItem")
})

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ProductOrderItem {

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("quantity")
  private Integer quantity;

  @JsonProperty("action")
  private ItemActionType action;

  @JsonProperty("appointment")
  private AppointmentRef appointment;

  @JsonProperty("billingAccount")
  private BillingAccountRef billingAccount;

  @JsonProperty("itemPrice")
  @Valid
  private List<OrderPrice> itemPrice = null;

  @JsonProperty("itemTerm")
  @Valid
  private List<OrderTerm> itemTerm = null;

  @JsonProperty("itemTotalPrice")
  @Valid
  private List<OrderPrice> itemTotalPrice = null;

  @JsonProperty("note")
  @Valid
  private List<Note> note = null;

  @JsonProperty("payment")
  @Valid
  private List<PaymentRef> payment = null;

  @JsonProperty("product")
  private ProductRefOrValue product;

  @JsonProperty("productOffering")
  private ProductOfferingRef productOffering;

  @JsonProperty("productOfferingQualificationItem")
  private ProductOfferingQualificationItemRef productOfferingQualificationItem;

  @JsonProperty("quoteItem")
  private QuoteItemRef quoteItem;

  @JsonProperty("productOrderItem")
  @Valid
  private List<ProductOrderItem> productOrderItem = null;

  @JsonProperty("productOrderItemRelationship")
  @Valid
  private List<OrderItemRelationship> productOrderItemRelationship = null;

  @JsonProperty("state")
  private ProductOrderItemStateType state;

  @JsonProperty("qualification")
  @Valid
  private List<ProductOfferingQualificationRef> qualification = null;

  @JsonProperty("id")
  private String id;

  @JsonProperty("requestedStartDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime requestedStartDate;

  @JsonProperty("requestedEndDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime requestedEndDate;

  public ProductOrderItem atType(String atType) {
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

  public ProductOrderItem atBaseType(String atBaseType) {
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

  public ProductOrderItem atSchemaLocation(String atSchemaLocation) {
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

  public ProductOrderItem quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Quantity ordered
   * @return quantity
  */
  
  @Schema(name = "quantity", description = "Quantity ordered", required = false)
  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public ProductOrderItem action(ItemActionType action) {
    this.action = action;
    return this;
  }

  /**
   * Get action
   * @return action
  */
  @Valid 
  @Schema(name = "action", required = false)
  public ItemActionType getAction() {
    return action;
  }

  public void setAction(ItemActionType action) {
    this.action = action;
  }

  public ProductOrderItem appointment(AppointmentRef appointment) {
    this.appointment = appointment;
    return this;
  }

  /**
   * Get appointment
   * @return appointment
  */
  @Valid 
  @Schema(name = "appointment", required = false)
  public AppointmentRef getAppointment() {
    return appointment;
  }

  public void setAppointment(AppointmentRef appointment) {
    this.appointment = appointment;
  }

  public ProductOrderItem billingAccount(BillingAccountRef billingAccount) {
    this.billingAccount = billingAccount;
    return this;
  }

  /**
   * Get billingAccount
   * @return billingAccount
  */
  @Valid 
  @Schema(name = "billingAccount", required = false)
  public BillingAccountRef getBillingAccount() {
    return billingAccount;
  }

  public void setBillingAccount(BillingAccountRef billingAccount) {
    this.billingAccount = billingAccount;
  }

  public ProductOrderItem itemPrice(List<OrderPrice> itemPrice) {
    this.itemPrice = itemPrice;
    return this;
  }

  public ProductOrderItem addItemPriceItem(OrderPrice itemPriceItem) {
    if (this.itemPrice == null) {
      this.itemPrice = new ArrayList<>();
    }
    this.itemPrice.add(itemPriceItem);
    return this;
  }

  /**
   * Get itemPrice
   * @return itemPrice
  */
  @Valid 
  @Schema(name = "itemPrice", required = false)
  public List<OrderPrice> getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(List<OrderPrice> itemPrice) {
    this.itemPrice = itemPrice;
  }

  public ProductOrderItem itemTerm(List<OrderTerm> itemTerm) {
    this.itemTerm = itemTerm;
    return this;
  }

  public ProductOrderItem addItemTermItem(OrderTerm itemTermItem) {
    if (this.itemTerm == null) {
      this.itemTerm = new ArrayList<>();
    }
    this.itemTerm.add(itemTermItem);
    return this;
  }

  /**
   * Get itemTerm
   * @return itemTerm
  */
  @Valid 
  @Schema(name = "itemTerm", required = false)
  public List<OrderTerm> getItemTerm() {
    return itemTerm;
  }

  public void setItemTerm(List<OrderTerm> itemTerm) {
    this.itemTerm = itemTerm;
  }

  public ProductOrderItem itemTotalPrice(List<OrderPrice> itemTotalPrice) {
    this.itemTotalPrice = itemTotalPrice;
    return this;
  }

  public ProductOrderItem addItemTotalPriceItem(OrderPrice itemTotalPriceItem) {
    if (this.itemTotalPrice == null) {
      this.itemTotalPrice = new ArrayList<>();
    }
    this.itemTotalPrice.add(itemTotalPriceItem);
    return this;
  }

  /**
   * Get itemTotalPrice
   * @return itemTotalPrice
  */
  @Valid 
  @Schema(name = "itemTotalPrice", required = false)
  public List<OrderPrice> getItemTotalPrice() {
    return itemTotalPrice;
  }

  public void setItemTotalPrice(List<OrderPrice> itemTotalPrice) {
    this.itemTotalPrice = itemTotalPrice;
  }

  public ProductOrderItem note(List<Note> note) {
    this.note = note;
    return this;
  }

  public ProductOrderItem addNoteItem(Note noteItem) {
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
  public List<Note> getNote() {
    return note;
  }

  public void setNote(List<Note> note) {
    this.note = note;
  }

  public ProductOrderItem payment(List<PaymentRef> payment) {
    this.payment = payment;
    return this;
  }

  public ProductOrderItem addPaymentItem(PaymentRef paymentItem) {
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
  public List<PaymentRef> getPayment() {
    return payment;
  }

  public void setPayment(List<PaymentRef> payment) {
    this.payment = payment;
  }

  public ProductOrderItem product(ProductRefOrValue product) {
    this.product = product;
    return this;
  }

  /**
   * Get product
   * @return product
  */
  @Valid 
  @Schema(name = "product", required = false)
  public ProductRefOrValue getProduct() {
    return product;
  }

  public void setProduct(ProductRefOrValue product) {
    this.product = product;
  }

  public ProductOrderItem productOffering(ProductOfferingRef productOffering) {
    this.productOffering = productOffering;
    return this;
  }

  /**
   * Get productOffering
   * @return productOffering
  */
  @Valid 
  @Schema(name = "productOffering", required = false)
  public ProductOfferingRef getProductOffering() {
    return productOffering;
  }

  public void setProductOffering(ProductOfferingRef productOffering) {
    this.productOffering = productOffering;
  }

  public ProductOrderItem productOfferingQualificationItem(ProductOfferingQualificationItemRef productOfferingQualificationItem) {
    this.productOfferingQualificationItem = productOfferingQualificationItem;
    return this;
  }

  /**
   * Get productOfferingQualificationItem
   * @return productOfferingQualificationItem
  */
  @Valid 
  @Schema(name = "productOfferingQualificationItem", required = false)
  public ProductOfferingQualificationItemRef getProductOfferingQualificationItem() {
    return productOfferingQualificationItem;
  }

  public void setProductOfferingQualificationItem(ProductOfferingQualificationItemRef productOfferingQualificationItem) {
    this.productOfferingQualificationItem = productOfferingQualificationItem;
  }

  public ProductOrderItem quoteItem(QuoteItemRef quoteItem) {
    this.quoteItem = quoteItem;
    return this;
  }

  /**
   * Get quoteItem
   * @return quoteItem
  */
  @Valid 
  @Schema(name = "quoteItem", required = false)
  public QuoteItemRef getQuoteItem() {
    return quoteItem;
  }

  public void setQuoteItem(QuoteItemRef quoteItem) {
    this.quoteItem = quoteItem;
  }

  public ProductOrderItem productOrderItem(List<ProductOrderItem> productOrderItem) {
    this.productOrderItem = productOrderItem;
    return this;
  }

  public ProductOrderItem addProductOrderItemItem(ProductOrderItem productOrderItemItem) {
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
  @Valid 
  @Schema(name = "productOrderItem", required = false)
  public List<ProductOrderItem> getProductOrderItem() {
    return productOrderItem;
  }

  public void setProductOrderItem(List<ProductOrderItem> productOrderItem) {
    this.productOrderItem = productOrderItem;
  }

  public ProductOrderItem productOrderItemRelationship(List<OrderItemRelationship> productOrderItemRelationship) {
    this.productOrderItemRelationship = productOrderItemRelationship;
    return this;
  }

  public ProductOrderItem addProductOrderItemRelationshipItem(OrderItemRelationship productOrderItemRelationshipItem) {
    if (this.productOrderItemRelationship == null) {
      this.productOrderItemRelationship = new ArrayList<>();
    }
    this.productOrderItemRelationship.add(productOrderItemRelationshipItem);
    return this;
  }

  /**
   * Get productOrderItemRelationship
   * @return productOrderItemRelationship
  */
  @Valid 
  @Schema(name = "productOrderItemRelationship", required = false)
  public List<OrderItemRelationship> getProductOrderItemRelationship() {
    return productOrderItemRelationship;
  }

  public void setProductOrderItemRelationship(List<OrderItemRelationship> productOrderItemRelationship) {
    this.productOrderItemRelationship = productOrderItemRelationship;
  }

  public ProductOrderItem state(ProductOrderItemStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @Valid 
  @Schema(name = "state", required = false)
  public ProductOrderItemStateType getState() {
    return state;
  }

  public void setState(ProductOrderItemStateType state) {
    this.state = state;
  }

  public ProductOrderItem qualification(List<ProductOfferingQualificationRef> qualification) {
    this.qualification = qualification;
    return this;
  }

  public ProductOrderItem addQualificationItem(ProductOfferingQualificationRef qualificationItem) {
    if (this.qualification == null) {
      this.qualification = new ArrayList<>();
    }
    this.qualification.add(qualificationItem);
    return this;
  }

  /**
   * Get qualification
   * @return qualification
  */
  @Valid 
  @Schema(name = "qualification", required = false)
  public List<ProductOfferingQualificationRef> getQualification() {
    return qualification;
  }

  public void setQualification(List<ProductOfferingQualificationRef> qualification) {
    this.qualification = qualification;
  }

  public ProductOrderItem id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the ProductOrder item (generally it is a sequence number 01, 02, 03, ...)
   * @return id
  */
  
  @Schema(name = "id", description = "Identifier of the ProductOrder item (generally it is a sequence number 01, 02, 03, ...)", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProductOrderItem requestedStartDate(OffsetDateTime requestedStartDate) {
    this.requestedStartDate = requestedStartDate;
    return this;
  }

  /**
   * Order fulfillment start date wished by the requestor. This is used when, for any reason, the requestor cannot allow the seller to operationally begin the fulfillment before a date. 
   * @return requestedStartDate
  */
  @Valid 
  @Schema(name = "requestedStartDate", description = "Order fulfillment start date wished by the requestor. This is used when, for any reason, the requestor cannot allow the seller to operationally begin the fulfillment before a date. ", required = false)
  public OffsetDateTime getRequestedStartDate() {
    return requestedStartDate;
  }

  public void setRequestedStartDate(OffsetDateTime requestedStartDate) {
    this.requestedStartDate = requestedStartDate;
  }

  public ProductOrderItem requestedEndDate(OffsetDateTime requestedEndDate) {
    this.requestedEndDate = requestedEndDate;
    return this;
  }

  /**
   * Order fulfillment end date wished by the requestor. This is used when, for any reason, the requestor cannot allow the seller to operationally shutdown before a date.
   * @return requestedEndDate
  */
  @Valid 
  @Schema(name = "requestedEndDate", description = "Order fulfillment end date wished by the requestor. This is used when, for any reason, the requestor cannot allow the seller to operationally shutdown before a date.", required = false)
  public OffsetDateTime getRequestedEndDate() {
    return requestedEndDate;
  }

  public void setRequestedEndDate(OffsetDateTime requestedEndDate) {
    this.requestedEndDate = requestedEndDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductOrderItem productOrderItem = (ProductOrderItem) o;
    return Objects.equals(this.atType, productOrderItem.atType) &&
        Objects.equals(this.atBaseType, productOrderItem.atBaseType) &&
        Objects.equals(this.atSchemaLocation, productOrderItem.atSchemaLocation) &&
        Objects.equals(this.quantity, productOrderItem.quantity) &&
        Objects.equals(this.action, productOrderItem.action) &&
        Objects.equals(this.appointment, productOrderItem.appointment) &&
        Objects.equals(this.billingAccount, productOrderItem.billingAccount) &&
        Objects.equals(this.itemPrice, productOrderItem.itemPrice) &&
        Objects.equals(this.itemTerm, productOrderItem.itemTerm) &&
        Objects.equals(this.itemTotalPrice, productOrderItem.itemTotalPrice) &&
        Objects.equals(this.note, productOrderItem.note) &&
        Objects.equals(this.payment, productOrderItem.payment) &&
        Objects.equals(this.product, productOrderItem.product) &&
        Objects.equals(this.productOffering, productOrderItem.productOffering) &&
        Objects.equals(this.productOfferingQualificationItem, productOrderItem.productOfferingQualificationItem) &&
        Objects.equals(this.quoteItem, productOrderItem.quoteItem) &&
        Objects.equals(this.productOrderItem, productOrderItem.productOrderItem) &&
        Objects.equals(this.productOrderItemRelationship, productOrderItem.productOrderItemRelationship) &&
        Objects.equals(this.state, productOrderItem.state) &&
        Objects.equals(this.qualification, productOrderItem.qualification) &&
        Objects.equals(this.id, productOrderItem.id) &&
        Objects.equals(this.requestedStartDate, productOrderItem.requestedStartDate) &&
        Objects.equals(this.requestedEndDate, productOrderItem.requestedEndDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType, atBaseType, atSchemaLocation, quantity, action, appointment, billingAccount, itemPrice, itemTerm, itemTotalPrice, note, payment, product, productOffering, productOfferingQualificationItem, quoteItem, productOrderItem, productOrderItemRelationship, state, qualification, id, requestedStartDate, requestedEndDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductOrderItem {\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    appointment: ").append(toIndentedString(appointment)).append("\n");
    sb.append("    billingAccount: ").append(toIndentedString(billingAccount)).append("\n");
    sb.append("    itemPrice: ").append(toIndentedString(itemPrice)).append("\n");
    sb.append("    itemTerm: ").append(toIndentedString(itemTerm)).append("\n");
    sb.append("    itemTotalPrice: ").append(toIndentedString(itemTotalPrice)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
    sb.append("    product: ").append(toIndentedString(product)).append("\n");
    sb.append("    productOffering: ").append(toIndentedString(productOffering)).append("\n");
    sb.append("    productOfferingQualificationItem: ").append(toIndentedString(productOfferingQualificationItem)).append("\n");
    sb.append("    quoteItem: ").append(toIndentedString(quoteItem)).append("\n");
    sb.append("    productOrderItem: ").append(toIndentedString(productOrderItem)).append("\n");
    sb.append("    productOrderItemRelationship: ").append(toIndentedString(productOrderItemRelationship)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    qualification: ").append(toIndentedString(qualification)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    requestedStartDate: ").append(toIndentedString(requestedStartDate)).append("\n");
    sb.append("    requestedEndDate: ").append(toIndentedString(requestedEndDate)).append("\n");
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

