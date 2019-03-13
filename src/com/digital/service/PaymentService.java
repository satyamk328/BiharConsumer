package com.digital.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.digital.utils.PaymentUtil;

/**
 * @author Satyam Kumar
 *
 */
@Service("ticketService")
public class PaymentService {

	@Autowired
	private PaymentUtil paymentUtil;

	/*
	 * provide by payubiz
	 */
	private final String merchant_key = "gtKFFx";
	/*
	 * provide by payubiz
	 */
	private final String salt = "eCwWELxi";

	/*
	 * Production POST URL: https://secure.payu.in/_payment
	 */
	private final String base_url = "https://test.payu.in/_payment";

	public ResponseEntity<String> startPayment() throws Exception {
		// Sample data
		Map<String, String> params = new HashMap<String, String>();
		String txnId = paymentUtil.generateTxnId();
		params.put("key", merchant_key);
		params.put("SALT", salt);
		params.put("txnid", txnId);
		params.put("amount", "10000.00");
		params.put("productinfo", "Description");
		params.put("firstname", "Robins Kumar");
		params.put("email", "robinsxxxxx@xxxx.com");
		params.put("phone", "8109xxxxxx");
		params.put("surl", "success_url");
		params.put("furl", "failure_url");
		params.put("curl", "cancel_url");

		paymentUtil.validate(params);
		String hashString = paymentUtil.generateHashString(params);

		/*
		 * generate checksum of hashString
		 */
		String hash = paymentUtil.hashCal("SHA-512", hashString);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.setAll(params);
		map.add("hash", hash);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> response = restTemplate.postForEntity(base_url, request, String.class);

		/*
		 * return the payment get-way page for payment option
		 */
		return response;
	}

}
