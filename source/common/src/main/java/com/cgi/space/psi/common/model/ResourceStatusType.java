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
 * ResourceStatusType enumerations
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum ResourceStatusType {
  
  ALARM("alarm"),
  
  AVAILABLE("available"),
  
  INSTALLED("installed"),
  
  NOT_EXISTS("not exists"),
  
  PENDINGREMOVAL("pendingRemoval"),
  
  PLANNED("planned"),
  
  RESERVED("reserved"),
  
  STANDBY("standby"),
  
  SUSPENDED("suspended"),
  
  UNKNOWN("unknown");

  private String value;

  ResourceStatusType(String value) {
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
  public static ResourceStatusType fromValue(String value) {
    for (ResourceStatusType b : ResourceStatusType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

