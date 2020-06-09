package com.example.demo.utile;

import java.io.UnsupportedEncodingException;

public class StringUtile {
	public static void checkEncoding(String checkStr) {
		String[] charSet = {"utf-8", "euc-kr", "ksc5601", "iso-8859-1", "x-windows-949"};
		
		for (int i = 0; i < charSet.length; i++) {
			for (int j = 0; j < charSet.length; j++) {
				try {
					System.out.println("[" + charSet[i] + "," + charSet[j] + "] = "
										+ new String (checkStr.getBytes(charSet[i]), charSet[j]));
					
				}catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String changeEncoding(String str, String fromEncoding, String toEncoding) {
		try {
			str = new String (str.getBytes(fromEncoding), toEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
}
