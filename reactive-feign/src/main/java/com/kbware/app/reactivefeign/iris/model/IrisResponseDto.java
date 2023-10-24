package com.kbware.app.reactivefeign.iris.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IrisResponseDto {

  @JsonProperty("EsimProfile")
  private ESimProfile eSimProfile;
}
