package com.example.demo.factory;

import com.example.demo.mq.KafkaResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class KafkaResponseFactory {

  public static KafkaResponse make(Object data, String error) {
    KafkaResponse kafkaResponse = new KafkaResponse();
    kafkaResponse.setData(StringUtils.isBlank(error) ? data : "");
    kafkaResponse.setError(error);
    return kafkaResponse;
  }
}
