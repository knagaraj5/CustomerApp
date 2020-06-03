package com.example.customerDemo;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.customerDemo.Model.Customer;
import org.junit.runners.MethodSorters;

@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CustomerAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void dPostTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 5555 + "/customer/";
		URI uri = new URI(baseUrl);
		Customer customer = new Customer(999, "TESTNAME", true, "987");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");
		headers.set("X-COM-LOCATION", "IN");

		HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
		try {
			ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		} catch (Exception e) {
		}
	}
	@Test
	public void cgetCustomerTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 5555 + "/customer/999";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void bgetAllTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 5555 + "/customer/";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertFalse(result.getBody().isEmpty());
	}



	@Test
	public void adeleteTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 5555 + "/customer/999";
		URI uri = new URI(baseUrl);
		try {
			restTemplate.delete(uri);
			final String baseUrl1 = "http://localhost:" + 5555 + "/customer/999";
			URI uri1 = new URI(baseUrl1);

			ResponseEntity<String> result = restTemplate.getForEntity(uri1, String.class);
			Assert.assertEquals(200, result.getStatusCodeValue());

		} catch (Exception e) {
		}
	}
}
