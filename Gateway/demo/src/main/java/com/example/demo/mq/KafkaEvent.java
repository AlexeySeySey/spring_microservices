package com.example.demo.mq;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.mq.response.ProductListResponse;

@Component
public class KafkaEvent {
	
    @Autowired 
    private ProductListResponse productListResponse;
	
	@KafkaListener(groupId="group_id", topics="ProductController.list.response")
	public void listen(String request) {
		this.productListResponse.setData(request);
	}
}