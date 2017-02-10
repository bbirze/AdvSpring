package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.jdbc.JdbcMessageHandler;
import org.springframework.integration.jdbc.MessagePreparedStatementSetter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
public class JDBCOutbound {
	
	@Bean
	public DataSource dataSource()  {
		DataSource dSource = null;
		return dSource;
	}
	
	@Bean
	public MessageChannel goodStudentChannel() {
		return new DirectChannel();
	}
	
	@Bean
	@ServiceActivator(inputChannel = "goodStudentChannel")
	public MessageHandler approvePolicyActivator() {

		// create a JDBC outbound adapter
		JdbcMessageHandler jh = new JdbcMessageHandler(dataSource(),
						"Insert into STUDENT (name, gpa) VALUES(?, ?)");
		
		// Class to map message properties
		class MyPreparedStatementSetter implements MessagePreparedStatementSetter {
			@Override
			public void setValues(PreparedStatement ps, Message<?> requestMessage) throws SQLException {
				Student s = (Student)requestMessage.getPayload();
				ps.setString(1,  s.getName());
				ps.setString(2,  s.getGpa());
			}
		}
		// configure the JDBC outbound adapter
		jh.setPreparedStatementSetter(new MyPreparedStatementSetter());
		
		return jh;
	}


}
