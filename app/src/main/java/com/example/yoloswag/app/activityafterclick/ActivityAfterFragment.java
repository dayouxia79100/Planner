package com.example.yoloswag.app.activityafterclick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoloswag.app.R;

/**
 * Created by dayouxia on 3/30/14.
 */
public class ActivityAfterFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main,container,false);
    }
}
