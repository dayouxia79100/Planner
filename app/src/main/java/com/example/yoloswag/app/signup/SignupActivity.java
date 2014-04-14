package com.example.yoloswag.app.signup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.yoloswag.app.SingleFragmentActivity;


public class SignupActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SignupFragment();
    }
   
}
