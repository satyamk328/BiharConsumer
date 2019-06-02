package com.digital.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) throws ParseException {
		String dateValues = "2019-06-20";
		String date2Values = "2019-06-02";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date date1 = format.parse(dateValues);
		Date date2 = format.parse(date2Values);

		if (date1.equals(date2)) {
			System.out.println("Equals");
		} else if (date1.before(date2)) {
			System.out.println("before");
		} else if (date1.after(date2)) {
			System.out.println("after");
		}
	}

	public static String convertStringToDateFormat(String dateValues) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(dateValues);
	}
}
