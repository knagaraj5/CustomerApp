package com.example.customerDemo.Service;

import org.springframework.http.ResponseEntity;
import com.example.customerDemo.Model.Customer;
import com.example.customerDemo.Model.CustomerResponse;

public interface CustomerService {
	public Customer getCustomer(int custId);
	public ResponseEntity<Iterable<Customer>> listCustomers();
	public ResponseEntity<CustomerResponse> addCustomer(Customer customer);
	public ResponseEntity<CustomerResponse> updateCustomer(Customer customer);
	public ResponseEntity<CustomerResponse> deleteCustomer(Customer customer);

}
