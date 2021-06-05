package com.android.classadviser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web =(WebView) findViewById(R.id.webview);
        web.loadUrl("https://classadviser.in");
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new myWebClient());
    }

    public class myWebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
            return super.shouldOverrideUrlLoading(view, request);
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