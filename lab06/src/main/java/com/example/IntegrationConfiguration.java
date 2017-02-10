package com.example;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.example.service.AccountService;
import com.example.service.AccountServiceImpl;

@Configuration
@EnableIntegration
public class IntegrationConfiguration {

	@Bean
	public AccountService accountService() {
		return new AccountServiceImpl();
	}

	@Bean
	public MessageChannel accountChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "accountChannel")
	public MessageHandler activator() {
	                	// Activator configured with Hello obj and sayHello method
		MethodInvokingMessageHandler mh = 
				new MethodInvokingMessageHandler(accountService(), "accrueInterest");

		return mh;

	}
}
