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
 * The state of the process related to the Performance Job  | state          | MEF 133 name | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                               | | -------------- | ------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | | `accepted`     | Accepted     | The Cancel/Modify/Resume/Suspend Performance Monitoring Job request has been validated and accepted by the Seller/Server.                                                                                                                                                                                                                                                                                                                                                                 | | `acknowledged` | Acknowledged | The Cancel/Modify/Resume/Suspend Performance Monitoring Job request has been received by the Seller/Server and has passed basic validation. Performance Monitoring Job Process Identifier is assigned in the Acknowledged state. The request remains Acknowledged until all validations as applicable are completed. If the attributes are validated, the request moves to the Accepted state. If not all attributes are validated, the request moves to the Declined state. | | `completed`    | Completed    | The Cancel/Modify/Resume/Suspend Performance Monitoring Job request has been completed by the Seller/Server.                                                                                                                                                                                                                                                                                                                                                                              | | `declined`     | Declined     | The Cancel/Modify/Resume/Suspend Performance Monitoring Job request has failed validation and has been declined by the Seller/Server.                                                                                                                                                                                                                                                                                                                                                         |
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum PerformanceJobProcessStateType {
  
  ACCEPTED("accepted"),
  
  ACKNOWLEDGED("acknowledged"),
  
  COMPLETED("completed"),
  
  DECLINED("declined");

  private String value;

  PerformanceJobProcessStateType(String value) {
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
  public static PerformanceJobProcessStateType fromValue(String value) {
    for (PerformanceJobProcessStateType b : PerformanceJobProcessStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

