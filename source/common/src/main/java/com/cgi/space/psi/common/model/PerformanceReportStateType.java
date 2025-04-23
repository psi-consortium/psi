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
 * Possible values for the state of a Performance Report.  | State        | Description                                                                                                                                                                                                                                                                                                                                                                                                                                 | | ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | | acknowledged | A Performance Report request has been received by Seller/Server and has passed basic validations. Performance Report Identifier is assigned in the Acknowledged state. The report remains Acknowledged until all validations as applicable are completed. If the attributes are validated, the Performance Report moves to the In-Progress state. If not all attributes are validated, the report moves to the Rejected state. | | completed    | A Performance Report is completed and results are available.                                                                                                                                                                                                                                                                                                                                                                                | | failed       | A Performance Report processing has failed.                                                                                                                                                                                                                                                                                                                                                                                                 | | inProgress   | A Performance Report has successfully passed the validations checks and the report processing has started.                                                                                                                                                                                                                                                                                                                                  | | rejected     | This state indicates that: <br>- Invalid information is provided through the `PerformanceReport` request <br>- The request fails to meet validation rules for `PerformanceReport` delivery (processing).                                                                                                                                                                                                                                    |
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public enum PerformanceReportStateType {
  
  ACKNOWLEDGED("acknowledged"),
  
  COMPLETED("completed"),
  
  FAILED("failed"),
  
  INPROGRESS("inProgress"),
  
  REJECTED("rejected");

  private String value;

  PerformanceReportStateType(String value) {
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
  public static PerformanceReportStateType fromValue(String value) {
    for (PerformanceReportStateType b : PerformanceReportStateType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

