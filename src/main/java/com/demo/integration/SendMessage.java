package com.demo.integration;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Naveenkumar Boopathi
 *
 */
@Component
@Slf4j
public class SendMessage {

	/**
	 * get message from the channel
	 */
	public void sendmsg(Message<String> msg) {
		String payload = msg.getPayload();
		log.info("Payload " + payload);
	}
	
}
