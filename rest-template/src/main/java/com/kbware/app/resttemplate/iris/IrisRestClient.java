package com.kbware.app.resttemplate.iris;

import com.kbware.app.resttemplate.iris.model.IrisDto;
import com.kbware.app.resttemplate.iris.model.IrisResponseDto;
import org.springframework.http.ResponseEntity;

public interface IrisRestClient {
  ResponseEntity<IrisResponseDto> updateEsimProfile(IrisDto dto);
}
