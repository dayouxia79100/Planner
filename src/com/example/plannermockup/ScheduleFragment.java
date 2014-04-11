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
	private static String processing_message = "Loading...";
	private static String url_get_user_name = DBConnectActivity.connect_url_header + "get_user_name.php";
	private static final String TAG_USERINFO = "userinfo";
	private static final String TAG_UID = "uid";
	private static final String TAG_NAME = "name";

	public static ScheduleFragment newInstance(int position){
		ScheduleFragment fragment = new ScheduleFragment();
		Bundle args = new Bundle();
		args.putInt(EXTRA_TAB_NUM, position);
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ArrayList<Event> eventlist = EventsSingleton.get().getEventList(mCurrentTabNumber);
		final Event currentEvent = eventlist.get(position);
		final User hostUser = new User();
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uid", Integer.toString(currentEvent.getHostId())));
		DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_get_user_name, "GET", processing_message) {
			@Override
			public void handleSuccessResponse() {
				// successfully found user
				JSONObject json = this.getJsonObject();
				try {
					JSONObject userInfo = json.getJSONArray(TAG_USERINFO).getJSONObject(0);
					hostUser.setUid(userInfo.getInt(TAG_UID));
					hostUser.setName(userInfo.getString(TAG_NAME));
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
		
		/*Intent i = new Intent(getActivity(), EventDetailActivity.class);
		i.putExtra("event", currentEvent);
		i.putExtra("host", hostUser);
		startActivity(i);*/
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mEventList = EventsSingleton.get().getEventList(getArguments().getInt(EXTRA_TAB_NUM));
		setListAdapter(new EventAdapter(mEventList));
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
						.inflate(R.layout.fragment_event_item,null);

			}

			Event currentEvent = getItem(position);
			TextView partyName = (TextView) convertView.findViewById(R.id.partyName_textView);
			partyName.setText(currentEvent.getEventName());

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
