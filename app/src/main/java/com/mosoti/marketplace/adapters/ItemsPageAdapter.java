package com.mosoti.marketplace.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mosoti.marketplace.models.Item;
import com.mosoti.marketplace.ui.ItemDetailFragment;

import java.util.ArrayList;

/**
 * Created by mosoti on 9/15/17.
 */

public class ItemsPageAdapter extends FragmentPagerAdapter {
    private ArrayList<Item> mItems;

    public ItemsPageAdapter(FragmentManager fm, ArrayList<Item> restaurants) {
        super(fm);
        mItems = restaurants;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemDetailFragment.newInstance(mItems.get(position));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position).getName();
    }
}
