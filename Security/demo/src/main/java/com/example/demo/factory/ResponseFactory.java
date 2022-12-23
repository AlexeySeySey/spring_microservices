package com.example.demo.factory;

import com.example.demo.gen.Response;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

  public Response make(String data, String error) {
    Response response = new Response();
    response.setData(error.isBlank() ? data : "");
    response.setError(error);
    return response;
  }
}