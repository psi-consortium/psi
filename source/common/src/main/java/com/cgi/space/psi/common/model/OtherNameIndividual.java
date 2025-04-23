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
 * Keeps track of other names, for example the old name of a woman before marriage or an artist name.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "OtherNameIndividual", description = "Keeps track of other names, for example the old name of a woman before marriage or an artist name.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class OtherNameIndividual {

  @JsonProperty("title")
  private String title;

  @JsonProperty("aristocraticTitle")
  private String aristocraticTitle;

  @JsonProperty("generation")
  private String generation;

  @JsonProperty("givenName")
  private String givenName;

  @JsonProperty("preferredGivenName")
  private String preferredGivenName;

  @JsonProperty("familyNamePrefix")
  private String familyNamePrefix;

  @JsonProperty("familyName")
  private String familyName;

  @JsonProperty("legalName")
  private String legalName;

  @JsonProperty("middleName")
  private String middleName;

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("formattedName")
  private String formattedName;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  public OtherNameIndividual title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Use for titles (aristrocatic, social, ...): Pr, Dr, Sir,....
   * @return title
  */
  
  @Schema(name = "title", description = "Use for titles (aristrocatic, social, ...): Pr, Dr, Sir,....", required = false)
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public OtherNameIndividual aristocraticTitle(String aristocraticTitle) {
    this.aristocraticTitle = aristocraticTitle;
    return this;
  }

  /**
   * e.g. Baron, Graf, Earl, etc.
   * @return aristocraticTitle
  */
  
  @Schema(name = "aristocraticTitle", description = "e.g. Baron, Graf, Earl, etc.", required = false)
  public String getAristocraticTitle() {
    return aristocraticTitle;
  }

  public void setAristocraticTitle(String aristocraticTitle) {
    this.aristocraticTitle = aristocraticTitle;
  }

  public OtherNameIndividual generation(String generation) {
    this.generation = generation;
    return this;
  }

  /**
   * e.g. Sr, Jr, etc.
   * @return generation
  */
  
  @Schema(name = "generation", description = "e.g. Sr, Jr, etc.", required = false)
  public String getGeneration() {
    return generation;
  }

  public void setGeneration(String generation) {
    this.generation = generation;
  }

  public OtherNameIndividual givenName(String givenName) {
    this.givenName = givenName;
    return this;
  }

  /**
   * First name
   * @return givenName
  */
  
  @Schema(name = "givenName", description = "First name", required = false)
  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public OtherNameIndividual preferredGivenName(String preferredGivenName) {
    this.preferredGivenName = preferredGivenName;
    return this;
  }

  /**
   * Contains the chosen name by which the person prefers to be addressed. Note: This name may be a name other than a given name, such as a nickname
   * @return preferredGivenName
  */
  
  @Schema(name = "preferredGivenName", description = "Contains the chosen name by which the person prefers to be addressed. Note: This name may be a name other than a given name, such as a nickname", required = false)
  public String getPreferredGivenName() {
    return preferredGivenName;
  }

  public void setPreferredGivenName(String preferredGivenName) {
    this.preferredGivenName = preferredGivenName;
  }

  public OtherNameIndividual familyNamePrefix(String familyNamePrefix) {
    this.familyNamePrefix = familyNamePrefix;
    return this;
  }

  /**
   * Family name prefix
   * @return familyNamePrefix
  */
  
  @Schema(name = "familyNamePrefix", description = "Family name prefix", required = false)
  public String getFamilyNamePrefix() {
    return familyNamePrefix;
  }

  public void setFamilyNamePrefix(String familyNamePrefix) {
    this.familyNamePrefix = familyNamePrefix;
  }

  public OtherNameIndividual familyName(String familyName) {
    this.familyName = familyName;
    return this;
  }

  /**
   * Contains the non-chosen or inherited name. Also known as last name in the Western context
   * @return familyName
  */
  
  @Schema(name = "familyName", description = "Contains the non-chosen or inherited name. Also known as last name in the Western context", required = false)
  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public OtherNameIndividual legalName(String legalName) {
    this.legalName = legalName;
    return this;
  }

  /**
   * Legal name or birth name (name one has for official purposes)
   * @return legalName
  */
  
  @Schema(name = "legalName", description = "Legal name or birth name (name one has for official purposes)", required = false)
  public String getLegalName() {
    return legalName;
  }

  public void setLegalName(String legalName) {
    this.legalName = legalName;
  }

  public OtherNameIndividual middleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  /**
   * Middle name or initial
   * @return middleName
  */
  
  @Schema(name = "middleName", description = "Middle name or initial", required = false)
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public OtherNameIndividual fullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  /**
   * Full name flatten (first, middle, and last names)
   * @return fullName
  */
  
  @Schema(name = "fullName", description = "Full name flatten (first, middle, and last names)", required = false)
  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public OtherNameIndividual formattedName(String formattedName) {
    this.formattedName = formattedName;
    return this;
  }

  /**
   * . A fully formatted name in one string with all of its pieces in their proper place and all of the necessary punctuation. Useful for specific contexts (Chinese, Japanese, Korean, etc.)
   * @return formattedName
  */
  
  @Schema(name = "formattedName", description = ". A fully formatted name in one string with all of its pieces in their proper place and all of the necessary punctuation. Useful for specific contexts (Chinese, Japanese, Korean, etc.)", required = false)
  public String getFormattedName() {
    return formattedName;
  }

  public void setFormattedName(String formattedName) {
    this.formattedName = formattedName;
  }

  public OtherNameIndividual validFor(TimePeriod validFor) {
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
    OtherNameIndividual otherNameIndividual = (OtherNameIndividual) o;
    return Objects.equals(this.title, otherNameIndividual.title) &&
        Objects.equals(this.aristocraticTitle, otherNameIndividual.aristocraticTitle) &&
        Objects.equals(this.generation, otherNameIndividual.generation) &&
        Objects.equals(this.givenName, otherNameIndividual.givenName) &&
        Objects.equals(this.preferredGivenName, otherNameIndividual.preferredGivenName) &&
        Objects.equals(this.familyNamePrefix, otherNameIndividual.familyNamePrefix) &&
        Objects.equals(this.familyName, otherNameIndividual.familyName) &&
        Objects.equals(this.legalName, otherNameIndividual.legalName) &&
        Objects.equals(this.middleName, otherNameIndividual.middleName) &&
        Objects.equals(this.fullName, otherNameIndividual.fullName) &&
        Objects.equals(this.formattedName, otherNameIndividual.formattedName) &&
        Objects.equals(this.validFor, otherNameIndividual.validFor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, aristocraticTitle, generation, givenName, preferredGivenName, familyNamePrefix, familyName, legalName, middleName, fullName, formattedName, validFor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OtherNameIndividual {\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    aristocraticTitle: ").append(toIndentedString(aristocraticTitle)).append("\n");
    sb.append("    generation: ").append(toIndentedString(generation)).append("\n");
    sb.append("    givenName: ").append(toIndentedString(givenName)).append("\n");
    sb.append("    preferredGivenName: ").append(toIndentedString(preferredGivenName)).append("\n");
    sb.append("    familyNamePrefix: ").append(toIndentedString(familyNamePrefix)).append("\n");
    sb.append("    familyName: ").append(toIndentedString(familyName)).append("\n");
    sb.append("    legalName: ").append(toIndentedString(legalName)).append("\n");
    sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    formattedName: ").append(toIndentedString(formattedName)).append("\n");
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

