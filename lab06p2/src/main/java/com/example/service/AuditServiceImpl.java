package com.example.service;

import java.math.BigDecimal;

import com.example.domain.Account;

public class AuditServiceImpl implements AuditService {
	
	@Override
	public void auditAccount(Account act) {
		BigDecimal balance = act.getBalance();
		System.out.println();
		System.out.println("\t Performing Audit for account #: " + act.getAccountNumber() + " Customer #: " + act.getCustomerNumber());
		
		if (balance.compareTo(new BigDecimal(0.0)) == -1)  {   // audit failed
			System.err.println("\t\t Audit Failed! \n" );
		}
		else {                                                 // audit Passed
			System.out.println("\t\t Audit Passed for account \n");
		}
	}

}
