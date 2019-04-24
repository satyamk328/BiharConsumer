package com.digital.audit.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceApiAuditLog {
	private Long logId;
	private String apiName;
	private Date startTime;
	private Date endTime;
	private Long durationMilliSeconds;
	private String requestHttpMethod;
	private String requestUrl;
	private String requestBody;
	private int responseHttpCode;
	private String responseBody;
	private String requestHeader;
	private String responseHeader;
	private String channelType;
	
}
