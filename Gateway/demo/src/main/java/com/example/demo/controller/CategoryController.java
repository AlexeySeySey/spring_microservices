package com.example.demo.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mq.KafkaEvent;
import com.example.demo.mq.KafkaProducer;
import com.example.demo.mq.response.CategoryListResponse;
import com.example.demo.mq.response.KafkaBaseResponse;
import com.example.demo.service.ResponseFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private CategoryListResponse categoryListResponse;
	
	@Autowired
	private ResponseFactory responseFactory;
	
	@GetMapping("/")
	public HashMap<String, Object> list() throws Exception {
		
		Map<String, Object> response = this.kafkaProducer.request("CategoryController.list", "")
				.withResponse(categoryListResponse);
		
		return this.responseFactory.make(response.get("data"), (String) response.get("error"));
	}
}