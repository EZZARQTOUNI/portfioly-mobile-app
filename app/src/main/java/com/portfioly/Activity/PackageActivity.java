package com.portfioly.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.portfioly.R;

public class PackageActivity extends AppCompatActivity {

    TextView storage_element;
    TextView language_element;
    ImageView premuim_tehems;
    ImageView diamond_theme;
    ImageView advanced_statics;
    MaterialButton go_back;


    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        storage_element = findViewById(R.id.storage_element);
        language_element = findViewById(R.id.language_element);
        premuim_tehems = findViewById(R.id.premuim_tehems);
        diamond_theme = findViewById(R.id.diamond_theme);
        advanced_statics = findViewById(R.id.advanced_statics);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(e->{finish();});
        String subscription = getIntent().getStringExtra("subscription");
        if(subscription.equals("STARTER")){
            storage_element.setText("1GB Storage space");
            language_element.setText("1 Website Language");
        }
        else if(subscription.equals("PLATINUM")){
            storage_element.setText("3GB Storage space");
            language_element.setText("2 Website Languages");
            premuim_tehems.setImageResource(R.drawable.done_icon);
        }
        else if(subscription.equals("DIAMOND")){
            storage_element.setText("5GB Storage space");
            language_element.setText("6 Website Languages");
            premuim_tehems.setImageResource(R.drawable.done_icon);
            advanced_statics.setImageResource(R.drawable.done_icon);
            diamond_theme.setImageResource(R.drawable.done_icon);
        }
    }
}