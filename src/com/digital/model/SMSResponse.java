package com.digital.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SMSResponse {
	private Data dataObject;

}

@Setter
@Getter
class Data {
	private String msgid;
	private String ack_id;

}
