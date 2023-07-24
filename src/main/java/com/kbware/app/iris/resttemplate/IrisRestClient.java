package com.kbware.app.iris.resttemplate;

import com.kbware.app.iris.model.IrisDto;
import com.kbware.app.iris.model.IrisResponseDto;
import org.springframework.http.ResponseEntity;

public interface IrisRestClient {
  ResponseEntity<IrisResponseDto> updateEsimProfile(IrisDto dto);
  
}
