package com.example.plannermockup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
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
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.plannermockup.eventdetail.EventDetailActivity;
import com.example.plannermockup.model.Event;
import com.example.plannermockup.model.EventsSingleton;
import com.example.plannermockup.model.MyUser;
import com.example.plannermockup.model.User;
import com.example.plannermockup.schedepagertab.SchedulePagerTabActivity;
import com.google.android.gms.internal.m;

/**
 * Created by dayouxia on 2/13/14.
 */
public class ScheduleFragment extends ListFragment {

	private ArrayList<Event> mEventList;
	public static final String EXTRA_TAB_NUM = "tabnumber";
	private int mCurrentTabNumber;
	private static final String processing_message = "Loading...";
	private static final String url_get_user_info = DBConnectActivity.connect_url_header + "get_user_info.php";
	private static final String url_update_event_status = DBConnectActivity.connect_url_header + "update_event_status.php";
	private static final String TAG_USERINFO = "userinfo";
	private static final String TAG_UID = "uid";
	private static final String TAG_EID = "eid";
	private static final String TAG_STATUS = "status";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "name";
	private static final String TAG_PHONE = "name";

	public static ScheduleFragment newInstance(int position){
		ScheduleFragment fragment = new ScheduleFragment();
		Bundle args = new Bundle();
		args.putInt(EXTRA_TAB_NUM, position);
		fragment.setArguments(args);
		return fragment;
	}

	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mEventAdapter.notifyDataSetChanged();
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ArrayList<Event> eventlist = EventsSingleton.get().getEventList(mCurrentTabNumber);
		final Event currentEvent = eventlist.get(position);
		final User hostUser = new User();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uid", Integer.toString(currentEvent.getHostId())));
		DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_get_user_info, "GET", processing_message) {
			@Override
			public void handleSuccessResponse() {
				// successfully found user
				JSONObject json = this.getJsonObject();
				try {
					JSONObject userInfo = json.getJSONArray(TAG_USERINFO).getJSONObject(0);
					hostUser.setUid(userInfo.getInt(TAG_UID));
					hostUser.setName(userInfo.getString(TAG_NAME));
					hostUser.setEmail(userInfo.getString(TAG_EMAIL));
					hostUser.setPhone(userInfo.getString(TAG_PHONE));
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			@Override
			protected void onPostExecute(String file_url) {
				if (this.success) {
					Intent i = new Intent(getActivity(), EventDetailActivity.class);
					i.putExtra("event", currentEvent);
					i.putExtra("host", hostUser);
					startActivity(i);
				}
				super.onPostExecute(file_url);
			}
		};
		dbConnect.execute();
	}
	
	private EventAdapter mEventAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mEventList = EventsSingleton.get().getEventList(getArguments().getInt(EXTRA_TAB_NUM));
		mEventAdapter = new EventAdapter(mEventList);
		setListAdapter(mEventAdapter);
		mCurrentTabNumber = getArguments().getInt(EXTRA_TAB_NUM);
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
						.inflate(R.layout.fragment_event_item, null);

			}

			final Event currentEvent = getItem(position);
			TextView partyName = (TextView) convertView.findViewById(R.id.partyName_textView);
			partyName.setText(currentEvent.getEventName());

			TextView address = (TextView)convertView.findViewById(R.id.partyAddress_textView);
			address.setText(currentEvent.getAddress());

			TextView time = (TextView)convertView.findViewById(R.id.time_textView);
			time.setText(currentEvent.getTime());

			final Button addButton = (Button)convertView.findViewById(R.id.add_button);
			String statusString = "";
			switch (currentEvent.getStatus()) {
			case 0:
				statusString = "Pending";
				break;
			case -1:
				statusString = "Declined";
				break;
			case 1:
				statusString = "Going";
				break;
			}
			addButton.setText(statusString);
			addButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.join_event_title)
                    .setMessage(R.string.join_event_message)
                    .setPositiveButton(R.string.accept_event, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { 
                            addButton.setText("Going");
                            updateStatusOnDB(1);
                            EventsSingleton.get().addToMySchedule(currentEvent);
                            EventsSingleton.get().updateEventStatus(currentEvent, 1);
                        }
                     })
                     .setNeutralButton(R.string.pending_event, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { 
                            addButton.setText("Pending");
                            updateStatusOnDB(0);
                            EventsSingleton.get().removeFromMySchedule(currentEvent);
                            EventsSingleton.get().updateEventStatus(currentEvent, 0);
                        }
                     })
                     .setNegativeButton(R.string.decline_event, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { 
                            addButton.setText("Declined");
                            updateStatusOnDB(-1);
                            EventsSingleton.get().removeFromMySchedule(currentEvent);
                            EventsSingleton.get().updateEventStatus(currentEvent, -1);
                        }
                     })
                    .show();
				}
				
				private void updateStatusOnDB(int status) {
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair(TAG_EID, Integer.toString(currentEvent.getEid())));
					params.add(new BasicNameValuePair(TAG_UID, Integer.toString(MyUser.getUser().getUid())));
					params.add(new BasicNameValuePair(TAG_STATUS, Integer.toString(status)));
					DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_update_event_status, "POST", processing_message);
					dbConnect.execute();
				}
			});

			return convertView;
		}


	}
}
