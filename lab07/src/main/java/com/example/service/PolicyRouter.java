package com.example.service;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import com.example.domain.InsurancePolicy;

@MessageEndpoint
public class PolicyRouter {

	@Router(inputChannel="ratedPolicyChannel")
	public String routePolicy(InsurancePolicy pol)  {
		
		if (pol.getStatus() == InsurancePolicy.Status.Approved) {
			return "goodPolicyChannel";
		}
		return "badPolicyChannel";
	}
}
