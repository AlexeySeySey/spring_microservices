package com.example.demo.mq;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.filter.ExceptionHandlerFilter;
import com.example.demo.mq.response.CategoryListResponse;
import com.example.demo.mq.response.KafkaBaseResponse;
import com.example.demo.mq.response.ProductListResponse;

@Component
public class KafkaEvent {
	
	@Autowired
    private ProductListResponse productListResponse;
    
	@Autowired
    private CategoryListResponse categoryListResponse;
	
	@KafkaListener(groupId="group_id", topics="ProductController.list.response")
	public void listenProductList(String response) {
		productListResponse.setData(response);
	}
	
	@KafkaListener(groupId="group_id", topics= {"CategoryController.list.response"})
	public void listenCategoryList(String response) {
		categoryListResponse.setData(response);
	}
}