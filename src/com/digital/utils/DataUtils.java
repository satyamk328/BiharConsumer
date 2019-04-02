package com.digital.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Satyam Kumar
 *
 */
@Component
@Slf4j
public class DataUtils {

	public boolean validatePhoneNumber(String phoneNo) {
		// validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}"))
			return true;
		// validating phone number with -, . or spaces
		else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
			return true;
		// validating phone number with extension length from 3 to 5
		else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}"))
			return true;
		// validating phone number where area code is in braces ()
		else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}"))
			return true;
		// return false if nothing matches the input
		else
			return false;
	}
	

	public Map<String, String> validIdCardTypes() {
		Map<String, String> idTypes = new HashMap<>();
		idTypes.put("NULL_IDCARD", "NULL_IDCARD");
		idTypes.put("DRIVING_LICENSE", "DRIVING_LICENSE");
		idTypes.put("PASSPORT", "PASSPORT");
		idTypes.put("PANCARD", "PANCARD");
		idTypes.put("GOVT_ICARD", "GOVT_ICARD");
		idTypes.put("BANK_PASSBOOK", "BANK_PASSBOOK");
		idTypes.put("STUDENT_ICARD", "STUDENT_ICARD");
		idTypes.put("CREDIT_CARD", "CREDIT_CARD");
		idTypes.put("UNIQUE_ICARD", "UNIQUE_ICARD");
		idTypes.put("VOTER_ICARD", "VOTER_ICARD");
		return idTypes;
	}

	public List<String> getTimeList() {
		List<String> busTypes = new ArrayList<>();
		busTypes.add("Before 6 am");
		busTypes.add("6 am to 12 pm");
		busTypes.add("12 pm to 6 pm");
		busTypes.add("After 6 pm");
		return busTypes;
	}
	
	public List<String> getBusType() {
		List<String> busTypes = new ArrayList<>();
		busTypes.add("AC");
		busTypes.add("Non AC");
		busTypes.add("Seater");
		busTypes.add("Sleeper");
		return busTypes;
	}
	
	public String parseBusDate(String str) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}
	
	public Date convertStringToDateFormat(String date, String format) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public String convertFormat(String date) {
		Date dateValues = convertStringToDateFormat(date, "yyyy-MM-dd");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(dateValues);
	}
	
	public String formatDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.format(date);
	}

	public static void main(String[] args) {
		String str = "1::2::3::4";
		System.out.println(str.split("::")[2]);
	}

	public String getGenerateOTP() {
		Random rnd = new Random();
		int otp = rnd.nextInt(900000) + 100000;
		return String.valueOf(otp);
	}
}
