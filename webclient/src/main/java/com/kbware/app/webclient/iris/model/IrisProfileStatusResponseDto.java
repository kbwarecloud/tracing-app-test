package com.kbware.app.webclient.iris.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IrisProfileStatusResponseDto {

  @JsonProperty("EUICCID")
  private String iccId;

  @JsonProperty("profileStatus")
  private Integer profileStatus;

  @JsonProperty("returnCode")
  private Integer returnCode;
}
