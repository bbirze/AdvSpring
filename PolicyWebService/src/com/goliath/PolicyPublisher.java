package com.goliath;

import javax.xml.ws.Endpoint;

public class PolicyPublisher
{

	public static void main(String[] args)
	{
		Endpoint.publish("http://localhost:8085/ratePolicy", new PolicyService());
		System.out.println("Service running...");
	}

}
