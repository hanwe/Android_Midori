package com.work.midori.Actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.work.midori.R;

/**
 * Created by hanwe on 16/4/20.
 */
public class PartnerActivity extends Activity
{
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_web_content);

        initLayout();
    }

    private void initLayout()
    {
        webView = (WebView) findViewById(R.id.partner_webView);

        webView.setWebViewClient(webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://docs.google.com/forms/d/14P4MGqvYCRH8ZFgyNp0cpkgKecFmX6mtbupKnfHAvEQ/viewform?c=0&w=1");
    }

    WebViewClient webViewClient = new WebViewClient()
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    };

}
