package com.digital.service;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.digital.audit.service.DBLoggingHandler;
import com.digital.audit.service.RestTemplateService;
import com.digital.model.SMS;
import com.digital.model.SMSResponse;

@Service
public class SMSWrapperService {

	private static final Logger log = LoggerFactory.getLogger(SMSWrapperService.class);

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private DBLoggingHandler dbLoggingHandler;

	@Value("${sms_gateWay_URL}")
	private String smsGateWayAPi;

	@Value("${sms_tocken}")
	private String smsAccessToken;

	public SMSResponse sendSMSGET(String phoneNumber) {
		log.debug("smsGateWayAPi ={}", smsGateWayAPi);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("transactionId", 1);

		HttpHeaders httpHeaders = new HttpHeaders();
		// set up HTTP Basic Authentication Header
		httpHeaders.add("X-Auth-Token", smsAccessToken);
		String username = "test", password = "123";
		String auth = username + ":" + password;
		byte[] encodedAuth = Base64Utils.encode(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		httpHeaders.set("Authorization", authHeader);
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		// request entity is created with request headers
		HttpEntity<SMSResponse> requestEntity = new HttpEntity<>(httpHeaders);
		ResponseEntity<SMSResponse> response = null;
		try {
			response = restTemplateService.getRestTemplate().exchange(smsGateWayAPi, HttpMethod.GET, requestEntity,
					SMSResponse.class, param);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				SMSResponse cardVaultResponse = response.getBody();
				return cardVaultResponse;
			} else {
				log.error("Failed to fetch card record for PhoneNumber={} , Http Status={}", phoneNumber,
						response != null ? response.getStatusCode() : "ResponseIsNull");
				dbLoggingHandler.logError("Failed to fetch card record for PhoneNumber=" + phoneNumber
						+ ", Http Status=" + (response != null ? response.getStatusCode() : "ResponseIsNull"));
			}
		} catch (Exception ex) {
			log.error("Failed to get response while fetch card vault token for PhoneNumber={}, Exp Msg={}", phoneNumber,
					ex.getMessage(), ex);
			dbLoggingHandler.logError("Failed to get response while fetch card vault token for PhoneNumber="
					+ phoneNumber + ", Exp Msg=" + ex.getMessage());
		}
		return null;
	}

	public SMSResponse sendSMSPost(String phoneNumber) {
		log.debug("smsGateWayAPi ={}", smsGateWayAPi);

		String username = "test", password = "123";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("transactionId", 1);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		// set up HTTP Basic Authentication Header
		String auth = username + ":" + password;
		byte[] encodedAuth = Base64Utils.encode(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		httpHeaders.set("Authorization", authHeader);

		//setting up the request body
        SMS user = new SMS();
        user.setName("Sample User");
        user.setUserName("user1");
        user.setPassword("pass123");
        
		// request entity is created with request headers
        HttpEntity<SMS> requestEntity = new HttpEntity<>(user, httpHeaders);

		ResponseEntity<SMSResponse> response = null;
		try {
			response = restTemplateService.getRestTemplate().exchange(smsGateWayAPi, HttpMethod.POST, requestEntity,
					SMSResponse.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				SMSResponse cardVaultResponse = response.getBody();
				return cardVaultResponse;
			} else {
				log.error("Failed to fetch card record for PhoneNumber={} , Http Status={}", phoneNumber,
						response != null ? response.getStatusCode() : "ResponseIsNull");
				dbLoggingHandler.logError("Failed to fetch card record for PhoneNumber=" + phoneNumber
						+ ", Http Status=" + (response != null ? response.getStatusCode() : "ResponseIsNull"));
			}
		} catch (Exception ex) {
			log.error("Failed to get response while fetch card vault token for PhoneNumber={}, Exp Msg={}", phoneNumber,
					ex.getMessage(), ex);
			dbLoggingHandler.logError("Failed to get response while fetch card vault token for PhoneNumber="
					+ phoneNumber + ", Exp Msg=" + ex.getMessage());
		}
		return null;
	}

}
