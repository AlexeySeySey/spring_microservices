package com.example.demo.service;

import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class ResponseFactory {

  public HashMap<String, Object> make(Object data, String error) {
    HashMap<String, Object> response = new HashMap<String, Object>();
    response.put("data", data);
    response.put("errors", error);
    return response;
  }
}
