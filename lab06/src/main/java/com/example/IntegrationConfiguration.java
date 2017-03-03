package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.support.PeriodicTrigger;

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
//		return new DirectChannel();    // sync communication
		return new QueueChannel(10);   // async communication
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller() {
		PollerMetadata pollerMetadata = new PollerMetadata();
		pollerMetadata.setTrigger(new PeriodicTrigger(100));
		return pollerMetadata;
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
