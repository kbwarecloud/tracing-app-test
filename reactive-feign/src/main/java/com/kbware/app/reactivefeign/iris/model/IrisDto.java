package com.kbware.app.reactivefeign.iris.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class IrisDto {

  @JsonProperty("iccId")
  private String serialNumber;

  @JsonProperty("eid")
  private String eid;

  @JsonProperty("systemId")
  private Integer systemId;

  @JsonProperty("callId")
  private Integer callId;

  @JsonProperty("operation")
  private String operation;
}
