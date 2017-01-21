package com.youngindia.jobportal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.youngindia.jobportal.ui.Tab1;
import com.youngindia.jobportal.ui.Tab2;
import com.youngindia.jobportal.ui.Tab3;

/**
 * Created by user on 5/11/2016.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
           int mNumOfTabs;


        public PageAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

    @Override
    public Fragment getItem(int position) {

            switch (position) {
                case 0:
                   Tab1 tab1 = new Tab1();

                    return tab1;
                case 1:
                    Tab2 tab2 = new Tab2();
                    return tab2;
                case 2:
                   Tab3 tab3 = new Tab3();
                    return tab3;
                    default:
                    return null;
        }

    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

