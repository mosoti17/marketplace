package com.mosoti.marketplace.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mosoti.marketplace.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        WebView webView= (WebView) findViewById(R.id.weview) ;
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //setContentView(webView);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
    }
}
