package com.digital.utils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
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

	public static TicketCancellationPolicy getPolicyToApply(List<TicketCancellationPolicy> policies,
			Long minutesAfterBooking, Long minutesBeforeStart) {
		// POLOICY ORDER BY PRIORITY
		for (TicketCancellationPolicy policy : policies) {
			if ("BEFORE_START".equals(policy.getApplyTerm()) && policy.getHoursToApply() * 60 <= minutesBeforeStart) {
				return policy;
			} else if ("DEFAULT".equals(policy.getApplyTerm())) {
				return policy;
			}
		}
		return null;
	}

	public static Double getRefundAmount(TicketCancellationPolicy policy, Double bookingAmount) {
		// POLOICY ORDER BY PRIORITY
		return (policy.getRefundPercent() * bookingAmount) / 100;
	}

	public static Date dateByDateAndTimeString(String date, String time) throws ParseException {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(date + " " + time);
		// return //simpleDateFormat.parse("2019-04-30 12:30:00");
	}

	public static Long getMinutesDiff(Date biggerDate, Date lesserDate) {
		long diff = biggerDate.getTime() - lesserDate.getTime();
		// long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return ((diffDays * 24 * 60) + (diffHours * 60) + diffMinutes);
	}

	public String getPNRNumber(String name, Long sourceId, Long destId, Integer bookCount) {
		StringBuilder builder = new StringBuilder();
		builder.append(sourceId);
		builder.append(destId);
		builder.append(bookCount);
		builder.append(getDate());
		return builder.toString();
	}

	private String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyy");
		Date date = new Date();
		return simpleDateFormat.format(date);
	}

	public static String convert24HrsTo12Hrs(String input) {
		DateFormat df = new SimpleDateFormat("HH:mm");
		DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
		Date date = null;
		String output = null;
		try {
			date = df.parse(input);
			output = outputformat.format(date);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return output;
	}

	public static String convert12HrsTo24Hrs(String input) {
		DateFormat df = new SimpleDateFormat("hh:mm aa");
		DateFormat outputformat = new SimpleDateFormat("HH:mm");
		Date date = null;
		String output = null;
		try {
			date = df.parse(input);
			output = outputformat.format(date);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return output;
	}

	public String getNextDate(String curDate){
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(curDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return format.format(calendar.getTime());
	}
	
}