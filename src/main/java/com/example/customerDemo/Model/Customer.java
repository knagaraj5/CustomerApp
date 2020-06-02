package com.example.customerDemo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;



@Entity
@Table
public class Customer {
	@Id
	@GeneratedValue
	private int id;
	private int custId;
	private String custName;
	@Value("false")
	private boolean isPrime;
	private int mobile;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int custId, String custName, boolean isPrime, int mobile) {
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

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", isPrime=" + isPrime + ", mobile=" + mobile
				+ "]";
	}
	
	

}
