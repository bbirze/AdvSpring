package com.example.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.messaging.handler.annotation.Header;

import com.example.domain.Account;

public class AccountServiceImpl implements AccountService {

	@Override
	public Account accrueInterest(Account a, 
			@Header(name="expirationDate") Long expiry) {
		System.out.println("Got Message with Expiry: " + new Date(expiry));
		System.out.println("   Current: " + a);
		
		BigDecimal balance = a.getBalance();
		balance = balance.add(balance.multiply(new BigDecimal(0.05)));
		a.setBalance(balance);
		
		System.out.println("   Updated: " + a);
		return a;
	}

}
