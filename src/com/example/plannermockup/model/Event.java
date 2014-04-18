package com.example.plannermockup.model;

import java.io.Serializable;



public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7258590786785197404L;
	private int eid;
	private String eventname;
	private int hostid;
	private String address;
	private String time;
	private String description;
	private boolean allowGuestInvite;
	private int status;
	
	public Event() {
		
	}
	
	public Event(String eventname, int hostid, String address, String time, String description, boolean allowGuestInvite) { 
		this.eventname = eventname;
		this.hostid = hostid;
		this.address = address;
		this.time = time;
		this.description = description;
		this.allowGuestInvite = allowGuestInvite;
	}
	
	public Event(int eid, String eventname, int hostid, String address, String time, String description, boolean allowGuestInvite) { 
		this.eid = eid;
		this.eventname = eventname;
		this.hostid = hostid;
		this.address = address;
		this.time = time;
		this.description = description;
		this.allowGuestInvite = allowGuestInvite;
	}
	
	public Event(int eid, String eventname, int hostid, String address, String time, String description, boolean allowGuestInvite, int status) { 
		this.eid = eid;
		this.eventname = eventname;
		this.hostid = hostid;
		this.address = address;
		this.time = time;
		this.description = description;
		this.allowGuestInvite = allowGuestInvite;
		this.status = status;
	}

	public int getEid() {
		return this.eid;
	}
	
	public void setEid(int eid) {
		this.eid = eid;
	}
	
	public String getEventName() {
		return eventname;
	}
	
	public void setEventName(String eventName) {
		this.eventname = eventName;
	}
	
	public int getHostId() {
		return this.hostid;
	}
	
	public void setHostId(int hostId) {
		this.hostid = hostId;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTime() {
		return this.time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getAllowGuestInvite() {
		return this.allowGuestInvite;
	}
	
	public void setAllowGuestInivte(boolean allow) {
		this.allowGuestInvite = allow;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
}
