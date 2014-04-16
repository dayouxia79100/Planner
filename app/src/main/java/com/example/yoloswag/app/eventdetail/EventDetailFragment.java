package com.example.yoloswag.app.eventdetail;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.eventlist.ListFragmentYo3;
import com.example.yoloswag.app.model.Event;
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

/**
 * Created by dayouxia on 2/14/14.
 */
public class EventDetailFragment extends Fragment {



    private GoogleMap googleMap;
    private Button mWhosGoingButton;
    private UiSettings mUiSettings;
    private Event mEvent;


    private TextView mEventTextView;
    private TextView mTimeTextView;
    private TextView mDescriptionTextView;
    private TextView mAddress;
    private TextView mHostName;


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
                Intent i = new Intent(getActivity(), WhosGoingActivity.class);
                startActivity(i);
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
