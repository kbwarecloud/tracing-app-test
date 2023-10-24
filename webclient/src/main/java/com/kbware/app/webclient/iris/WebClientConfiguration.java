package com.kbware.app.webclient.iris;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

  @Bean
  public WebClient irisWebClientInternal(
      @Value("${iris-client.config.url}") String url, WebClient.Builder webClientBuilder) {
    return webClientBuilder
        .baseUrl(url)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader("X-Tenant-Id", "tmpl")
        .defaultHeader("X-Originator", "RIM")
        .build();
  }
}
