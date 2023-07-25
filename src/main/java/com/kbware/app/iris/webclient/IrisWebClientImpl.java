package com.kbware.app.iris.webclient;

import com.kbware.app.iris.model.IrisDto;
import com.kbware.app.iris.model.IrisResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class IrisWebClientImpl implements IrisWebClient {
  
  private static final String UPDATE_PROFILE = "/esimProfile";
  
  private final WebClient irisWebClientInternal;
  
  @Override
  public ResponseEntity<IrisResponseDto> updateEsimProfile(IrisDto dto) {
    var result = irisWebClientInternal.post()
      .uri(UPDATE_PROFILE)
      .body(Mono.just(dto), IrisDto.class)
      .retrieve()
      .bodyToMono(IrisResponseDto.class)
      .block();
    
    return ResponseEntity.ok(result);
  }
}

