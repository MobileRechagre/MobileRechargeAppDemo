package com.sg.validator;

import java.net.MalformedURLException;
import java.net.URL;

public class Validator {

	public static boolean isEmptyText(String text){
		if (text == null)
			return true;
		else if (text != null && text.length() == 0)
			return true;
		else if (text.equals("null") || text == "")
			return true;

		return false;
	}

	public static boolean isValidURL(String urlStr) {
		try {
			URL url = new URL(urlStr);
			return true;
		}
		catch (MalformedURLException e) {
			return false;
		}
	}
	public static boolean isValidEmail(String emailStr) {
		boolean flag = false;
		String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		if (emailStr == null)
			flag = false;

		else if (emailStr.matches(emailPattern) && emailStr.length() > 0)
			flag = true;

		return flag;
	}
	public static boolean isValidLength(String text, int length){
		boolean flag = false;
		if (text == null)
			flag = false;
		else if (text.length() < length)
			flag = false;
		else 
			flag = true;

		return flag;
	}
}
