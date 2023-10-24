package com.kbware.app.resttemplate;

import com.kbware.app.resttemplate.iris.IrisRestClient;
import com.kbware.app.resttemplate.iris.model.IrisDto;
import com.kbware.app.resttemplate.iris.model.IrisResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired private IrisRestClient irisRestClient;

  @PostMapping("v1/perform")
  public ResponseEntity<IrisResponseDto> performRestTemplateCall() {
    var response = irisRestClient.updateEsimProfile(createRequest());
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
