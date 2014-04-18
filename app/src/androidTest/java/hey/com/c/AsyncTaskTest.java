package hey.com.c;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.login.LoginActivity;
import com.example.yoloswag.app.schedulepagertab.SchedulePagerTabActivity;
import com.example.yoloswag.app.signup.SignupActivity;

import java.util.concurrent.CountDownLatch;

/**
 * Created by dayouxia on 4/17/14.
 */
public class AsyncTaskTest extends ActivityInstrumentationTestCase2<LoginActivity> {
    private LoginActivity mLoginActivity;

    public AsyncTaskTest() {
        super(LoginActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        mLoginActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    public void testLoginAsyncTask () throws Throwable {
        // create CountDownLatch for which the test can wait.
        final Button loginButton = (Button) mLoginActivity.findViewById(R.id.login_button);
        Instrumentation.ActivityMonitor monitor =
                getInstrumentation().
                        addMonitor(LoginActivity.class.getName(), null, false);
        final TextView email = (TextView) mLoginActivity.findViewById(R.id.email_editText);
        final TextView passwords = (TextView)mLoginActivity.findViewById(R.id.password_editText);

        mLoginActivity.runOnUiThread(new Runnable() {
            public void run() {

                email.setText("a1");
                passwords.setText("a1");
                loginButton.performClick();
            }
        });
        // wait 2 seconds for the start of the activity
        LoginActivity startedActivity = (LoginActivity) monitor
                .waitForActivityWithTimeout(2000);
        assertNotNull(startedActivity);

       // startedActivity.getTitle();
    }

}
