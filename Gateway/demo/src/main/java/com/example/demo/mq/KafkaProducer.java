package com.example.demo.mq;

import com.example.demo.constant.Error;
import com.example.demo.mq.request.KafkaRequest;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

  @Value("${kafka.request.timeout}")
  private String kafkaRequestTimeout;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  private final KafkaResponseContainer kafkaResponseContainer;

  @Autowired
  public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate,
      KafkaResponseContainer kafkaResponseContainer) {
    this.kafkaTemplate = kafkaTemplate;
    this.kafkaResponseContainer = kafkaResponseContainer;
  }

  public String request(String topic, Object request) throws Exception {
    return request(topic, request, false);
  }

  public String request(String topic, Object request, boolean hasResponse) throws Exception {

    String token = UUID.randomUUID().toString();
    kafkaResponseContainer.addResponse(token, "");

    KafkaRequest kafkaRequest = new KafkaRequest();
    kafkaRequest.setResponseToken(token);
    kafkaRequest.setRequest(request);

    kafkaTemplate.send(topic, kafkaRequest);

    if (Boolean.FALSE.equals(hasResponse)) {
      kafkaResponseContainer.removeResponse(token);
      return null;
    }

    String response = "";
    var start = System.currentTimeMillis();
    while (true) {
      response = kafkaResponseContainer.getResponse(token);
      if (StringUtils.isNotBlank(response)) {
        kafkaResponseContainer.removeResponse(token);
        break;
      }
      if (start < (System.currentTimeMillis() - Integer.parseInt(kafkaRequestTimeout) * 1000L)) {
        kafkaResponseContainer.removeResponse(token);
        throw new Exception(Error.KAFKA_TIMEOUT.get());
      }
    }
    return response;
  }
}