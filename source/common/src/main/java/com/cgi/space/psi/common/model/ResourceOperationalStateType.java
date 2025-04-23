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
 * ResourceOperationalStateType enumerations; values defined by ITU X.731: 'disable': The resource is totally inoperable and unable to provide service to the user(s); 'enable': The resource is partially or fully operable and available for use.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum ResourceOperationalStateType {
  
  ENABLE("enable"),
  
  DISABLE("disable");

  private String value;

  ResourceOperationalStateType(String value) {
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
  public static ResourceOperationalStateType fromValue(String value) {
    for (ResourceOperationalStateType b : ResourceOperationalStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

