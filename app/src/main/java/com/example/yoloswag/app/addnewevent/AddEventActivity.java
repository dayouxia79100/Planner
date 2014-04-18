package com.example.yoloswag.app.addnewevent;

import android.support.v4.app.Fragment;

import com.example.yoloswag.app.SingleFragmentActivity;

/**
 * Created by dayouxia on 4/10/14.
 */
public class AddEventActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new AddEventFragment();
    }
}
