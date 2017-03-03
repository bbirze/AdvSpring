package com.goliath;

import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.goliath.InsurancePolicy.Status;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL, parameterStyle = ParameterStyle.BARE)
public class PolicyService
{
	private static int threshold;

	static
	{
		Random r = new Random();
		threshold = (r.nextInt(10) + 1) * 10000;
		System.out.println("Rating threshold: " + threshold);
	}

	@WebMethod(operationName = "insurancePolicy")
	public @WebResult(name = "insurancePolicy", targetNamespace = "http://goliath.com/") InsurancePolicy 
		ratePolicy(@WebParam(targetNamespace = "http://goliath.com/") InsurancePolicy p)
	{
		System.out.println("\n Policy WebService got insurancePolicy: " + p.getCustomerNumber());
		if (p.getCoverageAmount() < threshold)
		{
			p.setStatus(Status.Approved);
			System.out.println("\t Approve insurancePolicy!");
		}
		else
		{
			p.setStatus(Status.Rejected);
			p.setRatingComment("\t Rejected: Coverage amount too high for applicant");
			System.out.println("\t Rejected insurancePolicy!");
		}

		return p;
	}

}
