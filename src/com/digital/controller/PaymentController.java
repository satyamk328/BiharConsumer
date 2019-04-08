package com.digital.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
/**
 * @author Satyam Kumar
 *
 */
@Api(value="payment")
@RestController
@RequestMapping("/api/v0/payment")
@Slf4j
public class PaymentController {

	@PostMapping(value = "pay")
	public ResponseEntity<String> pay() {
		log.info("");
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@GetMapping(value = "success")
	public ResponseEntity<String> successPay(@RequestParam("paymentId") String paymentId,
			@RequestParam("PayerID") String payerId) {
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
