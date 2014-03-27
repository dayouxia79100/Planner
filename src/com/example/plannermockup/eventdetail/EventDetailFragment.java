package com.example.plannermockup.eventdetail;

import com.example.plannermockup.R;
import com.example.plannermockup.R.layout;
import com.example.plannermockup.whosgoing.WhosGoingActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by dayouxia on 2/14/14.
 */
public class EventDetailFragment extends Fragment {


    private GoogleMap googleMap;
    private Button mWhosGoingButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(EventDetailFragment.class.toString(),"onCreateView is called");
        View v = inflater.inflate(R.layout.fragment_event_detail,container,false);

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

            // Showing / hiding your current location
            googleMap.setMyLocationEnabled(true);

            // Enable / Disable zooming controls
            googleMap.getUiSettings().setZoomControlsEnabled(false);

            // Enable / Disable my location button
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            // Enable / Disable Compass icon
            googleMap.getUiSettings().setCompassEnabled(true);

            // Enable / Disable Rotate gesture
            googleMap.getUiSettings().setRotateGesturesEnabled(true);

            // Enable / Disable zooming functionality
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
        }

        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(39.9833, -82.9833))
                .title("YOLO SWAG");


        googleMap.addMarker(marker);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(marker.getPosition()).zoom(15).build();

        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));





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
}
