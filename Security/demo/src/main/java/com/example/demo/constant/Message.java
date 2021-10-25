package com.example.demo.constant;

public enum Message {
	
	ACCESS_ALLOWED("Access allowed"),
	REGISTRATION_SUCCEED("Successfully registered");
	
	private String data;
	
	Message(String data) {
		this.data = data;
	}
	
	public String get() {
		return this.data;
	}
}