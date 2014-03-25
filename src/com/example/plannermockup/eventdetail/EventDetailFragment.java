package com.example.plannermockup.eventdetail;

import com.example.plannermockup.R;
import com.example.plannermockup.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dayouxia on 2/14/14.
 */
public class EventDetailFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(EventDetailFragment.class.toString(),"onCreateView is called");
        View v = inflater.inflate(R.layout.fragment_event_detail,container,false);
        return v;
    }
}
