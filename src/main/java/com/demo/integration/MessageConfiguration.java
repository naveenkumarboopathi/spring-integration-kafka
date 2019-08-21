package com.demo.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author Naveenkumar Boopathi
 *
 */
@Configuration
public class MessageConfiguration {

	/**
	 * receive message from channel [from message gateway]
	 */
	@Bean
	public IntegrationFlow receivemessage() {
		return IntegrationFlows.from("test-channel")
				//.log()
				//.channel("log")
				.channel("send-to-kafka")
				.get();
	}

	/**
	 * send the channel message to kafka
	 */
	@Bean
	public IntegrationFlow sendToKafka(KafkaTemplate<?, ?> kafkaTemplate) {
		return IntegrationFlows.from("send-to-kafka")
				.log("send to kafka ")
				//.transform("payload.toString()")
				.handle(Kafka.outboundChannelAdapter(kafkaTemplate))
				.get();
	}

	/**
	 * receive the message from kafka and send to channel 
	 *  testTopic is the kafka [Topic]
	 */
	@Bean
	public IntegrationFlow receiveFromKafka(ConsumerFactory<?, ?> consumer) {
		return IntegrationFlows.from(Kafka.messageDrivenChannelAdapter(consumer,"testTopic"))
				.channel("log")
				.get();
	}

	/**
	 * receive the message and send to the class and method 
	 */
	@Bean
	public IntegrationFlow print() {
		return IntegrationFlows.from("log")
				.log("receive from kafka ")
				//	.transform("payload.toString()")
				//	.bridge()
				.handle("sendMessage","sendmsg")
				//.channel("send-to-kafka")
				.get();
	}

}
