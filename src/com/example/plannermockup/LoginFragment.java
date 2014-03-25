package com.example.plannermockup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by dayouxia on 2/13/14.
 */
public class LoginFragment extends Fragment{

    private Button mLoginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.v(LoginFragment.class.toString(),"onCreateView is called");
        View v = inflater.inflate(R.layout.fragment_login,container,false);
        mLoginButton = (Button)v.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),ScheduleListActivity.class);
                startActivity(i);
            }
        });

        return v;

    }
}
