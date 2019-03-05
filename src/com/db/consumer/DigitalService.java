package com.db.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;

public abstract class DigitalService<T> extends BaseService<T> {

	@Autowired
	@Qualifier("digitalRestTemplate")
	protected RestTemplate digitalRestTemplate;

	public DigitalService() {
		super();
	}

	@Override
	public RestTemplate getRestTemplate() {
		return this.digitalRestTemplate;
	}

	
}