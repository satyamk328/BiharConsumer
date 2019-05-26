package com.digital.utils;

import java.util.concurrent.atomic.AtomicLong;

public class TransactionService {

	private static final int MAX_SIZE = 18;
	private static final AtomicLong counter = new AtomicLong();
	private static String appPlatformId = "S";

	public static String generate() {
		StringBuilder txnId = new StringBuilder();
		txnId.append(System.currentTimeMillis());
		txnId.append(appPlatformId);
		txnId.append(String.format("%04d", counter.getAndIncrement()));
		if (txnId.length() > MAX_SIZE) {
			return txnId.substring(txnId.length() - MAX_SIZE);
		}
		return txnId.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(generate());
		}
	}
}
