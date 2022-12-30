package com.example.demo.mq.request;

import com.example.demo.constant.Error;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class Request implements Deserializer<Request> {

  private String responseToken;

  private Map<String, String> request = new HashMap<>();

  @Override
  public Request deserialize(String topic, byte[] data) {
    try {
      return data == null ? null
          : new Gson().fromJson(new String(data), Request.class);
    } catch (Exception e) {
      throw new SerializationException(Error.REQUEST_DESERIALIZATION_FAILURE.get());
    }
  }

  public String getResponseToken() {
    return responseToken;
  }

  public void setResponseToken(String responseToken) {
    this.responseToken = responseToken;
  }

  public Map<String, String> getRequest() {
    return request;
  }

  public void setRequest(Map<String, String> request) {
    this.request = request;
  }
}
