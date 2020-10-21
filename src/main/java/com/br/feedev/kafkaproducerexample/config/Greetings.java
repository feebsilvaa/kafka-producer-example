package com.br.feedev.kafkaproducerexample.config;

import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Greetings {

	private String msg;
	private String name;
	private Integer partition;
	private LocalDateTime timestamp;
	private long timestampInMillis;
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
		this.timestampInMillis = timestamp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
	
}
