package com.db.consumer;

import org.springframework.web.client.RestTemplate;

import com.db.exception.BaseException;

public interface IService<T> {

	public String getName();

	public T execute(String command, BaseHttpMethod httpMethod, Object... params) throws BaseException;

	public String getEndpoint();
	
	public RestTemplate getRestTemplate();
}
