package com.demo.controller;

import javax.annotation.Resource;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.integration.MessageGateway;

/**
 * @author Naveenkumar Boopathi
 *
 */
@RestController
public class MessageController {

	@Resource
	private MessageGateway messageGateway;
	
	/**
	 * get the payload from request and pass to the message gateway and kafka channel also mentioned here 
	 */
	@GetMapping("/sendMessage")
	public void sendMessage(@RequestParam String payload) {
		Message<String> msg = MessageBuilder
					.withPayload(payload)
					.setHeader("sender", "Naveen")
					.setHeader(KafkaHeaders.TOPIC, "testTopic")
					.build();
		messageGateway.sendMessage(msg);
	}
}
