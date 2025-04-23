package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A recurring frequency to run a job within a timeframe  defined by schedule definition, for example,  every 5 minutes, 15 minutes, 1 hour, 1 day
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "RecurringFrequency", description = "A recurring frequency to run a job within a timeframe  defined by schedule definition, for example,  every 5 minutes, 15 minutes, 1 hour, 1 day")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RecurringFrequency {

  @JsonProperty("recurringFrequencyValue")
  private Integer recurringFrequencyValue;

  /**
   * The unit of measure in recurring frequency. For example, if a recurring frequency is 2 weeks this value is WEEKS.
   */
  public enum RecurringFrequencyUnitsEnum {
    MINUTES("MINUTES"),
    
    HOURS("HOURS"),
    
    DAYS("DAYS"),
    
    WEEKS("WEEKS"),
    
    MONTHS("MONTHS");

    private String value;

    RecurringFrequencyUnitsEnum(String value) {
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
    public static RecurringFrequencyUnitsEnum fromValue(String value) {
      for (RecurringFrequencyUnitsEnum b : RecurringFrequencyUnitsEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("recurringFrequencyUnits")
  private RecurringFrequencyUnitsEnum recurringFrequencyUnits;

  public RecurringFrequency recurringFrequencyValue(Integer recurringFrequencyValue) {
    this.recurringFrequencyValue = recurringFrequencyValue;
    return this;
  }

  /**
   * The value of the recurrence as an integer. For example,  if the recurring frequency is 2 weeks this value is 2.
   * minimum: 1
   * @return recurringFrequencyValue
  */
  @NotNull @Min(1) 
  @Schema(name = "recurringFrequencyValue", description = "The value of the recurrence as an integer. For example,  if the recurring frequency is 2 weeks this value is 2.", required = true)
  public Integer getRecurringFrequencyValue() {
    return recurringFrequencyValue;
  }

  public void setRecurringFrequencyValue(Integer recurringFrequencyValue) {
    this.recurringFrequencyValue = recurringFrequencyValue;
  }

  public RecurringFrequency recurringFrequencyUnits(RecurringFrequencyUnitsEnum recurringFrequencyUnits) {
    this.recurringFrequencyUnits = recurringFrequencyUnits;
    return this;
  }

  /**
   * The unit of measure in recurring frequency. For example, if a recurring frequency is 2 weeks this value is WEEKS.
   * @return recurringFrequencyUnits
  */
  @NotNull 
  @Schema(name = "recurringFrequencyUnits", description = "The unit of measure in recurring frequency. For example, if a recurring frequency is 2 weeks this value is WEEKS.", required = true)
  public RecurringFrequencyUnitsEnum getRecurringFrequencyUnits() {
    return recurringFrequencyUnits;
  }

  public void setRecurringFrequencyUnits(RecurringFrequencyUnitsEnum recurringFrequencyUnits) {
    this.recurringFrequencyUnits = recurringFrequencyUnits;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RecurringFrequency recurringFrequency = (RecurringFrequency) o;
    return Objects.equals(this.recurringFrequencyValue, recurringFrequency.recurringFrequencyValue) &&
        Objects.equals(this.recurringFrequencyUnits, recurringFrequency.recurringFrequencyUnits);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recurringFrequencyValue, recurringFrequencyUnits);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RecurringFrequency {\n");
    sb.append("    recurringFrequencyValue: ").append(toIndentedString(recurringFrequencyValue)).append("\n");
    sb.append("    recurringFrequencyUnits: ").append(toIndentedString(recurringFrequencyUnits)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

