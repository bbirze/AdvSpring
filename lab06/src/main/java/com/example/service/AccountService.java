package com.example.service;

import com.example.domain.Account;

public interface AccountService {
                       // expiry, time to live, will go in the msg header
	void accrueInterest(Account a, Long expiry);
	
}
