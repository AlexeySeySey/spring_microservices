package com.example.demo.mq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class KafkaResponseContainer {

  private final Map<String, String> responses = new ConcurrentHashMap<>();

  public void addResponse(String id, String response) {
    responses.put(id, response);
  }

  public String getResponse(String id) {
    return responses.get(id);
  }

  public void removeResponse(String id) {
    responses.remove(id);
  }
}
