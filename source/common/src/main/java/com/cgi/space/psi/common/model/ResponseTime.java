package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * The response time for an inquiry. At least the maximum estimated time must be filled on creation, the actual value is filled on completion.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "ResponseTime", description = "The response time for an inquiry. At least the maximum estimated time must be filled on creation, the actual value is filled on completion.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ResponseTime {

  @JsonProperty("minimum")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime minimum;

  @JsonProperty("average")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime average;

  @JsonProperty("maximum")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime maximum;

  @JsonProperty("actual")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime actual;

  public ResponseTime minimum(OffsetDateTime minimum) {
    this.minimum = minimum;
    return this;
  }

  /**
   * The minimum estimated response time
   * @return minimum
  */
  @Valid 
  @Schema(name = "minimum", example = "1985-04-12T23:20:50.520Z", description = "The minimum estimated response time", required = false)
  public OffsetDateTime getMinimum() {
    return minimum;
  }

  public void setMinimum(OffsetDateTime minimum) {
    this.minimum = minimum;
  }

  public ResponseTime average(OffsetDateTime average) {
    this.average = average;
    return this;
  }

  /**
   * The average estimated response time
   * @return average
  */
  @Valid 
  @Schema(name = "average", example = "1985-04-12T23:20:50.520Z", description = "The average estimated response time", required = false)
  public OffsetDateTime getAverage() {
    return average;
  }

  public void setAverage(OffsetDateTime average) {
    this.average = average;
  }

  public ResponseTime maximum(OffsetDateTime maximum) {
    this.maximum = maximum;
    return this;
  }

  /**
   * The maximum estimated response time
   * @return maximum
  */
  @NotNull @Valid 
  @Schema(name = "maximum", example = "1985-04-12T23:20:50.520Z", description = "The maximum estimated response time", required = true)
  public OffsetDateTime getMaximum() {
    return maximum;
  }

  public void setMaximum(OffsetDateTime maximum) {
    this.maximum = maximum;
  }

  public ResponseTime actual(OffsetDateTime actual) {
    this.actual = actual;
    return this;
  }

  /**
   * The actual response time
   * @return actual
  */
  @Valid 
  @Schema(name = "actual", example = "1985-04-12T23:20:50.520Z", description = "The actual response time", required = false)
  public OffsetDateTime getActual() {
    return actual;
  }

  public void setActual(OffsetDateTime actual) {
    this.actual = actual;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseTime responseTime = (ResponseTime) o;
    return Objects.equals(this.minimum, responseTime.minimum) &&
        Objects.equals(this.average, responseTime.average) &&
        Objects.equals(this.maximum, responseTime.maximum) &&
        Objects.equals(this.actual, responseTime.actual);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minimum, average, maximum, actual);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseTime {\n");
    sb.append("    minimum: ").append(toIndentedString(minimum)).append("\n");
    sb.append("    average: ").append(toIndentedString(average)).append("\n");
    sb.append("    maximum: ").append(toIndentedString(maximum)).append("\n");
    sb.append("    actual: ").append(toIndentedString(actual)).append("\n");
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

