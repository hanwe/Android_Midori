package com.work.midori.Products.RecycledWater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.work.midori.Products.RecycledWater.Adapter.RecycledWaterPagerAdapter;
import com.work.midori.Products.RecycledWater.Fragment.RecycledWaterPageFragment;
import com.work.midori.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwe on 16/4/24.
 */
public class RecycledWaterActivity extends FragmentActivity
{

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycledwater_interduction);

        initLayout();
    }

    private void initLayout()
    {
        viewPager = (ViewPager) findViewById(R.id.recycledwater_viewpager);

        List<Fragment> fragments = getFragments();

        RecycledWaterPagerAdapter homePagerAdapter = new RecycledWaterPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(homePagerAdapter);
    }

    private List<Fragment> getFragments()
    {
        List<Fragment> fragments = new ArrayList<Fragment>();

        fragments.add(RecycledWaterPageFragment.newInstance(R.mipmap.midori_web_recyclewater));
        return fragments;
    }

}
