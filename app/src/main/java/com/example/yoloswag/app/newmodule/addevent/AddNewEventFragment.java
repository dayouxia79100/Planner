package com.example.yoloswag.app.newmodule.addevent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yoloswag.app.R;

public class AddNewEventFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.fragment_add_event_ming, container,false);

        Button next = (Button)v.findViewById(R.id.next_add_event);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddNewEventActivity2.class);
                startActivity(i);
            }
        });
		return v;
	}
	
	

}
