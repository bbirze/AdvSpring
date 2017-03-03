package com.example.service;

import java.util.Collection;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.example.domain.InsurancePolicy;

//  Message Gateway: encapsulates the application 
//  so clients don't need to understand messaging.
//
//  No implementation needed
//     Spring will create a proxy for us
//
@MessagingGateway
public interface RatePolicies {
	
	@Gateway(requestChannel="unratedPoliciesChannel")
	void rate(Collection<InsurancePolicy> policies);
}
