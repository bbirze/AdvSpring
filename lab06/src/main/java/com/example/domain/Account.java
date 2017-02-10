package com.example.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Account implements Serializable {

	private int accountNumber;
	private Date creationDate;
	private BigDecimal balance;
	private int customerNumber;
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public int getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", creationDate=" + creationDate + ", balance=" + balance
				+ ", customerNumber=" + customerNumber + "]";
	}
	 
	
}
