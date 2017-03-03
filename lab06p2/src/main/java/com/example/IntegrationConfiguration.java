package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.example.service.AccountService;
import com.example.service.AccountServiceImpl;
import com.example.service.AuditService;
import com.example.service.AuditServiceImpl;
import com.example.service.EmailService;
import com.example.service.EmailServiceImpl;

@Configuration
@EnableIntegration
public class IntegrationConfiguration {

	//  Service Configurations
	// ======================================
	@Bean
	public AccountService accountService() {
		return new AccountServiceImpl();
	}

	@Bean
	public AuditService auditService() {
		return new AuditServiceImpl();
	}

	@Bean
	public EmailService emailService() {
		return new EmailServiceImpl();
	}

	//  Channel Configurations
	// ======================================
	@Bean
	public MessageChannel accountChannel() {
	//	return new DirectChannel();
		return new QueueChannel(10);
	}

	@Bean(name = PollerMetadata.DEFAULT_POLLER)
	public PollerMetadata defaultPoller() {
		PollerMetadata pollerMetadata = new PollerMetadata();
		pollerMetadata.setTrigger(new PeriodicTrigger(100));
		return pollerMetadata;
	}

	@Bean
	public MessageChannel changedAccountChannel() {
		return new PublishSubscribeChannel();
	}
	
	//  Service Activator Configurations
	// ======================================
	@Bean
	@ServiceActivator(inputChannel = "accountChannel")
	public MessageHandler activator() {
//		MethodInvokingMessageHandler mh = 
//				new MethodInvokingMessageHandler(accountService(), "accrueInterest");
		
    	// Activator configured with accountService obj and accrueInterest method
		//     accrueInterest returns an Account object
		//     which we place on the changedAccountChannel
		ServiceActivatingHandler mh = 
				new ServiceActivatingHandler(accountService(), "accrueInterest");
		
		mh.setOutputChannel(changedAccountChannel());
		return mh;
	}
	
	
	@Bean
	@ServiceActivator(inputChannel = "changedAccountChannel")
	public MessageHandler auditActivator() {
	                	// Activator configured with Audit Server obj and auditAccount method
		                // using MethodInvokingMessageHandler, does not accept method return value
		ServiceActivatingHandler mh = 
				new ServiceActivatingHandler(auditService(), "auditAccount");
		
		return mh;
	}

	@Bean
	@ServiceActivator(inputChannel = "changedAccountChannel")
	public MessageHandler emailActivator() {
	                	// Activator configured with emailService obj and sendEmail method
                        // using ServiceActivatingHandler, does accept method return value
		ServiceActivatingHandler mh = 
				new ServiceActivatingHandler(emailService(), "sendEmail");
		
		return mh;
	}

}
