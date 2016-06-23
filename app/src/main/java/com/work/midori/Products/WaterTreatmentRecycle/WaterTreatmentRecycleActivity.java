package com.work.midori.Products.WaterTreatmentRecycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.work.midori.Products.WaterTreatmentRecycle.Adapter.WaterTreatmentRecyclePagerAdapter;
import com.work.midori.Products.WaterTreatmentRecycle.Fragment.WaterTreatmentRecycleFragment;
import com.work.midori.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanwe on 16/4/25.
 */
public class WaterTreatmentRecycleActivity extends FragmentActivity
{
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_watertreatmentrecycle_interduction);

        initLayout();
    }

    private void initLayout()
    {
        viewPager = (ViewPager) findViewById(R.id.watertreatment_recycle_viewpager);

        List<Fragment> fragments = getFragments();

        WaterTreatmentRecyclePagerAdapter homePagerAdapter = new WaterTreatmentRecyclePagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(homePagerAdapter);
    }

    private List<Fragment> getFragments()
    {
        List<Fragment> fragments = new ArrayList<Fragment>();

        fragments.add(WaterTreatmentRecycleFragment.newInstance(R.mipmap.midori_web_watertreatment_recycle));
        return fragments;
    }

}
