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
 * ResourceUsageStateType enumerations; values defined by ITU X.731: 'idle': The resource is not currently in use; 'active': The resource is in use, and has sufficient spare operating capacity to provide for additional users simultaneously; 'busy': The resource is in use, but it has no spare operating capacity to provide for additional users at this instant.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum ResourceUsageStateType {
  
  IDLE("idle"),
  
  ACTIVE("active"),
  
  BUSY("busy");

  private String value;

  ResourceUsageStateType(String value) {
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
  public static ResourceUsageStateType fromValue(String value) {
    for (ResourceUsageStateType b : ResourceUsageStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

