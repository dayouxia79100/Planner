package com.example.yoloswag.app.addnewevent2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoloswag.app.R;
import com.example.yoloswag.app.activityafterclick.ActivityAfterFragment;
import com.example.yoloswag.app.newmodule.addevent.AddNewEventFragment;
import com.example.yoloswag.app.newmodule.addevent.AddNewEventFragment2;

/**
 * Created by dayouxia on 4/10/14.
 */
public class AddEventFragment extends Fragment {


    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager_addevent, container, false);
        mViewPager = (ViewPager)v.findViewById(R.id.pager_content);


        final AddEventPagerAdapter adapter = new AddEventPagerAdapter(getFragmentManager());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setAdapter(adapter);



        return v;
    }


    private class AddEventPagerAdapter extends FragmentPagerAdapter{

        public AddEventPagerAdapter(FragmentManager fm ){
            super(fm);
        }


        // it is here to make sure the adapter redraw the view.
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new AddNewEventStep1Fragment();
                case 1:
                    return new AddNewEventStep2Fragment();
            }
            return null;


        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
