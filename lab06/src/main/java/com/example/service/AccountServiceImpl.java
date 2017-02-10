package com.example.service;

import java.math.BigDecimal;

import com.example.domain.Account;

public class AccountServiceImpl implements AccountService {

	@Override
	public void accrueInterest(Account a) {
		System.out.println("Current: " + a);
		
		BigDecimal balance = a.getBalance();
		balance = balance.add(balance.multiply(new BigDecimal(0.05)));
		a.setBalance(balance);
		
		System.out.println("Updated: " + a);
	}

}
