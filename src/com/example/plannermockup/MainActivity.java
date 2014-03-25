package com.example.plannermockup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;


public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	Log.v(MainActivity.class.toString(), "onCreate() is called");
    }
}
