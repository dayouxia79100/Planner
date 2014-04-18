package com.example.plannermockup.guestlist;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.plannermockup.SingleFragmentActivity;
import com.example.plannermockup.model.Event;
import com.example.plannermockup.model.User;

/**
 * Created by dayouxia on 3/26/14.
 */
public class GuestListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
    	GuestListFragment guestListFragment = new GuestListFragment();
		ArrayList<User> guestlist = (ArrayList<User>) getIntent().getSerializableExtra("guestlist");
		if (guestlist != null) guestListFragment.setGuestList(guestlist);
        return guestListFragment;
    }
}
