package com.example.yoloswag.app.eventdetail;

import com.example.yoloswag.app.SingleFragmentActivity;

import android.support.v4.app.Fragment;


public class EventDetailActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EventDetailFragment();
    }
}
