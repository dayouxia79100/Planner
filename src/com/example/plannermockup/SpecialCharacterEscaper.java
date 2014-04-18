package com.example.plannermockup;

public class SpecialCharacterEscaper {
	
	public static String quoteEscaper(String s) {
		String result = "";
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '\'' || s.charAt(i) == '\"') {
				result += "\\";
			}
			result += Character.toString(s.charAt(i));
		}
		
		return result;
	}
	
}
