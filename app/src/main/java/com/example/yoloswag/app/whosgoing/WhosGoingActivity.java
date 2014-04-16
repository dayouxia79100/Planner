package com.example.yoloswag.app.whosgoing;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.yoloswag.app.SingleFragmentActivity;
import com.example.yoloswag.app.model.User;

/**
 * Created by dayouxia on 3/26/14.
 */
public class WhosGoingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
    	WhosGoingFragment guestListFragment = new WhosGoingFragment();
		ArrayList<User> guestlist = (ArrayList<User>) getIntent().getSerializableExtra("guestlist");
		if (guestlist != null) guestListFragment.setGuestList(guestlist);
        return guestListFragment;
    }
}
