package com.example.demo.mq.request;

public class Request<T> {

  protected String responseToken;

  protected T request;

  public String getResponseToken() {
    return responseToken;
  }

  public void setResponseToken(String responseToken) {
    this.responseToken = responseToken;
  }

  public T getRequest() {
    return request;
  }

  public void setRequest(T request) {
    this.request = request;
  }
}
