package com.example.yoloswag.app.addnewevent2;


import com.example.yoloswag.app.R;
import com.example.yoloswag.app.login.LoginFragment;
import com.example.yoloswag.app.model.CurrentEventSession;
import com.example.yoloswag.app.model.Event;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.timessquare.CalendarPickerView;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//by summer

public class AddNewEventStep1Fragment extends Fragment{
	
	private Button nextButton;
	private EditText inputEventName;
	private EditText inputEventDescription;
	private EditText inputEventTime;
	private EditText inputEventAddress;
	private CheckBox inputAllowGuestInvite;
    private Event mCurrentEvent;

    private Location mLocation;

    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private UiSettings mUiSettings;
    private LatLng mMarkerPosition = new LatLng(40.0000,-83.0145);
    private TimePicker mTimePicker;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentEvent = CurrentEventSession.get().getEvent();

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

    Time time;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        Log.v(LoginFragment.class.toString(), "onCreateView is called");
        View v = inflater.inflate(R.layout.fragment_add_event_1_summer, container, false);

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
        Time picker is here.
         */

        mTimePicker = (TimePicker)v.findViewById(R.id.time_picker);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                 time = new Time(i,i2,0);
                mCurrentEvent.setClockTime(time.toString());
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


        /*
        event name starts here
         */



        inputEventName = (EditText) v.findViewById(R.id.event_name_editText);
        inputEventName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    mCurrentEvent.setEventName(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        /*
        address starts here
         */

            inputEventAddress = (EditText) v.findViewById(R.id.event_address_editText);
            inputEventAddress.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    mCurrentEvent.setActualAddress(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        /*
        description starts here
         */

            inputEventDescription = (EditText) v.findViewById(R.id.event_description_editText);
            inputEventDescription.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    ;
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    mCurrentEvent.setDescription(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


        /*
        allow guest invite starts here
         */


            inputAllowGuestInvite = (CheckBox) v.findViewById(R.id.allow_guest_invite_checkBox);
            inputAllowGuestInvite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    mCurrentEvent.setAllowGuestInivte(b);
                }
            });

            return v;
        }






}
