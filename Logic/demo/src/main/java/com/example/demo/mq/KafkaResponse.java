package com.example.demo.mq;

public class KafkaResponse {

  private Object data;

  private String error;

  private String responseToken;

  public void setData(Object data) {
    this.data = data;
  }

  public Object getData() {
    return data;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getError() {
    return error;
  }

  public String getResponseToken() {
    return responseToken;
  }

  public void setResponseToken(String responseToken) {
    this.responseToken = responseToken;
  }
}