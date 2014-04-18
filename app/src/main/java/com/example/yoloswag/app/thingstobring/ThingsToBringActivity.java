package com.example.yoloswag.app.thingstobring;

import java.util.ArrayList;

import com.example.yoloswag.app.SingleFragmentActivity;
import com.example.yoloswag.app.model.Item;

import android.support.v4.app.Fragment;
import android.util.Log;


public class ThingsToBringActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        ThingsToBringFragment thingsToBringFragment = new ThingsToBringFragment();
        ArrayList<Item> itemlist = (ArrayList<Item>) getIntent().getSerializableExtra("itemlist");
        if (itemlist != null) {
			thingsToBringFragment.setItemList(itemlist);
			Log.d("itemlist", itemlist.toString());
		}
        return thingsToBringFragment;
    }
}

