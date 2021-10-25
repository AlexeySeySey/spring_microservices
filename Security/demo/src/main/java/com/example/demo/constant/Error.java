package com.example.demo.constant;

public enum Error {

	ROLE_ASSIGNMENT("Cannot assign role"),
	USER_NOT_FOUND("User not found"),
	FORBIDDEN("Forbidden"),
    UNAUTHORIZED("Unauthorized"),
	USER_REGISTERED("User with such email already registered"),
	UNREGISTERED_USER("User with such credentials is not registered");
	
	private String data;
	
	Error(String data) {
		this.data = data;
	}
	
	public String get() {
		return this.data;
	}
}