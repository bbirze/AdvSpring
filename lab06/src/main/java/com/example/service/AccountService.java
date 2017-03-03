package com.example.service;

import com.example.domain.Account;

public interface AccountService {

	void accrueInterest(Account a, Long expiry);
	
}
