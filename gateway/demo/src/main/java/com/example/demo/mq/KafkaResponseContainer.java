package com.example.demo.mq;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class KafkaResponseContainer {

  private final Map<String, String> responses = new ConcurrentHashMap<>();

  public void addResponse(@NonNull String id, @NonNull String response) {
    responses.put(id, response);
  }

  public String getResponse(@NonNull String id) {
    return responses.get(id);
  }

  public void removeResponse(@NonNull String id) {
    responses.remove(id);
  }
}
