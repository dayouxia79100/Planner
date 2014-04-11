package com.example.plannermockup.guestlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.plannermockup.AddCalendarDialogFragment;
import com.example.plannermockup.R;
import com.example.plannermockup.model.Event;
import com.example.plannermockup.model.User;

import java.util.ArrayList;

/**
 * Created by dayouxia on 3/26/14.
 */
public class GuestListFragment extends ListFragment {

    private ArrayList<User> mGuestList = new ArrayList<User>();
    private ArrayList<String> mGuestName = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        setListAdapter(new WhosGoingAdapter(mGuestName));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class WhosGoingAdapter extends ArrayAdapter<String> {
        public WhosGoingAdapter(ArrayList<String> names){
            super(getActivity(),0, names);
        }


        @Override
        public int getCount() {
            return mGuestList.size();

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(android.R.layout.simple_list_item_1,null);

            }

            TextView textView = (TextView)convertView.findViewById(android.R.id.text1);
            textView.setText(mGuestName.get(position));


            return convertView;
        }


    }
    
    public void setGuestList(ArrayList<User> guestlist) {
    	mGuestList = guestlist;
    	mGuestName = new ArrayList<String>();
    	for (int i = 0; i < guestlist.size(); i++) {
    		mGuestName.add(guestlist.get(i).getName());
    	}
    	Log.d("guest name", mGuestName.toString());
    }
}
