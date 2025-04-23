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
 * Possible values of the availability result check for CheckProductStockItem.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum AvailabilityResultType {
  
  AVAILABLE("available"),
  
  NOTAVAILABLE("notAvailable"),
  
  ALTERNATE("alternate");

  private String value;

  AvailabilityResultType(String value) {
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
  public static AvailabilityResultType fromValue(String value) {
    for (AvailabilityResultType b : AvailabilityResultType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

