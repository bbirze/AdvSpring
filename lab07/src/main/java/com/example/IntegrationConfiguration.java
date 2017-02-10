package com.example;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.handler.MethodInvokingMessageHandler;
import org.springframework.integration.jdbc.JdbcMessageHandler;
import org.springframework.integration.jdbc.MessagePreparedStatementSetter;
import org.springframework.integration.ws.MarshallingWebServiceOutboundGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.example.domain.InsurancePolicy;
import com.example.service.RejectPolicyServiceImpl;
import com.example.service.RejectedPolicyService;

@Configuration
@EnableIntegration
@IntegrationComponentScan("com.example.service")
public class IntegrationConfiguration {

	@Bean
	public MessageChannel unratedPoliciesChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel unratedPolicyChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel ratedPolicyChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel goodPolicyChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel badPolicyChannel() {
		return new DirectChannel();
	}

	// Beans to send Policy to Rating Web Service 
	// =========================================
	@Bean // helper, maps InsurancePolicy obj to XML using JAX-B
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

		marshaller.setContextPath("com.example.domain");
		return marshaller;
	}

	@Bean // Gateway to Rating WebService
	@ServiceActivator(inputChannel = "unratedPolicyChannel")
	public MessageHandler wsOutboundGateway() {

		MarshallingWebServiceOutboundGateway gw = new MarshallingWebServiceOutboundGateway(
				"http://localhost:8085/ratePolicy", jaxb2Marshaller());
		gw.setOutputChannelName("ratedPolicyChannel");
		return gw;
	}

	// Beans to deal with Rejected Policies 
	// =========================================
	@Bean
	public RejectedPolicyService rejectedPolicyService() {
		return new RejectPolicyServiceImpl();
	}

	@Bean                               // Rejected Policy Service Activator
	@ServiceActivator(inputChannel = "badPolicyChannel")
	public MessageHandler rejectedPolicyActivator() {

		MethodInvokingMessageHandler mh = new MethodInvokingMessageHandler(rejectedPolicyService(), "acceptPolicy");

		return mh;
	}

	// Beans to put Approved Policies in Database
	// =========================================
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dsb = DataSourceBuilder.create();
		dsb.url("jdbc:hsqldb:hsql://localhost/");
		dsb.driverClassName("org.hsqldb.jdbcDriver");
		dsb.username("SA");
		dsb.password("");

		return dsb.build();
	}

	@Bean                          // Rejected Policy Service Activator
	@ServiceActivator(inputChannel = "goodPolicyChannel")
	public MessageHandler appovedPolicyActivator() {

		JdbcMessageHandler jh = new JdbcMessageHandler(dataSource(),
				"INSERT INTO policy (amount,customerNumber,status) VALUES(?, ?, ?)");
		
		class MyPreparedStatementSetter implements MessagePreparedStatementSetter {

			@Override
			public void setValues(PreparedStatement ps, Message<?> requestMessage) throws SQLException {
				InsurancePolicy ip = (InsurancePolicy) requestMessage.getPayload();
				ps.setDouble(1,  ip.getCoverageAmount());
				ps.setInt(2,  ip.getCustomerNumber());
				ps.setString(3,  ip.getStatus().name());
			}
		}
		jh.setPreparedStatementSetter(new MyPreparedStatementSetter());
		return jh;
	}

}