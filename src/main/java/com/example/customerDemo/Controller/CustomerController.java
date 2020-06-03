package com.example.customerDemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerResponse> addCustomer(@RequestBody Customer customer) {
		System.out.println(customer.toString());
		return customerService.addCustomer(customer);
	}

	@PutMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody Customer customer) {
		System.out.println(customer.toString());
		return customerService.updateCustomer(customer);
	}

	@DeleteMapping(path = "/delete", produces = "application/json")
	public ResponseEntity<CustomerResponse> deleteCustomer(@RequestBody Customer customer) {
		System.out.println(customer.getCustId());
		return customerService.deleteCustomer(customer);
	}
}
