package com.example.plannermockup.whosgoing;

import android.support.v4.app.Fragment;

import com.example.plannermockup.SingleFragmentActivity;

/**
 * Created by dayouxia on 3/26/14.
 */
public class WhosGoingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new WhosGoingFragment();
    }
}
