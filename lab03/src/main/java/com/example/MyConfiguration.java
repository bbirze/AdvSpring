package com.example;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.example.messaging.ReceivePurchaseOrder;

@Configuration
public class MyConfiguration {
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Bean
	public DefaultMessageListenerContainer messageListener()  {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		
		container.setConnectionFactory(connectionFactory);
		container.setDestinationName("poQueue");
		container.setMessageListener(new ReceivePurchaseOrder());
		
		return container;
	}

}