package com.example.yoloswag.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.TextView;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.eventlist.MainActivity;
import com.example.yoloswag.app.login.LoginActivity;

/**
 * Created by dayouxia on 4/17/14.
 */
public class ActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    LoginActivity activity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    public ActivityTest(){
        super(LoginActivity.class);
    }

    @SmallTest
    public void buttonLogIn(){
        Button button = (Button)activity.findViewById(R.id.login_button);
        assertEquals(button.getText().toString(), "Log in");
    }

    @SmallTest
    public void buttonLogfn(){
        Button button = (Button)activity.findViewById(R.id.login_button);
        assertEquals(button.getText().toString(), "Log in");
    }
}
