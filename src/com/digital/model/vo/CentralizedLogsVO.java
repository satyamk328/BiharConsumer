package com.digital.model.vo;

import java.util.Date;

import com.digital.utils.DateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/*import lombok.Getter;
import lombok.Setter;*/

/*@Getter
@Setter*/
public class CentralizedLogsVO {
	private String appName;
	private String logLevel;
	private Date logTimeStamp;
	private String logMessage;

	@JsonDeserialize(using = DateTimeDeserializer.class)
	public void setLogTimeStamp(final Date logTimeStamp) {
		this.logTimeStamp = logTimeStamp;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public Date getLogTimeStamp() {
		return logTimeStamp;
	}
	
}
