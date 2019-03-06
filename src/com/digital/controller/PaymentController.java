package com.digital.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.model.Payment;
/**
 * @author Satyam Kumar
 *
 */
@RestController
@RequestMapping("/api/v0/payment")
public class PaymentController {

	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

	public ResponseEntity<Object> processTransaction(
			@RequestBody Payment paymentForm) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PostMapping(value = "pay")
	public ResponseEntity<String> pay() {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "success")
	public ResponseEntity<String> successPay(@RequestParam("paymentId") String paymentId,
			@RequestParam("PayerID") String payerId) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
