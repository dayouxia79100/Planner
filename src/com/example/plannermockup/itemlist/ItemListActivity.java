package com.example.plannermockup.itemlist;

import java.util.ArrayList;

import com.example.plannermockup.SingleFragmentActivity;
import com.example.plannermockup.model.Item;
import com.example.plannermockup.model.User;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class ItemListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        ItemListFragment itemListFragment = new ItemListFragment();
        ArrayList<Item> itemlist = (ArrayList<Item>) getIntent().getSerializableExtra("itemlist");
        if (itemlist != null) {
			itemListFragment.setItemList(itemlist);
			Log.d("itemlist", itemlist.toString());
		}
        return itemListFragment;
    }
}

