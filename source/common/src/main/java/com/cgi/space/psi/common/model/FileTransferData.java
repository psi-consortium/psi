package com.cgi.space.psi.common.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.net.URI;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Defines the place where the report content should be stored.
 */
@com.fasterxml.jackson.annotation.JsonFilter(com.cgi.space.psi.common.config.DynamicJacksonFilterProvider.NAME)

@Schema(name = "FileTransferData", description = "Defines the place where the report content should be stored.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class FileTransferData {

  @JsonProperty("fileFormat")
  private String fileFormat;

  @JsonProperty("fileLocation")
  private URI fileLocation;

  @JsonProperty("transportProtocol")
  private String transportProtocol;

  /**
   * Compression types used for the collected data file.
   */
  public enum CompressionTypeEnum {
    NO_PACKING("NO_PACKING"),
    
    GZIP("GZIP"),
    
    TAR("TAR"),
    
    VENDOR_EXT("VENDOR_EXT"),
    
    MINOR_EXT("MINOR_EXT");

    private String value;

    CompressionTypeEnum(String value) {
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
    public static CompressionTypeEnum fromValue(String value) {
      for (CompressionTypeEnum b : CompressionTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("compressionType")
  private CompressionTypeEnum compressionType;

  @JsonProperty("packingType")
  private String packingType;

  @JsonProperty("retentionPeriod")
  private String retentionPeriod;

  public FileTransferData fileFormat(String fileFormat) {
    this.fileFormat = fileFormat;
    return this;
  }

  /**
   * Format of the file containing collected data.
   * @return fileFormat
  */
  
  @Schema(name = "fileFormat", description = "Format of the file containing collected data.", required = false)
  public String getFileFormat() {
    return fileFormat;
  }

  public void setFileFormat(String fileFormat) {
    this.fileFormat = fileFormat;
  }

  public FileTransferData fileLocation(URI fileLocation) {
    this.fileLocation = fileLocation;
    return this;
  }

  /**
   * Location of the file containing collected data.
   * @return fileLocation
  */
  @Valid 
  @Schema(name = "fileLocation", description = "Location of the file containing collected data.", required = false)
  public URI getFileLocation() {
    return fileLocation;
  }

  public void setFileLocation(URI fileLocation) {
    this.fileLocation = fileLocation;
  }

  public FileTransferData transportProtocol(String transportProtocol) {
    this.transportProtocol = transportProtocol;
    return this;
  }

  /**
   * Transport protocol to use for file transfer.
   * @return transportProtocol
  */
  
  @Schema(name = "transportProtocol", description = "Transport protocol to use for file transfer.", required = false)
  public String getTransportProtocol() {
    return transportProtocol;
  }

  public void setTransportProtocol(String transportProtocol) {
    this.transportProtocol = transportProtocol;
  }

  public FileTransferData compressionType(CompressionTypeEnum compressionType) {
    this.compressionType = compressionType;
    return this;
  }

  /**
   * Compression types used for the collected data file.
   * @return compressionType
  */
  
  @Schema(name = "compressionType", description = "Compression types used for the collected data file.", required = false)
  public CompressionTypeEnum getCompressionType() {
    return compressionType;
  }

  public void setCompressionType(CompressionTypeEnum compressionType) {
    this.compressionType = compressionType;
  }

  public FileTransferData packingType(String packingType) {
    this.packingType = packingType;
    return this;
  }

  /**
   * Specify if the data file is to be packed.
   * @return packingType
  */
  
  @Schema(name = "packingType", description = "Specify if the data file is to be packed.", required = false)
  public String getPackingType() {
    return packingType;
  }

  public void setPackingType(String packingType) {
    this.packingType = packingType;
  }

  public FileTransferData retentionPeriod(String retentionPeriod) {
    this.retentionPeriod = retentionPeriod;
    return this;
  }

  /**
   * A time interval to retain the file.
   * @return retentionPeriod
  */
  
  @Schema(name = "retentionPeriod", description = "A time interval to retain the file.", required = false)
  public String getRetentionPeriod() {
    return retentionPeriod;
  }

  public void setRetentionPeriod(String retentionPeriod) {
    this.retentionPeriod = retentionPeriod;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileTransferData fileTransferData = (FileTransferData) o;
    return Objects.equals(this.fileFormat, fileTransferData.fileFormat) &&
        Objects.equals(this.fileLocation, fileTransferData.fileLocation) &&
        Objects.equals(this.transportProtocol, fileTransferData.transportProtocol) &&
        Objects.equals(this.compressionType, fileTransferData.compressionType) &&
        Objects.equals(this.packingType, fileTransferData.packingType) &&
        Objects.equals(this.retentionPeriod, fileTransferData.retentionPeriod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileFormat, fileLocation, transportProtocol, compressionType, packingType, retentionPeriod);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FileTransferData {\n");
    sb.append("    fileFormat: ").append(toIndentedString(fileFormat)).append("\n");
    sb.append("    fileLocation: ").append(toIndentedString(fileLocation)).append("\n");
    sb.append("    transportProtocol: ").append(toIndentedString(transportProtocol)).append("\n");
    sb.append("    compressionType: ").append(toIndentedString(compressionType)).append("\n");
    sb.append("    packingType: ").append(toIndentedString(packingType)).append("\n");
    sb.append("    retentionPeriod: ").append(toIndentedString(retentionPeriod)).append("\n");
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

