package com.example.plannermockup;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dayouxia on 2/13/14.
 */
public class ScheduleFragment extends ListFragment {

    private ArrayList<Event> mEventList;
    public static final String EXTRA_TAB_NUM = "tabnumber";

    public static ScheduleFragment newInstance(int position){
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_TAB_NUM,position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent i = new Intent(getActivity(),EventDetailActivity.class);
        startActivity(i);
        // to be implemented


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEventList = EventsSingleton.get().getEventList(getArguments().getInt(EXTRA_TAB_NUM));
        setListAdapter(new EventAdapter(mEventList));
    }



    private class EventAdapter extends ArrayAdapter<Event>{
        public EventAdapter(ArrayList<Event> events){
            super(getActivity(),0,events);
        }


        @Override
        public int getCount() {
            return mEventList.size();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.fragment_event_item,null);

            }

            Event currentEvent = getItem(position);
            TextView partyName = (TextView) convertView.findViewById(R.id.partyName_textView);
            partyName.setText(currentEvent.getEventname());

            TextView address = (TextView)convertView.findViewById(R.id.partyAddress_textView);
            address.setText(currentEvent.getAddress());

            TextView time = (TextView)convertView.findViewById(R.id.time_textView);
            time.setText(currentEvent.getTime());

            Button addCalendarButton = (Button)convertView.findViewById(R.id.going_checkBox);
            addCalendarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddCalendarDialogFragment dialog = new AddCalendarDialogFragment();


                    dialog.show(getActivity().getSupportFragmentManager(),"zz");
                }
            });

            return convertView;
        }


    }
}
