package com.example.yoloswag.app.eventlist;

import android.support.v4.app.Fragment;

import com.example.yoloswag.app.SingleFragmentActivity;


public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return ListFragmentYo1.newInstance(1);
    }
}
