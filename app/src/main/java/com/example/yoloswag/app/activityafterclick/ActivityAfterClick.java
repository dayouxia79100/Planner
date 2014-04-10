package com.example.yoloswag.app.activityafterclick;

import android.support.v4.app.Fragment;

import com.example.yoloswag.app.SingleFragmentActivity;

/**
 * Created by dayouxia on 3/30/14.
 */
public class ActivityAfterClick extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ActivityAfterFragment();
    }
}
