package com.digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.model.TicketVO;
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

	@GetMapping(value = "/{mobileNumber}/{ticketNumber}")
	public ResponseEntity<RestResponse<Object>> cancelTicket(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) Long ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Printed ticket Successfully");
		// TODO cancel code
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}

	@GetMapping(value = "/{mobileNumber}/{ticketNumber}")
	public ResponseEntity<RestResponse<Object>> printTicket(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) Long ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Printed ticket Successfully");
		// TODO print code
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}

	@GetMapping(value = "/{mobileNumber}/{ticketNumber}")
	public ResponseEntity<RestResponse<Object>> ticketPDF(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) Long ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Generated pdf ticket Successfully");
		// TODO PDF code
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<RestResponse<Object>> bookTicket(@RequestBody(required = true) TicketVO ticketVO) {
		log.info("call print {},{}");
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Generated pdf ticket Successfully");
		// TODO PDF code
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}

}
