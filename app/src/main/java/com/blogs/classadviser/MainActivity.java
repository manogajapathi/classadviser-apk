package com.blogs.classadviser;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.android.classadviser.R;


public class MainActivity extends Activity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web =(WebView) findViewById(R.id.webView1);
        web.loadUrl("https://classadviser.in");
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new myWebClient());
    }

    public class myWebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView webview, String url){
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

        }

        @Override
        public void onPageFinished(WebView view, String url) {
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