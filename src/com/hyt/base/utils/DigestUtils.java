package com.hyt.base.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Title: DigestUtils 加解密相關Utils
 * Description: 
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/29
 */
public class DigestUtils {
	/**
	 * 將資料透過MD5雜湊加密
	 * @param content
	 * @return
	 */
	public static String md5Hex(byte[] content) {
		MessageDigest md5;
		String digest = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			digest = bytesToHex(md5.digest(content));
		} catch (NoSuchAlgorithmException e) {
		}
		return digest;
	}

	/**
	 * 將資料透過SHA雜湊加密
	 * @param content
	 * @return
	 */
	public static String shaHex(byte[] content) {
		MessageDigest sha;
		String digest = null;
		try {
			sha = MessageDigest.getInstance("SHA");
			digest = bytesToHex(sha.digest(content));
		} catch (NoSuchAlgorithmException e) {
		}
		return digest;
	}
	
	/**
	 * 將資料透過SHA-256雜湊加密
	 * @param content
	 * @return
	 */
	public static byte[] sha256(String content) {
		return sha256(content.getBytes());
	}
	
	/**
	 * 將資料透過SHA-256雜湊加密
	 * @param content
	 * @return
	 */
	public static byte[] sha256(byte[] content) {
		MessageDigest sha;
		byte[] digest = null;
		try {
			sha = MessageDigest.getInstance("SHA-256");
			digest = sha.digest(content);
		} catch (NoSuchAlgorithmException e) {
		}
		return digest;
	}

	/**
	 * 將byte array轉成Hex的字串
	 * @param b
	 * @return
	 */
	private static String bytesToHex(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; ++i) {
			sb.append((Integer.toHexString((b[i] & 0xFF) | 0x100)).substring(1, 3));
		}
		return sb.toString();
	}
}
