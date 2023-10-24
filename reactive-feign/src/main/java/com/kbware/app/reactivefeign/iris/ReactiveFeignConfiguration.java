package com.kbware.app.reactivefeign.iris;

import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@Configuration
@EnableReactiveFeignClients
@Import(FeignClientsConfiguration.class)
public class ReactiveFeignConfiguration {

  @Bean
  feign.Logger.Level feignLoggerLevel() {
    return feign.Logger.Level.BASIC;
  }
}
