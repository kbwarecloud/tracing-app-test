package com.kbware.app.iris.webclient;

import com.kbware.app.iris.model.IrisDto;
import com.kbware.app.iris.model.IrisResponseDto;
import org.springframework.http.ResponseEntity;

public interface IrisWebClient {
  
  ResponseEntity<IrisResponseDto> updateEsimProfile(IrisDto dto);
}
