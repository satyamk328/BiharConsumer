package com.digital.consumer;

import com.digital.exception.BaseException;
import com.digital.spring.model.RestResponse;

public interface DigitalServiceDelegate {

	public DigitalAuthToken generateAuthToken() throws BaseException;

	public RestResponse<?> createOrUpdateLeads(RestResponse<?> request) throws BaseException;

}