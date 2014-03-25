package com.example.plannermockup.addevent;

import com.example.plannermockup.SingleFragmentActivity;

import android.support.v4.app.Fragment;

public class AddNewEventActivity extends SingleFragmentActivity{
	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new AddNewEventFragment();
	}

}
