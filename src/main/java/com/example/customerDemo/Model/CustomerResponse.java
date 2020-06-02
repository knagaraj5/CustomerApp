package com.example.customerDemo.Model;

import org.springframework.http.HttpStatus;

public class CustomerResponse {
	private int statusCode;
	private String message;
	private HttpStatus httpStatus;
	public CustomerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomerResponse(int statusCode, String message, HttpStatus httpStatus) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.httpStatus = httpStatus;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	

}
