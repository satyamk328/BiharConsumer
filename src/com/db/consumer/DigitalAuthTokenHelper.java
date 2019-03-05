package com.db.consumer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.exception.BaseException;

@Service
public class DigitalAuthTokenHelper {

	private static Logger logger = Logger.getLogger(DigitalAuthTokenHelper.class);

	public DigitalAuthTokenHelper() {
		super();
	}

	@Autowired
	protected DigitalServiceDelegate digitalServiceDelegate;

	private volatile Long expiry;

	private transient DigitalAuthToken digitalAuthToken;

	public DigitalAuthToken generateToken() throws BaseException {
		long now  = System.currentTimeMillis();
		if (expiry == null || expiry < now) {
			logger.info("Token is empty or expired. Trying new authentication");
			DigitalAuthToken token = this.digitalServiceDelegate.generateAuthToken();
			logger.info("Got Authentication Response: " + token.jsonMap());
			this.expiry = System.currentTimeMillis() + (token.getExpiry() * 1000);
			this.digitalAuthToken = token;
		}
		return this.digitalAuthToken;
	}
	
}
