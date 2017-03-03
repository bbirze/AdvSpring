package com.example.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.example.shipping.Shipping;

@Controller           // program starting point because implements CommandLineRunner
public class ShippingController implements CommandLineRunner {

	private Logger log = Logger.getLogger(ShippingController.class);
	
	@Autowired
	private Shipping shipping;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("\n\t ==============================================");
		log.info("Domestic Shipping Rate: " + shipping.calculateDomestic("75068"));
		log.info("International Shipping Rate: " + shipping.calculateInternational("France") 
				+ "\n\t ==============================================");

	}

}
