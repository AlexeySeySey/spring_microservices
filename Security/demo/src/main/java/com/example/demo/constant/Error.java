package com.example.demo.constant;

public enum Error {

  ROLE_NOT_FOUND("Role not found"),
  FORBIDDEN("Forbidden"),
  UNAUTHORIZED("Unauthorized"),
  USER_REGISTERED("User with such email already registered"),
  UNREGISTERED_USER("User with such credentials is not registered");

  private final String data;

  Error(String data) {
    this.data = data;
  }

  public String get() {
    return data;
  }
}