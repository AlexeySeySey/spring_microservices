package com.example.demo.mq;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mq.response.KafkaBaseResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.demo.constant.Error;

@Component
public class KafkaProducer {

	@Value("${kafka.request.timeout}")
	private String kafkaRequestTimeout;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducer request(String topic, String request) throws Exception {
		kafkaTemplate.send(topic, request);
		return this;
	}
	
	public Map<String, Object> withResponse(KafkaBaseResponse baseResponse) throws Exception {
		
		String response = null;
		
		var start = System.currentTimeMillis();
		
		while (true) {

			response = baseResponse.getData();
			
			if (response != null) {
				break;
			}
			
			if (start < (System.currentTimeMillis() - Integer.valueOf(kafkaRequestTimeout) * 1000)) {
				throw new Exception(Error.KAFKA_TIMEOUT.get());
			}
		}

		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		Map<String, Object> parsedResponse = new Gson().fromJson(response, type);
		
		return parsedResponse;
	}
}