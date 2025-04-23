package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.time.Period;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * A period of time relative to another.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "RelativeTimePeriod", description = "A period of time relative to another.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class RelativeTimePeriod {

  @JsonProperty("endDateTime")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime endDateTime;

  @JsonProperty("startDateTime")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime startDateTime;

  @JsonProperty("startOffset")
  private Period startOffset;

  @JsonProperty("startAnchor")
  private String startAnchor;

  @JsonProperty("endOffset")
  private Period endOffset;

  @JsonProperty("endAnchor")
  private String endAnchor;

  public RelativeTimePeriod endDateTime(OffsetDateTime endDateTime) {
    this.endDateTime = endDateTime;
    return this;
  }

  /**
   * End of the time period, using IETC-RFC-3339 format
   * @return endDateTime
  */
  @Valid 
  @Schema(name = "endDateTime", example = "1985-04-12T23:20:50.520Z", description = "End of the time period, using IETC-RFC-3339 format", required = false)
  public OffsetDateTime getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(OffsetDateTime endDateTime) {
    this.endDateTime = endDateTime;
  }

  public RelativeTimePeriod startDateTime(OffsetDateTime startDateTime) {
    this.startDateTime = startDateTime;
    return this;
  }

  /**
   * Start of the time period, using IETC-RFC-3339 format
   * @return startDateTime
  */
  @Valid 
  @Schema(name = "startDateTime", example = "1985-04-12T23:20:50.520Z", description = "Start of the time period, using IETC-RFC-3339 format", required = false)
  public OffsetDateTime getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(OffsetDateTime startDateTime) {
    this.startDateTime = startDateTime;
  }

  public RelativeTimePeriod startOffset(Period startOffset) {
    this.startOffset = startOffset;
    return this;
  }

  /**
   * Relative start of the time period, using IETC-RFC-3339 duration format.
   * @return startOffset
  */
  @Valid 
  @Schema(name = "startOffset", example = "P7D", description = "Relative start of the time period, using IETC-RFC-3339 duration format.", required = false)
  public Period getStartOffset() {
    return startOffset;
  }

  public void setStartOffset(Period startOffset) {
    this.startOffset = startOffset;
  }

  public RelativeTimePeriod startAnchor(String startAnchor) {
    this.startAnchor = startAnchor;
    return this;
  }

  /**
   * Reference for startDateTime, e.g. 'mission.startDateTime', 'mission.endDateTime', 'self.endDateTime' or 'asset[1234].startTime'.
   * @return startAnchor
  */
  
  @Schema(name = "startAnchor", description = "Reference for startDateTime, e.g. 'mission.startDateTime', 'mission.endDateTime', 'self.endDateTime' or 'asset[1234].startTime'.", required = false)
  public String getStartAnchor() {
    return startAnchor;
  }

  public void setStartAnchor(String startAnchor) {
    this.startAnchor = startAnchor;
  }

  public RelativeTimePeriod endOffset(Period endOffset) {
    this.endOffset = endOffset;
    return this;
  }

  /**
   * Relative end of the time period, using IETC-RFC-3339 duration format.
   * @return endOffset
  */
  @Valid 
  @Schema(name = "endOffset", example = "-P7D", description = "Relative end of the time period, using IETC-RFC-3339 duration format.", required = false)
  public Period getEndOffset() {
    return endOffset;
  }

  public void setEndOffset(Period endOffset) {
    this.endOffset = endOffset;
  }

  public RelativeTimePeriod endAnchor(String endAnchor) {
    this.endAnchor = endAnchor;
    return this;
  }

  /**
   * Reference for endDateTime, e.g. 'mission.startDateTime', 'mission.endDateTime', 'self.startDateTime' or 'asset[1234].endTime'.
   * @return endAnchor
  */
  
  @Schema(name = "endAnchor", description = "Reference for endDateTime, e.g. 'mission.startDateTime', 'mission.endDateTime', 'self.startDateTime' or 'asset[1234].endTime'.", required = false)
  public String getEndAnchor() {
    return endAnchor;
  }

  public void setEndAnchor(String endAnchor) {
    this.endAnchor = endAnchor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelativeTimePeriod relativeTimePeriod = (RelativeTimePeriod) o;
    return Objects.equals(this.endDateTime, relativeTimePeriod.endDateTime) &&
        Objects.equals(this.startDateTime, relativeTimePeriod.startDateTime) &&
        Objects.equals(this.startOffset, relativeTimePeriod.startOffset) &&
        Objects.equals(this.startAnchor, relativeTimePeriod.startAnchor) &&
        Objects.equals(this.endOffset, relativeTimePeriod.endOffset) &&
        Objects.equals(this.endAnchor, relativeTimePeriod.endAnchor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endDateTime, startDateTime, startOffset, startAnchor, endOffset, endAnchor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelativeTimePeriod {\n");
    sb.append("    endDateTime: ").append(toIndentedString(endDateTime)).append("\n");
    sb.append("    startDateTime: ").append(toIndentedString(startDateTime)).append("\n");
    sb.append("    startOffset: ").append(toIndentedString(startOffset)).append("\n");
    sb.append("    startAnchor: ").append(toIndentedString(startAnchor)).append("\n");
    sb.append("    endOffset: ").append(toIndentedString(endOffset)).append("\n");
    sb.append("    endAnchor: ").append(toIndentedString(endAnchor)).append("\n");
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

