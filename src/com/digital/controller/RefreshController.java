package com.digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.service.ObjectCache;
import com.digital.spring.model.RestResponse;
import com.digital.spring.model.RestStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v0/cache")
@Slf4j
public class RefreshController {

	@Autowired
	private ObjectCache objectCache;

	@GetMapping("/")
	public ResponseEntity<RestResponse<Object>> evictCache() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Application cache clear Successfully");
		log.debug("Application cache clear Successfully");
		objectCache.clearAppCache();
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}
}
