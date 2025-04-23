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
 * Enumeration of applicable time intervals
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum Interval {
  
  _10_MILLISECONDS("10 milliseconds"),
  
  _100_MILLISECONDS("100 milliseconds"),
  
  _1_SECOND("1 second"),
  
  _10_SECOND("10 second"),
  
  _1_MINUTE("1 minute"),
  
  _5_MINUTES("5 minutes"),
  
  _15_MINUTES("15 minutes"),
  
  _30_MINUTES("30 minutes"),
  
  _1_HOUR("1 hour"),
  
  _24_HOURS("24 hours"),
  
  _1_MONTH("1 month"),
  
  _1_YEAR("1 year"),
  
  NOT_APPLICABLE("not applicable");

  private String value;

  Interval(String value) {
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
  public static Interval fromValue(String value) {
    for (Interval b : Interval.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

