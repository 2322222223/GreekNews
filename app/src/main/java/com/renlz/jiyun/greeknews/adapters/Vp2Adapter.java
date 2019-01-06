package com.renlz.jiyun.greeknews.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/1/1.
 */

public class Vp2Adapter extends FragmentPagerAdapter {
    public ArrayList<Fragment> mList;
    public ArrayList<String> mList2;

    public Vp2Adapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> list2) {
        super(fm);
        mList = list;
        mList2 = list2;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList2.get(position);
    }
}
