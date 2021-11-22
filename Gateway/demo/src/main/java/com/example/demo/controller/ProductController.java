package com.example.demo.controller;

import java.util.List;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyTypedMessageFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotation.Protected;
import com.example.demo.mq.KafkaProducer;
import com.example.demo.mq.request.ProductListRequest;
import com.example.demo.mq.request.ProductStoreRequest;
import com.example.demo.mq.response.KafkaBaseResponse;
import com.example.demo.mq.response.ProductListResponse;
import com.example.demo.service.ResponseFactory;
import com.example.demo.service.ServletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private ResponseFactory responseFactory;
	
	@Autowired
	private ProductListResponse productListResponse;

	@Protected
	@GetMapping("/")
	public Map<String, Object> list(ProductListRequest productListRequest) throws Exception {

		Map<String, Object> response = this.kafkaProducer.request("ProductController.list", productListRequest.asJson())
				.withResponse(productListResponse);

		return this.responseFactory.makeFromKafkaResponse(response);
	}
}
