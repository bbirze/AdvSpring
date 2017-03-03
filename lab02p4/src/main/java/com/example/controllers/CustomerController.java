package com.example.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.dao.CustomerDao;
import com.example.domain.Customer;

@Controller
public class CustomerController {

	private Logger log = Logger.getLogger(CustomerController.class);
	
	@SuppressWarnings("serial")
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	private class ResourceNotFoundException extends RuntimeException {
	}

	private CustomerDao dao;

	@Autowired
	public CustomerController(CustomerDao dao) {
		log.info("Constructed with dao: " + dao);
		this.dao = dao;
	}
	
	// REST MVC RequestMapping path on method, 
	//             represents a URI
	//   @ResponseBody: Only Rest returns an object which needs marchalling
	//
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public @ResponseBody 
	List<Customer> getCustomers() {

		List<Customer> custList = dao.allCustomers();
		log.info("Got customers: " + custList);
		return custList;
	}

}
