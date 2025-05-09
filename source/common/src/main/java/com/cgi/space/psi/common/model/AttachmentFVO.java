package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AttachmentFVO;
import com.cgi.space.psi.common.model.Quantity;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.net.URI;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * AttachmentFVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = AttachmentFVO.class, name = "Attachment")
})

@JsonTypeName("Attachment_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AttachmentFVO {

  @JsonProperty("@type")
  private String atType;

  @JsonProperty("@baseType")
  private String atBaseType;

  @JsonProperty("@schemaLocation")
  private String atSchemaLocation;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("url")
  private URI url;

  @JsonProperty("content")
  private org.springframework.core.io.Resource content;

  @JsonProperty("size")
  private Quantity size;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  @JsonProperty("attachmentType")
  private String attachmentType;

  @JsonProperty("mimeType")
  private String mimeType;

  public AttachmentFVO atType(String atType) {
    this.atType = atType;
    return this;
  }

  /**
   * When sub-classing, this defines the sub-class Extensible name
   * @return atType
  */
  @NotNull 
  @Schema(name = "@type", description = "When sub-classing, this defines the sub-class Extensible name", required = true)
  public String getAtType() {
    return atType;
  }

  public void setAtType(String atType) {
    this.atType = atType;
  }

  public AttachmentFVO atBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
    return this;
  }

  /**
   * When sub-classing, this defines the super-class
   * @return atBaseType
  */
  
  @Schema(name = "@baseType", description = "When sub-classing, this defines the super-class", required = false)
  public String getAtBaseType() {
    return atBaseType;
  }

  public void setAtBaseType(String atBaseType) {
    this.atBaseType = atBaseType;
  }

  public AttachmentFVO atSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
    return this;
  }

  /**
   * A URI to a JSON-Schema file that defines additional attributes and relationships
   * @return atSchemaLocation
  */
  
  @Schema(name = "@schemaLocation", description = "A URI to a JSON-Schema file that defines additional attributes and relationships", required = false)
  public String getAtSchemaLocation() {
    return atSchemaLocation;
  }

  public void setAtSchemaLocation(String atSchemaLocation) {
    this.atSchemaLocation = atSchemaLocation;
  }

  public AttachmentFVO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * unique identifier
   * @return id
  */
  
  @Schema(name = "id", description = "unique identifier", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AttachmentFVO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the attachment
   * @return name
  */
  
  @Schema(name = "name", description = "The name of the attachment", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AttachmentFVO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A narrative text describing the content of the attachment
   * @return description
  */
  
  @Schema(name = "description", example = "Photograph of the Product", description = "A narrative text describing the content of the attachment", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AttachmentFVO url(URI url) {
    this.url = url;
    return this;
  }

  /**
   * Uniform Resource Locator, is a web page address (a subset of URI)
   * @return url
  */
  @Valid 
  @Schema(name = "url", example = "https://host/Content/4aafacbd-11ff-4dc8-b445-305f2215715f", description = "Uniform Resource Locator, is a web page address (a subset of URI)", required = false)
  public URI getUrl() {
    return url;
  }

  public void setUrl(URI url) {
    this.url = url;
  }

  public AttachmentFVO content(org.springframework.core.io.Resource content) {
    this.content = content;
    return this;
  }

  /**
   * The actual contents of the attachment object, if embedded, encoded as base64
   * @return content
  */
  @Valid 
  @Schema(name = "content", description = "The actual contents of the attachment object, if embedded, encoded as base64", required = false)
  public org.springframework.core.io.Resource getContent() {
    return content;
  }

  public void setContent(org.springframework.core.io.Resource content) {
    this.content = content;
  }

  public AttachmentFVO size(Quantity size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
  */
  @Valid 
  @Schema(name = "size", required = false)
  public Quantity getSize() {
    return size;
  }

  public void setSize(Quantity size) {
    this.size = size;
  }

  public AttachmentFVO validFor(TimePeriod validFor) {
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

  public AttachmentFVO attachmentType(String attachmentType) {
    this.attachmentType = attachmentType;
    return this;
  }

  /**
   * a business characterization of the purpose of the attachment, for example logo, instructionManual, contractCopy
   * @return attachmentType
  */
  @NotNull 
  @Schema(name = "attachmentType", description = "a business characterization of the purpose of the attachment, for example logo, instructionManual, contractCopy", required = true)
  public String getAttachmentType() {
    return attachmentType;
  }

  public void setAttachmentType(String attachmentType) {
    this.attachmentType = attachmentType;
  }

  public AttachmentFVO mimeType(String mimeType) {
    this.mimeType = mimeType;
    return this;
  }

  /**
   * a technical characterization of the attachment content format using IETF Mime Types
   * @return mimeType
  */
  @NotNull 
  @Schema(name = "mimeType", description = "a technical characterization of the attachment content format using IETF Mime Types", required = true)
  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttachmentFVO attachmentFVO = (AttachmentFVO) o;
    return Objects.equals(this.atType, attachmentFVO.atType) &&
        Objects.equals(this.atBaseType, attachmentFVO.atBaseType) &&
        Objects.equals(this.atSchemaLocation, attachmentFVO.atSchemaLocation) &&
        Objects.equals(this.id, attachmentFVO.id) &&
        Objects.equals(this.name, attachmentFVO.name) &&
        Objects.equals(this.description, attachmentFVO.description) &&
        Objects.equals(this.url, attachmentFVO.url) &&
        Objects.equals(this.content, attachmentFVO.content) &&
        Objects.equals(this.size, attachmentFVO.size) &&
        Objects.equals(this.validFor, attachmentFVO.validFor) &&
        Objects.equals(this.attachmentType, attachmentFVO.attachmentType) &&
        Objects.equals(this.mimeType, attachmentFVO.mimeType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(atType, atBaseType, atSchemaLocation, id, name, description, url, content, size, validFor, attachmentType, mimeType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AttachmentFVO {\n");
    sb.append("    atType: ").append(toIndentedString(atType)).append("\n");
    sb.append("    atBaseType: ").append(toIndentedString(atBaseType)).append("\n");
    sb.append("    atSchemaLocation: ").append(toIndentedString(atSchemaLocation)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
    sb.append("    attachmentType: ").append(toIndentedString(attachmentType)).append("\n");
    sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
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

