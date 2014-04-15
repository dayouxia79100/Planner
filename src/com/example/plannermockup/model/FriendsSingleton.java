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
import android.R.integer;
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
public class FriendsSingleton {
	
	public static final int PENDING_FRIEND = 0;
	public static final int CURRENT_FRIEND = 1;

    private static FriendsSingleton sFriends;

    private ArrayList<User> pendingFriends;
    private ArrayList<User> currentFriends;

    private int uid;
    
    private static String processing_message = "Loading...";
    private static String url_get_friends = DBConnectActivity.connect_url_header + "get_all_friends.php";
    private static String url_update_friend_status = DBConnectActivity.connect_url_header + "update_friend_status.php";
    private static final String TAG_UID = "uid";
    private static final String TAG_PENDINGFRIENDS = "pendingfriends";
    private static final String TAG_CURRENTFRIENDS = "currentfriends";
    private static final String TAG_FRIENDID = "friendid";
    private static final String TAG_FRIENDNAME = "friendname";
    private static final String TAG_FRIENDEMAIL = "friendemail";
    private static final String TAG_FRIENDPHONE = "friendphone";
    private static final String TAG_UPDATETYPE = "updatetype";

    private FriendsSingleton (){
    	uid = MyUser.getUser().getUid();
        pendingFriends = new ArrayList<User>();
        currentFriends = new ArrayList<User>();
    }

    public static FriendsSingleton get(){
        if(sFriends == null){
            sFriends = new FriendsSingleton();
        }
        return sFriends;
    }
    
    public void loadFriends(FragmentActivity mActivity, final FragmentActivity nextActivity) {
    	pendingFriends.clear();
    	currentFriends.clear();
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(TAG_UID, Integer.toString(uid)));
    	DBConnectActivity dbConnect = new DBConnectActivity(mActivity, params, url_get_friends, "GET", processing_message) {
    		@Override
    		public void handleSuccessResponse() {
    			// successfully found user
    			JSONObject json = this.getJsonObject();
    			try {
					JSONArray pending_friends = json.getJSONArray(TAG_PENDINGFRIENDS);
					JSONArray current_friends = json.getJSONArray(TAG_CURRENTFRIENDS);
					
					for (int i = 0; i < pending_friends.length(); i++) {
						JSONObject c = pending_friends.getJSONObject(i);

						// Storing each json item in variable
						int fuid = Integer.parseInt(c.getString(TAG_FRIENDID));
						String fname = c.getString(TAG_FRIENDNAME);
						String femail = c.getString(TAG_FRIENDEMAIL);
						String fphone = c.getString(TAG_FRIENDPHONE);
						User user = new User(fuid, fname, femail, fphone);
						// adding HashList to ArrayList
						pendingFriends.add(user);
					}
					for (int i = 0; i < current_friends.length(); i++) {
						JSONObject c = current_friends.getJSONObject(i);

						// Storing each json item in variable
						int uid = Integer.parseInt(c.getString(TAG_FRIENDID));
						String name = c.getString(TAG_FRIENDNAME);
						String email = c.getString(TAG_FRIENDEMAIL);
						String phone = c.getString(TAG_FRIENDPHONE);
						User user = new User(uid, name, email, phone);
						
						// adding HashList to ArrayList
						currentFriends.add(user);
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
    	pendingFriends.clear();
    	currentFriends.clear();
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(TAG_UID, Integer.toString(uid)));
    	DBConnectActivity dbConnect = new DBConnectActivity(mActivity, params, url_get_friends, "GET", processing_message) {
    		@Override
    		public void handleSuccessResponse() {
    			// successfully found user
    			JSONObject json = this.getJsonObject();
    			try {
					JSONArray pending_friends = json.getJSONArray(TAG_PENDINGFRIENDS);
					JSONArray current_friends = json.getJSONArray(TAG_CURRENTFRIENDS);
					
					for (int i = 0; i < pending_friends.length(); i++) {
						JSONObject c = pending_friends.getJSONObject(i);

						// Storing each json item in variable
						int uid = Integer.parseInt(c.getString(TAG_FRIENDID));
						String name = c.getString(TAG_FRIENDNAME);
						String email = c.getString(TAG_FRIENDEMAIL);
						String phone = c.getString(TAG_FRIENDPHONE);
						User user = new User(uid, name, email, phone);
						// adding HashList to ArrayList
						pendingFriends.add(user);
					}
					for (int i = 0; i < current_friends.length(); i++) {
						JSONObject c = current_friends.getJSONObject(i);

						// Storing each json item in variable
						int uid = Integer.parseInt(c.getString(TAG_FRIENDID));
						String name = c.getString(TAG_FRIENDNAME);
						String email = c.getString(TAG_FRIENDEMAIL);
						String phone = c.getString(TAG_FRIENDPHONE);
						User user = new User(uid, name, email, phone);
						
						// adding HashList to ArrayList
						currentFriends.add(user);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
    		}
    	};
    	dbConnect.execute();
    }
    
    public ArrayList<User> getEventList(int type){
        switch (type){
            case PENDING_FRIEND:
                return pendingFriends;
            case CURRENT_FRIEND:
                return currentFriends;
            default:
                return null;
        }
    }
    
    public void sendFriendRequest(FragmentActivity mActivity, int friendid) {
    	updateFriendStatus(mActivity, friendid, 0);
    }
    
    public void confirmFriendRequest(FragmentActivity mActivity, int friendid) {
    	updateFriendStatus(mActivity, friendid, 1);
    }
    
    public void removeFriend(FragmentActivity mActivity, int friendid) {
    	updateFriendStatus(mActivity, friendid, -1);
    }
    
    private void updateFriendStatus(FragmentActivity mActivity, int friendid, int update_type) {
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(TAG_UID, Integer.toString(uid)));
        params.add(new BasicNameValuePair(TAG_FRIENDID, Integer.toString(friendid)));
        params.add(new BasicNameValuePair(TAG_UPDATETYPE, Integer.toString(update_type)));
    	DBConnectActivity dbConnect = new DBConnectActivity(mActivity, params, url_update_friend_status, "POST", processing_message);
    	dbConnect.execute();
    }
}
