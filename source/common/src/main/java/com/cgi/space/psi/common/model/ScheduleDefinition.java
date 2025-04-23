package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.HourRange;
import com.cgi.space.psi.common.model.MonthlyScheduleDayOfWeekDefinition;
import com.cgi.space.psi.common.model.RecurringFrequency;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * The schedule definition for running jobs.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ScheduleDefinition", description = "The schedule definition for running jobs.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ScheduleDefinition {

  @JsonProperty("scheduleDefinitionStartTime")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime scheduleDefinitionStartTime;

  @JsonProperty("scheduleDefinitionEndTime")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime scheduleDefinitionEndTime;

  @JsonProperty("recurringFrequency")
  private RecurringFrequency recurringFrequency;

  @JsonProperty("scheduleDefinitionHourRange")
  @Valid
  private List<HourRange> scheduleDefinitionHourRange = null;

  @JsonProperty("monthlyScheduleDayOfWeekDefinition")
  private MonthlyScheduleDayOfWeekDefinition monthlyScheduleDayOfWeekDefinition;

  @JsonProperty("weeklyScheduledDefinition")
  @Valid
  private List<Integer> weeklyScheduledDefinition = null;

  public ScheduleDefinition scheduleDefinitionStartTime(OffsetDateTime scheduleDefinitionStartTime) {
    this.scheduleDefinitionStartTime = scheduleDefinitionStartTime;
    return this;
  }

  /**
   * The Start time of the Schedule Definition. If the attribute is empty the Schedule starts immediately after provisioning of the Job. 
   * @return scheduleDefinitionStartTime
  */
  @Valid 
  @Schema(name = "scheduleDefinitionStartTime", description = "The Start time of the Schedule Definition. If the attribute is empty the Schedule starts immediately after provisioning of the Job. ", required = false)
  public OffsetDateTime getScheduleDefinitionStartTime() {
    return scheduleDefinitionStartTime;
  }

  public void setScheduleDefinitionStartTime(OffsetDateTime scheduleDefinitionStartTime) {
    this.scheduleDefinitionStartTime = scheduleDefinitionStartTime;
  }

  public ScheduleDefinition scheduleDefinitionEndTime(OffsetDateTime scheduleDefinitionEndTime) {
    this.scheduleDefinitionEndTime = scheduleDefinitionEndTime;
    return this;
  }

  /**
   * The Endtime of the Schedule Definition. If the attribute is empty the Schedule runs forever, not having a time constraint.
   * @return scheduleDefinitionEndTime
  */
  @Valid 
  @Schema(name = "scheduleDefinitionEndTime", description = "The Endtime of the Schedule Definition. If the attribute is empty the Schedule runs forever, not having a time constraint.", required = false)
  public OffsetDateTime getScheduleDefinitionEndTime() {
    return scheduleDefinitionEndTime;
  }

  public void setScheduleDefinitionEndTime(OffsetDateTime scheduleDefinitionEndTime) {
    this.scheduleDefinitionEndTime = scheduleDefinitionEndTime;
  }

  public ScheduleDefinition recurringFrequency(RecurringFrequency recurringFrequency) {
    this.recurringFrequency = recurringFrequency;
    return this;
  }

  /**
   * Get recurringFrequency
   * @return recurringFrequency
  */
  @Valid 
  @Schema(name = "recurringFrequency", required = false)
  public RecurringFrequency getRecurringFrequency() {
    return recurringFrequency;
  }

  public void setRecurringFrequency(RecurringFrequency recurringFrequency) {
    this.recurringFrequency = recurringFrequency;
  }

  public ScheduleDefinition scheduleDefinitionHourRange(List<HourRange> scheduleDefinitionHourRange) {
    this.scheduleDefinitionHourRange = scheduleDefinitionHourRange;
    return this;
  }

  public ScheduleDefinition addScheduleDefinitionHourRangeItem(HourRange scheduleDefinitionHourRangeItem) {
    if (this.scheduleDefinitionHourRange == null) {
      this.scheduleDefinitionHourRange = new ArrayList<>();
    }
    this.scheduleDefinitionHourRange.add(scheduleDefinitionHourRangeItem);
    return this;
  }

  /**
   * A list of time ranges within a specific day that the schedule will be active on, for example, 08:00-12:00, 16:00-19:00.
   * @return scheduleDefinitionHourRange
  */
  @Valid 
  @Schema(name = "scheduleDefinitionHourRange", description = "A list of time ranges within a specific day that the schedule will be active on, for example, 08:00-12:00, 16:00-19:00.", required = false)
  public List<HourRange> getScheduleDefinitionHourRange() {
    return scheduleDefinitionHourRange;
  }

  public void setScheduleDefinitionHourRange(List<HourRange> scheduleDefinitionHourRange) {
    this.scheduleDefinitionHourRange = scheduleDefinitionHourRange;
  }

  public ScheduleDefinition monthlyScheduleDayOfWeekDefinition(MonthlyScheduleDayOfWeekDefinition monthlyScheduleDayOfWeekDefinition) {
    this.monthlyScheduleDayOfWeekDefinition = monthlyScheduleDayOfWeekDefinition;
    return this;
  }

  /**
   * Get monthlyScheduleDayOfWeekDefinition
   * @return monthlyScheduleDayOfWeekDefinition
  */
  @Valid 
  @Schema(name = "monthlyScheduleDayOfWeekDefinition", required = false)
  public MonthlyScheduleDayOfWeekDefinition getMonthlyScheduleDayOfWeekDefinition() {
    return monthlyScheduleDayOfWeekDefinition;
  }

  public void setMonthlyScheduleDayOfWeekDefinition(MonthlyScheduleDayOfWeekDefinition monthlyScheduleDayOfWeekDefinition) {
    this.monthlyScheduleDayOfWeekDefinition = monthlyScheduleDayOfWeekDefinition;
  }

  public ScheduleDefinition weeklyScheduledDefinition(List<Integer> weeklyScheduledDefinition) {
    this.weeklyScheduledDefinition = weeklyScheduledDefinition;
    return this;
  }

  public ScheduleDefinition addWeeklyScheduledDefinitionItem(Integer weeklyScheduledDefinitionItem) {
    if (this.weeklyScheduledDefinition == null) {
      this.weeklyScheduledDefinition = new ArrayList<>();
    }
    this.weeklyScheduledDefinition.add(weeklyScheduledDefinitionItem);
    return this;
  }

  /**
   * The weekly schedule is used to define a schedule that is based on the days of the week, e.g. a schedule that will be active only on Monday and Tuesday.
   * @return weeklyScheduledDefinition
  */
  
  @Schema(name = "weeklyScheduledDefinition", description = "The weekly schedule is used to define a schedule that is based on the days of the week, e.g. a schedule that will be active only on Monday and Tuesday.", required = false)
  public List<Integer> getWeeklyScheduledDefinition() {
    return weeklyScheduledDefinition;
  }

  public void setWeeklyScheduledDefinition(List<Integer> weeklyScheduledDefinition) {
    this.weeklyScheduledDefinition = weeklyScheduledDefinition;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScheduleDefinition scheduleDefinition = (ScheduleDefinition) o;
    return Objects.equals(this.scheduleDefinitionStartTime, scheduleDefinition.scheduleDefinitionStartTime) &&
        Objects.equals(this.scheduleDefinitionEndTime, scheduleDefinition.scheduleDefinitionEndTime) &&
        Objects.equals(this.recurringFrequency, scheduleDefinition.recurringFrequency) &&
        Objects.equals(this.scheduleDefinitionHourRange, scheduleDefinition.scheduleDefinitionHourRange) &&
        Objects.equals(this.monthlyScheduleDayOfWeekDefinition, scheduleDefinition.monthlyScheduleDayOfWeekDefinition) &&
        Objects.equals(this.weeklyScheduledDefinition, scheduleDefinition.weeklyScheduledDefinition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scheduleDefinitionStartTime, scheduleDefinitionEndTime, recurringFrequency, scheduleDefinitionHourRange, monthlyScheduleDayOfWeekDefinition, weeklyScheduledDefinition);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScheduleDefinition {\n");
    sb.append("    scheduleDefinitionStartTime: ").append(toIndentedString(scheduleDefinitionStartTime)).append("\n");
    sb.append("    scheduleDefinitionEndTime: ").append(toIndentedString(scheduleDefinitionEndTime)).append("\n");
    sb.append("    recurringFrequency: ").append(toIndentedString(recurringFrequency)).append("\n");
    sb.append("    scheduleDefinitionHourRange: ").append(toIndentedString(scheduleDefinitionHourRange)).append("\n");
    sb.append("    monthlyScheduleDayOfWeekDefinition: ").append(toIndentedString(monthlyScheduleDayOfWeekDefinition)).append("\n");
    sb.append("    weeklyScheduledDefinition: ").append(toIndentedString(weeklyScheduledDefinition)).append("\n");
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

