package com.work.midori.Main;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.onesignal.OneSignal;
import com.work.midori.AWS.AWSMobileClient;
import com.work.midori.AWS.user.IdentityManager;
import com.work.midori.Actionbar.PartnerActivity;
import com.work.midori.GCM.PushListenerService;
import com.work.midori.Main.Adapter.MianCardAdapter;
import com.work.midori.Products.HomeEquipment.HomeEquipmentActivity;
import com.work.midori.Products.RecycledWater.RecycledWaterActivity;
import com.work.midori.Products.WaterTreatment.WaterTreatmentActivity;
import com.work.midori.Products.WaterTreatmentRecycle.WaterTreatmentRecycleActivity;
import com.work.midori.R;
import com.work.midori.Seller.SellerInfomationActivity;
import com.work.midori.Util.Configuration;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.*;
//import com.microsoft.windowsazure.notifications.NotificationsManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private MianCardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<ProductsInfomationClass> mProductList;

    public static MainActivity mainActivity;
    public static Boolean isVisible = false;
    private GoogleCloudMessaging gcm;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private IdentityManager identityManager;
    private int iNotificationIndex = 1;

    private MianCardAdapter.OnMainItemClickListener onItemClickListener = new MianCardAdapter.OnMainItemClickListener()
    {

        @Override
        public void OnItemClick(View view, int iID)
        {
            //Recycle view item click
            switch (iID)
            {
                case Configuration.HomeEquipment:
                {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, HomeEquipmentActivity.class);
                    startActivity(intent);
                    break;
                }
                case Configuration.RecycledWater:
                {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, RecycledWaterActivity.class);
                    startActivity(intent);
                    break;
                }
                case Configuration.WaterTreatment:
                {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, WaterTreatmentActivity.class);
                    startActivity(intent);
                    break;
                }
                case Configuration.WaterTreatmentRecycle:
                {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, WaterTreatmentRecycleActivity.class);
                    startActivity(intent);
                    break;
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setLogo(R.mipmap.logo);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SellerInfomationActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        mainActivity = this;
//        NotificationsManager.handleNotifications(this, NotificationSettings.SenderId, GCMHandler.class);
//        registerWithNotificationHubs();

        OneSignal.startInit(this)
                 .setNotificationOpenedHandler(new PushNotificationOpenedHandler())
                 .init();

        initData();
        initLayout();
        initAws();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();

        LocalBroadcastManager.getInstance(this).registerReceiver(notificationReceiver,
                                                                 new IntentFilter(PushListenerService.ACTION_SNS_NOTIFICATION));
    }

    private class PushNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler
    {
        @Override
        public void notificationOpened(String message, JSONObject additionalData, boolean isActive)
        {
            try
            {
                if (additionalData != null)
                {
                    if (additionalData.has("actionSelected"))
                        Log.d("OneSignalExample", "OneSignal notification button with id " + additionalData.getString("actionSelected") + " pressed");

                    Log.d("OneSignalExample", "Full additionalData:\n" + additionalData.toString());


                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE );
                    Notification.Builder builder = new Notification.Builder(MainActivity.this );
                    PendingIntent contentIndent = PendingIntent.getActivity(MainActivity. this, 0, new Intent(MainActivity.this, MainActivity. class), PendingIntent. FLAG_UPDATE_CURRENT);

                    builder.setContentIntent(contentIndent)
                           .setSmallIcon(R.mipmap.ic_launcher)
                           // 設置狀態列裡面的圖示（小圖示）　　　　　　　　　　　
                           .setLargeIcon(BitmapFactory. decodeResource(getResources(), R.mipmap.ic_launcher)) // 下拉下拉清單裡面的圖示（大圖示）
                           .setTicker(message) // 設置狀態列的顯示的資訊
                           .setAutoCancel(false) // 設置可以清除
                           .setContentTitle( "Midori ") // 設置下拉清單裡的標題
                           .setContentText(message); // 設置上下文內容

                    Notification notification = builder.getNotification();

                    // notification.defaults |= Notification.DEFAULT_SOUND;
                    notification.sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.drop);

            // 振動
//            notification.defaults |= Notification.DEFAULT_VIBRATE ; // 某些手機不支援 請加

                    // 加i是為了顯示多條Notification
                    notificationManager.notify(0, notification);
                    iNotificationIndex++;
                }
            }
            catch (Throwable t)
            {
                t.printStackTrace();
            }

            // The following can be used to open an Activity of your choose.
      /*
      Intent intent = new Intent(getApplication(), YourActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intent);
      */
            // Follow the insturctions in the link below to prevent the launcher Activity from starting.
            // https://documentation.onesignal.com/docs/android-notification-customizations#changing-the-open-action-of-a-notification
        }
    }

    private final BroadcastReceiver notificationReceiver = new BroadcastReceiver()

    {
        @Override
        public void onReceive(Context context, Intent intent)
        {

            Bundle data = intent.getBundleExtra(PushListenerService.INTENT_SNS_NOTIFICATION_DATA);
            String message = PushListenerService.getMessage(data);

//            new AlertDialog.Builder(MainActivity.this)
//                    .setTitle("Midori")
//                    .setMessage(message)
//                    .setPositiveButton(android.R.string.ok, null)
//                    .show();
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE );
            Notification.Builder builder = new Notification.Builder(MainActivity.this );
            PendingIntent contentIndent = PendingIntent.getActivity(MainActivity. this, 0, new Intent(MainActivity.this, MainActivity. class), PendingIntent. FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIndent)
                   .setSmallIcon(R.mipmap.ic_launcher)
                   // 設置狀態列裡面的圖示（小圖示）　　　　　　　　　　　
                   .setLargeIcon(BitmapFactory. decodeResource(getResources(), R.mipmap.ic_launcher)) // 下拉下拉清單裡面的圖示（大圖示）
                   .setTicker(message) // 設置狀態列的顯示的資訊
                   .setAutoCancel(false) // 設置可以清除
                   .setContentTitle( "Midori ") // 設置下拉清單裡的標題
                   .setContentText(message); // 設置上下文內容

            Notification notification = builder.getNotification();

            // notification.defaults |= Notification.DEFAULT_SOUND;
            notification.sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.drop);

//            // 振動
//            notification.defaults |= Notification.DEFAULT_VIBRATE ; // 某些手機不支援 請加

            // 加i是為了顯示多條Notification
            notificationManager.notify(0, notification);
            iNotificationIndex++;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_settings:
            {
                return true;
            }
            case R.id.action_order:
            {
                Intent intent = new Intent(this, PartnerActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.action_facebook:
            {
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData()
    {
        mProductList = new ArrayList();

        Resources res = getResources();
        TypedArray imagesArray = res.obtainTypedArray(R.array.product_image);
        String[] nameArray = res.getStringArray(R.array.product_Name);
        String[] contentArray = res.getStringArray(R.array.product_content);

        for( int i = 0 ; i < imagesArray.length() ; ++i)
        {
            ProductsInfomationClass productsInfomationClass = new ProductsInfomationClass();

            productsInfomationClass.dImageResouce = imagesArray.getDrawable(i);
            productsInfomationClass.strTitle = nameArray[i];
            productsInfomationClass.strContent = contentArray[i];

            mProductList.add(productsInfomationClass);
        }

    }

    private void initLayout()
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.banner_recyclerview);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        if(mProductList.size() > 0)
        {
            mAdapter = new  MianCardAdapter(mProductList, onItemClickListener);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void initAws()
    {
        AWSMobileClient.initializeMobileClientIfNecessary(this);
        final AWSMobileClient awsMobileClient = AWSMobileClient.defaultMobileClient();
        identityManager = awsMobileClient.getIdentityManager();
    }

    private boolean checkPlayServices()
    {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS)
        {
            if (apiAvailability.isUserResolvableError(resultCode))
            {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }
}
