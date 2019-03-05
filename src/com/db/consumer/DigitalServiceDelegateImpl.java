package com.db.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.exception.BaseException;
import com.db.spring.model.RestResponse;

@Service
public class DigitalServiceDelegateImpl implements DigitalServiceDelegate{

	@Autowired
	private DigitalAuthTokenService digitalAuthTokenService;
	
	@Override
	public DigitalAuthToken generateAuthToken() throws BaseException {
		return this.digitalAuthTokenService.getAuthTokenDetail();
	}

	@Override
	public RestResponse createOrUpdateLeads(RestResponse request) throws BaseException {
		return null;
	}

}
