package com.example;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import com.example.domain.Account;

@SpringBootApplication
public class DemoApplication {
	private static Logger log = Logger.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		// get rid of Spring Boot Banner
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Mode.OFF);
		ApplicationContext ctx = app.run(args);
		
		MessageChannel channel = ctx.getBean("accountChannel", MessageChannel.class);

		Account acct = new Account();
		acct.setAccountNumber(12345);
		acct.setCustomerNumber(34576);
		acct.setBalance(new BigDecimal(2345));
		acct.setCreationDate(new Date());

		System.out.println();
		System.out.println("Main use Account: " + acct);

		Message<Account> message = MessageBuilder.withPayload(acct).build();
		channel.send(message);
		
		acct.setAccountNumber(98765);
		acct.setCustomerNumber(98765);
		acct.setBalance(new BigDecimal(-10.44));
		System.out.println("Main use Account: " + acct);

		message = MessageBuilder.withPayload(acct).build();
		channel.send(message);
	}
}
