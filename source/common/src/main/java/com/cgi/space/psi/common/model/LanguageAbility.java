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
 * Ability of an individual to understand or converse in a language.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "LanguageAbility", description = "Ability of an individual to understand or converse in a language.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class LanguageAbility {

  @JsonProperty("languageCode")
  private String languageCode;

  @JsonProperty("languageName")
  private String languageName;

  @JsonProperty("isFavouriteLanguage")
  private Boolean isFavouriteLanguage;

  @JsonProperty("writingProficiency")
  private String writingProficiency;

  @JsonProperty("readingProficiency")
  private String readingProficiency;

  @JsonProperty("speakingProficiency")
  private String speakingProficiency;

  @JsonProperty("listeningProficiency")
  private String listeningProficiency;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  public LanguageAbility languageCode(String languageCode) {
    this.languageCode = languageCode;
    return this;
  }

  /**
   * Language code (RFC 5646)
   * @return languageCode
  */
  
  @Schema(name = "languageCode", description = "Language code (RFC 5646)", required = false)
  public String getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  public LanguageAbility languageName(String languageName) {
    this.languageName = languageName;
    return this;
  }

  /**
   * Language name
   * @return languageName
  */
  
  @Schema(name = "languageName", description = "Language name", required = false)
  public String getLanguageName() {
    return languageName;
  }

  public void setLanguageName(String languageName) {
    this.languageName = languageName;
  }

  public LanguageAbility isFavouriteLanguage(Boolean isFavouriteLanguage) {
    this.isFavouriteLanguage = isFavouriteLanguage;
    return this;
  }

  /**
   * A “true” value specifies whether the language is considered by the individual as his favourite one
   * @return isFavouriteLanguage
  */
  
  @Schema(name = "isFavouriteLanguage", description = "A “true” value specifies whether the language is considered by the individual as his favourite one", required = false)
  public Boolean getIsFavouriteLanguage() {
    return isFavouriteLanguage;
  }

  public void setIsFavouriteLanguage(Boolean isFavouriteLanguage) {
    this.isFavouriteLanguage = isFavouriteLanguage;
  }

  public LanguageAbility writingProficiency(String writingProficiency) {
    this.writingProficiency = writingProficiency;
    return this;
  }

  /**
   * Writing proficiency evaluated for this language
   * @return writingProficiency
  */
  
  @Schema(name = "writingProficiency", description = "Writing proficiency evaluated for this language", required = false)
  public String getWritingProficiency() {
    return writingProficiency;
  }

  public void setWritingProficiency(String writingProficiency) {
    this.writingProficiency = writingProficiency;
  }

  public LanguageAbility readingProficiency(String readingProficiency) {
    this.readingProficiency = readingProficiency;
    return this;
  }

  /**
   * Reading proficiency evaluated for this language
   * @return readingProficiency
  */
  
  @Schema(name = "readingProficiency", description = "Reading proficiency evaluated for this language", required = false)
  public String getReadingProficiency() {
    return readingProficiency;
  }

  public void setReadingProficiency(String readingProficiency) {
    this.readingProficiency = readingProficiency;
  }

  public LanguageAbility speakingProficiency(String speakingProficiency) {
    this.speakingProficiency = speakingProficiency;
    return this;
  }

  /**
   * Speaking proficiency evaluated for this language
   * @return speakingProficiency
  */
  
  @Schema(name = "speakingProficiency", description = "Speaking proficiency evaluated for this language", required = false)
  public String getSpeakingProficiency() {
    return speakingProficiency;
  }

  public void setSpeakingProficiency(String speakingProficiency) {
    this.speakingProficiency = speakingProficiency;
  }

  public LanguageAbility listeningProficiency(String listeningProficiency) {
    this.listeningProficiency = listeningProficiency;
    return this;
  }

  /**
   * Listening proficiency evaluated for this language
   * @return listeningProficiency
  */
  
  @Schema(name = "listeningProficiency", description = "Listening proficiency evaluated for this language", required = false)
  public String getListeningProficiency() {
    return listeningProficiency;
  }

  public void setListeningProficiency(String listeningProficiency) {
    this.listeningProficiency = listeningProficiency;
  }

  public LanguageAbility validFor(TimePeriod validFor) {
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
    LanguageAbility languageAbility = (LanguageAbility) o;
    return Objects.equals(this.languageCode, languageAbility.languageCode) &&
        Objects.equals(this.languageName, languageAbility.languageName) &&
        Objects.equals(this.isFavouriteLanguage, languageAbility.isFavouriteLanguage) &&
        Objects.equals(this.writingProficiency, languageAbility.writingProficiency) &&
        Objects.equals(this.readingProficiency, languageAbility.readingProficiency) &&
        Objects.equals(this.speakingProficiency, languageAbility.speakingProficiency) &&
        Objects.equals(this.listeningProficiency, languageAbility.listeningProficiency) &&
        Objects.equals(this.validFor, languageAbility.validFor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(languageCode, languageName, isFavouriteLanguage, writingProficiency, readingProficiency, speakingProficiency, listeningProficiency, validFor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LanguageAbility {\n");
    sb.append("    languageCode: ").append(toIndentedString(languageCode)).append("\n");
    sb.append("    languageName: ").append(toIndentedString(languageName)).append("\n");
    sb.append("    isFavouriteLanguage: ").append(toIndentedString(isFavouriteLanguage)).append("\n");
    sb.append("    writingProficiency: ").append(toIndentedString(writingProficiency)).append("\n");
    sb.append("    readingProficiency: ").append(toIndentedString(readingProficiency)).append("\n");
    sb.append("    speakingProficiency: ").append(toIndentedString(speakingProficiency)).append("\n");
    sb.append("    listeningProficiency: ").append(toIndentedString(listeningProficiency)).append("\n");
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

