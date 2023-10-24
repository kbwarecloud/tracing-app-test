package com.kbware.app.webclient;

import com.kbware.app.webclient.iris.IrisWebClient;
import com.kbware.app.webclient.iris.model.IrisDto;
import com.kbware.app.webclient.iris.model.IrisResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired private IrisWebClient irisWebClient;

  @PostMapping("v1/perform")
  public ResponseEntity<IrisResponseDto> performWebClientBlockingCall() {
    var response = irisWebClient.updateEsimProfile(createRequest()).block();
    return ResponseEntity.ok(response);
  }

  @PostMapping("v2/perform")
  public Mono<IrisResponseDto> performWebClientNonBlockingCall() {
    var response = irisWebClient.updateEsimProfile(createRequest());
    return response;
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
