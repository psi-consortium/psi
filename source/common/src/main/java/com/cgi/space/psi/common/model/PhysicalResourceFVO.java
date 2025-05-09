package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.cgi.space.psi.common.model.AttachmentOrDocumentRef;
import com.cgi.space.psi.common.model.CharacteristicFVO;
import com.cgi.space.psi.common.model.ExternalIdentifierFVO;
import com.cgi.space.psi.common.model.FeatureFVO;
import com.cgi.space.psi.common.model.IntentRefFVO;
import com.cgi.space.psi.common.model.LogicalResourceFVO;
import com.cgi.space.psi.common.model.NoteFVO;
import com.cgi.space.psi.common.model.PhysicalResourceFVO;
import com.cgi.space.psi.common.model.RelatedPartyRefOrPartyRoleRefFVO;
import com.cgi.space.psi.common.model.RelatedPlaceRefFVO;
import com.cgi.space.psi.common.model.RelatedResourceOrderItemFVO;
import com.cgi.space.psi.common.model.ResourceAdministrativeStateType;
import com.cgi.space.psi.common.model.ResourceCollectionFVO;
import com.cgi.space.psi.common.model.ResourceFVO;
import com.cgi.space.psi.common.model.ResourceOperationalStateType;
import com.cgi.space.psi.common.model.ResourceRefOrValueFVO;
import com.cgi.space.psi.common.model.ResourceRelationshipFVO;
import com.cgi.space.psi.common.model.ResourceSpecificationRefFVO;
import com.cgi.space.psi.common.model.ResourceStatusType;
import com.cgi.space.psi.common.model.ResourceUsageStateType;
import com.cgi.space.psi.common.model.TimePeriod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.net.URI;
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
 * PhysicalResourceFVO
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@JsonIgnoreProperties(
  value = "@type", // ignore manually set @type, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the @type to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = LogicalResourceFVO.class, name = "LogicalResource"),
  @JsonSubTypes.Type(value = PhysicalResourceFVO.class, name = "PhysicalResource"),
  @JsonSubTypes.Type(value = ResourceFVO.class, name = "Resource"),
  @JsonSubTypes.Type(value = ResourceCollectionFVO.class, name = "ResourceCollection")
})

@JsonTypeName("PhysicalResource_FVO")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class PhysicalResourceFVO extends ResourceFVO {

  @JsonProperty("manufactureDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime manufactureDate;

  @JsonProperty("powerState")
  private String powerState;

  @JsonProperty("serialNumber")
  private String serialNumber;

  @JsonProperty("versionNumber")
  private String versionNumber;

  public PhysicalResourceFVO manufactureDate(OffsetDateTime manufactureDate) {
    this.manufactureDate = manufactureDate;
    return this;
  }

  /**
   * This is a string attribute that defines the date of manufacture of this item in the fixed format \"dd/mm/yyyy\". This is an optional attribute.
   * @return manufactureDate
  */
  @Valid 
  @Schema(name = "manufactureDate", description = "This is a string attribute that defines the date of manufacture of this item in the fixed format \"dd/mm/yyyy\". This is an optional attribute.", required = false)
  public OffsetDateTime getManufactureDate() {
    return manufactureDate;
  }

  public void setManufactureDate(OffsetDateTime manufactureDate) {
    this.manufactureDate = manufactureDate;
  }

  public PhysicalResourceFVO powerState(String powerState) {
    this.powerState = powerState;
    return this;
  }

  /**
   * This defines the current power status of the hardware item. Values include:    0:  Unknown   1:  Not Applicable   2:  No Power Applied   3: Full Power Applied   4:  Power Save - Normal   5:  Power Save - Degraded   6:  Power Save - Standby   7:  Power Save - Critical   8:  Power Save - Low Power Mode   9:  Power Save - Unknown  10: Power Cycle  11: Power Warning  12: Power Off
   * @return powerState
  */
  
  @Schema(name = "powerState", description = "This defines the current power status of the hardware item. Values include:    0:  Unknown   1:  Not Applicable   2:  No Power Applied   3: Full Power Applied   4:  Power Save - Normal   5:  Power Save - Degraded   6:  Power Save - Standby   7:  Power Save - Critical   8:  Power Save - Low Power Mode   9:  Power Save - Unknown  10: Power Cycle  11: Power Warning  12: Power Off", required = false)
  public String getPowerState() {
    return powerState;
  }

  public void setPowerState(String powerState) {
    this.powerState = powerState;
  }

  public PhysicalResourceFVO serialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  /**
   * This is a string that represents a manufacturer-allocated number used to identify different instances of the same hardware item. The ModelNumber and PartNumber attributes are used to identify different types of hardware items. This is a REQUIRED attribute.
   * @return serialNumber
  */
  
  @Schema(name = "serialNumber", description = "This is a string that represents a manufacturer-allocated number used to identify different instances of the same hardware item. The ModelNumber and PartNumber attributes are used to identify different types of hardware items. This is a REQUIRED attribute.", required = false)
  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public PhysicalResourceFVO versionNumber(String versionNumber) {
    this.versionNumber = versionNumber;
    return this;
  }

  /**
   * This is a string that identifies the version of this physical resource. This is an optional attribute.
   * @return versionNumber
  */
  
  @Schema(name = "versionNumber", description = "This is a string that identifies the version of this physical resource. This is an optional attribute.", required = false)
  public String getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(String versionNumber) {
    this.versionNumber = versionNumber;
  }

  public PhysicalResourceFVO atType(String atType) {
    super.setAtType(atType);
    return this;
  }

  public PhysicalResourceFVO atBaseType(String atBaseType) {
    super.setAtBaseType(atBaseType);
    return this;
  }

  public PhysicalResourceFVO atSchemaLocation(String atSchemaLocation) {
    super.setAtSchemaLocation(atSchemaLocation);
    return this;
  }

  public PhysicalResourceFVO href(URI href) {
    super.setHref(href);
    return this;
  }

  public PhysicalResourceFVO id(String id) {
    super.setId(id);
    return this;
  }

  public PhysicalResourceFVO category(String category) {
    super.setCategory(category);
    return this;
  }

  public PhysicalResourceFVO description(String description) {
    super.setDescription(description);
    return this;
  }

  public PhysicalResourceFVO name(String name) {
    super.setName(name);
    return this;
  }

  public PhysicalResourceFVO endOperatingDate(OffsetDateTime endOperatingDate) {
    super.setEndOperatingDate(endOperatingDate);
    return this;
  }

  public PhysicalResourceFVO administrativeState(ResourceAdministrativeStateType administrativeState) {
    super.setAdministrativeState(administrativeState);
    return this;
  }

  public PhysicalResourceFVO operationalState(ResourceOperationalStateType operationalState) {
    super.setOperationalState(operationalState);
    return this;
  }

  public PhysicalResourceFVO resourceStatus(ResourceStatusType resourceStatus) {
    super.setResourceStatus(resourceStatus);
    return this;
  }

  public PhysicalResourceFVO usageState(ResourceUsageStateType usageState) {
    super.setUsageState(usageState);
    return this;
  }

  public PhysicalResourceFVO validFor(TimePeriod validFor) {
    super.setValidFor(validFor);
    return this;
  }

  public PhysicalResourceFVO note(List<NoteFVO> note) {
    super.setNote(note);
    return this;
  }

  public PhysicalResourceFVO addNoteItem(NoteFVO noteItem) {
    super.addNoteItem(noteItem);
    return this;
  }

  public PhysicalResourceFVO resourceOrderItem(List<RelatedResourceOrderItemFVO> resourceOrderItem) {
    super.setResourceOrderItem(resourceOrderItem);
    return this;
  }

  public PhysicalResourceFVO addResourceOrderItemItem(RelatedResourceOrderItemFVO resourceOrderItemItem) {
    super.addResourceOrderItemItem(resourceOrderItemItem);
    return this;
  }

  public PhysicalResourceFVO place(List<RelatedPlaceRefFVO> place) {
    super.setPlace(place);
    return this;
  }

  public PhysicalResourceFVO addPlaceItem(RelatedPlaceRefFVO placeItem) {
    super.addPlaceItem(placeItem);
    return this;
  }

  public PhysicalResourceFVO relatedParty(List<RelatedPartyRefOrPartyRoleRefFVO> relatedParty) {
    super.setRelatedParty(relatedParty);
    return this;
  }

  public PhysicalResourceFVO addRelatedPartyItem(RelatedPartyRefOrPartyRoleRefFVO relatedPartyItem) {
    super.addRelatedPartyItem(relatedPartyItem);
    return this;
  }

  public PhysicalResourceFVO supportingResource(List<ResourceRefOrValueFVO> supportingResource) {
    super.setSupportingResource(supportingResource);
    return this;
  }

  public PhysicalResourceFVO addSupportingResourceItem(ResourceRefOrValueFVO supportingResourceItem) {
    super.addSupportingResourceItem(supportingResourceItem);
    return this;
  }

  public PhysicalResourceFVO resourceRelationship(List<ResourceRelationshipFVO> resourceRelationship) {
    super.setResourceRelationship(resourceRelationship);
    return this;
  }

  public PhysicalResourceFVO addResourceRelationshipItem(ResourceRelationshipFVO resourceRelationshipItem) {
    super.addResourceRelationshipItem(resourceRelationshipItem);
    return this;
  }

  public PhysicalResourceFVO resourceCharacteristic(List<CharacteristicFVO> resourceCharacteristic) {
    super.setResourceCharacteristic(resourceCharacteristic);
    return this;
  }

  public PhysicalResourceFVO addResourceCharacteristicItem(CharacteristicFVO resourceCharacteristicItem) {
    super.addResourceCharacteristicItem(resourceCharacteristicItem);
    return this;
  }

  public PhysicalResourceFVO attachment(List<AttachmentOrDocumentRef> attachment) {
    super.setAttachment(attachment);
    return this;
  }

  public PhysicalResourceFVO addAttachmentItem(AttachmentOrDocumentRef attachmentItem) {
    super.addAttachmentItem(attachmentItem);
    return this;
  }

  public PhysicalResourceFVO resourceSpecification(ResourceSpecificationRefFVO resourceSpecification) {
    super.setResourceSpecification(resourceSpecification);
    return this;
  }

  public PhysicalResourceFVO startOperatingDate(OffsetDateTime startOperatingDate) {
    super.setStartOperatingDate(startOperatingDate);
    return this;
  }

  public PhysicalResourceFVO resourceVersion(String resourceVersion) {
    super.setResourceVersion(resourceVersion);
    return this;
  }

  public PhysicalResourceFVO activationFeature(List<FeatureFVO> activationFeature) {
    super.setActivationFeature(activationFeature);
    return this;
  }

  public PhysicalResourceFVO addActivationFeatureItem(FeatureFVO activationFeatureItem) {
    super.addActivationFeatureItem(activationFeatureItem);
    return this;
  }

  public PhysicalResourceFVO intent(IntentRefFVO intent) {
    super.setIntent(intent);
    return this;
  }

  public PhysicalResourceFVO externalIdentifier(List<ExternalIdentifierFVO> externalIdentifier) {
    super.setExternalIdentifier(externalIdentifier);
    return this;
  }

  public PhysicalResourceFVO addExternalIdentifierItem(ExternalIdentifierFVO externalIdentifierItem) {
    super.addExternalIdentifierItem(externalIdentifierItem);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhysicalResourceFVO physicalResourceFVO = (PhysicalResourceFVO) o;
    return Objects.equals(this.manufactureDate, physicalResourceFVO.manufactureDate) &&
        Objects.equals(this.powerState, physicalResourceFVO.powerState) &&
        Objects.equals(this.serialNumber, physicalResourceFVO.serialNumber) &&
        Objects.equals(this.versionNumber, physicalResourceFVO.versionNumber) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(manufactureDate, powerState, serialNumber, versionNumber, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhysicalResourceFVO {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    manufactureDate: ").append(toIndentedString(manufactureDate)).append("\n");
    sb.append("    powerState: ").append(toIndentedString(powerState)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("    versionNumber: ").append(toIndentedString(versionNumber)).append("\n");
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

