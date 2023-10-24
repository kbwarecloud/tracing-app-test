package com.kbware.app.reactivefeign;

import com.kbware.app.reactivefeign.iris.IrisReactiveFeignClient;
import com.kbware.app.reactivefeign.iris.model.ESimProfile;
import com.kbware.app.reactivefeign.iris.model.IrisDto;
import com.kbware.app.reactivefeign.iris.model.IrisResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired private IrisReactiveFeignClient irisReactiveFeignClient;

  @PostMapping("v1/perform")
  public Mono<IrisResponseDto> performReactiveFeignClientCall() {
    var response = irisReactiveFeignClient.updateEsimProfile(createRequest());

    return response
        .map(
            irisResponseDto -> {
              log.info("Response: {}", irisResponseDto);
              return irisResponseDto;
            })
        .onErrorResume(
            throwable -> {
              log.error("Error: {}", throwable.getMessage());
              return Mono.just(IrisResponseDto.builder().eSimProfile(ESimProfile.UNKNOWN).build());
            });
  }

  private static IrisDto createRequest() {
    return IrisDto.builder()
        .serialNumber("8948023223069211228")
        .callId(1824365061)
        .operation("releaseProfileForDownload")
        .systemId(1234)
        .build();
  }
}
