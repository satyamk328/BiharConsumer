package com.db.consumer;

import com.db.exception.BaseException;
import com.db.spring.model.RestResponse;

public interface DigitalServiceDelegate {

	public DigitalAuthToken generateAuthToken() throws BaseException;

	public RestResponse createOrUpdateLeads(RestResponse request) throws BaseException;

}