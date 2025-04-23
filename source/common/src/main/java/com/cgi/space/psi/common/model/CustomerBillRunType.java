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
 * Recommended Enumeration Type (not formal forced in standard): Valid values for the runType of a bill. The bill could be produced in a regular bill cycle 'onCycle'. Otherwise the bill is produced on a request (e.g. customer request). This could be indicated by 'offCycle'
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum CustomerBillRunType {
  
  ONCYCLE("onCycle"),
  
  OFFCYCLE("offCycle");

  private String value;

  CustomerBillRunType(String value) {
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
  public static CustomerBillRunType fromValue(String value) {
    for (CustomerBillRunType b : CustomerBillRunType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

