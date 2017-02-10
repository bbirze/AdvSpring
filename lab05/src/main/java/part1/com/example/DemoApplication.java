package part1.com.example;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
		
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Mode.OFF);
		ApplicationContext ctx = app.run(args);
		
		MessageChannel channel = ctx.getBean("shippingChannel", MessageChannel.class);
		Message<String> msg = MessageBuilder.withPayload("BB").build();
		
		channel.send(msg);            // trigger Activator to call sayHello method
	}
}
