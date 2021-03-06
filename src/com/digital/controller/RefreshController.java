package com.digital.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.service.ObjectCache;
import com.digital.spring.model.CCavenue;
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

	@Value("${forwordUrl}")
	private String forwordUrl;

	/**
	 * this method is use to clear the cache
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<RestResponse<Object>> evictCache() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Application cache clear Successfully");
		log.debug("Application cache clear Successfully");
		objectCache.clearAppCache();
		return new ResponseEntity<>(new RestResponse<>(null, status), HttpStatus.OK);
	}

	@GetMapping("/generate-tranid")
	public ResponseEntity<RestResponse<Object>> loadPayment() {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Application cache clear Successfully");
		String trans = transactionService.generate();
		log.debug("Application cache clear Successfully");
		return new ResponseEntity<>(new RestResponse<>(trans, status), HttpStatus.OK);
	}

	/**
	 * this method is use to encript the data
	 * 
	 * @param data
	 * @return
	 */
	@PostMapping("/encript")
	public ResponseEntity<RestResponse<Object>> encript(@RequestBody(required = true) CCavenue data,
			HttpServletRequest request) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Data encripted Successfully");
		CCavenue trans = objectCache.encript(data.getEncrypted(),request);
		log.debug("Application cache clear Successfully");
		return new ResponseEntity<>(new RestResponse<>(trans, status), HttpStatus.OK);
	}

	/**
	 * this method is use to decript the data
	 * 
	 * @param data
	 * @return
	 */
	@GetMapping("/descript/{data}")
	public ResponseEntity<RestResponse<Object>> descript(@PathVariable(name = "data", required = true) String data) {
		RestStatus<String> status = new RestStatus<>(HttpStatus.OK.toString(), "Data decripted Successfully");
		Map<String, String> trans = objectCache.decript(data);
		log.debug("Application cache clear Successfully");
		return new ResponseEntity<>(new RestResponse<>(trans, status), HttpStatus.OK);
	}

	/**
	 * this method is use to handle payment gateway request
	 * 
	 * @param request
	 * @param httpServletResponse
	 */
	@PostMapping(value = "/ccavResponseHandler")
	public void method(HttpServletRequest request, HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("Location", forwordUrl + "" + request.getParameter("encResp"));
		httpServletResponse.setStatus(303);
	}

}
