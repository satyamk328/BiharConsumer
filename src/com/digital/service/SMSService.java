package com.digital.service;

import java.nio.charset.Charset;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.digital.audit.service.DBLoggingHandler;
import com.digital.audit.service.RestTemplateService;
import com.digital.model.SMSResponse;

@Service
public class SMSService {

	private static final Logger log = LoggerFactory.getLogger(SMSService.class);

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private DBLoggingHandler dbLoggingHandler;

	@Value("${sms_gateWay_URL}")
	private String smsGateWayAPi;
	@Value("${select_trip_by_city}")
	private String smsAccessToken;

	public SMSResponse sendSMS(String phoneNumber) {
		log.debug("smsGateWayAPi ={}", smsGateWayAPi);
		ResponseEntity<SMSResponse> response = null;
		try {
			Map<String, Object> uriVariables = new HashMap<String, Object>();
			uriVariables.put("transactionId", 1);
			response = restTemplateService.getRestTemplate().exchange(smsGateWayAPi, HttpMethod.GET,
					new HttpEntity<SMSResponse>(createXAuthHeader(smsAccessToken)), SMSResponse.class, uriVariables);
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

	private HttpHeaders createXAuthHeader(String xAuthHeader) {
		log.info("Creating XAuthHeader , xAuthHeader={}", xAuthHeader);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("X-Auth-Token", xAuthHeader);
		
		String username = "test",password ="123";
		String auth = username + ":" + password;  
		byte[] encodedAuth = Base64Utils.encode(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
		httpHeaders.set("Authorization", authHeader);
		
		return httpHeaders;
	}

}
