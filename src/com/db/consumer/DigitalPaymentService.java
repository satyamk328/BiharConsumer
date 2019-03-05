package com.db.consumer;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.db.exception.BaseException;
import com.db.spring.model.RestResponse;


@Service
public class DigitalPaymentService extends DigitalService<RestResponse> {

	private static final Logger logger = Logger.getLogger(DigitalPaymentService.class);

	@Autowired
	private DigitalAuthTokenHelper digitalAuthTokenHelper;

	@Value("${marketo.appInstance.url}")
	private String endPoint;

	@Override
	public String getEndpoint() {
		return this.endPoint;
	}

	@Override	
	public ParameterizedTypeReference<RestResponse> buildParameterizedTypeReference() {
		return new ParameterizedTypeReference<RestResponse>(){};
	}
	
	public RestResponse execute(String leadId, String param) throws BaseException {
		Assert.assertNotNull(param, "The paramMergeRequest must not be null");
		//logger.info("Request that is going to sent to digital: " + paramLeadRequest.jsonMap());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("Authorization", this.digitalAuthTokenHelper.generateToken().tokenToBeSent());
		return this.execute("/rest/v1/leads/"+leadId+"/merge.json?"+param, BaseHttpMethod.POST, null, headers);
	}

}
