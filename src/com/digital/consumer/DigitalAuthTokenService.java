package com.digital.consumer;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.digital.exception.BaseException;

@Service
public class DigitalAuthTokenService extends DigitalService<DigitalAuthToken> {

	private static final Logger logger = Logger.getLogger(DigitalAuthTokenService.class);

	@Value("${digital.clientid}")
	protected String clientId;

	@Value("${digital.clientSecret}")
	protected String clientSecret;

	@Value("${digital.appInstance.url}")
	protected String marketoInstanceUrl;

	private transient String idEndpoint;

	public DigitalAuthTokenService() {
		super();
	}

	@Override
	public String getEndpoint() {
		return this.marketoInstanceUrl;
	}

	@PostConstruct
	public void initObject() {
		this.idEndpoint = "/identity/oauth/token?grant_type=client_credentials&client_id=" + this.clientId + "&client_secret=" + this.clientSecret;
	}

	@Override
	public ParameterizedTypeReference<DigitalAuthToken> buildParameterizedTypeReference() {
		return new ParameterizedTypeReference<DigitalAuthToken>(){};
	}

	public DigitalAuthToken execute() throws BaseException {
		return this.execute(this.idEndpoint, BaseHttpMethod.GET, new Object[]{});
	}

	public DigitalAuthToken getAuthTokenDetail() throws BaseException {
		try {
			return this.execute();
		} catch (BaseException e) {
			logger.error("Method Name: getResponse | Error Description : Unable to retrieve authentication token from endPoint " + this.marketoInstanceUrl, e);
			throw e;
		}
	}
}
