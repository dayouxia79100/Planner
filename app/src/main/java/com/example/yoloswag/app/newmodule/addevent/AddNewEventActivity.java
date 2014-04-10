package com.example.yoloswag.app.newmodule.addevent;


import android.support.v4.app.Fragment;

import com.example.yoloswag.app.SingleFragmentActivity;

public class AddNewEventActivity extends SingleFragmentActivity{
	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new AddNewEventFragment();
	}

}
