package com.digital.service;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.digital.model.Payment;
import com.digital.payment.model.PaymentRefund;

@Component
public class PaymentWrapperService {

	public PaymentRefund createRefund(PaymentRefund refund) {
		return null;
	}
	public Payment getPaymentRequest(String id) {
		
		return null;
	}
	public Boolean enablePaymentRequest(String id) {
		return true;
	}
	public Boolean disablePaymentRequest(String id) {
		return true;
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
