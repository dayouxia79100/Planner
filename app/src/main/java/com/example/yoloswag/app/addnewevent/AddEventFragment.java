package com.example.yoloswag.app.addnewevent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yoloswag.app.R;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.IconPagerAdapter;

/**
 * Created by dayouxia on 4/10/14.
 */
public class AddEventFragment extends Fragment {


    private ViewPager mViewPager;
    private IconPageIndicator mIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager_addevent, container, false);
        mViewPager = (ViewPager)v.findViewById(R.id.pager_content);



        final AddEventPagerAdapter adapter = new AddEventPagerAdapter(getFragmentManager());

        mViewPager.setAdapter(adapter);
        mIndicator = (IconPageIndicator)v.findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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



        return v;
    }


    private class AddEventPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter{

        final int[] ICONS = new int[] {
                R.drawable.page1_group,
                R.drawable.page2_group,
                R.drawable.page3_group

        };

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
                case 2:
                    return new AddNewEventStep3Fragment();
            }
            return null;


        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public int getIconResId(int i) {
            return ICONS[i];
        }
    }


}
