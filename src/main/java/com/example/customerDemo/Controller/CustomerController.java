package com.example.customerDemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerDemo.Model.Customer;
import com.example.customerDemo.Model.CustomerResponse;
import com.example.customerDemo.Repository.CustomerRepo;

@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	CustomerRepo customerRepo;

	@GetMapping(path="/",produces="application/json")
	public ResponseEntity<Iterable<Customer>> listCustomers() {
		Iterable<Customer> customers = customerRepo.findAll();
		return ResponseEntity.ok().body(customers);
	}

	@PostMapping(path="/add", produces="application/json")
	public ResponseEntity<CustomerResponse> addCustomer(@RequestBody Customer customer) {
		System.out.println(customer.toString());
		CustomerResponse response = new CustomerResponse();
		try {
			customerRepo.save(customer);
			response.setHttpStatus(HttpStatus.CREATED);
			response.setStatusCode(200);
			response.setMessage("Successfully Added");
		} catch (Exception e) {
			response.setHttpStatus(HttpStatus.CONFLICT);
			response.setStatusCode(409);
			response.setMessage("The entered custId is already present");
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<CustomerResponse>(response, HttpStatus.CONFLICT);
	}

	@PutMapping(path="/update", produces = "application/json")
	public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody Customer customer) {
		System.out.println(customer.toString());
		CustomerResponse resp = new CustomerResponse();
		try {
			 Customer customerForUpdate = customerRepo.findByCustId(customer.getCustId());
			customerForUpdate.setCustName(customer.getCustName());
			customerForUpdate.setPrime(customer.isPrime());
			customerForUpdate.setMobile(customer.getMobile());
			customerRepo.save(customerForUpdate);
			resp.setHttpStatus(HttpStatus.OK);
			resp.setStatusCode(200);
			resp.setMessage("Successfully Updated");
			return new ResponseEntity<CustomerResponse>(resp, HttpStatus.OK);
		} catch (Exception e) {
			resp.setHttpStatus(HttpStatus.NOT_FOUND);
			resp.setStatusCode(409);
			resp.setMessage("The Entered custId is Not Found to Update");
			return new ResponseEntity<CustomerResponse>(resp, HttpStatus.NOT_FOUND);
		}
		
	}

	@DeleteMapping(path="/delete",produces="application/json")
	public ResponseEntity<CustomerResponse> deleteCustomer(@RequestBody Customer customer) {
		System.out.println(customer.getCustId());
		CustomerResponse response = new CustomerResponse();
		Integer isDeleted = customerRepo.deleteByCustId(customer.getCustId());
		if (isDeleted == 1) {
			response.setHttpStatus(HttpStatus.OK);
			response.setStatusCode(200);
			response.setMessage("Deleted Successfully");
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
		} else {
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setMessage("The Entered custId Not Found to Delete");
			response.setStatusCode(404);
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
		}
	}
}
