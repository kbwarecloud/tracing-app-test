package com.kbware.app.webclient.iris;

import com.kbware.app.webclient.iris.model.IrisDto;
import com.kbware.app.webclient.iris.model.IrisResponseDto;
import reactor.core.publisher.Mono;

public interface IrisWebClient {

  Mono<IrisResponseDto> updateEsimProfile(IrisDto dto);
}
