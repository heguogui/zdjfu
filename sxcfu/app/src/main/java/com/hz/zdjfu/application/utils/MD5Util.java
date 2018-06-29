package com.hz.zdjfu.application.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @name MD5加密
 * @auth liujb
 * @date 2016-01-26 15:26:28
 */
public class MD5Util {

	/**
	 * MD5 32位长度 加密
	 * @param str
	 * @return
	 */
	public static String MD5_32(String str) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			result = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return result;
	}

	public static String md5s(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;


			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
//			str = buf.toString();
//			System.out.println("result: " + buf.toString());// 32位的加密

		} catch (NoSuchAlgorithmException e) {
			//
			e.printStackTrace();

		}
		return buf.toString();
	}

}
