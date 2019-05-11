package com.digital.audit.model;

import java.io.Serializable;
import java.util.Date;

import com.digital.utils.DateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CentralizedLogs implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long logId;
	private String appName;
	private String logLevel;
	private Date logTimeStamp;
	private String logMessage;
	private String channelType;

	@JsonDeserialize(using = DateTimeDeserializer.class)
	public void setLogTimeStamp(final Date logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

}
