package com.example.customerDemo.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.customerDemo.Model.Customer;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, Integer>{

	Customer findBycustId(int custId);

	Customer deleteBycustId(Customer customer);

	@Transactional
	Integer deleteByCustId(int i);

	Customer findByCustId(int custId);

}
