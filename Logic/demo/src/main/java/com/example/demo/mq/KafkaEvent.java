package com.example.demo.mq;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.factory.KafkaResponseFactory;
import com.example.demo.mq.request.Request;
import com.example.demo.mq.request.ProductListRequest;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEvent {

  private final ProductService productService;
  private final CategoryService categoryService;
  private final KafkaTemplate<String, KafkaResponse> kafkaTemplate;

  private static final Logger logger = LogManager.getLogger(KafkaEvent.class);

  @Autowired
  public KafkaEvent(
      ProductService productService,
      CategoryService categoryService,
      KafkaTemplate<String, KafkaResponse> kafkaTemplate) {
    this.productService = productService;
    this.categoryService = categoryService;
    this.kafkaTemplate = kafkaTemplate;
  }

  @KafkaListener(groupId = "group_id", topics = {"ProductController.list"})
  public void listenProductList(Request<ProductListRequest> request) {

    String error = null;
    List<ProductDto> products = null;
    ProductListRequest productListRequest = request.getRequest();

    try {
      products = productService.findAll(productListRequest.getProductName(),
          productListRequest.getCategory());
    } catch (Exception e) {
      error = e.getMessage();
      logger.error("ERROR", e);
    }

    KafkaResponse response = KafkaResponseFactory.make(products, error, request.getResponseToken());
    kafkaTemplate.send("ProductController.list.response", response);
  }

  @KafkaListener(groupId = "group_id", topics = {"CategoryController.list"})
  public void listenCategoryList(Request<?> request) {

    String error = null;
    List<CategoryDto> categories = null;

    try {
      categories = categoryService.findAll();
    } catch (Exception e) {
      error = e.getMessage();
      logger.error("ERROR", e);
    }

    KafkaResponse response = KafkaResponseFactory.make(categories, error, request.getResponseToken());
    kafkaTemplate.send("CategoryController.list.response", response);
  }
}