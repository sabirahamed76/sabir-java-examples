package com.home.sabir.spring.jms.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	//Uncomment the below to consume the MQ messages 
	/*
	 * @JmsListener(destination = "sabir-jms-queue") public void
	 * consumeMessage(String message) {
	 * logger.info("Message received from activemq queue---"+message); }
	 */
}