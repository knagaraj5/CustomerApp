package com.example.customerDemo.Validation;

import com.example.customerDemo.Model.Customer;
import com.example.customerDemo.Model.CustomerResponse;

public class InputValidator {
	public String validate(Customer customer) {
		String errorMessage="";
		if(customer.getCustId()== 0) {
			errorMessage = errorMessage.concat("custId is Missing");
		}
		if(customer.getCustName()== null) {
			errorMessage = errorMessage.concat("custName is Missing");
		}
		if(customer.getMobile()== null) {
			errorMessage = errorMessage.concat("mobile is Missing");
		}
		return errorMessage;
	}

}
