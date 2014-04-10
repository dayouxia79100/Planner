package com.example.plannermockup.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.plannermockup.DBConnectActivity;
import com.example.plannermockup.R;
import com.example.plannermockup.schedepagertab.SchedulePagerTabActivity;

/**
 * Created by dayouxia on 2/13/14.
 */
public class EventsSingleton {

    private static EventsSingleton sEvents;

    private ArrayList<Event> myScheduleEvents;
    private ArrayList<Event> invitedEvents;
    private ArrayList<Event> hostingEvents;

    private int uid;
    
    private static String processing_message = "Loading...";
    private static String url_get_invited_events = DBConnectActivity.connect_url_header + "get_all_events.php";
    private static final String TAG_UID = "uid";
    private static final String TAG_INVITED_EVENTS = "invited_events";
    private static final String TAG_HOSTING_EVENTS = "hosting_events";
    private static final String TAG_EID = "eid";
    private static final String TAG_EVENT_NAME = "eventname";
    private static final String TAG_HOSTID = "hostid";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_TIME = "time";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_ALLOW_GUEST_INVITE = "allowguestinvite";
    private static final String TAG_STATUS = "status";

    private EventsSingleton (){
    	uid = User.getUser().getUid();
        myScheduleEvents = new ArrayList<Event>();
        invitedEvents = new ArrayList<Event>();
        hostingEvents = new ArrayList<Event>();
    }

    public static EventsSingleton get(){
        if(sEvents == null){
            sEvents = new EventsSingleton();
        }
        return sEvents;
    }
    
    public void setInvitedEvents(ArrayList<Event> events) {
    	this.invitedEvents = events;
    }
    
    public void setHostingEvents(ArrayList<Event> events) {
    	this.hostingEvents = events;
    }
    
    public void loadEvents(FragmentActivity mActivity, final FragmentActivity nextActivity) {
    	invitedEvents.clear();
    	hostingEvents.clear();
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	Log.d("uid in events", Integer.toString(uid));
        params.add(new BasicNameValuePair(TAG_UID, Integer.toString(uid)));
    	DBConnectActivity dbConnect = new DBConnectActivity(mActivity, params, url_get_invited_events, "GET", processing_message) {
    		@Override
    		public void handleSuccessResponse() {
    			// successfully found user
    			JSONObject json = this.getJsonObject();
    			try {
					JSONArray invited_events = json.getJSONArray(TAG_INVITED_EVENTS);
					JSONArray hosting_events = json.getJSONArray(TAG_HOSTING_EVENTS);
					
					for (int i = 0; i < invited_events.length(); i++) {
						JSONObject c = invited_events.getJSONObject(i);

						// Storing each json item in variable
						int eid = Integer.parseInt(c.getString(TAG_EID));
						String eventName = c.getString(TAG_EVENT_NAME);
						int hostid = Integer.parseInt(c.getString(TAG_HOSTID));
						String address = c.getString(TAG_ADDRESS);
						String time = c.getString(TAG_TIME);
						String description = c.getString(TAG_DESCRIPTION);
						boolean allowguestinvite = false;
						if (Integer.parseInt(c.getString(TAG_ALLOW_GUEST_INVITE)) != 0)
							allowguestinvite = true;
						int status = Integer.parseInt(c.getString(TAG_STATUS));
						
						Event event = new Event(eid, eventName, hostid, address, time, description, allowguestinvite, status);

						// adding HashList to ArrayList
						invitedEvents.add(event);
					}
					for (int i = 0; i < hosting_events.length(); i++) {
						JSONObject c = hosting_events.getJSONObject(i);

						// Storing each json item in variable
						int eid = Integer.parseInt(c.getString(TAG_EID));
						String eventName = c.getString(TAG_EVENT_NAME);
						int hostid = Integer.parseInt(c.getString(TAG_HOSTID));
						String address = c.getString(TAG_ADDRESS);
						String time = c.getString(TAG_TIME);
						String description = c.getString(TAG_DESCRIPTION);
						boolean allowguestinvite = false;
						if (Integer.parseInt(c.getString(TAG_ALLOW_GUEST_INVITE)) != 0)
							allowguestinvite = true;
						int status = Integer.parseInt(c.getString(TAG_STATUS));
						
						Event event = new Event(eid, eventName, hostid, address, time, description, allowguestinvite, status);

						// adding HashList to ArrayList
						hostingEvents.add(event);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
    		}
    		
    		@Override
    		protected void onPostExecute(String file_url) {
    			Intent i = new Intent(this.currentActivity, nextActivity.getClass());
				this.currentActivity.startActivity(i);

				// closing this screen
				this.currentActivity.finish();
    			super.onPostExecute(file_url);
    		}
    	};
    	dbConnect.execute();
    }
    
    public void loadEvents(FragmentActivity mActivity) {
    	invitedEvents.clear();
    	hostingEvents.clear();
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	Log.d("uid in events", Integer.toString(uid));
        params.add(new BasicNameValuePair(TAG_UID, Integer.toString(uid)));
    	DBConnectActivity dbConnect = new DBConnectActivity(mActivity, params, url_get_invited_events, "GET", processing_message) {
    		@Override
    		public void handleSuccessResponse() {
    			// successfully found user
    			JSONObject json = this.getJsonObject();
    			try {
					JSONArray invited_events = json.getJSONArray(TAG_INVITED_EVENTS);
					JSONArray hosting_events = json.getJSONArray(TAG_HOSTING_EVENTS);
					
					for (int i = 0; i < invited_events.length(); i++) {
						JSONObject c = invited_events.getJSONObject(i);

						// Storing each json item in variable
						int eid = Integer.parseInt(c.getString(TAG_EID));
						String eventName = c.getString(TAG_EVENT_NAME);
						int hostid = Integer.parseInt(c.getString(TAG_HOSTID));
						String address = c.getString(TAG_ADDRESS);
						String time = c.getString(TAG_TIME);
						String description = c.getString(TAG_DESCRIPTION);
						boolean allowguestinvite = false;
						if (Integer.parseInt(c.getString(TAG_ALLOW_GUEST_INVITE)) != 0)
							allowguestinvite = true;
						int status = Integer.parseInt(c.getString(TAG_STATUS));
						
						Event event = new Event(eid, eventName, hostid, address, time, description, allowguestinvite, status);

						// adding HashList to ArrayList
						invitedEvents.add(event);
					}
					for (int i = 0; i < hosting_events.length(); i++) {
						JSONObject c = hosting_events.getJSONObject(i);

						// Storing each json item in variable
						int eid = Integer.parseInt(c.getString(TAG_EID));
						String eventName = c.getString(TAG_EVENT_NAME);
						int hostid = Integer.parseInt(c.getString(TAG_HOSTID));
						String address = c.getString(TAG_ADDRESS);
						String time = c.getString(TAG_TIME);
						String description = c.getString(TAG_DESCRIPTION);
						boolean allowguestinvite = false;
						if (Integer.parseInt(c.getString(TAG_ALLOW_GUEST_INVITE)) != 0)
							allowguestinvite = true;
						int status = Integer.parseInt(c.getString(TAG_STATUS));
						
						Event event = new Event(eid, eventName, hostid, address, time, description, allowguestinvite, status);

						// adding HashList to ArrayList
						hostingEvents.add(event);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
    		}
    	};
    	dbConnect.execute();
    }
    
    public ArrayList<Event> getEventList(int position){
        switch (position){
            case 0:
                return myScheduleEvents;
            case 1:
                return invitedEvents;
            case 2:
                return hostingEvents;
            default:
                return null;
        }

    }
}
