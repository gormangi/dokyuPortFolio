package com.dokyuportfolio.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SHA256 {

	public static String getEncrypt(String source, String key) {
		return getEncrypt(source, key.getBytes());
	}
	
	public static String getEncrypt(String source, byte[] key) {
		
		String result = "";
		
		byte[] a = source.getBytes();
		byte[] bytes = new byte[a.length + key.length];
		
		System.arraycopy(a, 0, bytes, 0, a.length);
		System.arraycopy(key, 0, bytes, a.length, key.length);
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytes);
			
			byte[] byteData = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < byteData.length; i++){
				sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
			}
			
			result = sb.toString();
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String generateKey() {
		Random random = new Random();
		
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < salt.length; i++){
			sb.append(String.format("%02x",salt[i]));
		}
		
		return sb.toString();
	}
}
