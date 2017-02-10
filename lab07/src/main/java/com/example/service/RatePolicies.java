package com.example.service;

import java.util.Collection;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.example.domain.InsurancePolicy;

@MessagingGateway
public interface RatePolicies {
	
	@Gateway(requestChannel="unratedPoliciesChannel")
	void rate(Collection<InsurancePolicy> policies);
}
