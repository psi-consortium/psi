package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Monthly scheduled day of the week.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "MonthlyScheduleDayOfWeekDefinition", description = "Monthly scheduled day of the week.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class MonthlyScheduleDayOfWeekDefinition {

  @JsonProperty("recurringDaySequence")
  @Valid
  private List<Integer> recurringDaySequence = null;

  @JsonProperty("dayOfMonthRecurrence")
  @Valid
  private List<Integer> dayOfMonthRecurrence = null;

  public MonthlyScheduleDayOfWeekDefinition recurringDaySequence(List<Integer> recurringDaySequence) {
    this.recurringDaySequence = recurringDaySequence;
    return this;
  }

  public MonthlyScheduleDayOfWeekDefinition addRecurringDaySequenceItem(Integer recurringDaySequenceItem) {
    if (this.recurringDaySequence == null) {
      this.recurringDaySequence = new ArrayList<>();
    }
    this.recurringDaySequence.add(recurringDaySequenceItem);
    return this;
  }

  /**
   * Get recurringDaySequence
   * @return recurringDaySequence
  */
  
  @Schema(name = "recurringDaySequence", required = false)
  public List<Integer> getRecurringDaySequence() {
    return recurringDaySequence;
  }

  public void setRecurringDaySequence(List<Integer> recurringDaySequence) {
    this.recurringDaySequence = recurringDaySequence;
  }

  public MonthlyScheduleDayOfWeekDefinition dayOfMonthRecurrence(List<Integer> dayOfMonthRecurrence) {
    this.dayOfMonthRecurrence = dayOfMonthRecurrence;
    return this;
  }

  public MonthlyScheduleDayOfWeekDefinition addDayOfMonthRecurrenceItem(Integer dayOfMonthRecurrenceItem) {
    if (this.dayOfMonthRecurrence == null) {
      this.dayOfMonthRecurrence = new ArrayList<>();
    }
    this.dayOfMonthRecurrence.add(dayOfMonthRecurrenceItem);
    return this;
  }

  /**
   * Get dayOfMonthRecurrence
   * @return dayOfMonthRecurrence
  */
  
  @Schema(name = "dayOfMonthRecurrence", required = false)
  public List<Integer> getDayOfMonthRecurrence() {
    return dayOfMonthRecurrence;
  }

  public void setDayOfMonthRecurrence(List<Integer> dayOfMonthRecurrence) {
    this.dayOfMonthRecurrence = dayOfMonthRecurrence;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MonthlyScheduleDayOfWeekDefinition monthlyScheduleDayOfWeekDefinition = (MonthlyScheduleDayOfWeekDefinition) o;
    return Objects.equals(this.recurringDaySequence, monthlyScheduleDayOfWeekDefinition.recurringDaySequence) &&
        Objects.equals(this.dayOfMonthRecurrence, monthlyScheduleDayOfWeekDefinition.dayOfMonthRecurrence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recurringDaySequence, dayOfMonthRecurrence);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MonthlyScheduleDayOfWeekDefinition {\n");
    sb.append("    recurringDaySequence: ").append(toIndentedString(recurringDaySequence)).append("\n");
    sb.append("    dayOfMonthRecurrence: ").append(toIndentedString(dayOfMonthRecurrence)).append("\n");
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

