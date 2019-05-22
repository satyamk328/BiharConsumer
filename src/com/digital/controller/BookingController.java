package com.digital.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PutMapping(value = "/{mobileNumber}/{ticketNumber}")
	public ResponseEntity<RestResponse<Object>> cancelTicket(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) Long ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Printed ticket Successfully");
		// TODO cancel code
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}

	@GetMapping(value = "print/{mobileNumber}/{ticketNumber}")
	public ResponseEntity<RestResponse<Object>> getTicketInfo(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) Long ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Printed ticket Successfully");
		// TODO print code
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}

	@GetMapping(value = "/{mobileNumber}/{ticketNumber}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> ticketPDF(
			@PathVariable(name = "mobileNumber", required = true) Long mobileNumber,
			@PathVariable(name = "ticketNumber", required = true) Long ticketNumber) {
		log.info("call print {},{}", mobileNumber, ticketNumber);
		// TODO PDF code
        ByteArrayInputStream bis = bookingService.citiesReport();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE));
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, HttpHeaders.CONTENT_TYPE);
		headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
		headers.add(HttpHeaders.PRAGMA, "no-cache");
		headers.add(HttpHeaders.EXPIRES, "0");
		
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=123.pdf");
		//headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
		
		//headers.setContentLength(bis.);
		return new ResponseEntity<>(new InputStreamResource(bis), headers, HttpStatus.OK);
	}

	

}
