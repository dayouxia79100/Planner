package hey.com.c; /**
 * Created by dayouxia on 4/17/14.
 */

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.login.LoginActivity;
import com.example.yoloswag.app.signup.SignupActivity;




/**
 * Created by dayouxia on 4/17/14.
 */
public class UiTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    LoginActivity mLoginActivity;
    private int mLoginButtonId;
    private int mSignupButtonId;

    public UiTest(){
        super(LoginActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mLoginActivity = getActivity();
        mLoginButtonId = R.id.login_button;
        mSignupButtonId = R.id.signup_button;
    }



    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    // test the activity itself is not null
    public void testPreconditions(){
        assertNotNull("Login Activity is null", mLoginActivity);
    }

    // test the has button id.
    public void testLoginButtonLayout() {

        assertNotNull(mLoginActivity.findViewById(mLoginButtonId));
        Button loginButton = (Button) mLoginActivity.findViewById(mLoginButtonId);
        assertEquals("Incorrect label of the button", "Log in", loginButton.getText());
    }


    @UiThreadTest
    public void testSetTextEmailWithAnnotation() throws Exception {
        // search for the textView
        final TextView textView = (TextView) mLoginActivity
                .findViewById(R.id.email_editText);

        textView.setText("a1");
        assertEquals("Text incorrect", "a1", textView.getText().toString());

    }

    @UiThreadTest
    public void testSetTextPasswordsWithAnnotation() throws  Exception{
        final TextView textView = (TextView) mLoginActivity
                .findViewById(R.id.password_editText);

        textView.setText("a1");
        assertEquals("Text incorrect", "a1", textView.getText().toString());
    }
}
