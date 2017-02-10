package part2.hello;

import org.springframework.integration.annotation.MessagingGateway;

//configure I/F as a Spring Integraion Gateway
//   Spring will create a HelloWorld proxy to use at runtime

@MessagingGateway(defaultRequestChannel="shippingChannel")
public interface  HelloWorld {

	String sayHello(String name);
}
