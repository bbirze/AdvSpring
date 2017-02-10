package com.example.domain;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

	public InsurancePolicy createInsurancePolicy() {
		return new InsurancePolicy();
	}
}
