package com.example.yoloswag.app.addnewevent2;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.helper.DBConnectActivity;
import com.example.yoloswag.app.helper.SpecialCharacterEscaper;
import com.example.yoloswag.app.model.CurrentEventSession;
import com.example.yoloswag.app.model.Event;
import com.example.yoloswag.app.model.User;
import com.example.yoloswag.app.schedepagertab.SchedulePagerTabActivity;

import android.R.integer;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// by summer

public class AddNewEventStep2Fragment extends Fragment{
	
	private Button doneButton;
	private TextView eventNameTextView;
	private EditText inputGuestList;
	private EditText inputItemList;
	private String guestList;
	private String itemList;
	private Event mEvent;

	private static String processing_message = "Creating event...";
	private static String url_create_event = DBConnectActivity.connect_url_header + "create_event.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = CurrentEventSession.get().getEvent();
    }


    @Override
    public void onResume() {
        super.onResume();
        mEvent = CurrentEventSession.get().getEvent();
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_add_event_2_summer, container,false);
		eventNameTextView = (TextView)v.findViewById(R.id.event_name_textView);
		eventNameTextView.setText(mEvent.getEventName());
		inputGuestList = (EditText)v.findViewById(R.id.guest_list);
		inputItemList = (EditText)v.findViewById(R.id.item_list);
		
		doneButton = (Button)v.findViewById(R.id.done_button);
		doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDone();
            }
        });
		
		return v;
	}
	
	public void setEvent(Event event) { 
		this.mEvent = event;
	}
	
	private void handleDone() {
		guestList = inputGuestList.getText().toString();
		itemList = inputItemList.getText().toString();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("eventname", SpecialCharacterEscaper.quoteEscaper(mEvent.getEventName())));
        params.add(new BasicNameValuePair("hostid", Integer.toString(User.getUser().getUid())));
        params.add(new BasicNameValuePair("address", SpecialCharacterEscaper.quoteEscaper(mEvent.getAddress())));
        params.add(new BasicNameValuePair("time", mEvent.getTime()));
        params.add(new BasicNameValuePair("description", SpecialCharacterEscaper.quoteEscaper(mEvent.getDescription())));
        if (mEvent.getAllowGuestInvite())
        	params.add(new BasicNameValuePair("allowguestinvite", "1"));
        else
        	params.add(new BasicNameValuePair("allowguestinvite", "0"));
        params.add(new BasicNameValuePair("guestlist", SpecialCharacterEscaper.quoteEscaper(guestList)));
        params.add(new BasicNameValuePair("itemlist", SpecialCharacterEscaper.quoteEscaper(itemList)));
    	DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_create_event, "POST", processing_message) {
    		@Override
    		public void handleSuccessResponse() {
    			// successfully created product
                Intent i = new Intent(getActivity(), SchedulePagerTabActivity.class);
                startActivity(i);

            	// closing this screen
                getActivity().finish();
    		}
    	};
    	dbConnect.execute();
	}
}
