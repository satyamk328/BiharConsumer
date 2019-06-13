package com.digital.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesCryptUtil {
	Cipher ecipher;
	Cipher dcipher;
	byte[] buf = new byte[1024];
	static final String HEXES = "0123456789ABCDEF";

	public AesCryptUtil() {
		try {
			KeyGenerator var1 = KeyGenerator.getInstance("AES");
			var1.init(128);
			this.setupCrypto(var1.generateKey());
		} catch (Exception var2) {
			var2.printStackTrace();
		}

	}

	public AesCryptUtil(String var1) {
		SecretKeySpec var2 = new SecretKeySpec(getMD5(var1), "AES");
		this.setupCrypto(var2);
	}

	private void setupCrypto(SecretKey var1) {
		byte[] var2 = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		IvParameterSpec var3 = new IvParameterSpec(var2);

		try {
			this.ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			this.dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			this.ecipher.init(1, var1, var3);
			this.dcipher.init(2, var1, var3);
		} catch (Exception var5) {
			var5.printStackTrace();
		}

	}

	public void encrypt(InputStream var1, OutputStream var2) {
		try {
			CipherOutputStream var5 = new CipherOutputStream(var2, this.ecipher);
			boolean var3 = false;

			int var6;
			while ((var6 = var1.read(this.buf)) >= 0) {
				var5.write(this.buf, 0, var6);
			}

			var5.close();
		} catch (IOException var4) {
			var4.printStackTrace();
		}

	}

	public String encrypt(String var1) {
		try {
			byte[] var2 = this.ecipher.doFinal(var1.getBytes("UTF-8"));
			return byteToHex(var2);
		} catch (Exception var3) {
			var3.printStackTrace();
			return null;
		}
	}

	public void decrypt(InputStream var1, OutputStream var2) {
		try {
			CipherInputStream var5 = new CipherInputStream(var1, this.dcipher);
			boolean var3 = false;

			int var6;
			while ((var6 = var5.read(this.buf)) >= 0) {
				var2.write(this.buf, 0, var6);
			}

			var2.close();
		} catch (IOException var4) {
			var4.printStackTrace();
		}

	}

	public String decrypt(String var1) {
		try {
			String var2 = new String(this.dcipher.doFinal(hexToByte(var1)), "UTF-8");
			return var2;
		} catch (Exception var3) {
			var3.printStackTrace();
			return null;
		}
	}

	public String decrypt(byte[] var1) {
		try {
			String var2 = new String(this.dcipher.doFinal(var1), "UTF-8");
			return var2;
		} catch (Exception var3) {
			var3.printStackTrace();
			return null;
		}
	}

	private static byte[] getMD5(String var0) {
		try {
			byte[] var1 = var0.getBytes("UTF-8");
			MessageDigest var2 = MessageDigest.getInstance("MD5");
			return var2.digest(var1);
		} catch (Exception var3) {
			return null;
		}
	}

	public static String byteToHex(byte[] var0) {
		if (var0 == null) {
			return null;
		} else {
			String var1 = "";

			for (int var2 = 0; var2 < var0.length; ++var2) {
				var1 = var1 + Integer.toString((var0[var2] & 255) + 256, 16).substring(1);
			}

			return var1;
		}
	}

	public static byte[] hexToByte(String var0) {
		int var1 = var0.length();
		byte[] var2 = new byte[var1 / 2];

		for (int var3 = 0; var3 < var1; var3 += 2) {
			var2[var3 / 2] = (byte) ((Character.digit(var0.charAt(var3), 16) << 4)
					+ Character.digit(var0.charAt(var3 + 1), 16));
		}

		return var2;
	}

	public static void main(String[] var0) {
		String var1 = null;
		String var2 = null;
		String var3 = null;
		String var4 = null;
		String var5 = null;
		if (var0 != null && var0.length >= 3) {
			var3 = var0[0];
			var4 = var0[1];
			var5 = var0[2];
			if (var3 == null) {
				var2 = "error: no key";
			} else if (var3.length() < 32) {
				var2 = "error: key length less than 32 bytes";
			} else if (var4 != null && var5 != null) {
				if (var5 == null) {
					var2 = "error: no action";
				} else if (!var5.equals("enc") && !var5.equals("dec")) {
					var2 = "error: invalid action";
				}
			} else {
				var2 = "error: no data";
			}
		} else {
			var2 = "error: missing one or more arguments. Usage: AesCryptUtil key data <enc|dec>";
		}

		if (var2 == null) {
			try {
				AesCryptUtil var6 = new AesCryptUtil(var3);
				if (var5.equals("enc")) {
					var1 = var6.encrypt(var4);
				} else {
					var1 = var6.decrypt(var4);
				}
			} catch (Exception var7) {
				var2 = "error : Exception in performing the requested operation : " + var7;
			}
		}

		if (var1 != null) {
			System.out.println(var1);
		} else {
			System.out.println(var2);
		}

	}
}