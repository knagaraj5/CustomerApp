package com.example.customerDemo.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Customer getCustomer(int custId) {
		Customer customerData = customerRepo.findByCustId(custId);
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
		Boolean isPresent = customerRepo.existsByCustId(customer.getCustId());
		if (isPresent == true) {
			response.setHttpStatus(HttpStatus.CONFLICT);
			response.setStatusCode(409);
			response.setMessage("The entered custId is already present");
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.CONFLICT);
		}
		try {
			customerRepo.save(customer);
			response.setHttpStatus(HttpStatus.CREATED);
			response.setStatusCode(200);
			response.setMessage("Successfully Added");
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.setHttpStatus(HttpStatus.CONFLICT);
			response.setStatusCode(409);
			response.setMessage(e.getMessage());
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<CustomerResponse> updateCustomer(Customer customer) {
		CustomerResponse response = new CustomerResponse();
		Query isExistQuery = new Query();
		Query updateQuery = new Query();
		isExistQuery.addCriteria(Criteria.where("custId").is(customer.getCustId()));
		Customer isPresent = mongoTemplate.findOne(isExistQuery, Customer.class);
		if (isPresent == null) {
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setStatusCode(409);
			response.setMessage("The Entered custId is Not Found to Update");
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.NOT_FOUND);
		}
		try {
			updateQuery.addCriteria(Criteria.where("custId").is(customer.getCustId()));
			updateQuery.fields().include("custId");
			Update update = new Update();
			update.set("custName", customer.getCustName());
			update.set("mobile", customer.getMobile());
			mongoTemplate.updateFirst(updateQuery, update, Customer.class);
			response.setHttpStatus(HttpStatus.OK);
			response.setStatusCode(200);
			response.setMessage("Successfully Updated");
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.setHttpStatus(HttpStatus.NOT_FOUND);
			response.setStatusCode(409);
			response.setMessage(e.getMessage());
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.NOT_FOUND);
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
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.NOT_FOUND);
		}
	}

}
