package com.example.demo.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Product;
import com.example.demo.service.JsonService;
import com.example.demo.service.ProductService;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class KafkaEvent {

	@Autowired
	private ProductService productService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private JsonService jsonService;
	
	private static final Logger logger = LogManager.getLogger(KafkaEvent.class);

	@KafkaListener(groupId = "group_id", topics = { "ProductController.list" })
	public void listen(String request) {
		
		String error = null;
		List<Product> products = null;

		try {
			
			Map<String, String> requestMap = this.jsonService.decode(request);

			products = this.productService.findAll(requestMap.get("searchQuery"),
					requestMap.get("category"));
			
		} catch (Exception e) {
			error = e.getMessage();
			logger.error("ERROR", e);
		}
		
		KafkaResponse response = new KafkaResponse();
		response.setData(products);
		response.setError(error);
		
		kafkaTemplate.send("ProductController.list.response", this.jsonService.encode(response));
	}
}