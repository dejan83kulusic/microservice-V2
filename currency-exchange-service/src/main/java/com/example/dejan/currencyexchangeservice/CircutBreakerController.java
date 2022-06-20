package com.example.dejan.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircutBreakerController {
	private Logger logger=LoggerFactory.getLogger(CircutBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name="sample-api",fallbackMethod = "harcodedResponse")
	//@CircuitBreaker(name="default",fallbackMethod = "harcodedResponse")
	//@RateLimiter(name = "default")
	// 10s -> 1000 calls to sample api
	@Bulkhead(name = "default")
	public String simpleApi() {
		logger.info("Sample API call recives");
		//RestTemplate restTemplate = new RestTemplate();
		//String fooResourceUrl
		 // = "http://localhost:8080/some-dummy-url";
		//ResponseEntity<String> response
		//  = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
		return "Sample API";
		
	}
	public String harcodedResponse(Exception ex) {
		return "fallback-response";
	}
	
	
	
	
	

}
