package com.example.plannermockup.login;

import com.example.plannermockup.SingleFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;


public class LoginActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }
   
}
