package com.example.customerDemo;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class CustomerAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void getCustomerTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 5555 + "/customer/4";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertFalse(result.getBody().isEmpty());
	}

	@Test
	public void getAllTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 5555 + "/customer/";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertFalse(result.getBody().isEmpty());
	}
	
//	@Test
	public void deleteTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 5555 + "/customer/4";
		URI uri = new URI(baseUrl);
		try {
		restTemplate.delete(uri);
		final String baseUrl1 = "http://localhost:" + 5555 + "/customer/4";
		URI uri1 = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri1, String.class);
		Assert.assertNotEquals(200, result.getStatusCodeValue());
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
