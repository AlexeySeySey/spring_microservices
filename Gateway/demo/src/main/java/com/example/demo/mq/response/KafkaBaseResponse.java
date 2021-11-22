package com.example.demo.mq.response;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.context.annotation.ScopedProxyMode;

public abstract class KafkaBaseResponse {

	protected String data;
	
	protected String error;

	public synchronized void setData(String data) {
		this.data = data;
	}

	public synchronized String getData() {
		String data = this.data;
		this.data = null;
		return data;
	}
	
	public synchronized void setError(String error) {
		this.error = error;
	}

	public synchronized String getError() {
		String data = this.data;
		this.data = null;
		return data;
	}
}