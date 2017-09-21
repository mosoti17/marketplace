package com.mosoti.marketplace.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.mosoti.marketplace.ui.DaysItemFragment;
import com.mosoti.marketplace.ui.SavedItemsFragment;
import com.mosoti.marketplace.ui.TrendingFragment;

/**
 * Created by mosoti on 9/21/17.
 */

public class MainActivityAdapter extends FragmentPagerAdapter {

    public MainActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch (position){
          case 0:
              DaysItemFragment daysItemFragment= new DaysItemFragment();
              return daysItemFragment;

          case 1:
              TrendingFragment trendingFragment=new TrendingFragment();
              return trendingFragment;
          case 2:
              SavedItemsFragment savedItemsFragment=new SavedItemsFragment();
              return savedItemsFragment;

      }

        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }


}
