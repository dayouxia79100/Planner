package com.example.plannermockup;

import android.support.v4.app.Fragment;

/**
 * Created by dayouxia on 2/14/14.
 */
public class EventDetailActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new EventDetailFragment();
    }
}
