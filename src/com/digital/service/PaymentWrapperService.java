package com.digital.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.digital.audit.service.RestTemplateService;

@Component
public class PaymentWrapperService {

	@Autowired
	private RestTemplateService restTemplateService;

	public void cancelpayment() {
		
	}
	public void confirmPayment() {
		final HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("lastname", "");
		map.add("00NG000000DO1wu", "");
		map.add("00NG000000DO1wz", "");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
				requestHeaders);

		try {
			final ResponseEntity<String> paymentResponse = restTemplateService.getRestTemplate().postForEntity("",
					request, String.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		String authToken = Base64Utils.encodeToString(("user:password").getBytes());
		httpHeaders.set("Authorization", "Basic " + authToken);
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return httpHeaders;
	}
}
