package part1.com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import part1.hello.HelloWorld;
import part1.hello.HelloWorldImpl;

@Configuration
@EnableIntegration
public class IntegrationConfiguration {
	
	@Bean
	public HelloWorld hello() {
		return new HelloWorldImpl();
	}
	
	@Bean
	public MessageChannel shippingChannel()  {
		return new DirectChannel();
	}

	@Bean                     // set Activator on shippingChannel
	@ServiceActivator(inputChannel="shippingChannel")
	public MessageHandler activator()  {
		                     // Activator configured with Hello obj and sayHello method
		MethodInvokingMessageHandler mh = 
				new MethodInvokingMessageHandler(hello(), "sayHello");
		
		return mh;
	}
	
}
