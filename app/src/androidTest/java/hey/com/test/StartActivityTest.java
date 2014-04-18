package hey.com.test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.login.LoginActivity;
import com.example.yoloswag.app.signup.SignupActivity;

/**
 * Created by dayouxia on 4/17/14.
 */
public class StartActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    public StartActivityTest(){
        super(LoginActivity.class);
    }

    private LoginActivity mLoginActivity;


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mLoginActivity = getActivity();
    }

    public void testIntentTriggerViaOnClick() throws Exception {

        final Button signupButton = (Button) mLoginActivity.findViewById(R.id.signup_button);
        Instrumentation.ActivityMonitor monitor =
                getInstrumentation().
                        addMonitor(SignupActivity.class.getName(), null, false);

        mLoginActivity.runOnUiThread(new Runnable() {
            public void run() {
                signupButton.performClick();
            }
        });
        // wait 2 seconds for the start of the activity
        SignupActivity startedActivity = (SignupActivity) monitor
                .waitForActivityWithTimeout(2000);
        assertNotNull(startedActivity);

        this.sendKeys(KeyEvent.KEYCODE_BACK);
    }

}
