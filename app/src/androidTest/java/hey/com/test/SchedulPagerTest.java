package hey.com.test;

import android.app.ActionBar;
import android.test.ActivityInstrumentationTestCase2;

import com.example.yoloswag.app.login.LoginActivity;
import com.example.yoloswag.app.schedulepagertab.SchedulePagerTabActivity;

/**
 * Created by dayouxia on 4/17/14.
 */
public class SchedulPagerTest extends ActivityInstrumentationTestCase2<SchedulePagerTabActivity> {

    private SchedulePagerTabActivity mSchedulePagerTabActivity;

    public SchedulPagerTest() {
        super(SchedulePagerTabActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        mSchedulePagerTabActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFirstTab(){
        final ActionBar actionBar = mSchedulePagerTabActivity.getActionBar();
        String title1 = actionBar.getTabAt(0).getText().toString();
        assertEquals("My Schedule", title1);

    }
    public void testSecondTab(){
        final ActionBar actionBar = mSchedulePagerTabActivity.getActionBar();
        String title2 = actionBar.getTabAt(1).getText().toString();
        assertEquals("Invited", title2);

    }

    public void testThirdTab(){
        final ActionBar actionBar = mSchedulePagerTabActivity.getActionBar();
        String title3 = actionBar.getTabAt(2).getText().toString();
        assertEquals("Hosting", title3);

    }

}
