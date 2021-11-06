package com.example.demo.mq;

import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mq.response.KafkaBaseResponse;

@Component
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducer request(String topic, String request) throws Exception {
		kafkaTemplate.send(topic, request);
		return this;
	}
	
	public String withResponse(KafkaBaseResponse baseResponse) {
		
		String response = null;

		while (true) {

			response = baseResponse.getData();

			if (response != null) {
				break;
			}
		}

		return response;
	}
}