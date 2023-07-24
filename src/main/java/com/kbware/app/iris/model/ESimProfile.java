package com.kbware.app.iris.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ESimProfile {

  @JsonProperty("iccId")
  private String iccId;

  @JsonProperty("isRetriable")
  private String isRetriable;

  @JsonProperty("returnCode")
  private String returnCode;

  @JsonProperty("returnInfo")
  private String returnInfo;

}
