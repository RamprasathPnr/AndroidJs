package com.androidjs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.Locale;

public class MainActivity extends Activity {
    private WebView mWebView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showWebView();
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void showWebView() {
        try {
            mWebView = new WebView(this);
            setContentView(mWebView);

            mWebView.requestFocus();

            mWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int progress) {
                    MainActivity.this.setTitle("Loading...");
                    MainActivity.this.setProgress(progress);

                    if (progress >= 80) {
                        MainActivity.this.setTitle("JsAndroid Test");
                    }
                }
            });

            mWebView.setOnKeyListener(new View.OnKeyListener() { // webview can
                // go back
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && mWebView.canGoBack()) {
                        mWebView.goBack();
                        return true;
                    }
                    return false;
                }
            });

            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDefaultTextEncodingName("utf-8");

           /* mWebView.addJavascriptInterface(this, "jsObj");
            Locale locale = getResources().getConfiguration().locale;
            String language = locale.getLanguage();
            if (language.endsWith("zh"))
           */     mWebView.loadUrl("file:///android_asset/login.html");
           // else
               // mWebView.loadUrl("file:///android_asset/index_en.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
