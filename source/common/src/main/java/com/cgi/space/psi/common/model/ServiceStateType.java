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
 * Valid values for the lifecycle state of the service
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum ServiceStateType {
  
  FEASIBILITYCHECKED("feasibilityChecked"),
  
  DESIGNED("designed"),
  
  RESERVED("reserved"),
  
  INACTIVE("inactive"),
  
  ACTIVE("active"),
  
  TERMINATED("terminated"),
  
  SUSPENDED("suspended");

  private String value;

  ServiceStateType(String value) {
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
  public static ServiceStateType fromValue(String value) {
    for (ServiceStateType b : ServiceStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

