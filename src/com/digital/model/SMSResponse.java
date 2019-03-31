package com.digital.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SMSResponse {
	private ResponeDate date;
	
	@Setter
	@Getter
	class ResponeDate{
		private String msgid;
		private String ack_id;
	}
}

