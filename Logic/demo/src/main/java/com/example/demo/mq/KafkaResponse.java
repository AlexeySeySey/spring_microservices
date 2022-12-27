package com.example.demo.mq;

public class KafkaResponse {

  private Object data;

  private String error;

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
}