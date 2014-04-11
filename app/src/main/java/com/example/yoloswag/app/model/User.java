package com.example.yoloswag.app.model;

public class User {
	private static User user;
	private int uid;
	private String name;
	private String email;
	private String phone;
	
	private User() {
		
	}
	
	public static User getUser() {
		if (user == null) 
			user = new User();
		return user;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getUid() {
		return this.uid;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return this.phone;
	}
}
