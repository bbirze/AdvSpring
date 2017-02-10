package com.example.service;

import java.math.BigDecimal;

import com.example.domain.Account;

public class AccountServiceImpl implements AccountService {

	@Override
	public Account accrueInterest(Account a) {
		System.out.println();
		System.out.println("\tCurrent: " + a.getAccountNumber() + " Balance: " + a.getBalance());
		
		BigDecimal balance = a.getBalance();
		balance = balance.add(balance.multiply(new BigDecimal(0.05)));
		a.setBalance(balance);
		
		System.out.println("\tUpdated: " + a.getAccountNumber() + " Balance: " + a.getBalance() + "\n");
		return a;
	}

}
