package com.example.shipping;

public interface Shipping {

	double calculateDomestic(String zip);
	
	double calculateInternational(String country);
}
