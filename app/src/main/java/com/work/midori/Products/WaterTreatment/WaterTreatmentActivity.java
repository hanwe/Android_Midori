package com.work.midori.Products.WaterTreatment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.work.midori.Products.WaterTreatment.Adapter.WaterTreatmentPagerAdapter;
import com.work.midori.Products.WaterTreatment.Fragment.WaterTreatmentFragment;
import com.work.midori.R;

import java.util.ArrayList;
import java.util.List;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;


/**
 * Created by hanwe on 16/4/25.
 */
public class WaterTreatmentActivity extends FragmentActivity
{
    private VerticalViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_watertreatment_interduction);

        initLayout();
    }

    private void initLayout()
    {
        viewPager = (VerticalViewPager) findViewById(R.id.watertreatment_viewpager);

        List<Fragment> fragments = getFragments();

        WaterTreatmentPagerAdapter homePagerAdapter = new WaterTreatmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(homePagerAdapter);
    }

    private List<Fragment> getFragments()
    {
        List<Fragment> fragments = new ArrayList<Fragment>();

        fragments.add(WaterTreatmentFragment.newInstance(R.mipmap.midori_web_watertreatment_1));
        fragments.add(WaterTreatmentFragment.newInstance(R.mipmap.midori_web_watertreatment_2));
        return fragments;
    }
}
