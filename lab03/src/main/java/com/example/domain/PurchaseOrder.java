package com.example.domain;

import java.io.Serializable;
import java.util.Date;

public class PurchaseOrder implements Serializable {

	private int poNumber;
	private Date poDate;
	private double amount;
	public int getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(int poNumber) {
		this.poNumber = poNumber;
	}
	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [poNumber=" + poNumber + ", poDate=" + poDate + ", amount=" + amount + "]";
	}
	
	
}
