package com.digital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;
import com.digital.user.model.Contact;
import com.digital.user.service.ContactUsService;
import com.digital.utils.GlobalConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v0/contactus")
@Slf4j
public class ContactUsController {

	@Autowired
	private ContactUsService contactService;
	
	@GetMapping("")
	public ResponseEntity<RestResponse<List<Contact>>> getAllComplain() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<Contact> contacts = contactService.getAllComplain();
		log.debug("Data fetched successfully from complain");
		return new ResponseEntity<>(new RestResponse<>(contacts, status), HttpStatus.OK);
	}
	
	@GetMapping("/type")
	public ResponseEntity<RestResponse<List<String>>> getAllType() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		List<String> list = contactService.getAllType();
		log.debug("Data fetched successfully from complain");
		return new ResponseEntity<>(new RestResponse<>(list, status), HttpStatus.OK);
	}
	
	@GetMapping("/{compId}")
	public ResponseEntity<RestResponse<Contact>> getById(@PathVariable(name = "compId", required = true) Long compId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "All Records Fetched Successfully");
		Contact contacts = contactService.getDetailById(compId);
		log.debug("Data fetched successfully from complain");
		return new ResponseEntity<>(new RestResponse<>(contacts, status), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<RestResponse<Object>> addSearchStation(@RequestBody(required = true) Contact contact) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Complain Added Successfully");
		Long integer = contactService.addContact(contact);
		if (integer == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), GlobalConstants.ERROR_MESSAGE);
			return new ResponseEntity<>(new RestResponse<>(integer, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(integer, status), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{compId}")
	public ResponseEntity<RestResponse<Object>> resetPassword(
			@PathVariable(name = "compId", required = true) Long compId,
			@RequestBody(required = true) Contact contact) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Complain updated Successfully");
		contact.setComplainId(compId);
		int i = contactService.updateContact(contact);
		if (i == 0) {
			status = new RestStatus<>(HttpStatus.INTERNAL_SERVER_ERROR.toString(), GlobalConstants.ERROR_MESSAGE);
			return new ResponseEntity<>(new RestResponse<>(i, status), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new RestResponse<>(i, status), HttpStatus.OK);
	}
	
	@DeleteMapping("/{compId}")
	public ResponseEntity<RestResponse<Object>> deleteCity(
			@PathVariable(name = "compId", required = true) Long compId) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Records deleted Successfully");
		int delete = contactService.deleteContact(compId);
		return new ResponseEntity<>(new RestResponse<>(delete, status), HttpStatus.OK);
	}
	
}
