package com.example.shipping;

import org.springframework.stereotype.Component;

@Component("shipping")
public class ShippingImpl implements Shipping {

	@Override
	public double calculateDomestic(String zip) {
		
		return 10.5;
	}

	@Override
	public double calculateInternational(String country) {
		
		return 40.99;
	}

}
