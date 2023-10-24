package com.kbware.app.reactivefeign.iris.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ESimProfile {

  public static final ESimProfile UNKNOWN = ESimProfile.builder().iccId("unknown").build();

  @JsonProperty("iccId")
  private String iccId;

  @JsonProperty("isRetriable")
  private boolean isRetriable;

  @JsonProperty("returnCode")
  private String returnCode;

  @JsonProperty("returnInfo")
  private String returnInfo;
}
