package com.digital.utils;

import java.io.UnsupportedEncodingException;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Base64;

/**
 * @author Satyam Kumar
 *
 */
public class CommonUtil {

	public static String encrypt(String data) {
		try {
			return Base64.getEncoder().encodeToString(data.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String decrypt(String encryptedData) {
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
}
