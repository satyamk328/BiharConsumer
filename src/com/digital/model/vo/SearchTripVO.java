package com.digital.model.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
/**
 * @author Satyam Kumar
 *
 */
@Getter
@Setter
public class SearchTripVO implements Serializable{
	private static final long serialVersionUID = -1860227643588269105L;
	
	private Long scheduleId;
	private Long busId;
	private Long sourceId;
	private Long destinationId;
	
}
