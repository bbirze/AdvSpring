package com.example;

import java.math.BigDecimal;
import java.util.Calendar;
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
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, 7);
		
		Account acct1 = new Account();
		acct1.setAccountNumber(12345);
		acct1.setCustomerNumber(34576);
		acct1.setBalance(new BigDecimal(2345));
		acct1.setCreationDate(new Date());

		System.out.println();
		System.out.println("Main use 1st Account: " + acct1);

		Message<Account> message = 
				MessageBuilder.withPayload(acct1)
				.setExpirationDate(now.getTime())
				.build();
		channel.send(message);
		System.out.println("Main 1st account sent! ");
		
		Account acct2 = new Account();
		acct2.setAccountNumber(98765);
		acct2.setCustomerNumber(98765);
		acct2.setBalance(new BigDecimal(-10.44));
		System.out.println("Main use 2nd Account: " + acct2);

		message = 
				MessageBuilder.withPayload(acct2)
				.setExpirationDate(now.getTime())
				.build();
		channel.send(message);
		System.out.println("Main 2nd account sent! ");
	}
}
