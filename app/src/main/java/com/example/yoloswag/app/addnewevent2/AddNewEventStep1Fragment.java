package com.example.yoloswag.app.addnewevent2;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


import com.example.yoloswag.app.R;
import com.example.yoloswag.app.login.LoginFragment;
import com.example.yoloswag.app.model.CurrentEventSession;
import com.example.yoloswag.app.model.Event;
import com.example.yoloswag.app.model.User;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

//by summer

public class AddNewEventStep1Fragment extends Fragment{
	
	private Button nextButton;
	private EditText inputEventName;
	private EditText inputEventDescription;
	private EditText inputEventTime;
	private EditText inputEventAddress;
	private CheckBox inputAllowGuestInvite;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.v(LoginFragment.class.toString(),"onCreateView is called");
		View v = inflater.inflate(R.layout.fragment_add_event_1_summer, container,false);
		
		inputEventName = (EditText)v.findViewById(R.id.event_name_editText);
        

		inputEventAddress = (EditText)v.findViewById(R.id.event_address_editText);
		inputEventDescription = (EditText)v.findViewById(R.id.event_description_editText);
		inputEventTime = (EditText)v.findViewById(R.id.event_date);
		inputAllowGuestInvite = (CheckBox)v.findViewById(R.id.allow_guest_invite_checkBox);
		
		nextButton = (Button)v.findViewById(R.id.next_button);
		nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNext();
            }
        });
		
		return v;
	}
	
	private void handleNext() {
		String eventName = inputEventName.getText().toString();
		String eventDescription = inputEventDescription.getText().toString();
		String eventTime = inputEventTime.getText().toString();
		String eventAddress = inputEventAddress.getText().toString();
		boolean allowGuestInvite = inputAllowGuestInvite.isChecked();
		int hostId = User.getUser().getUid();
		
		if (eventName.matches("") || eventTime.matches("")) {
			new AlertDialog.Builder(getActivity())
            .setTitle(R.string.info_not_complete_alert_title)
            .setMessage(R.string.info_not_complete_alert_message)
            .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { 
                    // do nothing
                }
             })
            .show();
        	return;
		}


        // need to pass this event, use a singleton or pass it along with bundle

		Event mEvent = new Event(eventName, hostId, eventAddress, eventTime, eventDescription, allowGuestInvite);
        CurrentEventSession.get().setEvent(mEvent);
		


        // closing this screen
        // getActivity().finish();
	}
}
