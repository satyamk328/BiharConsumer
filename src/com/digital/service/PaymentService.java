package com.digital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital.audit.service.RestTemplateService;

@Component
public class PaymentService {

	@Autowired
	private RestTemplateService restTemplateService;

	public void cancelpayment() {
		
	}
}
