package com.yhxy.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswdUtil {
	
	public static String SALT = "kxm";
	
	/**
	 * MD5加密
	 * @param input	要加密的字符串
	 * @return
	 */
	public static String encryptByMD5(String input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			try {
				md.update(input.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			byte byteData[] = md.digest();

			// 二进制转换为十六进制
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String encryptByMD5Salt(String input) {
		return encryptByMD5(input+"{"+SALT+"}");
	}

	/**
	 * SHA256加密
	 * @param input		要加密的字符串
	 * @return
	 */
	public static String encryptBySHA(String input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes());

			byte byteData[] = md.digest();

			// 二进制转换为十六进制
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(encryptByMD5("123456"));
		System.out.println(encryptByMD5Salt("673565"));
		System.out.println(encryptByMD5Salt("659865"));
		System.out.println(encryptByMD5Salt("678865"));
		System.out.println(encryptByMD5Salt("675465"));
		System.out.println(encryptByMD5Salt("678986"));
		System.out.println(encryptByMD5Salt("676545"));
		System.out.println(encryptByMD5Salt("609765"));
		System.out.println(encryptByMD5Salt("749865"));
		System.out.println(encryptByMD5Salt("672955"));
		System.out.println(encryptByMD5Salt("667585"));
		System.out.println(encryptByMD5Salt("609465"));
	}
	
}
