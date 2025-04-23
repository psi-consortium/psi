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
 * Possible values for the precedence of a characteristic value.
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum CharacteristicValuePrecedence {
  
  PREFERRED("preferred"),
  
  MINIMUM("minimum"),
  
  MAXIMUM("maximum"),
  
  ALTERNATIVE("alternative");

  private String value;

  CharacteristicValuePrecedence(String value) {
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
  public static CharacteristicValuePrecedence fromValue(String value) {
    for (CharacteristicValuePrecedence b : CharacteristicValuePrecedence.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

