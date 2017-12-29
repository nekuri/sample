package com.example.takase.sample;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView)findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                handler.proceed("lumielinabrand", "e2devel");
            }
        });

        try {
            if (savedInstanceState == null) {
                mWebView.loadUrl(getString(R.string.site_url));

                if (!mWebView.getUrl().equals(getString(R.string.site_url))) {
                    throw new Exception();
                }

            } else {
                mWebView.restoreState(savedInstanceState);
            }
        } catch (Exception e) {
            mWebView.loadUrl(getString(R.string.site_url));
            Toast.makeText(this, "不正なページが指定されました。", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mWebView.saveState(outState);
    }

    @Override
    public void onBackPressed() {
        mWebView.goBack();
    }
}
