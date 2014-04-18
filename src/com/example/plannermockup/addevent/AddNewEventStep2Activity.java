package com.example.plannermockup.addevent;

import com.example.plannermockup.SingleFragmentActivity;
import com.example.plannermockup.model.Event;

import android.support.v4.app.Fragment;
import android.util.Log;

public class AddNewEventStep2Activity extends SingleFragmentActivity{
	@Override
	protected Fragment createFragment() {
		AddNewEventStep2Fragment step2Fragment = new AddNewEventStep2Fragment();
		Event event = (Event) getIntent().getSerializableExtra("event");
		if (event != null) step2Fragment.setEvent(event);
		return step2Fragment;
	}

}
