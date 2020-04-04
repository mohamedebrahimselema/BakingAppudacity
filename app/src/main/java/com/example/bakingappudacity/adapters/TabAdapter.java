package com.example.bakingappudacity.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;


public class TabAdapter extends FragmentPagerAdapter {

    private List<Fragment> tabFragmentsList;
    private List<String> tabTitleList;

    public TabAdapter(FragmentManager fm, List<Fragment> tabFragmentsList, List<String> tabTitleList) {
        super(fm);
        this.tabFragmentsList=tabFragmentsList;
        this.tabTitleList=tabTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return tabFragmentsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleList.get(position);
    }


}
