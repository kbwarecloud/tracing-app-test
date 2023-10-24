package com.kbware.app.resttemplate.iris.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class IrisResponseDto {

  @JsonProperty("EsimProfile")
  private ESimProfile eSimProfile;
}
