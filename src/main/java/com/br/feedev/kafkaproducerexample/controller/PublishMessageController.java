package com.br.feedev.kafkaproducerexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RestController
@RequestMapping(path = "/publish")
public class PublishMessageController {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${kafka.topicName}")
	private String topicName;
	 
	@PostMapping
	public void sendMessage(@RequestBody BaseMessage msg) {
	    kafkaTemplate.send(topicName, msg.getMessage());
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@ToString
	static class BaseMessage {
		private String message;
	}
	
}
