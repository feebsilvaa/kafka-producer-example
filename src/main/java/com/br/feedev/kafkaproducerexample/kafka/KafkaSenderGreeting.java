package com.br.feedev.kafkaproducerexample.kafka;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.br.feedev.kafkaproducerexample.config.Greetings;

@Service
public class KafkaSenderGreeting {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private KafkaTemplate<String, Greetings> greetingsTemplate;
	
	@Value("${kafka.topicName}")
	private String topicName;
	
	public void send(String message) {
	    kafkaTemplate.send(topicName, message);
	}

	public void sendGreeting(Greetings greeting) {
//		greeting.setTimeout(System.currentTimeMillis());
//		greetingsTemplate.send(topicName, greeting);
//
////		greeting.setTimeout(System.currentTimeMillis());
////		greeting.setPartition(0);
////		greetingsTemplate.send(topicName, greeting.getPartition(), UUID.randomUUID().toString(), greeting);
////
////		greeting.setTimeout(System.currentTimeMillis());
////		greeting.setPartition(2);
////		greetingsTemplate.send(topicName, greeting.getPartition(), UUID.randomUUID().toString(), greeting);
////
////		greeting.setTimeout(System.currentTimeMillis());
////		greeting.setPartition(1);
////		greetingsTemplate.send(topicName, greeting.getPartition(), UUID.randomUUID().toString(), greeting);
////
////		greeting.setTimeout(System.currentTimeMillis());
////		greeting.setPartition(3);
////		greetingsTemplate.send(topicName, greeting.getPartition(), UUID.randomUUID().toString(), greeting);
		greeting.setTimestamp(LocalDateTime.now());
		greeting.setPartition(0);
		
		long epochMilli = greeting.getTimestamp().plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		greetingsTemplate.send(topicName, greeting.getPartition(), epochMilli, UUID.randomUUID().toString(), greeting);
	}

}
