package com.kbware.app.reactivefeign.iris;

import com.kbware.app.reactivefeign.iris.model.IrisDto;
import com.kbware.app.reactivefeign.iris.model.IrisResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "iris-reactive-client", url = "${iris-client.config.url}")
public interface IrisReactiveFeignClient {

  @PostMapping("/esimProfile")
  Mono<IrisResponseDto> updateEsimProfile(IrisDto dto);
}
