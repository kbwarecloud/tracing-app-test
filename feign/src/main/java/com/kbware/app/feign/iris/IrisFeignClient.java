package com.kbware.app.feign.iris;

import com.kbware.app.feign.iris.model.IrisDto;
import com.kbware.app.feign.iris.model.IrisResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "iris-client", url = "${iris-client.config.url}")
public interface IrisFeignClient {

  @PostMapping("/esimProfile")
  ResponseEntity<IrisResponseDto> updateEsimProfile(IrisDto dto);
}
