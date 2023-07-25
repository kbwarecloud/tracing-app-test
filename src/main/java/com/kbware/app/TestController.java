package com.kbware.app;

import com.kbware.app.iris.feign.IrisFeignClient;
import com.kbware.app.iris.model.IrisDto;
import com.kbware.app.iris.model.IrisResponseDto;
import com.kbware.app.iris.resttemplate.IrisRestClient;
import com.kbware.app.iris.webclient.IrisWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
  
  @Autowired
  private IrisRestClient irisRestClient;
  
  @Autowired
  private IrisFeignClient irisFeignClient;
  
  @Autowired
  private IrisWebClient irisWebClient;
  
  @PostMapping("v1/perform")
  public ResponseEntity<IrisResponseDto> performV1() {
    var response = irisRestClient.updateEsimProfile(createRequest());
    return response;
  }
  
  @PostMapping("v2/perform")
  public ResponseEntity<IrisResponseDto> performV2() {
    var response = irisFeignClient.updateEsimProfile(createRequest());
    return response;
  }
  
  @PostMapping("v3/perform")
  public ResponseEntity<IrisResponseDto> performV3() {
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
