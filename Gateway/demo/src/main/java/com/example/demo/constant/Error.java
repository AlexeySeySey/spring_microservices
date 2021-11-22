package com.example.demo.constant;

public enum Error {

	UNAUTHORIZED("Unauthorized"),
	UNPROCESSABLE_REQUEST("Request cannot be processed"),
	KAFKA_TIMEOUT("Request processing takes too long");
	
	private String data;
	
	Error(String data) {
		this.data = data;
	}
	
	public String get() {
		return this.data;
	}
}