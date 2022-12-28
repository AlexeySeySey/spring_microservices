package com.example.demo.constant;

public enum Error {

  UNAUTHORIZED("Unauthorized"),
  KAFKA_TIMEOUT("Request processing takes too long");

  private String data;

  Error(String data) {
    this.data = data;
  }

  public String get() {
    return data;
  }
}