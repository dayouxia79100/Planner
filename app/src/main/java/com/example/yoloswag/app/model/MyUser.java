package com.example.yoloswag.app.model;

public class MyUser {
	private static User user;
	
	private MyUser() {
		
	}
	
	public static User getUser() {
		if (user == null) 
			user = new User();
		return user;
	}
}
