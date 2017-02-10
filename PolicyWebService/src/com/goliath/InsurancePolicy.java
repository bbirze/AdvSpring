package com.goliath;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class InsurancePolicy implements Serializable
{
	private int policyNumber;
	private int coverageAmount;
	private int customerNumber;
	private String ratingComment;

	public enum Status
	{
		Unrated, Approved, Rejected;
	}

	private Status status;

	public int getPolicyNumber()
	{
		return policyNumber;
	}

	public void setPolicyNumber(int policyNumber)
	{
		this.policyNumber = policyNumber;
	}

	public int getCoverageAmount()
	{
		return coverageAmount;
	}

	public void setCoverageAmount(int coverageAmount)
	{
		this.coverageAmount = coverageAmount;
	}

	public int getCustomerNumber()
	{
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber)
	{
		this.customerNumber = customerNumber;
	}

	public Status getStatus()
	{
		return status;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

	public String getRatingComment()
	{
		return ratingComment;
	}

	public void setRatingComment(String ratingComment)
	{
		this.ratingComment = ratingComment;
	}

	@Override
	public String toString()
	{
		return "InsurancePolicy [policyNumber=" + policyNumber
				+ ", coverageAmount=" + coverageAmount + ", customerNumber="
				+ customerNumber + ", ratingComment=" + ratingComment
				+ ", status=" + status + "]";
	}
}
