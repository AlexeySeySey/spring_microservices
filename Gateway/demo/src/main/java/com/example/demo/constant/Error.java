package com.example.demo.constant;

public enum Error {

	UNPROCESSABLE_REQUEST("Request cannot be processed");
	
	private String data;
	
	Error(String data) {
		this.data = data;
	}
	
	public String get() {
		return this.data;
	}
}