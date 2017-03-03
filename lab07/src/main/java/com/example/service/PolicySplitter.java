package com.example.service;

import java.util.List;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Splitter;

import com.example.domain.InsurancePolicy;

@MessageEndpoint
public class PolicySplitter {

	@Splitter(inputChannel = "unratedPoliciesChannel",
		      outputChannel = "unratedPolicyChannel")
	public List<InsurancePolicy> split(List<InsurancePolicy> policies)  {
		System.out.println("\n Input List<InsurancePolicy> to split \n" + policies);

		return policies;
	}
}
