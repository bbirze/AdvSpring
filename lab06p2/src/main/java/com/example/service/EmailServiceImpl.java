package com.example.service;

import com.example.domain.Account;

public class EmailServiceImpl implements EmailService {

	@Override
	public void sendEmail(Account act) {
		System.out.println();
		System.out.println("\t Emailing account: " + act.getCustomerNumber() + "\n");
	}

}
