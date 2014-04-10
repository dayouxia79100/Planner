package com.example.yoloswag.app.schedepagertab;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.eventlist.ListFragmentYo1;
import com.example.yoloswag.app.eventlist.ListFragmentYo2;
import com.example.yoloswag.app.eventlist.ListFragmentYo3;
import com.example.yoloswag.app.newmodule.addevent.AddNewEventActivity;

/**
 * Created by dayouxia on 2/13/14.
 */
public class SchedulePagerTabActivity extends FragmentActivity {


    private ViewPager mViewPager;




    @TargetApi(11)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar= getActionBar();



        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);
        mViewPager.setAdapter(new ScheduleAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                actionBar.setSelectedNavigationItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        };
        for(int i = 0; i < 3; i++){
        	String s = "";
        	switch (i) {
			case 0:
				s = "My Schedule";
				break;
			case 1:
				s = "Invited";
				break;
			case 2:
				s = "Hosting";
				break;

			default:
				break;
			}
            actionBar.addTab(actionBar.newTab()
                    .setText(s)
                    .setTabListener(tabListener));
        }
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_new_event:
                Intent i = new Intent(this, AddNewEventActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class ScheduleAdapter extends FragmentPagerAdapter{
        public ScheduleAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int i) {

            switch (i){
                case 0:
                    return ListFragmentYo1.newInstance(i);
                case 1:
                    return ListFragmentYo2.newInstance(i);
                case 2:
                    return ListFragmentYo3.newInstance(i);
            }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "page"+position;
        }
    }


}
