package com.example.domain;

public class Account {

	private int    accountID;
	private double balance;
	private double interestRate;
	private int    customerID;
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", balance=" + balance + ", interestRate=" + interestRate
				+ ", customerID=" + customerID + "]";
	}
}
