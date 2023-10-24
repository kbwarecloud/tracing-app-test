package com.kbware.app.resttemplate.iris;

import com.kbware.app.resttemplate.iris.model.IrisDto;
import com.kbware.app.resttemplate.iris.model.IrisResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(value = "iris-client.enabled", havingValue = "true")
public class IrisRestClientImpl extends RestTemplateInvoke implements IrisRestClient {

  @Value("${iris-client.config.url}")
  private String irisUrl;

  private static final String UPDATE_PROFILE = "/esimProfile";

  public ResponseEntity<IrisResponseDto> updateEsimProfile(IrisDto dto) {
    RestRequest restRequest =
        RestRequest.builder()
            .requestEntity(new HttpEntity<>(dto, createHeaders()))
            .url(irisUrl + UPDATE_PROFILE)
            .httpMethod(HttpMethod.POST)
            .responseClazz(IrisResponseDto.class)
            .uriVariables(new Object[] {})
            .build();
    return invoke(restRequest);
  }

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("X-Tenant-Id", "tmpl");
    headers.set("X-Originator", "RIM");
    return headers;
  }
}
