package com.example.yoloswag.app.whosgoing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by dayouxia on 3/26/14.
 */
public class WhosGoingFragment extends ListFragment {

    private ArrayList<String> mGoingList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoingList.add("Charlie");
        mGoingList.add("Summer");
        mGoingList.add("Ming");
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        setListAdapter(new WhosGoingAdapter(mGoingList));
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
            return mGoingList.size();

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(android.R.layout.simple_list_item_1,null);

            }

            TextView textView = (TextView)convertView.findViewById(android.R.id.text1);
            textView.setText(mGoingList.get(position));


            return convertView;
        }


    }
}
