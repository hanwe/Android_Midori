package com.work.midori.Products.RecycledWater.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by hanwe on 16/4/22.
 */
public class RecycledWaterPagerAdapter extends FragmentPagerAdapter
{

    private List<Fragment> fragments;

    public RecycledWaterPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments)
    {
        super(fragmentManager);

        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
}
