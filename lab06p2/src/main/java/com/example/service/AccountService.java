package com.example.service;

import com.example.domain.Account;

public interface AccountService {

	Account accrueInterest(Account a, Long expiry);
	
}
