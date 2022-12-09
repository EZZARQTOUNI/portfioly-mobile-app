package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;
import com.portfioly.R;

public class AboutActivity extends AppCompatActivity {
    LinearLayout about;
    LinearLayout pricacy;
    LinearLayout terms;
    LinearLayout pricing;
    LinearLayout templates;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        about = findViewById(R.id.about);
        pricacy = findViewById(R.id.pricacy);
        terms = findViewById(R.id.terms);
        pricing = findViewById(R.id.pricing);
        templates = findViewById(R.id.templates);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AboutActivity.this,WebViewActivity.class);
                i.putExtra("title","About");
                i.putExtra("url","https://portfioly.net/about");
                i.putExtra("isPortfiolyUrl",true);
                startActivity(i);

            }
        });
        pricacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AboutActivity.this,WebViewActivity.class);
                i.putExtra("title","About");
                i.putExtra("url","https://portfioly.net/privacy-policy");
                i.putExtra("isPortfiolyUrl",true);
                startActivity(i);
            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AboutActivity.this,WebViewActivity.class);
                i.putExtra("title","About");
                i.putExtra("url","https://portfioly.net/terms");
                i.putExtra("isPortfiolyUrl",true);
                startActivity(i);
            }
        });
        pricing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AboutActivity.this,WebViewActivity.class);
                i.putExtra("title","About");
                i.putExtra("url","https://portfioly.net/pricing");
                i.putExtra("isPortfiolyUrl",true);
                startActivity(i);
            }
        });
        templates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AboutActivity.this,WebViewActivity.class);
                i.putExtra("title","About");
                i.putExtra("url","https://portfioly.net/templates");
                i.putExtra("isPortfiolyUrl",true);
                startActivity(i);
            }
        });

    }

}