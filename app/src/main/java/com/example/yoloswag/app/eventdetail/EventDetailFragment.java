package com.example.yoloswag.app.eventdetail;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.eventlist.ListFragmentYo3;
import com.example.yoloswag.app.helper.DBConnectActivity;
import com.example.yoloswag.app.model.Event;
import com.example.yoloswag.app.model.EventsSingleton;
import com.example.yoloswag.app.model.Item;
import com.example.yoloswag.app.model.User;
import com.example.yoloswag.app.thingstobring.ThingsToBringActivity;
import com.example.yoloswag.app.whosgoing.WhosGoingActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dayouxia on 2/14/14.
 */
public class EventDetailFragment extends Fragment {



    private GoogleMap googleMap;
    private Button mWhosGoingButton;
    private Button mWhatToBringButton;
    private UiSettings mUiSettings;
    private Event mEvent;


    private TextView mEventTextView;
    private TextView mTimeTextView;
    private TextView mDescriptionTextView;
    private TextView mAddress;
    private TextView mHostName;



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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

        //here i need to get the event from a list, but i don't know the index of the card i clicked.
        mEvent = (Event)getActivity().getIntent().getSerializableExtra(ListFragmentYo3.CardExample2.EXTRA_EVENT);
        mEvent.decomposeAddress();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(EventDetailFragment.class.toString(),"onCreateView is called");
        View v = inflater.inflate(R.layout.fragment_event_detail,container,false);

        mEventTextView = (TextView)v.findViewById(R.id.event_title_textview);
        mTimeTextView = (TextView)v.findViewById(R.id.event_time_textview);
        mDescriptionTextView = (TextView)v.findViewById(R.id.event_description);
        mAddress = (TextView)v.findViewById(R.id.event_address_text);
        mHostName = (TextView)v.findViewById(R.id.event_host_name);

        mEventTextView.setText(mEvent.getEventName());
        mTimeTextView.setText(mEvent.getTime());
        mDescriptionTextView.setText(mEvent.getDescription());
        mAddress.setText(mEvent.getActualAddress());
        mHostName.setText(mEvent.getHostId() + "");


        mWhosGoingButton = (Button) v.findViewById(R.id.whos_going_button);
        mWhosGoingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<User> guestList = new ArrayList<User>();
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
                        Intent i = new Intent(this.currentActivity, WhosGoingActivity.class);
                        i.putExtra("guestlist", guestList);
                        this.currentActivity.startActivity(i);
                        super.onPostExecute(file_url);
                    }
                };
                dbConnect.execute();

            }
        });


        mWhatToBringButton = (Button)v.findViewById(R.id.what_to_bring_button);
        mWhatToBringButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<Item>itemList = new ArrayList<Item>();
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
                        Intent i = new Intent(getActivity(), ThingsToBringActivity.class);
                        i.putExtra("itemlist", itemList);
                        getActivity().startActivity(i);
                        super.onPostExecute(file_url);
                    }
                };
                dbConnect.execute();

            }
        });



        if(googleMap == null) {
            googleMap = ((SupportMapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
        }
        if(googleMap != null){
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
            mUiSettings = googleMap.getUiSettings();

            // Showing / hiding your current location
            googleMap.setMyLocationEnabled(true);

            // Enable / Disable zooming controls
            mUiSettings.setZoomControlsEnabled(false);

            // Enable / Disable my location button
            mUiSettings.setMyLocationButtonEnabled(true);

            // Enable / Disable Compass icon
            mUiSettings.setCompassEnabled(true);

            // Enable / Disable Rotate gesture
            mUiSettings.setRotateGesturesEnabled(true);

            // Enable / Disable zooming functionality
            mUiSettings.setZoomGesturesEnabled(true);
        }



        MarkerOptions marker = new MarkerOptions().position(
                mEvent.getMarkerPosition())
                .title("YOLO SWAG");


        googleMap.addMarker(marker);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(marker.getPosition()).zoom(15).build();

        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));


        return v;
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
}
