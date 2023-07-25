package com.kbware.app.iris.resttemplate;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.Arrays;

@Data
@Builder
public class RestRequest {
  
  private HttpMethod httpMethod;
  private String url;
  private Class responseClazz;
  private ParameterizedTypeReference responseType;
  private Object[] uriVariables;
  private HttpEntity requestEntity;
  
  public Object[] getUriVariables() {
    return uriVariables == null ? null : Arrays.copyOf(uriVariables, uriVariables.length);
  }
  
  public void setUriVariables(Object[] variables) {
    this.uriVariables = variables != null ? Arrays.copyOf(variables, variables.length) : null;
  }
  
  public static class RestRequestBuilder {
    public RestRequestBuilder uriVariables(Object[] variables) {
      this.uriVariables = variables != null ? Arrays.copyOf(variables, variables.length) : null;
      return this;
    }
  }
}
