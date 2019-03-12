package com.digital.audit.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceApiAuditLog {
	private Long Id;
	private String apiName;
	private java.util.Date startTime;
	private java.util.Date endTime;
	private Long durationMilliSeconds;
	private String requestHttpMethod;
	private String requestUrl;
	private String requestBody;
	private int responseHttpCode;
	private String responseBody;
	private String requestHeader;
	private String responseHeader;
	
}
