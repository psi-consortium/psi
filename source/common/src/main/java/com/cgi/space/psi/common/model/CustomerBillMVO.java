package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AppliedPayment;
import com.cgi.space.psi.common.model.AttachmentOrDocumentRef;
import com.cgi.space.psi.common.model.BillCycleRef;
import com.cgi.space.psi.common.model.BillingAccountRef;
import com.cgi.space.psi.common.model.CustomerBillMVO;
import com.cgi.space.psi.common.model.CustomerBillRunType;
import com.cgi.space.psi.common.model.CustomerBillStateType;
import com.cgi.space.psi.common.model.FinancialAccountRef;
import com.cgi.space.psi.common.model.Money;
import com.cgi.space.psi.common.model.PaymentMethodRef;
import com.cgi.space.psi.common.model.ProductOrderRef;
import com.cgi.space.psi.common.model.RelatedPartyRefOrPartyRoleRef;
import com.cgi.space.psi.common.model.TaxItem;
import com.cgi.space.psi.common.model.TimePeriod;
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
 * CustomerBillMVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = CustomerBillMVO.class, name = "CustomerBill")
})

@JsonTypeName("CustomerBill_MVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CustomerBillMVO {

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("amountDue")
  private Money amountDue;

  @JsonProperty("appliedPayment")
  @Valid
  private List<AppliedPayment> appliedPayment = null;

  @JsonProperty("billDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime billDate;

  @JsonProperty("billDocument")
  @Valid
  private List<AttachmentOrDocumentRef> billDocument = null;

  @JsonProperty("billNo")
  private String billNo;

  @JsonProperty("billingAccount")
  private BillingAccountRef billingAccount;

  @JsonProperty("billingPeriod")
  private TimePeriod billingPeriod;

  @JsonProperty("billCycle")
  private BillCycleRef billCycle;

  @JsonProperty("category")
  private String category;

  @JsonProperty("financialAccount")
  private FinancialAccountRef financialAccount;

  @JsonProperty("lastUpdate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime lastUpdate;

  @JsonProperty("nextBillDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime nextBillDate;

  @JsonProperty("paymentDueDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime paymentDueDate;

  @JsonProperty("paymentMethod")
  private PaymentMethodRef paymentMethod;

  @JsonProperty("relatedParty")
  @Valid
  private List<RelatedPartyRefOrPartyRoleRef> relatedParty = null;

  @JsonProperty("remainingAmount")
  private Money remainingAmount;

  @JsonProperty("runType")
  private CustomerBillRunType runType;

  @JsonProperty("taxExcludedAmount")
  private Money taxExcludedAmount;

  @JsonProperty("taxIncludedAmount")
  private Money taxIncludedAmount;

  @JsonProperty("taxItem")
  @Valid
  private List<TaxItem> taxItem = null;

  @JsonProperty("state")
  private CustomerBillStateType state;

  @JsonProperty("productOrder")
  private ProductOrderRef productOrder;

  public CustomerBillMVO atType(String atType) {
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

  public CustomerBillMVO atBaseType(String atBaseType) {
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

  public CustomerBillMVO atSchemaLocation(String atSchemaLocation) {
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

  public CustomerBillMVO amountDue(Money amountDue) {
    this.amountDue = amountDue;
    return this;
  }

  /**
   * Get amountDue
   * @return amountDue
  */
  @Valid 
  @Schema(name = "amountDue", required = false)
  public Money getAmountDue() {
    return amountDue;
  }

  public void setAmountDue(Money amountDue) {
    this.amountDue = amountDue;
  }

  public CustomerBillMVO appliedPayment(List<AppliedPayment> appliedPayment) {
    this.appliedPayment = appliedPayment;
    return this;
  }

  public CustomerBillMVO addAppliedPaymentItem(AppliedPayment appliedPaymentItem) {
    if (this.appliedPayment == null) {
      this.appliedPayment = new ArrayList<>();
    }
    this.appliedPayment.add(appliedPaymentItem);
    return this;
  }

  /**
   * Applied payment is a payment associated with the bill. There may be a partial payment, then there should be several applied payments available. On the other hand, more than one bill could be payed by one payment. In general there is an n:m relation between payment and bill.
   * @return appliedPayment
  */
  @Valid 
  @Schema(name = "appliedPayment", description = "Applied payment is a payment associated with the bill. There may be a partial payment, then there should be several applied payments available. On the other hand, more than one bill could be payed by one payment. In general there is an n:m relation between payment and bill.", required = false)
  public List<AppliedPayment> getAppliedPayment() {
    return appliedPayment;
  }

  public void setAppliedPayment(List<AppliedPayment> appliedPayment) {
    this.appliedPayment = appliedPayment;
  }

  public CustomerBillMVO billDate(OffsetDateTime billDate) {
    this.billDate = billDate;
    return this;
  }

  /**
   * Bill date, external customer view (in consequence: different to the production date of the bill)
   * @return billDate
  */
  @Valid 
  @Schema(name = "billDate", description = "Bill date, external customer view (in consequence: different to the production date of the bill)", required = false)
  public OffsetDateTime getBillDate() {
    return billDate;
  }

  public void setBillDate(OffsetDateTime billDate) {
    this.billDate = billDate;
  }

  public CustomerBillMVO billDocument(List<AttachmentOrDocumentRef> billDocument) {
    this.billDocument = billDocument;
    return this;
  }

  public CustomerBillMVO addBillDocumentItem(AttachmentOrDocumentRef billDocumentItem) {
    if (this.billDocument == null) {
      this.billDocument = new ArrayList<>();
    }
    this.billDocument.add(billDocumentItem);
    return this;
  }

  /**
   * Get billDocument
   * @return billDocument
  */
  @Valid 
  @Schema(name = "billDocument", required = false)
  public List<AttachmentOrDocumentRef> getBillDocument() {
    return billDocument;
  }

  public void setBillDocument(List<AttachmentOrDocumentRef> billDocument) {
    this.billDocument = billDocument;
  }

  public CustomerBillMVO billNo(String billNo) {
    this.billNo = billNo;
    return this;
  }

  /**
   * Bill reference known by the customer or the party and displayed on the bill. Could be different from the id
   * @return billNo
  */
  
  @Schema(name = "billNo", description = "Bill reference known by the customer or the party and displayed on the bill. Could be different from the id", required = false)
  public String getBillNo() {
    return billNo;
  }

  public void setBillNo(String billNo) {
    this.billNo = billNo;
  }

  public CustomerBillMVO billingAccount(BillingAccountRef billingAccount) {
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

  public CustomerBillMVO billingPeriod(TimePeriod billingPeriod) {
    this.billingPeriod = billingPeriod;
    return this;
  }

  /**
   * Get billingPeriod
   * @return billingPeriod
  */
  @Valid 
  @Schema(name = "billingPeriod", required = false)
  public TimePeriod getBillingPeriod() {
    return billingPeriod;
  }

  public void setBillingPeriod(TimePeriod billingPeriod) {
    this.billingPeriod = billingPeriod;
  }

  public CustomerBillMVO billCycle(BillCycleRef billCycle) {
    this.billCycle = billCycle;
    return this;
  }

  /**
   * Get billCycle
   * @return billCycle
  */
  @Valid 
  @Schema(name = "billCycle", required = false)
  public BillCycleRef getBillCycle() {
    return billCycle;
  }

  public void setBillCycle(BillCycleRef billCycle) {
    this.billCycle = billCycle;
  }

  public CustomerBillMVO category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Category of the bill produced : normal, duplicate, interim, last, trial customer or credit note for example
   * @return category
  */
  
  @Schema(name = "category", description = "Category of the bill produced : normal, duplicate, interim, last, trial customer or credit note for example", required = false)
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public CustomerBillMVO financialAccount(FinancialAccountRef financialAccount) {
    this.financialAccount = financialAccount;
    return this;
  }

  /**
   * Get financialAccount
   * @return financialAccount
  */
  @Valid 
  @Schema(name = "financialAccount", required = false)
  public FinancialAccountRef getFinancialAccount() {
    return financialAccount;
  }

  public void setFinancialAccount(FinancialAccountRef financialAccount) {
    this.financialAccount = financialAccount;
  }

  public CustomerBillMVO lastUpdate(OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
    return this;
  }

  /**
   * Date of bill last update
   * @return lastUpdate
  */
  @Valid 
  @Schema(name = "lastUpdate", description = "Date of bill last update", required = false)
  public OffsetDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(OffsetDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public CustomerBillMVO nextBillDate(OffsetDateTime nextBillDate) {
    this.nextBillDate = nextBillDate;
    return this;
  }

  /**
   * ). Approximate date of  the next bill production given for information (only used/meaningful for on cycle / regular bills)
   * @return nextBillDate
  */
  @Valid 
  @Schema(name = "nextBillDate", description = "). Approximate date of  the next bill production given for information (only used/meaningful for on cycle / regular bills)", required = false)
  public OffsetDateTime getNextBillDate() {
    return nextBillDate;
  }

  public void setNextBillDate(OffsetDateTime nextBillDate) {
    this.nextBillDate = nextBillDate;
  }

  public CustomerBillMVO paymentDueDate(OffsetDateTime paymentDueDate) {
    this.paymentDueDate = paymentDueDate;
    return this;
  }

  /**
   * Date at which the amount due should have been paid
   * @return paymentDueDate
  */
  @Valid 
  @Schema(name = "paymentDueDate", description = "Date at which the amount due should have been paid", required = false)
  public OffsetDateTime getPaymentDueDate() {
    return paymentDueDate;
  }

  public void setPaymentDueDate(OffsetDateTime paymentDueDate) {
    this.paymentDueDate = paymentDueDate;
  }

  public CustomerBillMVO paymentMethod(PaymentMethodRef paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  /**
   * Get paymentMethod
   * @return paymentMethod
  */
  @Valid 
  @Schema(name = "paymentMethod", required = false)
  public PaymentMethodRef getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethodRef paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public CustomerBillMVO relatedParty(List<RelatedPartyRefOrPartyRoleRef> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public CustomerBillMVO addRelatedPartyItem(RelatedPartyRefOrPartyRoleRef relatedPartyItem) {
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
  public List<RelatedPartyRefOrPartyRoleRef> getRelatedParty() {
    return relatedParty;
  }

  public void setRelatedParty(List<RelatedPartyRefOrPartyRoleRef> relatedParty) {
    this.relatedParty = relatedParty;
  }

  public CustomerBillMVO remainingAmount(Money remainingAmount) {
    this.remainingAmount = remainingAmount;
    return this;
  }

  /**
   * Get remainingAmount
   * @return remainingAmount
  */
  @Valid 
  @Schema(name = "remainingAmount", required = false)
  public Money getRemainingAmount() {
    return remainingAmount;
  }

  public void setRemainingAmount(Money remainingAmount) {
    this.remainingAmount = remainingAmount;
  }

  public CustomerBillMVO runType(CustomerBillRunType runType) {
    this.runType = runType;
    return this;
  }

  /**
   * Get runType
   * @return runType
  */
  @Valid 
  @Schema(name = "runType", required = false)
  public CustomerBillRunType getRunType() {
    return runType;
  }

  public void setRunType(CustomerBillRunType runType) {
    this.runType = runType;
  }

  public CustomerBillMVO taxExcludedAmount(Money taxExcludedAmount) {
    this.taxExcludedAmount = taxExcludedAmount;
    return this;
  }

  /**
   * Get taxExcludedAmount
   * @return taxExcludedAmount
  */
  @Valid 
  @Schema(name = "taxExcludedAmount", required = false)
  public Money getTaxExcludedAmount() {
    return taxExcludedAmount;
  }

  public void setTaxExcludedAmount(Money taxExcludedAmount) {
    this.taxExcludedAmount = taxExcludedAmount;
  }

  public CustomerBillMVO taxIncludedAmount(Money taxIncludedAmount) {
    this.taxIncludedAmount = taxIncludedAmount;
    return this;
  }

  /**
   * Get taxIncludedAmount
   * @return taxIncludedAmount
  */
  @Valid 
  @Schema(name = "taxIncludedAmount", required = false)
  public Money getTaxIncludedAmount() {
    return taxIncludedAmount;
  }

  public void setTaxIncludedAmount(Money taxIncludedAmount) {
    this.taxIncludedAmount = taxIncludedAmount;
  }

  public CustomerBillMVO taxItem(List<TaxItem> taxItem) {
    this.taxItem = taxItem;
    return this;
  }

  public CustomerBillMVO addTaxItemItem(TaxItem taxItemItem) {
    if (this.taxItem == null) {
      this.taxItem = new ArrayList<>();
    }
    this.taxItem.add(taxItemItem);
    return this;
  }

  /**
   * Get taxItem
   * @return taxItem
  */
  @Valid 
  @Schema(name = "taxItem", required = false)
  public List<TaxItem> getTaxItem() {
    return taxItem;
  }

  public void setTaxItem(List<TaxItem> taxItem) {
    this.taxItem = taxItem;
  }

  public CustomerBillMVO state(CustomerBillStateType state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @Valid 
  @Schema(name = "state", required = false)
  public CustomerBillStateType getState() {
    return state;
  }

  public void setState(CustomerBillStateType state) {
    this.state = state;
  }

  public CustomerBillMVO productOrder(ProductOrderRef productOrder) {
    this.productOrder = productOrder;
    return this;
  }

  /**
   * Get productOrder
   * @return productOrder
  */
  @Valid 
  @Schema(name = "productOrder", required = false)
  public ProductOrderRef getProductOrder() {
    return productOrder;
  }

  public void setProductOrder(ProductOrderRef productOrder) {
    this.productOrder = productOrder;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerBillMVO customerBillMVO = (CustomerBillMVO) o;
    return Objects.equals(this.atType, customerBillMVO.atType) &&
        Objects.equals(this.atBaseType, customerBillMVO.atBaseType) &&
        Objects.equals(this.atSchemaLocation, customerBillMVO.atSchemaLocation) &&
        Objects.equals(this.amountDue, customerBillMVO.amountDue) &&
        Objects.equals(this.appliedPayment, customerBillMVO.appliedPayment) &&
        Objects.equals(this.billDate, customerBillMVO.billDate) &&
        Objects.equals(this.billDocument, customerBillMVO.billDocument) &&
        Objects.equals(this.billNo, customerBillMVO.billNo) &&
        Objects.equals(this.billingAccount, customerBillMVO.billingAccount) &&
        Objects.equals(this.billingPeriod, customerBillMVO.billingPeriod) &&
        Objects.equals(this.billCycle, customerBillMVO.billCycle) &&
        Objects.equals(this.category, customerBillMVO.category) &&
        Objects.equals(this.financialAccount, customerBillMVO.financialAccount) &&
        Objects.equals(this.lastUpdate, customerBillMVO.lastUpdate) &&
        Objects.equals(this.nextBillDate, customerBillMVO.nextBillDate) &&
        Objects.equals(this.paymentDueDate, customerBillMVO.paymentDueDate) &&
        Objects.equals(this.paymentMethod, customerBillMVO.paymentMethod) &&
        Objects.equals(this.relatedParty, customerBillMVO.relatedParty) &&
        Objects.equals(this.remainingAmount, customerBillMVO.remainingAmount) &&
        Objects.equals(this.runType, customerBillMVO.runType) &&
        Objects.equals(this.taxExcludedAmount, customerBillMVO.taxExcludedAmount) &&
        Objects.equals(this.taxIncludedAmount, customerBillMVO.taxIncludedAmount) &&
        Objects.equals(this.taxItem, customerBillMVO.taxItem) &&
        Objects.equals(this.state, customerBillMVO.state) &&
        Objects.equals(this.productOrder, customerBillMVO.productOrder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType, atBaseType, atSchemaLocation, amountDue, appliedPayment, billDate, billDocument, billNo, billingAccount, billingPeriod, billCycle, category, financialAccount, lastUpdate, nextBillDate, paymentDueDate, paymentMethod, relatedParty, remainingAmount, runType, taxExcludedAmount, taxIncludedAmount, taxItem, state, productOrder);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerBillMVO {\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    amountDue: ").append(toIndentedString(amountDue)).append("\n");
    sb.append("    appliedPayment: ").append(toIndentedString(appliedPayment)).append("\n");
    sb.append("    billDate: ").append(toIndentedString(billDate)).append("\n");
    sb.append("    billDocument: ").append(toIndentedString(billDocument)).append("\n");
    sb.append("    billNo: ").append(toIndentedString(billNo)).append("\n");
    sb.append("    billingAccount: ").append(toIndentedString(billingAccount)).append("\n");
    sb.append("    billingPeriod: ").append(toIndentedString(billingPeriod)).append("\n");
    sb.append("    billCycle: ").append(toIndentedString(billCycle)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    financialAccount: ").append(toIndentedString(financialAccount)).append("\n");
    sb.append("    lastUpdate: ").append(toIndentedString(lastUpdate)).append("\n");
    sb.append("    nextBillDate: ").append(toIndentedString(nextBillDate)).append("\n");
    sb.append("    paymentDueDate: ").append(toIndentedString(paymentDueDate)).append("\n");
    sb.append("    paymentMethod: ").append(toIndentedString(paymentMethod)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
    sb.append("    remainingAmount: ").append(toIndentedString(remainingAmount)).append("\n");
    sb.append("    runType: ").append(toIndentedString(runType)).append("\n");
    sb.append("    taxExcludedAmount: ").append(toIndentedString(taxExcludedAmount)).append("\n");
    sb.append("    taxIncludedAmount: ").append(toIndentedString(taxIncludedAmount)).append("\n");
    sb.append("    taxItem: ").append(toIndentedString(taxItem)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    productOrder: ").append(toIndentedString(productOrder)).append("\n");
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

