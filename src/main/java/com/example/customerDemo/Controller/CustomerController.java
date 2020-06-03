package com.example.customerDemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerDemo.Model.Customer;
import com.example.customerDemo.Model.CustomerResponse;
import com.example.customerDemo.Repository.CustomerRepo;
import com.example.customerDemo.Service.CustomerService;
import com.example.customerDemo.Validation.InputValidator;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	CustomerService customerService;

	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<Iterable<Customer>> listCustomers() {
		return customerService.listCustomers();
	}

	@GetMapping(path = "/{custId}", produces = "application/json")
	public Customer getCustomer(@PathVariable int custId) {	
		System.out.println("In Controller"+custId);
		return customerService.getCustomer(custId);
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerResponse> addCustomer(@RequestBody Customer customer) {
		System.out.println(customer.toString());
		InputValidator validator = new InputValidator();
		CustomerResponse response = new CustomerResponse();
		String errorMessage=validator.validate(customer);
		System.out.println(errorMessage);
		if(errorMessage!="") {
			response.setStatusCode(400);
			response.setMessage(errorMessage);
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.BAD_REQUEST);
		}
			
		return customerService.addCustomer(customer);
	}

	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody Customer customer) {
		System.out.println(customer.toString());
		CustomerResponse response = new CustomerResponse();
		InputValidator validator = new InputValidator();
		String errorMessage=validator.validate(customer);
		System.out.println(errorMessage);
		if(errorMessage!="") {
			response.setStatusCode(400);
			response.setMessage(errorMessage);
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.BAD_REQUEST);
		}
		return customerService.updateCustomer(customer);
	}

	@DeleteMapping(path = "/{custId}", produces = "application/json")
	public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable int custId) {
		System.out.println(custId);
		return customerService.deleteCustomer(custId);
	}
}
