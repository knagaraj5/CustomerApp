package com.example.customerDemo.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.customerDemo.Model.Customer;
import com.example.customerDemo.Model.CustomerResponse;
import com.example.customerDemo.Repository.CustomerRepo;
import com.example.customerDemo.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepo customerRepo;

	@Override
	public Customer getCustomer(int custId) {
		System.out.println("In Impl" + custId);
		Customer customerData = customerRepo.findByCustId(custId);
		System.out.println("in impl result" + customerData);
		return customerData;
	}

	@Override
	public ResponseEntity<Iterable<Customer>> listCustomers() {
		Iterable<Customer> customers = customerRepo.findAll();
		return ResponseEntity.ok().body(customers);
	}

	@Override
	public ResponseEntity<CustomerResponse> addCustomer(Customer customer) {
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

	@Override
	public ResponseEntity<CustomerResponse> updateCustomer(Customer customer) {
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

	@Override
	public ResponseEntity<CustomerResponse> deleteCustomer(int custId) {
		CustomerResponse response = new CustomerResponse();
		Integer isDeleted = customerRepo.deleteByCustId(custId);
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
