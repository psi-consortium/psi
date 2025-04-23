package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Skills evaluated for an individual with a level and possibly with a limited validity when an obsolescence is defined (Ex: the first-aid certificate first level is limited to one year and an update training is required each year to keep the level).
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "Skill", description = "Skills evaluated for an individual with a level and possibly with a limited validity when an obsolescence is defined (Ex: the first-aid certificate first level is limited to one year and an update training is required each year to keep the level).")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class Skill {

  @JsonProperty("skillCode")
  private String skillCode;

  @JsonProperty("skillName")
  private String skillName;

  @JsonProperty("evaluatedLevel")
  private String evaluatedLevel;

  @JsonProperty("comment")
  private String comment;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  public Skill skillCode(String skillCode) {
    this.skillCode = skillCode;
    return this;
  }

  /**
   * Code of the skill
   * @return skillCode
  */
  
  @Schema(name = "skillCode", description = "Code of the skill", required = false)
  public String getSkillCode() {
    return skillCode;
  }

  public void setSkillCode(String skillCode) {
    this.skillCode = skillCode;
  }

  public Skill skillName(String skillName) {
    this.skillName = skillName;
    return this;
  }

  /**
   * Name of the skill, such as Java language
   * @return skillName
  */
  
  @Schema(name = "skillName", description = "Name of the skill, such as Java language", required = false)
  public String getSkillName() {
    return skillName;
  }

  public void setSkillName(String skillName) {
    this.skillName = skillName;
  }

  public Skill evaluatedLevel(String evaluatedLevel) {
    this.evaluatedLevel = evaluatedLevel;
    return this;
  }

  /**
   * Level of expertise in a skill evaluated for an individual
   * @return evaluatedLevel
  */
  
  @Schema(name = "evaluatedLevel", description = "Level of expertise in a skill evaluated for an individual", required = false)
  public String getEvaluatedLevel() {
    return evaluatedLevel;
  }

  public void setEvaluatedLevel(String evaluatedLevel) {
    this.evaluatedLevel = evaluatedLevel;
  }

  public Skill comment(String comment) {
    this.comment = comment;
    return this;
  }

  /**
   * A free text comment linked to the evaluation done
   * @return comment
  */
  
  @Schema(name = "comment", description = "A free text comment linked to the evaluation done", required = false)
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Skill validFor(TimePeriod validFor) {
    this.validFor = validFor;
    return this;
  }

  /**
   * Get validFor
   * @return validFor
  */
  @Valid 
  @Schema(name = "validFor", required = false)
  public TimePeriod getValidFor() {
    return validFor;
  }

  public void setValidFor(TimePeriod validFor) {
    this.validFor = validFor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Skill skill = (Skill) o;
    return Objects.equals(this.skillCode, skill.skillCode) &&
        Objects.equals(this.skillName, skill.skillName) &&
        Objects.equals(this.evaluatedLevel, skill.evaluatedLevel) &&
        Objects.equals(this.comment, skill.comment) &&
        Objects.equals(this.validFor, skill.validFor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(skillCode, skillName, evaluatedLevel, comment, validFor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Skill {\n");
    sb.append("    skillCode: ").append(toIndentedString(skillCode)).append("\n");
    sb.append("    skillName: ").append(toIndentedString(skillName)).append("\n");
    sb.append("    evaluatedLevel: ").append(toIndentedString(evaluatedLevel)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
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

