package com.example.plannermockup.guestlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.plannermockup.model.FriendsSingleton;
import com.example.plannermockup.model.MyUser;
import com.example.plannermockup.model.User;

import java.util.ArrayList;

/**
 * Created by dayouxia on 3/26/14.
 */
public class GuestListFragment extends ListFragment {

    private ArrayList<User> mGuestList = new ArrayList<User>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        setListAdapter(new WhosGoingAdapter(mGuestList));
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

    private class WhosGoingAdapter extends ArrayAdapter<User> {
        public WhosGoingAdapter(ArrayList<User> names){
            super(getActivity(),0, names);
        }


        @Override
        public int getCount() {
            return mGuestList.size();

        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_guest,null);

            }

            TextView guestNameTextView = (TextView)convertView.findViewById(R.id.guest_name);
            guestNameTextView.setText(mGuestList.get(position).getName());
            TextView guestEmailTextView = (TextView)convertView.findViewById(R.id.guest_email);
            guestEmailTextView.setText(mGuestList.get(position).getEmail());
            /*Button addFriendButton = (Button)convertView.findViewById(R.id.send_friend_request_to_guest_button);
            addFriendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                	new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.send_friend_request_title)
                    .setMessage(R.string.send_friend_request_message)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            FriendsSingleton.get().sendFriendRequest(getActivity(), mGuestList.get(position).getUid());
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .show();
                }
            });*/
            return convertView;
        }
    }
    
    public void setGuestList(ArrayList<User> guestlist) {
    	mGuestList = guestlist;
    }
}
