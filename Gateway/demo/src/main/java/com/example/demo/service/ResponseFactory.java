package com.example.demo.service;

import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class ResponseFactory {

  public HashMap<String, String> make(String data, String error) {
    HashMap<String, String> response = new HashMap<String, String>();
    response.put("data", data);
    response.put("errors", error);
    return response;
  }
}
