package com.example.demo.constant;

public enum Message {

  ERROR("ERROR"),
  ACCESS_ALLOWED("Access allowed"),
  REGISTRATION_SUCCEED("Successfully registered");

  private String data;

  Message(String data) {
    this.data = data;
  }

  public String get() {
    return data;
  }
}