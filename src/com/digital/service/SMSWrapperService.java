package com.digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digital.audit.service.DBLoggingHandler;
import com.digital.audit.service.RestTemplateService;
import com.digital.model.SMS;
import com.digital.model.SMSResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SMSWrapperService {

	@Autowired
	private RestTemplateService restTemplateService;

	@Autowired
	private DBLoggingHandler dbLoggingHandler;

	@Value("${sms_gateWay_URL}")
	private String smsGateWayAPi;

	@Value("${sms_user_name}")
	private String smsUserName;

	@Value("${sms_user_name}")
	private String password;

	@Value("${sms_header_value}")
	private String headerValue;

	public SMSResponse sendSMS(List<String> numbers, String text) {

		log.debug("smsGateWayAPi ={}", smsGateWayAPi);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		// setting up the request body
		SMS sms = new SMS();
		sms.setUsername(smsUserName);
		sms.setPassword(password);
		sms.setFrom(headerValue);
		sms.setTo(numbers);
		sms.setText(text);
		sms.setFlash("1");
		sms.setCoding("0");

		// request entity is created with request headers
		HttpEntity<SMS> requestEntity = new HttpEntity<>(sms, httpHeaders);
		ResponseEntity<SMSResponse> response = null;
		try {
			response = restTemplateService.getRestTemplate().exchange(smsGateWayAPi, HttpMethod.POST, requestEntity,
					SMSResponse.class);
			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				SMSResponse smsResponse = response.getBody();
				return smsResponse;
			} else {
				log.error("Failed to fetch card record for PhoneNumber={} , Http Status={}", numbers,
						response != null ? response.getStatusCode() : "ResponseIsNull");
				dbLoggingHandler.logError("Failed to fetch card record for PhoneNumber=" + numbers + ", Http Status="
						+ (response != null ? response.getStatusCode() : "ResponseIsNull"));
			}
		} catch (Exception ex) {
			log.error("Failed to get response while fetch card vault token for PhoneNumber={}, Exp Msg={}", numbers,
					ex.getMessage(), ex);
			dbLoggingHandler.logError("Failed to get response while fetch card vault token for PhoneNumber=" + numbers
					+ ", Exp Msg=" + ex.getMessage());
		}
		return null;
	}

}
