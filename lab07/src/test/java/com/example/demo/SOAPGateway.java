package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ws.MarshallingWebServiceOutboundGateway;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@EnableIntegration
public class SOAPGateway {
	

	@Bean
	public MessageChannel processStudentChannel() {
		return new DirectChannel();
	}
	@Bean
	public MessageChannel outputChannel() {
		return new DirectChannel();
	}
	
	@Bean
	@ServiceActivator(inputChannel = "processStudentChannel")
	public MessageHandler wsOutboundGateway() {

		// create gateway to send msg to SOAP based web service,  wait for a response...
		MarshallingWebServiceOutboundGateway gw = new MarshallingWebServiceOutboundGateway(
						"http://example.com/demo/assignGrade", jaxb2Marshaller());
		
		// pass WebService response to output channel
		gw.setOutputChannelName("outputChannel");
		
		return gw;
	}
	
	@Bean                // helper, maps Student obj to XML using JAX-B
	public Jaxb2Marshaller jaxb2Marshaller()  {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		
		marshaller.setContextPath("edu.biguniversity");
		return marshaller;
	}
}
