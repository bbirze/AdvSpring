package com.example;

import java.util.ArrayList;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.domain.InsurancePolicy;
import com.example.service.RatePolicies;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Mode.OFF);
		ApplicationContext ctx = app.run(args);
		
		RatePolicies rater = (RatePolicies)ctx.getBean(RatePolicies.class);
		
		ArrayList<InsurancePolicy> policies = new ArrayList<>();
		InsurancePolicy pol = new InsurancePolicy();
		pol.setCustomerNumber(1234);
		pol.setCoverageAmount(1000);
		pol.setStatus(InsurancePolicy.Status.Unrated);
		policies.add(pol);
		
		pol = new InsurancePolicy();
		pol.setCustomerNumber(9876);
		pol.setCoverageAmount(100000);
		pol.setStatus(InsurancePolicy.Status.Unrated);
		policies.add(pol);
		
		rater.rate(policies);
	}
}
