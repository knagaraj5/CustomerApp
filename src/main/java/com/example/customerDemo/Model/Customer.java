package com.example.customerDemo.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
	private int custId;
	private String custName;
	private boolean isPrime;
	private String mobile;
	
	public Customer() {
		super();
	}

	public Customer(int custId, String custName, boolean isPrime, String mobile) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.isPrime = isPrime;
		this.mobile = mobile;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public boolean isPrime() {
		return isPrime;
	}

	public void setPrime(boolean isPrime) {
		this.isPrime = isPrime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", isPrime=" + isPrime + ", mobile=" + mobile
				+ "]";
	}
	
	

}
