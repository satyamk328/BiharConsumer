package com.digital.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TicketCancellationPolicy implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long policyId;
	private Long busId;
	private Integer hoursToApply;
	private Integer refundPercent;
	private Integer applyPriority;
	private String applyTerm;
	private String description;
}
