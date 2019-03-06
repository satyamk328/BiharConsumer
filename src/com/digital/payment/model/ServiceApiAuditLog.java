package com.digital.payment.model;

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

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public Long getDurationMilliSeconds() {
		return durationMilliSeconds;
	}

	public void setDurationMilliSeconds(Long durationMilliSeconds) {
		this.durationMilliSeconds = durationMilliSeconds;
	}

	public String getRequestHttpMethod() {
		return requestHttpMethod;
	}

	public void setRequestHttpMethod(String requestHttpMethod) {
		this.requestHttpMethod = requestHttpMethod;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public int getResponseHttpCode() {
		return responseHttpCode;
	}

	public void setResponseHttpCode(int responseHttpCode) {
		this.responseHttpCode = responseHttpCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public String getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(String requestHeader) {
		this.requestHeader = requestHeader;
	}

	public String getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(String responseHeader) {
		this.responseHeader = responseHeader;
	}
}
