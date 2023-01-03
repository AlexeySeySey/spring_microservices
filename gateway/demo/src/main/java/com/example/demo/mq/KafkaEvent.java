package com.example.demo.mq;

import com.example.demo.mq.response.KafkaResponseToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaEvent {

  private final KafkaResponseContainer kafkaResponseContainer;

  @Autowired
  public KafkaEvent(KafkaResponseContainer kafkaResponseContainer) {
    this.kafkaResponseContainer = kafkaResponseContainer;
  }

  @KafkaListener(groupId = "group_id", topics = {"CategoryController.list.response",
      "ProductController.list.response"})
  public void listen(String response) {
    KafkaResponseToken responseToken = new Gson().fromJson(response, KafkaResponseToken.class);
    kafkaResponseContainer.addResponse(responseToken.getResponseToken(), response);
  }
}