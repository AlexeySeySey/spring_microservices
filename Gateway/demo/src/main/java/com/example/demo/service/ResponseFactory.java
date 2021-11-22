package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ResponseFactory {
	
  public HashMap<String, Object> makeFromKafkaResponse(Map<String, Object> response) {
	  return this.make(response.get("data"), response.get("error"));
  }

  public HashMap<String, Object> make(Object data, Object object) {
    HashMap<String, Object> response = new HashMap<String, Object>();
    response.put("data", data);
    response.put("errors", object);
    return response;
  }
}
