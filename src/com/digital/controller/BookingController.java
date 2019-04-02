package com.digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.model.User;
import com.digital.service.BookingService;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v0/book")
@Slf4j
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@GetMapping(value = "/{userId}")
	public ResponseEntity<RestResponse<Object>> getUserDetail(
			@PathVariable(name = "userId", required = true) Long userId) {
		log.info("call getUserDetail {}", userId);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Fetch record Successfully");
		//User user = authService.getUser(userId);
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}
	
}
