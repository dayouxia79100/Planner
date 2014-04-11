package com.example.plannermockup.eventdetail;

import com.example.plannermockup.SingleFragmentActivity;
import com.example.plannermockup.model.Event;
import com.example.plannermockup.model.User;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by dayouxia on 2/14/14.
 */
public class EventDetailActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
    	EventDetailFragment eventDetailFragment = new EventDetailFragment();
		Event event = (Event) getIntent().getSerializableExtra("event");
		if (event != null) eventDetailFragment.setEvent(event);
		User host = (User) getIntent().getSerializableExtra("host");
		if (host != null) eventDetailFragment.setHost(host);
        return eventDetailFragment;
    }
}
