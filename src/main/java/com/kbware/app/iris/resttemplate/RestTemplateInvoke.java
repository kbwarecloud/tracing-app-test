package com.kbware.app.iris.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public abstract class RestTemplateInvoke {
  
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private ObjectMapper objectMapper;
  
  protected enum Type {
    POST, GET, PUT, PATCH, POST_LOCATION
  }
  
  public <T> ResponseEntity<T> invoke(RestRequest restRequest) {
    try {
      logRequest(restRequest);
      ResponseEntity<T> response = exchange(restRequest);
      logResponse(restRequest, response);
      return response;
    } catch (HttpStatusCodeException e) {
      throw new IllegalStateException(e);
    }
  }
  
  @SuppressWarnings("unchecked")
  private <T> ResponseEntity<T> exchange(RestRequest restRequest) {
    if (restRequest.getResponseClazz() != null) {
      return restTemplate.exchange(restRequest.getUrl(), restRequest.getHttpMethod(), restRequest.getRequestEntity(),
        restRequest.getResponseClazz(), restRequest.getUriVariables());
    } else {
      return restTemplate.exchange(restRequest.getUrl(), restRequest.getHttpMethod(), restRequest.getRequestEntity(),
        restRequest.getResponseType(), restRequest.getUriVariables());
    }
  }
  
  private void logRequest(RestRequest restRequest) {
    try {
      log.debug(String.format("Call [%s] %s with params:%s, requestEntity: %s", restRequest.getHttpMethod(), restRequest.getUrl(),
        Arrays.toString(restRequest.getUriVariables()), objectMapper.writeValueAsString(restRequest.getRequestEntity())));
    } catch (JsonProcessingException e) {
      log.debug(String.format("Call [%s] %s with params:%s, requestHeaders: %s", restRequest.getHttpMethod(), restRequest.getUrl(),
        Arrays.toString(restRequest.getUriVariables()), restRequest.getRequestEntity().getHeaders()));
    }
  }
  
  private <T> void logResponse(RestRequest restRequest, ResponseEntity<T> response) {
    try {
      log.debug(String.format("Response from call [%s] %s with params:%s, Response status: %s, responseHeaders: %s, responseBody: %s",
        restRequest.getHttpMethod(),
        restRequest.getUrl(),
        Arrays.toString(restRequest.getUriVariables()), response.getStatusCode(), response.getHeaders(), objectMapper.writeValueAsString(response.getBody())));
    } catch (JsonProcessingException e) {
      log.debug(String.format("Response from call [%s] %s with params:%s, Response status: %s, responseHeaders: %s, responseBody: %s",
        restRequest.getHttpMethod(),
        restRequest.getUrl(),
        Arrays.toString(restRequest.getUriVariables()), response.getStatusCode(), response.getHeaders(), response.getBody()));
    }
  }
  
  public String createErrorMessage(String responseBody, String errorMessage, RestRequest restRequest) {
    var defaultMessage = String.format("URI: %s, UriVariables: %s, Message: %s, ResponseBody: {%s}",
      restRequest.getUrl(), Arrays.toString(restRequest.getUriVariables()), errorMessage, responseBody);
    log.warn(defaultMessage);
    
    try {
      var details = objectMapper.readValue(responseBody, HashMap.class);
      var code = details.get("code");
      var message = details.get("message");
      
      return code == null && message == null ? defaultMessage : Stream.of(code, message)
        .filter(Objects::nonNull)
        .map(Objects::toString)
        .collect(Collectors.joining(" - "));
      
    } catch (IOException ex) {
      log.error("Failed to deserialize response from external system", ex);
      return defaultMessage;
    }
  }
  
}
