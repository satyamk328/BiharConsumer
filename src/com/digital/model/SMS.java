package com.digital.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SMS {
	
	private String username;
	private String password;
	private String from;
	private List<Long> to;
	private String text;
	private String coding;
	private String flash;

}
