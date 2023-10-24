package com.kbware.app.feign;

import com.kbware.app.feign.iris.IrisFeignClient;
import com.kbware.app.feign.iris.model.IrisDto;
import com.kbware.app.feign.iris.model.IrisResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired private IrisFeignClient irisFeignClient;

  @PostMapping("v1/perform")
  public ResponseEntity<IrisResponseDto> performFeignClientCall() {
    var response = irisFeignClient.updateEsimProfile(createRequest());
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
