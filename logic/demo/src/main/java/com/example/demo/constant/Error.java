package com.example.demo.constant;

public enum Error {

  REQUEST_DESERIALIZATION_FAILURE("Deserialization from byte[] to Request failed");

  private final String data;

  Error(String data) {
    this.data = data;
  }

  public String get() {
    return data;
  }
}