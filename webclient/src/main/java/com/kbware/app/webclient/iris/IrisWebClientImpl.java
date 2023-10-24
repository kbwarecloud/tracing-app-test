package com.kbware.app.webclient.iris;

import com.kbware.app.webclient.iris.model.IrisDto;
import com.kbware.app.webclient.iris.model.IrisResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class IrisWebClientImpl implements IrisWebClient {

  private static final String UPDATE_PROFILE = "/esimProfile";

  private final WebClient irisWebClientInternal;

  @Override
  public Mono<IrisResponseDto> updateEsimProfile(IrisDto dto) {
    return irisWebClientInternal
        .post()
        .uri(UPDATE_PROFILE)
        .body(Mono.just(dto), IrisDto.class)
        .retrieve()
        .bodyToMono(IrisResponseDto.class);
  }
}
