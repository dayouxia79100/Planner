package com.example.yoloswag.app.addnewevent;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.model.CurrentEventSession;
import com.example.yoloswag.app.model.Event;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddNewEventStep2Fragment extends Fragment {


    private Event mCurrentEvent;

    private Location mLocation;

    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private UiSettings mUiSettings;
    private LatLng mMarkerPosition = new LatLng(40.0000,-83.0145);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentEvent = CurrentEventSession.get().getEvent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_event_2_summer,container,false);


        /*
        date picker is here.
         */
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        CalendarPickerView calendar = (CalendarPickerView) v.findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                String calendarTime = format.format(date);
                mCurrentEvent.setCalendarTime(calendarTime);
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });


        /*
        Google map starts here.
         */

        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        // Gets to GoogleMap from the MapView and does initialization stuff
        if (mMapView != null) {
            mGoogleMap = mMapView.getMap();
            mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
            mGoogleMap.setMyLocationEnabled(true);
        }

        mUiSettings = mGoogleMap.getUiSettings();
        mUiSettings.setCompassEnabled(false);
        mUiSettings.setRotateGesturesEnabled(false);
        mUiSettings.setTiltGesturesEnabled(false);
        mUiSettings.setZoomControlsEnabled(false);


        mLocation = mGoogleMap.getMyLocation();

        mGoogleMap.addMarker(new MarkerOptions()
                .position(mMarkerPosition)
                .title("Yo whats up"))
                .setDraggable(true);
        mCurrentEvent.setMarkerPosition(mMarkerPosition);
        mGoogleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                mMarkerPosition = marker.getPosition();
                mCurrentEvent.setMarkerPosition(mMarkerPosition);
            }
        });



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
