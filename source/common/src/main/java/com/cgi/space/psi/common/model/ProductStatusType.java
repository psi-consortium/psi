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
 * Possible values for the status of the product
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum ProductStatusType {
  
  CREATED("created"),
  
  PENDINGACTIVE("pendingActive"),
  
  CANCELLED("cancelled"),
  
  ACTIVE("active"),
  
  PENDINGTERMINATE("pendingTerminate"),
  
  TERMINATED("terminated"),
  
  SUSPENDED("suspended"),
  
  ABORTED_("aborted ");

  private String value;

  ProductStatusType(String value) {
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
  public static ProductStatusType fromValue(String value) {
    for (ProductStatusType b : ProductStatusType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

