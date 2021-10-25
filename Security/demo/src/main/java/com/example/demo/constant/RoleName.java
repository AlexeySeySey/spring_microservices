package com.example.demo.constant;

public enum RoleName {
	
	CLIENT("client"),
	MANAGER("manager"),
	ADMIN("admin");
	
	private String data;
	
	RoleName(String data) {
		this.data = data;
	}
	
	public String get() {
		return this.data;
	}
}