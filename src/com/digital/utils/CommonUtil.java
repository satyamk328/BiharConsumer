package com.digital.utils;

import java.io.UnsupportedEncodingException;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;

import com.digital.model.TicketCancellationPolicy;

/**
 * @author Satyam Kumar
 *
 */
@Component
public class CommonUtil {

	public String encrypt(String data) {
		try {
			return Base64.getEncoder().encodeToString(data.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public String decrypt(String encryptedData) {
		byte[] asBytes = Base64.getDecoder().decode(encryptedData);
		try {
			return new String(asBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(encrypt("123"));
	}

	public static TicketCancellationPolicy getPolicyToApply(List<TicketCancellationPolicy> policies, int minutesAfterBooking, int minutesBeforeStart) {
		// POLOICY ORDER BY PRIORITY
		for (TicketCancellationPolicy policy : policies) {
			if ("AFTER_BOOK".equals(policy.getApplyTerm()) && policy.getHoursToApply() * 60 >= minutesAfterBooking) {
				return policy;
			} else if ("BEFORE_START".equals(policy.getApplyTerm()) && policy.getHoursToApply()*60 <= minutesBeforeStart) {
				return policy;
			} else {
				return policy;
			}
		}
		return null;
	}
	
	public static Double getRefundAmount(TicketCancellationPolicy policy, Double bookingAmount) {
		// POLOICY ORDER BY PRIORITY
		return (policy.getRefendPercent() * bookingAmount)/100;
	}
}