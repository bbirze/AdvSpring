package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.messaging.SendPurchaseOrder;

@Controller
@RequestMapping("/sendpo")
public class PoController {
	
	@Autowired
	private SendPurchaseOrder sender;
	
	@RequestMapping(method=RequestMethod.GET)
	public String setup()  {
		return "po";               // view po.html
	}

	@RequestMapping(method=RequestMethod.POST)
	public String submitForm(@RequestParam int number, double amount, Model model)  {
		sender.sendPO(number, amount);
		return "sendresult";       // view sentresult.html
	}
	
	
}
