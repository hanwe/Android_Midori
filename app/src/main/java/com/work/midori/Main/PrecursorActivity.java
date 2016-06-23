package com.work.midori.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.work.midori.R;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by hanwe on 16/6/1.
 */
public class PrecursorActivity extends Activity
{
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activiry_precursor);

        imageView =  (ImageView)findViewById(R.id.precursor_imageView);

        initHandler();
    }

    private void initHandler()
    {
        Handler handler = new Handler();

        handler.removeCallbacks(accountTimer);
        handler.postDelayed(accountTimer, 4000);
    }

    private Runnable accountTimer = new Runnable()
    {
        @Override
        public void run()
        {
            Intent intent = new Intent();
            intent.setClass(PrecursorActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }
    };

    @Override
    public void finish()
    {
        super.finish();

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


}
