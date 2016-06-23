package com.work.midori.Products.HomeEquipment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.work.midori.Products.HomeEquipment.Adapter.HomePagerAdapter;
import com.work.midori.Products.HomeEquipment.Fragment.HomePageFragment;
import com.work.midori.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwe on 16/4/22.
 */
public class HomeEquipmentActivity extends FragmentActivity
{
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_homequipment_interduction);

        initLayout();
    }

    private void initLayout()
    {
        viewPager = (ViewPager) findViewById(R.id.home_viewpager);

        List<Fragment> fragments = getFragments();

        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(homePagerAdapter);
    }

    private List<Fragment> getFragments()
    {
        List<Fragment> fragments = new ArrayList<Fragment>();

        fragments.add(HomePageFragment.newInstance(R.mipmap.midori_web_home));
        return fragments;
    }
}
