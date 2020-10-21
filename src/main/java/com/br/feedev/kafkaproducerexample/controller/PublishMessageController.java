package com.br.feedev.kafkaproducerexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.feedev.kafkaproducerexample.config.Greetings;
import com.br.feedev.kafkaproducerexample.kafka.KafkaSenderGreeting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RestController
@RequestMapping(path = "/publish")
public class PublishMessageController {
	
	@Autowired
	private KafkaSenderGreeting kafkaSenderGreeting;
	 
	@PostMapping
	public void sendMessage(@RequestBody BaseMessage msg) {
		this.kafkaSenderGreeting.send(msg.getMessage());
	}
	 
	@PostMapping(path = "/greeting")
	public void sendGreetingsMessage(@RequestBody Greetings greeting) throws InterruptedException {
		this.kafkaSenderGreeting.sendGreeting(greeting);
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@ToString
	static class BaseMessage {
		private String message;
	}
	
}
