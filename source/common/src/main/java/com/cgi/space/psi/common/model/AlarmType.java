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
 * Categorizes the alarm (X.733 8.1.1, 3GPP TS 32.111-2 Annex A)
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum AlarmType {
  
  COMMUNICATIONSALARM("communicationsAlarm"),
  
  PROCESSINGERRORALARM("processingErrorAlarm"),
  
  ENVIRONMENTALALARM("environmentalAlarm"),
  
  QUALITYOFSERVICEALARM("qualityOfServiceAlarm"),
  
  EQUIPMENTALARM("equipmentAlarm"),
  
  INTEGRITYVIOLATION("integrityViolation"),
  
  OPERATIONALVIOLATION("operationalViolation"),
  
  PHYSICALVIOLATION("physicalViolation"),
  
  SECURITYSERVICE("securityService"),
  
  MECHANISMVIOLATION("mechanismViolation"),
  
  TIMEDOMAINVIOLATION("timeDomainViolation");

  private String value;

  AlarmType(String value) {
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
  public static AlarmType fromValue(String value) {
    for (AlarmType b : AlarmType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

