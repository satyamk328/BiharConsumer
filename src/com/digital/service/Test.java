package com.digital.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
	public static void main(String[] args) throws ParseException {
		List<String> list = new ArrayList<>();
		System.out.println(list.get(0));
	}

	public static String convertStringToDateFormat(String dateValues) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(dateValues);
	}
}
