package com.example.plannermockup.model;

/**
 * Created by dayouxia on 2/13/14.
 */
public class Event {
    private String mEventname;
    private String mAddress;
    private boolean mGoing;
    private String mTime;

    public String getEventname() {
        return mEventname;
    }

    public void setEventname(String eventname) {
        mEventname = eventname;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public boolean isGoing() {
        return mGoing;
    }

    public void setGoing(boolean going) {
        mGoing = going;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }
}
