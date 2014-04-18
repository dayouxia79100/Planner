package com.example.yoloswag.app.model;

import java.io.Serializable;

import android.R.integer;

public class Item implements Serializable{


	private static final long serialVersionUID = 1968646824181506979L;
	
	private int iid;
	private String itemName;
	private int quantity;
	private User user;
	
	public Item() {
		
	}
	
	public Item(int _iid, String _itemName, int _quantity, User _user) {
		iid = _iid;
		itemName = _itemName;
		quantity = _quantity;
		user = _user;
	}
	
	public void setIid(int _iid) {
		iid = _iid;
	}
	
	public int getIid() {
		return iid;
	}
	
	public void setItemName(String _itemName) {
		itemName = _itemName;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setQuantity(int _quantity) {
		quantity = _quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setUser(User _user) {
		user = _user;
	}
	
	public User getUser() {
		return user;
	}
}
