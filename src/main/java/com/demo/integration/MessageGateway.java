package com.demo.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

/**
 * @author Naveenkumar Boopathi
 *
 */
@MessagingGateway
public interface MessageGateway {

	/**
	 * pass the messgae to the request channel
	 */
	@Gateway(requestChannel ="test-channel")
	public void sendMessage(Message<String> msg);
	
}
