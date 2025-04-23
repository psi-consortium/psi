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
 * ResourceAdministrativeStateType enumerations; values defined by ITU X.731: 'locked': The resource is administratively prohibited from performing services for its users; 'shutdown': Use of the resource is administratively permitted to existing instances of use only. While the system remains in the shutting down state the manager may at any time cause the managed object to revert to the unlocked state; 'unlocked': The resource is administratively permitted to perform services for its users. This is independent of its inherent operability.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum ResourceAdministrativeStateType {
  
  LOCKED("locked"),
  
  UNLOCKED("unlocked"),
  
  SHUTDOWN("shutdown");

  private String value;

  ResourceAdministrativeStateType(String value) {
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
  public static ResourceAdministrativeStateType fromValue(String value) {
    for (ResourceAdministrativeStateType b : ResourceAdministrativeStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

