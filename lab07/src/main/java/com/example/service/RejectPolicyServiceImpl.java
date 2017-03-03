package com.example.service;

import com.example.domain.InsurancePolicy;

public class RejectPolicyServiceImpl implements RejectedPolicyService {

	@Override
	public void acceptPolicy(InsurancePolicy pol) {
		System.out.println();
		System.out.println("Rejected Policy for Customer: " + pol.getCustomerNumber() + "\n\t with Comment: "  + pol.getRatingComment());
		System.out.println("----------------------------------\n");
	}

}
