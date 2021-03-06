package com.example.plannermockup.signup;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.plannermockup.DBConnectActivity;
import com.example.plannermockup.JSONParser;
import com.example.plannermockup.R;
import com.example.plannermockup.R.id;
import com.example.plannermockup.R.layout;
import com.example.plannermockup.login.LoginActivity;
import com.example.plannermockup.schedepagertab.SchedulePagerTabActivity;
import com.google.android.gms.drive.internal.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
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
public class SignupFragment extends Fragment{
    
	private Button mSignupButton;
	public EditText inputName;
	public EditText inputPassword;
	public EditText inputPassword2;
	public EditText inputEmail;
	public EditText inputPhone;
    private static String processing_message = "Signing up...";
    private static String url_create_user = DBConnectActivity.connect_url_header + "create_user.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_signup,container,false);
        // Edit Text
        inputName = (EditText)v.findViewById(R.id.name_editText);
        inputPassword = (EditText)v.findViewById(R.id.password_editText);
        inputPassword2 = (EditText)v.findViewById(R.id.password_confirm_editText);
        inputEmail = (EditText)v.findViewById(R.id.email_editText);
        inputPhone = (EditText)v.findViewById(R.id.phone_editText);
        
        mSignupButton = (Button)v.findViewById(R.id.signup_button);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	String name = inputName.getText().toString();
                String password = inputPassword.getText().toString();
                String password2 = inputPassword2.getText().toString();
                String email = inputEmail.getText().toString();
                String phone = inputPhone.getText().toString();
                if (name.matches("") || password.matches("") || password2.matches("") || email.matches("") || phone.matches("")) {
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
                if (!password.matches(password2)) {
                	inputPassword.setText("");
                	inputPassword2.setText("");
                	new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.password_not_matching_alert_title)
                    .setMessage(R.string.password_not_matching_alert_message)
                    .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) { 
                            // do nothing
                        }
                     })
                    .show();
                	return;
                }
                
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("password", password));
                params.add(new BasicNameValuePair("email", email));
                params.add(new BasicNameValuePair("phone", phone));
            	DBConnectActivity dbConnect = new DBConnectActivity(getActivity(), params, url_create_user, "POST", processing_message) {
            		@Override
            		public void handleSuccessResponse() {
            			// successfully created product
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);

                    	// closing this screen
                        getActivity().finish();
            		}
            		
            		@Override
            		protected void onPostExecute(String file_url) {
            			if (!this.success) {
        					new AlertDialog.Builder(getActivity())
        					.setTitle(R.string.email_exist_alert_title)
        					.setMessage(R.string.email_exist_alert_message)
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
        });
        return v;
    }
}
