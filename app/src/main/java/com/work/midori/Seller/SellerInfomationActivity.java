package com.work.midori.Seller;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.work.midori.R;
import com.work.midori.Seller.Adapter.SellerAdapter;
import com.work.midori.Seller.Data.SellerInfomation;

import java.util.ArrayList;

/**
 * Created by hanwe on 16/4/25.
 */
public class SellerInfomationActivity extends FragmentActivity
{
    private RecyclerView recyclerView;
    private SellerAdapter cellerAdapter;
    private ArrayList<SellerInfomation> data = new ArrayList();

    private SellerAdapter.OnSellerItemClickListener onSellerItemClickListener = new SellerAdapter.OnSellerItemClickListener()
    {


        @Override
        public void OnClickMail(String EmailAdress)
        {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            Uri uri = Uri.parse("mailto:");
            intent.setData(uri);
            String[] addresses = {EmailAdress};
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);

            ComponentName componentName = intent.resolveActivity(getPackageManager());
            if (componentName != null)
            {
                startActivity(intent);
            }
        }

        @Override
        public void OnClickCall(String iPhoneNumber)
        {
            Intent sallIntent = new Intent();
            sallIntent.setAction("android.intent.action.CALL");
            sallIntent.setData(Uri.parse("tel:" + iPhoneNumber));
            startActivity(sallIntent);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sellercontact_main);

        initContactView();
    }

    private void initContactView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.seller_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        TypedArray cellinfo = getResources().obtainTypedArray(R.array.SellInfomation);
        for (int i = 0; i < cellinfo.length(); ++i)
        {
            SellerInfomation cellerInfomation = new SellerInfomation();
            String[] persionalInfo = getResources().getStringArray(cellinfo.getResourceId(i, 0));
            if (persionalInfo.length > 0)
            {
                cellerInfomation.strName = persionalInfo[0];
                cellerInfomation.strPosition = persionalInfo[1];
                cellerInfomation.strEmail = persionalInfo[2];
                cellerInfomation.imageResId = getResources().getIdentifier(persionalInfo[3], "mipmap", getPackageName());
                cellerInfomation.phoneNumber = persionalInfo[4];
            }

            data.add(cellerInfomation);
        }

        cellerAdapter = new SellerAdapter(onSellerItemClickListener, data);

        recyclerView.setAdapter(cellerAdapter);
    }

}
