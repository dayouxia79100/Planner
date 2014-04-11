package com.example.yoloswag.app.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.helper.DBConnectActivity;
import com.example.yoloswag.app.model.EventsSingleton;
import com.example.yoloswag.app.model.User;
import com.example.yoloswag.app.schedepagertab.SchedulePagerTabActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dayouxia on 2/13/14.
 */
public class LoginFragment extends Fragment{

	private Button mLoginButton;
	private Button mSignupButton;
	private EditText inputEmail;
	private EditText inputPassword;
	private User mUser;

	private static String processing_message = "Logging in...";
	private static String url_log_in = DBConnectActivity.connect_url_header + "login.php";
	private static final String TAG_USERINFO = "userinfo";
	private static final String TAG_UID = "uid";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_PHONE = "phone";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Log.v(LoginFragment.class.toString(),"onCreateView is called");
		View v = inflater.inflate(R.layout.fragment_login,container,false);
		inputEmail = (EditText)v.findViewById(R.id.email_editText);
		inputPassword = (EditText)v.findViewById(R.id.password_editText);
		mLoginButton = (Button)v.findViewById(R.id.login_button);
		mLoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				handleSignin();
			}
		});
		mSignupButton = (Button)v.findViewById(R.id.signup_button);
		mSignupButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Intent i = new Intent(getActivity(),SignupActivity.class);
				//startActivity(i);
			}
		});

		return v;
	}

	private void handleSignin() {

		String email = inputEmail.getText().toString();
		String password = inputPassword.getText().toString();

		if (email.matches("") || password.matches("")) {
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

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_log_in, "GET", processing_message) {
			@Override
			public void handleSuccessResponse() {
				// successfully found user
				JSONObject json = this.getJsonObject();
				try {
					JSONObject userInfo = json.getJSONArray(TAG_USERINFO).getJSONObject(0);
					mUser = User.getUser();
					mUser.setUid(userInfo.getInt(TAG_UID));
					mUser.setName(userInfo.getString(TAG_NAME));
					mUser.setEmail(userInfo.getString(TAG_EMAIL));
					mUser.setPhone(userInfo.getString(TAG_PHONE));
					Log.d("uid", Integer.toString(mUser.getUid()));
					Log.d("name", mUser.getName());
					Log.d("email", mUser.getEmail());
					Log.d("phone", mUser.getPhone());
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			protected void onPostExecute(String file_url) {
				if (this.success) {
					EventsSingleton eventsSingleton = EventsSingleton.get();
					eventsSingleton.loadEvents(getActivity(), new SchedulePagerTabActivity());
				}
				else {
					new AlertDialog.Builder(getActivity())
					.setTitle(R.string.login_fail_alert_title)
					.setMessage(R.string.login_fail_alert_message)
					.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) { 
							// do nothing
						}
					})
					.show();
				}
				super.onPostExecute(file_url);
			}
		};
		dbConnect.execute();
	}
}
