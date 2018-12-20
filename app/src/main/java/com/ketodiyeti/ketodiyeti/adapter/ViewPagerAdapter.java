package com.ketodiyeti.ketodiyeti.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tabLayoutTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment addedFragment, String tabLayoutTitle) {
        fragmentList.add(addedFragment);
        tabLayoutTitleList.add(tabLayoutTitle);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabLayoutTitleList.get(position);
    }
}
