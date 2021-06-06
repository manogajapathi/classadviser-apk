package com.android.classadviser;
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


public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webview = (WebView) findViewById(R.id.webView1);
        WebSettings websettings = webview.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setSaveFormData(false);
        webview.loadUrl("https://classadviser.in");
        webview.setHorizontalScrollBarEnabled(false);
        webview.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webview.setBackgroundColor(128);
        webview.setWebViewClient(new ClassAdviserWebViewClient());

        webview.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });
    }

    private class ClassAdviserWebViewClient extends WebViewClient {
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
}