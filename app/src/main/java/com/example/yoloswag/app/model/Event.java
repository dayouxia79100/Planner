package com.example.yoloswag.app.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;



public class Event implements Serializable {


	private static final long serialVersionUID = 7258590786785197404L;
	private int eid;
	private String eventname;
	private int hostid;
	private String address;
	private String time;
    private String clockTime;
    private String calendarTime;

	private String description;
	private boolean allowGuestInvite;
	private int status;
    private LatLng markerPosition;

    // note address is (address + LatLng)
    private String actualAddress;
    public void convertTime(){
        time = calendarTime + clockTime;
    }



    public void convertAddress(){
        address = actualAddress + "$"+markerPosition.latitude + "$"+markerPosition.longitude;
    }

    public void decomposeAddress(){
        actualAddress = address.substring(0,address.indexOf('$'));
        String lat = address.substring(address.indexOf('$')+1,address.lastIndexOf('$'));
        String lng = address.substring(address.lastIndexOf('$')+1, address.length());
        markerPosition = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
    }
	
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

    public LatLng getMarkerPosition() {
        return markerPosition;
    }

    public void setMarkerPosition(LatLng markerPosition) {
        this.markerPosition = markerPosition;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
    }

    public String getCalendarTime() {
        return calendarTime;
    }

    public void setCalendarTime(String calendarTime) {
        this.calendarTime = calendarTime;
    }

    public String getClockTime() {
        return clockTime;
    }

    public void setClockTime(String clockTime) {
        this.clockTime = clockTime;
    }
}
