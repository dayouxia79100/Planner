package com.example.yoloswag.app.model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5175438735946162373L;
	private int uid;
	private String name;
	private String email;
	private String phone;
	
	public User() {}
	
	public User(int uid, String name, String email, String phone) {
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.phone = phone;
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
