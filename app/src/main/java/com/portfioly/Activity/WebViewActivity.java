package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.portfioly.R;

public class WebViewActivity extends AppCompatActivity {

    MaterialButton goBack;
    TextView titleElement;
    WebView webView;
    MaterialButton go_back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        titleElement = findViewById(R.id.title_view);
        webView = findViewById(R.id.webview_element);
        String title = getIntent().getExtras().getString("title");
        String url = "http://"+getIntent().getExtras().getString("url")+".portfioly.net";
        try {
            Boolean isPortfiolyUrl = getIntent().getExtras().getBoolean("isPortfiolyUrl");
            if(isPortfiolyUrl) url = getIntent().getExtras().getString("url");
        }
        catch (Exception e){}

        titleElement.setText(title);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.loadUrl(url);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        goBack = findViewById(R.id.go_back);
        goBack.setOnClickListener(e->{finish();});

    }
}