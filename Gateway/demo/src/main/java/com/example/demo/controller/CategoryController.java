package com.example.demo.controller;

import com.example.demo.mq.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

  private final KafkaProducer kafkaProducer;

  @Autowired
  public CategoryController(KafkaProducer kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  @GetMapping("/")
  public String index() throws Exception {
    return kafkaProducer.request("CategoryController.list", null, true);
  }
}