package com.blogs.classadviser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.classadviser.R;
import com.onesignal.OneSignal;

public class MainActivity extends Activity {

    private WebView web;
    ProgressDialog progressDialog = null;
    private static final String ONESIGNAL_APP_ID = "4120cc24-7238-405c-a86f-65b794e2a809";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        setContentView(R.layout.activity_main);
        web =(WebView) findViewById(R.id.webView1);
        web.loadUrl("https://classadviser.in");
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new myWebClient());
    }

    public class myWebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url){
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
            webview.loadUrl(url);
            return true;
        }


        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            view.loadUrl("file:///android_asset/noconnection.html");
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            try {
                if (progressDialog .isShowing()) {
                    progressDialog .dismiss();
                    progressDialog = null;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            findViewById(R.id.layout_splash).setVisibility(View.GONE);
        }
    }
    @Override
    public void onBackPressed(){
        if(web.canGoBack()) {
            web.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}