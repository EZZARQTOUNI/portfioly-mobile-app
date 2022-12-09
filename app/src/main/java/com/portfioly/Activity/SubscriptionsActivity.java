package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.portfioly.R;

public class SubscriptionsActivity extends AppCompatActivity {
    MaterialButton starter;
    MaterialButton platinuim;
    MaterialButton diamond;
    MaterialButton go_back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscriptions);
        starter = findViewById(R.id.starter);
        platinuim = findViewById(R.id.platinuim);
        diamond = findViewById(R.id.diamond);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SubscriptionsActivity.this,PackageActivity.class);
                i.putExtra("subscription","STARTER");
                startActivity(i);
            }
        });

        platinuim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SubscriptionsActivity.this,PackageActivity.class);
                i.putExtra("subscription","PLATINUM");
                startActivity(i);
            }
        });

        diamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SubscriptionsActivity.this,PackageActivity.class);
                i.putExtra("subscription","DIAMOND");
                startActivity(i);
            }
        });
    }
}