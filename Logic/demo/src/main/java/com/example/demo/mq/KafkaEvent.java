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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.contract.DTOable;
import com.example.demo.contract.IDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.DTOService;
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
	private CategoryService categoryService;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private JsonService jsonService;
	
	@Autowired
	private DTOService dtoService;

	private static final Logger logger = LogManager.getLogger(KafkaEvent.class);

	@KafkaListener(groupId = "group_id", topics = { "ProductController.list" })
	public void listenProductList(String request) {
		
		String error = null;
		String responseJson = null;
		List<Product> products = null;

		try {

			Map<String, String> requestMap = this.jsonService.decode(request);

			products = this.productService.findAll(requestMap.get("searchQuery"), requestMap.get("category"));

		} catch (Exception e) {
			error = e.getMessage();
			logger.error("ERROR", e);
		}
		
		KafkaResponse response = new KafkaResponse();
		response.setData(products);
		response.setError(error);
		
		responseJson = jsonService.encode(response);

		kafkaTemplate.send("ProductController.list.response", responseJson);
	}

	@KafkaListener(groupId = "group_id", topics = { "CategoryController.list" })
	public void listenCategoryList(String request) {
		
		String error = null;
		String responseJson = null;
		List<IDTO> categoryDTOs = null;

		try {
			
			List<? extends DTOable> categories = this.categoryService.findAll();
			
			categoryDTOs = dtoService.generate(categories);
			
		} catch (Exception e) {
			error = e.getMessage();
			logger.error("ERROR", e);
		}
		
		KafkaResponse response = new KafkaResponse();
		response.setData(categoryDTOs);
		response.setError(error);
		
		kafkaTemplate.send("CategoryController.list.response", jsonService.encode(response));
	}
}