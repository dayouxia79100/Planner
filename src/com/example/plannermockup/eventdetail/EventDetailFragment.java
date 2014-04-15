package com.example.plannermockup.eventdetail;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.plannermockup.DBConnectActivity;
import com.example.plannermockup.R;
import com.example.plannermockup.R.layout;
import com.example.plannermockup.R.string;
import com.example.plannermockup.guestlist.GuestListActivity;
import com.example.plannermockup.itemlist.ItemListActivity;
import com.example.plannermockup.model.Event;
import com.example.plannermockup.model.EventsSingleton;
import com.example.plannermockup.model.Item;
import com.example.plannermockup.model.MyUser;
import com.example.plannermockup.model.User;
import com.example.plannermockup.schedepagertab.SchedulePagerTabActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Debug;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dayouxia on 2/14/14.
 */
public class EventDetailFragment extends Fragment {
	private Event mEvent;
	
	private User hostUser;
	
	private TextView eventNameTextView;
	private TextView eventTimeTextView; 
	private TextView hostNameTextView;
	private TextView addressTextView;
	private TextView descriptionTextView;
	
    private Button mWhosGoingButton;
    private Button mThingsToBring;
    
    private static String processing_message = "Loading...";
    private static String url_get_guest_list = DBConnectActivity.connect_url_header + "get_guest_list.php";
    private static String url_get_item_list = DBConnectActivity.connect_url_header + "get_item_list.php";
    private static final String TAG_GUEST_LIST = "guestlist";
    private static final String TAG_ITEM_LIST = "itemlist";
    private static final String TAG_UID = "uid";
    private static final String TAG_EID = "eid";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_IID = "iid";
    private static final String TAG_QUANTITY = "quantity";
    private static final String TAG_ITEM_NAME = "itemname";
    
    private ArrayList<User> guestList;
    private ArrayList<Item> itemList;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_detail, container, false);

        eventNameTextView = (TextView)v.findViewById(R.id.event_title_textview);
        eventTimeTextView = (TextView)v.findViewById(R.id.event_time_textview);
        hostNameTextView = (TextView)v.findViewById(R.id.host_name_textview);
        addressTextView = (TextView)v.findViewById(R.id.address_textview);
        descriptionTextView = (TextView)v.findViewById(R.id.description_textview);
        
        eventNameTextView.setText(mEvent.getEventName());
        eventTimeTextView.setText(mEvent.getTime());
        hostNameTextView.setText(hostUser.getName());
        addressTextView.setText(mEvent.getAddress());
        descriptionTextView.setText(mEvent.getDescription());
        
        mWhosGoingButton = (Button) v.findViewById(R.id.whos_going_button);
        mWhosGoingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	handleWhosGoing();
            }
        });
        
        mThingsToBring = (Button) v.findViewById(R.id.things_to_bring_button);
        mThingsToBring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	handleThingsToBring();
            }
        });
        
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        setRetainInstance(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void setEvent(Event event) {
    	this.mEvent = event;
    }

	public void setHost(User host) {
    	this.hostUser = host;
	}
	
	private void handleWhosGoing() {
		guestList = new ArrayList<User>();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(TAG_EID, Integer.toString(mEvent.getEid())));
    	DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_get_guest_list, "GET", processing_message) {
    		@Override
    		public void handleSuccessResponse() {
    			// successfully found user
    			JSONObject json = this.getJsonObject();
    			try {
					JSONArray invited_events = json.getJSONArray(TAG_GUEST_LIST);
					
					for (int i = 0; i < invited_events.length(); i++) {
						JSONObject c = invited_events.getJSONObject(i);

						// Storing each json item in variable
						int uid = Integer.parseInt(c.getString(TAG_UID));
						String name = c.getString(TAG_NAME);
						String email = c.getString(TAG_EMAIL);
						String phone = c.getString(TAG_PHONE);
						
						User mUser = new User(uid, name, email, phone);

						// adding HashList to ArrayList
						guestList.add(mUser);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
    		}
    		
    		@Override
    		protected void onPostExecute(String file_url) {
    			Intent i = new Intent(this.currentActivity, GuestListActivity.class);
				i.putExtra("guestlist", guestList);
				this.currentActivity.startActivity(i);
    			super.onPostExecute(file_url);
    		}
    	};
    	dbConnect.execute();
	}
	
	private void handleThingsToBring() {
		itemList = new ArrayList<Item>();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(TAG_EID, Integer.toString(mEvent.getEid())));
    	DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_get_item_list, "GET", processing_message) {
    		@Override
    		public void handleSuccessResponse() {
    			// successfully found user
    			JSONObject json = this.getJsonObject();
    			try {
					JSONArray invited_events = json.getJSONArray(TAG_ITEM_LIST);
					
					for (int i = 0; i < invited_events.length(); i++) {
						JSONObject c = invited_events.getJSONObject(i);
						// Storing each json item in variable
						
						int iid = Integer.parseInt(c.getString(TAG_IID));
						String itemName = c.getString(TAG_ITEM_NAME);
						int quantity = Integer.parseInt(c.getString(TAG_QUANTITY));
						User mUser = new User();
						if (c.getString(TAG_UID) != "-1") {
							int uid = Integer.parseInt(c.getString(TAG_UID));
							String name = c.getString(TAG_NAME);
							String email = c.getString(TAG_EMAIL);
							String phone = c.getString(TAG_PHONE);
							mUser = new User(uid, name, email, phone);
						}
						Item mItem = new Item(iid, itemName, quantity, mUser);
						// adding HashList to ArrayList
						itemList.add(mItem);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
    		}
    		
    		@Override
    		protected void onPostExecute(String file_url) {
    			Intent i = new Intent(getActivity(), ItemListActivity.class);
				i.putExtra("itemlist", itemList);
				getActivity().startActivity(i);
    			super.onPostExecute(file_url);
    		}
    	};
    	dbConnect.execute();
	}
}
