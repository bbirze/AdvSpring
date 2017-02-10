package part2.com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import part2.hello.HelloWorld;
import part2.hello.HelloWorldImpl;

@Configuration
@EnableIntegration
@IntegrationComponentScan("part2.hello")
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
		                     // Activator supports return from sayHello method
		ServiceActivatingHandler mh = 
				new ServiceActivatingHandler(hello(), "sayHello");
		
//		MethodInvokingMessageHandler mh = 
//				new MethodInvokingMessageHandler(hello(), "sayHello");
		
		return mh;
	}
	
}
