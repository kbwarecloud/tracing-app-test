package com.kbware.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.kbware.app.iris.model.IrisResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static pl.t_mobile.logging.tracingv2.TMobileCustomPropagation.SPAN_ID_KEY;
import static pl.t_mobile.logging.tracingv2.TMobileCustomPropagation.TRACE_ID_KEY;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class TestControllerTests {
  
  @Autowired
  ObjectMapper objectMapper;
  
  @LocalServerPort
  int port;
  
  WireMockServer integrationServices;
  
  @BeforeEach
  public void setup() {
    integrationServices = new WireMockServer(WireMockConfiguration.wireMockConfig().port(30604));
    integrationServices.start();
  }
  
  @AfterEach
  public void teardown() {
    integrationServices.stop();
    integrationServices.resetAll();
  }
  
  @ParameterizedTest
  @ValueSource(strings = {
    "v1", //RestTemplate
    "v2", //OpenFeign
    "v3"  //WebClient
  })
  void shouldPropagateHeadersInApi(String versionApi) throws JsonProcessingException {
    // given
    var serviceUrl = "/mocks-service/iris/legacy/soabp/v1/physicalResource/esimProfile";
    var trackingId = "4d8d5d497be9866d";
    var requestId = "ca3037df-8653-4da6-915d-88f3e5b3fea2";
    
    integrationServices.stubFor(
      WireMock.post(urlEqualTo(serviceUrl))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(objectMapper.writeValueAsString(new IrisResponseDto()))
        )
    );
    
    // when
    var response = given()
      //lower case headers due to nginx
      .header(TRACE_ID_KEY.toLowerCase(), trackingId)
      .header(SPAN_ID_KEY.toLowerCase(), requestId)
      .post("http://localhost:" + port + "/test/"+ versionApi + "/perform")
      .prettyPeek();
    
    // then
    integrationServices.verify(
      postRequestedFor(urlEqualTo(serviceUrl))
        //TODO: with this assertion tests fails
        .withHeader(TRACE_ID_KEY, equalTo(trackingId))
    );
  }
}
