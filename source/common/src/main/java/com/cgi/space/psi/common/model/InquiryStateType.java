package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Possible values for the state of the customer inquiry
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum InquiryStateType {
  
  PENDING("pending"),
  
  INPROGRESS("inProgress"),
  
  CANCELLED("cancelled"),
  
  COMPLETED("completed");

  private String value;

  InquiryStateType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static InquiryStateType fromValue(String value) {
    for (InquiryStateType b : InquiryStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

