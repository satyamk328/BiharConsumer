package com.digital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.service.ObjectCache;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;
import com.digital.utils.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0/cache")
@Slf4j
public class RefreshController {

	@Autowired
	private ObjectCache objectCache;
	
	@Autowired
	private TransactionService transactionService;

	@GetMapping("")
	public ResponseEntity<RestResponse<Object>> evictCache() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Application cache clear Successfully");
		log.debug("Application cache clear Successfully");
		objectCache.clearAppCache();
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}
	
	@GetMapping("/generate-tranid")
	public ResponseEntity<RestResponse<Object>> loadPayment(HttpServletRequest request) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Application cache clear Successfully");
		String trans = transactionService.generate();
		log.debug("Application cache clear Successfully");
		return new ResponseEntity<>(new RestResponse<>(trans, status), HttpStatus.OK);
	}
}
