package com.example.demo.mq.response;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public abstract class KafkaBaseResponse {

	protected String data;
	
	protected String error;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public synchronized void setData(String data) {
		this.data = data;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public synchronized String getData() {
		String data = this.data;
		this.data = null;
		return data;
	}
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public synchronized void setError(String error) {
		this.error = error;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public synchronized String getError() {
		String data = this.data;
		this.data = null;
		return data;
	}
}