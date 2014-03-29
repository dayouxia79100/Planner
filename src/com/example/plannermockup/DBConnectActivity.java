package com.example.plannermockup;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.plannermockup.login.LoginActivity;

import android.R.bool;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class DBConnectActivity extends AsyncTask<String, String, String> {

	private Activity currentActivity;
	private ProgressDialog pDialog;
	private JSONParser jsonParser = new JSONParser();
	private List<NameValuePair> params;
	private String connect_url;
	private String processMessage;
	private int alertTitle;
	private int alertMessage;
	private String requestType;
	private static final String TAG_SUCCESS = "success";
	private boolean success;

	public DBConnectActivity (FragmentActivity current, List<NameValuePair> params, String url, String requestType, String pmessage, int atitle, int amessage) {
		this.currentActivity = current;
		this.params = params;
		this.connect_url = url;
		this.processMessage = pmessage;
		this.alertTitle = atitle;
		this.alertMessage = amessage;
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
		JSONObject json = jsonParser.makeHttpRequest(connect_url,
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
		if (!success) {
			new AlertDialog.Builder(currentActivity)
			.setTitle(alertTitle)
			.setMessage(alertMessage)
			.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) { 
					// do nothing
				}
			})
			.show();
		}
	}

	public void handleSuccessResponse() {
		// to be overridden
	}

	public void handleFailureResponse() {
		// to be overridden
	}
}
