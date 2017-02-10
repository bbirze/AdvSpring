package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.CustomerDao;
import com.example.domain.Customer;

@Controller
@RequestMapping("/customers")       // MVC RequestMapping Path on Class
public class CustomerController {

	private CustomerDao dao;

	@Autowired
	public CustomerController(CustomerDao dao) {
		this.dao = dao;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String allCustomers(Model model) {
		List<Customer> custList = dao.allCustomers();
		if (custList != null) {
			model.addAttribute("customers", custList);
		}
		return "customers";
	}
}
