package part2.com.example;

// Notice No imports for Spring integration needed!
// don't have to know anything but HelloWorld interface!

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import part2.hello.HelloWorld;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
		
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Mode.OFF);
		ApplicationContext ctx = app.run(args);
		
		HelloWorld hw = ctx.getBean("hello", HelloWorld.class);
		String s = hw.sayHello("Sam");
		System.out.println(s);
				
//		MessageChannel channel = ctx.getBean("shippingChannel", MessageChannel.class);
//		Message<String> msg = MessageBuilder.withPayload("BB").build();
//		
//		channel.send(msg);            // trigger Activator to call sayHello method
	}
}
