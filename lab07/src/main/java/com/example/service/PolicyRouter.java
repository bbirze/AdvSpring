package com.example.service;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

import com.example.domain.InsurancePolicy;

@MessageEndpoint
public class PolicyRouter {

                                         // route based on content
	@Router(inputChannel="ratedPolicyChannel")
	public String routePolicy(InsurancePolicy pol)  {

		System.out.println("\nInput from ratedPolicyChannel, customer: " + pol.getCustomerNumber());

		if (pol.getStatus() == InsurancePolicy.Status.Approved) {
			System.out.println("\t Routing to goodPolicyChannel!");
			return "goodPolicyChannel";
		}
		System.out.println("\t Routing to badPolicyChannel!");
		return "badPolicyChannel";
	}
}
