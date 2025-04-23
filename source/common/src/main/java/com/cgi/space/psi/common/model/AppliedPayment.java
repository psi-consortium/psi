package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.Money;
import com.cgi.space.psi.common.model.PaymentRef;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * The applied payment is the result of lettering process. It enables to assign automatically or manually part of incoming payment amount to a bill.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "AppliedPayment", description = "The applied payment is the result of lettering process. It enables to assign automatically or manually part of incoming payment amount to a bill.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AppliedPayment {

  @JsonProperty("appliedAmount")
  private Money appliedAmount;

  @JsonProperty("payment")
  private PaymentRef payment;

  public AppliedPayment appliedAmount(Money appliedAmount) {
    this.appliedAmount = appliedAmount;
    return this;
  }

  /**
   * Get appliedAmount
   * @return appliedAmount
  */
  @Valid 
  @Schema(name = "appliedAmount", required = false)
  public Money getAppliedAmount() {
    return appliedAmount;
  }

  public void setAppliedAmount(Money appliedAmount) {
    this.appliedAmount = appliedAmount;
  }

  public AppliedPayment payment(PaymentRef payment) {
    this.payment = payment;
    return this;
  }

  /**
   * Get payment
   * @return payment
  */
  @Valid 
  @Schema(name = "payment", required = false)
  public PaymentRef getPayment() {
    return payment;
  }

  public void setPayment(PaymentRef payment) {
    this.payment = payment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppliedPayment appliedPayment = (AppliedPayment) o;
    return Objects.equals(this.appliedAmount, appliedPayment.appliedAmount) &&
        Objects.equals(this.payment, appliedPayment.payment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appliedAmount, payment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppliedPayment {\n");
    sb.append("    appliedAmount: ").append(toIndentedString(appliedAmount)).append("\n");
    sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
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

