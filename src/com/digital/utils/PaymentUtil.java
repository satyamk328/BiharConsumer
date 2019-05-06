package com.digital.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class PaymentUtil {
	/*
	 * sha512(key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|
	 * |||||SALT) hash = sha512(gtKFFx|PLS-999994w5-3|600.000|SAU Admission
	 * 2014|Robin|robinsxxxxx@xxxx.com|||||||||||eCwWELxi)
	 */
	public static String hashCal(String type, String str) {
		byte[] hashseq = str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();

			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1)
					hexString.append("0");
				hexString.append(hex);
			}
		} catch (NoSuchAlgorithmException nsae) {
		}
		return hexString.toString();
	}

	public static String generateTxnId() {
		Random rand = new Random();
		String txnId = Integer.toString(rand.nextInt()) + (System.currentTimeMillis() / 1000L);
		return hashCal("SHA-256", txnId).substring(0, 20);
	}
	public static void main(String[] args) {
		System.out.println(generateTxnId());
	}
}