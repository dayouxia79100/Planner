package com.example.yoloswag.app.helper;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.plannermockup.login.LoginActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class DBConnectActivity extends AsyncTask<String, String, String> {

	public static String connect_url_header = "https://164.107.127.214/event_planner/";
	public Activity currentActivity;
	private ProgressDialog pDialog;
	private JSONParser jsonParser = new JSONParser();
	private List<NameValuePair> params;
	private String connect_url;
	private String processMessage;
	private int alertTitle;
	private int alertMessage;
	private String requestType;
	private static final String TAG_SUCCESS = "success";
	public boolean success;
	private JSONObject json;
	
	public DBConnectActivity (FragmentActivity current, List<NameValuePair> params, String url, String requestType, String pmessage) {
		this.currentActivity = current;
		this.params = params;
		this.connect_url = url;
		this.processMessage = pmessage;
		this.alertTitle = -1;
		this.alertMessage = -1;
		this.requestType = requestType;
		this.success = false;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pDialog = new ProgressDialog(currentActivity);
		pDialog.setMessage(processMessage);
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}

	protected String doInBackground(String... args) {

		// getting JSON Object
		// Note that create product url accepts POST method
		this.json = jsonParser.makeHttpRequest(connect_url,
				requestType, params);

		// check log cat fro response
		Log.d("Create Response", json.toString());

		// check for success tag
		try {
			int success = json.getInt(TAG_SUCCESS);

			if (success == 1) {
				this.success = true;
				this.handleSuccessResponse();
			} else {
				this.handleFailureResponse();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	protected void onPostExecute(String file_url) {
		// dismiss the dialog once done
		pDialog.dismiss();
	}

	public void handleSuccessResponse() {
		// to be overridden
	}

	public void handleFailureResponse() {
		// to be overridden
	}
	
	public JSONObject getJsonObject() {
		return this.json;
	}
}
