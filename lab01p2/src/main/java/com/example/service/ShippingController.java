package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shipping.Shipping;

@Controller
@RequestMapping("/shipping")
public class ShippingController  {

	@Autowired
	private Shipping shipping;
	
	@RequestMapping(method = RequestMethod.GET)
	public String setup()  {
		return "shipping";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@RequestParam String zip, Model model)  {
		double cost = shipping.calculateDomestic(zip);
		
		model.addAttribute("zip", zip);
		model.addAttribute("shipping", cost);
		return "shippingresult";
	}
}
