package com.example.demo.controller;

import com.example.demo.annotation.Protected;
import com.example.demo.mq.KafkaProducer;
import com.example.demo.mq.request.ProductListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final KafkaProducer kafkaProducer;

  @Autowired
  public ProductController(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @Protected
  @GetMapping("/")
  public String index(ProductListRequest productListRequest) {
    return kafkaProducer.request("ProductController.list", productListRequest, true);
  }
}
