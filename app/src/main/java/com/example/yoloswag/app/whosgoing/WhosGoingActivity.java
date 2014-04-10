package com.example.yoloswag.app.whosgoing;

import android.support.v4.app.Fragment;

import com.example.yoloswag.app.SingleFragmentActivity;

/**
 * Created by dayouxia on 3/26/14.
 */
public class WhosGoingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new WhosGoingFragment();
    }
}
