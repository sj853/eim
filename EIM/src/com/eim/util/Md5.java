package com.eim.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

	public String str;

	public String encode(String password) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte b[] = md.digest();

			int i;

			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
			System.out.println("result: " + buf.toString());// 32位的加密
			System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		return str;
	}
	
	
	public boolean check(String password){
		String code = encode(password);
		PropertiesUtil pu = new PropertiesUtil("admin.properties");
		String realPw = pu.read("password");
		if(code.equals(realPw)){
			return true;
		}
		else{
			return false;
		}
	}
	
	

}
