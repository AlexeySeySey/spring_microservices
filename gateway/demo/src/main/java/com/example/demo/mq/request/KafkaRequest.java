package com.example.demo.mq.request;

import java.util.Objects;

public class KafkaRequest {

  private String responseToken;
  private Object request;

  public String getResponseToken() {
    return responseToken;
  }

  public void setResponseToken(String responseToken) {
    this.responseToken = responseToken;
  }

  public Object getRequest() {
    return request;
  }

  public void setRequest(Object request) {
    this.request = request;
  }
}
